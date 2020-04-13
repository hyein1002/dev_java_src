package book.chap08;

public class Tivoli extends Car {
	String carColor;
	int volum;
	//생성자는 전변을 초기화 해준다.
	Tivoli() {
		carColor = "남색";
		volum = 0;
		super.speed = 50;
	}
	@Override
	public void stop() {
		System.out.println("override STOP 호출 성공");
		if(speed>0) {
		speed = speed -2;
		}
	}
	public void volumUP() {
		volum += 1;
	}
	public void volumDown() {
		volum -= 1;
	}
	
}
