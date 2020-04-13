package book.chap05;

import java.util.Scanner;

public class TTvSimulation {

	public static void main(String[] args) {

		TTv ttv = new TTv();
		System.out.println("TV를 보시겠습니까?");
		
		Scanner scan = new Scanner(System.in);
		boolean isOk = scan.nextBoolean();
		//너 전원 누른거니?
		if(isOk) {
			System.out.println("true를 입력하셨군요");
		}
		else {
			System.out.println("false를 입력하셨군요");
		}
		
		//ttv.power();//false!->true//파라미터가 없어서 값이 안넘어감
		//파라미터가 있어야 내 의사를 표현할 수 있다.->소통시작
		ttv.power(isOk);//false!->true
		System.out.println(ttv.power);
		if(ttv.power) {
			System.out.println("현재 TV를 시청중입니다.");
		}
		else {
			System.out.println("아무도 TV를 보고 있지 않아요.");
		}
	}

}
