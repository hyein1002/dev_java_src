package study.sungjuk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import oracle.jdbc2.ZipCodeVO;
import sun.nio.cs.ext.SJIS;

public class SungJuk implements ActionListener {
	//선언부
	JFrame		jf_sungjuk		= new JFrame();//
	JButton		jbtn_account	= new JButton("성적처리");//	
	JButton		jbtn_exit		= new JButton("종료");//	
	JButton		jbtn_data		= new JButton("데이터가져오기");//	
	JTextField 	jtf_inwon		= new JTextField(10);//
	JLabel		jlb_su			= new JLabel("인원수");//
	JLabel		jlb_inwon		= new JLabel("명");//
	JPanel 		jp_north		= new JPanel();//
	JPanel 		jp_south		= new JPanel();//
	//사용자가 입력한 인원수를 담을 변수입니다.
	//전역변수로 한 이유는 인원수를 듣기는 jtf_inwon에서 엔터쳤을 때 값이 결정됩니다.
//	그 때 결정된 3이 jbtn_account에서도 필요합니다.
//	왜냐하면 총점을 기준으로 석차를 구하기로 결정되었으므로 총점과 석차를 같이 관리할
//	2차 배열을 선언하였기 때문입니다
	int			inwon			= 0;
	int 		imsi[][] 		= null;

	//테이블 처리 코드 추가
	String 			   cols[] = {"이름", "국어","영어","수학","총점","평균","석차"};
	String 			 data[][] = null;
	DefaultTableModel dtm_sj = null;
	JTable	       	   jt_sj = null;
	JTableHeader	  jth_sj = null;
	JScrollPane		  jsp_sj = null;
	
	//생성자
	SungJuk(){
		start();
	}
	//총점을 구하는 메소드 구현
public int[] total() {
	int tot[] = new int[inwon];
	for(int i = 0;i<inwon;i++) {
		tot[i] =
				Integer.parseInt((String)dtm_sj.getValueAt(i, 1))
				+Integer.parseInt((String)dtm_sj.getValueAt(i, 2))
				+Integer.parseInt((String)dtm_sj.getValueAt(i, 3));
				//구한 총점을 DefaultTableModel객체에 담기
//				imsi[i][0] = tot;
				dtm_sj.setValueAt(tot[i], i, 4);
//				double avg = 0.0;
//				avg = tot/3.0;
//				dtm_sj.setValueAt(avg, i, 5);
				imsi[i][1] = 1;//조건을 수렴하지 않을 경우가 발생할 수 있다.
				//이 때 0등이 나오면 안되니까 초기화를 1로 변경하였다.
	}	
	return tot;
	}
	
	//평균을 구하는 메소드 구현
	public double average(int tot[]) {
		double avg = 0.0;
		for(int i = 0;i<inwon;i++) {
			imsi[i][0] = tot[i];
		avg = tot[i]/3.0;
		dtm_sj.setValueAt(avg, i, 5);
		
		}
		
		return avg;
	}
	
	//석차를 구하는 메소드 구현
	public int[] ranking (int tot[]) {
		//return new null;
		for(int i = 0; i<inwon;i++) {
			for(int j = 0; j<inwon;j++) {
				//imsi[0][0]<imsi[0][0]
				//imsi[0][0]<imsi[1][0]
				//imsi[0][0]<imsi[2][0]
				//3<3,3<1,3<2
				if(imsi[i][0]<imsi[j][0]) {
					imsi[i][1]++;
				}
			}
		}
		for(int i = 0;i<inwon;i++) {
			dtm_sj.setValueAt(imsi[i][1], i, 6);
		}
		return new int[2];
	}
	
	//이벤트 소스와 이벤트 처리 클래스를 매핑
	public void start() {
		//엔터쳤을때 감지하고 콜백메소드를 호출하자
		jbtn_exit.addActionListener(this);
		jbtn_account.addActionListener(this);
		jbtn_data.addActionListener(this);
		jtf_inwon.addActionListener(this);
	}
	
	//화면처리부
	public void initDisplay() {
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north.add("West",jlb_su);
		jp_north.add("Center",jtf_inwon);
		jp_north.add("East",jlb_inwon);
		jf_sungjuk.add("North",jp_north);
		//jp_south.setLayout(new GridLayout(1,3));
		jp_south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jp_south.add(jbtn_data);
		jp_south.add(jbtn_account);
		jp_south.add(jbtn_exit);
		jf_sungjuk.add("South",jp_south);
		jf_sungjuk.setTitle("성적처리 프로그램 Ver1.0");//화면띄우는것
		jf_sungjuk.setSize(400, 400);//화면띄우는것
		//jf_sungjuk.pack();//화면띄우는것
		jf_sungjuk.setVisible(true);//화면띄우는것
	}

	//메인메소드
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SungJuk sj = new SungJuk();
		sj.initDisplay();


	}
	//@Overrid는 어노테이션이라고 읽음(골뱅이) - ActionListner가 가진 추상 메소드를
	//그대로 가져다가 재정의해서 사용하시오.
	//void methodA();<-좌중괄호 우중괄호가 오는게 아닌 ;로 끝나는 메소드가 추상메소드, 이름은 정해져있는데 기능은 정해지지 않음
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		if(obj==jbtn_exit) {
			System.exit(0);
		}
		else if(obj==jbtn_account) {
			//총점과 석차가 들어갈 공간
			//인원수는 어떻게 가져오지?-전역변수로 선언하고 사용하는게 좋겠어
			//왜냐명 다른 이벤트에서도 필요하기 때문이지
			int tot[] = total();
			average(tot);
			ranking(tot);
		}
		else if(obj==jbtn_data) {
			String data[][] = 
				{{"강호동","70", "80", "90"}  //1row - 강호동
				,{"유재석","60", "70", "60"}	//2row - 유재석
				,{"강감찬","80", "30", "70"}
				,{"김유신","90", "50", "90"}
				,{"이성계","10", "80", "80"}
				};
			//초기화할수있니?
			//2중 for문 활용할수있는거야?
			for(int i=0;i<data.length;i++) {
				for(int j=0;j<data[0].length;j++) {
					dtm_sj.setValueAt(data[i][j], i, j);//데이터(배열)를 넣어줌
				}
			}
			}
		
		else if(obj==jtf_inwon) {
			inwon 		= Integer.parseInt(jtf_inwon.getText());
			imsi 		= new int [inwon][2];
			data	 	= new String[inwon][7];
			dtm_sj 		= new DefaultTableModel(data,cols);
			jt_sj 		= new JTable(dtm_sj);
			jsp_sj 		= new JScrollPane(jt_sj);
			jf_sungjuk.add("Center",jsp_sj);
			jf_sungjuk.pack();
			//사용중인 컴퓨터의 스크린 사이즈 정보 가져오기
			Dimension di
			= Toolkit.getDefaultToolkit().getScreenSize();//getsize는 해상도를 가져옴
			//현재 내가 그린 화면의 크기 가져오기(400*300에서 변화되었겠죠)
			Dimension di2 = jf_sungjuk.getSize();
			jf_sungjuk.setLocation
			((int)(di.getWidth()/2-di2.getWidth()/2)
			, (int)(di.getHeight()/2-di2.getHeight()/2));
		}
		
	}
}
