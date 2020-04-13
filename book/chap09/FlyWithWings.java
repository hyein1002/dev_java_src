package book.chap09;
//재사용성이 높아진다.
//독립성이 높아진다.
//단위테스트가 가능하다.
//단위테스트가 되어야 통합테스트도 할수있다.
//의존성이 낮다.
public class FlyWithWings implements FlyBehavior {

	@Override
	public void fly() {//인터페이스 재정의
		System.out.println("나는 날고있어요.");
	}

}
