package thread.bank;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import design.book.TimeServerThread;

public class ServerBankThread extends Thread {
	ServerBank 			sb 			= null;
	ObjectInputStream 	ois 		= null;//듣다가 끊길 수 있으니까 예외처리
	ObjectOutputStream 	oos 		= null;//말하다가 멈출 수 있으니까 try...catch
	String 				nickName 	= null;
	String currentName =null;
	String msg2 = null;
	//생성자의 파라미터 자리에 있는 클래스와 의존관계에 있다. 그래서 다오를 서버뱅크에서 인스턴스화해도 관계없다.
	public ServerBankThread(ServerBank sb) {
		this.sb = sb;
		try {
			oos = new ObjectOutputStream(sb.socket.getOutputStream());//sb.socket에 대한 네트워크 정보를 ois에 담아주기
			ois = new ObjectInputStream(sb.socket.getInputStream());
			//서버측에서 클라이언트가 보낸 메시지 청취완료
			String msg = (String)ois.readObject();
			sb.jta_log.append(msg+"\n");
			StringTokenizer st = null;
			//msg 100#진아 100#정훈 100#진완
			//스레드마다 다른 사람을 관리/구분하기 때문에 스레드를 통해서 구분해야하니까 닉네임은 전역변수로 선언해야함
			if(msg!=null) {
				st = new StringTokenizer(msg,"#");
			}	
			st.nextToken();//100
			nickName = st.nextToken();//진아, 정훈, 진완
			login(nickName);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("[[Exception]]"+e.toString());
		}
	}
	public ServerBankThread() {
		// TODO Auto-generated constructor stub
	}
	//로그인 처리 구현
	public void login(String mem_id) {
		Map<String, Object> rMap = null;
		rMap = sb.cDao.login(mem_id);
		if(rMap!=null) {//로그인 성공했을 때
			
			for(ServerBankThread sbt : sb.globalList) {
				currentName = rMap.get("mem_name").toString();
				msg2 = 100+"#"+currentName;
				this.send(sbt.msg2);//100 # 뒤에가 닉네임이라고 알려주는것
			}
			Vector<String> v = new Vector<>();
			v.add(setTimer());
			v.add(nickName);
			v.add(100+"#"+rMap.get("mem_name").toString());
			v.add("로그인");
			sb.dtm_history.addRow(v);
			sb.globalList.add(this);//SeverBankThread
			this.broadCasting(100+"#"+rMap.get("mem_name").toString());//100#진완
		}
		else {//실패했을 때
			for(ServerBankThread sbt : sb.globalList) {
				this.send(110+"#"+mem_id+"님은 로그인 실패입니다.");//100 # 뒤에가 닉네임이라고 알려주는것
			}
			
			this.broadCasting(110+"#"+mem_id+"님은 로그인 실패입니다.");//100#진완
		}
	}
	public void broadCasting(String msg) {
		//현재 서버에 몇사람이 있는지 출력하기
		sb.jta_log.append("현재 인원수 : "+sb.globalList.size());
		synchronized(this) {//다른 스레드가 인터셉트 하는 것을 방어하기 위해 동기화처리함
			//원래 있던 사람들에게 나의 정보를 알려주기?
			for(ServerBankThread sbt : sb.globalList) {
				sbt.send(msg);
			}
		}
	}///////////////end of broadCasting
	
	//서버에서 클라이언트에게 전송하는 메세지 구현
	public void send(String msg) {
		try {
			oos.writeObject(msg);
		} catch (Exception e) {
			e.printStackTrace();//예외 발생 시 에러메시지 history 출력
		}
	}///////////////end of send
	//서버는 클라이언트와는 달리 들은 내용을 즉시 전달해야 하므로 
	//run메소드에서 듣기(ois)와 말하기(oos)를 동시에 진행함.
	public void run() {
		sb.jta_log.append(sb.socket+"님의 정보를 관리해야 함.-생성자에서 추가");
		
		
	}
	public String setTimer() {
		Calendar cal = Calendar.getInstance();
		int yyyy = cal.get(Calendar.YEAR);
		int mm = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		return yyyy+"-"+
				(mm<10?"0"+mm:""+mm)+"-"+
				(day<10?"0"+day :""+day )+" "+
				(hour<10?"0"+hour:""+hour)+":"+
				(min<10?"0"+min:""+min)+":"+
				(sec<10?"0"+sec:""+sec);
	}

}
