package book.chap05;
/*
 * 쿼리문을 작성할 때 String을 사용하면 안되는 이유에 대해 말할 수 있다.
 * String은 원본이 절대로 바뀌지 않는다.
 * 따라서 변경하려면 반드시 새로운 변수에 그 값을 다시 담아야 한다.
 * 이렇게 되면 같은 이름의 변수가 n번 만큼 생성되므로 비효율적이다.
 * 따라서 이럴때는 StringBuilder를 사용한다.
 */
public class StringTest {
//주소번지를 비교한다.
//주소번지가 가리지는 값을 비교한다.
	public static void main(String[] args) {
		//값이 같은데 s1이 먼저 생성되었으므로 s2는 풍선을 안만들고 s1풍선에 s2끈이 생긴다
		//그러므로 주소번지가 같다. 그래서 true가 나옴
		String s1 = "apple";
		s1 = s1.replace('p', '1');
		System.out.println(s1);
		//s1 두개가 만들어져요. 그러니까 같은 타입의 변수 두개를 관리한다.
		StringBuilder sb = new StringBuilder("hello");
		sb.append("world");//원본에 붙여쓰기를 한 경우에 해당되므로 메모리 사용을 절약 가능
		System.out.println(sb.toString());
//		String s2 = "apple";
//		String s3 = new String("apple");
//		String s4 = new String("apple");
//		//똑같은 값을 가진 s1이 있기때문에 s2는 s1을 가리킨다.==은 주소번지를 비교
//		System.out.println(s1==s2);//true,false//값비교
//		//주소번지가 같니?
//		System.out.println(s3==s4);//true,false//주소번지비교
//		//그 주소번지가 가리키는 값이 같니?equals는 그 안에 있는 값을 가리킴
//		System.out.println(s3.equals(s4));
	}

}
