package aTest0404;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calc extends JFrame implements ActionListener{

	JTextField	jtf_account		= new JTextField(20);
	JPanel		jp_ac			= new JPanel();
	JPanel		jp_center		= new JPanel();
	JPanel		jp_one			= new JPanel();
	JPanel		jp_two			= new JPanel();
	JPanel		jp_three		= new JPanel();
	JPanel		jp_four			= new JPanel();
	JPanel		jp_five			= new JPanel();
	
	JButton		jbtn_b1			= new JButton("");
	JButton		jbtn_b2			= new JButton("");
	JButton		jbtn_ac			= new JButton("AC");
	JButton		jbtn_bs			= new JButton("←");
	JButton		jbtn_seven		= new JButton("7");
	JButton		jbtn_eight		= new JButton("8");
	JButton		jbtn_nine		= new JButton("9");
	JButton		jbtn_plus		= new JButton("+");
	JButton		jbtn_four		= new JButton("4");
	JButton		jbtn_five		= new JButton("5");
	JButton		jbtn_six		= new JButton("6");
	JButton		jbtn_minus		= new JButton("-");
	JButton		jbtn_one		= new JButton("1");
	JButton		jbtn_two		= new JButton("2");
	JButton		jbtn_three		= new JButton("3");
	JButton		jbtn_mul		= new JButton("×");
	JButton		jbtn_zero		= new JButton("0");
	JButton		jbtn_dot		= new JButton(".");
	JButton		jbtn_equal		= new JButton("=");
	JButton		jbtn_div		= new JButton("÷");
	
	
	
	public Calc() {
		initDisplay();
	}
	
	public void initDisplay() {
		jp_center.setLayout(new GridLayout(5,1));
		jp_one.setLayout(new GridLayout(1,2));
		jp_two.setLayout(new GridLayout(1,2));
		jp_three.setLayout(new GridLayout(1,2));
		jp_four.setLayout(new GridLayout(1,2));
		jp_five.setLayout(new GridLayout(1,2));
		jp_ac.setLayout(new GridLayout(1,2));
		jp_five.add(jbtn_zero);
		jp_five.add(jbtn_dot);
		jp_five.add(jbtn_equal);
		jp_five.add(jbtn_div);
		jp_four.add(jbtn_one);
		jp_four.add(jbtn_two);
		jp_four.add(jbtn_three);
		jp_four.add(jbtn_mul);
		jp_three.add(jbtn_four);
		jp_three.add(jbtn_five);
		jp_three.add(jbtn_six);
		jp_three.add(jbtn_minus);
		jp_two.add(jbtn_seven);
		jp_two.add(jbtn_eight);
		jp_two.add(jbtn_nine);
		jp_two.add(jbtn_plus);
		jp_one.add(jbtn_b1);
		//jp_one.add(jbtn_b2);
		jp_one.add(jp_ac);
		jp_ac.add(jbtn_ac);
		jp_ac.add(jbtn_bs);
		jp_center.add(jp_one);
		jp_center.add(jp_two);
		jp_center.add(jp_three);
		jp_center.add(jp_four);
		jp_center.add(jp_five);
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
		this.add("North",jtf_account);
		this.add("Center",jp_center);
		this.setSize(300,400);;
		this.setVisible(true);
	}
	
	
	
	
	public static void main(String[] args) {
		new Calc();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==jbtn_ac) {
			jtf_account.setText("");
		}
		else if(obj==jbtn_one) {
			jtf_account.setText(jtf_account.getText()+jbtn_one.getText());
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
		else if(obj==jbtn_nine) {
			jtf_account.setText(jtf_account.getText()+jbtn_nine.getText());
		}
		else if(obj==jbtn_zero) {
			jtf_account.setText(jtf_account.getText()+jbtn_zero.getText());
		}
		else if(obj==jbtn_bs) {
			String t = jtf_account.getText().substring(0,(jtf_account.getText().length())-1);
			jtf_account.setText(t);
			
		}
		else if(obj==jbtn_plus) {
			jtf_account.setText(jtf_account.getText()+jbtn_plus.getText());

		}
		else if(obj==jbtn_equal) {
			String t = jtf_account.getText();
			String str[] = t.split("\\+");
			for(int i =0;i<str.length;i++) {
				System.out.println(str[i]);
			}
			String target = "\\+";
			int target_num = t.indexOf(target);
			String result = t.substring(target_num);
			System.out.println(result);
			
		}
		
		
	}

}
