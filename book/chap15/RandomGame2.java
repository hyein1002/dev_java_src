package book.chap15;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomGame2 extends JFrame{
	
	public static void main(String[] args) {
		RandomGame2 rg = new RandomGame2();
		Random	r = new Random();
		int dap = r.nextInt(10);//0-9까지 채번
		String user = "-1";//사용자가 입력한 숫자 담기
		JOptionPane.showMessageDialog(rg, "0부터 9중에서 입력해봐", "INFO", JOptionPane.INFORMATION_MESSAGE);
		InputStreamReader in = new InputStreamReader(System.in);//기반스트림
		//버퍼링 기능이 추가되어 있는 보조스트림이다.
		//단독으로는 읽기 불가함.
		BufferedReader br	= new BufferedReader(in);//보조스트림//보조스트림 안에 기반스트림을 넣어줘야 사용할수있다.
		try {
//			user = in.read();//아스키코드 10진수를 가져온다.
			while(((user=br.readLine())!=null)) {
				System.out.println("사용자가 입력한 숫자 : "+user);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
