import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javafx.scene.image.Image;

//superclass for all the different characters we are going to introduce
//abstract because all characters have a second set of completely different abilities
public abstract class Character{
	
	private int lives = 3;
	
	//vectors represent movement
	private double VectorX = 0.0;
	private double VectorY = 0.0;
	
	//animation we are playing rn
	private Animation currentAnimation;
	
	//name of character
	private String name;
	
	//movement variables
	private int remainingJumps = 0;//jump counter
	private boolean grounded = false;//whether or not we are on ground
	private boolean moving = false;
	private boolean movementBlocked = false;
	
	//damage and launch multiplier
	private double LaunchFactor = 0.0;
	
	//location
	private double Xcoordinate;
	private double Ycoordinate;
	
	//character attributes
	private double weight;
	private double speed;
	private double damage;
	private double height;
	private double width;
	
	//hitboxes for attacking and collision detection
	private ArrayList<HitBox> DefenseBoxes = new ArrayList<>();
	private ArrayList<HitBox> AttackBoxes = new ArrayList<>();
	
	//the basic attacks, shared effects among all characters
	private Attack[] attacks = new Attack[4];
	
	//abstract methods for the special abilities that vary wildly from character to character!
	abstract void leftSpecial();
	abstract void rightSpecial();
	abstract void downSpecial();
	abstract void upSpecial();
	abstract void specialFunctions();
	
	//attack variables
	private Attack currentAttack;
	boolean attacking = false;
	
	//animations
	private final Animation[] resting = new Animation[2];
	private Animation[] falling = new Animation[2];
	private Animation[] smashed = new Animation[4];
	private Animation[] run = new Animation[2];
	private Animation hitGround;
	private Animation[] jumping = new Animation[2];
	//left or right? (0 or 1)
	private int direction = 1;
	//keyboard input
	private boolean pressed[] = new boolean[4]; 
	private boolean pressedBasic = false;
	private boolean pressedSpecial = false;
	//is he dead?
	private boolean alive = true;
	
	/**
	 * 
	 * @return the damage multiplier the character currently has
	 */
	public double getLaunchFactor() {
		return LaunchFactor;
	}
	
	/**
	 * 
	 * @param name name to change character to
	 */
	public void setName(String name) {
		this.name = name; 
	}
	
	/**
	 * 
	 * @return name of character
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return remaining lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * 
	 * @return whether or not he is dead
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * @function reset parts of character upon death
	 */
	public void kill() {
		LaunchFactor = 0.0;
		VectorX = 0.0;
		VectorY = 0.0;
		lives--;
		if(lives<1) {
			alive = false;
		}
	}
	
	/**
	 * 
	 * @return array of basic attacks fro this character
	 */
	public Attack[] getBasicAttacks() {
		return attacks;
	}
	
	/**
	 * 
	 * @return whether or not character is currently attacking
	 */
	public boolean isAttacking() {
		return attacking;
	}
	
	/**
	 * 
	 * @return current attack, if attacking (otherwise null)
	 */
	public Attack getCurrentAttack() {
		return currentAttack;
	}
	
	/**
	 * @function teleport character somewhere else
	 * @param x new x
	 * @param y new y	
	 */
	public void ChangeLocation(double x, double y) {
		Xcoordinate = x;
		Ycoordinate = y;
	}
	
	/**
	 * @function create a character based on attributes, and initialize attacks
	 * @param name
	 * @param x
	 * @param y
	 * @param weight
	 * @param damage
	 * @param speed
	 * @param width
	 * @param height
	 */
	public Character(String name, double x, double y,double weight, double damage, double speed, double width, double height) {
		this.name = name;
		Xcoordinate = x;
		Ycoordinate = y;
		this.weight = weight;
		this.damage = damage;
		this.speed = speed;
		this.width = width;
		this.height = height;
		
		//create attacks based on damage given
		Attack leftAtk = new Attack();
		leftAtk.setDamage(damage);
		leftAtk.setDirection(0);
		attacks[0] = leftAtk;
		
		Attack rightAtk = new Attack();
		rightAtk.setDamage(damage);
		rightAtk.setDirection(1);
		attacks[1] = rightAtk;
		
		Attack upAtk = new Attack();
		upAtk.setDamage(damage/2);
		upAtk.setDirection(2);
		attacks[2] = upAtk;
		
		Attack downAtk = new Attack();
		downAtk.setDamage(damage*2);
		downAtk.setDirection(3);
		attacks[3] = downAtk;
	}
	
	/**
	 * @function modify an attack with a new hitbox
	 * @param attack attack to modify
	 * @param Xchange start of box relative to x coordinate
	 * @param Ychange start of box relative to y coordinate
	 * @param length box length
	 * @param width box width
	 */
	public void setAttackHitBox(Attack attack, double Xchange, double Ychange, double length, double width) {
		attack.setXchange(Xchange);
		attack.setYchange(Ychange);
		attack.addAttackBox(Xcoordinate, Ycoordinate, length, width);
	}
	
