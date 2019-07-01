package interfaces;

import java.util.ArrayList;
import java.util.Calendar;

public interface ApiInterface {
	
	//Create, change, delete Objects:
	/**Creates a new project.
	 * 
	 * @param projectName Names of projects should be unique. Should not be null or empty.
	 * @param startDate StartDate should not be after endDate. Should not be null.
	 * @param endDate EndDate should be after startDate. Should not be null.
	 * @return true if project was created successfully, false if otherwise.
	 */
	boolean createProject(String projectName, Calendar startDate, Calendar endDate);
	
	/**Changes the name and/or the dates of the project.
	 * If the old name equals the new name, the name won't be changed. The same goes for the dates.
	 * If the project which is subject to a name-change, all references to the project should be updated accordingly.
	 * 
	 * @param projectNameOld Should not be null or empty.
	 * @param projectNameNew Names of projects should be unique. Should not be null or empty.
	 * @param startDate StartDate should not be after endDate. Should not be null.
	 * @param endDate EndDate should be after startDate. Should not be null.
	 * @return true if project was changed successfully, false if otherwise.
	 */
	boolean changeProject(String projectNameOld, String projectNameNew, Calendar startDate, Calendar endDate);
	
	/**Removes project and references to the project from all lists (like departments). 
	 * 
	 * @param projectName Should not be null or empty.
	 * @return true if project was deleted successfully, false if otherwise.
	 */
	boolean deleteProject(String projectName);
	
	/**Returns list of the names of all projects.
	 * 
	 * @return List of the names of all projects. List is empty if no projects were found.
	 */
	ArrayList<String> getProjectNames();
	
	/**Creates an employee (which is not a department lead at the same time)
	 * 
	 * @param employeeName Names of employees should be unique. Should not be null or empty.
	 * @param salary Should not be null or negative.
	 * @return true if employee was created successfully, false if otherwise.
	 */
	boolean createEmployee(String employeeName, int salary);
	
	/**Changes the name and/or the salary of an employee.
	 * If the old name equals the new name, the name won't be changed. The same goes for the salary.
	 * If the employee which is subject to a name-change is a departmentLead, the department is renamed. All references to the department should be updated accordingly.
	 * It is not possible to promote an employee.
	 * 
	 * @param employeeNameOld Should not be null or empty.
	 * @param employeeNameNew Names of employees should be unique. Should not be null or empty.
	 * @param salary Should not be null or negative.
	 * @return true if employee was changed successfully, false if otherwise.
	 */
	boolean changeEmployee(String employeeNameOld, String employeeNameNew, int salary);
	
	/**Removes an employee and references to the employee from all lists (like departments). 
	 * As DepartmentLeads are employees, too they also can be removed with this method.
	 * If a DepartmentLead is removed, his department and all references to this department should be deleted as well.
	 * 
	 * @param employeeName
	 * @return
	 */
	boolean deleteEmployee(String employeeName);
	
	/**Returns list of the names of all employees including department leads
	 * 
	 * @return List of the names of all employees including department leads. List is empty if no leads were found.
	 */
	ArrayList<String> getEmployeeNames();
	

	boolean createDepartmentlead(String departmentLeadName, int salary);
	
	/**
	 * @deprecated Please use "changeEmployee(...)" instead
	 * @param departmentLeadName Names of employees should be unique. Should not be null or empty.
	 * @param salary Should not be null or negative.
	 * @return true if departmentLead could be changed successfully, false if otherwise.
	 */
	boolean changeDepartmentlead(String departmentLeadNameOld, String departmentLeadNameNew, int salary);
	
	/** 
	 * @deprecated Please use "deleteEmployee(...)" instead
	 * @param departmentLeadName Should not be null or empty.
	 * @return true if departmentLead could be removed successfully, false if otherwise.
	 */
	boolean deleteDepartmentlead(String departmentLeadName);
	
	/**Returns list of the names of all department leads
	 * 
	 * @return List of the names of all department leads. List is empty if no leads are found.
	 */
	ArrayList<String> getDepartmentLeadNames();
	//-------------------------------------------------------------------------------------------------------
	
