package oracle.jdbc2;

import java.util.Vector;

public class ZipCodeList {

	public static void main(String[] args) {
		//Vector<Object> v2 = new Vector<Object>();
		Vector v2 = new Vector();//제네릭을 생략한 경우, 타입을 알수없다. 이러한 경우에도 타입을 맞춰줘야함
		v2.add("사과");//add는 넣다
		v2.add("딸기");
		v2.add(1,"바나나");
		Vector<String> v = new Vector<String>();
		v.add("사과");//add는 넣다
		v.add("딸기");
		v.add(1,"바나나");//끼워넣기
		v2.remove(2);
		//v2.remove(2);
		for(int i = 0; i<v.size();i++) {
			String f = v.get(i);
			String f2 = (String)v2.get(i);
			System.out.println("v : "+v.get(i));//get은 꺼내다
		}
	}

}
