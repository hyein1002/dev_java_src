package book.chap09;

public abstract class Duck {
	final int eye = 2;//오리의 눈이 2개니까 final
	public FlyBehavior flyBehavior = null;
	Duck(){//추상클래스는 생성자를 가질 수 있다.
		
	}
	public void performFly() {
		flyBehavior.fly();
	}
	public void fly() {
		System.out.println("오리는 날 수 있어요.");
	}
	
	public abstract void display();//오리들은 다 다르게 생겨서 추상메소드로 정의
	public void swimming() {//일반 메소드
		System.out.println("모든 오리는 물 위에 뜹니다. 가짜 오리도 물 위에 뜨죠");
	}
}
