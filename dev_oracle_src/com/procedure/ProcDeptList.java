package com.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class ProcDeptList {
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl11";
	String user = "scott";
	String pw = "tiger";
	Connection con = null;//java.sql.*
	//프로시저를 call할 때는 CallableStatement를 사용한다.
	CallableStatement	cstmt = null;
	OracleCallableStatement ocstmt = null;
	ResultSet cursor = null;
	
	public List<Map<String, Object>> deptList(){
		List<Map<String, Object>> dList = new ArrayList<Map<String,Object>>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pw);
			cstmt = con.prepareCall("{call proc_deptList(?)}");//출력값을 파라미터로 받아오기때문에 ?값을 파라미터에 넣어준다.
			//call명령어를 이용해서 프로시저를 호출한다.
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			ocstmt = (OracleCallableStatement)cstmt;
		
			cursor = ocstmt.getCursor(1);
			Map<String,Object> rMap = null;
			
			while(cursor.next()) {
				rMap = new HashMap<>();
				rMap.put("deptno", cursor.getInt(1));
				rMap.put("dname", cursor.getString(2));
				dList.add(rMap);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			System.out.println("finally ");
			try {
				if(cursor != null) cursor.close();
				if(ocstmt != null) ocstmt.close();
				if(cstmt != null) cstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return  dList;
	}
	public static void main(String[] args) {
		ProcDeptList pdl = new ProcDeptList();
		List<Map<String, Object>> dList = null;
		dList = pdl.deptList();
		if(dList == null) {
			System.out.println("조회 결과가 없습니다.");
		}else {
			for(Map<String, Object> rMap : dList) {
				System.out.println(rMap.get("deptno")+","+rMap.get("dname"));
			}
		}
	}
}
