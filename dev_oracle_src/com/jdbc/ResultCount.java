package com.jdbc;
/*
 * java jdbc API활용 연습
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

public class ResultCount {
	public static void main(String[] args) {
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Connection con = null;//Interface
		PreparedStatement pstmt = null;//Interface
		ResultSet rs = null;//Interface
		StringBuilder sql = new StringBuilder();
		
		try {
			con = dbMgr.getConnection();
			sql.append("SELECT empno FROM emp");
			//sensitive는 비 순차적으로 커서 옵션을 이동, conur_updatable은 resultset을 이용해서 추가로 필요한 정보를 얻는데 ㅔ필요한 옵션
			pstmt = con.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();//select문 요청 시 
			rs.last();//기본적으로는 first에 있는 커서를 맨 마지막으로 이동
			int rowcount = rs.getRow();
			System.out.println("Total Row:"+rowcount);
			rs.first();
			System.out.println("rs.next():"+rs.next());
			rs.absolute(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
