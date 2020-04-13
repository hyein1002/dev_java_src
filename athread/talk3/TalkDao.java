package athread.talk3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TalkDao {
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	Connection con = null;
	PreparedStatement pstmt = null;//쿼리문을 요청할 때-오라클
	PreparedStatement pstmt1 = null;//쿼리문을 요청할 때-오라클
	ResultSet rs = null;//오라클에서 살고 있는 커서를 조작하기
	public String loginProc(String mem_id, String mem_pw) {
		String mem_name = null;
		//insert here
		return mem_name;
	}
	public String login(String mem_id, String mem_pw) {
		String mem_name = null;
		StringBuilder isID = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		int status = 2;
		try {
			isID.append("SELECT                                            ");
		    isID.append("   NVL((                                          ");
		    isID.append("        SELECT 1 FROM dual                        ");
		    isID.append("         WHERE EXISTS (SELECT mem_name FROM member");
		    isID.append("                        WHERE mem_id=?)     ");  
		    isID.append("       ),-1) isID                                 ");
		    isID.append("  FROM dual                                       ");
		    con = dbMgr.getConnection();
		    pstmt = con.prepareStatement(isID.toString());
		    pstmt.setString(1, mem_id);
		    rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	status = rs.getInt("isID");
		    }
		    if(status==1) {//아이디가 존재하면 비번을 비교한다.
		    	sql.append("SELECT mem_name FROM member");
		    	sql.append(" WHERE mem_id =?         ");
		    	sql.append("   AND mem_pw =?	     ");
		    	pstmt1 = con.prepareStatement(sql.toString());
		    	pstmt1.setString(1, mem_id);
		    	pstmt1.setString(2, mem_pw);
		    	rs = pstmt1.executeQuery();
		    	if(rs.next()) {
		    		mem_name = rs.getString("mem_name");
		    	}else {
		    		mem_name = "비밀번호가 맞지 않습니다.";
		    	}
		    	return mem_name;
		    }
		    else {//아이디가 존재하지 않으면 비번을 비교할 필요없음.
		    	
		    	return "아이디가 존재하지 않습니다.";
		    }
//		    System.out.println("status:"+status);
		}catch (SQLException se) {
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return mem_name;
	}
}
