package design.book;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeClient extends Thread {
	//서버에서 청취한 현재 시간 정보를 담을 변수
	JFrame 	jf		 	= new JFrame();
	JLabel	jlb_time 	= null;
	JLabel	jlb_time2 	= new JLabel("현재시간",JLabel.CENTER);
	
	//run메소드 보다 TimeClient 생성자가 반드시 먼저 실행되어야 한다.
	public TimeClient() {
		jf.add("North",jlb_time2);
		jf.setVisible(true);
	}
	public TimeClient(JLabel jlb_time) {
		this.jlb_time = jlb_time;
	}
	/*
	 * 객체주입관계는 BookApp.java에서 TimeClient(JLable)생성자를 호출함.
	 */
	public void run() {
		String time = null;
		Socket socket = null;
		ObjectInputStream	ois	= null;//읽기-듣기
		ObjectOutputStream	oos	= null;//쓰기-말하기
		try {
			socket  = new Socket("192.168.0.218",3000);
			oos = new ObjectOutputStream(socket.getOutputStream());//말할때
			//클라이언트가 말한 내용을 듣기
			ois  = new ObjectInputStream(socket.getInputStream());//들을때
			while(true) {
				time = (String)ois.readObject();
				if(time != null) {
//					System.out.println(time);
					Font f = new Font("sans serif",Font.BOLD,20);
					jlb_time.setFont(f);
					jlb_time.setText(time);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("앗~~...");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("타임 서버에 접속할 수 없습니다.");
		}
	}
	public static void main(String[] args) {
		TimeClient tc = new TimeClient();
		tc.start();
	}
}
