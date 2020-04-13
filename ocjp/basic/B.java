package ocjp.basic;

public class B {
	void methodA() {
		
	}
	static void methodB() {
		
	}

	public static void main(String[] args) {
		B b = new B();
		b.methodA();//<-static이 없어서 인스턴스화를 해줘야함
		methodB();//<-static이 있으니까 그냥 호출 가능
	}

}
