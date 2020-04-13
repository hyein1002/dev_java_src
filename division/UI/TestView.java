package division.UI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestView extends JFrame {
	TestSouth ts = new TestSouth();
	JPanel	jp_north = new JPanel();
	JButton	jbtn_change = new JButton("변경");
	JTextArea	jta = new JTextArea("로그창");
	//ts를 넘기면 TestSouth만 누릴 수 있지만 this를 넘기면 TestView와 TestSouth
	//모두를 누릴 수 있다.
	TestEvent te = new TestEvent(this);
	public TestView() {
		initDisplay();
	}
	public void initDisplay() {
		//코드의 가독성이 좋아짐
		ts.jtf_msg.addActionListener(te);
		ts.jtf_msg.addFocusListener(te);
		ts.jbtn.addActionListener(te);
		jbtn_change.addActionListener(te);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add(jbtn_change);
		this.add("North",jp_north);
		this.add("South",ts);
		this.add("Center",jta);
		this.setTitle("클래스쪼개기");
		this.setSize(500,600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestView();
	}

}
