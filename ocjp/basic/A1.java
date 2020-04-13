package ocjp.basic;
/*
 * 전변
 * 인스턴스 변수로 호출할 수 있다.
 * 지변 
 * 인스턴스 변수로 호출할 수 없다.
 */
public class A1 {
	//자바에서는 원시타입의 디폴트값이 있다.double 0.0, boolean false
	int x;//전변은 초기화를 생략할 수 있다. 왜냐하면 생성자가 해주니까...
	boolean check() {
		x++;
		return true;
	}
	public static void main(String[] args) {
		new A1().check();//아래의 인스턴스화랑 다름. 관련없음. 복제본. 재사용이안됨. 이름이없어서
		A1 a1 = new A1();//위의 인스턴스화랑 다름. 관련없음. 복제본
		a1.check();
		System.out.println("x="+a1.x);//al.x는 18번줄을 호출한것
		//int y = 10;//지변-메소드 안에서 선언한 변수는 지역변수임.
		//System.out.println("y=" + a1.y);<-지역변수이기때문에 인스턴스변수로 호출할수없다
	}

}
