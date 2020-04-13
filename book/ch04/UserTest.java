package book.ch04;

import java.util.Scanner;

public class UserTest {
	//사용자가 입력한 값을 받아오는 곳
	/***********************************************************************
	 * 사용자가 입력한 값에 대한 힌트를 출력하는 메소드입니다.
	 * @param user 사용자가 입력한 세자리 숫자입니다.
	 * @return 컴터가 채번한 숫자와 사용자가 입력한 숫자를 비교할 후 힌트문을 전달합니다.
	 ***********************************************************************/

	public String account(String user) {
		int my[] = new int[3];

		int temp = Integer.parseInt(user);
		my[0]= temp/100;
		my[1]= (temp%100)/10;
		my[2]= temp%10;
		
		
		ThreeRandomGame trg = new ThreeRandomGame();
		
		int strike = 0;
		int ball = 0;
		
		for(int i = 0; 0<trg.pc.length;i++) {
			for(int j = 0; 0<trg.my.length;j++) {
				if(trg.pc[i]==trg.my[j]) {//내가 입력한 숫자중에 컴터에 그 숫자가 있니?
					if(i==j) {//혹시 그 숫자가 자리도 일치하는거야?
						strike++;
					}//스트라이크 결정
					else {
						ball++;
					}
				}//볼카운트 확보
			}//나의 숫자 
		}
		return strike+"스"+ball+"볼";
	}
	public static void main(String[] args) {

		UserTest ut = new UserTest();
		
		System.out.print("세자리 숫자를 입력하세요 : ");
		Scanner scan = new Scanner(System.in);
		
		int imsi = 0;//채번한 숫자를 담을 변수
		int cnt = 0;//입력 받은 횟수를 세는 변수
		while(cnt<3) {
			imsi = scan.nextInt();
			System.out.println(cnt+": 사용자가 입력한 숫자는 "+imsi+"입니다");
			cnt++;
		}
		

	}

}
