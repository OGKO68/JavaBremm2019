
import java.util.InputMismatchException;
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
    // test Date 
    Date today = getToday();
    
    Vector oVector = gettigStarted(sc);
    //oVector.elementAt(0).printProject();
    /**This is the menu routine */
    menutext();
    /*
    switch (sc.nextInt()) {
        case 1:
            oVector.add(createProject(sc, oVector));
        break;

        case 2:
            System.out.println("please enter the pID numeber");
            oVector.remove(sc.nextInt());
            break;

        case 3:
            for (int i = 0; i < oVector.size(); i++) {
                //Project curr = oVector.elementAt(i).printProject();
            }
            break;
        
        case 4:
            for (int i = 0; i < oVector.size(); i++) {
                //Project p = oVector.elementAt(i);
                /*Date pDate = oVector.elementAt(i). ;
                if(pDate.isEarlierDate(getToday)){

                }
                */ 
                /*
            }
            break;
       
        case 5:
            for (int i = 0; i < oVector.size(); i++) {
                
            }
            break;
        
        case 6:

            break;
        default:
            System.out.println("this was not a valid selection");
            break;
    }
    */

    sc.close();
    }
    public static void menutext(){
        System.out.println("now choose between");
        System.out.println("1 | create new Project ");
        System.out.println("2 | delete Prject by id");
        System.out.println("3 | list all projects");
        System.out.println("4 | list current projects");
        System.out.println("5 | list past projects");
        System.out.println("6 | list upcoming prjects");

    }

    public static Vector<Project> gettigStarted(Scanner sc ) {
        Vector oVector = new Vector<Project>();
        System.out.println("Let's start you with a new Project here");
        oVector.add(createProject(sc, oVector));
        System.out.println(oVector.size());
        oVector.elementAt(0).printProject();
        return oVector;
    }

    public static Project createProject(Scanner sc, Vector<Project> oVector) {
        
        String pname = getPNameString(oVector, sc);
        Date pEndDate = new Date();
        Date pStartDate = new Date();
        //please refactor later this is not optimal, there should be a recoursive solution
        try {
            String start = "start";
            String end = "end";
            pStartDate = scanDate(sc, start);
            pEndDate = scanDate(sc, end);
            while( pStartDate.isEarlierDate(pEndDate)){
                System.out.println("---------------------------------------------------------------");
                System.out.println("there might have been an error let's check that again");
                System.out.println("---------------------------------------------------------------");
                pStartDate = scanDate(sc, start);
                pEndDate = scanDate(sc, end);
            }
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
            System.err.println("inproper date format");
            createProject(sc, oVector);
        }

        Project createProject = new Project(oVector.size(), pname, pStartDate, pEndDate);
        createProject.printProject();
        return createProject;
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

    public static String getPNameString(Vector<Project> oVector, Scanner sc){
        System.out.println("please enter a project name");
        String pname = sc.nextLine();
        //empty name avoidance - found it here: https://www.tutorialspoint.com/check-if-a-string-is-empty-or-null-in-java
        //also edited to cap singel space hit, I kinda thinkt this is enough
        if( pname == null || pname.length() == 0 || pname.equals(" ")) getPNameString(oVector, sc);
        if(! (oVector.size() == 0 )){
            for (int i = 0; i < oVector.size(); i++) {
                if ( oVector.elementAt(i).getPName() == pname ) {
                    System.out.println("The name has allready been taken by Project id " + oVector.elementAt(i).getId());
                    getPNameString(oVector, sc);
                }
            }
        } // if ther isn't any obj in vector we don't need to test
        return pname;
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

}
