package book.chap02;

public class SungJuk {
/*
 * 내 안에 있는 메소드라 하더라도 메소드 선언시 static이 없으면 main메소드에서
 * 호출 할 수 없다.	
 * 총점을 구해주는 메소드가 있다. 이름은 hap임.
 * 파라미터가 있다.
 * 같은 이름의 메소드를 선언해도 괜찮음. 파라미터로 구분하면 되니까.
 */
	int hap(int kor, int eng) {
		int tot = 0;
		tot = kor + eng;//140
		return tot;
	}////end of hap

	//평균은 소수점이 나올 수 있다.
	//리턴값은 실수형으로 선언한다.
	//메소드 선언하기
	//반환타입 메소드이름(파라미터1,파라미터2,....)<-파라미터는 사용자의 입력값을 받아주는 곳
	double avg(int tot) {//<-위의 hap을 가지고오기 위해 int타입으로 씀. tot를 쓸거라서 tot로 변수통일. {
			return tot/2.0;
		}//end of avg
	/*
	 * 28-30-32(국어)-33(영어)-35(합계)-11(호출했으니까, 값을 가지고감(값이복사됨)(80,60점))-12-13-14-15(hap()끝)
	 * 39(140출력)-41-21(140복사됨)-22(double이니까 2.0으로 나눔)-43(값출력)
	 */
	public static void main(String[] args) {
		//hap 메소드에서 합을 구한 값을 여기서 사용하고 싶어요.
		SungJuk hap = new SungJuk();
		
		int kor = 50;
		int eng = 85;
		
		int tot = hap.hap(kor, eng);//리턴타입이 있기때문에 이렇게 쓸수있음. 없으면 쓸수없음.
		
		//위에서 계산한 결과인 tot변수를 avg메소드에서 사용하고 싶다.
		
		System.out.println(tot);
				
		double avgValue = hap.avg(tot);//35번에서 선언한 tot을 가져와서 avg()에서 사용함
		
		System.out.println(avgValue);
				
	}

}