	/**
	 * 
	 * @return x coordinate of character
	 */
	public double getX() {
		return Xcoordinate;
	}
	/**
	 * 
	 * @return y coordinate of character
	 */
	public double getY() {
		return Ycoordinate;
	}
	/**
	 * 
	 * @return width of character
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * 
	 * @return height of character
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * @function intialize the basic attack animations (params self explanatory)
	 * @param restingLeft
	 * @param restingRight
	 * @param fallingLeft
	 * @param fallingRight
	 * @param runLeft
	 * @param runRight
	 * @param hitGround animation for collision for ground after flying for a while
	 * @param jumpingLeft
	 * @param jumpingRight
	 */
	public void setBasicAnimations(Animation restingLeft,Animation restingRight,Animation fallingLeft,Animation fallingRight,Animation runLeft,Animation runRight,Animation hitGround,Animation jumpingLeft,Animation jumpingRight) {
		resting[0] = restingLeft;
		resting[1] = restingRight;
		falling[0] = fallingLeft;
		falling[1] = fallingRight;
		run[0] = runLeft;
		run[1] = runRight;
		jumping[0] = jumpingLeft;
		jumping[1] = jumpingRight;
		this.hitGround = hitGround;
		currentAnimation = falling[direction]; 
	}
	
	/**
	 * @function animations to represent character getting launched back after getting hit (params self explanatory)
	 * @param smashedLeft
	 * @param smashedRight
	 * @param smashedUp
	 * @param smashedDown
	 */
	public void setSmashedAnimations(Animation smashedLeft, Animation smashedRight, Animation smashedUp, Animation smashedDown) {
		smashed[0] = smashedLeft;
		smashed[1] = smashedRight;
		smashed[2] = smashedUp;
		smashed[3] = smashedDown;
	}
	
	/**
	 * @function initialize the basic attack animations
	 * @param left left hit animation
	 * @param right right hit animation
	 * @param up up hit animation
	 * @param down hit the ground animation
	 */
	public void setAttackAnimations(Animation left, Animation right, Animation up, Animation down) {
		attacks[0].setAnimation(left);
		attacks[1].setAnimation(right);
		attacks[2].setAnimation(up);
		attacks[3].setAnimation(down);
	}
	
	/**
	 * 
	 * @return whether or not character is allowed to perform actions
	 */
	public boolean isMovementBlocked() {
		return movementBlocked;
	}
	
	/**
	 * @function add a region to register hits on character
	 * @param length length of hitbox
	 * @param width width of hitbox
	 */
	public void addDefenseBox(double length, double width) {
		DefenseBoxes.add(new HitBox(Xcoordinate,Ycoordinate,length,width));
	}
	
	/**
	 * @function Hit the character with a given attack (ouch)
	 * @param attack
	 */
	public void hit(Attack attack) {
		moving = false;//not moving anymore
		//beat him up
		double launchForce = (attack.getDamage()*LaunchFactor)-weight;//launch back based on multiplier and damage
		LaunchFactor+=attack.getDamage()/5.0;//increment damage multiplier
		//set animation to launched back
		resetAnimations();
		currentAnimation = smashed[attack.getDirection()];
		//apply launch back physics on the character
		switch(attack.getDirection()) {
			case 0:{
				System.out.println("direction 0");
				VectorY-=10;//slight punch up to prevent skidding on ground
				VectorX-=launchForce;//apply forces to the left
				// immediately remove from hitbox 
				Xcoordinate-=40;//prevent unintentional second hit
				break;
			}
			case 1:{
				System.out.println("direction 1");
				VectorY-=10;//slight punch up to prevent skidding on ground
				VectorX+=launchForce;//apply forces to right
				Xcoordinate+=40;//immediately remove from hitbox
				break;
			}
			case 2:{//straight upward force
				System.out.println("direction 2");
				VectorY-=launchForce;
				break;
			}
			case 3:{
				//straight downward force
				System.out.println("direction 3");
				VectorY+=launchForce;
				break;
			}
		}
		movementBlocked = true;
	}
	
	/**
	 * 
	 * @param otherGuy character to check collision with
	 * @return whether or not this character is attacking the other character
	 */
	public boolean isHitting(Character otherGuy) {
		for(HitBox thisHit : AttackBoxes) {//loop through this character's attacks
			for(HitBox otherHit : otherGuy.DefenseBoxes) {//check collision with other character's defenses
				if(thisHit.collidesWith(otherHit)) {
					return true;//true if detected
				}
			}
		}
		return false;//otherwise false
	}
	
