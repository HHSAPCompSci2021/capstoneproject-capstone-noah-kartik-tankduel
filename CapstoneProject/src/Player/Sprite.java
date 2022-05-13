package Player;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;

/**
 * Credit to John Shelby for code
 * @author npien
 *
 */
 
public class Sprite extends Rectangle2D.Double {
	// CONSTRUCTORS

	private static final long serialVersionUID = 1005976734654758648L;


	/**
	 * Creates a sprite with these certain parameters
	 * @param x - x position of sprite
	 * @param y - y position of sprite
	 * @param w - width of sprite
	 * @param h - height of sprite
	 */
	public Sprite(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	/**
	 * Moves the sprite to a new location	
	 * @param x - new x coordinate
	 * @param y - new y coordinate
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	/**
	 * moves the sprite by a certain x and y values
	 * @param x - the x amount you are moving it by
	 * @param y - the y amount you are moving it by
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	/**
	 * Checks to see if the sprite does not go out of bounds
	 * @param windowWidth - width of window
	 * @param windowHeight - height of window
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Standard processing method to draw stuff on the PApplet
	 * @param g - PApplet
	 */
	public void draw(PApplet g) {
		g.fill(25,255,255);
		g.rect((float)x,(float)y,(float)width,(float)height);
	}
	
	
}









