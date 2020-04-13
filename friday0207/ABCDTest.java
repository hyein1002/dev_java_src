package friday0207;
//클래스 A,B,C에는 main메소드가 없다.
//****생성자보다 전역변수가 먼저 호출됨.********
class A {
	//B b = new B();//11번 호출
	String name = null;
	A(){
		System.out.println("디폴트 A생성자.");
	}
	A(String name){
		System.out.println("파라미터가 name인 A생성자.");
		this.name = name;
	}
	A(ABCDTest a){
		System.out.println("this ABCDTest를 받는 생성자.");
	}
}
class B{
	A a = new A();
	B(){ 
		System.out.println("디폴트 B생성자.");
	}
}
class C {
	A a = new A();
	B b = new B();
}
public class ABCDTest {
//22-23-3-6
//클래스 안에 디폴트 생성자를 생략할수도 있고 명시적으로 선언할 수도 있다.
	ABCDTest(){
		A a1 = new A(this);//this는  - ABCDTest 해당 클래스를 나타냄, this(ABCDTest)클래스를 받을수 있는(파라미터) 생성자가 있어야함
	}
	public static void main(String[] args) {
		new ABCDTest();
		//A a1 = new A("김혜인");//디폴트 A생성자 호출 그리고 heap메모리에 로딩됨.
	}

}
