package book.chap15;

public class StopThread implements Runnable {//스레드를 종료하고 있는 방법
	boolean stopped = false;
	@Override
	public void run() {
//		while(true) {//권장방식이 아님
		while(!stopped) {//
			System.out.println("Thread is alive...");
			try {
				
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread is deaded");
	}
	
	public void stop() {
		stopped = true;
	}

}
