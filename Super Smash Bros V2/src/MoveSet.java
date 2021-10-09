package app;

import javafx.scene.input.KeyCode;

public class MoveSet {
	//class to represent a set of Keybindings
	private KeyCode[] moves = new KeyCode[4]; //movement binds
	private KeyCode basic;//basic atk bind
	private KeyCode special;//special ability bind
	
	/**
	 * @function initialize the movement binds
	 * @param left left key bind
	 * @param right right key bind
	 * @param up up key bind
	 * @param down down key bind
	 */
	public MoveSet(KeyCode left, KeyCode right, KeyCode up, KeyCode down) {
		moves[0] = left;
		moves[1] = right;
		moves[2] = up;
		moves[3] = down;
	}
	
	/**
	 * @return the movement keybinds
	 */
	public KeyCode[] getMoves() {
		return moves;
	}
	
	/**
	 * @return the basic atk bind
	 */
	public KeyCode getBasic() {
		return basic;
	}
	
	/**
	 * @return the special atk bind
	 */
	public KeyCode getSpecial() {
		return special;
	}
	
	/**
	 * @function set the attack buttons
	 * @param basic basic atk button
	 * @param special special atk button
	 */
	public void setAttackButtons( KeyCode basic, KeyCode special) {
		this.basic = basic;
		this.special = special;
	}
}
