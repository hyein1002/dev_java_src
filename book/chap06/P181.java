package book.chap06;

import javax.swing.JOptionPane;

public class P181 {

	void methodA() {
		System.out.println("methodA 호출 성공");
	}
	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("숫자를 입력하세요.");//앞에 클래스타입이 옴. 이게 스테틱임. 인스턴스화 없이 바로 호출 가능
		int i = Integer.parseInt("20");
		//P181.methodA();<-메소드 선언 시 static을 사용하지 않았으므로 에러 발생함.
		//static이 없는 메소드를 호출할 땐 반드시 인스턴스화 할것
		//만일 static이 있다면 클래스 타입.메소드명
		P181 p = new P181();
		p.methodA();
		//"30" -> 30
		System.out.println(Integer.parseInt(input)+10);//String을 int로 바꿔줬으니까 더하기
		System.out.println(input+10);//string 변수 input이 왓으므로 붙여쓰기
	}

}
