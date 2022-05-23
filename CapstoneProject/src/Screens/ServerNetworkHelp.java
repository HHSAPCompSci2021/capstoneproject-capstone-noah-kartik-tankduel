package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**
 * This screen shows the rules of the game
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for part of code
 */
public class ServerNetworkHelp extends Screens{

	private Rectangle back;
	private DrawingSurface surface;
	/**
	 * Gives each RulesScreen the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public ServerNetworkHelp(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);
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
			surface.text("How to start a server", 540-surface.textWidth("How to start a server")/2,70);
			
		
		
		surface.popStyle();
		
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.RULES);
		
		
	}
	
}
