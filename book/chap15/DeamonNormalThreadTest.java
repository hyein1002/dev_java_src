package book.chap15;
/*
 * 상황에따라 분리된 스레드로 백그라운드 작업을 해야 하는 경우가 있다.
 * JVM안에 가비지 컬렉션과 같은 작업이 대표적이다.
 * 이런 백그라운드 작업이 일반 스레드로 설정되어있다면 전원이 종료되거나 사용자가 강제종료
 * 하지 않는 한 애플리케이션은 영원히 정지 하지 않을 것이다.
 */
public class DeamonNormalThreadTest {

	public static void main(String[] args) {
		Thread th = new Thread() {
			public void run() {
				try {
					//5초가 될때까지 NormalThreadTest객체는 종료되지 않는다.
					Thread.sleep(5000);//milisecond 5초 정지
					System.out.println("INNER Thread 종료");
				} catch (InterruptedException e) {
					
				}
			}
		};
		th.setDaemon(true);//main이 끝나자마자 바로 종료됨. run thread를 기다려주지않음
		th.start();
		System.out.println("main종료");
		

	}
}
