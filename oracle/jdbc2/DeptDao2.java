package oracle.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

public class DeptDao2 {

	Connection 			con 	= null;
	PreparedStatement 	pstmt 	= null;
	ResultSet			rs		= null;
	DeptVO				dVO		= null;
	DeptVO[]			dVOs	= null;
	DBConnectionMgr		dbMgr	= DBConnectionMgr.getInstance();
	public int daptInsert(DeptVO dVO) {
		int result = 0;
		StringBuilder	sb		= new StringBuilder();
		sb.append("INSERT INTO dept(deptno, dname, loc) values(?,?,?) ");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			int i = 0;
			pstmt.setInt(++i, dVO.getDeptno());
			pstmt.setString(++i, dVO.getDname());
			pstmt.setString(++i, dVO.getLoc());
			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("입력성공");
			}
			else if(result == 0) {
				System.out.println("입력실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {//에러가 발생하더라도 자원반납은 무조건 꼬오옥 해주세요.
			//사용한 자원은 반납해주세요.
			dbMgr.freeConnection(con, pstmt);
		}	
		return result;
	}
	public int daptUpdate(DeptVO dVO) {
		int result = 0;
		return result;
	}
	public int daptDelete(DeptVO dVO) {
		int result = 0;
		return result;
	}
	public DeptVO[] daptList(DeptVO dVO) {
		return dVOs;
	}
	
}
