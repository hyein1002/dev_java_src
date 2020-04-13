package study.bank;

public class Bank {
	String name = null;
	String account = null;
	int cash = 0;
	
	
	
	@Override
	public String toString() {
		return String.format("예금주:%s%n계좌번호:%s%n잔액:%s원%n"
								,name, account, cash);
	}
	public void take(int money) {//인출
		if(money>cash) {
			System.out.println("잔액이 부족합니다.");
		}else {
			System.out.println("인출금액->"+money);
			System.out.println("잔액->"+cash);
		}
	}
	public void deposit(int money) {//입금
		cash += money;
		System.out.println("입금액->"+money);
		System.out.println("잔액->"+cash);
	}

}
