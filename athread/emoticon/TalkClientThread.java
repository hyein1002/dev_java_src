package athread.emoticon;

import java.awt.Color;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TalkClientThread extends Thread {
	TalkClient	tc	= null;

	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
	}
	public SimpleAttributeSet makeAttribute(String fcolor) {
		SimpleAttributeSet sas = new SimpleAttributeSet();
		sas.addAttribute(StyleConstants.ColorConstants.Foreground, new Color(Integer.parseInt(fcolor)));
		return sas;
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
				case Protocol.LOGIN:{//로그인
					String nickName = st.nextToken();
					try {
						tc.sd_display.insertString(tc.sd_display.getLength(), nickName+"님이 입장하였습니다.\n"
												, new SimpleAttributeSet());
					} catch (Exception e) {
						// TODO: handle exception
					}
					Vector<String> v = new Vector<>();
					v.add(nickName);
					tc.dtm.addRow(v);
				}break;
				case Protocol.ONE:{//1:1
					String nickName = st.nextToken();//내이름
					String otherName = st.nextToken();//1:1상대이름
					String msg1 = st.nextToken();//메세지
					try {
						tc.sd_display.insertString(tc.sd_display.getLength(),nickName+"님이 "+otherName+"님에게"+msg1+"\n"
								, new SimpleAttributeSet());
						tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				case Protocol.MULTI:{//n:n
					String nickName = st.nextToken();
					String message = st.nextToken();
					String fontColor = st.nextToken();
					String imgChoice = st.nextToken();
					MutableAttributeSet attr = new SimpleAttributeSet();
					if(!message.equals("default")) {//이모티콘
						int i = 0;
						for(i=0;i<tc.emov.imgFile.length;i++) {
							if(tc.emov.imgFile[i].equals(imgChoice)) {
								tc.sd_display.insertString(tc.sd_display.getLength(),"["+nickName+"=>]",null);
								StyleConstants.setIcon(attr, new ImageIcon("src\\emoticon\\"+tc.emov.imgFile[i]));
								try {
									tc.sd_display.insertString(tc.sd_display.getLength(), "["+nickName+"=>]"+message+"\n", attr);

								} catch (Exception e) {
								}
							}
					
						}
					}
					if(!message.equals("이모티콘")) {
						SimpleAttributeSet sas = makeAttribute(fontColor);
						try {
							tc.sd_display.insertString(tc.sd_display.getLength(), "["+nickName+"=>]"+message+"\n", sas);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					tc.jtp_display.setCaretPosition(tc.sd_display.getLength());
				}break;
				case Protocol.CHANGE:{//대화명변경
					String nickName = st.nextToken();//이전이름
					String chatName = st.nextToken();//바꾼이름
					try {
						tc.sd_display.insertString(tc.sd_display.getLength(),nickName+"님은 "+chatName+"로 변경되었습니다.\n"
								, new SimpleAttributeSet());
					} catch (Exception e) {
						// TODO: handle exception
					}
					//테이블의 대화명 변경 하기
					for(int i=0;i<tc.dtm.getRowCount();i++) {//로우의 갯수를 계산해주는 함수
						String n = (String)tc.dtm.getValueAt(i, 0);//값꺼내기(getValueAt)
						if(n.equals(nickName)) {
							tc.dtm.setValueAt(chatName, i, 0);//값넣기(setValueAt)
							break;
						}
					}
					}break;
				case Protocol.EXIT:{//나가기
					String nickName = st.nextToken();//이전이름
					try {
						tc.sd_display.insertString(tc.sd_display.getLength(),nickName+"님이 퇴장하였습니다.\n"
								, new SimpleAttributeSet());
					} catch (Exception e) {
						// TODO: handle exception
					}
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
