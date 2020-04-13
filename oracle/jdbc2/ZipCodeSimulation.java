package oracle.jdbc2;

public class ZipCodeSimulation {

	void zipArray() {
		ZipCode zc = new ZipCode();
		ZipCode zca[] = new ZipCode[3];
		zc.setZipCode("0123");
		zc.setDong(1);
		zc.setPung(1.1);
		zca[0]=zc;
		
		zc = new ZipCode();
		zc.setZipCode("0456");
		zc.setDong(2);
		zc.setPung(2.2);
		zca[1]=zc;		
		
		zc = new ZipCode();
		zc.setZipCode("0789");
		zc.setDong(3);
		zc.setPung(3.3);
		zca[2]=zc;	
		
		PrintZipCode(zca);
	}
	
	void PrintZipCode(ZipCode zca[] ) {
		for(ZipCode zcaa:zca) {
			System.out.println(zcaa.getDong());
			System.out.println(zcaa.getPung());
			System.out.println(zcaa.getZipCode());
		}
	}
	
	
	public static void main(String[] args) {
		ZipCodeSimulation zzz = new ZipCodeSimulation();
		zzz.zipArray();
		
	}

}
