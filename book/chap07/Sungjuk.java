package book.chap07;
/*<석차 매겨보기>
 * 1차배열, 2차배열 - 초기화하기(초기화하는방법), 꺼내서 사용하기
 * 배열에 담긴 정보를 꺼낼 수 있다.(반복문과 조건문 활용)
 * 개선된 for문 연습 - 컬렉션 프레임워크
 * 객체배열 따로 정리하기
 * 
 * 성적처리 방법
 * jumsus[i][j]
 * 변수 i는 row의 수 - 사람 구분
 * 변수 j는 colum수 - 과목 구분
 * 총점구하기
 * for문 한개로 가능하다?아니다?
 * 만일 아니다면 2개일것이다.
 * 만일 강호동의 총점을 구한다면 i가 고정된 상태에서 j가 변해야 한다?아니다?
 * 
 * 메소드를 사용할 것인가?
 * 1단계 - main메소드안에만 코딩한다.
 * 2단계 - 메소드로 꺼내어보기 : 재사용성과 이식성을 높이는 코드를 작성하기 
 * 
 * 총점과 평균 구하기에 매열을 사용할 것인가?
 * int korTotal = 0;
 * int engTotal = 0;
 * int mathTotal = 0;
 * 
 * 평균은 계산한 결과를 바로 출력하기
 * 
 */
public class Sungjuk {
	int jumsus[][] =
			{{100, 80, 90}  //1row - 강호동
			,{60, 70, 90}	//2row - 유재석
			,{80, 70, 70}
			,{90, 90, 90}
			,{80, 80, 80}
			};
	String names[] = {"강호동", "유재석", "강감찬","김유신","이성계"};
	String subjects[] = {"no","국어", "영어", "수학","총점","평균"};

 //	int total(int kor,int eng,int math) {
//		Sungjuk sj = new Sungjuk();
//		int result;
//		return result = kor+eng+math;
//	}

	static int korTotal=0;
	int engTotal;
	int mathTotal;
	
	public static void main(String[] args) {

		Sungjuk sj = new Sungjuk();
		
		for(int i = 0; i<sj.subjects.length; i++) {
			//System.out.print(""+sj.subjects [i]+" ");
		}
		System.out.println("no   kor   eng   math   total   avg");
		System.out.println("==============================================");
		
		int total = 0;
		double avg = 0;
		
		int engTotal = 0;
		int mathTotal = 0;

		for(int j=0; j<sj.jumsus.length;j++) {
			total = 0;//초기화를 시켜야 다음 점수들만 더해짐. 아니면  1번의 점수와 2번의 점수들이 다 더해짐
			avg = 0;
			System.out.print(j+1);
			for(int s=0; s<sj.jumsus[j].length;s++) {
				//for문에서 증감 연산자는 반복문이 진행되는 동안 계속 증감이 일어난다.
				//for문에 있는 조건문에서 비교할 때 
				System.out.print("    "+sj.jumsus[j][s]);
				total += sj.jumsus[j][s];
				avg = total/(double)sj.jumsus[j].length;
				}///end of inner
			System.out.println("    "+total+"    "+avg);
			korTotal += sj.jumsus[j][0]; 
			engTotal += sj.jumsus[j][1]; 
			mathTotal += sj.jumsus[j][2]; 			
			
			}///////end of outer
		System.out.println("==============================================");
		System.out.println("총점"+"   "+korTotal+"   "+engTotal+"   "+mathTotal);
//		 for(int i = 0; i<5;i++) {
//			// System.out.println(sj.jumsus[i][0]);
//		 }
		 int i = 0;
//		 while(i<2) {
//			 korTotal += sj.jumsus[0][i];
//			 if(i==2) {
//				 System.out.println(korTotal);					 
//			 }
//			 i++;
//		 }
//		 double korAvg = korTotal/3.0;
	}
	
	
}
