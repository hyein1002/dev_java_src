package athread.talk3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class TalkServerThread extends Thread {
	public TalkServer ts = null;
	Socket client = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String chatName = null;//현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	String g_title = null;//현재 그 사람의 대화방명
	int g_current = 0;//현재 인원수 담기
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
		this.client = ts.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			String msg = (String)ois.readObject();
			ts.jta_log.append(msg+"\n");
			StringTokenizer st = null;
			if(msg !=null) {//사용자가 보낸 메시지가 도착한거야?
				st = new StringTokenizer(msg,Protocol.SEPERATOR);
			}
			if(st.hasMoreTokens()) {
				st.nextToken();//100
				chatName = st.nextToken();
				g_title = st.nextToken();//대기
			}
			for(TalkServerThread tst:ts.globalList) {
			//이전에 입장해 있는 친구들 정보 받아내기
				String currentName = tst.chatName;
				String currentState = tst.g_title;
				this.send(Protocol.WAIT
						+Protocol.SEPERATOR+currentName
						+Protocol.SEPERATOR+currentState
						);
			}
			//새로운 친구가 로그인을 하면 기존에 생성된 단톡을 공유한다.(목록 갱신)
			//방목록은 어디에 저장되고 있는거지? List<Room>
			for(int i=0;i<ts.roomList.size();i++) {
				Room room = ts.roomList.get(i);
				String title = room.title;
				g_title = title;//전역변수에 반드시 초기화 할것(초기화 완결편)
				int current = 0;//현재원정보
				if(room.userList!=null && room.userList.size()!=0) {
					current = room.userList.size();
				}
				g_current = current;
				this.send(Protocol.ROOM_LIST
						+Protocol.SEPERATOR+g_title
						+Protocol.SEPERATOR+g_current
						);
			}///////////////end of for//////////////
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
	public void broadCasting(String msg) {
		synchronized(this) {//동기화 블록을 씌운다. 먼저 선점한 클래스가 
		//해당 스레드에 대한 lock flag값을 가지게 되고 해당 스레드가 안전하게 진행,종료
		//될때까지 인터셉트를 방어한다.	
			for(TalkServerThread tst:ts.globalList) {
				tst.send(msg);
			}
		}
	}
	//클라이언트에게 말하기 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		String msg = null;
		boolean isStop = false;
		try {
			//while(true) {//무한루프에 빠질 수 있다.
			run_start:
			while(!isStop) {
				msg = (String)ois.readObject();
				ts.jta_log.append(msg+"\n");
				ts.jta_log.setCaretPosition
				(ts.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg !=null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
					case Protocol.ROOM_CREATE:{
						String roomTitle = st.nextToken();
						String currentNum = st.nextToken();
						Room room = new Room(roomTitle, Integer.parseInt(currentNum));
						ts.roomList.add(room);
						this.broadCasting(Protocol.ROOM_CREATE
								        +Protocol.SEPERATOR+roomTitle
								        +Protocol.SEPERATOR+currentNum								        
								);
					}break;
					//130#하늘공원#나신입
					case Protocol.ROOM_IN:{
						String roomTitle = st.nextToken();
						String nickName = st.nextToken();
						for(int i=0;i<ts.roomList.size();i++) {
							Room room = ts.roomList.get(i);
							if(roomTitle.equals(room.title)) {//단톡명이 같니?
								g_title = roomTitle;
								g_current = room.current+1;
								//현재 인원수로 업데이트 처리
								room.setCurrent(g_current);
								room.userList.add(this);
								room.nameList.add(nickName);
							}
						}//방정보에 대한 업데이트
						//방에 있는 친구들에게 메시지를 전송 - 
						for(int i=0;i<ts.roomList.size();i++) {
							Room room = ts.roomList.get(i);
							String title = room.title;
							g_title = title;
							int current = 0;
							if(room.userList !=null &&  room.userList.size()!=0) {
								//현재 대화방에 들어온 사용자 스레드의 갯수가 현재 인원 수가 됨.
								current = room.userList.size();
							}
							//유재석과 다른 이름 중에서 방이름이 같은 경우 클라이언트로 전송함.
							for(int j=0;j<room.nameList.size();j++) {
								if(!nickName.equals(room.nameList.get(j))) {
									if(roomTitle.equals(room.title)) {
										TalkServerThread tst = room.userList.get(j);
										tst.send(Protocol.ROOM_INLIST
												+Protocol.SEPERATOR+g_title
												+Protocol.SEPERATOR+g_current
												+Protocol.SEPERATOR+nickName
												);
									}
								}
							}
							
						}///////현재 방에 있는 친구들 목록  뿌려주기
						broadCasting(Protocol.ROOM_IN
								+Protocol.SEPERATOR+g_title
								+Protocol.SEPERATOR+g_current
								+Protocol.SEPERATOR+this.chatName
								);
					}break;
					case 500:{
						String nickName = st.nextToken();
						ts.globalList.remove(this);
						broadCasting(500
								+"#"+nickName);
					}break run_start;
				}/////////////end of switch
			}/////////////////end of while			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}/////////////////////////end of run
}
