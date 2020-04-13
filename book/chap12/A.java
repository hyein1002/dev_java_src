package book.chap12;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class A {
	//클래서 A에서 메소드4개를 호출해 보자.
	List<String> li  = null;
	public A() {
		li = new ArrayList<String>();
	}
	public static void main(String[] args) {
		B b = new B();
		new A();
		List<String> li2 = new Vector<String>();//왼쪽엔 항상 상위가 와야하고 오른쪽엔 항상 하위가 와야한다.
		Collection<String> col = new ArrayList<String>();//arrayList는 싱글스레드에 안정(경합이 벌어지지않음) - 동기화 구현이 안되어있다. - 속도빠름(백터보다)
		//강아지가 공을 물어오는 동안에 가만히 있으면 동기화, 강아지가 공을 가지고 오는 동안에 내가 다른일을 하고있으면 비동기화, 동기화는 서로 맞춘다는 뜻 내가 공을 던질테니 니가 물ㅇㅓ와
		Collection<String> col2 = new Vector<String>();//Vector는 멀티스레드(여러사람이 같이씀)에 안전 - 여러사람이 같이씀(버스)새치기 당하면 안전하지 못한거임 - 동기화를 구현함 - 속도는 느림(여러사람이 같이쓰니까)
		ArrayList<String> al = new ArrayList<String>();
		Vector<String> v = new Vector<String>();
		//Collection<String> col = new List<String>();은 안된다. 둘 다 인터페이스/추상클래스이기때문에
		b.methodA(col);
		b.methodA(new A().li);//List가 파라미터 타입은 메소드가 없어도 상위인 collection으로 간다.
		b.methodA(li2);
		b.methodA(al);//ArrayList가 파라미터 타입인 메소드가 없어도 상위인 list로 간다.
		b.methodA(v);
	}
}
