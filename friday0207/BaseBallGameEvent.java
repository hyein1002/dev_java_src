package friday0207;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.test.DBConnectionTest;
import com.util.DBConnectionMgr;

import sun.security.pkcs11.Secmod.DbMode;

public class BaseBallGameEvent implements ActionListener {

	BaseBallGameView bbView = null;//********************
	//게임을 진행하는 동안에는 계속 그 숫자를 기억하고 있다가 1씩 증가되야 하니까....
	//전역변수로 해야함.
	int cnt = 0;//회차를 출력할 변수 선언
	DBConnectionMgr bdMgr = null;
	Connection con = null;
	
	public BaseBallGameEvent(BaseBallGameView baseBallGameView) {//********************
		this.bbView = baseBallGameView;//7번의 전역변수를 파라미터로 초기화를 해줘야함
	}
	
	public void exitGame() {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		//insert here
		//BaseBallGameView bbView = new BaseBallGameView();<-new를 쓰면 디폴트 생성자(디폴트생성자에 화면이 있어서)가 불려와서 화면이 계속 뜸
		if(obj==bbView.jmi_oracle) {
			bdMgr = DBConnectionMgr.getInstance();
			con = bdMgr.getConnection();
			if(con!=null) {
				System.out.println("오라클 테스트 호출 성공"+con);
			}else {
				System.out.println();
			}
		}
		else if((obj == bbView.jbtn_exit) || (obj == bbView.jmi_exit)) {
			System.out.println("나가기 버튼 호출 성공");
			exitGame();
		}
		else if(obj == bbView.jbtn_next||(obj == bbView.jmi_next)) {
			System.out.println("다음 게임 버튼 호출 성공");
			cnt = 0;//다음게임을 누르면 회차가 초기화가 되어야하니까 초기화해주기
			bbView.bbLogic.ranCom();//<-클래스 안에 클래스안에 메소드를 불러옴
			bbView.jbtn_dap.setEnabled(true);
			//BaseBallGameLogic안에 com배열에 선언되어있음
			//인스턴스화를 한 상태이므로 접근이 가능
			for(int coms : bbView.bbLogic.com) {
				System.out.print(coms+" ");
			}
			System.out.println();
		}
		else if(obj==bbView.jtf_input) {
			String user = bbView.jtf_input.getText();
//			for(int coms : bbView.bbLogic.com) {
//				System.out.print(coms+" ");
//			}
			bbView.jta_display.append(++cnt+"회 : "+user+"==>");//append는 추가해주는메소드
			//insert here - 오라클 서버에 insert문 요청 처리하기
			//수집해야 하는 정보를 출력해보기
			System.out.println("mem_id: "+bbView.result[1]);
			System.out.println("game_seq: "+ cnt);
			System.out.println("input: "+user);
			System.out.println("hint: "+(bbView.bbLogic.account(user)));
			System.out.println("dap: "+(bbView.bbLogic.com[0]+""+bbView.bbLogic.com[1]+""+bbView.bbLogic.com[2]));

			//VO에 값을 초기화 할 때 생성자를 활용해보세요.
			BaseballVO bbVO = new BaseballVO(cnt,user,bbView.bbLogic.account(user)
							,(bbView.bbLogic.com[0]+""+bbView.bbLogic.com[1]+""+bbView.bbLogic.com[2])
							,bbView.result[1]);
			System.out.println("mem_id: "+bbVO.getMem_id());
			System.out.println("game_seq: "+ bbVO.getGame_seq());
			System.out.println("input: "+bbVO.getInput());
			System.out.println("hint: "+bbVO.getHint());
			System.out.println("dap: "+bbVO.getDap());
			bbView.jtf_input.setText("");
			int result = bbView.bbLogic.history(bbVO);
			if(result==1) {
				JOptionPane.showMessageDialog(bbView, "등록성공");
			}else if(result==0) {
				JOptionPane.showMessageDialog(bbView, "등록실패");
			}
			bbView.jta_display.append(bbView.bbLogic.account(user)+"\n");
			//radcom에서 뽑은 숫자랑 user의 값을 account로 보내주기
			//자바에서는 같은 이름의 메소드를 정의할 수 있다.
			//단 파라미터의 갯수가 다르거나 파라미터 타입이 반드시 달라야 한다.
		}
		else if(obj==bbView.jbtn_clear||(obj==bbView.jmi_clear)) {
			System.out.println("지우기 버튼 호출 성공");
			bbView.jta_display.setText("");
		}
		else if(obj==bbView.jbtn_dap||(obj==bbView.jmi_dap)) {//답은 com배열에 저장되어있으므로 com배열을 불러오기
			System.out.println("정답 버튼 호출 성공");
			bbView.jta_display.append(bbView.bbLogic.com[0]+""+bbView.bbLogic.com[1]+""+bbView.bbLogic.com[2]+"\n");
			bbView.jbtn_dap.setEnabled(false);
//			for(int coms : bbView.bbLogic.com) {
//				System.out.print(coms+" ");
//			}
		}
}

}
