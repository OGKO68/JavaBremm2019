package interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import main.*;

public class apiImplementation implements ApiInterface {

	ArrayList<Project> projectArrayList;
	HashSet<String> projectNameHashSet;


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


	@Override
	public boolean createProject(String projectName, Calendar startDate, Calendar endDate) {
		ArrayList<Project> localProjectArrayList = this.getProjectArrayList();
		HashSet<String> localProjectNameHashSet = this.getProjectNameHashSet();
		Project localProject = new Project(); 
		// if the name is empty,space,null,not unq than false 
		if(localProject.setProjectName(projectName, localProjectNameHashSet) ) {
			//localProjectNameHashSet.add(projectName);
		} else return false;
		
		try {
			if(! localProject.setProjectStartDate(startDate) ) return false;
		} catch (Exception e) {
			return false;
		}
		
		try {
			if(! localProject.setProjectEndDate(endDate) ) return false;
		} catch (Exception e) {
			return false;
		}
		localProjectArrayList.add(localProject);
		
		this.setProjectArrayList(localProjectArrayList);
		this.setProjectNameHashSet(localProjectNameHashSet);

		return true;
	}

	@Override
	public boolean changeProject(String projectNameOld, String projectNameNew, Calendar startDate, Calendar endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProject(String projectName) {
		for(int  i = projectArrayList.size(); i > 0  ; i-- ){
			if ( projectArrayList.get(i).getProjectName() == projectName ) {
				projectArrayList.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> getProjectNames() {
		// TODO Auto-generated method stub
		return null;
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
