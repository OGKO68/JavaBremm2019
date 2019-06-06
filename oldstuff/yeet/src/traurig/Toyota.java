package traurig;

public class Toyota extends Car {
	
	private Integer doors;
	
	public Toyota() {
		this.setMarke("Toyota");
		this.setYear(null);
		this.setDoors(null);
	}
	
	public Toyota(int year, int doors) {
		this.setMarke("Toyota");
		this.setYear(year);
		this.setDoors(doors);
	}
		

    /**
     * @return Integer return the doors
     */
    public Integer getDoors() {
        return doors;
    }

    /**
     * @param doors the doors to set
     */
    public void setDoors(Integer doors) {
        this.doors = doors;
    }

}
