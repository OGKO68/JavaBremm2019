
import java.util.Scanner;
import java.util.Vector;

import helpful.*;

public class vectorObjectAsObjectStorgae {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("plese enter your object amount");
        int in = sc.nextInt();
        Vector objVector = createObj(in);


        sc.close();
    }
    public static Vector createObj(int in){
        Vector objVector = new Vector<Sample>(in);
        for (int i = 0; i < objVector.size(); i++) {
            objVector.add(new Sample(i, "test"));
        }
        return objVector;
    }
}
