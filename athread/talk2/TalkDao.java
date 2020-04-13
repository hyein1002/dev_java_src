package athread.talk2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import thread.bank.DBConnectionMgr;

public class TalkDao {
	Connection			con		= null;
	PreparedStatement	pstmt 	= null;//쿼리문을 요청할 때 - 오라클
	PreparedStatement	pstmt1 	= null;//쿼리문을 요청할 때 - 오라클
	DBConnectionMgr		dbMgr	= DBConnectionMgr.getInstance();
	ResultSet			rs		= null;//오라클에서 살고 있는 커서를 조작하기
	int 				status	= 0;
	public String login(String mem_id, String mem_pw){
		StringBuilder	isID = new StringBuilder();
		StringBuilder	sql = new StringBuilder();
		String mem_name = null;
		status = 2;
		try {
		isID.append("SELECT NVL((SELECT 1 FROM dual WHERE EXISTS(SELECT mem_name FROM member");
		isID.append(" WHERE mem_id=?)),-1) isID FROM dual");
		con = dbMgr.getConnection();
		pstmt = con.prepareStatement(isID.toString());
		pstmt.setString(1, mem_id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			status = rs.getInt("isID");
		}

		if(status==1) {//아이디가 존재하면 비번을 비교한다.
			sql.append("SELECT mem_name FROM member");
			sql.append(" WHERE mem_id=?           ");
			sql.append(" AND mem_pw=?             ");
			pstmt1 = con.prepareStatement(sql.toString());
			pstmt1.setString(1, mem_id);
			pstmt1.setString(2, mem_pw);
			rs = pstmt1.executeQuery();
			if(rs.next()) {
				mem_name = rs.getString("mem_name");
			}else {
				mem_name = "비밀번호가 맞지 않습니다.";
			}
		}
		else {//아이디가 존재하지 않으면 비번을 비교할 필요 없음.
			return "아이디가 존재하지 않습니다.";
		
		} 
		}catch (Exception e) {
		
		}
		return mem_name;
//		StringBuilder	sb = new StringBuilder();
//		try {
//			sb.append("SELECT mem_name FROM member");
//			sb.append(" WHERE mem_id = ?");
//			sb.append(" AND mem_pw = ?");
//			con = dbMgr.getConnection();
//			pstmt = con.prepareStatement(sb.toString());
//			int i = 1;
//			pstmt.setString(i++, mem_id);
//			pstmt.setString(i++, mem_pw);
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				mem_name = rs.getString("mem_name");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mem_name;
	
	}
	
	public static void main(String[] args) {
		TalkDao	td	= new TalkDao();
		System.out.println(td.login("test", "123"));
	}
	
}
	

