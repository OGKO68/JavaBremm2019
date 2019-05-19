
package Back;
import static java.lang.Thread.sleep;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Main {
    public static void main( String[] args ) {
        Scanner sc = new Scanner( System.in );
        // test Date 
        Date today = getToday();
        System.out.println("Today is");
        today.printDate();
        HashSet<String> hSet = new HashSet<String>();
        ArrayList<Project> oArrayList = gettigStarted( sc, hSet); //thanks to Ahmet Altundag for explaining me the need for the second wildcard. After NodeJS I kinda forgot about this thing 
        hSet.add( oArrayList.get(0).getPName() );
        //oArrayList.get(0).printProject();
        /**This is the menu routine */
        looper(oArrayList, hSet, sc, today);
        //anything beyond this point will not be exceuted
    }

    public static void menutext(){
        try{
        sleep(5000);
        }catch(InterruptedException e){}
        
        System.out.println("---------------------------------------------------------------");
        System.out.println("now choose between");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1  | create new project ");
        System.out.println("2  | delete project by id");
        System.out.println("3  | list all projects");
        System.out.println("4  | list current projects");
        System.out.println("5  | list upcoming projects");
        System.out.println("6  | list past projects");
        System.out.println("7  | change project name by id");
        System.out.println("8  | change project start by id");
        System.out.println("9  | change project end by id");
        System.out.println("10 | end the programm");
        System.out.println("---------------------------------------------------------------");
        System.out.println(" please only enter a number without spaces like \'8\'");
        System.out.println("---------------------------------------------------------------");

    }

    public static ArrayList<Project> gettigStarted(Scanner sc, HashSet<String> hSet) {
        ArrayList<Project> oArrayList = new ArrayList<>();
        System.out.println("Let's start a new Project here");
        oArrayList.add(createProject(sc, oArrayList, hSet));
        return oArrayList;
    }

    public static Project createProject(Scanner sc, ArrayList<Project> oArrayList, HashSet<String> hSet) {
        String pname = getPNameString(oArrayList, sc, hSet);
        Date pEndDate = new Date();
        Date pStartDate = new Date();
        String start = "start";
        String end = "end";
        pStartDate = scanDate(sc, start);
        pEndDate = scanDate(sc, end);
        while( ! pStartDate.isEqualEarlierDate( pEndDate ) ){
        	System.out.println("---------------------------------------------------------------");
            System.out.println("there might have been an error let's check that again");
            System.out.println("---------------------------------------------------------------");
            pStartDate = scanDate( sc, start );
            pEndDate = scanDate( sc, end );
        }
        int pid = 0;
        if (oArrayList.isEmpty() ) pid = 0;
        else pid = oArrayList.get( oArrayList.size() - 1 ).getId() + 1;
        Project createProject = new Project(pid, pname, pStartDate, pEndDate);
        System.out.println("---------------------------------------------------------------");
        System.out.println("you created project is");
        createProject.printProject();
        System.out.println("---------------------------------------------------------------");

        return createProject;
    }

    public static Date scanDate( Scanner sc, String dateType ) throws ArrayIndexOutOfBoundsException {
        System.out.println("so whats the " + dateType + " date");
        System.out.println("please enter date like dd-MM-yyyy");
        Date scDate = new Date();
        try {
            String todayString = sc.nextLine();
            String[] todayStringArr = todayString.split("-");
            
            Calendar cals = parseTimestamp(todayString);
            
            scDate = new Date(Integer.parseInt(todayStringArr[2]), Integer.parseInt(todayStringArr[1]),Integer.parseInt(todayStringArr[0])) ;
            //pDate.printDate();          
       
        } catch( ArrayIndexOutOfBoundsException | InputMismatchException | NumberFormatException | ParseException e) {
        	
            return scanDate(sc, dateType);
            
        }

        return scDate;
    }
    
    public synchronized static Calendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        sdf.setLenient(false);
        java.util.Date d = sdf.parse(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }
    
    public static String getPNameString(ArrayList<Project> oArrayList, Scanner sc, HashSet<String> hSet){
        System.out.println("please enter a project name");
        String pname = sc.nextLine();
        //empty name avoidance - found it here: https://www.tutorialspoint.com/check-if-a-string-is-empty-or-null-in-java
        //also edited to cap singel space hit, I kinda think this is enough
        if( pname == null || pname.length() == 0 || pname.equals(" ")) return getPNameString(oArrayList, sc, hSet);
        //test if name is taken
        //could be redone with sets but this works for now
        for (int i = 0; i < oArrayList.size(); i++) {
            if ( hSet.contains(pname) ) {
                System.out.println("The name has allready been taken by Project id: " + oArrayList.get(i).getId());
                return getPNameString(oArrayList, sc, hSet);
            }
        } 
        return pname;
    }

    public static Date getToday() {
    	//comment
        System.out.println("---------------------------------------------------------------");
        System.out.println("|This Program uses LocalDate so the date might be one day off.|");
        System.out.println("---------------------------------------------------------------");
        
        //credits https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate)); //YYYY/MM/DD
        
        String[] todayString = dtf.format(localDate).split("/");
        Date todayDate = new Date(Integer.parseInt(todayString[0]), Integer.parseInt(todayString[1]), Integer.parseInt(todayString[2]) );
        //todayDate.printDate();
        return todayDate;
    }

    public static void looper(ArrayList<Project> oArrayList, HashSet<String> hSet, Scanner sc, Date today){
        int locId = 0;
        Date locDate = new Date();
        while(true){

            menutext();

            switch ( nlInt(sc) ) {
                case 1:/**this creates new projects */
                    oArrayList.add ( createProject( sc, oArrayList, hSet ) );
                    hSet.add( oArrayList.get( oArrayList.size()-1 ).getPName());
                    
                break;

                case 2: /**this deltes projects found by id */
                    if (oArrayList.isEmpty() ){
                        System.out.println("there is no project that can be deleted");
                        break;
                    }
                    System.out.println("---------------------------------------------------------------");
                    System.out.println( "please enter the PID number" );
                    System.out.println("---------------------------------------------------------------");
                    locId = findElementById( oArrayList, sc );
                    hSet.remove(oArrayList.get(locId).getPName());
                    oArrayList.remove( locId );
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("Project has been removed");
                    System.out.println("---------------------------------------------------------------");
                    break;

                case 3:/**this lists all listed projects */
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All listed projects like id|name|start|end" );
                    System.out.println( "---------------------------------------------------------------" );
                    for ( int i = 0; i < oArrayList.size(); i++ ){
                        oArrayList.get(i).printProject();
                        //the reminace of a quick and dirty solution
                        //pro = (Project) oArrayList.get(i); //Thanks Kevin Kopp for helping out and telling me to cast because I didn't need to in the ArrayList sample+
                        //pro.printProject();
                    }
                    if( oArrayList.isEmpty() ) System.out.println("no projects");
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println("");
                    break;
                
                case 4:/**this lists all current projects */
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All current projects like id|name|start|end" );
                    System.out.println( "---------------------------------------------------------------" );
                    boolean b = false;
                    for ( int i = 0; i < oArrayList.size(); i++ ) {
                        b = today.isEqualEarlierDate( oArrayList.get(i).getPEndDate() ) && oArrayList.get(i).getPStartDate().isEqualEarlierDate( today ) ;
                        if( b ) oArrayList.get(i).printProject();
                        else if(i==0) System.out.println("no projects");
                    }
                    break;
            
                case 5:/**this lists all upcoming projects */ 
                    System.out.println( "---------------------------------------------------------------" );
                    System.out.println( "All upcoming projects like id|name|start|end" );
                    System.out.println( "---------------------------------------------------------------" );
                    for ( int i = 0; i < oArrayList.size(); i++ ) {
                        if( today.isEarlierDate( oArrayList.get(i).getPStartDate() ) ) oArrayList.get(i).printProject();
                        else if(i==0) System.out.println("no projects");
                    } 
                    break;
                
                case 6:/**this lists all past projects */
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("All past projects like id|name|start|end");
                    System.out.println("---------------------------------------------------------------");
                    for (int i = 0; i < oArrayList.size(); i++) {
                        if ( oArrayList.get(i).getPEndDate().isEqualEarlierDate( today ) ) oArrayList.get(i).printProject();
                        else if(i==0) System.out.println("no projects");
                    }
                    break;
                
                case 7:/**this is a name change */
                    System.out.println("---------------------------------------------------------------");                    
                    System.out.println("edit project name by id");
                    System.out.println("---------------------------------------------------------------");
                    if (oArrayList.size() < 1 ) {
                    	System.out.println("There is no Project to edit please create a Project by choosing 1 in the menu");
                    	break;
                    }
                    locId = findElementById(oArrayList, sc);
                    hSet.remove( oArrayList.get(locId).getPName() );
                    oArrayList.get(locId).setPName(getPNameString(oArrayList, sc, hSet));
                    break;
                
                case 8 : /**this is a start date change */ 
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("which start date would you like to change( refered to by id number )");
                    System.out.println("---------------------------------------------------------------");
                    if (oArrayList.size() < 1 ) {
                    	System.out.println("There is no Project to edit please create a Project by choosing 1 in the menu");
                    	break;
                    }
                    locId = findElementById(oArrayList, sc);
                    System.out.println("---------------------------------------------------------------");
                    locDate = scanDate(sc, "start");
                    if (locDate.isEqualEarlierDate(oArrayList.get(locId).getPEndDate())){ 
                        oArrayList.get(locId).setPStartDate(locDate);
                        System.out.println("success");
                        System.out.println("---------------------------------------------------------------\n");
                    }
                    else System.out.println("there was a an issue \n --------------------------------------------------------------- ");
                    break;

                case 9: /**this is a end date change */
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("which end date would you like to change( refered to by id number )");
                    System.out.println("---------------------------------------------------------------");
                    if (oArrayList.size() < 1 ) {
                    	System.out.println("There is no Project to edit please create a Project by choosing 1 in the menu");
                    	break;
                    }
                    locId = findElementById(oArrayList, sc);
                    locDate = scanDate(sc, "end");
                    if ( ! locDate.isEarlierDate(oArrayList.get(locId).getPStartDate())){ 
                        oArrayList.get(locId).setPEndDate(locDate);
                        System.out.println("success");
                        System.out.println("---------------------------------------------------------------\n");                        
                    } 
                    else System.out.println("there was a an issue \n --------------------------------------------------------------- ");
                    break;

                case 10: /**this is the exit */
                    System.out.println("---------------------------------------------------------------");
                    System.out.println("are you sure, all you Projects will be lost");
                    System.out.println("type \"confirm\" to close the program");
                    System.out.println("---------------------------------------------------------------");
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

    public static int findElementById( ArrayList<Project> oArrayList, Scanner sc){
        int pid = nlInt(sc);
        for (int i = 0; i < oArrayList.size() ; i++){
            if ( oArrayList.get(i).getId() == pid  ){
                pid = i;
                break;
            }
        }
        return pid;
    }

    public static int nlInt(Scanner sc){
        try {
            return Integer.parseInt( sc.nextLine() ) ;
        } catch ( InputMismatchException | NumberFormatException e) {
            return nlInt(sc);
        }
    }

}
