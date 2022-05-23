package Screens;

import System.DrawingSurface;
import processing.event.MouseEvent;
/**
 * the credits screen
 * @author Kartik Joshi
 *
 */
public class CreditsScreen extends Screens{
	private DrawingSurface surface;
	/**
	 * draws the credit screen
	 * @param surface the surface on which to draw on
	 */
	public CreditsScreen(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
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

		surface.fill(255,255,255);
		surface.textSize(35);
		surface.text("Founders of the game => Kartik and Noah", 540-surface.textWidth("Founders of the game => Kartik and Noah")/2, 240);
		surface.color(150);
		surface.textSize(25);
		
	}
	

}
