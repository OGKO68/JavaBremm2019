
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import helpful.*;

/**
 * yeet
 */
public class yeet {

    public synchronized static void main(String[] args) throws ParseException {
        //comment
        System.out.println("---------------------------------------------------------------");
        System.out.println("|This Program uses LocalDate so the date might be one day off.|");
        System.out.println("---------------------------------------------------------------");

        //use date today function from old
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.now();
        String todayString = dtf.format(localDate);
        
        //compatibilty layer 
        Calendar cals = parseTimestamp(todayString);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        System.out.println("Calendar : " + sdf.format(cals.getTime()));
        
    }

    public synchronized static Calendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        sdf.setLenient(false);
        Date d = sdf.parse(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }
}
