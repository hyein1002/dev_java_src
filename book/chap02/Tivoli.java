package book.chap02;

public class Tivoli {
	int speed = 0;
	int speedUp() {
		speed = speed + 1;//호출이 안되서 실행되지 않음. main부터 실행됨. 안에 있어도 호출이 안되면 실행안됨.
		return speed;
	}
	/*
	 * 자바가상머신이 동작하는 순서대로 번호를 매겨볼까?
	 * 14 - 20 - 21[주소번지출력] - 22[초기화-30] - 23[
	 */
	//entry point - 제일먼저 실행되는 부분
	public static void main(String[] args) {
		/*
		 * 문제제기
		 * non-static타입의 speed변수는 static이 있는 메인메소드안에서 접근이 불가
		 * 해결방법 - 어떡하지? How
		 * 인스턴스화하면 된다.
		 */
		Tivoli gnom = new Tivoli();
		System.out.println("gnom의 주소번지는" + gnom);//16진수표현{0 1 2 3 4 5 6 7 8 9 a b c d e f}값으로 표시
		gnom.speed = 30;
		int s = gnom.speedUp();
		System.out.println(s);
		
		System.out.println(gnom.speed);
	}

}
