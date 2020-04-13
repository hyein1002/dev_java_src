package book.chap05;

public class TelBookSimulation {

	public static void main(String[] args) {
		Telbook tb = new Telbook();
		System.out.println(tb);
		//toString메소드는 내 안에 없는 메소드 이지만 모든 클래스의 부모 클래스인
		//Object안에 정의된 메소드임
		System.out.println(tb.toString());
	}

}
