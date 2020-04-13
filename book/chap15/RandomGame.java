package book.chap15;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomGame extends JFrame{
	
	public static void main(String[] args) {
		RandomGame rg = new RandomGame();
		Random	r = new Random();
		int dap = r.nextInt(10);//0-9까지 채번
		int user = -1;//사용자가 입력한 숫자 담기
		JOptionPane.showMessageDialog(rg, "0부터 9중에서 입력해봐", "INFO", JOptionPane.INFORMATION_MESSAGE);
		InputStreamReader in = new InputStreamReader(System.in);
		try {
//			user = in.read();//아스키코드 10진수를 가져온다.
			while(((user=in.read())!=-1)) {
				
				System.out.println("사용자가 입력한 숫자 : "+user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
