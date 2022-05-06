package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**Credits to John Shelby for part of code
 * 
 *
 */
public class MenuScreen extends Screens{

	private DrawingSurface surface;
	private Rectangle play;
	private Rectangle rules;
	
	public MenuScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		play = new Rectangle(300,200,480,50);
		rules = new Rectangle(300,300,480,50);

	}

	public void draw() {
		surface.pushStyle();
		surface.background(0,0,0);
		surface.textSize(150);
		surface.text("YOU ARE IT", 540-surface.textWidth("YOU ARE IT")/2,125);
		
		surface.rect(play.x, play.y, play.width, play.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("PLAY", play.x+play.width/2-surface.textWidth("PLAY")/2, play.y + play.height/2 + 18);
		
		surface.fill(255,255,255);
		surface.rect(rules.x, rules.y, rules.width, rules.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("RULES", rules.x+rules.width/2-surface.textWidth("RULES")/2, rules.y + rules.height/2 + 18);
		
		surface.popStyle();
		
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (rules.contains(p))
			surface.switchScreen(ScreenSwitcher.RULES);
		if (play.contains(p))
			surface.switchScreen(ScreenSwitcher.PLAYSCREEN1);
	}
	
}
