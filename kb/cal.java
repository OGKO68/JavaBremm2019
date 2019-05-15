import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * cal
 */
public class cal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String iString = sc.nextLine();
        sc.close();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
        Date date2 = new Date();
        try {
            date2 = format.parse(iString);            
        } catch ( ParseException e) {
            //ignore
        }
        SimpleDateFormat format2 = new SimpleDateFormat("dd-mm-yy");
        String datePrint = format2.format(date2);
        System.out.println(datePrint);

    }
}