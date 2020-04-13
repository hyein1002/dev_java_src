package ocjp.basic;

public class X {
	 public static void modify(String s) {

	 }
	public static void main(String[] args) {
		//String타입은 절대로 원본이 바뀌지 않는다.
		 String s = new String("Hello");//일반 참조형 타입 선언문
		 String s1 = "Hello";//원시형타입
		 //s1=s1+"world";//<-대입연산자를 활용해야 바뀐다.
		 //s1 += "world";
		 System.out.println(s1+"world");
		 if(s==s1) {//주소번지가 같니? s와 s1이 참조형타입
			 System.out.println("주소번지가 같아요");
		 }
		 else {
			 System.out.println("주소번지가 달라요");
			 
		 }
		 //주번이 가리키는 값을 비교함<-equals메소드
		 if(s.equals(s1)) {//주소번지가 가리키는 값이 같은거야?
			 System.out.println("주.번이 가리키는 값이 같아요");

		 }
		 else {
			 System.out.println("주.번이 가리키는 값이 달라요");

		 }

	}
		

	}
