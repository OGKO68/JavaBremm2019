package interfaces.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

//import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

import interfaces.ApiInterface;
import main.*;

public class API implements ApiInterface {

	private HashMap<String, Project> projectHashMap;
	private HashMap<String, Employee> employeeHashMap;
	private HashMap<String, DepartmentLead> depLeadHashMap;
	//custom default constructor
	public API() {
		this.depLeadHashMap = new HashMap<String, DepartmentLead>();
		this.employeeHashMap = new HashMap<String, Employee>();
		this.projectHashMap = new HashMap<String, Project>();
	}

	//IMPLEMENTATION START
	@Override //DONE
	public boolean createProject(String projectName, Calendar startDate, Calendar endDate){
		// Check if the name is valid or already exist
		if (!isValidName(projectName) || projectHashMap.containsKey(projectName))
			return false;
		// Check if the given information builds a valid project
		Project validProject = this.createValidProject(projectName, startDate, endDate);
		if (validProject == null)
			return false;
		// Add the project to the list
		projectHashMap.put(projectName, validProject);
		return true;
	}

	@Override //DONE
	public boolean changeProject(String projectNameOld, String projectNameNew, Calendar startDate, Calendar endDate) {
		// Check if the new name is valid
		if (!isValidName(projectNameNew))
			return false;
		// Check existence and duplicates
		if (!projectHashMap.containsKey(projectNameOld)
				|| (projectHashMap.containsKey(projectNameNew) && !projectNameNew.equals(projectNameOld)))
			return false;
		// create a new project
		Project newProject = this.createValidProject(projectNameNew, startDate, endDate);
		if (newProject == null)
			return false;
		// Replace the projects and move their data
		Project oldProject = projectHashMap.get(projectNameOld);
		newProject.setParticipatingWorkers(oldProject.getParticipatingWorkers());
		projectHashMap.remove(projectNameOld);
		projectHashMap.put(projectNameNew, newProject);
		return true;
	}

	@Override //DONE
	public boolean deleteProject(String projectName) {
		
		if (!projectHashMap.containsKey(projectName))
			return false;
		projectHashMap.remove(projectName);
		return true;
	}

	@Override //DONE
	public ArrayList<String> getProjectNames() {
		ArrayList<String> namesList = new ArrayList<String>();
		for (String name : projectHashMap.keySet())
			namesList.add(name);
		return namesList;
	}

	@Override
	public boolean createEmployee(String employeeName, int salary) {
		if (salary < 0 || !isValidName(employeeName))
			return (false);
		if (employeeHashMap.containsKey(employeeName))
			return (false);
		employeeHashMap.put(employeeName, new DepartmentLead(employeeName, salary));
		return true;
	}

	@Override
	public boolean changeEmployee(String employeeNameOld, String employeeNameNew, int salary) {
	
		if (salary < 0 || !isValidName(employeeNameOld) || !isValidName(employeeNameNew))
			return (false);
		// Check if the employee name is a Department lead
		if (depLeadHashMap.containsKey(employeeNameOld))
			return this.changeDepartmentlead(employeeNameOld, employeeNameNew, salary);
		// Check existence and duplicates
		if (!employeeHashMap.containsKey(employeeNameOld)
				|| (employeeHashMap.containsKey(employeeNameNew) && !employeeNameOld.equals(employeeNameNew)))
			return false;
		// Replace the old employee by the new one
		employeeHashMap.remove(employeeNameOld);
		employeeHashMap.put(employeeNameNew, new Employee(employeeNameNew, salary));
		
		// Update the department leads with the new employee
		for (DepartmentLead depLead : depLeadHashMap.values())
			if (depLead.getAllEmployees().contains(employeeNameOld))
			{
				depLead.removeEmployee(employeeNameOld);
				depLead.addEmployee(employeeNameNew);
			}
		
		// Update the projects with the new employee
		for (Project project : projectHashMap.values())
			if (project.getParticipatingWorkers().contains(employeeNameOld))
			{
				project.RemoveParticipatingWorker(employeeNameOld);
				project.addParticipatingWorker(employeeNameNew);
			}
			
		return true;
	}

	@Override
	public boolean deleteEmployee(String employeeName) {
		if (!isValidName(employeeName))
			return false;
		if (!employeeHashMap.containsKey(employeeName))
			return false;
		
		employeeHashMap.remove(employeeName);
		
		// Remove the employee from the departments
		for (DepartmentLead depLead : depLeadHashMap.values())
			if (depLead.getAllEmployees().contains(employeeName))
				depLead.removeEmployee(employeeName);
		
		// Remove the employee from the projects
		for (Project project : projectHashMap.values())
			if (project.getParticipatingWorkers().contains(employeeName))
				project.RemoveParticipatingWorker(employeeName);
		return true;
	}

	@Override
	public ArrayList<String> getEmployeeNames() {
		ArrayList<String> namesList = new ArrayList<String>();
		for (String name : depLeadHashMap.keySet())
			namesList.add(name);
		for (String name : employeeHashMap.keySet())
			namesList.add(name);
		return namesList;
	}

