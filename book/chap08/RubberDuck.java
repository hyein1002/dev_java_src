package book.chap08;

public class RubberDuck extends Duck{
	public void quack() {
		System.out.println("나는 삑삑 소리를 내요.");
	}
	@Override
	public void fly() {
		System.out.println("RubberDuck은 날 수 없어요.");
	}
	@Override
	public void swimming() {
		//재정의
		System.out.println("나는 물 위에 뜰 수 있지만 잠수는 불가능합니다.");
	}
	
}
