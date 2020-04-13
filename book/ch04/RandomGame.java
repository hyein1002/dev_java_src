package book.ch04;

import java.util.Random;
import java.util.Scanner;

/*
 * 0부터 9사이의 임의의 숫자를 채번하기
 * 
 */
public class RandomGame {
	public static void main(String[] args) {
		int imsi;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("0부터 9까지의 숫자를 입력해주세요.");
		//int s = scan.nextInt();
		
		Random r = new Random();
		imsi = r.nextInt(10);//10은 범위에 포함이 안됨
		
		int cnt = 0;
		while(cnt<3) {
			int s = scan.nextInt();
			if(cnt==2) {
				System.out.println("바보");				
			}
			else if(s<imsi) {
				System.out.println("입력한 숫자를 높여주세요");
			}
			else if(s>imsi) {
				System.out.println("입력한 숫자를 낮춰주세요");
			}
			else if(s==imsi) {
				System.out.println("정답입니다.");
				break;
			}
			cnt++;
		}
		
	}

}
