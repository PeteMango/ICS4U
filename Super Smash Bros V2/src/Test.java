package app;

public class Test extends Character{
	
	/**
	 * @function construct a test
	 */
	public Test (){
		//call the constructor, setting attributes
		super("test",500,100.0,5,2,10,100,100);
		//set the animations
		setBasicAnimations(
			new Animation("rest left",60),
			new Animation("rest right",60),
			new Animation("fall left",60),
			new Animation("fall right",60),
			new Animation("run left",8),
			new Animation("run right",8),
			new Animation("punch left",20),
			new Animation("jump left",8),
			new Animation("jump right",8)
		);
		//set the smashed animations
		this.setSmashedAnimations(
			Animation.fake,
			Animation.fake,
			Animation.fake,
			Animation.fake	
		);
		//set the basic attack animations
		setAttackAnimations(
			new Animation("punch left",8),
			new Animation("punch right",8),
			new Animation("punch left",20),
			new Animation("punch left",20)
		);
		//set basic attack hitboxes
		setAttackHitBox(getBasicAttacks()[0], -5, 10, 35, 25);
		setAttackHitBox(getBasicAttacks()[1], 65, 15, 35, 25);
		setAttackHitBox(getBasicAttacks()[2], 0, -20, 20, 20);
		//set the player's hitbox
		addDefenseBox(100, 100);
	}
	
	/**
	 * @function left special attack
	 */
	public void leftSpecial() {
		
	}
	/**
	 * @function right special attack
	 */
	public void rightSpecial() {
		
	}
	/**
	 * @function down special attack
	 */
	public void downSpecial() {
		
	}
	/**
	 * @function Up special attack
	 */
	public void upSpecial() {
		
	}
	/**
	 * @function anything that has to be executed every frame to make the special abilities work
	 */
	public void specialFunctions() {
		
	}
	
	
}