	/**
	 * 
	 * @return list of defense htiboxes
	 */
	public ArrayList<HitBox> getDefenseBoxes(){
		return DefenseBoxes;
	}
	
	/**
	 * 
	 * @return list of attack hitboxes
	 */
	public ArrayList<HitBox> getAttackBoxes(){
		return AttackBoxes;
	}
	
	/**
	 * @function make this character basic attack
	 * @param direction direction to basic attack in
	 */
	public void playAttack(int direction) {
		if(!movementBlocked) {//no cancelling animations
			//set animation
			resetAnimations();		
			currentAnimation = attacks[direction].getAnimation();
			//set attack
			currentAttack = attacks[direction];
			attacking = true;
			//add attack's hitboxes to character
			for(HitBox attackBox : attacks[direction].getAttackBoxes()) {
				AttackBoxes.add(attackBox);
			}
			//stop animation cancelling
			movementBlocked = true;
			moving = false;
		}
	}
	
	/**
	 * @function clear the animations and attacks for something new
	 */
	public void resetAnimations() {//clear the current animation
		attacking = false;
		AttackBoxes.clear();//clear attack hit boxes
		currentAnimation.reset();//reset the last used animation
		currentAnimation = null;//reset current animation
	}
	
	/**
	 * @function plant character on to ground
	 */
	public void ground() {
		remainingJumps = 2;//reset jumps
		//check is character was being launched
		boolean WasSmashed = false;
		if(Arrays.asList(smashed).contains(currentAnimation)) {
			WasSmashed = true;
		}
		
		resetAnimations();//prepare for animation change
		//if we were launched, change to ground collision
		if(WasSmashed) {
			movementBlocked = true;
			currentAnimation = hitGround;
		}
		//otherwise, run the normal animation detection based on keypress code
		else {
			if(moving) {
				if(pressed[0] && pressed[1]) {
					if(direction == 0) {
						currentAnimation = run[0];
					}
					else {
						currentAnimation = run[1];
					}
				}
				else if(pressed[0]) {
					currentAnimation = run[0];
				}
				else if(pressed[1]) {
					currentAnimation = run[1];
				}
			}
			else{
				currentAnimation = resting[direction];
			}
		}
		//reset physics forces
		VectorX = 0.0;
		VectorY = 0.0;
		grounded = true;//ground
	}
	
	/**
	 * @function remove from ground
	 */
	public void unGround() {
		grounded = false;
	}
	
	/**
	 * 
	 * @return whether or not character is grounded (planted on platform/floor)
	 */
	public boolean isGrounded() {
		return grounded;
	}
	
	/**
	 * @function make the character start moving
	 */
	public void StartMoving() {
		if(!movementBlocked) {//no animation cancelling
			if(grounded) {//if grounded, set animation to running
				if(currentAnimation != run[direction]) {
					resetAnimations();
					currentAnimation = run[direction];
				}
			}
			else {//otherwise, make it falling
				if(currentAnimation != falling[direction]) {
					resetAnimations();
					currentAnimation = falling[direction];
				}
			}
			moving = true;//set to moving
		}
	}
	
	/**
	 * @function update the hitboxes based on character x and y position
	 */
	public void relocateHitBoxes() {
		for(HitBox DefenseBox : DefenseBoxes){ // update each defense hitbox
			DefenseBox.changeLocation(Xcoordinate,Ycoordinate);
		}
		//update each attack hitbox
		AttackBoxes.clear();
		if(attacking) {
			for(HitBox attackBox : currentAttack.getAttackBoxes()) {
				attackBox.changeLocation(Xcoordinate+currentAttack.getXchange(), Ycoordinate+currentAttack.getYchange());
				AttackBoxes.add(attackBox);
			}
		}
	}
	
	/**
	 * @function make character jump
	 */
	public void jump() {
		if(remainingJumps>0 && !movementBlocked){//limited jumps before grounding, and no animation canceling
			grounded = false;//not on ground
			VectorY=-35+weight;//apply force of jump height, based on weight
			//change animation to jumping
			resetAnimations();
			currentAnimation = jumping[direction];
			remainingJumps--;//one less jump remaining
		}
	}
	
	/**
	 * @function make character pass through the floor below him/her
	 */
	public void fallThroughFloor() {
		if(grounded && !movementBlocked){//only proc if already on floor, with no animation canceling
			Ycoordinate+=11;//remove from floor's hit box (10 pixels)
			unGround();//no longer on ground
			//change animation to falling
			resetAnimations();
			currentAnimation = falling[direction];
		}
	}
	
