package athread.talk;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TalkClient extends JFrame implements ActionListener{
	//////통신과 관련한 전역변수 추가 시작//////
	Socket				socket			= null;
	ObjectOutputStream	oos				= null;//말 하고 싶을 때
	ObjectInputStream	ois				= null;//듣기 할 때
	String				nickName		= null;//닉네임 등록
	String				chatName		= null;//바꾼 닉네임 등록
	//////통신과 관련한 전역변수 추가 끝//////
	JPanel				jp_second 		= new JPanel();
	JPanel				jp_second_south	= new JPanel();
	JButton				jbtn_one		= new JButton("1:1대화");
	JButton				jbtn_change		= new JButton("대화명변경");
	JButton				jbtn_font		= new JButton("글자색변경");
	JButton				jbtn_exit		= new JButton("나가기");
	String				cols[]			= {"대화명"};
	String				data[][]		= new String[0][1];
	DefaultTableModel	dtm				= new DefaultTableModel(data,cols);
	JTable				jtb 			= new JTable(dtm);
	JScrollPane			jsp				= new JScrollPane(jtb);
	JPanel				jp_first 		= new JPanel();
	JPanel				jp_first_south	= new JPanel();
	JTextField			jtf_msg			= new JTextField(20);//south속지 center
	JButton				jbtn_send		= new JButton("전송");//south속지 east
	JTextArea			jta_display		= null;
	JScrollPane			jsp_display		= null;
	//배경 이미지에 사용될 객체 선언 - JTextArea에 페인팅
	Image				back			= null;
	
	public TalkClient() {
		jbtn_send.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_one.addActionListener(this);
		jbtn_font.addActionListener(this);
		jtf_msg.addActionListener(this);
	}
	public void initDisplay() {
		//사용자의 닉네임 받기
		nickName = JOptionPane.showInputDialog("닉네임을 입력하세요.");
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_one);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		jp_first.setLayout(new BorderLayout());
		jp_first_south.setLayout(new BorderLayout());
		jp_first_south.add("Center",jtf_msg);
		jp_first_south.add("East",jbtn_send);
		back = getToolkit().getImage("src\\thread\\talk\\n.jpg");
		jta_display = new JTextArea() {
			public void paint(Graphics g) {
				g.drawImage(back, 0, 0, this);
				Point p = jsp_display.getViewport().getViewPosition();
				g.drawImage(back, p.x, p.y, null);
				paintComponent(g);
			}
		};
		jta_display.setLineWrap(true);
		jta_display.setOpaque(false);//투명도 true는 날아가서 배경이 안보임
		Font font = new Font("돋움",Font.BOLD,25);
		jta_display.setFont(font);
		jsp_display = new JScrollPane(jta_display); 
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setTitle(nickName+"님의 대화창");
		this.setSize(800,550);
		this.setVisible(true);

	}
	public static void main(String[] args) {
		TalkClient tc = new TalkClient();
		tc.initDisplay();
		tc.init();
	}
	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.218",3000);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 추 init메소드가 호출되므로
			//서버에게 내가 입장한 사실을 알린다(말하기)
			oos.writeObject(100+"#"+nickName);
			//서버에 말을 한 후 들을 준비를 한다.
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생 했을 때 직접적인 원인이 되는 클래스 명 출력하기
			System.out.println(e.toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		if(obj==jtf_msg) {
			try {
				oos.writeObject(201+"#"+nickName+"#"+msg);
				jtf_msg.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if(obj==jbtn_one) {
			//상대를 선택하기
			int row = jtb.getSelectedRow();
			if(row==-1) {//-1:end of file
				JOptionPane.showInputDialog(this,"상대를 선택하세요");
				return;//actionPerformed 탈출
			}
			else {//상대가 다른 사람이 아닌 나 자신일때는 배제한다.
				String name = (String)dtm.getValueAt(row, 0);
				if(name.equals(nickName)) {
					JOptionPane.showInputDialog(this,"자기 자신을 선택했어요. 다른 사람을 선택하세요.");
					return;//actionPerformed 탈출
				}
				//메시지 입력받기
				String msg1 = JOptionPane.showInputDialog(name+"님에게 보낼 메시지를 입력하세요.");
				try {
					oos.writeObject(200+"#"+nickName+"#"+name+"#"+msg1);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
			
			//선택한 대화 상대 초기화
			jtb.clearSelection();
//			try {
//				chatName = JOptionPane.showInputDialog("1:1 대화를 할 사람의 닉네임을 입력하세요.");
//				oos.writeObject(200+"#"+nickName+"#"+chatName+"#"+msg);
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
		}
		else if(obj==jbtn_send) {
			jta_display.append(msg);
		}
		else if(obj==jbtn_exit) {
			try {
				oos.writeObject(500+"#"+this.nickName);
				//자바가상머신과 연결고리 끊기
				System.exit(0);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		else if(obj==jbtn_change) {
			chatName = JOptionPane.showInputDialog("변경 할 닉네임을 입력하세요.");
			this.setTitle(chatName+"님의 대화창");
			if(chatName == null || chatName.trim().length()<1) {
				chatName = JOptionPane.showInputDialog(this,"변경 할 닉네임을 입력하세요.","INFO",JOptionPane.INFORMATION_MESSAGE);
				return;
			}else {
				try {
					oos.writeObject(202+"#"+nickName+"#"+chatName);
					nickName = chatName;
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}

		}
		
	}


}
