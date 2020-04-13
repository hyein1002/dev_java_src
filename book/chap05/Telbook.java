package book.chap05;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Telbook {
	//선언부
	int width = 600;
	int height = 500;
	String title = "전화번호부";
	JPanel jp_north = new JPanel();
	JButton jbtn = new JButton("전화목록을 출력할 영역");
	//객체배열선언
//	JButton jbtns[] = new JButton[4];//삭제
//	String jbtn_label[] = {"조회","입력","수정","삭제"};//삭제
	
	JButton jbtn_sel = new JButton("조회");
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	GridLayout glay = new GridLayout(1,4);
	//생성자
	public String toString() {
		return "나는 TelBook테스트 클래스임.";
	}
	
	//화면처리부
	public void display(){
		System.out.println("display 호출 성공");
		//윈도우 화면에 창을 만들어주는 클래스 입니다. 가로세로 변경 가능, 제목도 바꿀 수 있음
		JFrame jf_tel = new JFrame();
		//속지의 레이아웃을 GridLayout 1,4 로우 한개 컬럼 4개로 n분할
		jp_north.setLayout(glay);
		jp_north.add(jbtn_sel);
		jp_north.add(jbtn_ins);
		jp_north.add(jbtn_upd);
		jp_north.add(jbtn_del);
		//화면의 크기를 정해주세요. 600, 500
		//setSize메소드를 호출해보세요
		jf_tel.setSize(width, height);
		//jf_tel.setSize(400, 600);//위에게 먼저 실행됨. 그래서 출력은 아래문장으로 
		jf_tel.setTitle(title);//<-창의 제목을 정하는 메소드
		//String a = jf_tel.setTitle(title);//<-void라서 이렇게 쓸수 없음
		jf_tel.add("North",jp_north);
		jf_tel.add("Center",jbtn);
		jf_tel.setVisible(true);//화면을 활성화(true)하고 비활성화(false)함.
		
	}
	
	//메인메소드
	public static void main(String[] args) {
		Telbook tb = new Telbook();
		tb.display();
	}

}
