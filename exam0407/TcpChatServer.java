package exam0407;
/*
 * TCP/IP 방식 - 군사목적 : 안전, 신뢰도 높다., 장애가 발생하면 지속적으로 체크 다시 연결
 * - 휴대전화
 *  
 * UDP방식 - 신뢰도는 낮다, 대용량 전송 유리, 보내고 나면 확인 불가, 미디어 전송처리
 * - 편지(보내는이, 받는이)
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//타임서버 만들었던거 참고
public class TcpChatServer extends Thread {
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	ServerSocket server = null;
	Socket client = null;
	@Override
	public void run() {
		try {
			//서버소켓을 생성한다.
			server = new ServerSocket(5005);
			System.out.println("클라이언트를 기다린다...");
		} catch (Exception e) {
			System.out.println(e.toString());//예외 메시지 출력
			e.printStackTrace();//예외 상황을 관리하는 stack메모리 영역에 쌓여있는 메시지를 순차적으로 보여줌
			
		}
		while(true) {
			//클라이언트가 접속 할때까지 기다린다.
			try {
				client = server.accept();//손님이 입장
				System.out.println("server측:"+client.getInetAddress());//getinetaddrss로 확인할수있는정보
				//출력을 먼저 인스턴스화
				//OutputStream os = client.getOutputStream(); - 하위클래스인 아래쪽같이 하는게 좋다. 시험에는 이것처럼 출제함
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
				oos.writeObject("서버에서 보내는 메시지입니다.");
				System.out.println("메시지를 전송했습니다.");
				String msg = (String) ois.readObject();
				System.out.println("클라이언트가 보낸 메세지:"+msg);
			} catch (Exception e) {				
			} finally {
				try {
//					if(oos!=null) 	oos.close();
//					if(client!=null) client.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		TcpChatServer tcs = new TcpChatServer();
		tcs.start();
	}

}
