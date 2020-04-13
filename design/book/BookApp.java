package design.book;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import com.util.DBConnectionMgr;

public class BookApp extends JFrame implements ActionListener{
	//선언부

	//DB커넥션 연결하기
	DBConnectionMgr		dbMgr 		= DBConnectionMgr.getInstance();
	//이미지 경로추가
	String 				imgPath 	= "src\\design\\book\\";
	URL 				bookURL 	= getClass().getResource("book.png");
	ImageIcon			bicon 		= new ImageIcon(bookURL);
	//메뉴바 추가하기
	JMenuBar			jmb_book 	= new JMenuBar();
	JMenu				jm_file 	= new JMenu("File");
	JMenuItem			jmi_db		= new JMenuItem("DB연결");
	JMenuItem			jmi_open	= new JMenuItem("Open File");
	JSeparator			js_file		= new JSeparator();
	JMenuItem			jmi_exit	= new JMenuItem("Exit");
	JMenu				jm_edit		= new JMenu("Edit");
	JMenuItem			jmi_all 	= new JMenuItem("전체조회");
	JMenuItem			jmi_sel 	= new JMenuItem("상세보기",new ImageIcon(imgPath+"detail.gif"));
	JMenuItem			jmi_ins 	= new JMenuItem("입력",new ImageIcon(imgPath+"new.gif"));
	JMenuItem			jmi_upd 	= new JMenuItem("수정",new ImageIcon(imgPath+"update.gif"));
	JMenuItem			jmi_del 	= new JMenuItem("삭제",new ImageIcon(imgPath+"delete.gif"));
	static BookApp 		ba 			= null;
	//파라미터가 없는 생성자는 디폴트로 지원해주지만 있는 경우는 예측불가이므로 지원 불가함.
	//이때 파라미터로 넘어간 boolean,string은 값이 이미 결정된 상태 이므로 아무리 버튼을 바꾸어 눌렀어도
	//title의 값은 변하지 않는것이다.
	//생성자가 호출되는 시점과 이벤트가 감지되는 시점 사이에 차이가 발생하였다.
	BookDialog 			bd 			= new BookDialog();
	//jp_north속지는 JFrame의 북쪽에 배치
	JPanel				jp_north	= new JPanel();
	JPanel				jp_south	= new JPanel();
	//아래 버튼은 jp_north속지에 차례대로 배치 - 배치는 왼쪽부터
	String				cols[]		= {"도서번호","도서명","저자","출판사"};
	String				data[][]	= new String[0][4];
	JToolBar			jtbar		= new JToolBar();
	JButton				jbtn_db		= new JButton("DB연결");
	JButton				jbtn_all	= new JButton("전체조회");
	JButton				jbtn_ins	= new JButton("입력");
	JButton				jbtn_upd	= new JButton("수정");
	JButton				jbtn_del	= new JButton("삭제");
	JButton				jbtn_sel	= new JButton("상세보기");
	DefaultTableModel	dtm_book	= new DefaultTableModel(data, cols);
	JTable				jtb_book	= new JTable(dtm_book);
	JScrollPane			jsp_book	= new JScrollPane(jtb_book);
	BookController		bCtrl		= new BookController(this);
	BooKDao 			bDao 		= new BooKDao();
	JLabel				jlb_time	= new JLabel("현재시간",JLabel.CENTER);
	TimeClient tc = null;
	public BookApp() {
	}
	//이벤트 소스와 이벤트 핸들러 클래스 연결하기
	public void eventMapping() {
		//db연결 메뉴 아이템 이벤트 처리
		jmi_db.addActionListener(new ActionListener() {//메소드 안에서 인터페이스를 인스턴스화하니까 구현체 메소드를 바로 생성
			
			@Override
			public void actionPerformed(ActionEvent ae) {//actionperformed(재정의한메소드)안에서 다른 메소드를 호출할수있다.
				dbActionPerformed(ae);
			}
		});
		//db연결 버튼 이벤트 처리
		jbtn_db.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				dbActionPerformed(ae);
			}
		});
		jmi_all.addActionListener(this);
		jbtn_sel.addActionListener(this);
		jbtn_all.addActionListener(this);
		jbtn_ins.addActionListener(this);
		jbtn_upd.addActionListener(this);
		jbtn_del.addActionListener(this);
		jmi_del.addActionListener(this);
		jmi_exit.addActionListener(this);
		jmi_ins.addActionListener(this);
		jmi_open.addActionListener(this);
		jmi_sel.addActionListener(this);
		jmi_upd.addActionListener(this);
	}
	protected void dbActionPerformed(ActionEvent ae) {
		System.out.println("db연결 테스트");
		Connection con = null;
		con = dbMgr.getConnection();
		System.out.println(con.toString());
		PreparedStatement pstmt = null;
		ResultSet	rs = null;
	}
	//화면그리기
	public void initDisplay() {
		jm_file.setMnemonic('F');//alt+f
		jm_edit.setMnemonic('E');//alt+e
		//실제로 타임서버로부터 시간정보를 듣기는 timeclient에서 진행되지만 
		//생성자의 파라미터를 통해서 BookApp jlb_time 원본의 주소번지를
		//넘겼으므로 TimeClient에서는 원본에 직접 써주면 화면에 보임.
		tc = new TimeClient(jlb_time);
		tc.start();
		//아래코드가 JFrame의 자원을 회수함.
		//부모자원이 회수될 때 JDialog도 같이 회수됨.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp_north.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setJMenuBar(jmb_book);
		jm_file.add(jmi_db);
		jm_file.add(jmi_open);
		jm_file.add(js_file);
		jm_file.add(jmi_exit);
		jm_edit.add(jmi_all);
		jm_edit.add(jmi_sel);
		jm_edit.add(jmi_ins);
		jm_edit.add(jmi_upd);
		jm_edit.add(jmi_del);
		jmb_book.add(jm_file);
		jmb_book.add(jm_edit);
		this.add("North",jtbar);
		this.add("South",jp_south);
		this.add("Center",jsp_book);
		jtbar.add(jbtn_db);
		jtbar.add(jbtn_all);
		jtbar.add(jbtn_sel);
		jtbar.add(jbtn_ins);
		jtbar.add(jbtn_upd);
		jtbar.add(jbtn_del);
		jp_south.add(jlb_time);
		jbtn_sel.setToolTipText("상세조회");//커서를 올리면 ""이 나옴
		jbtn_all.setToolTipText("전체조회");//커서를 올리면 ""이 나옴
		jbtn_db.setToolTipText("DB연결");//커서를 올리면 ""이 나옴
		jbtn_del.setToolTipText("삭제");//커서를 올리면 ""이 나옴
		jbtn_ins.setToolTipText("입력");//커서를 올리면 ""이 나옴
		jbtn_upd.setToolTipText("수정");//커서를 올리면 ""이 나옴
		this.setTitle("도서관리시스템");
		this.setSize(700,500);
		this.setIconImage(bicon.getImage());
		this.setVisible(true);
		jbtn_sel.setIcon(new ImageIcon(imgPath+"detail.gif"));
		jbtn_ins.setIcon(new ImageIcon(imgPath+"new.gif"));
		jbtn_upd.setIcon(new ImageIcon(imgPath+"update.gif"));
		jbtn_del.setIcon(new ImageIcon(imgPath+"delete.gif"));


	}
	public void refreshData() {
		System.out.println("refreshData 호출성공");
		List<BookVO> bookList = null;
		BookVO pbVO = new BookVO();//MVC패턴
		pbVO.setCommand("all");//MVC패턴-인스턴스화를 해야  command변수에 all을 넣어줌
		bookList = bCtrl.sendALL(pbVO);//MVC패턴-그래야 sendall메소드에서 값을 받음
		//기존에 조회된 결과를 출력한 화면은 삭제처리한다.
		while(dtm_book.getRowCount()>0) {
			dtm_book.removeRow(0);//계속 로우만큼 반복하면서 첫번째 로우 즉 0번은 계속 지워준다.
		}//삭제한 후 다시 출력하기
		for(int i = 0;i<bookList.size();i++) {
			BookVO bVO = bookList.get(i);
			Vector<Object> v = new Vector<>();
			v.add(bVO.getB_no());
			v.add(bVO.getB_title());
			v.add(bVO.getB_author());
			v.add(bVO.getB_publish());
			//JTable에 추가하는 것이 아니다.-JTable은 양식일 뿐이고 
			//실제 데이터를 갖는 클래스는 DefaultTableModel이다.-DataSet지원함.
			//한개 로우는 Vector에 담고 그 벡터를 for문 안에서 반복 추가해줌.
			dtm_book.addRow(v);
		}
	}////////////end of refreshData
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);

		TimeServer ts = new TimeServer();
		ts.initDisplay();//화면을 그리고 난 뒤 스레드 대기를 타도록 해야함.
		Thread th = new Thread(ts);
		th.start();//스레드의 run메소드를 호출하는 메소드//쓰레드를 동작시켜주는 start
		ba = new BookApp();
		ba.initDisplay();//화면처리부분
		ba.eventMapping();//이벤트 연결 - 익명클래스 처리
	}

	//JButton에 대한 이벤트를 지원하는 인터페이스가 ActionListner임.
	//클래스 뒤에 implements를 할 것
	//ActionListner에 정의된 추상메소드를 재정의할것.
	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 감지된 클래스의 주소번지 받기
		Object obj = e.getSource();
		if(obj==jbtn_sel || obj==jmi_sel) {
//			bd.title = jbtn_.getLabel();
//			bd.isView = true;
//			bd.title = jbtn_sel.getText();
			System.out.println("상세조회 호출 성공");
			Map<String, Object> rMap = null;
			int indexs[] = jtb_book.getSelectedRows();
			if(indexs.length==0) {
				JOptionPane.showMessageDialog(this, "상세조회 할 로우를 선택하세요.");
				return;
			}
			else {
				int b_no = Integer.parseInt(dtm_book.getValueAt(indexs[0], 0).toString());
				System.out.println("b_no:"+b_no);
				BookVO pbVO = new BookVO();
				pbVO.setCommand("detail");
				pbVO.setB_no(b_no);
				System.out.println("command: "+pbVO.getCommand());
				System.out.println("B_no: "+pbVO.getB_no());
				BookVO rbVO = bCtrl.send(pbVO);
				bd.set(true,jbtn_sel.getText(),rbVO,false,this);
			}
//			bd.jtf_title.setText("");
//			bd.set(true,jbtn_sel.getText(),rMap,false,null);
			//initDisplay를 호출 한 이유는 setTitle("입력")과 setvisible(true)때문이었다.
			//그런 그 둘을 setmethod로 이전하였다. 그러므로 initdisplay를 불러오는것도 없어도 됨
//			bd.initDisplay();
		}
		//입력버튼을 누른거니?
		else if(obj==jbtn_ins || obj==jmi_ins ) {
//			bd.isView = true;
//			bd.title = jbtn_ins.getLabel();
			//위의 두줄을 메소드를 만들어서 파라미터 두개로 받으면 한줄로 해결할수있음.
//			bd.jtf_title.setText("");
//			Map<String, Object> rMap = new HashMap<>();
//			bd.set(true,jbtn_ins.getText(),rMap,true,ba);
//			bd.initDisplay();
			BookVO pbVO = null;
			bd.set(true,jbtn_ins.getText(),pbVO,true,this);
		}
		else if(obj==jbtn_upd || obj==jmi_upd) {//수정시에는 먼저 기본정보를 조회하고 화면이동
			//select처리한 후 한개 로우를 받아서 Map에 저장
//			bd.isView = true;
//			bd.title = jbtn_upd.getLabel();
//			Map<String, Object> rMap = null;
//			rMap = new HashMap<String, Object>();
//			rMap.put("b_title", "자바의 정석");
//			bd.set(true,jbtn_upd.getText(),rMap,true,ba);
//			bd.initDisplay();
			
			BookVO rbVO = new BookVO();
			BookVO pbVO = new BookVO();
			//파라미터로 도서 번호를 넘거야 한다.
			int index = -1;
			index = jtb_book.getSelectedRow();//선택한 로우 하나
			
			if(index>=0) {//선택한 로우값이 있다.
				pbVO.setCommand("detail");
				int b_no = Integer.parseInt(dtm_book.getValueAt(index, 0).toString());
				pbVO.setB_no(b_no);
				rbVO = bCtrl.send(pbVO); 
				
			}else {//선택한 로우가 없다.
				JOptionPane.showMessageDialog(this, "수정 할 데이터를 선택하세요.");
				return;
			}
			bd.set(true,jbtn_upd.getText(),rbVO,true,this);

		}
		else if(obj==jbtn_all || obj==jmi_all) {
			System.out.println("전체 조회 호출 성공");
			refreshData();
//			List<Map<String, Object>> li = new BooKDao().bookList(pbVO);
//			for(int i =0; i<li.size();i++) {
//				Vector v = new Vector();
//				v.add(0,li.get(i).get("b_no"));
//				v.add(1,li.get(i).get("b_title"));
//				v.add(2,li.get(i).get("b_author"));
//				v.add(3,li.get(i).get("b_publish"));
//				v.add(4,li.get(i).get("b_detail"));
//				dtm_book.addRow(v);
//			}
			
		}
		else if(obj==jmi_db) {
			new BooKDao();
		}
		else if(obj==jmi_exit) {
			System.exit(0);
		}
		else if(obj==jbtn_del) {
			int indexs[] = jtb_book.getSelectedRows();
			if(indexs.length==0) {
				JOptionPane.showMessageDialog(this, "삭제할 로우를 선택하세요");
				return;
			}else {
				List<Integer> bnos = new ArrayList<>();
				for(int i=0;i<indexs.length;i++) {
					//선택된 로우인지 체크
					if(jtb_book.isRowSelected(indexs[i])) {
						int b_no = Integer.parseInt(dtm_book.getValueAt(indexs[i], 0).toString());
						bnos.add(b_no);
					}
				}
				BookVO pbVO = new BookVO();
				pbVO.setCommand("delete");//command에 delete를 넘기는게 중요
				pbVO.setBnos(bnos);//List에 담았다.
				int result = 0;
				BookVO rbVO = new BookVO();
				rbVO= bCtrl.send(pbVO);
				result = rbVO.getResult();
				if(result>0) {
					JOptionPane.showMessageDialog(this, "삭제 처리 되었습니다.");
					refreshData();
				}else {
					JOptionPane.showMessageDialog(this, "실패하였습니다.");
					refreshData();
				}
			}
			

//			result = ba.bDao.bookDelete(rbVO);
//			JOptionPane.showMessageDialog(ba, result);
			
		}
	}
}
