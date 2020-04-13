package test.d20200219;

import java.util.ArrayList;

public class BankApp {
	public static void main(String[] args) {
			Bank b = new Bank();
			b = new Bank("자바맨", "1111111", 100000);
			System.out.println(b.toString());
			b.name = "자바맨";
			b.account = "123-456";
			b.cash = 10000;
			System.out.println(b.toString());
			b.deposit(15000);
			b.take(30000);
			new Bank("자바맨","123-456", 10000);
			System.out.println(b);
			System.out.println(b.take2(100));
		}
	}
