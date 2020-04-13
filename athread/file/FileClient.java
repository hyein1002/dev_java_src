package athread.file;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import athread.emoticon.Protocol;
import athread.emoticon.TalkClientThread;

public class FileClient extends JFrame{
	Socket		socket	= null;
	JTextArea	jta_log	= new JTextArea(10,30);
	JScrollPane	jsp_log	= new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
						,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
	public void initDisplay() {
		this.setTitle("★★★★클라이언트★★★★");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.218",3000);
			FileClientThread fct = new FileClientThread(this);
			fct.start();
		} catch (Exception e) {
			//예외가 발생 했을 때 직접적인 원인이 되는 클래스 명 출력하기
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		FileClient fs = new FileClient();
		fs.initDisplay();
		fs.init();
	}
}
