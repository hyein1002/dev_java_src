package oracle.jdbc2;

public class ZipCode {

	private String zipCode;
	private int dong;
	private double pung;
	public String getZipCode() {//<-읽기
		return zipCode;
	}
	public void setZipCode(String zipCode) {//<-쓰기
		this.zipCode = zipCode;
	}
	public int getDong() {
		return dong;
	}
	public void setDong(int dong) {
		this.dong = dong;
	}
	public double getPung() {
		return pung;
	}
	public void setPung(double pung) {
		this.pung = pung;
	}
	
}
