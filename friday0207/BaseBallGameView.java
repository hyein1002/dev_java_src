package friday0207;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BaseBallGameView extends JFrame{
	BaseBallGameLogic bbLogic = new BaseBallGameLogic();//생성자가 호출되는게 아니므로 null을 주게되면 null point가 나옴//***************
	//화면과 관련된 코드 추가
//	JFrame		jf_bbgame	= new JFrame();
	//JMenuBar는 JFrame안에 메뉴바를 추가하기
	JMenuBar 	jmb_bbgame 	= new JMenuBar();
	//JMenu는 JMenuBar안에 들어갈 메뉴 추가하기
	JMenu	 	jm_game		= new JMenu("게임");
	JMenu	 	jm_info		= new JMenu("도움말");
	//JMenuItem은 JMenu아래에 들어갈 메뉴내용들
	JMenuItem 	jmi_next	= new JMenuItem("다음게임");
	JMenuItem 	jmi_clear 	= new JMenuItem("지우기");
	JMenuItem 	jmi_dap		= new JMenuItem("정답");
	JMenuItem 	jmi_oracle	= new JMenuItem("오라클테스트");
	JMenuItem 	jmi_exit 	= new JMenuItem("나가기");
	JTextArea	jta_display = new JTextArea("");
	JScrollPane	jsp_display	= new JScrollPane(jta_display);
	JTextField 	jtf_input 	= new JTextField();
	JButton		jbtn_next	= new JButton("다음게임");	
	JButton		jbtn_clear	= new JButton("지우기");	
	JButton		jbtn_dap	= new JButton("정답");	
	JButton		jbtn_exit	= new JButton("나가기");
	//JTextArea와 JTextField를 붙일 속지 추가하기
	JPanel 		jp_center	= new JPanel();
	//버튼 4개를 붙일 속지 추가하기
	JPanel 		jp_east		= new JPanel();
	String mem_name = null;
	String result[] = null;
	//화면과 관련된 코드 추가 끝
	//전역변수를 선언하여 문제를 해결할 수 있어요
	//생성자의 파라미터로 배열의 주소번지를 받게되는데 이 값이 사용하는 곳이
	//생성자 안에서가 아니라 initDisplay메소드 안에 setTitle메소드에서
	//사용해야 하기 때문에 파라미터로 넘어온 값을 반드시 전변과 초기화 해야합니다.
	//파라미터 자리는 변수를 선언하는 자리입니다.
	//초기화는 일어나지 않지요.-생성하는 자리가 아닙니다.
	public BaseBallGameView(String result[]) {
		this.result = result;
		if(this.result==null) {
			this.result = new String[2];
			this.result[0] = "";//0번에서는 이름이랑 경고메세지가 담김
			this.result[1] = "";			
		}
		System.out.println("이름: "+this.result[0]+", 아이디: "+this.result[1]);
	//생성자 안에서 메소드를 호출하면 인스턴스화 없이도 호출이 가능함.
		if(this.result[0]!=null) {
			initDisplay();
			bbLogic.ranCom();
		}
	}
	///////////화면처리 시작////////////////////
	public void initDisplay() {
		//이벤트 소스와 이벤트 처리 클래스 매핑
		/* 이전 버전에서는 friday0131소스 이벤트 처리를 직접 하였다. - ActionListner 했다.(event에다가)
		 * 
		 */
		//디폴트 생성자는 JVM이 만들어줄 수 있어요.왜냐하면 파라미터가 없기 때문이죠.
		//파라미터가 있는 생성자는 내가 대신해 줄 수 없다. 왜냐하면 네 생각을 난 알 수 없으니까.
		BaseBallGameEvent bbEvent = new BaseBallGameEvent(this);//원본이 파라미터로 들어옴//*****************
		jbtn_exit.addActionListener(bbEvent);
		jbtn_next.addActionListener(bbEvent);
		jmi_exit.addActionListener(bbEvent);
		jmi_dap.addActionListener(bbEvent);
		jmi_oracle.addActionListener(bbEvent);
		jmi_next.addActionListener(bbEvent);
		jmi_clear.addActionListener(bbEvent);
		jtf_input.addActionListener(bbEvent);
		jbtn_clear.addActionListener(bbEvent);
		jbtn_dap.addActionListener(bbEvent);
//		System.out.println("화면 처리 시작");
		jp_center.setLayout(new BorderLayout());
		jp_center.add("Center",jsp_display);
		jp_center.add("South",jtf_input);
		jbtn_next.setBackground(new Color(158,9,9));
		jbtn_next.setForeground(new Color(212,212,212));
		jbtn_clear.setBackground(new Color(7,84,170));
		jbtn_clear.setForeground(new Color(212,212,212));
		jbtn_dap.setBackground(new Color(19,99,57));
		jbtn_dap.setForeground(new Color(212,212,212));
		jbtn_exit.setBackground(Color.black);
		jbtn_exit.setForeground(new Color(212,212,212));
		jp_east.setLayout(new GridLayout(4,1));
		jp_east.add(jbtn_next);
		jp_east.add(jbtn_clear);
		jp_east.add(jbtn_dap);
		jp_east.add(jbtn_exit);
//		jf_bbgame.add("Center",jp_center);//원래는 jframe을 변수로 선언해서 사용해야하는데 상속관계에 있으므로 아래처럼 이름없이 선언가능하다.
		add("Center",jp_center);
		add("East",jp_east);
		///////////메뉴바 추가시작//////////
		jm_game.add(jmi_next);
		jm_game.add(jmi_clear);
		jm_game.add(jmi_dap);
		jm_game.add(jmi_oracle);
		jm_game.add(jmi_exit);
		//메뉴를 메뉴바에 붙여요
		jmb_bbgame.add(jm_game);
		jmb_bbgame.add(jm_info);
		setJMenuBar(jmb_bbgame);
		///////////메뉴바 추가 끝//////////
		setTitle("야구숫자게임"+"-"+result[0]+"["+result[1]+"]");
		setSize(600, 400);
		setVisible(true);
		
	}
	///////////화면처리 끝////////////////////
	public static void main(String[] args) {
		new BaseBallGameView(null);
	}
}
