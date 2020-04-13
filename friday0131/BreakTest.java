package friday0131;

public class BreakTest {

	public static void main(String[] args) {
		for(int i=1; i<4;i++) {
			if(i==2) {
				break;//break를 만나면 for문 탈출
			}
			System.out.println("여기....");
		}
		System.out.println("for문 밖....");
	}

}
