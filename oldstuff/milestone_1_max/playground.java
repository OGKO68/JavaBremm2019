/**
 * playground
 */
import storage.*;
import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class playground {

    public static void main(String[] args) {
        Date today = getToday();
        Project pro = new Project(1, "test", getToday(), getToday());
        pro.printProject();
        Scanner sc = new Scanner(System.in);
        while(true) System.out.println( today.isEarlierDate(scanDate(sc, "yeeet")) );
    }

    public static Date getToday() {
        //credits https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate)); //YYYY/MM/DD
        
        String[] todayString = dtf.format(localDate).split("/");
        Date todayDate = new Date(Integer.parseInt(todayString[0]), Integer.parseInt(todayString[1]), Integer.parseInt(todayString[2]) );
        //todayDate.printDate();
        return todayDate;
    }


    public static Date scanDate( Scanner sc, String dateType ) throws ArrayIndexOutOfBoundsException {
        System.out.println("so whats the " + dateType + " date");
        System.out.println("please enter date like (YYYY/MM/DD)");
        String[] inDate = sc.nextLine().split("/");
        // litte date validation, not in depth but the biggest mistakes will be stoped
        Date scDate = new Date(Integer.parseInt(inDate[0]), Integer.parseInt(inDate[1]), Integer.parseInt(inDate[2]) );
        if( Integer.parseInt(inDate[1]) > 12 || Integer.parseInt(inDate[2]) > 31) scDate = scanDate(sc, dateType);
        return scDate;
    }
}