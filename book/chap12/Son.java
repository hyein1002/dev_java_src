package book.chap12;

public class Son extends Parent {

	public Son() {
		
	}
	@Override
	public void methodA() {
		System.out.println("아들에게 있는 methodA 입니다.");
	}
	public void sonMatod() {
		System.out.println("아들에게만 있는 sonMethod 입니다.");
	}

}
