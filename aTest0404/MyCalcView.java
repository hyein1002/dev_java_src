package aTest0404;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalcView extends JFrame implements ActionListener{
	
	//선언부
	//JFrame - BorderLayout
	JTextField	jtf_account	= new JTextField(20);//North
	JPanel		jp			= new JPanel();
	JPanel		jp_center	= new JPanel();
	JButton		jbtn_b1		= new JButton(" ");
	JButton		jbtn_b2		= new JButton(" ");
	JButton		jbtn_ac		= new JButton("AC");
	JButton		jbtn_bs		= new JButton("←");
	JButton		jbtn_seven	= new JButton("7");
	JButton		jbtn_eight	= new JButton("8");
	JButton		jbtn_nine	= new JButton("9");
	JButton		jbtn_plus	= new JButton("+");
	JButton		jbtn_four	= new JButton("4");
	JButton		jbtn_five 	= new JButton("5");
	JButton		jbtn_six	= new JButton("6");
	JButton		jbtn_minus	= new JButton("-");
	JButton		jbtn_one	= new JButton("1");
	JButton		jbtn_two	= new JButton("2");
	JButton		jbtn_three	= new JButton("3");
	JButton		jbtn_mul	= new JButton("×");
	JButton		jbtn_zero	= new JButton("0");
	JButton		jbtn_dot	= new JButton(".");
	JButton		jbtn_equal	= new JButton("=");
	JButton		jbtn_div	= new JButton("÷");
	JMenuBar	jmb			= new JMenuBar();
	JMenu		jm_info		= new JMenu("도움말");
	JMenuItem	jmi_create	= new JMenuItem("만든사람들.");
	int 		result		= 0;
	
	//생성자
	public MyCalcView(){
		initDisplay();
	}
	public int methodA(int a, int b) {
		int result =0;
		System.out.println("a:"+a+"b:"+b);
		if(a==b) {
			result = 1;
		}return result;
	}
	//화면처리부
	public void initDisplay() {
		jp_center.setLayout(new GridLayout(5,4,2,2));//가로,세로,폭,폭
		jtf_account.setEditable(false);
		jp_center.add(jbtn_b1);
		jp_center.add(jbtn_b2);
		jp_center.add(jbtn_ac);
		jp_center.add(jbtn_bs);
		jp_center.add(jbtn_seven);
		jp_center.add(jbtn_eight);
		jp_center.add(jbtn_nine);
		jp_center.add(jbtn_plus);
		jp_center.add(jbtn_four);
		jp_center.add(jbtn_five);
		jp_center.add(jbtn_six);
		jp_center.add(jbtn_minus);
		jp_center.add(jbtn_one);
		jp_center.add(jbtn_two);
		jp_center.add(jbtn_three);
		jp_center.add(jbtn_mul);
		jp_center.add(jbtn_zero);
		jp_center.add(jbtn_dot);
		jp_center.add(jbtn_equal);
		jp_center.add(jbtn_div);
		jm_info.add(jmi_create);
		jmi_create.addActionListener(this);
		jbtn_b1.addActionListener(this);	
		jbtn_b2.addActionListener(this);		
		jbtn_ac.addActionListener(this);		
		jbtn_bs.addActionListener(this);		
		jbtn_seven.addActionListener(this);	
		jbtn_eight.addActionListener(this);	
		jbtn_nine.addActionListener(this);	
		jbtn_plus.addActionListener(this);	
		jbtn_four.addActionListener(this);	
		jbtn_five.addActionListener(this); 	
		jbtn_six.addActionListener(this);	
		jbtn_minus.addActionListener(this);	
		jbtn_one.addActionListener(this);	
		jbtn_two.addActionListener(this);	
		jbtn_three.addActionListener(this);	
		jbtn_mul.addActionListener(this);	
		jbtn_zero.addActionListener(this);	
		jbtn_dot.addActionListener(this);	
		jbtn_equal.addActionListener(this);	
		jbtn_div.addActionListener(this);	
		jmb.add(jm_info);
		this.setJMenuBar(jmb);
		this.add("North",jtf_account);
		this.add("Center",jp_center);
		this.pack();
		this.setVisible(true);
	}
	
	//메인메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyCalcView mv = new MyCalcView();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(jmi_create==obj) {
			JOptionPane.showMessageDialog(this, "만든사람 : 김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인,김혜인");
		}
		else if(obj==jbtn_two) {
			jtf_account.setText(jtf_account.getText()+jbtn_two.getText());
		}
		else if(obj==jbtn_three) {
			jtf_account.setText(jtf_account.getText()+jbtn_three.getText());
		}
		else if(obj==jbtn_four) {
			jtf_account.setText(jtf_account.getText()+jbtn_four.getText());
		}
		else if(obj==jbtn_five) {
			jtf_account.setText(jtf_account.getText()+jbtn_five.getText());
		}
		else if(obj==jbtn_six) {
			jtf_account.setText(jtf_account.getText()+jbtn_six.getText());
		}
		else if(obj==jbtn_seven) {
			jtf_account.setText(jtf_account.getText()+jbtn_seven.getText());
		}
		else if(obj==jbtn_eight) {
			jtf_account.setText(jtf_account.getText()+jbtn_eight.getText());
		}
		else if(obj==jbtn_nine) {
			jtf_account.setText(jtf_account.getText()+jbtn_nine.getText());
		}
		else if(obj==jbtn_zero) {
			jtf_account.setText(jtf_account.getText()+jbtn_zero.getText());
		}
		else if(obj==jbtn_plus) {
//			int a = Integer.parseInt(jtf_account.getText());
			jtf_account.setText(jtf_account.getText()+jbtn_plus.getText());
			
//			int a = Integer.parseInt(jtf_account.getText());
//			int b = Integer.parseInt(jtf_account.getText());
//			result = a+b;
			
		}
		else if(obj==jbtn_ac) {
			jtf_account.setText("");
		}
		else if(obj==jbtn_bs) {
			String imsi = jtf_account.getText();
			imsi = imsi.substring(0,imsi.length()-1);
			jtf_account.setText(imsi);
		}
		else if(obj==jbtn_equal) {
			System.out.println(jtf_account.getText());
			String imsi = jtf_account.getText();
			String p = imsi.substring(imsi.indexOf("\\+"));
			System.out.println("p:"+p);
			String a[] = imsi.split("\\+");
			System.out.println("a[0]"+a[0]+"a[1]"+a[1]);
//			jtf_account.setText(String.valueOf(result));
		}
	}

}
