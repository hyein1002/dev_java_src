package method.temparature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class SeoulTempDAO {
	/*
	 * 서울 기상 통계 정보로부터 최근 10년 년도 가져오기
	 */
	Connection		  con   = null;//전역변수 선언하기 - 클래스 전역에서 사용 가능함
	PreparedStatement pstmt = null;
	ResultSet		    rs  = null;
	DBConnectionMgr	  dbMgr = DBConnectionMgr.getInstance();
	String			ta_year	= null;
	
	public String[] getYearList() {
		String years[] = null;
		years = new String[11];//최근 10년이니까 10개의 칸이 필요
		//오라클 서버에게 보낼 select문 작성하기
		StringBuilder sb = new StringBuilder();
		//자바코드는 이클립스에서 디버깅하고 select문에서 
		sb.append("select distinct(to_char(to_date(sdate),'YYYY')) ta_year from seoultemp ");
		sb.append(" where to_char(to_date(sdate),'YYYY') > to_char(sysdate,'YYYY')-11      ");          
		sb.append(" order by ta_year asc                                                   ");
		
		//asc가 오름차순
		try {//물리적으로 떨어져있는 서버에 ip주소로 접근하니까 예외가 발생할 가능성이 있음
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<>();
			//커서를 한줄씩 넘기면서 커서의 위치에 값을 변수에 담는다.
			while(rs.next()) {
				ta_year = rs.getString("ta_year");
				//조회된 값을 백터 클래스에 추가한다.
				v.add(ta_year);
			}
			//오라클 서버에서 조회된 결과 만큼 추가된 벡터의 크키값을 가지고
			//배열을 생성한다.
			years = new String[v.size()];
			//백터에 들어있는 정보를 String배열에 복사한다.
			v.copyInto(years);
		}catch (Exception e) {
			//stack영역에 관리되는 에러메세지 정보를 라인번호와 이력까지 출력해줌.
			e.printStackTrace();
		}
		
		return years;
	}

	public String[] getMonthList(String ta_year) {
		String months[] = null;
		months = new String[12];//최근 10년이니까 10개의 칸이 필요
		StringBuilder sb = new StringBuilder();
		//자바코드는 이클립스에서 디버깅하고 select문에서 
		sb.append("select distinct(to_char(to_date(sdate),'MM')) ta_month from seoultemp");
		sb.append(" where to_char(to_date(sdate),'YYYY') = ?                        ");
		sb.append(" order by ta_month                                                    ");
		//asc가 오름차순
		try {//물리적으로 떨어져있는 서버에 ip주소로 접근하니까 예외가 발생할 가능성이 있음
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, ta_year);
			rs = pstmt.executeQuery();
			Vector<String> v = new Vector<>();
			//커서를 한줄씩 넘기면서 커서의 위치에 값을 변수에 담는다.
			while(rs.next()) {
				String sdate = rs.getString("ta_month");
				//조회된 값을 백터 클래스에 추가한다.
				v.add(sdate);
			}
			//오라클 서버에서 조회된 결과 만큼 추가된 벡터의 크키값을 가지고
			//배열을 생성한다.
			months = new String[v.size()];
			//백터에 들어있는 정보를 String배열에 복사한다.
			v.copyInto(months);
		}catch (Exception e) {
			//stack영역에 관리되는 에러메세지 정보를 라인번호와 이력까지 출력해줌.
			e.printStackTrace();
		}
		
		return months;
	}

}
