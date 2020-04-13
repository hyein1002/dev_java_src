package goodschool;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javafx.scene.text.Font;

public class GoodSchoolView extends JFrame{
	JLabel jlb = new JLabel("국어수강생학점");
	String cols[] = {"이름","학번","중점과목","점수"};
	String data[][] = new String[0][4];
	DefaultTableModel dtm = new DefaultTableModel(data,cols);
	JTable jta = new JTable(dtm);
	JScrollPane jsp = new JScrollPane(jta);
	public GoodSchoolView() {
		
	}
	public void initDisplay() {
		this.add("North",jlb);
		this.add(jsp);
		this.setTitle("과목 별 결과 리포트");
		this.setSize(700,500);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		GoodSchoolView gsv = new GoodSchoolView();
		gsv.initDisplay();
	}

}
