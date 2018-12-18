import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateDiff {
public static void main(String[] args) throws ParseException {
	System.out.println("akp");
	String start = "2018-09-24T08:45:51Z";
	String end ="2018-09-23T08:45:51Z";
/*System.out.println(start.replace('T', ' '));
System.out.println(end.replace('T', ' '));
start= start.replace('T', ' ');
2001-07-04T12:08:56.235-0700            yyyy-MM-dd'T'HH:mm:ss.SSSZ
2001-07-04T12:08:56.235-07:00           yyyy-MM-dd'T'HH:mm:ss.SSSXXX
end= end.replace('T', ' ');*/
	

//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
Date firstDate = sdf.parse(start);
Date secondDate = sdf.parse(end);
long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
System.out.println(diffInMillies);
System.out.println(diff);
	
	/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
	LocalDate date = LocalDate.parse(start, formatter);
	System.out.println(date); // 2010-01-02
	//date.minus(amountToSubtract)
*/}
}
