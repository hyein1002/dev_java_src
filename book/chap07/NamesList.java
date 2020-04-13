package book.chap07;

public class NamesList {

	public static void main(String[] args) {
		Sungjuk sj = new Sungjuk();
		for(String name:sj.names) {
			System.out.println(name);
		}
		//여기서 국어총점을 출력해보자
		//주소번지.변수이름은 전역변수만 할수있다.
		sj.main(args);//main안에서 계산을 해주기 때문에 main 메소드를 실행시켜줘야 한다.
		int k_tot = sj.korTotal;
		int kr_tot = Sungjuk.korTotal;
	
		System.out.println(k_tot);
		System.out.println(kr_tot);
	}

}
