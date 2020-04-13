package book.chap12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConnectionMgr;

public class OrderBasketDataSet {
	Connection 			con 		= null;
	PreparedStatement 	pstmt 		= null;
	ResultSet 			rs 			= null;
	DBConnectionMgr 	dbMgr 		= DBConnectionMgr.getInstance();
	
	public ArrayList<OrderBasketVO> getList2(){
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT decode(b.rno,1,a.indate_vc,2,'총계')  indate_vc      ");
	    sb.append("  ,sum(a.qty_nu)  t_qty                           ");
	    sb.append("  ,sum(a.qty_nu * a.price_nu) t_price");
	    sb.append("  FROM t_orderbasket a,                                           ");
	    sb.append(" (SELECT rownum rno FROM dept WHERE rownum<3) b                     ");
	    sb.append(" GROUP BY decode(b.rno,1,a.indate_vc,2,'총계')                       ");
	    sb.append(" ORDER BY decode(b.rno,1,a.indate_vc,2,'총계') asc                       ");
	    try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			//VO는 한번에한 개 로우만 담을 수 있어요. 두 개 로우는 안되죠
			//VO에는 변수 하나에 한개값만 담는 변수를 선언했기 때문이죠.
			OrderBasketVO obVO = null;
			while(rs.next()) {
				obVO = new OrderBasketVO();
				//오라클에서 꺼낸 값은 rs로 꺼내고 
				//위에서 꺼낸 값은 obVO에 선언된 변수 중 indate_vc변수에 값을
				//담아주세요.
				//왜 setter메소드로 값을 자꾸만 담는거야?
				obVO.setIndate_vc(rs.getString("indate_vc"));
				obVO.setT_qty(rs.getInt("t_qty"));
				obVO.setT_price(rs.getInt("t_price"));
				al.add(obVO);
			}
			
		} catch (SQLException se) {//오라클에서 발생되는 에러메세지 잡기
			//자바에러는 이클립스에서 잡고 오라클 에러는 토드에서 잡는게 좋겠다.
			System.out.println(se.toString());
		} catch (Exception e) {//자바전체에서 발생되는 에러메세지 잡기
			System.out.println(e.toString());
		}	    
		return al;//al=null al이 null일수도 있음
	}
	
	public ArrayList<OrderBasketVO> getList(){
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>();
		OrderBasketVO obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-19");
		obVO.setT_qty(150);
		obVO.setT_price(560000);
		al.add(obVO);
		
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("2020-02-20");
		obVO.setT_qty(105);
		obVO.setT_price(360000);
		al.add(obVO);
		
		obVO = new OrderBasketVO();
		obVO.setIndate_vc("총계");
		obVO.setT_qty(255);
		obVO.setT_price(920000);
		al.add(obVO);
		
		return al;
	}

	public static void main(String[] args) {
		//ArrayList<Integer> al2 = new ArrayList<>();
		ArrayList<OrderBasketVO> al = new ArrayList<OrderBasketVO>();

		for(OrderBasketVO obVO2:al) {
			System.out.print(obVO2.getIndate_vc()+"          "+obVO2.getT_qty()+"          "+obVO2.getT_price()+"\n");
		}
		
	}

}
