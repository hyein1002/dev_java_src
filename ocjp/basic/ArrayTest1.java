package ocjp.basic;

public class ArrayTest1 {
	static void methodA(int is[]) {
		System.out.println("is : "+is);//hop spot
		//파라미터로 넘어온 is배열의 원본에 세번째 방에 있는 값을 0에서 10으로 오버라이트(덮어쓰기)
		is[2] = 10;
	}
	public static void main(String[] args) {
		//배열 선언과 생성하기-방이 세개 만들어짐.
		//변수is는 배열타입이고 배열의 변수명이다.
		int is[] = new int[3];//<-방이 3개가 만들어짐. 디폴트값으로 0이 초기화 되어있음
								// is[0]=0, is[1]=0, is[2]=0
		//배열의 주소번지 출력해보기
		System.out.println("main is : "+is);//hot spot
		//methodA(is)호출할때 is배열의 주소번지를 파라미터로 넘겨줌.
		methodA(is);
		//for(초기화;조건식;증감연산자)
		//for(int x = 0; x<3; x++) {
		for(int x = 0; x<3; ++x) {
			System.out.println("is["+x+"]="+is[x]);
		}
		System.out.println("==========================================");
		//개선된 for문 - 배열에 있는 모든 정보를 다 출력할 때 
		for(int a:is) {
			System.out.println(a);
		}
	}

}
