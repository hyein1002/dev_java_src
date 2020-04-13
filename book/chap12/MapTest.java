package book.chap12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
 * 인터페이스는 단독으로 인스턴스화 할 수가 없다. 
 * 반드시 구현채 클래스가 있어야 한다.
 * 컬렉션 프레임워크에서 제공되는 모든 클래스는 객체타입(object)만 담을 수 있다.
 */
public class MapTest {
	Map<String, Object> pMap = new HashMap<>();//왼쪽에 map이 올 수 없고 hashmap이 와야한다. map은 인터페이스라서
	//string은 키의 타입이고 object는 값의 타입. 값은 여러가지 타입이 올수있으므로 object로 해야한다.
	
	public void showAllDept2() {//선생님의 코드
		Object keys[] = pMap.keySet().toArray();//key값이 여러개니까 []로 받아주고 여러개니까 for문을 돌려야 함
		for(int i = 0;i<keys.length;i++) {
			String key = (String) keys[i];
			System.out.println(key+","+pMap.get(key));
		}
	}
	
	public void showAllDept() {//책의코드
		//Iterator<Object> ir = pMap.keySet().iterator();//pmap은 hashmap이다. keyset은 map거지만 hashmap이 아들이니까 아빠거를 쓸수있다.
		//map에서 key값의 타입을 string으로 정해줬기때문에 Integer<>타입에 key값의 타입이와야한다.
		//Iterator<String> ir = pMap.keySet().iterator();//아래는 나눠서 적은것
		Iterator<String> ir = null;//iterator는 Map에 담긴 정보를 꺼내는데 필요한 메소드를 제공함.
		Set<String> keys = pMap.keySet();
		ir = keys.iterator();
		int deptno = 0;
		String dname = null;
		String loc = null;
		while(ir.hasNext()) {
			String key = ir.next();//deptno, dname, loc
			if(pMap.get(key) instanceof Integer) {//꺼낸 key에 들어있는 값이 integer타입이니?
				deptno = Integer.parseInt(pMap.get(key).toString());//parseint는 문자를 숫자로 변환해줌
			}
			if(pMap.get(key) instanceof String) {
				if("dname".equals(key)) {//값을 비교할때는 equals를 사용
					dname = pMap.get(key).toString();//pMap.get(key)는 값의 타입이 object이니까 tostring을 사용해서 key값이 나오도록해주는것
				}
				else if("loc".equals(key)) {//key이름이 loc인지 equals로 확인
					loc = pMap.get(key).toString();
				}
			}//end of if
		}///end of while
		System.out.println(deptno+","+dname+","+loc);
	}///////end of showAllDept
	public static void main(String[] args) {
		//Map<String, String> pMap = new HashMap<>();//오브젝트타입만 담을수 있으므로 이렇게 쓰게되면 에러가 난다/
		MapTest mt = new MapTest();
		mt.pMap.put("deptno",10);//앞에가 키 뒤에가 값
		mt.pMap.put("dname","ACCOUNTING");//앞에가 키 뒤에가 값
		mt.pMap.put("loc","NEW YORK");//앞에가 키 뒤에가 값
		mt.showAllDept();
//		System.out.println(mt.pMap.get("deptno"));
//		System.out.println(mt.pMap.get("dname"));
//		System.out.println(mt.pMap.get("loc"));
	}

}
