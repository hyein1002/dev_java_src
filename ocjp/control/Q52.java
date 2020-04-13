package ocjp.control;

public class Q52 {
	 public enum Dogs {collie, harrier, shepherd};
	 public static void main(String [] args) {
		 Dogs myDog = Dogs.collie;
		 switch (myDog) {
			 case collie:
			 System.out.print("collie ");
			// break;//브레이크가 없으면 다음 실행문이 실행된다
			 default:  //case default: 가 아니고 default: 이다
			 System.out.print("retriever ");//디폴트를 만난 다음에는 다음 조건문을 실행해버린다
			 //보통은 디폴트가 맨 끝에 온다
			 //break;
			 case harrier:
			 System.out.print("harrier ");
			 break;
		 }///end of switch
	 }//////end of main
}///////////end of Q52