	@Override
	public boolean createDepartmentlead(String departmentLeadName, int salary) {
		if (salary < 0 || !isValidName(departmentLeadName))
			return (false);
		if (depLeadHashMap.containsKey(departmentLeadName) || employeeHashMap.containsKey(departmentLeadName))
			return (false);
		depLeadHashMap.put(departmentLeadName, new DepartmentLead(departmentLeadName, salary));
		return true;
	}

	@Override
	public boolean changeDepartmentlead(String departmentLeadNameOld, String departmentLeadNameNew, int salary) {
		
		if (salary < 0 || !isValidName(departmentLeadNameOld) || !isValidName(departmentLeadNameNew))
			return (false);
		// Check existence and duplicates
		if ((!depLeadHashMap.containsKey(departmentLeadNameOld) || employeeHashMap.containsKey(departmentLeadNameNew))
				|| (depLeadHashMap.containsKey(departmentLeadNameNew) && !departmentLeadNameOld.equals(departmentLeadNameNew)))
			return false;
		// Create a new Dep Lead
		DepartmentLead newDepLead = new DepartmentLead(departmentLeadNameNew, salary);
		// Update the new dep-lead with the list of employees
		for (String empName : depLeadHashMap.get(departmentLeadNameOld).getAllEmployees())
			if (!empName.equals(departmentLeadNameOld))
				newDepLead.addEmployee(empName);
		
		// Replace the dep-leads
		depLeadHashMap.remove(departmentLeadNameOld);
		depLeadHashMap.put(departmentLeadNameNew, newDepLead);
		return true;
	}

	@Override
	public boolean deleteDepartmentlead(String departmentLeadName) {
		
		if (!isValidName(departmentLeadName))
			return false;
		if (!depLeadHashMap.containsKey(departmentLeadName))
			return false;
		
		depLeadHashMap.remove(departmentLeadName);
		
		// Remove the dep-leads from the projects
		for (Project project : projectHashMap.values())
			if (project.getParticipatingWorkers().contains(departmentLeadName))
				project.RemoveParticipatingWorker(departmentLeadName);
		return true;
	}

	@Override
	public ArrayList<String> getDepartmentLeadNames() {
		ArrayList<String> namesList = new ArrayList<String>();
		for (String name : depLeadHashMap.keySet())
			namesList.add(name);
		return namesList;
	}

	@Override
	public boolean addEmployeeToProject(String employeeName, String projectName) {
		
		if (!isValidName(employeeName) || !isValidName(projectName))
			return false;
		
		// Check existence and duplicates
		if ((!employeeHashMap.containsKey(employeeName) && !depLeadHashMap.containsKey(employeeName))
				|| !projectHashMap.containsKey(projectName))
			return false;
		
		projectHashMap.get(projectName).addParticipatingWorker(employeeName);
		return true;
	}

	@Override
	public boolean removeEmployeeFromProject(String employeeName, String projectName) {
		
		if (!isValidName(employeeName) || !isValidName(projectName))
			return false;
		
		if ((!employeeHashMap.containsKey(employeeName) && !depLeadHashMap.containsKey(employeeName))
				|| !projectHashMap.containsKey(projectName))
			return false;
		
		projectHashMap.get(projectName).RemoveParticipatingWorker(employeeName);
		return true;
	}

	@Override
	public boolean addEmployeeToDepartment(String employeeName, String nameOfDepartmentLead) {
		
		if (!isValidName(employeeName) || !isValidName(nameOfDepartmentLead))
			return false;
		if (!employeeHashMap.containsKey(employeeName) || !depLeadHashMap.containsKey(nameOfDepartmentLead))
			return false;
		
		// Remove employee from the previous department
		for (DepartmentLead depLead : depLeadHashMap.values())
			if (depLead.getAllEmployees().contains(employeeName))
				depLead.removeEmployee(employeeName);
		
		depLeadHashMap.get(nameOfDepartmentLead).addEmployee(employeeName);
		return true;
	}

	@Override
	public boolean removeEmployeeFromDepartment(String employeeName, String nameOfDepartmentLead) {
		
		if (!isValidName(employeeName) || !isValidName(nameOfDepartmentLead))
			return false;
		if (!employeeHashMap.containsKey(employeeName) || !depLeadHashMap.containsKey(nameOfDepartmentLead))
			return false;
		
		depLeadHashMap.get(nameOfDepartmentLead).removeEmployee(employeeName);
		return true;
	}

	@Override
	public ArrayList<String> getEmployeesWorkingOnProject(String projectName) {
		
		if (!isValidName(projectName) || !projectHashMap.containsKey(projectName))
			return new ArrayList<String>();
		
		return projectHashMap.get(projectName).getParticipatingWorkers();
	}

