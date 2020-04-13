package design.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * 자바에서는 단일 상속만 가능함.
 * 다중 상속이 필요할 땐 인터페이스로 대신한다.
 * 여기서 처럼 JFrame을 이미 상속받은 경우 Thread를 또 상속받을 수 없다.
 * 이런 경우를 지원하기 위해서 Runnable이라는 인터페이스를 지원함.
 */
public class TimeServer extends JFrame implements Runnable {//계속 제공하는거니까 thread 상속받기
	Socket socket = null;//복사본이 아니라 원본을 사용해야 하니까 반드시 null로 초기화
	int port = 3000;//포트번호는 강제로 하기 전까지 못바꿈
	//서버소켓은 사용자가 접속해 오기를 기다립니다. 언제까지 기다려야 할지 알 수 없죠... 그러니까 계속 열여놓음
	ServerSocket server	= null;	
	JTextArea jta_log = new JTextArea();
	JScrollPane jsp_log = new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	List<TimeServerThread> globalList = null;
	TimeServerThread tst = null;

	public TimeServer() {

	}
	public String setTimer() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		return (hour<10?"0"+hour:""+hour)+":"+
				(min<10?"0"+min:""+min)+":"+
				(sec<10?"0"+sec:""+sec);
	}
	public void run() {//지연처리가능, 들은 정보를 내보낼 수 있다., 1초에 한번씩 시간정보를 내보낸다.
		globalList = new Vector<>();//멀티스레드의 경합에서 안전하기때문에 백터사용
		try {
			server = new ServerSocket(port);//가게 문 열고 기다리는중... 손님이 언제 올까(ip,port)

		} catch (Exception e) {
			e.printStackTrace();
		}
		jta_log.append("TimeServer Started Succeddfully...\n");
		while(true) {//무한루프 - while탈출 불가 , 전기를 끊었을 때 빠져나감
			try {
				//클라이언트측에서 접속해온 정보를 client소켓에게 넘김.
				socket = server.accept();//대기//홀에서 안녕하세요 빕스입니다.
				jta_log.append("New Client connected...."+socket.toString()+"\n");//여기에 찍히는 주소번지가 클라이언트 정보
				tst = new TimeServerThread(this);
				tst.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	/*
	 * main메소드 안에서 만들어진 정보를 run 메소드에서 사용하려면 생성자를 통해서
	 * 초기화를 해주어야 한다.
	 * 복사본을 사용하는 것이 아니라 메인에서 접속한 클라이언트의 소켓원본을 사용해야 하니까.
	 */
	public static void main(String[] args) {
	TimeServer ts = new TimeServer();
	ts.initDisplay();//화면을 그리고 난 뒤 스레드 대기를 타도록 해야함.
	Thread th = new Thread(ts);
	th.start();//스레드의 run메소드를 호출하는 메소드//쓰레드를 동작시켜주는 start
	}/////////////////////end of main
	public void initDisplay() {
		this.setTitle("TimeServer 로그");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
}
