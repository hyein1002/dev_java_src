package ocjp.basic;

import java.util.Scanner;

public class WrapperTest {

	public static void main(String[] args) {
		int i = 5;
		//6번의 변수 i에대한 Wrapper클래스라고 함.
		Integer oi = new Integer(5);
		//원시형타입 i는 메소드를 호출할 수 없다.
		//System.out.println(i.intValue());
		System.out.println(oi.intValue());
		
		Double pi = new Double(3.14);
		double d = pi.doubleValue();
		double d1 = pi;  
		//오토박싱-자동으로 원시형과 참조형 사이에 타입을 맞춰줌
		
		Scanner scan = new Scanner(System.in);
		System.out.println("당신의 몸무게를 입력하세요 : ");
		int w = scan.nextInt();
		
		double result;
		result = w * 0.17;
		
		System.out.println("달에서 몸무게는 : "+ result);
	}

}
