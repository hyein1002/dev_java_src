package test.d20200219;
//1부터 1000까지 숫자->반복문(for,while)
//중 2의 배수 이거나 -> if
//5의 배수인 숫자->if
public class Hap {

	public static void main(String[] args) {
		int result = 0;//총 합의 결과를 담을 변수
		int i = 1;
		while(i<=1000) {//1000까지 반복//while문 쓸 때 break 꼭 사용*******************
			if(i%2==0 && i%5==0) {
				//제외
				continue;
			}
			else if(i%2==0 || i%5==0) {
				result += i;
			}
			i++;//무한루프방지코드***************************
		}
		System.out.println("1부터 1000까지의 숫자 중 2와 5의 배수인 숫자의 합은 "+result+"입니다.");
	
	int sum = 0;//총 합의 결과를 담을 변수
	//i를 초기화해서 i를 그대로 사용 가능
	for(int j = 1; j<=1000;j++) {//1000까지 반복
		if(j%2==0 && j%5==0) {
		}
		else if(j%2==0 || j%5==0) {
			sum += j;
		}
	}
	System.out.println("1부터 1000까지의 숫자 중 2와 5의 배수인 숫자의 합은 "+sum+"입니다.");
	}
}