package book.chap09;

public class DuckTest {

	public static void main(String[] args) {
//		Duck myDuck = new Duck();//추상클래스여서 자기자신을 단독으로 인스턴스화할수없다.
		//선언부의 타입과 생성부의 타입이 다를 때 다형성을 누릴 수 있다.
		Duck myDuck = new WoodDuck();
		myDuck.performFly();
		myDuck = null;
		myDuck = new MallardDuck();
		myDuck.performFly();
		myDuck = null;
		myDuck = new RubberDuck();
		myDuck.performFly();
		
	}

}
