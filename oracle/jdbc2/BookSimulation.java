package oracle.jdbc2;
class Book{
	String title = null;
	int price = 0;
	public static Book getBook() {
		return new Book();
	}
}
public class BookSimulation {

	public static void main(String[] args) {
		Book myBook = new Book();//아래것과 같이 인스턴스화한 효과를 누릴 수 있다.
		//메소드를 호출해서 객체를 주입 받을 수도 있다. - 이런느낌
		Book gnomBook = Book.getBook();//위의것과 같이 인스턴스화한 효과를 누릴 수 있다.
		System.out.println(myBook+","+gnomBook);
	}

}
