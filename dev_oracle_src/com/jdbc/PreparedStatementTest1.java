package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PreparedStatementTest1 {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
		String user = "scott";
		String pw = "tiger";
		Connection con = null;//java.sql.*
		PreparedStatement	pstmt = null;
		ResultSet	rs = null;
		Scanner	scan = new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//드라이버클래스로드
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("con:"+con);
			System.out.println("사원번호를 입력하세요");
			int u_empno = scan.nextInt();
			StringBuilder	sql = new StringBuilder();
			sql.append("SELECT ename, job FROM emp WHERE empno=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1,u_empno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("ename:"+rs.getString("ename")+", job:"+rs.getString("job"));
			}else {
				System.out.println("조회결과가 없습니다.");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		} catch (Exception e) {
			//stack영역에 쌓여 있는 에러 메시지의 history를 출력 - line번호 출력
			e.printStackTrace();
		}finally {
			System.out.println("정상적으로 처리가 되었을 때도 실행됨.");
			//사용한 자원 반납하기
			//반납할 때는 생성된 역순으로 처리하기
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		System.out.println("여기");
}
}