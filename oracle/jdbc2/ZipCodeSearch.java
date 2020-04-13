package oracle.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Vector;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class ZipCodeSearch {
	//선언부
	//드라이버클래스가 필요하다.-JDBCTest에서 꺼내쓰자
	//URL정보도 JDBCTest에서 꺼내 쓸 수 있다.
	//_user와 _pw는 생략할 수 있다.-왜?static이라서 인스턴스화 안해도 쓸수있다.
	Connection 		  con   = null;//오라클서버를 이어줌
	PreparedStatement pstmt = null;//select문을 가져가서 오라클한테 전달해줘서 처리해달라고 하는것
	ResultSet		  rs	= null;//조회결과를 받아줌
	//오라클 서버와 연결통로 만들기를 메소드로 꺼내보세요.
	//메소드 뒤에 Exception을 붙이는 건 드라이버 클래스를 잘못 작성하여 에러가 아닌
	//런타임 에러 즉 ClassNotFoundException을 맞을 수 있기 때문에 선언하였다.
	public Connection getConnection()  throws Exception{
		System.out.println("getConnection 호출 성공");
		//오라클 회사 정보를 수집함
		Class.forName(JDBCTest._DRIVER);
		con = DriverManager.getConnection(JDBCTest._URL
										, JDBCTest._USER
										, JDBCTest._PW);
		return con;
	}
	//main()->userInput()[동이름결정]->getZipCodeList('당산동')
	public ZipCodeVO[] getZipCodeList(String userDong) 
			throws Exception //예외가 발생하면 나를 호출한곳에서 처리를 받으세요
			//예외처리를 내가 하지 않고 미룬다. 예외를 던져주세요
	{//오라클서버에게 select문을 전달하고 응답받음
		System.out.println("getZipCodeList 호출 성공"+userDong);
		ZipCodeVO zcVOS[] = null;//매번다른값이 오면 인스턴스화를 할수 없으므로 널값을 넣어줌
		ZipCodeVO zcVO = null;
		String sql = "";
		sql +="SELECT address, zipcode";
		sql +=" FROM zipcode_t";//이어질는 문장을 입력할때 첫칸(from앞에)에 띄어쓰기를 해주기
		sql +=" WHERE dong LIKE '%' || ? || '%'";//?는 파라미터값(userDong)//조회결과가 3건일경우
		//오라클서버에 요청을 보내기
		getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, userDong);//?에 들어갈 동 이름이 결정됨
		rs = pstmt.executeQuery();//오라클 서버에게 처리를 요청함
		Vector<ZipCodeVO> v = new Vector<>();
		while(rs.next()) {//커서이동, 커서이동//rs.next는 boolean
			//System.out.println("while문 : "+rs.next());//rs.next->현재 커서가 위치하는곳에 데이터가 있습니까?//커서이동
			zcVO = new ZipCodeVO();
			zcVO.setAddress(rs.getNString("address"));
			zcVO.setZipcode(rs.getInt("zipcode"));
//			zcVO.setAddress(rs.getNString(1));//42번을 보면 1번은 얻드레스 2번은 집코드
			v.add(zcVO);
		}
		zcVOS = new ZipCodeVO[v.size()];
		v.copyInto(zcVOS);//백터 자료구조에 들어있는 정보를 복사하기
//		System.out.println("while문 탈출");
//		//서버에 요청하기 전에 사용자로부터 동이름을 먼저 입력받아야한다.
//		//zcVO.uid_no = 10;//문법에러-private-왜? 웹이나 앱에서 동시사용자가 많을때 변조되면 안됨
//		zcVO.setUid_no(10);//28번처럼 써야하지만 프라이빗이라서 29번처럼 써야함
		printZipCode(zcVOS);//메소드 호출시에는 타입표시안함.
		return zcVOS;
	}
	//조회된 우편번호 목록을 출력해보기
	public void printZipCode(ZipCodeVO zcVOS[]) {
		for(ZipCodeVO zVO:zcVOS) {
			System.out.println(zVO.getAddress()+"   "+zVO.getZipcode());
		}
	}
	//사용자로부터 동을 입력받는 메소드를 구현해보시오.
	public String userInput() {
//		JDBCTest._USER = "hr";static만 있으니까 계정이름 변경 가능함.
//		JDBCTest._DRIVER = "";final이 있으니까 불가함.
		Scanner scan = new Scanner(System.in);
		String userDong = null;
		userDong = scan.nextLine();
		return userDong;
	}
	
	
	//메인메소드
	/*
	 * 30(가장먼저호출-entry pointer-main 스레드)-33(변수선언:지변)-31(인스턴스화)
	 * 35(메소드호출)-14(파라미터는 없다:리턴은 있다)-15-16-17-18(입력받은값확정)
	 * 35(리턴값으로 받음)-
	 * 
	 */
	
	public static void main(String[] args) throws Exception {
		ZipCodeSearch zs = new ZipCodeSearch();
		System.out.println("동을 입력하세요.");
		String userDong = null;
		
		userDong = zs.userInput();//호출한값을 userDong에 넣어주기
				
		if(userDong==null) {
			System.out.println("반드시 동을 입력해야 합니다.");
			return;
		}
		else {
			System.out.println("당신은 "+userDong+"을 입력하였습니다.");
			ZipCodeVO zcVOS[] = zs.getZipCodeList(userDong);
			
		}
	}

}
