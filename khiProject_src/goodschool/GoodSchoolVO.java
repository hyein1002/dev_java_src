package goodschool;

public class GoodSchoolVO {
	  String 	name      	= null; 
	  int 		id_number 	= 0;
	  String 	major     	= null; 
	  String 	re_subject	= null; 
	  int 		math		= 0;
	  int 		kor 		= 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId_number() {
		return id_number;
	}
	public void setId_number(int id_number) {
		this.id_number = id_number;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getRe_subject() {
		return re_subject;
	}
	public void setRe_subject(String re_subject) {
		this.re_subject = re_subject;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}

}
