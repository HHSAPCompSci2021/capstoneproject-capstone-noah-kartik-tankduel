package Player;
import java.awt.geom.Line2D;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Queue;

import Screens.NormalMapScreen;
import Screens.ScreenSwitcher;
import Screens.TwoPlayerOrNetwork;
import SpecialAbilities.*;
import System.DrawingSurface;
import networking.frontend.NetworkDataObject;
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

	private boolean speed;
	private boolean jump;
	public boolean invisible;
	
	private boolean playerType; // false for runner true for tagger
	private double xVel, yVel;
	
	private static final String messageTypeInvisible = "INVISIBLE";
	private static final String messageTypeDiveTag = "DIVETAG";
	private DrawingSurface surface;
	
	/**
	 * Creates a player at position x and y and calls the super constructor 
	 * @param x - x coordinate of the spawn point 
	 * @param y - y coordinate of the spawn point
	 */
	public Player(int x, int y, DrawingSurface surface) {
		super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		xVel = 0;
		yVel = 0;
		onASurface = false;
		speed = false;
		jump = false;
		this.surface = surface;
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
	public boolean getInvisible() {
		return invisible;
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
		if(System.currentTimeMillis()-cloakTime>4000) {
			invisible = false;
		}
		if(System.currentTimeMillis()-diveTime>7000) {
			this.width = 17;
			this.height = 20;
		}
		onASurface = false;
		yVel += 0.2;
		
		x += xVel;
		y += yVel;

		int c = 0;
		if(invisible && c == 0) {
			c = 1;
			cloakTime = System.currentTimeMillis();
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
				if(a instanceof SneakyCloak && !playerType) {
					//make invisible here
					invisible = true;
					cloakTime = System.currentTimeMillis();
//					if(TwoPlayerOrNetwork.network)
//						((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeInvisible, true);
					NormalMapScreen.deleteCloak();
				}
				if(a instanceof DiveTag && playerType) {
					//increase tag range here
					diveTime = System.currentTimeMillis();
					NormalMapScreen.deleteDive();
					this.width = 26;
					this.height = 30;
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
		if(invisible) {
			g.fill(255,255,255);
			g.stroke(255,255,255);
		}
		g.rect((float)x,(float)y,(float)width,(float)height);
//		if(TwoPlayerOrNetwork.network) {
//			if (((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger() == null)
//				return;
//			
//			Queue<NetworkDataObject> queue = ((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
//			while (!queue.isEmpty()) {
//				NetworkDataObject ndo = queue.poll();
//	
//				String host = ndo.getSourceIP();
//	
//				if (ndo.messageType.equals(NetworkDataObject.MESSAGE)) {
//					if (ndo.message[0].equals(messageTypeInvisible)) {
//						String s = (String)ndo.message[1];
//						if(s.equals(host)) {
//							g.fill(255,255,255);
//							g.stroke(255,255,255);
//						}
//					}
//				}
//			}
//		}
		g.popStyle();
	}
	


}