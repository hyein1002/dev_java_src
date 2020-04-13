package basicmethod;
//메소드를 설계할 수 있다.
//리턴타입은 뭘로하지?-나는 실수영역까지 처리할거야-double- 1개
//파라미터는 몇개로 할까?-2개 - double, double-2개
//파라미터의 타입은 어떡하지?-실수영역까지
//메소드의 이름은 무엇으로 할까? - max
import java.util.Scanner;

public class MaxMethod {
	
	int max (int num1, int num2) {
		int maxNumber = 0;
		//둘(num1과num2) 중에 누가 더 크니?
		if(num1>num2) {
			//System.out.printf("최대값은 %d 입니다.",num1);<-이렇게 하면 void타입
			maxNumber = num1;
		}
		else if(num2>num1){
			//System.out.printf("최대값은 %d 입니다.", num2);
			maxNumber = num2;
		}
		else {
			maxNumber=0;
		}
		//return 0;<- return값을 정해도 되지만 변수로 하기
		return maxNumber;
	}//end of max

	public static void main(String[] args) {
		System.out.print("숫자를 입력하세요.");
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		System.out.print("숫자를 입력하세요.");
		int num2 = scan.nextInt();
		
		System.out.printf("첫번째 정수 : %d, 두번째 정수 : %d%n", num1, num2);
		MaxMethod mm = new MaxMethod();
		mm.max(num1, num2);
		System.out.println("최대값은 " + mm.max(num1, num2)+"입니다.");				
	}
}