package book.chap06;
/*
 * 10번 12번 14번의 주소번지 모두 다른 값일 것이다.
 * 10번 12번은 같지만 14번은 다를것이다.
 * 10번 12번 14번 모두 같은 주소값을 가질것이다.
 */
public class Tivoli {
	public int speed = 0;
	//디폴트 생성자 구현하기 - 파라미터가 없는 생성자임
	//JVM이 대신 만들어 줄 수 있는 생성자 - 파라미터를 결정하지 않아도 되니까 제공가능함
	Tivoli(){//생성자의 기본적인 역할은 초기화이다.
		//this - 자기자신 주번
		//this() - 자기자신 생성자를 호출
		this(50);//생성자 호출하기, 16번 생성자를 호출한다.
	}
	Tivoli(int speed){
		this.speed = speed;//14번에서 입력한걸 여기에서 전역변수에 전달해줌
	}
	
	public static void main(String[] args) {
		Tivoli mycar = new Tivoli();//디폴트생성자호출하기-11-14(50)-16-17//복제를 하면 주소번지가 달라진다. new를 붙이면 새로운 객체생성
		mycar.speed = 30;
		System.out.println("mycar 주.번 : "+mycar);
		System.out.println("나는 현재"+mycar.speed+"로 가는중이야.");
		//자바에서는 같은 이름의 변수를 중복선언불가
		mycar = new Tivoli();//mycar 이름으로 하나를 더 복제해서 새로운 주소번지가 나옴. 10번은 끊어짐. 이제 쓸수없음
		mycar.speed = 50;
		System.out.println("mycar2 주.번 : "+mycar);
		System.out.println("나는 현재"+mycar.speed+"속도를 내고있어.");
		Tivoli hercar = new Tivoli();
		hercar.speed = 100;
		System.out.println("mycar3 주.번 : "+hercar);
		System.out.println("나는 현재"+hercar.speed+"속도로 달리고 있어.");
	}


}
