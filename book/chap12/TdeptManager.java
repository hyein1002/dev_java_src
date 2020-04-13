package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class TdeptManager extends JFrame {
	String				cols[] 		= {"아이디","사원명","부서명"};
	String				data[][]	= new String[0][3];
	DefaultTableModel	dtm			= new DefaultTableModel(data,cols);
	JTable 				jtb			= new JTable(dtm);
	JScrollPane			jsp			= new JScrollPane(jtb);
	Connection 			con 		= null;
	PreparedStatement 	pstmt 		= null;
	ResultSet 			rs 			= null;
	DBConnectionMgr 	dbMgr 		= DBConnectionMgr.getInstance();
	TempVO[] tVOS = null;
	Vector v = null;
	public TdeptManager() {
		
	}
	public void initDisplay() {
		this.setTitle("사원정보 조회");
		this.add("Center",jsp);
		this.setSize(500,400);
		this.setVisible(true);
		getDeptList();
		
	}
	
	public ArrayList<TempVO> getDeptList(){
		ArrayList<TempVO> al = new ArrayList<TempVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.emp_id,t.emp_name,d.dept_name ");
	    sb.append("  from temp t, tdept d 				 ");
	    sb.append("  where t.dept_code = d.dept_code	 ");
	    try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			TempVO tVO = null;
			while(rs.next()) {
				tVO = new TempVO();
				tVO.setEmp_id(rs.getInt("emp_id"));
				tVO.setEmp_name(rs.getString("emp_name"));
				tVO.setDept_name(rs.getString("dept_name"));
				al.add(tVO);
				System.out.println(tVO);
			}
//			tVOS = new TempVO[al.size()];
//			for(int i = 0;i<tVOS.length;i++) {
//				tVOS[i] = al.get(i);
//				System.out.println(tVOS[i].getEmp_id());
//				System.out.println(tVOS[i].getEmp_name());
//				System.out.println(tVOS[i].getDept_name());
//			}
			for(int i = 0;i<al.size();i++) {
				v = new Vector();
				v.add(al.get(i).getEmp_id());
				v.add(al.get(i).getEmp_name());
				v.add(al.get(i).getDept_name());
				dtm.addRow(v);
			}
			
		} catch (SQLException se) {//오라클에서 발생되는 에러메세지 잡기
			System.out.println(se.toString());
		} catch (Exception e) {//자바전체에서 발생되는 에러메세지 잡기
			System.out.println(e.toString());
		}	    
		return al;//al=null al이 null일수도 있음
	}
	public static void main(String[] args) {
		TdeptManager tm = new TdeptManager();
		tm.initDisplay();
	}

}
