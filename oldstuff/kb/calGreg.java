
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class calGreg {

  public static Calendar parseTimestamp(String timestamp) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.GERMAN);
    sdf.setLenient(false);
    Date d = sdf.parse(timestamp);
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    return cal;
  }

  public static Calendar test1() throws ParseException {
    String timestamp = "28-Feb-2009";
    System.out.println("Timestamp : " + timestamp);
    return parseTimestamp(timestamp);
  }

  public static Calendar test2() throws ParseException{
    Calendar date = Calendar.getInstance();
    try {
        String timestamp = "31-Feb-2009";
        System.out.println("Timestamp : " + timestamp);
        date = parseTimestamp(timestamp);
    } catch (ParseException e) {
    return test1();
    }
    return date;
  }

  public static void main(String a[]) throws ParseException {
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");

    Calendar date = test2();
    
    System.out.println("Calendar : " + sdf.format(date.getTime()));
  }

}