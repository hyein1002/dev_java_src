package oracle.jdbc2;

public class DeptDaoTest2 {
	DeptDao2  dd = new DeptDao2();
	int result = 0;
	public void insertTest(DeptVO dv) {
		result = dd.daptInsert(dv);
		System.out.println(result);
	}
	public static void main(String[] args) {
		DeptVO			dv	= new DeptVO();
		DeptDaoTest2	dd2	= new DeptDaoTest2();
		
		dv = null;
		dv	= new DeptVO();
		dv.setDeptno(99);
		dv.setDname("인사팀");
		dv.setLoc("서울");
		dd2.insertTest(dv);
	}

}
