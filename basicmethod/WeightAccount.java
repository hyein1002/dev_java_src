package basicmethod;

import java.util.Scanner;

public class WeightAccount {

	static double mw (double w) {//지구의 몸무게
		double result = 0;
		result = (w * 17)/100.0;
		return result;//달의 몸무게
	}
	public static void main(String[] args) {
		System.out.println("당신의 몸무게를 입력하세요. : ");
		Scanner scan = new Scanner(System.in);
		double d_ew = 0;//지구의 몸무게, 0으로 초기화
		d_ew = scan.nextDouble();
		System.out.println("당신이 입력한 값은 "+d_ew);
		//달의 몸무게를 담을 변수가 필요
//		double d_mw = 0;//달의 몸무게
//		//17%
//		d_mw = (d_ew * 17)/100.0;
//		System.out.println("달의 몸무게 :" + d_mw);
		
		System.out.println("달에서의 몸무게는 : " + mw(d_ew));
	}

}
