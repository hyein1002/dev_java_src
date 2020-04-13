package design.book;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDialog extends JDialog implements ActionListener {
	/*
	 * 자녀창에서 저장(입력) 성공했을 때 부모창의 refreshData를 호출해야 한다.
	 * 그런데 원본을 재사용해야 하므로 set메소드의 파라미터로 받아서 사용할 것이다.
	 * 다른 메소드에서 ba를 사용해야 하니까 전역변수로 선언한 다음 초기화를 반드시 할것.
	 */
	String 		imgPath 	= "src\\design\\book\\";
	//도서 이미지 추가 해 보기
	ImageIcon	icon		= null;
	boolean 	isView 		= false;
	String		title		= null;
	//인스턴스화를 하면 생성자 호출이 일어남
	JLabel 		jlb_title 	= new JLabel("책제목");
	JTextField 	jtf_title	= new JTextField(20);
	JLabel 		jlb_author 	= new JLabel("저자");
	JTextField 	jtf_author	= new JTextField(20);
	JLabel 		jlb_publish	= new JLabel("출판사");
	JTextField 	jtf_publish	= new JTextField(20);
	JLabel 		jlb_detail 	= new JLabel("도서소개");
	JTextArea 	jta_detail	= new JTextArea(8,25);
	JScrollPane	jsp_detail	= new JScrollPane(jta_detail);
	
	JButton		jbtn_file	= new JButton("파일찾기");
	JTextField	jtf_file	= new JTextField(30);
	JLabel		jlb_img		= new JLabel("이미지없음");
	JButton		jbtn_save	= new JButton("저장");
	JButton		jbtn_close	= new JButton("닫기");
	JPanel		jp_center	= new JPanel();
	JPanel		jp_south	= new JPanel();
	JScrollPane	jsp			= new JScrollPane(jp_center);
	BookApp 	ba 			= null;
	BookVO		rbVO		= null;
	JFileChooser	jfc = new JFileChooser();
	Container	cont		= this.getContentPane();
	
	public BookDialog() {
		jbtn_close.addActionListener(this);
		jbtn_save.addActionListener(this);
		jbtn_file.addActionListener(this);
	}
	
	public void initDisplay() {
		//TestArea의 자동 줄바꿈 처리해보기
		jta_detail.setLineWrap(true);
		//속지에 레이아웃이 FlowLayout이었던 것을 null로 초기화 함
		jp_center.setLayout(null);//null->좌표값으로 배치할거야
		jp_south.setLayout(new FlowLayout(FlowLayout.CENTER));
		//화면에 배치할 때 setBounds(x좌표,y좌표,가로길이,세로길이)
		//앞에 두자리가 시작점 좌표값
		//세번째 네번쨰가 가로세로결정
		jlb_title.setBounds(20,20,100,20);
		jtf_title.setBounds(120,20,200,20);
		jlb_author.setBounds(20,80,100,20);
		jtf_author.setBounds(120,80,120,20);
		jlb_publish.setBounds(20,140,100,20);
		jtf_publish.setBounds(120,140,150,20);
		jlb_detail.setBounds(20,200,100,20);
		jsp_detail.setBounds(120,200,330,150);
		jlb_img.setBorder(BorderFactory.createEtchedBorder());
		jlb_img.setBounds(120,380,300,360);
		jbtn_file.setBounds(20,355,90,20);
		jtf_file.setBounds(120,355,300,20);
		jp_center.add(jlb_title);
		jp_center.add(jtf_title);
		jp_center.add(jlb_author);
		jp_center.add(jtf_author);
		jp_center.add(jlb_publish);
		jp_center.add(jtf_publish);
		jp_center.add(jlb_detail);
		jp_center.add(jsp_detail);
		jp_center.add(jlb_img);
		jp_center.add(jbtn_file);
		jp_center.add(jtf_file);
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_close);

		this.add("Center",jsp);
		this.add("South",jp_south);
		this.setTitle(title);
		this.setSize(500,820);
		//부모창에서 선택한 버튼에 따라 화면을 제어한다.-변수사용
		this.setVisible(isView);
	}
	//한 건을 조회한 결과를 담고 있는 bookVO객체 혹은 Map객체까지도 담을 수 있는 set 메소드를 만들기
	//BookMain을 인스턴스화 할때 전역변수에 선언된 bookdialog도 같이 인스턴스화한다.
	//입력과 수정시에는 컬럼값을 수정 가능하도록 하고 
	//조회시에는 불가능하게 하는 메소드를 선언해봐요.
	public void setEditable(boolean isOK) {
		jtf_title.setEditable(isOK);
		jtf_author.setEditable(isOK);
		jtf_publish.setEditable(isOK);
		jta_detail.setEditable(isOK);
	}
	/**********************************
	 * @param isView 다이얼로그창 여부
	 * @param title  다이얼로그창 뷰 여부
	 * @param rMap	 조회결과를 담은 주소번지
	 * @param isOK	입력 컴포넌트 수정 여부
	 **********************************/
	public void set(boolean isView, String title,Map<String, Object> rMap, boolean editable,BookApp ba) {
		setValue(rMap);
		setEditable(editable);
		this.rbVO = rbVO;
		this.isView = isView;
		this.title = title;		
		this.ba = ba;
		initDisplay();
	}
	/*****************************************************
	 * 
	 * @param isView true: 화면에 보여줌, false: 안보여줌
	 * @param title 입력|수정|상세조회
	 * @param rbVO null이면 값 없음, rbVO[new BookVO]이면 값 있음
	 * @param editable true : 수정하게 해줌, false: 수정못하게함
	 * @param ba BookApp의 주소번지 원본을 가지고 있음.
	 *****************************************************/
	public void set(boolean isView, String title, BookVO rbVO, boolean editable, BookApp ba) {
		setValue(rbVO);
		setEditable(editable);
		this.rbVO = rbVO;
		this.isView = isView;
		this.title = title;		
		this.ba = ba;
		initDisplay();		
//		jlb_img.setText(null);

	}
	//조회된 결과를 bookdialog에 초기화 하기
	//새로 입력하는 경우에는 빈 문자열로 초기화 하기
	/***********************************************
	 * BookApp에서 조회된 한 건을 BookDialog에 초기화함.
	 * @param rbVO 조회된 한 건을 BookVO로 받은 경우
	 ***********************************************/
	private void setValue(BookVO rbVO) {
		//입력을 위한 화면 설정 - 모든값을 null로 세팅한다.
		if(rbVO == null) {
			setB_Title("");
			setB_Author("");
			setB_publish("");
			setB_detail("");
			setB_img("");//새로 이미지를 초이스 해야 하니까 비워둔다.

		}
		//상세조회와 수정시는 파라미터로 받은 값으로 셋팅한다.
		else {
			setB_Title(rbVO.getB_title());
			setB_Author(rbVO.getB_author());
			setB_publish(rbVO.getB_publish());
			setB_detail(rbVO.getB_detail());
			setB_img(rbVO.getB_img());

		}		
	}
	/***********************************************
	 * BookApp에서 조회된 한 건을 BookDialog에 초기화함.
	 * @param rmap 조회된 한 건을 Map으로 받은 경우
	 ***********************************************/
	public void setValue(Map<String,Object> rmap) {
		//입력을 위한 화면 설정 - 모든값을 null로 세팅한다.
		if(rmap == null) {
			setB_Title("");
			setB_Author("");
			setB_publish("");
			setB_detail("");
		}
		//상세조회와 수정시는 파라미터로 받은 값으로 셋팅한다.
		//처음 설계시에는 맵으로 했던 걸 어제 bVO로 추가 처리함
		else {
			setB_Title(rmap.get("b_title").toString());
			setB_Author(rmap.get("b_author").toString());
			setB_publish(rmap.get("b_publish").toString());
			setB_detail(rmap.get("b_detail").toString());
		}
	}
	//각 컬럼들의 값들을 설정하거나 읽어오는 getter/setter메소드 입니다.
	public String getB_Title() {	return jtf_title.getText();}
	public void setB_Title(String title) {	jtf_title.setText(title);}
	public String getB_author() {	return jtf_author.getText();}
	public void setB_Author(String author) {	jtf_author.setText(author);}
	public String getB_publish() {	return jtf_publish.getText();}
	public void setB_publish(String publish) {	jtf_publish.setText(publish);}
	public String getB_detail() {	return jta_detail.getText();}
	public void setB_detail(String detail) {	jta_detail.setText(detail);}
	public String getB_img() {	return jlb_img.getText();}
	//JLabel에 도서 이미지 출력하기
	public void setB_img(String img) {
		icon = new ImageIcon(imgPath+img);
		//원본의 이미지 크기 정보를 가져온다.
		Image originImg = icon.getImage();//저장한 이미지의 원래 크기
		//원본의 이미지 크기를 가져와서 300*380 이미지 크기로 재정의
		Image changeImg = originImg.getScaledInstance(300, 380, Image.SCALE_SMOOTH);//이미지 사이즈 지정하기
		//원본의 이미지 아이콘 icon -> cicon으로 변경처리 -> 적용됨.
		//원래 있던 이미지 아이콘을 버리고 새로운 ImageIcon객체를 인스턴스화 하였다.
		ImageIcon cicon = new ImageIcon(changeImg);
		//JLable에 setIcon이라는 메소드의 파라미터로 넘겨서 적용시킨다.
		jlb_img.setIcon(cicon);}

	@Override
	public void actionPerformed(ActionEvent e) {
//		Object obj = e.getSource();
		String command = e.getActionCommand();//이벤트 소스의 라벨을 읽어옴
//		JOptionPane.showMessageDialog(ba, "이벤트 소스 라벨 : "+command);
		//닫기버튼을 누른거니?
		if(jbtn_file==e.getSource()) {
			int ir = jfc.showOpenDialog(this);
			if(ir==JFileChooser.APPROVE_OPTION) {
				//선택한 파일을 File객체에 ㄷ입
				//File 클래스는 .java파일을 .class로 변환해줌. 그래서 꼭 파라미터가 필요함
				//파일명을 클래스급으로 만들어줌
				File myFile = jfc.getSelectedFile();
				//절대경오를 가져온다.
				//C:\workspace_java\dev_java\src<-이것처럼 처음부터 끝까지 다 적어줌
				//<->상대경로는 내가 바라보는 위치를 기준으로 적음(여럿이서 왓다갓다할때 상대경로를 써야함)
				jtf_file.setText(myFile.getAbsolutePath());
				String cfile = myFile.getAbsolutePath();
				icon = new ImageIcon(cfile);
				//원본의 이미지 크기 정보를 가져온다.
				Image originImg = icon.getImage();//저장한 이미지의 원래 크기
				//원본의 이미지 크기를 가져와서 300*380 이미지 크기로 재정의
				Image changeImg = originImg.getScaledInstance(300, 380, Image.SCALE_SMOOTH);//이미지 사이즈 지정하기
				//원본의 이미지 아이콘 icon -> cicon으로 변경처리 -> 적용됨.
				//원래 있던 이미지 아이콘을 버리고 새로운 ImageIcon객체를 인스턴스화 하였다.
				ImageIcon cicon = new ImageIcon(changeImg);
				//JLable에 setIcon이라는 메소드의 파라미터로 넘겨서 적용시킨다.
				jlb_img.setIcon(cicon);
				cont.revalidate();
				
			}
		}
		else if("저장".equals(command)) {
			//insert here - 입력인지 수정인지 어떻게 구분하지?
			int result = 0;
			//rbVO는 BookVO타입으로 BookApp에서 이벤트 발생 시 set메소드의
			//4번째 파라미터로 넘어온 값이다.
			//이 주소번지가 null이면 조회를 하지 않았다는 뜻이고
			//null이 아니면 조회를 하였다는 의미
			if(rbVO!=null) {
				BookVO pbVO = new BookVO();
//				pbVO.setCommand("update");
				pbVO.setB_no(rbVO.getB_no());
				pbVO.setB_title(getB_Title());
				pbVO.setB_author(getB_author());
				pbVO.setB_publish(getB_publish());
				pbVO.setB_detail(getB_detail());
				result = ba.bDao.bookUpdate(pbVO);
				JOptionPane.showMessageDialog(ba, result);
			}
			else {
				result = 0;
				BookVO pbVO = new BookVO();
				pbVO.setB_title(getB_Title());
				pbVO.setB_author(getB_author());
				pbVO.setB_publish(getB_publish());
				pbVO.setB_detail(getB_detail());
				result = ba.bDao.bookInsert(pbVO);
				JOptionPane.showMessageDialog(ba, result);
			}
			//인서트가 되어야하고
			//main으로 연결되어야한다.
			ba.refreshData();
			this.dispose();
		}
		//저장버튼을 누른거니?
		else if("닫기".equals(command)) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		BookDialog bd = new BookDialog();
		bd.set(true,"입력",new BookVO(),true,null);
		bd.initDisplay();
	}


}
