package design.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.util.DBConnectionMgr;

public class BooKDao {
	List<Map<String, Object>> li = null;
	Map<String, Object> rMap = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DBConnectionMgr dbMgr = DBConnectionMgr.getInstance();
	public void BookDao() {
		
	}
	public List<Map<String, Object>> bookList(){
		li = new ArrayList<Map<String,Object>>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT b_no, b_title, b_author, b_publish, b_detail, b_img FROM booklist");
		sb.append(" ORDER BY b_no ASC");
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rMap = new HashMap<String, Object>();
				rMap.put("b_no", rs.getString("b_no"));
				rMap.put("b_title", rs.getString("b_title"));
				rMap.put("b_author", rs.getString("b_author"));
				rMap.put("b_publish", rs.getString("b_publish"));
				rMap.put("b_detail", rs.getString("b_detail"));
				li.add(rMap);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	public void addList() {
		
	}
	public int bookDelete(BookVO pbVO) {
		System.out.println("bookDelete");
		int result = 0;
		StringBuilder sql = new StringBuilder();
		int cnt = 0;
		try {
			if(pbVO.getBnos()!=null) {
				cnt = pbVO.getBnos().size();
			}
			sql.append("DELETE FROM booklist WHERE b_no IN(");
			for(int x = 0;x<cnt;x++) {
				if(x==cnt-1) {
					sql.append("?)");
				}else {
					sql.append("?,");
				}
			}
			System.out.println(sql.toString());
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			//insert here
			int no = 1;
			List<Integer> bnos = pbVO.getBnos();
			for(int i = 0; i<cnt;i++) {
				pstmt.setInt(no++, bnos.get(i));
			}
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//파라미터가 4개가 필요함.
	//1 row inserted니까 성공하면 1 실패하면 0
	public int bookInsert(BookVO pbVO) {
		int result = 0;
		System.out.println("bookInsert");
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("INSERT into booklist(b_no,b_title,b_author,b_publish,b_detail)");
			sql.append(" VALUES(seq_book_no.nextval,?,?,?,?) ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			//insert here
			int i = 1;
			pstmt.setString(i++, pbVO.getB_title());
			pstmt.setString(i++, pbVO.getB_author());
			pstmt.setString(i++, pbVO.getB_publish());
			pstmt.setString(i++, pbVO.getB_detail());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int bookUpdate(BookVO pbVO) {
		System.out.println("bookUpdate");
		int result = 0;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("UPDATE booklist SET b_title=?,b_author=?,b_publish=?, b_detail=?");//어떤걸 수정 가능하게 할건지?
			sql.append("WHERE b_no=?");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			//insert here
			int i = 1;
			pstmt.setString(i++, pbVO.getB_title());
			pstmt.setString(i++, pbVO.getB_author());
			pstmt.setString(i++, pbVO.getB_publish());
			pstmt.setString(i++, pbVO.getB_detail());
			pstmt.setInt(i++, pbVO.getB_no());
			result = pstmt.executeUpdate();
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;	
		}
	public BookVO bookDetail(BookVO pbVO) {
		System.out.println("bookDetail");
		BookVO rbVO = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT b_no, b_title, b_author, b_publish, b_detail, b_img");
			sql.append(" FROM booklist ");
			sql.append(" WHERE b_no=? ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			//insert here
			pstmt.setInt(1, pbVO.getB_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rbVO = new BookVO();
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_title(rs.getString("b_title"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_detail(rs.getString("b_detail"));
				rbVO.setB_img(rs.getString("b_img"));
			}
			System.out.println("rbVO:"+rbVO.getB_detail());
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rbVO;
	}
	//전체 조회 구현
	public List<BookVO> bookList(BookVO pbVO) {
		System.out.println("bookList");
		List<BookVO> bookList = new ArrayList<BookVO>();
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT b_no, b_title, b_author, b_publish,b_img");
			sql.append(" FROM booklist ");
			sql.append(" ORDER BY b_no ASC ");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			BookVO rbVO = null;
			while(rs.next()) {
				rbVO = new BookVO();
				rbVO.setB_no(rs.getInt("b_no"));
				rbVO.setB_title(rs.getString("b_title"));
				rbVO.setB_author(rs.getString("b_author"));
				rbVO.setB_publish(rs.getString("b_publish"));
				rbVO.setB_img(rs.getString("b_img"));
				bookList.add(rbVO);
			}
			System.out.println("bookList.size():"+bookList.size());
		} catch (SQLException se) {
			System.out.println(se.toString());
			System.out.println("[query]"+sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}////////////////end of bookList

//	public static void main(String[] args) {
//		BooKDao b = new BooKDao();
//		BookVO pbVO = new BookVO();
//		List<Integer> bnos = new ArrayList<Integer>();
//		bnos.add(9);
//		bnos.add(10);
//		bnos.add(11);
//		pbVO.setBnos(bnos);
//		int result = b.bookDelete(pbVO);
//		System.out.println("result:"+result);
//		pbVO.setB_no(1);
//		b.bookDetail(pbVO);
//		BookVO rbVO = b.bookDetail(pbVO);
//		System.out.println(rbVO.getB_img());
//	}
}
