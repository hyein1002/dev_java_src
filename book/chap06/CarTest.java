package book.chap06;
//전역변수는 유지됨. 값이 넘어감 주소번지가 넘어가서
public class CarTest {
	void methodA(Tivoli myCar) {
		myCar.speed=50;
	}

	public static void main(String[] args) {
		CarTest ct = new CarTest();
		Tivoli myCar = new Tivoli();
		myCar.speed = 30;
		System.out.println("methodA 호출전"+myCar.speed);
		ct.methodA(myCar);
		System.out.println("methodA 호출후"+myCar.speed);
		Tivoli herCar = new Tivoli();
		herCar.speed = 50;
	}

}
