package athread.file;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import athread.emoticon.TalkServerThread;

public class FileServer extends JFrame implements Runnable{
	
	
	ServerSocket	server	= null;//경합이 벌어짐. 그렇기때문에 runnable을 붙임. 자원은 하나인데 손님이 계속와서.
	Socket			socket	= null;//그 순간에는 하나.
	List<FileServerThread> globalList = null;//순서대로, 속도가느림
	Map<String, FileServerThread> map = new HashMap<>();//순서가 랜덤. 속도가 빠름
	JTextArea		jta_log	= new JTextArea(10,30);
	JScrollPane		jsp_log	= new JScrollPane(jta_log,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
														,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public void initDisplay() {
		this.setTitle("★★★★서버로그★★★★");
		this.add("Center",jsp_log);
		this.setSize(500,400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		FileServer fs = new FileServer();
		fs.initDisplay();
		Thread th = new Thread(fs);
		th.start();
	}
	
	@Override
	public void run() {
		//손님을 관리하기 위한 백터
		globalList	= new Vector<>();
		boolean isStop = false;
		try {
			server = new ServerSocket(3000);
			jta_log.append("Server Ready....\n");
			while(!isStop) {
				socket = server.accept();
				jta_log.append("client info :"+socket+"\n");
				//생성자 호출이 먼저야? run메소드(듣고말하기) 호출이 먼저야?
				FileServerThread fst = new FileServerThread(this);
				fst.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
