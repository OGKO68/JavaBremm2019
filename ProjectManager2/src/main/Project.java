
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
    	
    	//seting up a strict calendat object
        this.projectStartDate.setLenient(false);
        
        //actually setting the date
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
            } else { return false; }
        
        // if there was an eception the date must be wrong
        } catch(Exception e){
            return false;
        }
    }

    
    /**
     * @param projectEndDate the projectStartDate to set a new end date for the project
     */
    public boolean setProjectEndDate(Calendar projectEndDate) throws Exception{

    	//seting up a strict calendat object
    	this.projectStartDate.setLenient(false);
        
    	// if the start date is null we'll set the end date anyway
    	if (this.projectStartDate == null ){
            this.projectEndDate = projectEndDate;
            return true;
        }
    	
    	//if the start date is before the end date that would be correct
        try{
            if(this.projectStartDate.before(projectEndDate)){
                this.projectEndDate = projectEndDate;
                return true;
            } else { return false; }
            
        //if ther was an exception the input mus have been wrong
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
    	HashSet<String> localizedProjectNameHashSet = projectNameHashSet;
    	// if the project name is null, empty or a space it's wrong
        if(projectName == null || projectName == "" || projectName == " ") return false;
        
        //if the name is an the hashset it should be false
        if( localizedProjectNameHashSet.contains( projectName ) ){
        	 return false;
        }
        
        // if everything is good so far than ther project should name should be fine and return 
        this.projectName = projectName;
        return true;
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
