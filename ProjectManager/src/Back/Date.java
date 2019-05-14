package Back;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int year, int month, int day){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(){
        this.day = 1;
        this.month = 1;
        this.year = 1970; 
    }

    public void printDate(){
        System.out.println(year + "/" + month + "/" + day );
    }
   
    public String getDate(){
        return(year + "/" + month + "/" + day);
    }
   
    public boolean compareDate(Date d){ // if : else also no variable because ma memory
        if( ! ( this.day == d.day ) ) return false;
        if( ! ( this.month == d.month ) ) return false;
        if( ! ( this.year == d.year ) ) return false;
        return true;
    }

    public boolean isEarlierDate(Date d){
        if( this.year < d.year ) return true;
        else if ( this.year == d.year ){
            if ( this.month < d.month ) return true;
            else if ( this.month == d.month ){
                if(this.day < d.day) return true;
                else return false;
            } else return false;
        } else return false;

        //if (this. year <= d.year && this.month <= d.month && this.day < d.day ) return false;
        //else return true;
    }
  
    public boolean isEqualEarlierDate(Date d){
        if( this.year < d.year ) return true;
        else if ( this.year == d.year ){
            if ( this.month < d.month ) return true;
            else if ( this.month == d.month ){
                if(this.day < d.day) return true;
                else if (this.day == d.day ) return true;
                else return false;
            } else return false;
        } else return false;
    }

    /**
     * @return int return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return int return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return int return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    public static void main(String[] args) {
        System.out.println("this is not the class you are looking for, move along");        
    }

}



