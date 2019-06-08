package testing;

import main.*;

import java.util.*;

public class main {
	HashSet<String> projectNameHashSet = new HashSet<String>();
	ArrayList<Project> projectArrayList = new ArrayList<Project>();

	public static void main(String[] args) {
		
		testcreation("p1",new GregorianCalendar(2019,5,12), new GregorianCalendar(2019,6,13));


	}
	
	public static boolean testcreation(String projectName, Calendar startDate, Calendar endDate){
		projectArrayList.add(new Project());
		// if the name is emty,space,null,not unq than false 
		if(! projectArrayList.get(projectArrayList.size() - 1).setProjectName(projectName, projectNameHashSet) ) return false;
		else projectNameHashSet.add(projectName);
		try {
			if(! projectArrayList.get(projectArrayList.size() - 1).setProjectStartDate(startDate) ) return false;
		} catch (Exception e) {
			return false;
		}
		try {
			if(! projectArrayList.get(projectArrayList.size() - 1).setProjectEndDate(endDate) ) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
