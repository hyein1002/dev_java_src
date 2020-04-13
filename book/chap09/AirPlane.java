package book.chap09;
//상속을 하면 결합도가 높아진다.
//상속을 하면 독립적이지 않다.
//상속을 하면 재사용성이 떨어진다.
//상속이 재사용성을 높이는 모범 답안일 수 없다.
//결론
//인터페이스 중심의 코딩을 전개한다.
public class AirPlane extends Duck {

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		AirPlane ap = new AirPlane();
		Duck ap2 = new AirPlane();
		ap.fly();
	}

}
