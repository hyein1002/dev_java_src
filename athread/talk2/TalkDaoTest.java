package athread.talk2;

public class TalkDaoTest {

	public static void main(String[] args) {
		TalkDao td = new TalkDao();
		String result = td.login("sk5", "123");
		System.out.println("result:"+result);
	}

}
