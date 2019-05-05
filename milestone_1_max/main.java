
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import storage.*;

public class main {
    public static void main( String[] args ) {
        Scanner sc = new Scanner( System.in );
        System.out.println("---------------------------------------------------------------");
        System.out.println("|This Program uses LocalDate so the date might be one day off.|");
        System.out.println("---------------------------------------------------------------");
        // test Date 
        Date today = getToday();
        System.out.println("Today is");
        today.printDate();

        Vector oVector = gettigStarted( sc );
        //oVector.elementAt(0).printProject();
        /**This is the menu routine */
        while(true){
            menutext();
            Project pro = new Project();
            switch ( sc.nextInt() ) {
                case 1:
                    oVector.add ( createProject( sc, oVector ) );
                break;

                case 2:
                    System.out.println( "please enter the PID number" );
                    oVector.remove( sc.nextInt() );
                    break;

                case 3:
                    System.out.println( "All listed projects" );
                    for ( int i = 0; i < oVector.size(); i++ ){
                        pro = (Project) oVector.elementAt(i); //Thanks Kevin Kopp for helping out and telling me to cast because I didn't need to in the vector sample
                        pro.printProject();
                    }
                    break;
                
                case 4:
                System.out.println( "All current projects" );
                    for ( int i = 0; i < oVector.size(); i++ ) {
                        pro = (Project) oVector.elementAt(i);
                        if( pro.getPStartDate().isEarlierDate(today) ) pro.printProject();
                    }
                    break;
            
                case 5:
                    System.out.println("All past projects");
                    for ( int i = 0; i < oVector.size(); i++ ) {
                        pro = (Project) oVector.elementAt(i);
                        if( pro.getPEndDate().isEarlierDate(today) ) pro.printProject();
                    } 
                    break;
                
                case 6:
                    System.out.println("All upcoming projects");
                    for (int i = 0; i < oVector.size(); i++) {
                        pro = (Project) oVector.elementAt(i);
                        if( today.isEarlierDate( pro.getPStartDate() )) pro.printProject();
                    }
                    break;
                case 7:
                    System.out.println("are you sure, all you Projects will be lost");
                    System.out.println("type \"confirm\" to close the program");
                    String closeDiagString = sc.nextLine();
                    if( closeDiagString.equals("confirm") ) {
                        sc.close();
                        return;
                    } else break;
                default:
                    System.out.println( "this was not a valid selection" );
                    break;
            }
        }
    }

    public static void menutext(){
        System.out.println("now choose between");
        System.out.println("1 | create new Project ");
        System.out.println("2 | delete Prject by id");
        System.out.println("3 | list all projects");
        System.out.println("4 | list current projects");
        System.out.println("5 | list past projects");
        System.out.println("6 | list upcoming prjects");
        System.out.println("7 | end the programm");
    }

    public static Vector<Project> gettigStarted(Scanner sc ) {
        Vector oVector = new Vector<Project>();
        System.out.println("Let's start you with a new Project here");
        oVector.add(createProject(sc, oVector));
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
        //also edited to cap singel space hit, I kinda think this is enough
        if( pname == null || pname.length() == 0 || pname.equals(" ")) getPNameString(oVector, sc);
        //test if name is taken
        //could be redone with sets but this works for now
        if(! (oVector.size() == 0 )){ // if ther isn't any obj in vector we don't need to test
            for (int i = 0; i < oVector.size(); i++) {
                if ( oVector.elementAt(i).getPName() == pname ) {
                    System.out.println("The name has allready been taken by Project id: " + oVector.elementAt(i).getId());
                    getPNameString(oVector, sc);
                }
            }
        } 
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
