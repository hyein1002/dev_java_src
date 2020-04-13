package book.chap07;

public class ArrayString {

	public static void main(String[] args) {
		String nameList[] = {"안형재","이정훈","전진완","이진아"};
		String nameList2[][] = {
				{"안형재","이정훈","전진완","이진아"}
				,{"25","26","27","28"}
		};
		String nameList3[][] = {
				 {"안형재","이정훈","전진완","이진아"}
				,{"25","26","27","28"}
				,{"바둑","웨이크보드","당구","사이클"}
		};
		//내 친구의 취미만 출력
		for(int h = 0; h<nameList3[2].length;h++) {
			System.out.println(nameList3[2][h]+" ");
		}
		System.out.println("==============================");
		//nameList3은 배열의 이름이고 여기에 length이면 로우의 수이다.
		for(int i = 0; i<nameList3.length;i++) {
			for(int j = 0;j<nameList3[0].length;j++) {
				if(i==2) {//친구들의 취미이니?3번째 행에있어?
					System.out.println(nameList3[i][j]+" ");
				}
			}
		}
	}
}