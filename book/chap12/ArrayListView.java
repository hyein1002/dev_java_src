package book.chap12;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArrayListView extends JFrame {
	String cols[] = {"날짜","판매수량","매출액"};
	String data[][] = new String[0][3];
	DefaultTableModel	dtm = new DefaultTableModel(data, cols);
	JTable	jt = new JTable(dtm);
	JScrollPane	js = new JScrollPane(jt);
	
	public ArrayListView() {
		this.add("Center",js);
		this.setVisible(true);
		this.setSize(400,500);
		dataSet();
	}
	public void dataSet() {
		ArrayListTest at = new ArrayListTest();
		at.sql();
		Vector v = null;
		for(int i = 0; i<at.avos.length;i++) {
			v = new Vector();
			v.add(at.avos[i].getDate());
			v.add(at.avos[i].getQty());
			v.add(at.avos[i].getPrice());
			dtm.addRow(v);
		}
	}

	public static void main(String[] args) {
		new ArrayListView();
	}

}
