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

    public DepartmentLead(){
        
        this.setEmployeeName2();
        this.setEmployeeDepartmentLead2();
        this.setEmployeeSalary2();
        this.setEmployeeProjectList2();
        
        this.assignedEmployees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employeeToAdd){
        assignedEmployees.add(employeeToAdd);
    }
    public void removeEmployee(Employee employeeToRemove){
        assignedEmployees.remove(employeeToRemove);
    }

    /** returns List of all Projects subordinates work on */
    public ArrayList<String> allEmployeeProjects(){
        ArrayList<String> returnArrayList =  new ArrayList<String>();

        for (int i = 0; i < assignedEmployees.size(); i++) {
            returnArrayList.add(assignedEmployees.get(i).getEmployeeName());
        }
        
        return returnArrayList;
    }

    /** Calculates the Salarie of all subordinates Employees */
    public int employeesSalaries(){
        int salary = 0;
        
        for (int i = 0; i < assignedEmployees.size(); i++) {
            salary = salary + assignedEmployees.get(i).getEmployeeSalary();
        }

        return salary;
    }
	
}

/* QUESTIONS
- What happens with lead changes will
	-> no promotion no demotion. no rank up
- Where to place an arrayList with all Employees
	-> araylist hashmap in api class
- Can we use HashSets for easier checking
	-> ok 
- Do I really need a create an object to inact method not written in the same file
	-> Static method, ClassName.staticfunction no object to create
- if there is no main how to save the Employees Arraylist and the Department lead ArrayList
	-> 
	
Question 2
only one file?

save arrayList of Employees
*/
