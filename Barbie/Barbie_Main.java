/**
 * @Name: Peter Wang
 * @Teacher: Mr. Anandarajan
 * @Date: 19 December, 2019
 * @Purpose: Test the Barbie class that extends the Toy class
 */

public class Barbie_Main {
    public static void main (String args[]) {
        Barbie SnowWhite = new Barbie ("Blone", "Snow White", "White", 25, 10.0, 20.0);

        System.out.println("The cost of the barbie is: " + SnowWhite.getCost());
    }
}
