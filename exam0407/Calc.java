package exam0407;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import javafx.scene.layout.Border;

public class Calc extends JFrame {
	JMenuBar	jmb			= new JMenuBar();
	JMenu		jm_one		= new JMenu("보기(V)");
	JMenu		jm_two		= new JMenu("편집(E)");
	JMenu		jm_three	= new JMenu("도움말(H)");
	
	JTextField	jtf_account = new JTextField(10);
	JPanel	jp_center	= new JPanel();
	JPanel	jp_right	= new JPanel();
	JPanel	jp_left		= new JPanel();
	
	JPanel	jp_lone		= new JPanel();
	JPanel	jp_ltwo		= new JPanel();
	JPanel	jp_lthree	= new JPanel();
	JPanel	jp_lfour	= new JPanel();
	JPanel	jp_lfive	= new JPanel();
	JPanel	jp_lsix		= new JPanel();
	JPanel	jp_lso		= new JPanel();
	JPanel	jp_lst		= new JPanel();
	
	JPanel	jp_rone		= new JPanel();
	JPanel	jp_rtwo		= new JPanel();
	JPanel	jp_rthree	= new JPanel();

	JButton	jbtn_mc		= new JButton("MC");
	JButton	jbtn_mr		= new JButton("MR");
	JButton	jbtn_ms		= new JButton("MS");
	JButton	jbtn_mp		= new JButton("M+");
	JButton	jbtn_mm		= new JButton("M-");
	JButton	jbtn_bs		= new JButton("←");
	JButton	jbtn_ce		= new JButton("CE");
	JButton	jbtn_c		= new JButton("C");
	JButton	jbtn_pm		= new JButton("±");
	JButton	jbtn_check	= new JButton("√");
	JButton	jbtn_seven	= new JButton("7");
	JButton	jbtn_eight	= new JButton("8");
	JButton	jbtn_nine	= new JButton("9");
	JButton	jbtn_div	= new JButton("/");
	JButton	jbtn_pc		= new JButton("%");
	JButton	jbtn_four	= new JButton("4");
	JButton	jbtn_five	= new JButton("5");
	JButton	jbtn_six	= new JButton("6");
	JButton	jbtn_mul	= new JButton("*");
	JButton	jbtn_x		= new JButton("1/𝒳");
	JButton	jbtn_one	= new JButton("1");
	JButton	jbtn_two	= new JButton("2");
	JButton	jbtn_three	= new JButton("3");
	JButton	jbtn_minus	= new JButton("-");
	JButton	jbtn_zero	= new JButton("0");
	JButton	jbtn_dot	= new JButton(".");
	JButton	jbtn_plus	= new JButton("+");
	JButton	jbtn_equal	= new JButton("=");
	Font	font		= new Font("arian", Font.BOLD, 30);
	

	public Calc() {
		initDisplay();
	}
	public void initDisplay() {
		jp_lso.setLayout(new GridLayout(1,1,2,2));	
		jp_lst.setLayout(new GridLayout(1,1,2,2));	
		jp_lone.setLayout(new GridLayout(1,1,2,2));
		jp_ltwo.setLayout(new GridLayout(1,1,2,2));
		jp_lthree.setLayout(new GridLayout(1,1,2,2));
		jp_lfour.setLayout(new GridLayout(1,1,2,2));
		jp_lfive.setLayout(new GridLayout(1,1,2,2));
		jp_lsix.setLayout(new GridLayout(1,2,2,2));
		jp_rone.setLayout(new GridLayout(2,1,2,2));
		jp_rtwo.setLayout(new GridLayout(2,1,2,2));
		jp_rthree.setLayout(new GridLayout(1,1,2,2));
		jp_right.setLayout(new GridLayout(3,1,2,2));
		jp_left.setLayout(new GridLayout(6,1,2,2));
		//jp_center.setLayout(new GridLayout(1,4,2,2));
		jtf_account.setFont(font);
		jtf_account.setHorizontalAlignment(JTextField.RIGHT);
	
		
		jp_rone.add(jbtn_mm);
		jp_rone.add(jbtn_check);
		
		jp_rtwo.add(jbtn_pc);
		jp_rtwo.add(jbtn_x);
		
		jp_rthree.add(jbtn_equal);
		
		jp_lone.add(jbtn_mc);
		jp_lone.add(jbtn_mr);
		jp_lone.add(jbtn_ms);
		jp_lone.add(jbtn_mp);
		
		jp_ltwo.add(jbtn_bs);
		jp_ltwo.add(jbtn_ce);
		jp_ltwo.add(jbtn_c);
		jp_ltwo.add(jbtn_pm);
		
		jp_lthree.add(jbtn_seven);
		jp_lthree.add(jbtn_eight);
		jp_lthree.add(jbtn_nine);
		jp_lthree.add(jbtn_div);
		
		jp_lfour.add(jbtn_four);
		jp_lfour.add(jbtn_five);
		jp_lfour.add(jbtn_six);
		jp_lfour.add(jbtn_mul);
		
		jp_lfive.add(jbtn_one);
		jp_lfive.add(jbtn_two);
		jp_lfive.add(jbtn_three);
		jp_lfive.add(jbtn_minus);
		
		jp_lso.add(jbtn_zero);
		jp_lst.add(jbtn_dot);
		jp_lst.add(jbtn_plus);
		
		jp_lsix.add(jp_lso);
		jp_lsix.add(jp_lst);
		
		jp_right.add(jp_rone);
		jp_right.add(jp_rtwo);
		jp_right.add(jp_rthree);
		
		jp_left.add(jp_lone);
		jp_left.add(jp_ltwo);
		jp_left.add(jp_lthree);
		jp_left.add(jp_lfour);
		jp_left.add(jp_lfive);
		jp_left.add(jp_lsix);
		
		jp_center.add("Center",jp_left);
		jp_center.add("East",jp_right);
		
		jmb.add(jm_one);
		jmb.add(jm_two);
		jmb.add(jm_three);
		
		jtf_account.setText("0");
		
		jp_center.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 10 , 10));
		
		
		jtf_account.setBorder(new LineBorder(Color.gray,1));
		
		this.setJMenuBar(jmb);
		this.add("North",jtf_account);
		this.add("Center",jp_center);
		this.setTitle("계산기");
		this.pack();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Calc();
	}

}
