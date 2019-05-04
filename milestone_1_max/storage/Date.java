
package storage;
/**
 * date
 */
public class Date {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void printDate(){
        System.out.println("YYYY/MM/DD : " + year + "/" + month + "/" + day );
    }
    public boolean compareDate(Date d){ // if : else also no variable because ma memory
        if( ! ( this.day == d.day ) ) return false;
        if( ! ( this.month == d.month ) ) return false;
        if( ! ( this.year == d.year ) ) return false;
        return true;
    }
    public boolean isEarlierDate(Date d){
        if ( ! (this.day > d.day ) ) return false;
        if ( ! (this.month > d.month ) ) return false;
        if ( ! (this.year > d.year ) ) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println("this is not the class you are looking for, move along");        
    }
}