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
				projectNameHashSet.remove(projectName);
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
				employeeNameHashSet.remove(employeeName);
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
				departmentLeadList.get(i).addEmployee(employeeName);
			}
			return false;		
		} else {
			return false;
		}
	}

	@Override
	public boolean removeEmployeeFromDepartment(String employeeName, String nameOfDepartmentLead) {
		if(employeeNameHashSet.contains(nameOfDepartmentLead)) {
			for (int i = 0; i < departmentLeadList.size(); i++) {
				if(departmentLeadList.get(i).getEmployeeName().equals(nameOfDepartmentLead)){
					departmentLeadList.get(i).removeEmployee(employeeName);
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
	}

	@Override
	public ArrayList<String> getEmployeesWorkingOnProject(String projectName) {
		if(projectName == null || projectName == ""){return false;}
		if(! projectNameHashSet.contains(projectName)){return false;}
		for (int i = 0; i < projectArrayList.size(); i++) {
			if (projectArrayList.get(i).getProjectName().equals(projectName)) {
				return projectArrayList.get(i).getParticipatingWorkers();
			}
		}
	}

	@Override
	public ArrayList<String> getDepartmentLeadsOfProject(String projectName) {
		if(projectName == null || projectName == ""){return false;}
		if(! projectNameHashSet.contains(projectName)){return false;}
		ArrayList<String> empList = getEmployeesWorkingOnProject(projectName);

		if(empList.size() == 0){return false;}

		ArrayList<String> leadList = new ArrayList<String>();
		HashSet<String> leadSet = new HashSet<String>();

		//lel
		for (int i = 0; i < empList.size(); i++) {
			for (int j = 0; j < employeeArrayList.size(); j++) {
				if (employeeArrayList.get(i).getEmployeeName().equals(empList.get(i))) {
					if(leadSet.contains(employeeArrayLis.get(i).getEmployeeDepartmentLead())){
						break;
					}else{
						leadList.add(employeeArrayLis.get(i).getEmployeeDepartmentLead()); 
						leadSet.add(employeeArrayLis.get(i).getEmployeeDepartmentLead());
					}
				}
			}	
		}
		return false;
	}

	@Override
	public int getMonthlyCostsOfProject(String projectName) {
		if(projectName == null || projectName == ""){return false;}
		if(! projectNameHashSet.contains(projectName)){return false;}
		
		ArrayList<String> empList = getEmployeesWorkingOnProject(projectName);
		String curEmpString = null;
		int cost = 0;

		for (int i = 0; i < empList.size(); i++) {
			for (int j = 0; j < employeeArrayList.size(); j++) {
				if (employeeArrayList.get(j).getEmployeeName().equals(empList.get(i))) {
					cost = cost + employeeArrayList.get(j).getEmployeeSalary();
					break;
				}
			}	
		}
		return cost;
	}

	@Override
	public ArrayList<String> getEmployeesOfDepartment(String nameOfDepartmentLead) {
		if(nameOfDepartmentLead == null || nameOfDepartmentLead == ""){return false;}
		if(! employeeNameHashSet.contains(nameOfDepartmentLead)){return false;}
		
		for (int i = 0; i < departmentLeadList.size(); i++) {
			if(departmentLeadList.get(i).getEmployeeName().equals(nameOfDepartmentLead)){
				return departmentLeadList.get(i).getEmployeeProjectList();
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> getProjectsOfDepartment(String nameOfDepartmentLead) {
		if(nameOfDepartmentLead == null || nameOfDepartmentLead == ""){return false;}
		if(! employeeNameHashSet.contains(nameOfDepartmentLead)){return false;}

		ArrayList<String> empList = getEmployeesOfDepartment(nameOfDepartmentLead);
		
		if (empList.size() == 0) { return false; }

		ArrayList<String> prjList = new ArrayList<String>();
		HashSet<String> prjSet = new HashSet<String>();
		
		for (int i = 0; i < empList.size(); i++) {
			for (int j = 0; j < employeeArrayList.size(); j++) {
				if( employeeArrayList.get(j).getEmployeeName().equals(empList.get(i) ) ) {

					if( employeeArrayList.get(j).getEmployeeProjectList().size() == 0 ){ break;}
					ArrayList<String> locPrjList = new ArrayList<String>();
					for (int l = 0; l < employeeArrayList.get(j).getEmployeeProjectList().size(); l++) {	
						if(! prjSet.contains(employeeArrayList.get(j).getEmployeeProjectList().get(l))){
							prjSet.add(employeeArrayList.get(j).getEmployeeProjectList().get(l));
							prjList.add(employeeArrayList.get(j).getEmployeeProjectList().get(l));
						}
					}
				}
			}
		}

		return prjList;
	}

	@Override
	public int getMonthlyCostsOfDepartment(String nameOfDepartmentLead) {
		if(nameOfDepartmentLead == null || nameOfDepartmentLead == ""){return false;}
		if(! employeeNameHashSet.contains(nameOfDepartmentLead)){return false;}
		ArrayList<String> empList = getEmployeesOfDepartment(nameOfDepartmentLead);
		int cost = 0;

		for (int i = 0; i < empList.size(); i++) {
			for (int j = 0; j < employeeArrayList.size(); j++) {
				if(employeeArrayList.get(j).getEmployeeName().equals(empList.get(i))){
					cost = cost + employeeArrayList.get(j).getEmployeeSalary();
				}
			}
		}

		return cost;
	}

	@Override
	public ArrayList<String> getProjectsOfEmployees(String employeeName) {
		if(employeeName == null || employeeName == ""){return false;}
		if(! employeeNameHashSet.contains(employeeName)){return false;}

		for (int i = 0; i < employeeArrayList.size(); i++) {
			if(employeeArrayList.get(i).equals(employeeName)){
				return employeeArrayList.get(i).getEmployeeProjectList();
			}
		}

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
