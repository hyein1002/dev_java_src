package book.chap07;

/*
* 1차원 배열 선언 - 사람 5명 - [5]
* 2차원 배열 선언 - 과목3개, 사람5명 - [5][3]배열선언
* 
*/
	public class SungJuk2 {
		String h[] = {"김", "이", "박", "문", "강"};
		int s[][] = {
					{100, 80, 90}
					,{60, 70, 90}
					,{80, 70, 70}
					,{90, 80, 90}
					,{80, 80, 80}
					};
	
	public static void main(String[] args) {
		SungJuk2 sj2 = new SungJuk2();
		System.out.println("no name kor eng math total avg              등수");
		System.out.println("==========================================");

		int r = 0;
		int c = 0;
		int total;
		double avg = 0;
		int korTotal = 0;
		int engTotal = 0;
		int mathTotal = 0;
		
		while(r<sj2.s.length) {  
			System.out.print(r+1+"   "+ sj2.h[r]+"     ");
			total = 0;
			c=0;//아래 while문에서 증가한 c가 계속 3인 상태라서 초기화를 시켜줘야 다시 0번째 방부터 출력
			while(c<sj2.s[r].length) {
				System.out.print(sj2.s[r][c]+"  ");
				total += sj2.s[r][c];
				avg = (double)total/3.0;
					c++;
			}
				System.out.println(total+"  "+avg);
				korTotal += sj2.s[r][0];
				engTotal += sj2.s[r][1];
				mathTotal += sj2.s[r][2];
				r++;
		}

		System.out.println("==========================================");
		System.out.println("총점:    "+korTotal+"  "+engTotal+"  "+mathTotal);		
	}
}