	@Override
	public ArrayList<String> getDepartmentLeadsOfProject(String projectName) {

		if (!isValidName(projectName) || !projectHashMap.containsKey(projectName))
			return new ArrayList<String>();

		ArrayList<String> depLeads = new ArrayList<String>();
		// Search for dep-leads involved in the project
		for (String empName : this.getEmployeesWorkingOnProject(projectName))
		{
			for (DepartmentLead depLead : depLeadHashMap.values())
				if (depLead.getAllEmployees().contains(empName) && !depLeads.contains(depLead.getEmployeeName()))
					depLeads.add(depLead.getEmployeeName());
		}
		return depLeads;
	}

	@Override
	public int getMonthlyCostsOfProject(String projectName) {

		if (!isValidName(projectName) || !projectHashMap.containsKey(projectName))
			return 0;

		int cost = 0;
		for (String empName : projectHashMap.get(projectName).getParticipatingWorkers())
		{
			if (employeeHashMap.containsKey(empName))
				cost += employeeHashMap.get(empName).getEmployeeSalary();
			else if (depLeadHashMap.containsKey(empName))
				cost += depLeadHashMap.get(empName).getEmployeeSalary();
		}
		return (cost);
	}

	@Override
	public ArrayList<String> getEmployeesOfDepartment(String nameOfDepartmentLead) {

		if (!isValidName(nameOfDepartmentLead) || !depLeadHashMap.containsKey(nameOfDepartmentLead))
			return new ArrayList<String>();

		return depLeadHashMap.get(nameOfDepartmentLead).getAllEmployees();
	}

	@Override
	public ArrayList<String> getProjectsOfDepartment(String nameOfDepartmentLead) {

		if (!isValidName(nameOfDepartmentLead) || !depLeadHashMap.containsKey(nameOfDepartmentLead))
			return new ArrayList<String>();

		ArrayList<String> projects = new ArrayList<String>();
		for (Project project : projectHashMap.values())
			if (this.getDepartmentLeadsOfProject(project.getProjectName()).contains(nameOfDepartmentLead))
				projects.add(project.getProjectName());
		return projects;
	}

	@Override
	public int getMonthlyCostsOfDepartment(String nameOfDepartmentLead) {

		if (!isValidName(nameOfDepartmentLead) || !depLeadHashMap.containsKey(nameOfDepartmentLead))
			return 0;

		DepartmentLead depLead = depLeadHashMap.get(nameOfDepartmentLead);
		int cost = depLead.getEmployeeSalary();
		
		for (String empName : depLead.getAllEmployees())
			if (employeeHashMap.containsKey(empName))
				cost += employeeHashMap.get(empName).getEmployeeSalary();
		return cost;
	}

	@Override
	public ArrayList<String> getProjectsOfEmployees(String employeeName) {

		if (!isValidName(employeeName) || !employeeHashMap.containsKey(employeeName))
			return new ArrayList<String>();

		ArrayList<String> projects = new ArrayList<String>();

		for (Project project : projectHashMap.values())
			if (project.getParticipatingWorkers().contains(employeeName))
				projects.add(project.getProjectName());
		return projects;
	}

	@Override
	public boolean saveProjects(String pathToFile) {

		try {
			FileOutputStream fileOutStream = new FileOutputStream(pathToFile);
			ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
			
			objOutStream.writeObject(projectHashMap);
			
			objOutStream.flush();
			objOutStream.close();
			fileOutStream.close();
		} catch (FileNotFoundException e) {
			System.err.format("File Not Found: %s\n", pathToFile);
			return false;
		} catch (IOException e) {
			System.err.println("Input/Output Error !");
			return false;
		}
		return true;
	}

	@Override
	public boolean loadProjects(String pathToFile) {

		try {
			FileInputStream fileInStream = new FileInputStream(pathToFile);
			ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
			
			projectHashMap = (HashMap<String, Project>)objInStream.readObject();
			
			objInStream.close();
			fileInStream.close();
		} catch (FileNotFoundException e) {
			System.err.format("File not found: %s\n", pathToFile);
			return false;
		} catch (IOException e) {
			System.out.println("Input / Output Error !");
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found Exception !");
			return false;
		}
		return true;
	}

	/**
	 * Checks if a given name is valid (Not null, empty or blank).
	 * @param name the name to be treated.
	 * @return Returns true if the name is valid.
	 */
	private boolean isValidName(String name)
	{
		return (name != null && !name.isBlank() && !name.isEmpty());
	}

	/**
	 * Create a valid project
	 * @param projectName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private Project createValidProject(String projectName, Calendar startDate, Calendar endDate){
		
		if (!isValidName(projectName))
			return (null);
		
		//cereate a local project to add after the all checks to avoid having empty objects in ArrayList
		Project localProject = new Project(projectName);
		
		//check start date not null and if before end
		if(! localProject.setProjectStartDate(startDate) ) return null;
		
		//check end date not null and if after start
		if(! localProject.setProjectEndDate(endDate)) return null;
		
		return localProject;
	}
	
}
