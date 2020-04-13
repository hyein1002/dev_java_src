package thread.bank;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import design.book.TimeServerThread;
//인터페이스를 추가하면 반드시 구현체 클래스가 되기 위해서 추상메소드를 재정의해야함.-규칙
//run메소드를 반드시 오버라이딩 해야 한다.
//이 메소드 안에서는 무엇을 하지? - 기다려[Thread.sleep(1000)], 듣기[ois.readObject()]와 말하기[oos.writeObject("메세지")]
public class ServerBank extends JFrame implements Runnable{
	//전역변수 선언하기 
	Socket socket = null;//복사본이 아니라 원본을 사용해야 하니까 반드시 null로 초기화
	int port = 3000;//포트번호는 강제로 하기 전까지 못바꿈
	//서버소켓은 사용자가 접속해 오기를 기다립니다. 언제까지 기다려야 할지 알 수 없죠... 그러니까 계속 열여놓음
	ServerSocket server	= null;	
	JTextArea jta_log = new JTextArea(12,30);
	JScrollPane jsp_log = new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String				cols[]			= {"접속시간","접속자","메시지","상태"};
	String				data[][]		= new String[0][4];
	DefaultTableModel	dtm_history 	= new DefaultTableModel(data,cols);
	JTable 				jtb_history		= new JTable(dtm_history);
	JScrollPane 		jsp_history 	= new JScrollPane(jtb_history,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	//클라이언트에서 접속해온 사용자에 대한 스레드를 담기 위한 List선언
	//일단 선언만 해두었다가 서버소켓이 개설되기 직전에 인스턴스화 함.
	//List는 인터페이스이므로 단독으로 인스턴스화 불가하니까 구현체 클래스 중에서 
	//여러 사람을 손실없이 관리할 수 있는 Vector객체를 생성할 것.
	//클라이언트가 접속을 했을 때 스레드를 가동시킴.
	//ServerBankThread가 서버측에서 생성한 클래스이지만 그 안에 담긴 정보는 클라이언트에 대한
	//정보를 담고 있다.
	//클라이언트가 접속 성공하면 일반소켓에게 서버소켓이 수집한 정보를 넘김.
	//이 정보를 넘겨 받으면 그 안에 클라이언트 정보가 담김.
	//스레드가 생성되었을 떄 그 때 Vector안에 add처리 할 것.-그래야 그사람 정보를 유지가능
	//담는 작업은 스레드가 생성되었을 때 거의 동시에 일어나는 사건이므로 생성자 안에서 처리함.
	List<ServerBankThread> globalList = null;//멀티스레드
	ServerBankThread sbt = null;//accept했을 떄 인스턴스화한다.
	CustomerDao	cDao = new CustomerDao();
	//메인메소드는 entry point이다
	//메인 스레드라고도 한다. - 경합 벌어진다.
	//화면처리와 서버 개통하기 사이에 경합이 벌어질 수 있다.
	//스레드 클래스의  run메소드는 어떻게 호출하지?
	public static void main(String[] args) {
		ServerBank sb = new ServerBank();
		sb.initDisplay();
//		sb.start();왜냐하면 Thread를 상속 받지 않았으니까 - 나는 스레드가 아님. 그래서 스타트를 직접 호출하지 못함.
		//어떻게 해결하지? - 일단  Thread를 인스턴스화 하고 생성자에 구현체 클래스를 넣어줌
		Thread th = new Thread(sb);
		th.start();//이렇게 run메소드를 호출해줌
		
	}

	@Override
	public void run() {
		JOptionPane.showMessageDialog(this, "run호출 성공-스레드 가동 중");
		globalList = new Vector<>();
		try {
			server = new ServerSocket(port);//가게 문 열고 기다리는중... 손님이 언제 올까(ip,port)

		} catch (Exception e) {
			e.printStackTrace();
		}
		jta_log.append("ServerBank Started Succeddfully...\n");
		while(true) {//무한루프 - while탈출 불가 , 전기를 끊었을 때 빠져나감
			try {
				//클라이언트측에서 접속해온 정보를 client소켓에게 넘김.
				socket = server.accept();//대기//홀에서 안녕하세요 빕스입니다.
				jta_log.append("New Client connected...."+socket.toString()+"\n");//여기에 찍히는 주소번지가 클라이언트 정보
				sbt = new ServerBankThread(this);//접수가 된 다음에 thread를 생성//this는 serverbank자신을 가리킴-원본-생성자호출
				sbt.start();//스레드에 구현된 run메소드 호출
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void initDisplay() {
		//윈도우 창에서 X버튼 클릭했을 때 이벤트 처리
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {//사용 자원 반납하기
					server.close();
					socket.close();
					System.exit(0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		this.setTitle("ServerBank 로그창");
		this.add("West",jsp_log);
		this.add("Center",jsp_history);
		this.setSize(900,400);
		this.setVisible(true);
	}
}
