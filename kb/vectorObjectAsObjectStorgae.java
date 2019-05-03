
import java.util.Scanner;
import java.util.Vector;

import helpful.*;

public class vectorObjectAsObjectStorgae {

    public static void main(String[] args) {
        System.out.println("plese enter your object amount");
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
        sc.close();
        Vector objVector = createObj(in);
        // try and see the objects yourself
        // preferably use a lower number so you see the diffrence faster
        objPrint(objVector);
        // deleting one object from the vector to check if we can use this approach for milestone 1
        objVector.remove(0);
        System.out.println("-----");
        // print again to see the results
        objPrint(objVector);
    }
    public static Vector createObj(int in){
        Vector objVector = new Vector<Sample>();
        for (int i = 0; i < in; i++) {
            objVector.add(new Sample(i, "test"));
        }
        return objVector;
    }
    public static void objPrint(Vector<Sample> vec) {
        for (int i = 0; i < vec.size(); i++) {
            System.out.println("the name is " + vec.elementAt(i).getText() + " and the number " + vec.elementAt(i).getNumber() + " .");
        }
        System.out.println("Vec size " + vec.size() );
    }
}
