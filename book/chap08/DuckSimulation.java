package book.chap08;

public class DuckSimulation {
	/*
	 * 선언부(아빠)의 타입과 생성부(자식)의 타입이 다를 수 있다.
	 * 결론 : 생성부 타입으로 객체가 로딩된다.(생성된다, heap영역에 올라간다.)
	 * 그런데 선언부 타입이 그 메소드를 선언하고 있지 않으면 자식클래스 안에 
	 * 그 메소드가 선언되어있다 하더라ㄷ도 호출할 수 없다.
	 * 완결편 : 무조건 부모 타입에 선언된 메소드만 호출할 수 있다.
	 */

	public static void main(String[] args) {
		Duck myDuck = new WoodDuck();//권장사항
		Duck herDuck = new RubberDuck();//권장사항
		Duck yourDuck = new MallardDuck();//권장사항

		WoodDuck wd = new WoodDuck();//디폴트, 스탠다드
		
		wd.sound = "소리없음";
		wd.fly();
		wd.quack();
		wd.swimming();
		
		RubberDuck rd = new RubberDuck();
		rd.fly();
		rd.quack();
		rd.swimming();
		
		MallardDuck md = new MallardDuck();
		md.fly();
		md.swimming();
		md.quack();

		Duck d = new RubberDuck();
		d.fly();
		d.swimming();
	}

}
