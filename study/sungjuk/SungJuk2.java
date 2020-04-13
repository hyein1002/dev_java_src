package study.sungjuk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SungJuk2 implements ActionListener {
	JFrame jf_sungjuk = new JFrame();
	JPanel jp_north = new JPanel();
	JPanel jp_south = new JPanel();
	JLabel jlb_inwon = new JLabel("인원수");
	JLabel jlb_su = new JLabel("명");
	JTextField jtf_inwon = new JTextField(10);
	JButton jbtn_data = new JButton("데이터가져오기");
	JButton jbtn_account = new JButton("성적처리");
	JButton jbtn_exit = new JButton("종료");
	
	String cols[] = {"이름","국어","영어","수학","총점","평균","석차"};
	DefaultTableModel dtm_sj = null;
	JTable jt_sj = null;
	JTableHeader jth_sj = null;
	JScrollPane jsp_sj = null;
	int inwon = 0;
	String data[][] = null;
	int imsi[][] = new int[inwon][2];
	SungJuk2(){
	initDisplay();
	start();
	}
	
	public void initDisplay() {
		jf_sungjuk.add("North",jp_north);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add("West",jlb_inwon);
		jp_north.add("Center",jtf_inwon);
		jp_north.add("East",jlb_su);
		jf_sungjuk.add("South",jp_south);
		jp_south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_south.add(jbtn_data);
		jp_south.add(jbtn_account);
		jp_south.add(jbtn_exit);
		jf_sungjuk.setSize(400,300);
		jf_sungjuk.setTitle("성적처리");
		jf_sungjuk.setVisible(true);
	}
	
	public int[] total() {
		int tot[] = new int[inwon];
		for(int i = 0;i<inwon;i++) {
		tot[i] = 
		Integer.parseInt((String)dtm_sj.getValueAt(i, 1))
		+Integer.parseInt((String)dtm_sj.getValueAt(i, 2))
		+Integer.parseInt((String)dtm_sj.getValueAt(i, 3));
		dtm_sj.setValueAt(tot[i], i, 4);
	}
		return tot ;
	}
	public double[] average(int tot[]) {
		double avg[] = new double[inwon];
		for(int i = 0;i<inwon;i++) {
		avg[i] = tot[i]/3.0;
		dtm_sj.setValueAt(avg[i], i, 5);
	}
		return avg;
	}
	public int[] ranking(int tot[]) {
		//imsi[0]에 있는 걸로 비교
		int rank[] = new int[inwon];
		for(int a = 0;a<inwon;a++) {
			rank[a] = 1;
		}
		for(int i = 0;i<inwon;i++) {
			for(int j = 0;j<inwon;j++) {
			if(tot[j]>tot[i]) {
				++rank[i];
			}
		}
	}
	for(int i = 0;i<inwon;i++) {
		dtm_sj.setValueAt(rank[i], i, 6);
		}
		return rank;
	}
	//이벤트처리
	public void start() {
		jbtn_exit.addActionListener(this);
		jbtn_account.addActionListener(this);
		jbtn_data.addActionListener(this);
		jtf_inwon.addActionListener(this);
	
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		new SungJuk2();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==jbtn_exit) {
			System.exit(0);
		}
		else if(obj==jtf_inwon) {
			inwon = Integer.parseInt(jtf_inwon.getText());
			data = new String[inwon][7];
			dtm_sj = new DefaultTableModel(data,cols);
			jt_sj = new JTable(dtm_sj);
			jsp_sj = new JScrollPane(jt_sj);
			jf_sungjuk.add("Center",jsp_sj);
			jf_sungjuk.pack();
			
			Dimension di
			= Toolkit.getDefaultToolkit().getScreenSize();//getsize는 해상도를 가져옴
			//현재 내가 그린 화면의 크기 가져오기(400*300에서 변화되었겠죠)
			Dimension di2 = jf_sungjuk.getSize();
			jf_sungjuk.setLocation
			((int)(di.getWidth()/2-di2.getWidth()/2)
			, (int)(di.getHeight()/2-di2.getHeight()/2));
		}
		else if(obj==jbtn_account) {
			total();
			average(total());
			ranking(total());
		}
		else if(obj==jbtn_data) {
			String data[][]= {
			{"강호동","100","10","40"}
			,{"유재석","80","90","50"}
			,{"지석진","70","20","30"}
		};
		for(int i = 0;i<inwon;i++) {
			for(int j = 0; j<data[0].length;j++) {
				this.data[i] = data[i];
				dtm_sj.setValueAt(data[i][j], i, j);
				}
			}
		
		}
	}
	
}