package book.chap09;
//인터페이스는 추상메소드만 갖는다
//그래서 abstract를 생략가능하다
public interface FlyBehavior {
	public void fly();//public 뒤에 abstract 생략 가능
//	public void fly2() {}//일반메소드를 가질 수 없다.
//	public FlyBehavior() {}//생성자를 가질 수 없다.
}
