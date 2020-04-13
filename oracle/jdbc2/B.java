package oracle.jdbc2;

public class B {
	public static void main(String[] args) {
		int i = 0;
		//i = 100/0;
	
		try {
			//예외가 발생할 수 있는 가능성이 있는 코드를 작성함
			//만약 예외가 발생하지 않으면 없는것과 동일한 실행
			i = 100/0;//<-이런게 예외, 0으로 나눌수없기때문, 문법적으로는 합법이지만 결과는 불가능
		} catch (Exception e) {
			System.out.println("0으로 나눈값은 계산할 수 없잖아.");
		}
	
		System.out.println("변수 i는 "+i);
	}
}
