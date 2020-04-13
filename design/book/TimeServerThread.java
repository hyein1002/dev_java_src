package design.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TimeServerThread extends Thread {
	TimeServer	ts	 = null;
	ObjectInputStream	ois	= null;//읽기-듣기
	ObjectOutputStream	oos	= null;//쓰기-말하기
	String time = "";
	
	public TimeServerThread(TimeServer ts) {
		this.ts = ts;
		try {
			//소켓을 활용하여 printWriter객체를 생성하므로 NullPointException이 발생하지 않도록
			//생성자를 통해서 초기화 해준다.
			//생성자의 기본역할이 전변의 초기화
			//서버에서 클라이언트로 말하기 구현
			//네트워크를 통해서 읽기와 쓰기를 지원하는 패키지 java.io.*
			//반드시 접속을 해온 클라이언트 측의 소켓객체를 통해서 인스턴스화 할 것.
			oos = new ObjectOutputStream(ts.socket.getOutputStream());//말할때
			//클라이언트가 말한 내용을 듣기
			ois  = new ObjectInputStream(ts.socket.getInputStream());//들을때
			//TimeSerever에서 정의한 setTimer메소드에서 현재 장치에 시간정보가져오기
			time = ts.setTimer();
//			oos.writeObject(time);
			//내가 입장하기 전에 있던 친구들의 정보를 새로 입장하는 나에게 알려주기
			for(TimeServerThread tst:ts.globalList) {
				this.send(time);
			}
			ts.globalList.add(this);
			this.broadCasting(time);//원래 있던 친구들에게 내가 온걸 알려주기
		} catch (Exception e) {
			System.out.println("TimeServerThread:"+e.toString());
		}
	}
	//현재 서버에 접속한 모든 사용자에게 시간 정보 방송하기 메소드 구현
	public void broadCasting(String msg) {
		//현재 서버에 몇사람이 있는지 출력하기
		ts.jta_log.append("현재 인원수 : "+ts.globalList.size());
		synchronized(this) {//다른 스레드가 인터셉트 하는 것을 방어하기 위해 동기화처리함
			//원래 있던 사람들에게 나의 정보를 알려주기?
			for(TimeServerThread tst : ts.globalList) {
				tst.send(msg);
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
	public void run() {
		while(true) {
			try {
				//TimeSerever에서 정의한 setTimer메소드에서 현재 장치에 시간정보가져오기
				time = ts.setTimer();
				oos.writeObject(time);
				sleep(1000);//1초동안 지연시키기//이러한 기능을 구현하려면 반드시 쓰레드를 구현해줘야한다.
			}catch(IOException ie) {
				System.out.println(ie.toString());
			} catch (Exception e) {
				System.out.println("다른 스레드가 새치기했을 때");
			}
		}//////////////////end of while
	}//////////////////////end of run
}
