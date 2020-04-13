package book.chap05;

public class PersonTest {

	public static void main(String[] args) {
		//파라미터가 있는 생성자가 한개라도 있으면 자동으로 제공되지 않아요
		//생성자가 한개도 없으면 JVM이 디폴트 생성자는 제공해주지만
		//한개라도 있을땐 제공하지 않아요
		Person p = new Person("강호동");
		System.out.println(p.name+", "+p.height+", "+p.weight);
		//p.name = "유재석";
		p.height = 180.f;
		p.weight = 80.5f;
		System.out.println(p.name+", "+p.height+", "+p.weight);
		
		p.setName("이순신");
		p.setHeight(180);
		p.setWeight(70);
		System.out.println(p.name+", "+p.height+", "+p.weight);
		
	}

}
