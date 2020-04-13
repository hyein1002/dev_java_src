package book.chap02;
/*****************************************
 * 나는 클래스에서 변수의 선언 위치에 대해 말할 수 있다.
 * 클래스 선언한 바로 다음 자리 - 전역변수
 *****************************************/
public class Sonata {
	String carColor = null;//참조형(reference type - 주소번지출력) 타입을 선언할 때 쓰는 예약어임.
	int speed = 50;//전역변수 - 초기화를 생략할 수 있다. 왜 안해도 되지? 생성자가 해주니까... 50으로 초기화, type+이름 대입(=)	
	public boolean methodA(int a, int b) {
		boolean isOk = false;
		if(a==b) {
			isOk = true;
		}
		return isOk;
	}
	public static void main(String[] args) {
		int wheelNum;//지역변수 - 반드시 초기화를 해야한다. 초기화를 생략할 수 없다. 선언
		//변수의 장애 - 변수는 한번에 하나만 담을 수 있다. - 동시에 두개를 담을 수 없다.
		
		wheelNum = 0;
		wheelNum = 4;//초기화
		System.out.println(wheelNum);//0 or 4?
		
		Sonata gnom = new Sonata();
		gnom.carColor = "빨강";
		System.out.println(gnom.carColor);
		System.out.println("빨강");
		//소나타 클래스안에서 티볼리 안에 있는 변수나 메소드를 누릴(호출) 수 있다.
		Tivoli yourCar = new Tivoli();
		
	}/////////end of main

}
