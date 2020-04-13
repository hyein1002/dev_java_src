package book.chap15;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest1 {
	
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src\\book\\chap15\\input.txt");
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			System.out.println((char)fis.read());
			
		} catch (IOException e) {
			System.out.println(e);
		}finally {
			try {
				fis.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}catch(NullPointerException e) {
				System.out.println(e);
			}
		}
		System.out.println("end");
	}
}
