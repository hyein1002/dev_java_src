package study.sungjuk;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javafx.scene.paint.Color;

public class BorderLayoutTest2 {
	JFrame jf				= new JFrame();//디폴트 레이아웃이 BorderLayout[동,서,남,북,중앙]
	JPanel jp_north 		= new JPanel();
	JLabel jlb_su			= new JLabel("인원수");
	JLabel jlb_su2			= new JLabel("인원수");
	JLabel jlb_inwon		= new JLabel("명");
	JTextField jtf_inwon	= new JTextField();
	JTextField jtf_inwon2	= new JTextField(15);
	JPanel jp_south 		= new JPanel();//디폴트 레이아웃이 FlowLayout(중앙에서부터 양쪽으로 펼치면서 배치)
	JPanel jp_west 			= new JPanel();
	JPanel jp_east 			= new JPanel();
	JPanel jp_center 		= new JPanel();
	
	public BorderLayoutTest2() {
		//원래 FlowLayout이었는데 이것을 BorderLayout으로 변경하기
		jp_north.setLayout(new BorderLayout());
		jp_north.setBackground(java.awt.Color.white);
		jp_south.setBackground(java.awt.Color.orange);
		jp_south.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_south.add(jlb_su2);
		jp_south.add(jtf_inwon2);
		jp_south.add(jlb_inwon);
		jp_west.setBackground(java.awt.Color.PINK);
		jp_east.setBackground(java.awt.Color.CYAN);
		jp_center.setBackground(java.awt.Color.blue);
		jp_north.add("West",jlb_su);
		jp_north.add("Center",jtf_inwon);
		jf.add("North",jp_north);
		jf.add("South",jp_south);
		jf.add("West",jp_west);
		jf.add("East",jp_east);
		jf.add("Center",jp_center);
		jf.setSize(500,400);
		//화면에 JFrame을 출력해주세요
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new BorderLayoutTest2();
	}

}
