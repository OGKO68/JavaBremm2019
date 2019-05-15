
package helpful;
public class Sample{
    // most common datatypes for sampling reasons
    private int number;
    private String text; 
    
    public Sample(int i, String s){ // sample constructor
        number = i; // set number var of obj to var i from constructor call
        text = s; // set String var of obj to var s from constuctor call
    }
    
    //autogen setter and getter 

    /**
     * @return int return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public void outSampleStats(){
        System.out.println("the name is " + text + " and the number " + number + " .");
    }
    public static void main(String[] args) {
        System.out.println("nothing to see here, move along");
    }
}