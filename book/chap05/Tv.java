package book.chap05;
//전원을 먼저 키고 끄는걸 다시 만들어보기

import java.util.Scanner;

/*
 * TV의 속성 - 채널(int), 볼륨(int), 크기(double)
 * TV의 기능 - 채널 업다운(if), 볼륨 업다운(if), 온오프(boolean)
 */
public class Tv {
	int ch = 2;//채널의 디폴트값은 2번
	int vo = 0;//볼륨의 디폴트값은 0
	String chc;//채널컨트롤. +,-
	String voc;//볼륨컨트롤. +,-
	String po;//파워컨트롤. on/off
	
	int wch[] = new int[100];//원하는 채널 입력하기 위한 채널표
	
	void channel(String chc) {//채널컨트롤 메소드
		if(chc.equals("a")) {//채널업
			++ch;
			System.out.println("채널: "+ch);
		}
		else if(chc.equals("b")) {//채널다운
			--ch;
			System.out.println("채널: "+ch);
		}
	}
	
	void volum(String voc) {//볼륨컨트롤 메소드
		if(voc.equals("c")) {//+버튼이 1
			++vo;
			System.out.println("볼륨 : "+vo);
		}
		else if(voc.equals("d")) {//-버튼이 0
			--vo;
			System.out.println("볼륨 : "+vo);
		}
	}
	
	boolean power(String po) {//파워컨트롤 메소드
		if(po.equals("e")) {//on버튼이 1
			System.out.println("전원이 켜집니다.");
			return true;
		}
		else if(po.equals("f")) {//off버튼이 0
			System.out.println("전원이 꺼집니다.");
			return false;
		}
		return false;
	}
//	void power() {
//		power = !power;
//	}//power의 경우 값에 상관없이 항상 반대의 값으로 변경해주면 되므로 굳이 if를 사용하지 않아도 됨
	
	public static void main(String[] args) {
		Tv tv = new Tv();
		
		tv.wch[0] = 1;
		for(int c=1; c<tv.wch.length;c++) {//채널 100번까지 만들기
			tv.wch[c] = tv.wch[c-1]+1;
//			System.out.println(tv.wch[c]);
		}
		
//		2번-> tv.wch[1]->2번
//		n번 -> tv.wch[n-1]
		
		Scanner scan = new Scanner(System.in);
		System.out.print("원하는 채널 번호 입력 : ");
		int wc = scan.nextInt();
		if(wc==0) {
			System.out.println("다른 채널을 입력해 주세요.");
		}
		else {
		System.out.println(tv.wch[wc-1]+"번 채널로 이동합니다.");
		}
		
		
		System.out.println("원하는 걸 선택 <a:채널업, b:채널다운, c:볼륨업, d:볼륨다운, e:파워온, f:파워오프>\n메뉴를 나가시려면:out");
		for(int c=1;c>0;c++) {
			String w = scan.nextLine();
		if(w.equals("a")||w.equals("b")) {
			tv.channel(w);
		}
		else if(w.equals("c")||w.equals("d")) {
			tv.volum(w);
		}
		else if(w.equals("e")||w.equals("f")) {
			tv.power(w);
		}
		else if(w.equals("out")) {
			break;
		}
		}
		
//		System.out.print("채널 업/다운: ");
//		tv.chc = scan.nextInt();
//		tv.channel(tv.chc);
//		
//		System.out.print("볼륨 업/다운: ");
//		tv.voc = scan.nextInt();
//		tv.volum(tv.voc);
//		
//		System.out.print("전원을 켜시겠습니까?");
//		tv.po = scan.nextInt();
//		tv.power(tv.po);

	}

}
