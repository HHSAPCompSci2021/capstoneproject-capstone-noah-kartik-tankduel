package Player;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * the player in the game
 * @author Noah Pien and Kartik Joshi
 * Credit to John Shelby for code
 * @author npien
 *
 */

public class Player extends Sprite {

	public static final int PLAYER_WIDTH = 40;
	public static final int PLAYER_HEIGHT = 60;
	private boolean onASurface;

	private double xVel, yVel;

	public Player(int x, int y) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		xVel = 0;
		yVel = 0;
		onASurface = false;
	}

	// METHODS
	public void walk(int dir) {
		xVel = dir * 3;
	}

	public void jump() {
		if(onASurface) {
		yVel -= 9;
		}
	}

	public void act(Line2D[] obstacles) {
		onASurface = false;
		yVel += 0.2;
		
		x += xVel;
		y += yVel;
		
		for(Line2D s: obstacles) {
			if(super.intersectsLine(s)) {
				yVel = 0;
				super.y = s.getY1() - super.height;
				onASurface = true;
			}
		}
		
	}


}