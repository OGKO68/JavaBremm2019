package interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import main.*;

public class apiImplementation implements ApiInterface {

	private ArrayList<Project> projectArrayList;
	private HashSet<String> projectNameHashSet;
	
	//custom default constructor
	public apiImplementation() {
		this.projectArrayList = new ArrayList<Project>();
		this.projectNameHashSet = new HashSet<String>();
	}
	
	/*
	 * THIS IS NOT API IMPLEMENTATION
	 * */
	public ArrayList<Project> getProjectArrayList(){
		return this.projectArrayList;
	}
	
	/**
	 * @param projectArrayList the projectArrayList to set
	 */
	public void setProjectArrayList(ArrayList<Project> projectArrayList) {
		this.projectArrayList = projectArrayList;
	}
	
	public HashSet<String> getProjectNameHashSet(){
		return this.projectNameHashSet;
	}
	
	/**
	 * @param projectNameHashSet the projectNameHashSet to set
	 */
	public void setProjectNameHashSet(HashSet<String> projectNameHashSet) {
		this.projectNameHashSet = projectNameHashSet;
	}
	/*
	 * THIS IS NOT API IMPLEMENTATION
	 * */

	//IMPLEMENTATION START
	@Override
	public boolean createProject(String projectName, Calendar startDate, Calendar endDate){
		
		//work with local lists to only complete finished transactions
		ArrayList<Project> localProjectArrayList = this.getProjectArrayList();
		HashSet<String> localProjectNameHashSet = this.getProjectNameHashSet();
		
		//cereate a local project to add after the all checks to avoid having empty objects in ArrayList
		Project localProject = new Project();
		
		// if the name is empty,space,null,not unq than false 
		if(! localProject.setProjectName(projectName, localProjectNameHashSet) ) return false;
		//check start date not null and if before end
		if(! localProject.setProjectStartDate(startDate) ) return false;
		//check end date not null and if after start
		if(! localProject.setProjectEndDate(endDate)) return false;
		
		//add name to hash set
		localProjectNameHashSet.add(projectName);
		//add the Project to the List
		localProjectArrayList.add(localProject);
		
		//update the lists
		this.setProjectArrayList(localProjectArrayList);
		this.setProjectNameHashSet(localProjectNameHashSet);
		
		//success
		return true;
	}

	@Override
	public boolean changeProject(String projectNameOld, String projectNameNew, Calendar startDate, Calendar endDate) {
			
		for(int i = 0; this.projectArrayList.size() > i ; i++) {
			if(this.projectArrayList.get(i).getProjectName() == projectNameOld) {
				if( projectNameOld.equals(projectNameNew)){
					//check start date not null and if before end
					if(! this.projectArrayList.get(i).setProjectStartDate(startDate))return false;
					//check end date not null and if after start
					if(!this.projectArrayList.get(i).setProjectEndDate(endDate))return false;
					//success
					return true;
				}
				else {
					
					// if the name is empty,space,null,not unq than false 
					if(! this.projectArrayList.get(i).setProjectName(projectNameNew, this.projectNameHashSet) ) return false;
					//check start date not null and if before end
					if(! this.projectArrayList.get(i).setProjectStartDate(startDate) ) return false;
					//check end date not null and if after start
					if(! this.projectArrayList.get(i).setProjectEndDate(endDate)) return false;
					//success
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteProject(String projectName) {
		for(int i = 0; this.projectArrayList.size() > i ; i++) {
			if(projectArrayList.get(i).getProjectName().equals(projectName)) {
				projectArrayList.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> getProjectNames() {
		//create the list
		ArrayList<String> localProjectNameList = new ArrayList<String>();
		//populate the list
		for(int i = 0; this.projectArrayList.size() > i ; i++) {
			//iterate through all Projects to get the name
			localProjectNameList.add(this.projectArrayList.get(i).getProjectName());
		}
		//return the name
		return localProjectNameList;
	}

	@Override
	public boolean createEmployee(String employeeName, int salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeEmployee(String employeeNameOld, String employeeNameNew, int salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(String employeeName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> getEmployeeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createDepartmentlead(String departmentLeadName, int salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDepartmentlead(String departmentLeadNameOld, String departmentLeadNameNew, int salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDepartmentlead(String departmentLeadName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> getDepartmentLeadNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEmployeeToProject(String employeeName, String projectName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEmployeeFromProject(String employeeName, String projectName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEmployeeToDepartment(String employeeName, String nameOfDepartmentLead) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEmployeeFromDepartment(String employeeName, String nameOfDepartmentLead) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> getEmployeesWorkingOnProject(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getDepartmentLeadsOfProject(String projectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMonthlyCostsOfProject(String projectName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> getEmployeesOfDepartment(String nameOfDepartmentLead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getProjectsOfDepartment(String nameOfDepartmentLead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMonthlyCostsOfDepartment(String nameOfDepartmentLead) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<String> getProjectsOfEmployees(String employeeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveProjects(String pathToFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadProjects(String pathToFile) {
		// TODO Auto-generated method stub
		return false;
	} 
	
}
