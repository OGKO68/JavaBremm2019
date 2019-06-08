package interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import main.*;

public class apiImplementation implements ApiInterface {

	ArrayList<Project> projectArrayList = new ArrayList<Project>();
	HashSet<String> projectNameHashSet = new HashSet<String>();
	
	@Override
	public boolean createProject(String projectName, Calendar startDate, Calendar endDate) {
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
