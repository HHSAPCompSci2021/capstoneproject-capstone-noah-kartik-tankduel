package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**
 * This screen shows the settings of the game. The player can adjust this.
 * 
 * @author Noah Pien and Kartik Joshi
 */
public class SettingScreen extends Screens{

	private Rectangle back;
	private Rectangle wasd;
	private Rectangle arrowKey;
	private DrawingSurface surface;
	
	/**
	 * Gives setting the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public SettingScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);
		wasd = new Rectangle (600,120,100,40);
		arrowKey = new Rectangle (800,120,150,40);

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
		surface.text("SETTINGS", 540-surface.textWidth("SETTINGS")/2,70);
		
		surface.textSize(40);
		surface.text("INPUT METHOD:", 200, 153);
		
		if(!surface.getInputMethod()) {
			surface.fill(25,255,255);
			surface.rect(wasd.x-5, wasd.y-5, wasd.width+10, wasd.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(wasd.x, wasd.y, wasd.width, wasd.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("WASD", wasd.x+wasd.width/2-surface.textWidth("WASD")/2, wasd.y + wasd.height/2 + 7);
		
		if(surface.getInputMethod()) {
			surface.fill(25,255,255);
			surface.rect(arrowKey.x-5, arrowKey.y-5, arrowKey.width+10, arrowKey.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(arrowKey.x, arrowKey.y, arrowKey.width, arrowKey.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("ARROW KEYS", arrowKey.x+arrowKey.width/2-surface.textWidth("ARROW KEYS")/2, arrowKey.y + arrowKey.height/2 + 7);
		
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
			surface.switchScreen(ScreenSwitcher.MENU);
		if (wasd.contains(p)) {
			surface.setInputMethod(false);
		}
		if (arrowKey.contains(p))
			surface.setInputMethod(true);
	}
	
}
