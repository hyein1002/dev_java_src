package aTest0404.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInfo {
	public long getDifferenceDates(String sDate, String eDate, String pattern, char delim) {
		long diff = 0L;
		try {
			SimpleDateFormat sd = new SimpleDateFormat(pattern);
			Date sdate = sd.parse(sDate);
			Date edate = sd.parse(eDate);
			long sTime = sdate.getTime();
			long eTime = edate.getTime();
			diff = Math.abs(sTime-eTime);
			diff = (diff/(60*60*1000));
			//System.out.println("diff:"+diff/(60*60*1000));
		} catch (ParseException e) {
			// TODO: handle exception
		}
		
		return diff;
	}
	public static void main(String[] args) {
		DateInfo di = new DateInfo();
		di.getDifferenceDates("2020-04-07", "2020-04-05", "yyyy-MM-dd", 'H');
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sd.parse("2020-04-06");
			System.out.println("d:"+d);
			String s = sd.format(Calendar.getInstance().getTime());
			System.out.println("s:"+s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
