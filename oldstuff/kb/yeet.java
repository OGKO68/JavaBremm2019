
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * yeet
 */
public class yeet {

    public static void main(String[] args) throws ParseException {
        //comment
        System.out.println("---------------------------------------------------------------");
        System.out.println("|This Program uses LocalDate so the date might be one day off.|");
        System.out.println("---------------------------------------------------------------");

        //use date today function from old
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate localDate = LocalDate.now();
        String todayString = dtf.format(localDate);
        
        //compatibilty layer 
        Calendar cals = parseTimestamp(todayString);
    }

    public static Calendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.GERMAN);
        sdf.setLenient(false);
        Date d = sdf.parse(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    /**yeetable */
        /*
        String[] todayString = dtf.format(localDate).split("/");
        String todayString2 = todayString[2] + "-" +  intToMonth( Integer.parseInt( todayString[1] ) ) + "-" + todayString[0];
        System.out.println("-" + todayString2 +"-");
        */
    /*
    public static String intToMonth( int i ) {
        switch (i) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
        }
        return "Rip";
    }
    */
}


