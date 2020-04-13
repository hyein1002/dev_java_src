package goodschool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String _URL = "jdbc:oracle:thin:@192.168.0.218:1521:orcl11";
	public static String _ID = "bank";
	public static String _PW = "tiger";
	private static DBConnectionMgr dbMgr = new DBConnectionMgr();
	private DBConnectionMgr() {}
	public static DBConnectionMgr getInstance() {
		if(dbMgr==null) {
			dbMgr = new DBConnectionMgr();
		}
		return dbMgr;
	}
	public Connection getConnection() {
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL,_ID,_PW);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}
	public void freeConnection(Connection con,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
