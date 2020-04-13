package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class ArrayListTest {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	ArrayListVO	avo = null;
	ArrayListVO	avos[] = null;//************************************
	
	public ArrayListTest() {

	}
	
	
	public ArrayListVO[] sql() {
		StringBuilder sb = new StringBuilder();                                    
		sb.append("SElECT decode(b.rno,1,indate_vc,2,'총계') as 판매날짜      ");
		sb.append(" ,sum(a.qty_nu)   as 판매개수                      ");
		sb.append(" ,sum(a.qty_nu * a.price_nu) as 가격");
		sb.append(" FROM t_orderbasket a, (SELECT rownum rno FROM dept WHERE rownum<3)b ");
		sb.append(" GROUP BY decode(b.rno,1,indate_vc,2,'총계')                     ");
		sb.append(" ORDER BY decode(b.rno,1,indate_vc,2,'총계')                     ");
		ArrayList<ArrayListVO> a = null;
		try {
			a = new ArrayList<ArrayListVO>();
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				avo = new ArrayListVO();
				avo.setDate(rs.getString("판매날짜"));
				avo.setQty(rs.getInt("판매개수"));
				avo.setPrice(rs.getInt("가격"));
				a.add(avo);
			}
			avos = new ArrayListVO[a.size()];
			for(int i = 0; i<a.size();i++) {
				avos[i] = a.get(i);
			}
//			for(int i = 0; i<avos.length;i++) {
//				Vector v = new Vector();
//				v.add(avos[i].getPrice());
//				v.add(avos[i].getQty());
//				v.add(avos[i].getPrice());
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		for(int i = 0; i<a.size();i++) {
//		System.out.println(avos[i].getDate()+","+avos[i].getQty()+","+avos[i].getPrice());
//		}
		return avos;
	}
	


}
