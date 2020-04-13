package book.chap08;

public class MallardDuck extends Duck{
	@Override
	public void fly() {
		super.fly();
	}
	public void quack() {
		System.out.println("나는 꽥꽥 소리를 내요.");
	}
	@Override
	public void swimming() {
		System.out.println("청둥오리는 물 위에 뜨기도 하고 잠수도 가능합니다.");
	}

}
