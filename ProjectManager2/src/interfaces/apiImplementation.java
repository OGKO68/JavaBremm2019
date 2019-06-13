package interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import main.*;

public class apiImplementation implements ApiInterface {

	private ArrayList<Project> projectArrayList;
	private HashSet<String> projectNameHashSet;
	private ArrayList<Employee> employeeArrayList;
	//shared
	private HashSet<String> employeeNameHashSet;
	private ArrayList<DepartmentLead> departmentLeadList;

	//custom default constructor
	public apiImplementation() {
		this.projectArrayList = new ArrayList<Project>();
		this.projectNameHashSet = new HashSet<String>();
		this.employeeArrayList = new ArrayList<Employee>();
		this.employeeNameHashSet = new HashSet<String>();
		this.departmentLeadList = new ArrayList<DepartmentLead>();
	}

	//IMPLEMENTATION START
	@Override //DONE
	public boolean createProject(String projectName, Calendar startDate, Calendar endDate){
		
		//cereate a local project to add after the all checks to avoid having empty objects in ArrayList
		Project localProject = new Project();
		
		// if the name is empty,space,null,not unq than false 
		if(! localProject.setProjectName(projectName, projectNameHashSet) ) return false;
		//check start date not null and if before end
		if(! localProject.setProjectStartDate(startDate) ) return false;
		//check end date not null and if after start
		if(! localProject.setProjectEndDate(endDate)) return false;
		
		//add name to hash set
		projectNameHashSet.add(projectName);
		//add the Project to the List
		projectArrayList.add(localProject);
				
		//success
		return true;
	}

	@Override //DONE
	public boolean changeProject(String projectNameOld, String projectNameNew, Calendar startDate, Calendar endDate) {
			
		for(int i = 0; this.projectArrayList.size() > i ; i++) {
			if(this.projectArrayList.get(i).getProjectName() == projectNameOld) {
				if( projectNameOld.equals(projectNameNew)){
					//check start date not null and if before end
					if(!this.projectArrayList.get(i).setProjectStartDate(startDate))return false;
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
					//remove name
					projectNameHashSet.remove(projectNameOld);
					//success
					return true;
				}
			}
		}
		return false;
	}

