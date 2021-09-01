/**
 * @Name: Peter Wang
 * @Teacher: Mr. Anandarajan
 * @Date: 19 December, 2019
 * @Purpose: Barbie class
 */
public class Barbie extends Toy {
    private String hairColor, Name, skinTone;
    private int age;

    /* Declaration of instance variables */
    public Barbie () {
        hairColor = "";
        skinTone = "";
        Name = "";
        age = 0;

    }
    public Barbie (String newHairColor, String newName, String newSkinTone, int newAge, double newCost, double newSellingPrice) {
        super(newName, newCost, newSellingPrice);
        hairColor = newHairColor;
        Name = newName;
        skinTone = newSkinTone;
        age = newAge;
    }

    /* Getter methods */
    public String getHairColor () { return hairColor; } /* Gets the hair color */
    public String getName () { return Name; } /* Gets the name */
    public String skinTone () { return skinTone; } /* Gets the skin tone */
    public int getAge () { return age; } /* Gets the age */

    /* Setter methods */
    public void setHairColor (String newHairColor) { hairColor = newHairColor; } /* Sets the hair color */
    public void setName (String newName) { Name = newName; } /* Sets the name */
    public void setSkinTone (String newSkinTone) { skinTone = newSkinTone; } /* Sets the skin tone */
    public void setAge (int newAge) { age = newAge; } /* Sets the age */
}