	//Assign & remove:
	/**Adds an employee to a project. A employee can be assigned to more than one project.
	 * 
	 * @param employeeName should not be null or empty.
	 * @param projectName should not be null or empty.
	 * @return true if employee was added the project successfully, false if otherwise.
	 */
	boolean addEmployeeToProject(String employeeName, String projectName);
	
	/**Removes an employee from a project.
	 * 
	 * @param employeeName should not be null or empty. 
	 * @param projectName should not be null or empty.
	 * @return true if employee was removed from the project successfully, false if otherwise.
	 */
	boolean removeEmployeeFromProject(String employeeName, String projectName);
	
	/**Assigns an employee to a department. Employees can only be assigned to one department.
	 * If an employee is assigned to a new department, he/she should be removed from the old one.
	 * DepartmentLeads are employees, too but it should not be possible to assign them to other departments. 
	 * 
	 * @param employeeName Should not be null or empty.
	 * @param nameOfDepartmentLead Should not be null or empty.
	 * @return true if employee was assigned successfully, false if otherwise.
	 */
	boolean addEmployeeToDepartment(String employeeName, String nameOfDepartmentLead);
	
	/**Removes an employee from her/his department. 
	 * DepartmentLeads are employees, too but it should not be possible to remove them from their own department. 
	 * 
	 * @param employeeName should not be null or empty.
	 * @param nameOfDepartmentLead should not be null or empty.
	 * @return true if employee was removed from the department successfully, false if otherwise.
	 */
	boolean removeEmployeeFromDepartment(String employeeName, String nameOfDepartmentLead);
	//-------------------------------------------------------------------------------------------------------
	
	//Answer Questions:
	/**Returns a list of all employees working on the given project.
	 * 
	 * @param projectName Should not be null or empty.
	 * @return list of all employees working on the given project. An empty list is returned if no one is working on the given project.
	 */
	ArrayList<String> getEmployeesWorkingOnProject(String projectName);
	
	/**Returns a list of all departmentLeads of employees working on the given project. Each name should only occur in the list once.
	 * 
	 * @param projectName Should not be null or empty.
	 * @return list of all departmentLeads of employees working on the given project.
	 */
	ArrayList<String> getDepartmentLeadsOfProject(String projectName);
	
	/**Calculates the monthly costs reflected by the sum of the salaries of all employees working on the given project.
	 * 
	 * @param projectName should not be null or empty.
	 * @return positive value of the sum of all salaries
	 */
	int getMonthlyCostsOfProject(String projectName);
	
	/**Returns names of all employees of the given department including the name of the department lead itself.
	 * 
	 * @param nameOfDepartmentLead should not be empty or null
	 * @return ArryList names of all employees of the given department including the name of the department lead itself.
	 */
	ArrayList<String> getEmployeesOfDepartment(String nameOfDepartmentLead);
	
	/**Returns all projects where members of the department (including the department lead) are assigned to.
	 * 
	 * @param nameOfDepartmentLead should not be empty or null
	 * @return all projects where members of the department (including the department lead) are assigned to. List is empty if no one is working on projects.
	 */
	ArrayList<String> getProjectsOfDepartment(String nameOfDepartmentLead);
	
	/**Returns the sum of the salary of all department members including the department lead
	 * 
	 * @param nameOfDepartmentLead should not be empty or null
	 * @return Sum of the salary of all department members including the department lead
	 */
	int getMonthlyCostsOfDepartment(String nameOfDepartmentLead);
	
	/**Returns all projects of the given employee.
	 * 
	 * @param employeeName should not be empty or null
	 * @return returns all projects of the given employee. List is empty if no one is working on the given project.
	 */
	ArrayList<String> getProjectsOfEmployees(String employeeName);
	
	//-------------------------------------------------------------------------------------------------------
	
	//Load & Save:
	/**
	 * 
	 * @param pathToFile
	 * @return
	 */
	boolean saveProjects(String pathToFile);
	boolean loadProjects(String pathToFile);
}
