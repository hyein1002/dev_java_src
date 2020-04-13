package athread.emoticon;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class TalkClient extends JFrame implements ActionListener{
	//////통신과 관련한 전역변수 추가 시작//////
	Socket				socket			= null;
	ObjectOutputStream	oos				= null;//말 하고 싶을 때
	ObjectInputStream	ois				= null;//듣기 할 때
	String				chatName		= null;//바꾼 닉네임 등록
	//////통신과 관련한 전역변수 추가 끝//////
	JPanel				jp_second 		= new JPanel();
	JPanel				jp_second_south	= new JPanel();
	JButton				jbtn_one		= new JButton("1:1대화");
	JButton				jbtn_change		= new JButton("대화명변경");
	JButton				jbtn_font		= new JButton("글자색변경");
	JButton				jbtn_exit		= new JButton("나가기");
	JButton				jbtn_create		= new JButton("방생성");
	JButton				jbtn_emot		= new JButton("이모티콘");

	String				cols[]			= {"대화명"};
	String				data[][]		= new String[0][1];
	DefaultTableModel	dtm				= new DefaultTableModel(data,cols);
	JTable				jtb 			= new JTable(dtm);
	JScrollPane			jsp				= new JScrollPane(jtb);
	JPanel				jp_first 		= new JPanel();
	JPanel				jp_first_south	= new JPanel();
	JTextField			jtf_msg			= new JTextField(20);//south속지 center
	JButton				jbtn_send		= new JButton("전송");//south속지 east
	//글꼴적용이나 스타일 추가를 하려면 스타일 클래스를 매핑해야함.
	StyledDocument		sd_display		= new DefaultStyledDocument(new StyleContext());
	JTextPane			jtp_display		= new JTextPane(sd_display);
	JScrollPane			jsp_display		= new JScrollPane(jtp_display,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
															,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//배경 이미지에 사용될 객체 선언 - JTextArea에 페인팅
	Image				back			= null;
//	LoginForm 			lf				= new LoginForm();//
	LoginForm 			lf				= null;//
	String				fontColor		= "0";
	//이모티콘을 선택하자마자 메시지전송 처리 하려면 TalkClient의 주소번지를 넘긴다.
	EmoticonView 		emov 			= new EmoticonView(this);
	public TalkClient() {
	}
	public TalkClient(LoginForm lf) {
		jbtn_send.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
		jbtn_one.addActionListener(this);
		jbtn_font.addActionListener(this);
		jbtn_create.addActionListener(this);
		jbtn_emot.addActionListener(this);
		jtf_msg.addActionListener(this);
		this.lf = lf;
		initDisplay();
		init();//서버소켓 연결하기
	}
	public void initDisplay() {
		//사용자의 닉네임 받기
		this.setLayout(new GridLayout(1,2));
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp);
		jp_second_south.setLayout(new GridLayout(3,2));
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_emot);
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
		Font font = new Font("돋움",Font.BOLD,25);
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jp_first_south);
		this.add(jp_first);
		this.add(jp_second);
		this.setSize(800,550);
		this.setVisible(true);

	}
//	public static void main(String[] args) {
//		TalkClient tc = new TalkClient();
//		tc.initDisplay();
//		tc.init();
//	}
	//소켓 관련 초기화
	public void init() {
		try {
			//서버측의 ip주소 작성하기
			socket = new Socket("192.168.0.218",3000);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			//initDisplay에서 닉네임이 결정된 추 init메소드가 호출되므로
			//서버에게 내가 입장한 사실을 알린다(말하기)
			this.setTitle(lf.nickName+"님의 대화창");
			oos.writeObject(Protocol.LOGIN+"#"+lf.nickName);
			//서버에 말을 한 후 들을 준비를 한다.
			//서버에서 듣고 말한 내용을 들을 준비한다.
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();
		} catch (Exception e) {
			//예외가 발생 했을 때 직접적인 원인이 되는 클래스 명 출력하기
			System.out.println(e.toString());
		}
	}
	/******************************************************************************
	 * 일반 메시지 전송 구현
	 * @param msg 사용자가 입력한 메시지를 넘길 때
	 * @param imgChoice EmoticonView에서 이모티콘을 선택 했을 때 이미지 정보
	 ******************************************************************************/
	public void message_process(String msg, String imgChoice) {
		if(imgChoice!=null) {//이모티콘을 선택했다.
			msg = "이모티콘";
			try {
				oos.writeObject(201+"#"+lf.nickName+"#"+msg+"#"+fontColor+"#"+emov.imgChoice);
				jtf_msg.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else {//일반메시지를 입력했다.
			try {
				oos.writeObject(201+"#"+lf.nickName+"#"+msg+"#"+fontColor+"#"+"default");
				jtf_msg.setText("");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		if(msg==null||msg.length()==0) {
			msg="이모티콘";
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		if(obj==jtf_msg || obj==jbtn_send) {
			message_process(msg, null);//메시지는 163번에있고 이모티콘은 message_process에서 선택됨
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
				if(name.equals(lf.nickName)) {
					JOptionPane.showInputDialog(this,"자기 자신을 선택했어요. 다른 사람을 선택하세요.");
					return;//actionPerformed 탈출
				}
				//메시지 입력받기
				String msg1 = JOptionPane.showInputDialog(name+"님에게 보낼 메시지를 입력하세요.");
				try {
					oos.writeObject(200+"#"+lf.nickName+"#"+name+"#"+msg1);
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
		else if(obj==jbtn_exit) {
			try {
				oos.writeObject(500+"#"+lf.nickName);//원래는 this.loginform이었음
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
					oos.writeObject(202+"#"+lf.nickName+"#"+chatName);
					lf.nickName = chatName;
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		else if(jbtn_font==obj) {
			JDialog jdl_color = new JDialog();
			jdl_color.setTitle("컬러변경");
			jdl_color.setSize(600,500);
			JColorChooser jcc = new JColorChooser();
			ColorSelectionModel csm = jcc.getSelectionModel();
			//선택한색상처리
			ChangeListener cl = new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					Color color = jcc.getColor();
					fontColor = String.valueOf(color.getRGB());
				}
			};
			csm.addChangeListener(cl);
//			jta_display.setForeground(fontColor);
			jdl_color.add(jcc);
			jdl_color.setVisible(true);
		}
		else if(emov.equals(obj)||jbtn_emot.equals(obj)) {
			emov.initDisplay();
			
		}
	}
}
