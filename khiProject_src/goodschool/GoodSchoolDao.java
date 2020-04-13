package goodschool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodSchoolDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public GoodSchoolVO score(String subject) {
		GoodSchoolVO gsv = null;
		StringBuilder sql = new StringBuilder();
		try {
			if(subject.equals("math")) {
				sql.append("SELECT name, id_number, major, re_subject,? FROM goodschool");
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				pstmt.setString(1, subject);
				while(rs.next()) {
					rs.getString(1);
				}
				
			}
			else if(subject.equals("kor")) {
				sql.append("SELECT name, id_number, major, re_subject,? FROM goodschool");
				con = dbMgr.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return gsv;
	}
	
	
}
