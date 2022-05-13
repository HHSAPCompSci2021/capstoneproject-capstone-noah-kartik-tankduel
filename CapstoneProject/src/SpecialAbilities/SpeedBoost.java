package SpecialAbilities;

import processing.core.PApplet;

/**
 * the player in the game
 * @author Noah Pien and Kartik Joshi
 * Credit to John Shelby for code
 * @author npien
 *
 */

public class SpeedBoost extends SpecialAbilities {
	private static final long serialVersionUID = 1389832171568527844L;

	/**
	 * Creates a SpeedBoost special ability by first making a call to super constructor
	 * @param x - x position of the Dive Tag
	 * @param y - y position of the Dive Tag
	 */
	public SpeedBoost(int x, int y) {
		super(x, y, ABILITY_WIDTH, ABILITY_HEIGHT);
	}

	/**
	 * Standard processing drawing
	 */
	public void draw(PApplet g) {
		g.fill(0,255,0);
		g.rect((float)x,(float)y,(float)width,(float)height);
	}
	

}