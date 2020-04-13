package com.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConnectionMgr;

//최근에 와서 RDBMA제품을 대신할 수 있는 다른 제품들이 많이 나왔다-무겁다. 고가, 
//경량데이터베이스,NOSQL,빅데이터 혹은 클라우드 시스템 - Map기반 열중심의 처리 최적화된 db
//오라클은 행중심의 처리
//ORM솔루션 제공(MyBatis[구글, 오픈소스기반=-쿼리문작성-xml문서작성, 지버깅편리], iBatis, Hibernate[쿼리문이 없다-튜닝], jdo....)
//DB연동코드의 30%이상이 줄어든다.
//반복되는 코드를 많이 줄여준다.
public class ResultSetTest {
	ResultSetTest(){
		DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
		Connection con = null;//Interface
		PreparedStatement pstmt = null;//Interface
		ResultSet rs = null;//Interface
		StringBuilder sql = new StringBuilder();
		try {
			con = dbMgr.getConnection();
			sql.append("SELECT empno,ename FROM emp");
			//sensitive는 비 순차적으로 커서 옵션을 이동, conur_updatable은 resultset을 이용해서 추가로 필요한 정보를 얻는데 ㅔ필요한 옵션
			//insentitiv는 동기화가 안됨
			pstmt = con.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			//데이터추가하기
			//insert문이나 update문 없이도 데이터를 추가/삭제할수있다.
			rs.moveToInsertRow();
			rs.updateInt(1,1001);
			rs.updateString(2, "김유신");
			rs.insertRow();
			rs.moveToInsertRow();
			rs.updateInt(1,1002);
			rs.updateString(2, "이성계");
			rs.insertRow();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+","+rs.getString("ename"));
			}
			if(!rs.isBeforeFirst()) {
				rs.beforeFirst();
			}
			rs.isFirst();
//			System.out.println("rs.next():"+rs.next()+",rs.isFirst():"+rs.isFirst());
			while(rs.next()) {
				System.out.println("==>"+rs.getInt("empno"));
				if(rs.getInt("empno")==1001) {
					rs.deleteRow();
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		new ResultSetTest();
	}

}
