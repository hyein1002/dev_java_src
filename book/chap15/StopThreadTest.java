package book.chap15;

public class StopThreadTest {
	public void process() {
		StopThread st = new StopThread();
		Thread th = new Thread(st);//자동차 안에 내비게이션을 켜면 스레드가 실행됨
		th.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		st.stop();
	}
	//16-17-18-4-5(Thread에 대한 객체생성)-6-7(run호출)-thread is alive-2번
	//-[StopThred]11(sleep)-13-[StopThread]19-Thread is deaded
	public static void main(String[] args) {
		StopThreadTest stt = new StopThreadTest();
		stt.process();
	}
}
