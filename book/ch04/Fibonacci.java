package book.ch04;

public class Fibonacci {

	public static void main(String[] args) {
		int fi[] = new int[20];
		fi[0] = 1;
		fi[1] = 1;
		
		System.out.println(fi[0]);
		System.out.println(fi[1]);
		int cnt = 2;
		for(; cnt<18; cnt++) {
		 fi[cnt] = fi[cnt-2] + fi[cnt-1];
		 System.out.println(fi[cnt]);
		}
	}


}
