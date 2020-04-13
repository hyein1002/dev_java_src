package method.temparature;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;

import oracle.jdbc2.JDBCTest;
import oracle.jdbc2.ZipCodeVO;

public class SeoulTempView implements ActionListener, ItemListener, FocusListener {

	//선언부
	Connection		  con   = null;//전역변수 선언하기 - 클래스 전역에서 사용 가능함
	PreparedStatement pstmt = null;
	ResultSet		    rs  = null;
	DBConnectionMgr	  dbMgr = DBConnectionMgr.getInstance();

	JTextField	     jtf_date = new JTextField("날짜를 입력하세요.");//검색창이 jTextField
	JButton	      jbtn_search = new JButton("조회 ");
	//오라클에서 조회 한 결과를 담을 클래스 선언 및 생성하기
	//테이블의 헤더 설정하기
	String 			   cols[] = {"날짜", "최저온도","최고온도"};
	String 			 data[][] = new String[0][3];
	//생성자에는 파라미터를 가질수있다. 
	//첫번째 파라미터는 데이터영역을 표시하는 주소번지
	//두번째 파라미터는 테이블 헤더 영역에 해달하는 주소번지
	//파라미터의 갯수에 따라서 서로 다른 생성자를 선언하는 것도 가능하다는 것인가?
	DefaultTableModel dtm_zip = new DefaultTableModel(data,cols);
	//테이블 양식 그려줌
	JTable	       	   jt_zip = new JTable(dtm_zip);
	JTableHeader	  jth_zip = new JTableHeader();
	JScrollPane		  jsp_zip = new JScrollPane(jt_zip);
	JFrame             jf_zip = new JFrame();//운영체제위에 창을 띄운다
	JPanel           jp_north = new JPanel();//속지를 만들어준다.
	JComboBox		jcb_year  = null;
	JComboBox		jcb_month = null;
	String 			years[]  = null;//오라클 서버 경유해서 반환받는 값으로 초기화
	String 			months[]  = null;//오라클 서버 경유해서 반환받는 값으로 초기화
	SeoulTempDAO	stDao	= new SeoulTempDAO();//data access object
	//생성자
	public SeoulTempView() {
		//오라클 서버 공유하기
		years = stDao.getYearList();
//		months = stDao.getMonthList(stDao.ta_year);

		//오라클 서버 경유하고 나서 받은 리턴값으로 콤보박스 인스턴스화 하기
		jcb_year = new JComboBox(years);
		jcb_month = new JComboBox();
		//생성자에서 메소드 호출할 수 있다.
		initDisplay();
	}
	
	
	//화면처리부
	public void initDisplay() {
		System.out.println("initDisplay 호출성공");
		
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(Color.BLUE);
		jth_zip.setForeground(Color.white);
		jth_zip.setFont(new Font("맑은고딕",Font.BOLD,20));
		jt_zip.setGridColor(Color.black);//그리드색상
		//그리드의 높이 변경하기
		jt_zip.setRowHeight(20);
		//컬럼의 너비 조정하기
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(250);	
		//선택한 로우의 배경색이나 글자색 변경하기
		jt_zip.setSelectionBackground(new Color(186, 186, 241));
		jt_zip.setSelectionForeground(new Color(22, 22, 100));
		//this는 나 자신을 가리키는 예약어, 이벤트가 일어난 소스와 이벤트를 처리하는 클래스(actionPerformed(이벤트처리담당메소드)를 연결해준다.
		//jp_north 속지에는 중앙에 jtf_dong을 붙이고 동쪽에는 jbtn_search를 붙인다.
		//이렇게 동, 서, 남, 북, 중앙에 버튼을 배치하고 싶으면 BorderLayout을 사용함
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));//속지의 레이아웃을 보더 레이아웃에 설정해줌
		jp_north.setBackground(Color.RED);
		jp_north.add(jcb_year);
		jp_north.add(jcb_month);
		jp_north.add(jtf_date);
		jp_north.add(jbtn_search);
		jbtn_search.addActionListener(this);//버튼을 누를때
		jtf_date.addActionListener(this);//엔터칠때
		jtf_date.addFocusListener(this);
		jf_zip.setTitle("서울 기후 통계 검색");
		//JFrame판넬 위에 북쪽에 jp_north속지를 붙이자.
		//속지 안에 버튼과 텍스트필드가 붙어있으니까 같이 따라온다.
		jf_zip.add("North",jp_north);
		jf_zip.add("Center",jsp_zip);
		jf_zip.setSize(600, 500);
		jf_zip.setVisible(true);
		jcb_month.addItemListener(this);
		jcb_year.addItemListener(this);
	}
	
	//전체조회 혹은 조건검색하기 구현
	void refreshData(String Date)  {

	}
	//메인메소드
	public static void main(String[] args) {
		new SeoulTempView();
	}
	
	public void actionPerformed(ActionEvent e) {
		}


	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();

		if(obj==jcb_year) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				stDao.ta_year = years[jcb_year.getSelectedIndex()];
				System.out.println(years[jcb_year.getSelectedIndex()]);
				
		        jp_north.remove(jcb_month);
		        jcb_month = null;
		        months = stDao.getMonthList(stDao.ta_year);
		        jcb_month = new JComboBox(months); 
		        jcb_month.addActionListener(this);
		        jp_north.add(jcb_month,1);
		        Container cont = jf_zip.getContentPane();
		        cont.revalidate();
			}		
					
		}
	}


	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		if(obj==jtf_date) {
			jtf_date.setText("");
		}		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	}


