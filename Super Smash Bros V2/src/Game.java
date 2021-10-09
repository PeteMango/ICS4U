package app;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Node;

public class Game {
	private Character[] characters;
	private MoveSet[] keyBindings;
	private Floor[] platforms;
	private Scene scene;
	private double maxX;
	private double minX;
	private double maxY;
	private double minY;
	private Color[] colors = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.DEEPPINK,Color.BROWN};
	
	public Game(Character[] characters, MoveSet[] keyBindings, Scene scene, Floor[] platforms) {
		this.characters = characters;
		this.keyBindings = keyBindings;
		this.scene = scene;
		this.platforms = platforms;
		
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	
            	KeyCode code = event.getCode();
            	//System.out.println(keyBindings[0].getBasic()+" "+keyBindings[1].getBasic());
            	for(int i=0;i<keyBindings.length;i++) {
            		for(int v=0;v<keyBindings[i].getMoves().length;v++) {
            			if(keyBindings[i].getMoves()[v] == code) {
            				characters[i].press(v);
            				//System.out.println("found! "+v);
            			}
            			else if(keyBindings[i].getBasic() == code) {
            				//System.out.println("pressing basic"); 
            				characters[i].press(4);
            			}
            			else if(keyBindings[i].getBasic() == code) {
            				//System.out.println("pressing special"); 
            				characters[i].press(5);
            			}
            		}
            	}
            }
        });
        
        this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	KeyCode code = event.getCode();
            	//System.out.println(code);
            	for(int i=0;i<keyBindings.length;i++) {
            		for(int v=0;v<keyBindings[i].getMoves().length;v++) {
            			if(keyBindings[i].getMoves()[v] == code) {
            				//System.out.println("letting go of "+code+";"+v);
            				characters[i].letGo(v);
            			}
            			else if(keyBindings[i].getBasic() == code) {
            				//System.out.println("letting go of basic"); 
            				characters[i].letGo(4);
            			}
            			else if(keyBindings[i].getBasic() == code) {
            				//System.out.println("letting go of special"); 
            				characters[i].letGo(5);
            			}
            		}
            	}
            }
        });
	}
	
	boolean bug;
	
	public void setBounds(double maxX,double minX,double maxY,double minY) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
	}
	
	public static boolean checkGrounding(Character character, Floor[] floors) {
		for(HitBox defense:character.getDefenseBoxes()) {
			for(Floor floor:floors) {
    			if(floor.collidesWith(defense)) {
    				return true;
    			}
			}
		}
		return false;
	}
	
	private ArrayList<ShapeAnimation> animations= new ArrayList<>();
	
	public ArrayList<Node> nextFrame(){
		ArrayList<Node> components = new ArrayList<>();
		
		int iterator = 0;
		
		//create box to show characters' locations on the map at all times (mini map)
		double scaleFactor = 15.0;
		double Xmodifier = minX*-1;
		double Ymodifier = minY*-1;
		Line topBounds = new Line(5,5,(maxX+Xmodifier)/scaleFactor+5,5);
		Line botBounds = new Line(5,(maxY+Ymodifier)/scaleFactor+5,(maxX+Xmodifier)/scaleFactor+5,(maxY+Ymodifier)/scaleFactor+5);
		Line leftBounds = new Line(5,5,5,(maxY+Ymodifier)/scaleFactor+5);
		Line rightBounds = new Line((maxX+Xmodifier)/scaleFactor+5,5,(maxX+Xmodifier)/scaleFactor+5,(maxY+Ymodifier)/scaleFactor+5);
		
		for(Floor floor : platforms) {
			Line miniFloor = new Line(
				(floor.getX()+Xmodifier)/scaleFactor,(floor.getY()+Ymodifier)/scaleFactor,
				(floor.getX()+Xmodifier+floor.getWidth())/scaleFactor,(floor.getY()+Ymodifier)/scaleFactor
			);
			components.add(miniFloor);
		}
		
		components.add(topBounds);
		components.add(botBounds);
		components.add(leftBounds);
		components.add(rightBounds);
		
		for(Character character:characters) {
			
			if(!character.isAlive()) {
				continue;
			}
			
			ImageView showCharacter = new ImageView(character.exist());
			showCharacter.relocate(character.getX(),character.getY());
			showCharacter.setFitHeight(character.getHeight());
			showCharacter.setFitWidth(character.getWidth());
			
			if(!character.isGrounded()) {
				if(checkGrounding(character,platforms)) {
        			character.ground();
        		}
    		}
    		else {
    			boolean stillGrounded = checkGrounding(character,platforms);

    			if(!stillGrounded) {
    				character.unGround();
    			}
    		}
			
			if(character.isAttacking()) {
				for(Character target:characters) {
					if(character == target) {
						continue;
					}
					else {
						if(character.isHitting(target)) {
							target.hit(character.getCurrentAttack());
						}
					}
				}
			}
			
			if(character.getX()>maxX || character.getX()<minX || character.getY()>maxY || character.getY()<minY) {
				//register death
				animations.add(new DeathAnimation(character.getX(),character.getY(),scene.getWidth()-character.getX(),scene.getHeight()-character.getY()));
				character.kill();
				//respawn
				if(character.isAlive()) {
					character.ChangeLocation((minX+maxX)/2.0, minY+300.0);
				}
			}
			
			//large if-else chain to create off screen indicators for the characters
			double radius = Math.sqrt(2)*50;
			//passed on right
			if(character.getX()>scene.getWidth()) {
				//top right corner
				if(character.getY()<0) {
					double movement = 25.0;
					Circle circle = new Circle(scene.getWidth()-50-movement,50+movement,radius);
					showCharacter.relocate(scene.getWidth()-100-movement,+movement);
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    scene.getWidth()-50-movement, 25.0+movement,
					    scene.getWidth()-25.0-movement, 50.0+movement,
					    scene.getWidth(), 0.0
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
				//bottom right corner
				else if(character.getY()>scene.getHeight()-100){
					double movement = 25.0;
					Circle circle = new Circle(scene.getWidth()-50-movement,scene.getHeight()-50-movement,radius);
					showCharacter.relocate(scene.getWidth()-100-movement,scene.getHeight()-100-movement);
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    scene.getWidth()-50-movement, scene.getHeight()-25-movement,
					    scene.getWidth()-25.0-movement, scene.getHeight()-50-movement,
					    scene.getWidth(), scene.getHeight()
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
				//on right side
				else {
					Circle circle = new Circle(scene.getWidth()-100,character.getY()+50,radius);
					showCharacter.relocate(scene.getWidth()-150,character.getY());
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    scene.getWidth()-50, character.getY()+25,
					    scene.getWidth()-50, character.getY()+75,
					    scene.getWidth(), character.getY()+50
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
			}
			//passed on left
			else if(character.getX()<-100.0) {
				//top left
				if(character.getY()<0) {
					double movement = 25.0;
					Circle circle = new Circle(movement+50,50+movement,radius);
					showCharacter.relocate(movement,movement);
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    50+movement, 25.0+movement,
					    25.0+movement, 50.0+movement,
					    0.0, 0.0
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
				//bottom left
				else if(character.getY()>scene.getHeight()-100){
					double movement = 25.0;
					Circle circle = new Circle(50+movement,scene.getHeight()-50-movement,radius);
					showCharacter.relocate(movement,scene.getHeight()-100-movement);
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    50+movement, scene.getHeight()-25-movement,
					    25.0+movement, scene.getHeight()-50-movement,
					    0.0, scene.getHeight()
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
				//left 
				else {
					Circle circle = new Circle(100,character.getY()+50,radius);
					showCharacter.relocate(50,character.getY());
					Polygon arrow = new Polygon();
					arrow.getPoints().addAll(new Double[]{
					    50.0, character.getY()+25,
					    50.0, character.getY()+75,
					    0.0, character.getY()+50
			    	});
					arrow.setOpacity(0.5);
					arrow.setFill(colors[iterator]);
					circle.setOpacity(0.5);
					circle.setFill(colors[iterator]);
					components.add(arrow);
					components.add(circle);
				}
			}
			//passed on bottom
			else if(character.getY()>scene.getHeight()-100){
				Circle circle = new Circle(character.getX()+50,scene.getHeight()-100,radius);
				showCharacter.relocate(character.getX(),scene.getHeight()-150);
				Polygon arrow = new Polygon();
				arrow.getPoints().addAll(new Double[]{
					character.getX()+25, scene.getHeight()-50, 
					character.getX()+75, scene.getHeight()-50, 
					character.getX()+50 ,scene.getHeight()
		    	});
				arrow.setOpacity(0.5);
				arrow.setFill(colors[iterator]);
				circle.setOpacity(0.5);
				circle.setFill(colors[iterator]);
				components.add(arrow);
				components.add(circle);
			}
			//passed on top
			else if(character.getY()<-100) {
				Circle circle = new Circle(character.getX()+50,100,radius);
				showCharacter.relocate(character.getX(),50);
				Polygon arrow = new Polygon();
				arrow.getPoints().addAll(new Double[]{
					character.getX()+25, 50.0,
					character.getX()+75, 50.0,
					character.getX()+50, 0.0 
		    	});
				arrow.setOpacity(0.5);
				arrow.setFill(colors[iterator]);
				circle.setOpacity(0.5);
				circle.setFill(colors[iterator]);
				components.add(arrow);
				components.add(circle);
			}
			
			//add the current character to the minimap
			Circle minimapIndicator = new Circle((character.getX()+50+Xmodifier)/scaleFactor,(character.getY()+50+Ymodifier)/scaleFactor,2);
			minimapIndicator.setFill(colors[iterator]);
			components.add(minimapIndicator);
			
			String displayText = character.getName()+" ";
			for(int i=0;i<character.getLives();i++) {
				displayText+="\u2764";
			}
			displayText+=" \n"+(int)(character.getLaunchFactor()*100.0)/10.0;
			displayText+="% Launch Factor";
			Text infoBar= new Text(displayText);
			infoBar.setFont(new Font(14));
			infoBar.setFill(colors[iterator]);
			infoBar.relocate(character.getX(), character.getY()-40);
			
			displayText = displayText.replace("\n", "");
			Text mainInfoBar= new Text(displayText);
			mainInfoBar.setFont(new Font(15));
			mainInfoBar.setFill(colors[iterator]);
			mainInfoBar.relocate(5,(maxY+Ymodifier)/scaleFactor+5+10+20*iterator);
			
			components.add(infoBar);
			components.add(showCharacter);
			components.add(mainInfoBar);
			//show hitboxes during debugging
			bug  = false;
			if(bug) {
				for(HitBox box:character.getDefenseBoxes()) {
                	Rectangle displaybox = box.getBox();
                	displaybox.setFill(Color.BLUE);
                	displaybox.setOpacity(0.5);
                	components.add(displaybox);
                }
				
				for(HitBox box:character.getAttackBoxes()) {
                	Rectangle displaybox = box.getBox();
                	displaybox.setFill(Color.RED);
                	displaybox.setOpacity(0.5);
                	components.add(displaybox);
                }
			}
			
			iterator++;
		}
		
		ArrayList<ShapeAnimation> removalAnimations = new ArrayList<>();
		
		for(ShapeAnimation animation:animations) {
			ArrayList<Node> animationComponents = animation.nextFrame();
			if(animationComponents != null) {
				for(Node component:animationComponents) {
					components.add(component);
				}
			}
			else {
				removalAnimations.add(animation);
			}
		}
		for(ShapeAnimation animation:removalAnimations) {
			animations.remove(animation);
		}
		
		for(Floor floor:platforms) {
			components.add(floor.getBox());
		}
		
		return components;
	}
	
}
