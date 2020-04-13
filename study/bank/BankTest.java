package study.bank;

public class BankTest {
	
	public static void main(String[] args) {
		Bank b = new Bank();
		b.name = "자바사랑";
		b.account = "123-456-789";
		b.cash = 1000;
		System.out.println(b.toString());
		b.deposit(15000);
		b.take(30000);
	}
}
