package exam0407;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class DBConnectionPractice {
	Connection con = null;
	PreparedStatement 	pstmt = null;
	ResultSet	rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	
	public List<LevelVO> voTest(){
		List<LevelVO> lVO = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append("SELECT lev, from_sal, to_sal, from_age, to_age FROM emp_level");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			lVO = new Vector<LevelVO>();
			
			while(rs.next()) {
				LevelVO lv = new LevelVO();
				lv.setLev(rs.getString("lev"));
				lv.setFrom_sal(rs.getInt("from_sal"));
				lv.setTo_sal(rs.getInt("to_sal"));
				lv.setFrom_age(rs.getInt("from_age"));
				lv.setTo_age(rs.getInt("to_age"));
				lVO.add(lv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lVO;
	}
	
	
	public Map<String,Object> mapTest(){
		Map<String,Object> rMap = null;
		StringBuilder sql = new StringBuilder();

		try {
			sql.append("SELECT lev, from_sal, to_sal, from_age, to_age FROM emp_level");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rMap = new HashMap<String, Object>();
				rMap.put("lev", rs.getString("lev"));
				rMap.put("from_sal", rs.getInt("from_sal"));
				rMap.put("to_sal", rs.getInt("to_sal"));
				rMap.put("from_age", rs.getInt("from_age"));
				rMap.put("to_age", rs.getInt("to_age"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rMap;
	}
	public List<Map<String,Object>> levelList(){
		List<Map<String,Object>> lList = new ArrayList<Map<String,Object>>();
		Map<String,Object> cols = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT lev, from_sal, to_sal, from_age, to_age FROM emp_level");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cols = new HashMap<String, Object>();
				cols.put("lev", rs.getString("lev"));
				cols.put("from_sal", rs.getInt("from_sal"));
				cols.put("to_sal", rs.getInt("to_sal"));
				cols.put("from_age", rs.getInt("from_age"));
				cols.put("to_age", rs.getInt("to_age"));
				lList.add(cols);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lList;
	}
	
	public static void main(String[] args) {
		DBConnectionPractice p = new DBConnectionPractice();
		List<Map<String,Object>> lList = p.levelList();
		System.out.println(lList);
		Map<String,Object> rMap = p.mapTest();
		System.out.println(rMap);
		List<LevelVO> lVO = p.voTest();
		for(int i=0;i<lVO.size();i++) {
			System.out.println(lVO.get(i).getLev());
			System.out.println(lVO.get(i).getFrom_age());
			System.out.println(lVO.get(i).getTo_age());
			System.out.println(lVO.get(i).getFrom_sal());
			System.out.println(lVO.get(i).getTo_sal());
		}
	}

}
