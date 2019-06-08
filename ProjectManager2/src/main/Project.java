
package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

/**
 * Mandatory:
 * -Name
 * Recommended:
 * -People working on the project
 * -Department leads of people working on the project
 * -Sum of salary of project members
 */

public class Project {

    public Project(){
        this.projectName = null;
        this.projectStartDate = null;
        this.projectEndDate = null;
        this.participatingWorkers = null;
        this.departmentLead = null;
    }
    /** name of the Project */
    private String projectName;

    /** start date of the project */
    private Calendar projectStartDate;

    /** end date of the Project */
    private Calendar projectEndDate;

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
    public boolean setProjectStartDate(Calendar projectStartDate) throws Exception {
        this.projectStartDate.setLenient(false);
        try{
            //null means this is a new project so we do not need to check
            if (this.projectEndDate == null ){
                this.projectStartDate = projectStartDate;
                return true;
            }
            // if the the endDate is before the start tana there is a problem dealt with in else 
            if( projectStartDate.before(this.projectEndDate) ){
                this.projectStartDate = projectStartDate;
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
            return false;
        }
    }

    
    /**
     * @param projectEndDate the projectStartDate to set a new end date for the project
     */
    public boolean setProjectEndDate(Calendar projectEndDate) throws Exception{
        this.projectStartDate.setLenient(false);
        try{
            if(this.projectStartDate.before(projectEndDate)){
                this.projectEndDate = projectEndDate;
                return true;
            } else {
                return false;
            }
        } catch(Exception e){
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
    public boolean setProjectName(String projectName, HashSet<String> projectNameHashSet){
        if(projectName == null || projectName == "" || projectName == " ") return false;
        if( projectNameHashSet.contains( projectName )) return false;
        this.projectName = projectName;
        return false;
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
        //TODO
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
        //TODO
        this.departmentLead = departmentLead;
    }

}
