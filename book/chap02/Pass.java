package book.chap02;

public class Pass {

	public static void main(String[] args) {
		int x = 5;//main에서 선언된 지역변수
		Pass p = new Pass();
		p.doStuff(x);
		System.out.println("main x = " + x);//밑에는 x가 증가했지만 지역변수기때문에 아래 메소드랑 메인 메소드 안에있는 x값이랑 다르니까 값이 5가 나온다
	}
	
	void doStuff(int x) {
		System.out.println("doStuff x = " + x++);//x를 증가시키기 전에 출력한다.
	}

}
