
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
        String todayStringArr[] = dtf.format(localDate).split("-");
        Calendar cals = parseTimestamp("01-01-1970");
        //compatibilty layer 
        try {
            cals = parseTimestamp(todayString);
        } catch (Exception e) {
            System.err.println("y u du dis");
            return;
        }

        Date pDate = new Date(Integer.parseInt(todayStringArr[2]), Integer.parseInt(todayStringArr[1]),Integer.parseInt(todayStringArr[0])) ;
        pDate.printDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        System.out.println("Calendar : " + sdf.format(cals.getTime()));
        
    }

    public synchronized static Calendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        sdf.setLenient(false);
        java.util.Date d = sdf.parse(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }
}
