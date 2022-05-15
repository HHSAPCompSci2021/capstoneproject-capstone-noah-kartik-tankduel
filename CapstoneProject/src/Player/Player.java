package Player;
import java.awt.geom.Line2D;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Screens.NormalMapScreen;
import SpecialAbilities.*;

/**
 * the player in the game
 * @author Noah Pien and Kartik Joshi
 * Credit to John Shelby for code
 * @author npien
 *
 */

public class Player extends Sprite {

	private static final long serialVersionUID = -7613280413136318879L;
	public static final int PLAYER_WIDTH = 17;
	public static final int PLAYER_HEIGHT = 20;
	public String host;
	private boolean onASurface;
	private boolean speed;
	private boolean jump;
	private long diveTime;
	private long speedTime;
	private long cloakTime;
	private long jumpTime;

	private double xVel, yVel;

	/**
	 * Creates a player at position x and y and calls the super constructor 
	 * @param x - x coordinate of the spawn point 
	 * @param y - y coordinate of the spawn point
	 */
	public Player(int x, int y) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		xVel = 0;
		yVel = 0;
		onASurface = false;
		speed = false;
		jump = false;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Makes the character move left and right
	 * @param dir - direction you want the character to go 
	 */
	public void walk(int dir) {
		xVel = dir * 3;
		if(speed)
			xVel*=1.25;
	}

	/**
	 * Makes the player jump up 
	 */
	public void jump() {
		if(onASurface) {
		yVel -= 6.5;
		if(jump)
			yVel*=1.2;
		}
	}

	/**
	 * Checks the player collision between the boundaries of the map and makes sure it does not go outside the boundary. 
	 * Checks to see if a player has collided and if it does then it acts in that way
	 * Checks to see if a player has picked up any of the special abilities which is basically also a collision.
	 * @param obstacles - the array of platforms on which the player can jump on
	 * @param abilities - the array of ablities from which you can pick up 
	 */
	public void act(Line2D[] obstacles,SpecialAbilities[] abilities) {
		if(System.currentTimeMillis()-speedTime>7000) {
			speed = false;
		}
		if(System.currentTimeMillis()-jumpTime>7000) {
			jump = false;
		}
		if(System.currentTimeMillis()-cloakTime>7000) {
			//turn invisible off
		}
		if(System.currentTimeMillis()-diveTime>7000) {
			//decrease tag range to normal
		}
		onASurface = false;
		yVel += 0.2;
		
		x += xVel;
		y += yVel;

			
		for(Line2D s: obstacles) {
			if(super.intersectsLine(s)) {
				double temp = yVel;
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
						this.y = Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())- this.height;
					else {
						this.y = Math.abs(Math.min(s.getX1(), s.getX2())-this.getX()-this.width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
					}
				}
				else if(s.getY2() == s.getY1()) {
					if(this.getY()+5<s.getY1()) {
						this.y = s.getY1()-this.height;
					}
					else {
						this.y = s.getY1()+3;
					}
				}
				else {
					if(getY() + height-10< s.getY1() - Math.abs(Math.min(s.getX1(), s.getX2())-(getX()+width)) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1())) {
						y = Math.abs(Math.max(s.getX1(), s.getX2())-getX()-width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())-height;
					}
					else {
						y = Math.abs(Math.max(s.getX1(), s.getX2())-getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
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
		
		for(SpecialAbilities a: abilities) {
			if(a.intersects(this)) {
				if(a instanceof SpeedBoost) {
					speed = true;
					speedTime = System.currentTimeMillis();
					NormalMapScreen.deleteSpeed();
				}
				if(a instanceof HighJump) {
					jump = true;
					jumpTime = System.currentTimeMillis();
					NormalMapScreen.deleteJump();
				}
				if(a instanceof SneakyCloak) {
					//make invisible here
					cloakTime = System.currentTimeMillis();
					NormalMapScreen.deleteCloak();
				}
				if(a instanceof DiveTag) {
					//increase tag range here
					diveTime = System.currentTimeMillis();
					NormalMapScreen.deleteDive();
				}
				
			}
		}
		
		
	}



}