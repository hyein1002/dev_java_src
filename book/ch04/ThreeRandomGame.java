package book.ch04;

import java.util.Random;

public class ThreeRandomGame {
	//선언부
	//채번하는 메소드 선언
	//재사용성을 늘린다 -> 여러번 호출될 수 있다.
	//새게임 버튼을 누르거나 강제 종료 후 다시 시작할 때 호출됩니다.
	Random rd = new Random();
	
	int pc[] = new int[3];
	int my[] = new int[3];
	
	public void ranPC() {
		pc[0] = rd.nextInt(10);
		do {
			pc[1] = rd.nextInt(10);
		}while(pc[0]==pc[1]);
		do {
			pc[2] = rd.nextInt(10);
		}while((pc[0]==pc[1]) || (pc[1]==pc[2]));
	}
	
	
//	int com() {
//		int c = 1;
	
//		do {
//			int su = rd.nextInt(10);
//			pc[c] = su;
//			c++;
//		}while(c<3);
//		return pc[c];
//	}

	public static void main(String[] args) {
		//메인메소드
		ThreeRandomGame trg = new ThreeRandomGame();
		//0번방에 담기는 숫자는 반복할 필요가 없다.
		//두번재 방과 세번재 방에 담기는 숫자는 같은 숫자를 채번할 수 있으므로
		//다시 채번해야 할 경우가 발행할 수 있을것이다.
		//첫번째 방에 담긴 숫자와 두번쨰 방에 담긴 숫자가 같으면 다시채번 , 아니면 채번안함
		
		for(int i =0; i<11;i++) {
			trg.ranPC();
			System.out.println(trg.pc[0]+"" + trg.pc[1]+""+ trg.pc[2]);
		}
				
		
		
	}
	

}
