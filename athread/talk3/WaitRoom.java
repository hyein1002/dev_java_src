package athread.talk3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class WaitRoom extends JPanel 
implements MouseListener, ActionListener {
	//단톡명 담기
	String roomTitle = null;
	TalkClientVer2 tc = null;
	JPanel jp_first = new JPanel();//대기모드 처리
	String cols[] = {"대화명","위치"};
	String data[][] = new String[0][2];
	DefaultTableModel dtm_wait = new DefaultTableModel(data,cols);
	JTable jtb_wait = new JTable(dtm_wait);
	JScrollPane jsp_wait = new JScrollPane(jtb_wait);
	JTableHeader jth_wait = jtb_wait.getTableHeader();
	JPanel jp_second = new JPanel();//단톡정보 처리
	String cols2[] = {"단톡명","현재인원"};
	String data2[][] = new String[0][2];
	DefaultTableModel dtm_room = new DefaultTableModel(data2,cols2);
	JTable jtb_room = new JTable(dtm_room);
	JScrollPane jsp_room = new JScrollPane(jtb_room);
	JTableHeader jth_room = jtb_room.getTableHeader();
	JPanel jp_second_south = new JPanel();
	JButton jbtn_create = new JButton("단톡만들기");
	JButton jbtn_in 	= new JButton("입장하기");
	JButton jbtn_out 	= new JButton("나가기");
	JButton jbtn_exit 	= new JButton("종료");
	JLabel jlb_banner = new JLabel();
	public WaitRoom(TalkClientVer2 tc) {
		this.tc = tc;
		initDisplay();
	}

	public void initDisplay() {
		jbtn_create.addActionListener(this);
		jbtn_in.addActionListener(this);
		jtb_room.addMouseListener(this);
		this.setLayout(new GridLayout(1,2));
		jth_wait.setBackground(Color.RED);
		jth_wait.setForeground(Color.WHITE);
		jtb_wait.setGridColor(Color.RED);
		jtb_wait.setSelectionBackground(Color.yellow);
		//테이블 헤더 위치 변경 꺼두기
		jth_wait.setReorderingAllowed(false);
		jp_first.setBorder(BorderFactory.createEtchedBorder());
		jp_first.setBackground(Color.pink);
		jp_first.setLayout(new BorderLayout());
		jp_first.add(jsp_wait);
		jp_second.setBackground(Color.cyan);
		jp_second.setLayout(new BorderLayout());
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_in);
		jp_second_south.add(jbtn_out);
		jp_second_south.add(jbtn_exit);
		jp_second.add("Center",jsp_room);
		jp_second.add("South",jp_second_south);
		jlb_banner.setBorder(BorderFactory.createEtchedBorder());
		jlb_banner.setIcon(new ImageIcon("src\\athread\\room\\etest.gif"));
		jp_second.add("North",jlb_banner);
		this.add(jp_first);
		this.add(jp_second);
		this.setBackground(Color.green);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == jtb_room) {
			JOptionPane.showMessageDialog(tc, "mousePressed");
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if("입장하기".equals(command)) {
			int index[] = jtb_room.getSelectedRows();
			if(index.length == 0) {
				JOptionPane.showMessageDialog(this, "단톡방을 선택하세요.");
				return;
			}
			else {
				try {
					for(int i=0;i<jtb_room.getRowCount();i++) {
						if(jtb_room.isRowSelected(i)) {
							String roomTitle =(String)dtm_room.getValueAt(i, 0);
							tc.oos.writeObject(Protocol.ROOM_IN
									+Protocol.SEPERATOR+roomTitle
									+Protocol.SEPERATOR+tc.nickName
									);
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}/////////////////////end of try
			}/////////////////////////end of else
		}
		else if("단톡만들기".equals(command)) {
			roomTitle = JOptionPane.showInputDialog("단톡이름을 주세요.");
			if(roomTitle !=null && roomTitle.trim().length()>0) {
				try {
					tc.oos.writeObject(Protocol.ROOM_CREATE
					+Protocol.SEPERATOR+roomTitle
					+Protocol.SEPERATOR+0);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		else if("입장하기".equals(command)) {
			tc.jtp.setSelectedIndex(1);
		}
		
	}
}