	/**
	 * @function register key presses, and take actions based on them
	 * @param key direction key pressed (0-3), or ability button pressed (4 & 5)
	 */
	public void press(int key) {
		if(key<=3) {//directional keys
			pressed[key] = true;
		}
		
		if(key == 0 || key == 1) {//movement keys
			direction = key;//set direction
			StartMoving();//start moving in the above direction
		}
		else if(key == 4) {//basic ability key
			pressedBasic = true;
		}
		else if(key == 5) {//special ability key
			pressedSpecial = true;
		}
		
		if(key == 2 && !pressedBasic && !pressedSpecial) {//jump if up and no attack buttons are pressed
			jump();
		}
		if(key == 3 && !pressedBasic && !pressedSpecial) {//fall if down and no attack buttons are pressed
			fallThroughFloor();
		}
		
		//register attacks
		if(pressed != new boolean[4]){//only if directional keys are pressed (pressed array has at least 1 true)
			if(pressedBasic) {//if we have pressed the basic atk button
				//sequential search for the key being pressed currently
				for(int i=0;i<pressed.length;i++) {
					if(pressed[i]) {
						//play the attack found
						playAttack(i);
						break;
					}
				}
			}
			else if(pressedSpecial) {//if we have pressed the special attack button
				//sequential search for pressed key
				for(int i=0;i<pressed.length;i++) {
					if(pressed[i]) {
						//launch special attacks based on the pressed key
						if(i == 0) {
							leftSpecial();
						}
						else if(i == 1) {
							rightSpecial();
						}
						else if(i == 2) {
							upSpecial();
						}
						else {
							downSpecial();
						}
						break;
					}
				}
			}
		}
	}
	
	/**
	 * mini
	 * @param key register user releasing specified key
	 */
	public void letGo(int key) {
		if(key<=3) {//directional keys
			pressed[key] = false;
		}
		//attack keys
		else if(key == 4) {
			pressedBasic = false;
		}
		else if(key == 5) {
			pressedSpecial = false;
		}
		//stop moving if the release the movement keys
		if(key == 0 || key == 1) {
			stopMoving();
		}
	}
	
	/**
	 * @function stop the character from moving
	 */
	public void stopMoving() {
		movementBlocked = false;
		if(!pressed[0] && !pressed[1]) {
			moving = false;
			resetAnimations();
			if(grounded) {
				currentAnimation = resting[direction];
			}
			else {
				currentAnimation = falling[direction];
			}
		}
	}
	
	public Image exist() {
		//deceleration of x
		Xcoordinate+=VectorX;
		if(VectorX>0) {
			VectorX = Math.max(VectorX-2, 0.0);
		}
		else if(VectorX<0) {
			VectorX = Math.min(VectorX+2, 0.0);
		}
		//deceleration of y
		Ycoordinate+=VectorY;
		if(VectorY>0) {
			VectorY = Math.max(VectorY-2, 0.0);
		}
		else if(VectorY<0) {
			VectorY = Math.min(VectorY+2, 0.0);
		}
		
		//gravity
		if(!grounded) {
			//this speed is approved by pao pao, do not mess with it james
			Ycoordinate+=6;//constant push down, no acceleration because most games do not have it?
		}
		
		//if moving, move in the specified direction
		if(moving) {
			if(pressed[0] && pressed[1]) {
				//handling multiple key presses, do based on direction, not key press
				if(direction == 0) {
					Xcoordinate-=speed;
				}
				else {
					Xcoordinate+=speed;
				}
			}
			//otherwise move based on key press
			else if(pressed[0]) {
				Xcoordinate-=speed;
			}
			else if(pressed[1]) {
				Xcoordinate+=speed;
			}
		}
		
		relocateHitBoxes();
		
		//if we are still on launch back animation although we are no longer being launched
		if(VectorX == 0.0 && VectorY == 0.0 && Arrays.asList(smashed).contains(currentAnimation)) {
			//change to falling
			resetAnimations();
			currentAnimation = falling[direction];
		}
		
		
		specialFunctions();//special ability housekeeping tasks, for every frame
		
		Image CurrentPicture = currentAnimation.nextFrame();//get the next frame in the animation
		
		if(CurrentPicture == null) {//on animation end
			movementBlocked = false;//free to move again, because animation ended
			resetAnimations();//reset for next time it's played
			if(grounded) {//if we are on the ground
				//run movement code to determine animation
				if(moving) {
					if(pressed[0] && pressed[1]) {
						if(direction == 0) {
							currentAnimation = run[0];
						}
						else {
							currentAnimation = run[1];
						}
					}
					else if(pressed[0]) {
						currentAnimation = run[0];
					}
					else if(pressed[1]) {
						currentAnimation = run[1];
					}

					else {
						currentAnimation = resting[direction];
					}
				}
				else {
					currentAnimation = resting[direction];
				}
			}
			else {
				currentAnimation = falling[direction];
			}
			return currentAnimation.nextFrame();//return the next frame
		}
		return CurrentPicture;//return the next frame
	}
}
