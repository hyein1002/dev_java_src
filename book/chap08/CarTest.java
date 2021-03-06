package book.chap08;

public class CarTest {

	public static void main(String[] args) {
		//myCar로 접근할 수 있는 변수의 갯수는 몇개 일까요? 1개
		//myCar로 호출할 수 있는 메소드의 갯수는 몇개니? 1개
		//Car로 객체를 생성한 경우에는 메소드 한개 변수 한개만 호출 할 수 있어요
		/* myCar의 타입이 Car타입이어서 Tivoli타입의 변수나 메소드는 한개도 접근, 호출
		 * 불가합니다.
		 * 이것은 new Tivoli()로 인스턴스화 한 경우에도 동일하게 적용됩니다.
		 * 다시 말하지만 타입이 Car타입이어서 생성부의 이름이 설사 Tivoli가 온다 하더라도
		 * Tivoli타입의 변수나 메소드는 접근, 호출이 불가하다는것이죠.
		 * 이런경우 메소드는 부모와 자식클래스 모두에 선언해 놓으면 호출은 가능합니다.->메소드오버라이드
		 * 만일 부모에는 존재하고 자식에는 존재하지 않는 메소드의 경우 둘다 무조건 부모메소드가
		 * 호출된다.
		 * 그러나 변수는....?
		 */
		Car myCar = null;
		myCar = new Car();
		System.out.println(myCar.speed);
		myCar.speed = 10;
		System.out.println(myCar.speed);
		myCar.stop();
		System.out.println(myCar.speed);
		myCar = null;
		myCar = new Tivoli();//24번에서 25번으로 진행되는 과정에서 candidate상태
		System.out.println(myCar.speed);
		myCar.speed = 100;
		System.out.println(myCar.speed);
		myCar.stop();
		System.out.println(myCar.speed);
		//herCar로 접근할 수 있는 변수의 갯수는 몇개 일까요? 3개
		//herCar로 호출할 수 있는 메소드의 갯수는 몇개니? 3개
		Tivoli herCar = null;
		herCar = new Tivoli();
		System.out.println(herCar.speed);
		System.out.println(herCar.carColor);
		System.out.println(herCar.volum);
		herCar.carColor = "노란색";
		System.out.println(herCar.carColor);
		herCar.speed = 90;
		System.out.println(herCar.speed);
		herCar.volum = 40;
		System.out.println(herCar.volum);
		herCar.stop();
		System.out.println(herCar.speed);
		herCar.volumUP();
		System.out.println(herCar.volum);
		herCar.volumDown();
		System.out.println(herCar.volum);
//		Car himCar = null;
		
	}

}
