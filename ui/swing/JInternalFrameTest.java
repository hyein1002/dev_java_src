package ui.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class JInternalFrameTest implements ActionListener {
	JFrame jf = new JFrame();
	//이 아이를 중앙에 배치하고 JInternalFrame으로 내부에 띄워줄 창을 만들면 
	//JFrame창 안에 여러개의 내부 창을 관리할 수 있다.
	JDesktopPane jdp = new JDesktopPane();
	JButton jbtn_zip	= new JButton("우편번호찾기");
	JButton jbtn_dong	= new JButton("동찾기");
	JButton jbtn_si	= new JButton("시찾기");
	JPanel jp_north = new JPanel();
	
	public JInternalFrameTest() {
		initDisplay();
	}//////////////////////////////end of 생성자
	
	public void initDisplay() {
		jf.setTitle("윈도우 창에 내부 프레임 생성하기");
		//jp_north속지에 우편번호 찾기 버튼을 오른쪽에서 부터 붙인다.
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add(jbtn_zip);
		jp_north.add(jbtn_dong);
		jp_north.add(jbtn_si);
		//북쪽에는 jp_north 속지를 붙여줄까
		jf.add("North",jp_north);
		//jf중앙에 JDesktopPane속지를 붙여줄래
		jf.add("Center",jdp);
		jf.setSize(700,800);
		jf.setVisible(true);
		//내 안에 actionPerformed메소드를 구현하였다.
		//이 코드가 있어야 자동(누가?JVM)으로 actionPerformed메소드를 호출해줌
		jbtn_zip.addActionListener(this);//두번쓰면 두번 호출되고 세번쓰면 세번 호출된다.		
		jbtn_dong.addActionListener(this);//두번쓰면 두번 호출되고 세번쓰면 세번 호출된다.		
		jbtn_si.addActionListener(this);//두번쓰면 두번 호출되고 세번쓰면 세번 호출된다.		

	}//////////////////////////////////////end of initDisplay
	
	public static void main(String[] args) {
		new JInternalFrameTest();
	}//////////////////////////////////////end of main
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==jbtn_zip) {
//			System.out.println("우편번호 찾기 호출 성공");
			InnerFrame inn = new InnerFrame(jbtn_zip.getText(),true,true,true,true);
			InnerFrame inn_dong = new InnerFrame(jbtn_dong.getText(),true,true,true,true);
			InnerFrame inn_si = new InnerFrame(jbtn_si.getText(),true,true,true,true);
			jdp.add(inn);
			jdp.add(inn_dong);
			jdp.add(inn_si);
		}
	}///////////////////////////////////////end of actionPerformed
}
class InnerFrame extends JInternalFrame {
	InnerFrame(String title, boolean resizable, boolean closable, 
			boolean maximizable	, boolean iconifiable)
	{
		//아빠 생성자 호출 문장  - 아빠 객체가 가진 기능들을 온전히 누릴 수 있다.
		super(title,resizable,closable,maximizable,iconifiable);
		this.setSize(300,200);
		this.setVisible(true);
		this.setTitle(title);
	}
}
