package com.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;

import com.util.DBConnectionMgr;

public class PLSQLTest {
	DBConnectionMgr 	dbMgr 	= DBConnectionMgr.getInstance();
	Connection 			con 	= null;
	CallableStatement 	cstmt 	= null;//프로시저를 호출하는 클래스
	
	public void empSalUpdate() {
		try {
			con = dbMgr.getConnection();
//			con.setAutoCommit(true);//디폴트가 트루여서 롤백이 안됨. 롤백을 하려면 false로 바꿔줘야함
			cstmt = con.prepareCall("{call proc_emp_salupdate(?,?)}");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("사원번호를 입력하세요");
			String v_empno = br.readLine();
			cstmt.setInt(1, Integer.parseInt(v_empno));
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			int result = cstmt.executeUpdate();
//			con.commit();오라클에서 커밋하는것과 같음
//			con.rollback();오라클에서 롤백하는것과 같음
			System.out.println("result:"+cstmt.getString(2));
		} catch (Exception e) {
			System.out.println("Exception:"+e.toString());
		}
	}
	public static void main(String[] args) {
		
		PLSQLTest pl = new PLSQLTest();
		pl.empSalUpdate();
	}

}
