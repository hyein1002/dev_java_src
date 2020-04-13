package book.chap08;

public class WoodDuck extends Duck{

	public void quack() {
		System.out.println("나는 소리를 낼 수 없어요.");
	}
	@Override
	public void fly() {
		System.out.println("WoodDuck은 하늘을 날 수 없어요");
	}
	@Override
	public void swimming() {
		//재정의
		System.out.println("나는 물 위에 3초정도 떠 있다가 가라앉습니다.");
	}
}
