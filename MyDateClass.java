import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyDateClass {

	private static Calendar parseTimeString(String dateStr, int timeOffset) throws ParseException {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date date = dateFormat.parse(dateStr);
		int y = currentDate.get(Calendar.YEAR);
		int m = currentDate.get(Calendar.MONTH);
		int d = currentDate.get(Calendar.DATE);

		Calendar cal = Calendar.getInstance();

		cal.setTime(date); // This Will Set Time To EPOCH Time + Parsed Time...
		cal.set(y, m, d);
	    cal.add(Calendar.HOUR, timeOffset);
	    return cal;
	}

	public static String convertMilliSecondsToHMmSs(long milliseconds) {
		long seconds = milliseconds / 1000;
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        return String.format("%d:%02d:%02d", h,m,s);
}


	public static void main(String args[]) throws ParseException {

		String dateStrStart = "10:00 PM";
		String dateStrEnd = "10:44 PM";
		int offset = -2;

		Calendar cdate = Calendar.getInstance();

		Boolean isWithInTwoHours = !cdate.before(parseTimeString(dateStrStart, offset)) && !cdate.after(parseTimeString(dateStrStart, 0));
		Boolean isEnded = cdate.after(parseTimeString(dateStrEnd, 0));


		if (isWithInTwoHours) {
			long startTime = (parseTimeString(dateStrStart, 0)).getTimeInMillis();
			long estimatedTime = startTime - System.currentTimeMillis();
			System.out.println(String.format("Left %s To Open", convertMilliSecondsToHMmSs(estimatedTime)));
		} else if (!isEnded) {
			System.out.println(String.format("Open Now"));
		} else {
			System.out.println(String.format("Closed"));
		}
	
	}
}