package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**
 * This screen is the menu of the game. From this screen a person can navigate to play the game, read the rules, and change the settings
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for part of code
 */
public class MenuScreen extends Screens{

	private DrawingSurface surface;
	private Rectangle play;
	private Rectangle rules;
	private Rectangle setting;
	
	/**
	 * Gives menu the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public MenuScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		play = new Rectangle(300,200,480,50);
		rules = new Rectangle(300,300,480,50);
		setting = new Rectangle(300,400,480,50);
	}

	/**
	 * Draws the screen on the DrawingSurface
	 */
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
		
		surface.fill(255,255,255);
		surface.rect(setting.x, setting.y, setting.width, setting.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("SETTINGS", setting.x+setting.width/2-surface.textWidth("SETTINGS")/2, setting.y + setting.height/2 + 18);
		
		surface.popStyle();
		
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (rules.contains(p))
			surface.switchScreen(ScreenSwitcher.RULES);
		if (play.contains(p))
			surface.switchScreen(ScreenSwitcher.PLAYSCREEN1);
		if (setting.contains(p))
			surface.switchScreen(ScreenSwitcher.SETTING);
	}
	
}
