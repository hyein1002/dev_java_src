package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class DeptManager extends JFrame{
	String 				cols[] 		= {"이름","부서이름"};
	String				data[][]	= new String[0][2];
	DefaultTableModel	dtm			= new DefaultTableModel(data, cols);
	JTable				jt			= new JTable(dtm);
	JScrollPane			jp			= new JScrollPane(jt);
	Connection			con			= null;
	PreparedStatement	pstmt		= null;
	ResultSet			rs			= null;
	DBConnectionMgr		dbMgr		= DBConnectionMgr.getInstance();
	
	public DeptManager() {
		initDisplay();
		tempData();
	}
	
	public void initDisplay() {
		this.setSize(500,400);
		this.setVisible(true);
		this.setTitle("사원정보 조회");
		this.add("Center",jp);
	}
	public ArrayList<TempVO> tempData() {
		ArrayList<TempVO> al = new ArrayList<>();
		Vector v = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT emp.ename, dept.dname      ");
		sb.append(" FROM emp, dept                   ");
		sb.append(" WHERE emp.deptno(+) = dept.deptno");
		try {
			con = dbMgr.getConnection();                
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TempVO	tvo = new TempVO();
				tvo.setEmp_name(rs.getString("ename"));
				tvo.setDept_name(rs.getString("dname"));
				al.add(tvo);
			}
			for(int i = 0;i<al.size();i++) {
				v = new Vector();
				v.add(al.get(i).getEmp_name());
				v.add(al.get(i).getDept_name());
				dtm.addRow(v);
			}
			
		} catch (Exception e) {
		}
		
		return al;
	}
	public static void main(String[] args) {
		DeptManager dm	= new DeptManager();
	}

}
