package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
import processing.event.MouseEvent;
/**
 * the credits screen
 * @author Kartik Joshi
 *
 */
public class CreditsScreen extends Screens{
	private DrawingSurface surface;
	private Rectangle back;
	/**
	 * draws the credit screen
	 * @param surface the surface on which to draw on
	 */
	public CreditsScreen(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);

		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Standard processing drawing
	 */
	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		surface.textSize(50);
		surface.text("Credits", 540-surface.textWidth("Credits")/2,125);

		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.textSize(35);
		surface.text("Founders of the game => Kartik and Noah", 540-surface.textWidth("Founders of the game => Kartik and Noah")/2, 240);
		surface.color(150);
		surface.textSize(25);
		surface.popStyle();
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU);
	}
}
