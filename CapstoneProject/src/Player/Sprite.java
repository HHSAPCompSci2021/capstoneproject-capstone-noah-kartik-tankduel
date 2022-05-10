package Player;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Credit to John Shelby for code
 * @author npien
 *
 */
 
public class Sprite extends Rectangle2D.Double {
	// CONSTRUCTORS
	
	public Sprite(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	// METHODS	
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
	public void draw(PApplet g) {
		g.fill(100);
		g.rect((float)x,(float)y,(float)width,(float)height);
	}
	
	
}










