package oracle.jdbc2;

import java.util.Vector;

public class ZipCodeList2 {

	public static void main(String[] args) {
//		Vector<String> v1 = new Vector<>();//뒤에는 타입 스킵 가능, 다이아몬드
//		
//		System.out.println("v.size() : "+v.size());
//		zcVO.setAddress("서울시 마포구 공덕동");
//		zcVO.setZipcode(21545);
//		v.add(0, zcVO);
//		v1.add(0, "파인애플");// 넌 틀렸어! 타입이 맞지않아
//		System.out.println(v1.get(0));
//		System.out.println(v.get(0).getZipcode());//아래 두줄과 같은뜻
//		ZipCodeVO zVO = v.get(0);
//		int zipcode = zVO.getZipcode();
//		System.out.println(zipcode);
		
		Vector<ZipCodeVO> v = new Vector<ZipCodeVO>();//아직 데이터가 추가되지않았다.
		ZipCodeVO zcVO = new ZipCodeVO();
		zcVO = null;
		ZipCodeVO zcVOS[] = null;//배열선언
		int i = 0;
		while(i<3) {
			zcVO = new ZipCodeVO();
			v.add(zcVO);
			i++;
		}
		for(int x = 0; x<v.size();x++) {
			ZipCodeVO zVO = v.get(x);
			System.out.println(zVO);
		}
		zcVOS = new ZipCodeVO[v.size()];//배열의 크기를 정해줌
		System.out.println("배열의 크기는" + zcVOS.length);//3
		//zcVOS가 가리키는 객체 배열에 들어있는 정보를 출력해보세요
		//insert here
		//zcVOS.length는 배열의 방크기(갯수)-3개이다.
		//저 배열의 세개 방 안에 ZipCodeVO가 초기화 될 수 있도록 코드를 작성해보세요.
		v.copyInto(zcVOS);//
		for(int y = 0; y<zcVOS.length;y++) {
//			zcVOS[y] = v.get(y);
			System.out.println(zcVOS[y]);//null,null,null
			System.out.println("zcVOS["+y+"] : "+zcVOS[y]+"\n v"+y+" : "+v.get(y));
		}
		//리턴타입이 void이지만 값을 유지하게 할 수 있다.
		//파라미터로 넘긴 주소번지에 v에 저장되어있는 주소번지를 복제해주는 메소드.

	}
}
