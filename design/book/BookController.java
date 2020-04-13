package design.book;

import java.util.List;

public class BookController {
	BookApp bookApp = null;
	private static final String _DEL = "delete";//삭제하기
	private static final String _SEL = "detail";//상세조회
	private static final String _INS = "insert";//입력하기
	private static final String _UPD = "update";//수정하기
	private static final String _ALL = "all";//전체조회
	
	public BookController(BookApp bookApp) {
		this.bookApp = bookApp;
	}
	public BookVO send(BookVO pbVO) {
		BookVO rbVO = new BookVO();
		String command = pbVO.getCommand();
		//insert here
		if(command.equals(_DEL)) {
			//DELETE FROM booklist WHERE b_no = 1
			int result = 0;
			result = bookApp.bDao.bookDelete(pbVO);
			rbVO.setResult(result);
		}
		else if(command.equals(_INS)) {
			//INSERT INTO booklist(b_no,b_name,b_author,b_publish,,b_detail
			//VALUES(?,?,?,?,?)
			int result = 0;
			result = bookApp.bDao.bookInsert(pbVO);
			rbVO.setResult(result);
		}
		else if(command.equals(_UPD)) {
			//UPDATE booklist SET b_name=?,b_author=?,b_publish=?
			//WHERE b_no=2
			int result = 0;
			result = bookApp.bDao.bookUpdate(pbVO);
			rbVO.setResult(result);
		}
		else if(command.equals(_SEL)) {
			//SELECT b_no,b_name,b_author,b_publish FROM booklist
			//WEHRE b_no = 3
			rbVO = bookApp.bDao.bookDetail(pbVO);
		}

		return rbVO;
	}///////////////end of send
	//전체조회
	public List<BookVO> sendALL(BookVO pbVO){
		System.out.println("sendALL 호출 성공");
		List<BookVO> bList = null;
		String command = pbVO.getCommand();
		if(_ALL.equals(command)) {
			//사용자의 선택은 읽었지만 여기서 DB연동을 하지는 않는다.db연동은 BookDao쪽에서 하도록 설계
			BookVO rbVO = null;
			bList = bookApp.bDao.bookList(pbVO);
			
		}
		return bList;
	}////////////////end of sendALL
	public static void main(String[] args) {
		
	}
}
