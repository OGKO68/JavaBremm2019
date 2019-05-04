

import java.util.Scanner;
import java.time.LocalDate;
import storage.*;

public class datecp {
    public static void main(String[] args) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
    System.out.println(dtf.format(localDate)); //2016/11/16
    
    }
    
}
