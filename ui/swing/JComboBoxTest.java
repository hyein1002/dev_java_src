package ui.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.util.DBConnectionMgr;

import oracle.jdbc2.JDBCTest;

public class JComboBoxTest implements ItemListener {
	//선언부
	String 			  data[] 		= null;
	JComboBox 		  jcb_dept		= new JComboBox(data);//데이터배열을 넘겨줌
	Connection		  con   		= null;
	PreparedStatement pstmt 		= null;
	ResultSet		  rs 			= null;
	DBConnectionMgr	  dbMgr			= DBConnectionMgr.getInstance();//friendly상태는 서로 다른 패키지는 접근 불가

	
	
	//생성자
	public JComboBoxTest(){
		jcb_dept.addItemListener(this);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame jf = new JFrame();
		jf.add("North",jcb_dept);
		jf.setSize(200,300);
		jf.setVisible(true);
		jf.setTitle("부서목록");
//		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public String[] dname(String name) {
		String  data[] = null;

		StringBuilder sb = new StringBuilder("");
		try {      
			sb.append(" SELECT  dname  FROM dept  ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

				}catch (Exception e) {
					// TODO: handle exception
				}
		return data;
	}
	
	//메인메소드
	/* 오라클 서버에서 dept테이블에 있는 정보를 조회하시오.
	 * 조회된 정보를 data배열에 초기화 하시오.
	 */
	public static void main(String[] args) {
		new JComboBoxTest();
	}
	/*ItemListner의 공식명칭은 인터페이스이다.
	 * 인터페이스는 추상 메소드를 가지고 있으므로 반드시 구형 해 주어야 한다.
	 * 이 때 부모가 가진 메소드의 원형을 절대로 훼손해서는 안된다. 리턴타입이나 파라미터를 바꾸면 안됨.
	 */
	@Override
	public void itemStateChanged(ItemEvent ie) {
		Object obj = ie.getSource();
		if(obj==jcb_dept) {
			if(ie.getStateChange()==ItemEvent.SELECTED) {
				System.out.println(jcb_dept.getSelectedIndex());
			}
		}
		
	}
}
