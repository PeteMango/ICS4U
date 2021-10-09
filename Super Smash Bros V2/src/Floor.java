package app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//floors are just glorified hitboxes lmao
public class Floor extends HitBox{	
	/**
	 * @function create a new floor based on specs
	 * @param length length of floor
	 * @param x x of floor
	 * @param y y of floor
	 */
	public Floor(double length, double x, double y) {
		super(x, y, length, 10);
	}
	
	/**
	 * @function get something to draw to screen
	 * @return the rectangle node
	 */
	@Override
	public Rectangle getBox() {
		Rectangle floorBox = super.getBox();
		//display brown box of thickness 10
		floorBox.setFill(Color.BROWN);
		return floorBox;
	}
	
	/**
	 * @function check collision, but only return true if our object is above the line
	 * @param otherBox hit box to collide with
	 * @return whether or not it collided
	 */
	@Override
	public boolean collidesWith(HitBox otherBox) {
		
		if(otherBox.getY()+otherBox.getHeight()>getY()+getHeight()) {
			return false;
		}
		
		return super.collidesWith(otherBox);
	}
}
