package division.UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TestEvent implements ActionListener, FocusListener {
	TestView tv = null;
	public TestEvent(TestView tv) {
		this.tv = tv;
	}
	public TestEvent() {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==tv.ts.jtf_msg) {
//			tv.ts.jtf_msg.setText("오늘 스터디 할까?");
			tv.jta.append(tv.ts.jtf_msg.getText());
			tv.jta.append("\n");
			tv.ts.jtf_msg.setText("");

			
		}
		else if(obj==tv.ts.jbtn) {
			tv.ts.jtf_msg.setText("아니");
		}
		else if(obj==tv.jbtn_change) {//너 변경버튼 클릭한거야?
			Container	cont	= tv.getContentPane();//jframe의 가장 밑바닥에 있는 속지에 접근
			cont.remove(tv.ts);
			cont.remove(tv.ts.jtf_msg);
			JPanel jp = new JPanel();
			JButton jbtn = new JButton("테스트");
			jp.add(jbtn);
			tv.add("South",jp);
			cont.revalidate();
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		if(obj==tv.ts.jtf_msg) {
			tv.ts.jtf_msg.setText("");

		}
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
