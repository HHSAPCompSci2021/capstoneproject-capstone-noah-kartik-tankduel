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

	public static final int PLAYER_WIDTH = 17;
	public static final int PLAYER_HEIGHT = 20;
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
		yVel -= 6.5;
		}
	}

	public void act(Line2D[] obstacles) {
		onASurface = false;
		yVel += 0.2;
		
		x += xVel;
		y += yVel;

			
		for(Line2D s: obstacles) {
			if(super.intersectsLine(s)) {
				double temp = yVel;
				double degree = Math.atan((s.getY2()-s.getY1()/(s.getX2()-s.getX1())));
				yVel = 0;
				xVel = 0;
				if(s.getX1() == s.getX2()) {
					if(x+3>s.getX1())
						x = s.getX1()+3;
					else
						x = s.getX1()-PLAYER_HEIGHT;
					xVel = 0;
					yVel = temp;
					continue;
				}
				if(s.getY2()>s.getY1()) {
					if(this.getY()<s.getY1() + Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()))
						super.y = Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())- super.height;
					else {
						super.y = Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()-super.width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
					}
				}
				else {
					if(this.getY()>s.getY1() - Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1())) {
						System.out.println("hi");
						super.y = Math.abs(Math.max(s.getX1(), s.getX2())-this.getX()-super.width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
					}
					else {
						System.out.println("hello");
						super.y = Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()-super.width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())- super.height;
					}
				}
				
				onASurface = true;
			}
			if(x<2)
				x = 2;
			if(x + PLAYER_WIDTH> 1078) {
				x = 1061;
			}
			if(y<2) {
				y = 2;
				yVel = 0;
			}
			if(y>718)
				y = 718;
		}
		
		
	}


}