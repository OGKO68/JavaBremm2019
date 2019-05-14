package Back;

public class Project {
    private int id;
    private String pName;
    private Date pStartDate;
    private Date pEndDate;
    
    public Project(){
        //nothing eh?
        //I hope it works
    }
    public Project(int id, String pName, Date pStartDate, Date pEndDate){
        this.id = id;
        this.pName = pName;
        this.pStartDate = pStartDate;
        this.pEndDate = pEndDate;
    }
    
    public void printProject(){
        System.out.println( pName + " | " + pStartDate.getDate() + " | " + pEndDate.getDate() + " | " + id );
    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the pName
     */
    public String getPName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setPName(String pName) {
        this.pName = pName;
    }

    /**
     * @return Date return the pStartDate
     */
    public Date getPStartDate() {
        return pStartDate;
    }

    /**
     * @param pStartDate the pStartDate to set
     */
    public void setPStartDate(Date pStartDate) {
        this.pStartDate = pStartDate;
    }

    /**
     * @return Date return the pEndDate
     */
    public Date getPEndDate() {
        return pEndDate;
    }

    /**
     * @param pEndDate the pEndDate to set
     */
    public void setPEndDate(Date pEndDate) {
        this.pEndDate = pEndDate;
    }

    public static void main(String[] args) {
        System.out.println("this is not the class you are looking for, move along");
    }
}
