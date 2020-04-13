package athread.talk;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;
import java.util.Vector;

public class TalkClientThread extends Thread {
	TalkClient	tc	= null;

	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
	}
	/*
	 * 서버에서 말한 내용을 들어봅시다.
	 */
	public void run() {
		boolean isStop = false;
		while(!isStop) {
			String msg = "";//100#apple
			try {
				msg = (String)tc.ois.readObject();
//				tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
//				tc.jta_display.append(msg+"\n");
				StringTokenizer st = null;
				int protocol = 0;//100|200|201|202|500
				if(msg != null) {
					st = new StringTokenizer(msg,"#");
					protocol = Integer.parseInt(st.nextToken());//100
				}
				switch(protocol) {
				case 100:{//로그인
					String nickName = st.nextToken();
					tc.jta_display.append(nickName+"님이 입장하였습니다.\n");
					Vector<String> v = new Vector<>();
					v.add(nickName);
					tc.dtm.addRow(v);
				}break;
				case 200:{//1:1
					String nickName = st.nextToken();//내이름
					String otherName = st.nextToken();//1:1상대이름
					String msg1 = st.nextToken();//메세지
					tc.jta_display.append(nickName+"님이 "+otherName+"님에게"+msg1+"\n");
					tc.jta_display.setCaretPosition(tc.jta_display.getDocument().getLength());
					
				}
				case 201:{//n:n
					String nickName = st.nextToken();
					String message = st.nextToken();
					tc.jta_display.append("["+nickName+"=>]"+message+"\n");
				}break;
				case 202:{//대화명변경
					String nickName = st.nextToken();//이전이름
					String chatName = st.nextToken();//바꾼이름
					tc.jta_display.append(nickName+"님은"+chatName+"으로 변경되었습니다.\n");
					//테이블의 대화명 변경 하기
					for(int i=0;i<tc.dtm.getRowCount();i++) {//로우의 갯수를 계산해주는 함수
						String n = (String)tc.dtm.getValueAt(i, 0);//값꺼내기(getValueAt)
						if(n.equals(nickName)) {
							tc.dtm.setValueAt(chatName, i, 0);//값넣기(setValueAt)
							break;
						}
					}
					}break;
				case 500:{//나가기
					String nickName = st.nextToken();//이전이름
					tc.jta_display.append(nickName+"님이 퇴장하였습니다.\n");
					
					for(int i=0;i<tc.dtm.getRowCount();i++) {
						String n = (String)tc.dtm.getValueAt(i, 0);//값꺼내기
						if(n.equals(nickName)) {
							tc.dtm.removeRow(i);
						}
					}
						
				}break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
}
