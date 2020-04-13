package book.chap12;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.util.DBConnectionMgr;

import oracle.jdbc2.JDBCTest;
import oracle.jdbc2.ZipCodeVO;

public class ZipCodeSearchApp implements ItemListener,ActionListener, FocusListener {

	String 			zDO = null;
	String 			myDong 	= null;
	String 			zdos[]	= null;
	JComboBox 		jcb_zdo	= null;
	Connection		  con   = null;//전역변수 선언하기 - 클래스 전역에서 사용 가능함
	PreparedStatement pstmt = null;
	ResultSet		       rs = null;
	JTextField	     jtf_dong = new JTextField("동 이름을 입력하세요");//검색창이 jTextField
	JButton	      jbtn_search = new JButton("조회 ");
	JButton	      jbtn_del = new JButton("삭제 ");
	String 			   cols[] = {"주소", "우편번호"};
	String 			 data[][] = new String[0][2];
	DefaultTableModel dtm_zip = new DefaultTableModel(data,cols);
	JTable	       	   jt_zip = new JTable(dtm_zip);
	JTableHeader	  jth_zip = new JTableHeader();
	JScrollPane		  jsp_zip = new JScrollPane(jt_zip);
	JFrame             jf_zip = new JFrame();//운영체제위에 창을 띄운다
	JPanel           jp_north = new JPanel();//속지를 만들어준다.
	DBConnectionMgr	  dbMgr	  = DBConnectionMgr.getInstance();
	
	public ZipCodeSearchApp() {
		System.out.println("나는 파라미터가 없는 디폴트 생성자라고 해.");
		System.out.println("나는 인스턴스화 하면 자동으로 호출되는거야.");
		zdos = getZdoList();
		jcb_zdo = new JComboBox(zdos);
		initDisplay();
	}
	public List<Map<String, Object>> refreshData(String zDO,String myDong)  {
		con = dbMgr.getConnection();
		System.out.println("refreshData 호출 성공"+myDong+","+zDO);
		ZipCodeVO zcVOS[] = null;//매번다른값이 오면 인스턴스화를 할수 없으므로 널값을 넣어줌
		ZipCodeVO zcVO = null;
		int col = 1;
		List<Map<String, Object>> addrList = new ArrayList<>();
		StringBuilder sb2 = new StringBuilder();
		sb2.append("SELECT address, zipcode");
		sb2.append(" FROM zipcode_t WHERE 1=1  ");
		if(zDO!=null && zDO.length()>0) {
			sb2.append(" AND zdo =?");
		}
		if(myDong!=null && myDong.length()>0) {
			sb2.append(" AND dong LIKE '%' || ? || '%' ORDER by address asc");
		}
		try {
		pstmt = con.prepareStatement(sb2.toString());//인스턴스화를 한것처럼 이해하기
		if(zDO!=null && zDO.length()>0) {
			pstmt.setString(col++, zDO);//?에 들어갈 동 이름이 결정됨
		}
		if(myDong!=null && myDong.length()>0) {
			pstmt.setString(col++, myDong);//?에 들어갈 동 이름이 결정됨
		}
		rs = pstmt.executeQuery();//오라클 서버에게 처리를 요청함
		Map<String, Object> rMap = null;
		while(rs.next()) {//커서이동, 커서이동//rs.next는 boolean
			rMap= new HashMap<>();
			rMap.put("adress", rs.getString("address"));
			rMap.put("zipcode",rs.getInt("zipcode"));
			addrList.add(rMap);//n건을 map에 담음
		}
		System.out.println("addrList.size():"+addrList.size()+",");
		if(addrList.size()>0) {//조회된 결과가 있니?
			while(dtm_zip.getRowCount()>0){
				dtm_zip.removeRow(0);
			}
			for(int x = 0;x<addrList.size();x++) {
				Map<String,Object> map = addrList.get(x);
				Vector oneRow = new Vector();
				oneRow.add(0, map.get("adress"));
				oneRow.add(1, map.get("zipcode"));
				dtm_zip.addRow(oneRow);
			}
		}
		}catch(SQLException se) {
			System.out.println("[[query]]"+sb2.toString());
		}catch(Exception e) {//그밖에 문제가 발생할경우 잡아준다
			System.out.println("[[Exception]]"+e);
		}
		return addrList;
	}
	
	
	public String[] getZdoList() {
		String zdos[] = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT '전체' as zdo FROM dual UNION ALL SELECT zdo "); 
		sb.append("    from (select distinct(zdo) zdo from zipcode_t order by zdo asc)"); 
		try {//물리적으로 떨어져있는 서버에 ip주소로 접근하니까 예외가 발생할 가능성이 있음
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<>();
			while(rs.next()) {
				String zdo = rs.getString("zdo");
				v.add(zdo);
			}
			zdos = new String[v.size()];
			v.copyInto(zdos);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return zdos;
	}
	void initDisplay() {
		System.out.println("initDisplay 호출성공");
		jth_zip = jt_zip.getTableHeader();
		jth_zip.setBackground(Color.yellow);
		jth_zip.setForeground(Color.magenta);
		jth_zip.setFont(new Font("바탕",Font.BOLD,20));
		jt_zip.setGridColor(new Color(22,22,100));//그리드색상
		jt_zip.setRowHeight(20);
		jt_zip.getColumnModel().getColumn(0).setPreferredWidth(350);	
		jt_zip.setSelectionBackground(new Color(186, 186, 241));
		jt_zip.setSelectionForeground(new Color(22, 22, 100));
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));//속지의 레이아웃을 보더 레이아웃에 설정해줌
		jp_north.setBackground(Color.RED);
		jp_north.add(jcb_zdo);
		jp_north.add(jtf_dong);
		jp_north.add(jbtn_search);
		jp_north.add(jbtn_del);
		jbtn_search.addActionListener(this);//버튼을 누를때
		jbtn_del.addActionListener(this);//버튼을 누를때
		jtf_dong.addActionListener(this);//엔터칠때
		jf_zip.setTitle("우편번호 검색");
		jf_zip.add("North",jp_north);
		jf_zip.add("Center",jsp_zip);
		jf_zip.setSize(600, 500);
		jf_zip.setVisible(true);
		jtf_dong.addFocusListener(this);
		jcb_zdo.addItemListener(this);
	}
	public static void main(String[] args) {
		new ZipCodeSearchApp();
	}
	@Override//상속관계에있을때 아빠차를 들고가서 튜닝해서 들고오는것, 값/기능을 바꾸는 재정의, 느끼는것(버튼을 누른걸)
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if((obj==jbtn_search) || (obj == jtf_dong)) {
			myDong = jtf_dong.getText();
			refreshData(zDO, myDong);
		}
		else if(obj==jbtn_del) {//컨트롤 누르고 칸을 찍는건 테이블이다. 데이터가 아님.
			int index[] = jt_zip.getSelectedRows();
			for(int row:index) {
				JOptionPane.showMessageDialog(jf_zip, row);
			}
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource()==jtf_dong) {
			jtf_dong.setText("");
		}
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		if(obj==jcb_zdo) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				zDO = zdos[jcb_zdo.getSelectedIndex()];
				System.out.println(zdos[jcb_zdo.getSelectedIndex()]);
			}		
		}
	}
}
		
