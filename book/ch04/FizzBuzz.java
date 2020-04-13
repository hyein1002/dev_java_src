package book.ch04;

public class FizzBuzz {

	public static void main(String[] args) {
		
		int i;
		
		for(i = 1;i<=100;i++) {//i를 0으로 초기화를 해줄경우 ++i로 해주면 됨
			if(i%5==0 && i%7==0) {//i%35==0으로 해줘도 됨
				System.out.println(i+" = fizzbuzz");
			}
			else if(i%5==0) {
				System.out.println(i+" = fizz");
			}
			else if(i%7==0) {
				System.out.println(i+" = buzz");
			}
			else {
				System.out.println(i);
			}
		}
		System.out.println("=========================================");

	}

}
