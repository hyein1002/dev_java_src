package test.d20200219;

import java.util.Calendar;

public class Age {
	
	public static void main(String[] args) {
		int year = 1992;//내가 태어난 년도
		Calendar cal = Calendar.getInstance();
		int nyear = cal.get(Calendar.YEAR);//현재 년도
		int age = nyear - year;//나의 나이(현재년도 - 태어난년도)
		System.out.println("내가 태어난 년도는 "+year+"입니다.");
		System.out.println("나의 나이는 "+age+"살 입니다.");

	}

}
