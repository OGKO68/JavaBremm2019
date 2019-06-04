package main;

import java.util.ArrayList;

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
    public ArrayList<Employee> assignedEmployees;

    /** returns List of all Projects subordinates work on */
    public ArrayList<String> allEmployeeProjects(){
        /** @TODO implementation */
        ArrayList<String> returnArrayList =  new ArrayList<String>();
        return returnArrayList;
    }

    /** Calculates the Salarie of all subordinates Employees */
    public int employeesSalaries(){
        /** TODO implementation */
        return 1;
    }
	
}

/*
- What happens with lead changes will
- Where to place an arrayList with all Employees
- Can we use HashSets for easier checking 
*/
