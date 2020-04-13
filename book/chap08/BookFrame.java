package book.chap08;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookFrame extends JFrame{
	
	String cols[] = {"도서명", "저자"};
	String data[][] = new String[0][2];
	DefaultTableModel	dtm_book = new DefaultTableModel(data, cols);
	JTable	jtb_book 	= new JTable(dtm_book);
	JScrollPane	jsp_book	= new JScrollPane(jtb_book);
	
	public BookFrame() {
		ArrayList<Book1> library = new ArrayList<Book1>();
		Book1	b1	= new Book1();
		b1.b_title = "태백산맥";
		b1.b_author = "조정래";
		library.add(b1);
		System.out.println(library.get(0));
		b1 = null;
		b1	= new Book1();
		b1.b_title = "데미안";
		b1.b_author = "헤르만헤세";
		library.add(b1);
		System.out.println(library.size());
		Vector<String> v = new Vector<>();
		v.add(library.get(0).b_title);
		v.add(library.get(0).b_author);
		dtm_book.addRow(v);
		v = new Vector<>();//공간이 2개라서 초기화를 안하고 바로 넣어버리면 4개의 정보가 나와서 초기화를 해서 두개씩 담아야함
		v.add(library.get(1).b_title);
		v.add(library.get(1).b_author);
		dtm_book.addRow(v);
		this.add("Center",jsp_book);
		this.setSize(600,300);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new BookFrame();
	}

}
