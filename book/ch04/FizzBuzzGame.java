package book.ch04;

public class FizzBuzzGame {

	//if문으로 처리하기
	public void ifTest() {
		System.out.println("ifTest 호출 성공");
		int i = 0;//i=1
//		for(//초기화;조건식;증감연산자) {//1부터 100까지 센다 -while문으로도 처리할수있다
		for(i=1;i<101;i=i+1) {//1부터 100까지 센다 -while문으로도 처리할수있다
			if(i%35==0) {//35로 나누었을 때 0이야?
				System.out.println("fizzbuzz");
			}
			else if(i%5==0) {//5로 나누면 0이니?
				System.out.println("fizz");
			}
			else if(i%7==0) {//7로 나누었을 때 나머지가 0인거야?
				System.out.println("buzz");
			}
			else {
				System.out.println(i);
			}
		}
	}///////////end of ifTest
	
	//switch문으로 처리하기
	public void switchTest() {
		System.out.println("switchTest 호출 성공");
		int i = 1;
		for(i=1;i<101;i=i+1) {//1부터 100까지 센다 -while문으로도 처리할수있다//i를 선언했으니까 초기화를 안하고 비워놔도 됨
			switch(i%35) {
			case 0 :
				System.out.println("fizzbuzz");
				break;
			case 5 : case 10: case 15 : case 20: case 25: case 30: 
				System.out.println("fizz");
				break;
			case 7: case 14: case 21: case 28:
				System.out.println("buzz");
				break;
			default:
				System.out.println(i);
			}//end of switch
		}//end of for
	}////end of switchTest
	
	public void whileTest() {
		System.out.println("whileTest 호출 성공");
		int i = 1;
		while(i<=100) {
			if(i%35==0) {//35로 나누었을 때 0이야?
				System.out.println("fizzbuzz");
			}
			else if(i%5==0) {//5로 나누면 0이니?
				System.out.println("fizz");
			}
			else if(i%7==0) {//7로 나누었을 때 나머지가 0인거야?
				System.out.println("buzz");
			}
			else {
				System.out.println(i);
			}
			i++;
			}
		}
	}

