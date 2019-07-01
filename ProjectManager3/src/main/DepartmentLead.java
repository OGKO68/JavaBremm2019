package main;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Mandatory:
 * - Assignment & return of the employees
 * Recommended:
 * - Return of the projects where the respective
 *   employees are working on
 * - Return of sum of salary of members of the department
 */

public class DepartmentLead extends Employee {

	/** List of assigned Employees, all unasigned Emplyees will be deleted */
    public ArrayList<String> assignedEmployees;
    
	public DepartmentLead(String name, int salary)
	{
		super(name, salary);
		this.assignedEmployees = new ArrayList<String>();
		this.assignedEmployees.add(this.getEmployeeName());
	}
	
	public boolean addEmployee(String empName)
	{
		if (assignedEmployees.contains(empName))
			return false;
		assignedEmployees.add(empName);
		return true;
	}
	
	public boolean removeEmployee(String empName)
	{
		if (!assignedEmployees.contains(empName))
			return false;
		assignedEmployees.remove(empName);
		return true;
	}

	public ArrayList<String> getAllEmployees()
	{
		return assignedEmployees;
	}
	
    /** returns List of all Projects subordinates work on */
    public ArrayList<String> allEmployeeProjects(){
        // TODO implementation
        ArrayList<String> returnArrayList =  new ArrayList<String>();
        return returnArrayList;
    }

    /** Calculates the Salarie of all subordinates Employees */
    public int employeesSalaries(){
        return 1;
    }
}


