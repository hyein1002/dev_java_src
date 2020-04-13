package oracle.jdbc2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class DeptDao {
	DeptVO[] 			dVOs 	= null;
	Connection		 	con   	= null;
	PreparedStatement 	pstmt 	= null;
	ResultSet		    rs		= null;
	DBConnectionMgr	  	dbMgr 	= DBConnectionMgr.getInstance();
	//INSERT INTO dept(deptno, dname, loc) values(?,?,?);
	
	public int deptInsert(int deptno, String dname, String loc) {
		int result = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO dept(deptno, dname, loc) values(?,?,?)");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			int i = 0;
			pstmt.setInt(++i, deptno);
			pstmt.setString(++i, dname);
			pstmt.setString(++i, loc);
			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("입력성공");
			}
			else if(result==0) {
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
	//UPDATE SET dname = ?, loc =?, WHERE deptno =?
	public int deptUpdate(int deptno, String dname, String loc) {
		int result = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE dept SET dname = ?, loc = ? WHERE deptno = ?");
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			int i = 0;
			pstmt.setString(++i, dname);
			pstmt.setString(++i, loc);
			pstmt.setInt(++i, deptno);
			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("입력성공");
			}
			else if(result==0) {
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
	//DELETE FROM dept WHERE deptno=?
	public int deptDelete(int deptno) {
		//조회 결과가 n건일수 있으므로 객체배열로 받아야 한다
		int result = 0;
		//쿼리문을 작성할 때 String대신 StringBuilder를 사용하자
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM dept WHERE deptno = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("입력성공");
			}
			else if(result==0) {
				System.out.println("입력실패");
			}
		} catch (Exception e) {
			//StringBuilder가 try문 안에 왔을 때 delete문에 에러가 발행했을 때 delete문을 출력하는 문장을 작성할 수
			//있는데 이때 변수 sb를 사용할 수 있다? 없다?->없다
			
			e.printStackTrace();
		}finally {//에러가 발생하더라도 자원반납은 무조건 꼬오옥 해주세요.
			//사용한 자원은 반납해주세요.
			dbMgr.freeConnection(con, pstmt);
		}		
		return result;
	}
	//SELECT deptno, dname, loc FROM dept WHERE deptno = 10;
	public DeptVO[] deptList(int deptno) {
		dVOs 	= null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT deptno, dname, loc FROM dept WHERE deptno = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			int i = 0;
			pstmt.setInt(++i, deptno);
			rs = pstmt.executeQuery();
			DeptVO dVO = null;
			Vector<DeptVO> v = new Vector<DeptVO>();
			while(rs.next()){
				dVO = new DeptVO();
				dVO.setDeptno(rs.getInt("deptno"));
				dVO.setDname(rs.getString("dname"));
				dVO.setLoc(rs.getString("loc"));
				v.add(dVO);
			}
			dVOs = new DeptVO[v.size()];
			v.copyInto(dVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {//에러가 발생하더라도 자원반납은 무조건 꼬오옥 해주세요.
			//사용한 자원은 반납해주세요.
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return dVOs;
	}
	//메소드 오버로딩이라고 한다.
	//무조건 파라미터의 갯수가 다르거나 혹은 파라미터의 타입이 달라야 한다.**************************
	//SELECT deptno, dname, loc FROM dept WHERE deptno > 10 AND dname=?;
	public DeptVO[] deptList(int deptno, String dname) {
		dVOs 	= null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT deptno, dname, loc FROM dept WHERE deptno > 10 AND dname = ?");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			int i = 0;
			pstmt.setString(++i, dname);
			rs = pstmt.executeQuery();
			DeptVO dVO = null;
			Vector<DeptVO> v  = new Vector<>();
			while(rs.next()) {
				dVO = new DeptVO();
				dVO.setDeptno(rs.getInt("deptno"));
				dVO.setDname(rs.getString("dname"));
				dVO.setLoc(rs.getString("loc"));
				v.add(dVO);
			}
			dVOs = new DeptVO[v.size()];
			v.copyInto(dVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {//에러가 발생하더라도 자원반납은 무조건 꼬오옥 해주세요.
			//사용한 자원은 반납해주세요.
			dbMgr.freeConnection(con, pstmt, rs);
		}
		return dVOs;
	}

}
