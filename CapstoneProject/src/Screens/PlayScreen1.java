package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**Credits to John Shelby for part of code
 * 
 *
 */
public class PlayScreen1 extends Screens{

	private Rectangle back;
	
	private DrawingSurface surface;
	public PlayScreen1(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);

	}

	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		
		surface.pushStyle();
		surface.stroke(25,255,255);
		surface.strokeWeight(4);
		surface.line(538, 0, 538, 720);
		surface.popStyle();

		surface.textSize(40);
		surface.text("CHOOSE A MAP", (540-surface.textWidth("CHOOSE A MAP"))/2,150);
		
		
		
		surface.textSize(40);
		surface.text("CHOOSE A GAMEMODE", 540 + (540 - surface.textWidth("CHOOSE A GAMEMODE"))/2,150);
	
		surface.fill(255,255,255);
		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.popStyle();
	}
	
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU);
	}
	
}
