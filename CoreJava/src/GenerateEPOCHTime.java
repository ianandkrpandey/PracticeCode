import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GenerateEPOCHTime {
	public static void main(String[] args) {
		
		Date currentDate = Calendar.getInstance().getTime();
		
		//currentDate.setTime(new Date()); // Now use today date.
		
		//currentDate.add(Calendar.DATE, 5);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

		String currentTime = dateFormat.format(currentDate);
		
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, 5);
		String timeafterFiveDays = dateFormat.format(c.getTime());
		//System.out.println("DateAfter 5 Days -- "+dateFormat.format(c.getTime()));

		try {

			// parse() parses text from the beginning of the given string to produce a date.
			Date creationDate = dateFormat.parse(currentTime);
			
			Date expirationDate = dateFormat.parse(timeafterFiveDays);
			
			

			// getTime() returns the number of milliseconds since January 1, 1970, 00:00:00
			// GMT represented by this Date object.
			long currentepochTime = creationDate.getTime();
			long epoctomeafterfivedays = expirationDate.getTime();
			
			System.out.println("Current Epoc Time----"+currentepochTime);
			System.out.println("Epoc time after five days"+epoctomeafterfivedays);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
