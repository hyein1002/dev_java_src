package book.chap15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ViewURL {
	public ViewURL() {}
	
	public ViewURL(String strURL) {
		URL 			myURL 		= null;
		URLConnection 	urlCon 		= null;
		InputStream 	is 			= null;
		BufferedReader 	br 			= null;
		String			data		= null;
		String			headerType	= null;
		try {
			myURL = new URL(strURL);
			urlCon = myURL.openConnection();
			urlCon.connect();
			headerType = urlCon.getContentType();//mime type
			is = urlCon.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			File f = new File("src\\book\\chap15\\google_source.txt");
//			FileOutputStream fos = new FileOutputStream(f);//파일생성, 그렇지만 안에 내용은 없다.
			FileOutputStream fos = new FileOutputStream(f,true);//true를 붙이면 뒤에 이어서 내용을 넣어줌
			while((data=br.readLine())!=null) {
				fos.write(data.getBytes());//data의 파일을 google_source에 입력해줌
//				System.out.println(data);
			}
			fos.flush();
			fos.close();
		} catch (Exception e) {
			System.out.println("Exception:"+e.toString());//오류내용
			e.printStackTrace();//오류난 곳의 줄이나옴
		}
	}

	public static void main(String[] args) {
		new ViewURL("http://www.google.com/index.html");
	}

}