	@Override //DONE
	public boolean deleteProject(String projectName) {
		for(int i = 0; this.projectArrayList.size() > i ; i++) {
			if(projectArrayList.get(i).getProjectName().equals(projectName)) {
				projectArrayList.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override //DONE
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

	@Override //DONE
	public boolean createEmployee(String employeeName, int salary) {
		Employee localEmployee = new Employee();
		
		if(! localEmployee.setEmployeeName(employeeName, employeeNameHashSet)) return false;

		if(! localEmployee.setEmployeeSalary(salary)) return false;
		
		employeeNameHashSet.add(employeeName);
		employeeArrayList.add(localEmployee);
		
		return true;
	}

	@Override //DONE
	public boolean changeEmployee(String employeeNameOld, String employeeNameNew, int salary) {
				
		if(employeeNameOld.equals(employeeNameNew)){
			for (int i = 0; i < employeeArrayList.size(); i++) {
				if (employeeArrayList.get(i).getEmployeeName().equals(employeeNameOld)){
					if(! employeeArrayList.get(i).setEmployeeSalary(salary)) return false;
					return true;
				}
			}
		}else{
			for (int i = 0; i < employeeArrayList.size(); i++) {
				if (employeeArrayList.get(i).getEmployeeName().equals(employeeNameOld)){
					if(! employeeArrayList.get(i).setEmployeeName(employeeNameNew, employeeNameHashSet)) return false;
					if(! employeeArrayList.get(i).setEmployeeSalary(salary)) return false;
					employeeNameHashSet.remove(employeeNameOld);
					return true;
				}
			}
		}
		return false;
		
	}

	@Override //DONE
	public boolean deleteEmployee(String employeeName) {
		for (int i = 0; i < employeeArrayList.size(); i++) {
			if (employeeArrayList.get(i).getEmployeeName().equals(employeeName)){
				employeeArrayList.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override //DONE
	public ArrayList<String> getEmployeeNames() {
		ArrayList<String> allEmployeeList = new ArrayList<String>(employeeArrayList.size());
		for (int i = 0; i < employeeArrayList.size(); i++) {
			allEmployeeList.add(employeeArrayList.get(i).getEmployeeName());
		}
		for (int i = 0; i < departmentLeadList.size(); i++) {
			allEmployeeList.add(departmentLeadList.get(i).getEmployeeName());
		}
		return allEmployeeList;
	}

	@Override //DONE
	public boolean createDepartmentlead(String departmentLeadName, int salary) {
		DepartmentLead localDepartmentLead = new DepartmentLead();
		
		if(! localDepartmentLead.setEmployeeName(departmentLeadName, employeeNameHashSet)) return false;

		if(! localDepartmentLead.setEmployeeSalary(salary)) return false;
		
		employeeNameHashSet.add(departmentLeadName);
		departmentLeadList.add(localDepartmentLead);
		return true;
	}

	@Override //DONE
	public boolean changeDepartmentlead(String departmentLeadNameOld, String departmentLeadNameNew, int salary) {
		if(departmentLeadNameOld.equals(departmentLeadNameNew)){
			for (int i = 0; i < departmentLeadList.size(); i++) {
				if (departmentLeadList.get(i).getEmployeeName().equals(departmentLeadNameOld)){
					if(! departmentLeadList.get(i).setEmployeeSalary(salary)) return false;
					return true;
				}
			}
		}else{
			for (int i = 0; i < departmentLeadList.size(); i++) {
				if (departmentLeadList.get(i).getEmployeeName().equals(departmentLeadNameOld)){
					if(! departmentLeadList.get(i).setEmployeeName(departmentLeadNameNew, employeeNameHashSet)) return false;
					if(! departmentLeadList.get(i).setEmployeeSalary(salary)) return false;
					employeeNameHashSet.remove(departmentLeadNameNew);
					return true;
				}
			}
		}
		return false;
	}

	@Override //DONE
	public boolean deleteDepartmentlead(String departmentLeadName) {
		for (int i = 0; i < departmentLeadList.size(); i++) {
			if (departmentLeadList.get(i).getEmployeeName().equals(departmentLeadName)){
				departmentLeadList.remove(i);
				employeeNameHashSet.remove(departmentLeadName);
				return true;
			}
		}
		return false;
	}

	@Override //DONE
	public ArrayList<String> getDepartmentLeadNames() {
		ArrayList<String> allDepartmentLeadList = new ArrayList<String>(departmentLeadList.size());
		for (int i = 0; i < departmentLeadList.size(); i++) {
			allDepartmentLeadList.add(departmentLeadList.get(i).getEmployeeName());
		}
		return allDepartmentLeadList;
	}

	@Override 
	public boolean addEmployeeToProject(String employeeName, String projectName) {
		for (int i = 0; i < projectArrayList.size(); i++) {
			if(projectArrayList.get(i).getProjectName().equals(projectName)){
				projectArrayList.get(i).addParticipatingWorker(employeeName);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeEmployeeFromProject(String employeeName, String projectName) {
		if(projectNameHashSet.contains(projectName)) {
			for (int i = 0; i < projectArrayList.size(); i++) {
				if(projectArrayList.get(i).getProjectName().equals(projectName)){
					projectArrayList.get(i).removeParticipatingWorker(employeeName);
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean addEmployeeToDepartment(String employeeName, String nameOfDepartmentLead) {
		if (employeeNameHashSet.contains(nameOfDepartmentLead) && employeeName.contains(employeeName)) {
			for (int i = 0; i < departmentLeadList.size(); i++) {
				if (departmentLeadList.get(i).getEmployeeName().equals(nameOfDepartmentLead)) {
					Employee employeeToAdd = new Employee();
					for (int j = 0; j < employeeArrayList.size(); i++) {
						if(employeeArrayList.get(j).getEmployeeName().equals(employeeName)){
							employeeToAdd = employeeArrayList.get(j);
						}
					}
					departmentLeadList.get(i).addEmployee(employeeToAdd);
				} 
			}
			return false;		
		} else {
			return false;
		}
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
