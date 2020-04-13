package book.chap05;

public class TTv {

	//전역변수는 그 클래스가 활동중에는 계속 유지됩니다.
	String color = null;
	boolean power = false;
	int channel = 2;
	
	void power() {
		power = !power;
	}
	/* 메소드의 파라미터자리는 사용자가 선택한 값, 입력한 값 등을 받아오는 자리입니다.
	 * u_power = true가 저장됨
	 * 19라인에서 그 변수에 not이 있으므로 반대인 false로 변환 후 대입된다 
	 * false->true
	 */
	void power(boolean u_power) {//호출할 때 결정된 값이 넘어오는 변수입니다.
		power = !u_power;
	}
	void channelUp() {
		++channel;
	}
	void channelDown() {
		--channel;
	}
}
