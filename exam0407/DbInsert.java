package exam0407;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import com.util.DBConnectionMgr;

public class DbInsert {
	  public static void main(String[] args) { 
	    Map<String, String> crawlingMap = new HashMap<String, String>();
	    
	    crawlingMap.put("이름", "유관순");
	    crawlingMap.put("나이", "18");
	    crawlingMap.put("생년월일", "1902년 12월 16일");
	    crawlingMap.put("출생지", "천안");
	    crawlingMap.put("업적", "일제 강점기의 독립운동가");
	     
	    try {
	      //DB연결
	      Class.forName("oracle.jdbc.OracleDriver");
	      String url = "jdbc:oracle:thin://@localhost:1521:orcl";
	      String userid="오라클계정명";
	      String userpw="패스워드";
	      /*① 위 접속정보 이용하여 DB연결 및 Connection객체 생성 */
	      DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	      Connection con = dbMgr.getConnection();
	      PreparedStatement psmt = null;
	      
	      
	      //DB연결 성공시...
	      if(con!=null){      
	        /*② insert 쿼리문 작성 */
	    	  StringBuilder	sql = new StringBuilder();
	    	  sql.append("INSERT INTO table(name, age, birth, contury, history) VALUES (?,?,?,?,?)");
	        /*③ PreparedStatement 객체 생성 및 인파라미터 설정 */     
	    	  psmt = con.prepareStatement(sql.toString());
	    	  int i = 1;
	    	  psmt.setString(i++, crawlingMap.get("name"));
	    	  psmt.setString(i++, crawlingMap.get("age"));
	    	  psmt.setString(i++, crawlingMap.get("birth"));
	    	  psmt.setString(i++, crawlingMap.get("contury"));
	    	  psmt.setString(i++, crawlingMap.get("history"));

		//쿼리문실행 및 반환값 확인
	        int resultNum = psmt.executeUpdate();
	        System.out.println(resultNum +"행이 입력성공");
	      }
	    }
	    catch (ClassNotFoundException ce) { }
	    catch (SQLException se) { }
	    catch (Exception e) { }
	  }
	}
