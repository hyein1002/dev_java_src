package book.chap08;

public class CarTest2 {

	public static void main(String[] args) {
		Car myCar = null;
		Tivoli herCar = new Tivoli();
		System.out.println(myCar + ", "+ herCar);
		myCar = herCar;//같은 주소번지를 가짐. hercar의 주소번지를 mycar에 넘겨줬으므로
		//결론 : 두 변수가 같은 주소번지를 갖는다.
		System.out.println(myCar + ", "+ herCar);
		myCar.speed = 10;
		herCar.speed = 100;
		//주소번지가 같은데 100이 더 나중에 초기화가 되었으므로 이름은 다르지만 값이 변함
		System.out.println(myCar.speed);//100
		System.out.println(herCar.speed);//100
		
		if(myCar instanceof Car) {
			System.out.println("myCar는 Car클래스 타입입니다.");
		}else  {
			System.out.println("myCar는 Car클래스 타입이 아니다.");
			
		}
		if(herCar instanceof Car) {//상속관계라서 tivoli는 car클래스(부모)타입도 될수있다.
			System.out.println("herCar는 Car클래스 타입입니다.");
		}
		if(herCar instanceof Tivoli) {
			System.out.println("herCar는 Tivoli클래스 타입입니다.");
		}else  {
			System.out.println("herCar는 Car클래스 타입이 아니다.");
			
		}
	}

}
