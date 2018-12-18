import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {


	public static long creationTime;
	public static long expirationTime;

	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

		Calendar currentCalender = Calendar.getInstance();
		currentCalender.add(Calendar.DATE, 5);
		String timeafterFiveDays = dateFormat.format(currentCalender.getTime());

		try {

			Date expirationDate = dateFormat.parse(timeafterFiveDays);
			creationTime = System.currentTimeMillis();
			expirationTime = expirationDate.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}


public static void main(String[] args) {
	System.out.println(creationTime);
	System.out.println(expirationTime);
	System.out.println(expirationTime-creationTime);
	
	
}
}
