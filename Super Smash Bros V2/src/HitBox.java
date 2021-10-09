package app;


import javafx.scene.shape.Rectangle;

public class HitBox {
	
	//state variables
	//self explanatory
	double Xcoordinate;
	double Ycoordinate;
	//the hitbox's top left corner, relative to the X and Y coordinates
	//hitboxes length and width
	private double length;
	private double width;
	
	/**
	 * @function make a hitbox
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param Xrelativity the hitbox's top left corner's x, relative to the X coordinate
	 * @param Yrelativity the hitbox's top left corner's y, relative to the Y coordinate
	 * @param Length Length of the hitbox
	 * @param Width width of the hitbox
	 */
	public HitBox(double x, double y,double length,double width) {
		this.length = length;
		this.width = width;
		Ycoordinate = y;
		Xcoordinate = x;
	}
	
	/**
	 * @function teleport the hitbox to somewhere else
	 * @param x new x
	 * @param y new y
	 */
	public void changeLocation(double x, double y) {
		Xcoordinate=x;
		Ycoordinate=y;
	}
	
	/**
	 * 
	 * @return the width of this hitbox
	 */
	public double getHeight() {
		return width;
	}
	
	/**
	 * 
	 * @return the height of this hitbox
	 */
	public double getWidth() {
		return length;
	}
	
	/**
	 * 
	 * @return the Y coordinate of this hitbox
	 */
	public double getY() {
		return Ycoordinate;
	}
	
	/**
	 * 
	 * @return the X coordinate of this hitbox
	 */
	public double getX() {
		return Xcoordinate;
	}
	
	/**
	 * @return return a rectangle that represents the hitbox
	 */
	public Rectangle getBox() {
		return new Rectangle(Xcoordinate,Ycoordinate,length,width);
	}
	
	/**
	 * Collision detection
	 * @param otherBox the box to check collision of this box with
	 * @return whether or not the 2 hitboxes collide
	 */
	public boolean collidesWith(HitBox otherBox) {
		//Check collision of X coordinates
		double thisXStart = Xcoordinate;
		double thisXEnd = thisXStart+length;
		double otherXStart = otherBox.Xcoordinate;
		double otherXEnd = otherXStart+otherBox.length;
		boolean Xoverlap = (thisXStart <= otherXEnd) && (thisXEnd >= otherXStart);
		//Check collision of Y coordinates
		double thisYStart = Ycoordinate;
		double thisYEnd = thisYStart+width;
		double otherYStart = otherBox.Ycoordinate;
		double otherYEnd = otherYStart+otherBox.width;
		boolean Yoverlap = (thisYStart <= otherYEnd) && (thisYEnd >= otherYStart);
		//if both coordinates collide, there is a collision
		return Xoverlap && Yoverlap;
	}
	
	public boolean isAbove(HitBox otherbox) {
		return true;
	}

}
