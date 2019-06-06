package traurig;

public class Car {
	
	private String marke;
	private Integer year;
	
	public Car(){
		this.setMarke(null);
		this.setYear(null);
	}
	
	public Car(String marke, int year){
		this.setMarke(marke);
		this.setYear(year);
	}

    /**
     * @return String return the marke
     */
    public String getMarke() {
        return marke;
    }

    /**
     * @param marke the marke to set
     */
    public void setMarke(String marke) {
        this.marke = marke;
    }

    /**
     * @return Integer return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

}
