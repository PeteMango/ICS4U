package app;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class DeathAnimation extends ShapeAnimation{
	
	//place of death
	private double deathX;
	private double deathY;
	//place animation points to
	private double pointerX;
	private double pointerY;
		
	public DeathAnimation(double deathX,double deathY,double pointerX,double pointerY) {
		super(6,200);//speed 6, end at 200
		//initialize variables
		this.deathX = deathX;
		this.deathY = deathY;
		this.pointerX = pointerX;
		this.pointerY = pointerY;
	}
	
	public ArrayList<Node> nextFrameComponents(double elapsedFrames){
		ArrayList<Node> components = new ArrayList<>();//arraylist of nodes to return
		
		//green triangle goes 
		Polygon greenTriangle = new Polygon();
		greenTriangle.getPoints().addAll(new Double[]{
		    deathX-200, deathY,
		    deathX+200, deathY,
		    pointerX,pointerY
    	});
		greenTriangle.setOpacity(0.5);
		greenTriangle.setFill(Color.GREEN);
		components.add(greenTriangle);
		
		Polygon redTriangle = new Polygon();
		redTriangle.getPoints().addAll(new Double[]{
		    deathX-200, deathY,
		    deathX+20, deathY,
		    pointerX-elapsedFrames,pointerY
    	});
		redTriangle.setOpacity(0.5);
		redTriangle.setFill(Color.RED);
		components.add(redTriangle);
		
		Polygon blueTriangle = new Polygon();
		blueTriangle.getPoints().addAll(new Double[]{
		    deathX-200, deathY,
		    deathX+200, deathY,
		    pointerX+elapsedFrames,pointerY
    	});
		blueTriangle.setOpacity(0.5);
		blueTriangle.setFill(Color.BLUE);
		components.add(blueTriangle);
		
		return components;
	}
}
