package SpecialAbilities;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;

/**
 * Credit to John Shelby for code
 * @author npien
 *
 */
 
public class SpecialAbilities extends Rectangle2D.Double {

	private static final long serialVersionUID = 5341643981976337346L;
	// CONSTRUCTORS

	public static final int ABILITY_HEIGHT = 7;
	public static final int ABILITY_WIDTH = 7;

	public SpecialAbilities(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	// METHODS	
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
	public void draw(PApplet g) {
		g.fill(0,0,0);
		g.rect((float)x,(float)y,(float)width,(float)height);
	}
	
	
}









