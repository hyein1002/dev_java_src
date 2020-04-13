package book.chap09;

public class MallardDuck extends Duck {
	public MallardDuck(){
		flyBehavior = new FlyWithWings();//선언부에는 인터페이스 생성부에는 구현체클래스가 옴. 다형성을 누릴 수 있다.
	}
	@Override
	public void display() {//템플릿메소드
		// TODO Auto-generated method stub
		
	}



}
