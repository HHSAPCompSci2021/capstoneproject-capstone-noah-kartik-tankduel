package Player;

import processing.core.PApplet;

/**
 * Tagger is a subclass of player
 * @author kjoshi980
 *
 */
public class Tagger extends Player{

	public Tagger(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(PApplet g) {
		g.fill(238,232,170);
		g.rect((float)x,(float)y,(float)width,(float)height);

	}

}
