
package main;

import java.util.ArrayList;

/**
 * Mandatory:
 * -Name
 * Recommended:
 * -People working on the project
 * -Department leads of people working on the project
 * -Sum of salary of project members
 */

public class Project {
    /** name of the Project */
    private String projectName;
    /** ArrayList of Workers that Participate in the Project */
    private ArrayList<String> participatingWorkers;
    /** Singular DepartmentLead leading the project, also represents the department here*/
    private String departmentLead; 
    /** Function that returns the Salary of all employees */
    public int getSalarySum(){
        /** TODO implementation */
        return 1;
    }

}
