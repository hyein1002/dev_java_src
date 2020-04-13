package test.d20200219;

public class Bank extends Object{//클래스의 아버지인 objeect, 근데 기본으로 들어가있어서 이렇게 안적어줘도 됨
	//변수선언 - 고유명사 - 그 이름으로 클래스가 연상되는 그런 아이들 - 전역변수
	String name;//예금주
	String account;//계좌번호
	int cash;//잔액
	
	//디폴트생성자
	public Bank() {//생성자에서 변수 초기화
		name = null;
//		name2 = "";//null과 ""의 차이??
		account = null;
		cash = 0;
	}
	public Bank(String name, String account, int cash) {
		this.name = name;
		this.account = account;
		this.cash = cash;
	}
	
	//메소드선언 - 리턴타입과 파라미터
	//DB연동하기 - select, insert, update, delete 무조건 먼저 작성해보기
	//쿼리문 안에는 ?가 있다. - 사용자가 입력하는 값을 넣어줄 곳 - 파라미터
	//처리 결과를 사용자에게 응답해야 한다 - 리턴타입, 리턴값
	
	/* 원래는 부모가 가진 메소드이므로 별도로 정의하지 않아도 호출할 수 있다.
	 * 그러나 추가로 작성하고 싶은 내용이 있다면 언제든지 재정의 할 수 있다.
	 * 재정의 할 때 반환값을 문자열로 바꾸었으므로 주소번지 대신
	 * return값이 나옴.
	 * 설계한 클래서의 기본 정보를 출력할 때 많이 활용 된다.
	 * 또한 UI/UX 화면단을 구현해주는 외부 클래스에도 적용할 수 있다. 
	 */
//	@Override
//	public String toString() {//아빠가 가진 기능을 재정의하였다.//인스턴스화를 하면 자동으로 호출되는 메소드******
//		return String.format("예금주:%s%n계좌번호:%s%n잔액:%d원%n"
//								,name, account, cash);<-print f가 format
//	}
	@Override
	public String toString() {//아빠가 가진 기능을 재정의하였다.//인스턴스화를 하면 자동으로 호출되는 메소드******
		String accountINFO
		= "예금주는"+name+", 계좌번호는"+account+", 잔액은"+cash;
		return accountINFO;
	}
	
	public void take(int money) {//인출메소드//얼마를 가져갈거니? - 파라미터
		if(money>cash) {
			System.out.println("잔액이 부족합니다.");
			//계속 기다리고 있으면 입금 등 다른 일을 못하니까 리턴해서 나와야함********************
			return;//모자르니까 바로 리턴해서 나와야함. 그러고 메세지를 출력해야함. 기다리면 안되고 바로 빠져나와야함***************
		}else {
			System.out.println("인출금액->"+money);
			System.out.println("잔액->"+cash);
		}
	}
	public int take2(int money) {
		if(money>cash) {
			System.out.println("잔액이 부족합니다.");
		}else {
			cash -= money;
			System.out.println("인출금액->"+money);
			System.out.println("잔액->"+cash);
		}
		return cash;
		}
	public void deposit(int money) {//입금메소드
		cash += money;
		System.out.println("입금액->"+money);
		System.out.println("잔액->"+cash);
	}
	public static void main(String[] args) {
		Bank b = new Bank();
		b.name = "자바맨";
		b.account = "123-456";
		b.cash = 1000;
		System.out.println(b.toString());
	}

}
