package oracle.jdbc2;
/*
 * private(나만씀)<protected<friendly[코딩하지않음]<public(누구나씀)
 */
public class ZipCodeVO {
	//인스턴스화를 해야만 사용할수 있으니까
	//인스턴스화가 null로 초기화되면 그 값은 사라지므로 변조할 수 없을꺼야
	int i;//friendly의 상태, 타입앞에 아무것도 안적은것이 프랜들리
	private int    uid_no   = 0;// 
	private int    zipcode  = 0;//
	private String zdo      ="";//
	private String sigu     ="";//private으로 둔 이유는 변조를 방지하기위해서
	private String dong     ="";//
	private String ri       ="";//
	private String bungi    ="";//
	private String aptname  ="";//
	private String upd_date ="";//
	private String address  ="";//
	// VO패턴
	//<-갈무리-우클릭-소스-제너레이트 게터세터-insertion point는 라스트멤버-셀렉트올을 하면 게터세터변수가 나옴

	//getter메소드 - 읽기
	public int getUid_no() {
		return uid_no;
	}
	//setter메소드(리턴타입이 void, 파라미터가 있음) - 쓰기, 저장, 기억, 초기화 - 메소드 이름은 카멜표기법(첫단어 소문자 두번째단어 대문자
	public void setUid_no(int uid_no) {
		this.uid_no = uid_no;//this는 위의 프라이빗에 있는 변수임(전역변수), this가 없으면 파라미터의 변수
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getZdo() {
		return zdo;
	}
	public void setZdo(String zdo) {
		this.zdo = zdo;
	}
	public String getSigu() {
		return sigu;
	}
	public void setSigu(String sigu) {
		this.sigu = sigu;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getBungi() {
		return bungi;
	}
	public void setBungi(String bungi) {
		this.bungi = bungi;
	}
	public String getAptname() {
		return aptname;
	}
	public void setAptname(String aptname) {
		this.aptname = aptname;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}