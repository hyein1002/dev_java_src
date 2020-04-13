package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {

	//선언부
	Connection		  con   = null;//전역변수 선언하기 - 클래스 전역에서 사용 가능함
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	//이 클래스를 읽어야 오라클 제품인것을 확인가능함.
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";//final이 붙어있을 때 변수 이름을 대문자로 씀.
	//물리적으로 떨어져있는 오라클 서버에 URL정보 추가
	public static final String _URL = "jdbc:oracle:thin:@192.168.0.218:1521:orcl11";//ip,포트번호,이름
	public static String _USER = "scott";
	public static String _PW = "tiger";
	//static-클래스급이다.-공유(하나다-정적변수)
	private static DBConnectionMgr dbMgr = new DBConnectionMgr();
	private DBConnectionMgr() {}
		//싱글톤 패턴으로 객체 관리하기 - 인스턴스화 과정이다.
	public static DBConnectionMgr getInstance() {
		if(dbMgr == null) {
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	//물리적으로 떨어져있는 오라클 서버와 연결통로 만들기
	//인스턴스화를 해주는 메소드 구현
	public Connection getConnection(){
		System.out.println("getConnection 호출 성공");
		try {
		Class.forName(_DRIVER);//에러가 일어날수 있어서 오류가 뜰수있다고 경고를 주니 예외처리하기
//		con = new Connection();//반드시 구현체 클래스가 있어야 한다.
		con = DriverManager.getConnection(_URL, _USER,_PW);
		}catch(ClassNotFoundException ce) {
			System.out.println("드라이버 클래스 이름을 찾을 수 없어요");
		}catch(Exception e) {
			System.out.println("예외가 발생했음. 정상적으로 처리가 안됨.");
		}
		return con;
	}
	/*
	 * DBConnectionMgr은 여러 업무에서 공통으로 사용하는 클래스입니다.
	 * 사용한 자원(Connection, PreparedStatement, ResultSet)은
	 * 반드시 반납하도록 합니다.->select일때만 반납함.
	 * 동시 접속자 수가 많은 시스템에서 자원사용은 곧 메모리랑 직결되므로
	 * 서버가 다운되거나 시스템 장애 발생에 원인이 됩니다.
	 */
	public void freeConnection(Connection con
							 , PreparedStatement pstmt
							 , ResultSet rs) {
		try {
			//사용자원의 생성 역순으로 반환할 것
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch (Exception e) {
			System.out.println("Exception : "+e.toString());
		}
		
	}
	//자바에서는 같은 이름의 메소드를 여러개 만들 수 있다.
	//1) 메소드오버로딩 - 파라미터 갯수
	//2) 메소드오버라이딩
	//업데이트,딜리트,인서트때는 이걸 사용 
	public void freeConnection(Connection con
			, PreparedStatement pstmt) {
		try {
			//사용자원의 생성 역순으로 반환할 것

			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch (Exception e) {
			System.out.println("Exception : "+e.toString());
		}
		
	}
	public void freeConnection(Connection con
			, CallableStatement cstmt) {
		try {
			//사용자원의 생성 역순으로 반환할 것
			
			if(cstmt!=null) {
				cstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}catch (Exception e) {
			System.out.println("Exception : "+e.toString());
		}
		
	}
}
