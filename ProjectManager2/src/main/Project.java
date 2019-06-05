
package main;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Mandatory:
 * -Name
 * Recommended:
 * -People working on the project
 * -Department leads of people working on the project
 * -Sum of salary of project members
 */

public class Project {

    Project(){
        projectName = null;

    }

    /** name of the Project */
    private String projectName;

    /** end date of the Project */
    private Calendar projectEndDate;

    /** start date of the project */
    private Calendar projectStartDate;

    /** ArrayList of Workers that Participate in the Project */
    private ArrayList<String> participatingWorkers;

    /** Singular DepartmentLead leading the project, also represents the department here*/
    private String departmentLead; 
    
    /** Function that returns the Salary of all employees */
    public int getSalarySum(){

        // TODO implementation
        return 1;
    }

    /**
     * @param projectStartDate the projectStartDate to set new end date for the project
     */
    public boolean setProjectStartDate(Calendar projectStartDate) {
        if (this.projectEndDate == null ){
            this.projectStartDate = projectStartDate;
            return true;
        }
        if( projectStartDate.before(this.projectEndDate) ){
            this.projectStartDate = projectStartDate;
            return true;
        }else {
            return false;
        }
    }

    
    /**
     * @param projectEndDate the projectStartDate to set a new end date for the project
     */
    public boolean setProjectEndDate(Calendar projectEndDate) {
        if(this.projectStartDate.before(projectEndDate)){
            this.projectEndDate = projectEndDate;
            return true;
        } else {
            return false;
        }  
    }
    



    /**
     * @return String return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return Calendar return the projectEndDate
     */
    public Calendar getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * @return Calendar return the projectStartDate
     */
    public Calendar getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * @return ArrayList<String> return the participatingWorkers
     */
    public ArrayList<String> getParticipatingWorkers() {
        return participatingWorkers;
    }

    /**
     * @param participatingWorkers the participatingWorkers to set
     */
    public void setParticipatingWorkers(ArrayList<String> participatingWorkers) {
        this.participatingWorkers = participatingWorkers;
    }

    /**
     * @return String return the departmentLead
     */
    public String getDepartmentLead() {
        return departmentLead;
    }

    /**
     * @param departmentLead the departmentLead to set
     */
    public void setDepartmentLead(String departmentLead) {
        this.departmentLead = departmentLead;
    }

}
