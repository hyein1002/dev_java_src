package basicmethod;

public class MyMathSimulation {

	public static void main(String[] args) {
		MaxMethod mm = new MaxMethod();//인스턴스화
		int maxNumber = mm.max(65,5);//max메소드를 불러옴
		System.out.println(maxNumber);				
	}

}
