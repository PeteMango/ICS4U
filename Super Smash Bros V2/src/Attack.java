import java.util.ArrayList;

public class Attack {
	private ArrayList<HitBox> AttackBoxes = new ArrayList<>();
	private Animation animation;
	private double damage;
	private int direction;
	private double relativeXchange;
	private double relativeYchange;
	
	public Attack() {
		animation = null;
		damage = 0.0;
		direction = -1;
		relativeXchange =0.0;
		relativeYchange =0.0;
	}
	
	public Attack(Animation animation, ArrayList<HitBox> AttackBoxes, double damage, int direction,double relativeXchange,double relativeYchange) {
		this.animation = animation;
		this.AttackBoxes = AttackBoxes;
		this.damage = damage;
		this.direction = direction;
		this.relativeXchange = relativeXchange;
		this.relativeYchange = relativeYchange;
	}
	
	public int getDirection() {
		return direction;
	}
	public double getDamage() {
		return damage;
	}
	public Animation getAnimation() {
		return animation;
	}
	public ArrayList<HitBox> getAttackBoxes() {
		return AttackBoxes;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public double getXchange() {
		return relativeXchange;
	}
	public void setYchange(double change) {
		relativeYchange = change;
	}
	public void setXchange(double change) {
		relativeXchange = change;
	}
	public double getYchange() {
		return relativeYchange;
	}
	public void addAttackBox(double X,double Y,double length,double width) {
		AttackBoxes.add(new HitBox(X+relativeXchange,Y+relativeYchange,length,width));
	}
}
