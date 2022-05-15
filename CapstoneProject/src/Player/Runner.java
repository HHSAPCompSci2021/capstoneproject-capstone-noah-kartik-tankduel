package Player;

import processing.core.PApplet;

/**
 * Runner class which is a subclass of Player
 * @author Kartik Joshi
 *
 */
public class Runner extends Player{

	public Runner(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void draw(PApplet g) {
		g.fill(25,255,255);
		g.rect((float)x,(float)y,(float)width,(float)height);
	}
}
