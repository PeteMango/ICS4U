/**
 * @Name: Peter Wang
 * @Teacher: Mr.Anandarajan
 * @Date: 19 December, 2019
 * @Purpose: Test the Animal Class
 */
public class Animal_Main {
    public static void main (String args[]) { // Main Class

        /* Declaration of Animal Objects */
        Animal Tiger = new Animal (true, "Tiger", "Orange", 2.15, 1.43, 285, 4, 0, 0);
        Animal Lion = new Animal (true,"Lion", "Yellow", 2.33, 1.01, 314, 5, 0, 0);

        /* Tiger fights the Lion */
        Animal Winner = Tiger.fights(Lion);
        System.out.println("The winner of the fight is: " + Winner.getType());

        /* Kills the tiger */
        Tiger.die();
        System.out.println("The color of the tiger after it dies: " + Tiger.getColor());

        /* Move the Tiger up by 5 units and right by 7 units */
        Tiger.move(5, 7);
        System.out.println("The Tiger is currently at (" + Tiger.getLocationX() + ", " + Tiger.getLocationY() + ")");

        /* Increase the age of the lion by one */
        System.out.println("The Lion's age: " + Lion.getAge() + ", Length: " + Lion.getLength() + ", Width: " + Lion.getWidth());
        Lion.grow();
        System.out.println("The Lion's age after growing: " + Lion.getAge() + ", Length: " + Lion.getLength() + ", Width: " + Lion.getWidth());

        /* Lion mate with the tiger */
        Tiger.setType("Tiger");
        Lion.mate(Tiger);
        System.out.println("The mate of the lion is: " + Lion.getMate().getType());
    }
}
