package book.chap12;

public class ParentSonTest {

	public static void main(String[] args) {
		/*
		 * 자바에서는 생성부의 이름으로 객체가 생성된다.
		 * 따라서 부모클래스 타입으로 양쪽에 있는 메소드가 호출되면 아들타입에
		 * 정의된 메소드가 호출횐다.
		 * 부모클래서의 메소드는 은닉메소드가 된다.
		 * 그러나 만일 아들의 동일한(methoda)메소드가 존재하지 않으면 생성된 객체는 
		 * 아들 객체이지만 부모에 있는 메소드가 호출된다. 
		 */
		Parent p = new Son();
		p.methodA();
		p.parentMethod();
	}

}
