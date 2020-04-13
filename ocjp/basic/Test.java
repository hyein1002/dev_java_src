package ocjp.basic;

public class Test {
	 public static void replaceJ(String text) {
		 text.replace('j', 'l');
	 }
	 public static String replaceJ2(String text) {
		 String imsi = null;
		 imsi = text.replace('j', 'l');//대입연산자를 통해서 받아야 변한값이 유지가 됨.
		 return imsi;
	 }
	 public static void main(String args[]) {
		 String text = new String("java");
		 replaceJ(text);
		 System.out.println(text);
		 System.out.println("===============================");
//		 replaceJ2(text);
//		 System.out.println(text);
		 String text2 = null;//리턴값을 받을 변수가 필요함
		 text2 = replaceJ2(text);//리턴값을 받을 변수가 필요함
		 System.out.println(text2);
	}
}
