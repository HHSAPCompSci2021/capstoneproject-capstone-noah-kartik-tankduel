package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**Credits to John Shelby for part of code
 * 
 *
 */
public class RulesScreen extends Screens{

	private Rectangle back;
	
	private DrawingSurface surface;
	public RulesScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);

	}

	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		surface.textSize(70);
		surface.text("RULES", 540-surface.textWidth("RULES")/2,75);
		
		surface.fill(255,255,255);
		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 9);
		
		surface.popStyle();
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN1);
	}
	
}
