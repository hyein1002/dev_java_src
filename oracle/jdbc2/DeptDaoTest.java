package oracle.jdbc2;

public class DeptDaoTest {
	DeptDao 	dd 	= new DeptDao();
	int result = 0;//입력결과, 수정결과, 삭제결과를 담을 변수
	//조회하기 테스트
	public void selectTest(DeptVO dVO) {//vo로 쓰면 컬럼명을 다 안써도 되니까 vo로 쓰기
		System.out.println("selectTest 호출성공");
		DeptVO dVOS[] = dd.deptList(dVO.getDeptno());
		for(DeptVO rdVO:dVOS) {
			System.out.println(rdVO.getDeptno()+", "+rdVO.getDname()+", "+rdVO.getLoc());
		}
	}
	//등록하기 테스트
	public void insertTest(DeptVO dVO) {//vo로 쓰면 컬럼명을 다 안써도 되니까 vo로 쓰기
		System.out.println("insertTest 호출성공");
		result = dd.deptInsert(dVO.getDeptno(), dVO.getDname(), dVO.getLoc());
		System.out.println("입력성공유무:"+result);
	}
	//수정하기 테스트
	public void updateTest(DeptVO dVO) {//vo로 쓰면 컬럼명을 다 안써도 되니까 vo로 쓰기
		System.out.println("updateTest 호출성공");
		result = dd.deptUpdate(dVO.getDeptno(), dVO.getDname(), dVO.getLoc());
		System.out.println("입력성공유무:"+result);

	}
	//삭제하기 테스트
	public void deleteTest(DeptVO dVO) {//vo로 쓰면 컬럼명을 다 안써도 되니까 vo로 쓰기
		System.out.println("deleteTest 호출성공");
		result = dd.deptDelete(dVO.getDeptno());
		System.out.println("입력성공유무:"+result);
	}
	public static void main(String[] args) {
		//화면이 아직 완정되지 않았으므로 통합테스트는 불가하다.
		//하지만 단위테스트는 언제나 가능
		DeptVO 		dvo = new DeptVO();
		DeptDaoTest ddt = new DeptDaoTest();
		
		dvo = null;
		dvo = new DeptVO();
		dvo.setDeptno(61);
		dvo.setDname("품질관리팀");
		dvo.setLoc("인천");
		ddt.insertTest(dvo);
		
		dvo = null;//기존에 연결고리를 끊는다.
		dvo = new DeptVO();//새로운 객체를 조립시작
		dvo.setDeptno(61);
		ddt.selectTest(dvo);

		dvo = null;
		dvo = new DeptVO();
		dvo.setDeptno(61);
		dvo.setDname("품질관리팀2");
		dvo.setLoc("인천2");
		ddt.updateTest(dvo);
		
		dvo = null;//기존에 연결고리를 끊는다.
		dvo = new DeptVO();//새로운 객체를 조립시작
		dvo.setDeptno(61);
		ddt.selectTest(dvo);
		
		dvo = null;
		dvo = new DeptVO();
		dvo.setDeptno(61);
		ddt.deleteTest(dvo);
		
		//조회하기 테스트
		
		//등록하기 테스트
//		dd.deptInsert(61, "개발부", "서울");
//		dd.deptInsert(62, "인사부", "경기");
//		dd.deptInsert(63, "총무부", "인천");
		
		//수정하기 테스트
//		dd.deptUpdate(61, "일부", "부산");
//		dd.deptUpdate(62, "이부", "제주");
//		dd.deptUpdate(63, "삼부", "울산");
		
		//삭제하기 테스트
//		dd.deptDelete(61);
//		dd.deptDelete(62);
//		dd.deptDelete(63);
		
	}

}
