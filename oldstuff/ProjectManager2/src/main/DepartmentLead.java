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
    public ArrayList<String> assignedEmployeeList;
    public HashSet<String> assignedEmployeeSet;

    public DepartmentLead(){
        
        this.setEmployeeName2();
        this.setEmployeeDepartmentLead2();
        this.setEmployeeSalary2();
        this.setEmployeeProjectList2();
        
        this.assignedEmployeeList = new ArrayList<String>();
        this.assignedEmployeeSet = new HashSet<String>();
    }

    public boolean addEmployee(String employeeToAdd){
        if(employeeToAdd == null || employeeToAdd == "") {return false;}
        assignedEmployeeList.add(employeeToAdd);
        return true;
    }

    public boolean removeEmployee(String employeeToRemove){
    	if(employeeToRemove == null || employeeToRemove == "") {return false;}
    	if(assignedEmployeeSet.contains(employeeToRemove)) {
    		assignedEmployeeList.remove(employeeToRemove);
    		return true;
    	} else {
    		return false;
    	}	
    }
	
}
