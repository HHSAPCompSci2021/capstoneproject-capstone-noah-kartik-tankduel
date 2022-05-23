package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**
 * This screen shows the rules of the game
 * 
 * @author Noah Pien and Kartik Joshi
 */
public class AbilitiesInstructScreen extends Screens{

	private Rectangle back;
	private DrawingSurface surface;
	private Rectangle next;
	int x = 1;
	/**
	 * Gives each RulesScreen the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public AbilitiesInstructScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);
		next = new Rectangle(990,690,70,20);
	}

	/**
	 * Draws the screen on the DrawingSurface
	 */
	public void draw() {
			surface.clear();
			surface.background(0,0,0);
			surface.fill(255,255,255);
			surface.textSize(10);
	
			surface.pushStyle();
			surface.textSize(70);
			surface.text("ABILITIES", 540-surface.textWidth("ABILITIES")/2,70);
			
			surface.fill(255,255,255);
			surface.textSize(25);
			surface.text("Abilities will randomly spawn on the map when the game starts.", 540-surface.textWidth("Abilities will randomly spawn on the map when the game starts.")/2, 120);
			surface.text("Abilities look like colored dots.", 540-surface.textWidth("Abilities look like colored dots.")/2, 150);
			surface.text("Different abilities have different colors and only one of each will spawn.", 540-surface.textWidth("Different abilities have different colors and only one of each will spawn.")/2, 180);
			surface.text("All abilities are one time use. Used abilities are gone until the next round starts.", 540-surface.textWidth("All abilities are one time use. Used abilities are gone until the next round starts.")/2, 210);
	
			surface.fill(0,255,0);
			surface.textSize(40);
			surface.text("SPEED BOOST",  540-surface.textWidth("SPEED BOOST")/2, 250);
			surface.fill(255,255,255);
			surface.textSize(25);
			surface.text("This ability will give you a 20% speed boost for 7 seconds.",  540-surface.textWidth("This ability will give you a 20% speed boost for 7 seconds.")/2, 280);
			surface.text("This color of this boost is green.",  540-surface.textWidth("This color of this boost is green.")/2, 310);
	
			surface.fill(0,0,255);
			surface.textSize(40);
			surface.text("JUMP BOOST",  540-surface.textWidth("JUMP BOOST")/2, 350);
			surface.fill(255,255,255);
			surface.textSize(25);
			surface.text("This ability will give you a 20% jump boost for 7 seconds.",  540-surface.textWidth("This ability will give you a 20% jump boost for 7 seconds.")/2, 380);
			surface.text("This color of this boost is blue.",  540-surface.textWidth("This color of this boost is blue.")/2, 410);
			
			surface.fill(128,128,128);
			surface.textSize(40);
			surface.text("SNEAKY CLOAK",  540-surface.textWidth("SNEAKY CLOAK")/2, 450);
			surface.fill(255,255,255);
			surface.textSize(25);
			surface.text("This ability will make you invisible for 7 seconds.",  540-surface.textWidth("This ability will make you invisible for 7 seconds.")/2, 480);
			surface.text("This color of this boost is gray.",  540-surface.textWidth("This color of this boost is gray.")/2, 510);
			surface.text("*This boost is limited to runners/robbers only! Note: You won't see your own character too!*",  540-surface.textWidth("*This boost is limited to runners/robbers only! Note: You won't see your own character too!*")/2, 540);
	
			surface.fill(255,0,0);
			surface.textSize(40);
			surface.text("DIVE TAG",  540-surface.textWidth("DIVE TAG")/2, 590);
			surface.fill(255,255,255);
			surface.textSize(25);
			surface.text("This ability will give a 50% increase in tag range.",  540-surface.textWidth("This ability will give a 50% increase in tag range.")/2, 620);
			surface.text("This color of this boost is red.",  540-surface.textWidth("This color of this boost is red.")/2, 650);
			surface.text("*This boost is limited to taggers/cops only!*",  540-surface.textWidth("*This boost is limited to taggers/cops only!*")/2, 680);
	
			
			surface.fill(255,255,255);
			surface.rect(next.x, next.y, next.width, next.height);
			surface.fill(0,0,0);
			surface.textSize(20);
			surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);
	
			surface.fill(255,255,255);
			surface.rect(back.x, back.y, back.width, back.height);
			surface.fill(0,0,0);
			surface.textSize(20);
			surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
			
		
		
			surface.popStyle();
		
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.RULES);
		if(next.contains(p)) {
			surface.switchScreen(ScreenSwitcher.SERVERNETWORKHELP);
		}
		
	}
	
}
