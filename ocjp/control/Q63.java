package ocjp.control;

public class Q63 {

	 public static void main(String[] args) {
		 int i;
		 for (i=0;i<= 10;i++){
			if(i>6) break;//i가 7이면
		}
		 //요기로 탈출
		System.out.println(i); //i가 지역변수이기때문에 for문을 벗어나서 i를 찾을수없음
								//그래서 변수를 for문 밖에서 선언함
		}
}
