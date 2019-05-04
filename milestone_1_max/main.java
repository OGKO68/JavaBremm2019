
import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import storage.*;

public class main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("---------------------------------------------------------------");
    System.out.println("|This Program uses LocalDate so the date might be one day off.|");
    System.out.println("---------------------------------------------------------------");
    Date today = getToday();
    Vector oVector = gettigStarted(sc);
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
    public static Vector<prj> gettigStarted(Scanner sc ) {
        Vector oVector = new Vector<prj>();
        System.out.println("Let's start you with a new Project here");
        oVector.add(createProject(sc, oVector));
        return oVector;
    }
    public static prj createProject(Scanner sc, Vector<prj> oVector) {
        boolean cap = false;
        System.out.println("pleae enter a project name");
        String pname = sc.nextLine();
        while (cap) {
            if(! (oVector.size() == 0 )){
                for (int i = 0; i < oVector.size(); i++) {
                    if(oVector.elementAt(i).getPName() == pname) System.out.println("The name has allready been taken by Project id " + oVector.elementAt(i).getId());
                    else break;
                }
            }else break; // if ther isn't any obj in vector we don't need to test
            pname = sc.nextLine();
        }
        System.out.println("this is about the start date");
        Date sDate = scanDate(sc);
        System.out.println("this is about the end date");
        Date eDate = scanDate(sc);
        while (cap){
            if()
        }
        
        prj createprj = new prj(1, pname, getToday(), getToday());
        return createprj;
    }
    public static Date scanDate(Scanner sc ) {
        System.out.println("please enter date like (YYYY/MM/DD)");
        String[] inDate = sc.nextLine().split("/");
        Date scDate = new Date(Integer.parseInt(inDate[0]), Integer.parseInt(inDate[1]), Integer.parseInt(inDate[2]) );
        return scDate;
    }
}
