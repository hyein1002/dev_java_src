package athread.talk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

import thread.bank.Protocol;

public class TalkServerThread extends Thread {
	public TalkServer 	ts 		= null;
	Socket				client 	= null;
	ObjectOutputStream	oos		= null;
	ObjectInputStream	ois		= null;
	String 				chatName = null;//현재 서버에 입장한 클라이언트 스레드 닉네임 저장
	public TalkServerThread(TalkServer ts) {
		this.ts = ts;
		this.client = ts.socket;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			//JTextArea에 현재 입장한 클라이언트 스레드 이름 출력하기
//			ts.jta_log.append("this.getName():"+this.getName()+"\n");
			//나 전에 입장한 사람이 한명도 없을때는 아래 for문 실행기회 없음.
			//내가 입장하기 전에 입장한 클라이언트 스레드 이름 출력하기
			String msg = (String)ois.readObject();
			StringTokenizer	st = new StringTokenizer(msg,"#");
			st.nextToken();//100
			chatName = st.nextToken();//유신
//			msg = st.nextToken();//뭐해?
			ts.jta_log.append(chatName+"님이 입장하였습니다.\n");
			for(TalkServerThread tst : ts.globalList) {
				//이전에 입장해 있는 친구들 정보 받아내기
				String currentName = tst.chatName;
				this.send(100+"#"+currentName);
//				ts.jta_log.append("tst.getName():"+tst.getName()+"\n");
			}
			//현재 서버에 입장한 클라이언트 스레드 추가하기
			ts.globalList.add(this);
			this.broadCasting(msg);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	//현재 입장ㅇ해 있는 친구들 모두에게 메시지 전송하기 구현
	private void broadCasting(String msg) {
		for(TalkServerThread tst : ts.globalList) {
			tst.send(msg);
		}
	}
	//클라이언트에게 말하기 구현
	private void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() {
		boolean isStop = false;
		String msg = null;
		try {
			run_start://라벨이 붙은 반복문을 탈출하도록 해줌다.
			while(!isStop) {//조건에 true를 주면 무한루프에 빠질 수 있으므로 변수를 써준다.
				msg = (String)ois.readObject();
				ts.jta_log.append(msg+"\n");
				ts.jta_log.setCaretPosition(ts.jta_log.getDocument().getLength());
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg != null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
				case 200:{//1:1
//					st.nextToken();
					//보내는 사람
					String nickName = st.nextToken();
					//받는사람
					String otherName = st.nextToken();
					//보내진 메시지
					String msg1 = st.nextToken();
					//클라이언트로 전송하기
					//스레드 중에서 상대 스레드에게만 메세지 전송할 것
					for(TalkServerThread tst : ts.globalList) {
						if(otherName.equals(tst.chatName)) {//내가 선택한 상대가 맞는거야?
							tst.send(200+"#"+nickName+"#"+otherName+"#"+msg1);
						}
					}
					//그리고 나 자신에게도 전송해보자
					this.send(200+"#"+nickName+"#"+otherName+"#"+msg1);
				}break;
				case 201:{//n:n
					String nickName = st.nextToken();
					String message = st.nextToken();
					broadCasting(201+"#"+nickName+"#"+message);
				}break;
				case 202:{//대화명변경
					String nickName = st.nextToken();
					chatName = st.nextToken();
					broadCasting(202+"#"+nickName+"#"+chatName);

				}break;	
				case 500:{//나가기
					String nickName = st.nextToken();
					ts.globalList.remove(this);//내 스레드를 빼달라는 것
					broadCasting(500+"#"+nickName);
				}break run_start;//이렇게 해야 블럭 전체를 빠져나감	
				}//////////end of switch
			}//////////////end of while
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
