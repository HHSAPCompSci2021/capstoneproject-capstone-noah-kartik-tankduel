package Player;
import java.awt.geom.Line2D;

import java.net.InetAddress;
import java.net.UnknownHostException;
import Screens.*;
import SpecialAbilities.*;
import System.DrawingSurface;
import processing.core.PApplet;

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
	public String name;
	private long diveTime;
	private long speedTime;
	private long cloakTime;
	private long jumpTime;
	private DrawingSurface surface;
	private boolean speed;
	private boolean jump;
	public boolean invisible;
	public boolean dive;
	private boolean frozen;
	
	private boolean playerType; // false for runner true for tagger
	private double xVel, yVel;
	private int countInvis = 0;
	private int countDive = 0;
	public boolean invisUsed;
	public boolean diveUsed;
	public boolean turnInvisOff;
	public boolean turnDiveOff;
	private int taggedTime;
	private long unfrozenTime;
	private long frozenTime;

	
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
		frozen = false;
		taggedTime = 0;
		unfrozenTime = 0;
		frozenTime= 0;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean getPlayerType() {
		return playerType;
	}
	public void setPlayerType(boolean x) {
		playerType = x;
	}

	
	/**
	 * Makes the character move left and right
	 * @param dir - direction you want the character to go 
	 */
	
	public void isFrozen() {
		if(!frozen) {
			frozen = true;
			frozenTime = System.currentTimeMillis();
		}
	}
	public void unFrozen() {
		if(frozen) {
			frozen = false;
			unfrozenTime = System.currentTimeMillis();
		}
	}
	public long getunfrozenTime() {
		return unfrozenTime;
	}
	public long getFrozenTime() {
		return frozenTime;
	}
	public boolean frozeOrUnfroze() {
		return frozen;
	}
	public void gotTagged() {
		taggedTime++;
	}
	public int getTaggedTime() {
		return taggedTime;
	}
	public void walk(int dir) {
		if(!frozen) {
			xVel = dir * 3;
			if(playerType)
				xVel*=1.1;
			if(speed)
				xVel*=1.25;
		}else {
			xVel = 0;
			yVel = 0;
		}
	}

	/**
	 * Makes the player jump up 
	 */
	public void jump() {
		if(!frozen) {
			if(onASurface) {
			yVel -= 6.5;
			if(playerType)
				yVel *= 1.1;
			if(jump)
				yVel*=1.2;
		}
		}else {
			yVel = 0;
		}
	}
	public boolean getInvisible() {
		return invisible;
	}

	/**
	 * Checks the player collision between the boundaries of the map and makes sure it does not go outside the boundary. 
	 * Checks to see if a player has collided and if it does then it acts in that way
	 * Checks to see if a player has picked up any of the special abilities which is basically also a collision.
	 * @param obstacles - the array of platforms on which the player can jump on
	 * @param abilities - the array of abilities from which you can pick up 
	 */
	public void act(Line2D[] obstacles,SpecialAbilities[] abilities) {
		if(System.currentTimeMillis()-speedTime>7000) {
			speed = false;
		}
		if(System.currentTimeMillis()-jumpTime>7000) {
			jump = false;
		}
		if(cloakTime !=0 && System.currentTimeMillis()-cloakTime>4000) {
			invisible = false;
			turnInvisOff = true;
		}
		if(diveTime != 0 && System.currentTimeMillis()-diveTime>7000) {
			dive = false;
			turnDiveOff = true;
		}
		onASurface = false;
		if(!frozen)
			yVel += 0.2;
		
		x += xVel;
		y += yVel;

		if(invisible && countInvis == 0) {
			countInvis = 1;
			cloakTime = System.currentTimeMillis();
			NormalMapScreen.deleteCloak();
			NormalMapFreezeTagScreen.deleteCloak();
			WaterMapScreen.deleteCloak();
			WaterMapFreezeTagScreen.deleteCloak();
			CopsNRobbersWaterMap.deleteCloak();
			NormalMapCopsNRobbers.deleteCloak();
		}
		if(dive && countDive == 0) {
			countDive = 1;
			diveTime = System.currentTimeMillis();
			NormalMapScreen.deleteDive();
			NormalMapFreezeTagScreen.deleteDive();
			WaterMapScreen.deleteDive();
			WaterMapFreezeTagScreen.deleteDive();
			CopsNRobbersWaterMap.deleteDive();
			NormalMapCopsNRobbers.deleteDive();
		}
		
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

				if(s.getY2()>s.getY1()) {//   \ slope
					if(getY() + height -10< s.getY1() + Math.abs(Math.min(s.getX1(), s.getX2())-getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1())) {//top side of \ slope
						if(getX()<= s.getX1()) {
							y = s.getY1()-height;
						}
						else
							y = Math.abs(Math.min(s.getX1(), s.getX2())-getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())- height;
					}
					else{//under side of \ slope
						if(getX() + width>s.getX2()) {
							y = s.getY2();
						}
						else if(getX() + width +10 >s.getX1())
							y = Math.abs(Math.min(s.getX1(), s.getX2())-getX()-width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
					}
				}
				else if(s.getY2() == s.getY1()) {//horizontal slope --
					if(getY()+5<s.getY1()) {
						y = s.getY1()-height;
					}
					else {
						y = s.getY1()+3;
					}
				}
				else {//   / slope upwards
					if(getY() + height - 10< s.getY1() - Math.abs(Math.min(s.getX1(), s.getX2())-getX()-width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1())) { // top side
						if(getX() + width >= s.getX2())
							y = s.getY2()-height;
						else
							y = Math.abs(Math.max(s.getX1(), s.getX2())-getX()-width) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2())-height;

					}
					else {
						if(getX() <s.getX1())//bottom side
							y = s.getY1();
						else
							y = Math.abs(Math.max(s.getX1(), s.getX2())-getX()) / Math.abs(s.getX1()-s.getX2()) * Math.abs(s.getY2()-s.getY1()) + Math.min(s.getY1(), s.getY2());
					}
				}
				
				onASurface = true;
			}
			if(x<2)
				x = 2;
			if(x + PLAYER_WIDTH + Math.abs(PLAYER_WIDTH-width)> 1078) {
				x = 1061 - Math.abs(PLAYER_WIDTH-width);
			}
			if(y<2) {
				y = 2;
				yVel = 0;
			}
			if(y>718)
				y = 718;
		}
		if(NormalMapFreezeTagScreen.abilityUseable || NormalMapScreen.abilityUseable
				|| NormalMapCopsNRobbers.abilityUseable || WaterMapScreen.abilityUseable 
					|| CopsNRobbersWaterMap.abilityUseable || WaterMapFreezeTagScreen.abilityUseable
				) {
			for(SpecialAbilities a: abilities) {
				if(a.intersects(this)) {
					if(a instanceof SpeedBoost) {
						speed = true;
						speedTime = System.currentTimeMillis();
						NormalMapScreen.deleteSpeed();
						NormalMapFreezeTagScreen.deleteSpeed();
						WaterMapScreen.deleteSpeed();
						WaterMapFreezeTagScreen.deleteSpeed();
						CopsNRobbersWaterMap.deleteSpeed();
						NormalMapCopsNRobbers.deleteSpeed();
					}
					if(a instanceof HighJump) {
						jump = true;
						jumpTime = System.currentTimeMillis();
						NormalMapScreen.deleteJump();
						NormalMapFreezeTagScreen.deleteJump();
						WaterMapScreen.deleteJump();
						WaterMapFreezeTagScreen.deleteJump();
						CopsNRobbersWaterMap.deleteJump();
						NormalMapCopsNRobbers.deleteJump();
					}
					if(a instanceof SneakyCloak && !playerType) {
						invisible = true;
						cloakTime = System.currentTimeMillis();
						NormalMapScreen.deleteCloak();
						NormalMapFreezeTagScreen.deleteCloak();
						WaterMapScreen.deleteCloak();
						WaterMapFreezeTagScreen.deleteCloak();
						CopsNRobbersWaterMap.deleteCloak();
						NormalMapCopsNRobbers.deleteSpeed();
					}
					if(a instanceof DiveTag && playerType) {
						dive = true;
						diveTime = System.currentTimeMillis();
						NormalMapScreen.deleteDive();
						NormalMapFreezeTagScreen.deleteDive();
						WaterMapScreen.deleteDive();
						WaterMapFreezeTagScreen.deleteDive();
						CopsNRobbersWaterMap.deleteDive();
						NormalMapCopsNRobbers.deleteDive();
					}
					
				}
			}
		}
		
		
	}
	public void draw(PApplet g) {
		g.pushStyle();
		g.strokeWeight(3);
		if(playerType)
			g.fill(238,232,170);
		else
			g.fill(25,255,255);
		
		if(frozen) {
			g.fill(85,118,209);
		}
		if(dive) {
			width = 26;
			height = 30;
		}
		else {
			width = 17;
			height = 20;
		}
		if(!invisible) {
			g.rect((float)x,(float)y,(float)width,(float)height);
		}
		g.popStyle();
	}
	


}