import javax.xml.stream.Location;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Name: Peter
 * @Teacher: Mr. Anandarajan
 * @Date: 19 December 2019
 * @Purpose: Creates Animal Class
 */

public class Animal {
    /**
     * Animal Class
     * Attributes: Type (String), Color (String), Length (double), Width (double), Wight (double), Age (int), LocationX (int), LocationY (int), mate (Animal)
     * Actions: Fight (Animal Opponent), Move (int, int), Grow (void), Mate (Animal Victim), Die (void) - Resets the animal
     */
    private String type, color;
    private double length, width, weight;
    private int age, LocationX, LocationY;
    private Animal mate;
    private boolean alive;

    /* Declaration of instance variables within the object */
    public Animal (boolean defaultAlive, String defaultType, String defaultColor, double defaultLength, double defaultWidth, double defaultWeight, int defaultAge, int defaultLocationX, int defaultLocationY){
        alive = defaultAlive;
        type = defaultType;
        color = defaultColor;
        length = defaultLength;
        width = defaultWidth;
        weight = defaultWeight;
        age = defaultAge;
        LocationX = defaultLocationX;
        LocationY = defaultLocationY;
    }
    public Animal () {
        alive = true;
        type = "";
        color = "";
        length = 0.00;
        width = 0.00;
        age = 0;
        LocationX = 0;
        LocationY = 0;
    }

    /* Getter Methods */
    public String getType () { return type; } /* Gets the type */
    public String getColor () { return color; } /* Gets the color */
    public double getLength () { return length; } /* Gets the length */
    public double getWidth () { return width; } /* Gets the width */
    public double getWeight () { return weight; } /* Gets the weight */
    public int getAge () { return age; } /* Gets the age */
    public int getLocationX () { return LocationX; } /* Gets the x-coordinate */
    public int getLocationY () { return LocationY; } /* Get the y-coordinate*/
    public Animal getMate () { return mate; } /* Get the mate */
    public boolean getAlive () { return alive; } /* Get the alive boolean */

    /* Setter Methods */
    public void setType (String newType) { type = newType; } /* Changes the type */
    public void setColor (String newColor) { color = newColor; } /* Changes the color */
    public void setLength (double newLength) { length = newLength; } /* Changes the length */
    public void setWidth (double newWidth) { width = newWidth; } /* Changes the width */
    public void setWeight (double newWeight) { weight = newWeight; } /* Changes the weight */
    public void setAge (int newAge) { age = newAge; } /* Changes the age */
    public void setLocationX (int newLocationX) { LocationX = newLocationX; } /* Changes the x-coordinate */
    public void setLocationY (int newLocationY) { LocationY = newLocationY; } /* Changes the y-coordinate */

    /* Other Methods */
    public Animal fights (Animal Opponent) { /* Function which returns the winner between two animals */
        if(length * width > Opponent.getLength() * Opponent.getWidth()){
            return this;
        }
        return Opponent;
    }
    public void mate (Animal Lover) { /* Set Animal B to be the mate of Animal A */
         Lover.mate = this;
        mate = Lover;
    }
    public void move (int xValue, int yValue) { /* Moves the x-coordinates by xValue and y-coordinates by yValue */
        LocationX += xValue;
        LocationY += yValue;
    }
    public void grow () { /* Increase the age by 1 - length, width and weight by 1.13 times the previous value */
        age++;
        length = Math.round((length * 1.13) * 100.0) / 100.0;
        width = Math.round((width * 1.13) * 100.0) / 100.0;
        weight = Math.round((weight * 1.13) * 100.0) / 100.0;
    }
    public void die () {  /* Kill the Animal and reset all features */
        alive = false;
        type = "null";
        color = "null";
        length = 0.00;
        width = 0.00;
        age = 0;
        LocationX = 0;
        LocationY = 0;
    }
}