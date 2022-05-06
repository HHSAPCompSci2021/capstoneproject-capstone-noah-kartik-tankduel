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
		surface.textSize(20);
		surface.text("Freeze Mode", 540-surface.textWidth("Freeze Mode")/2, 150);
		surface.color(150);
		surface.textSize(10);
		surface.text("1. If you are a runner and you get tagged you are frozen",  540-surface.textWidth("1. If you are a runner and you get tagged you are frozen")/2, 200);
		surface.text("2. If one person gets tagged three times runners lose",  540-surface.textWidth("2. If one person gets tagged three times runners lose")/2, 220);
		surface.text("3. If runners survive till time ends they win",  540-surface.textWidth("3. If runners survive till time ends they win")/2, 240);
		surface.textSize(20);
		surface.text("CopsNRobbers Mode", 540-surface.textWidth("CopsNRobbers Mode")/2, 300);
		surface.textSize(10);
		surface.text("1. Randomly splits half the group into cops and robbers",  540-surface.textWidth("1. Randomly splits half the group into cops and robbers")/2, 350);
		surface.text("2. Once a cop touches a robber they go the jail",  540-surface.textWidth("2. Once a cop touches a robber they go the jail")/2, 370);
		surface.text("3. To get out of jail you have to run past the jail guard",  540-surface.textWidth("3. To get out of jail you have to run past the jail guard")/2, 390);
		surface.text("4. If time runs out and there are still robbers alive cops lose",  540-surface.textWidth("4. If time runs out and there are still robbers alive cops lose")/2, 410);
		surface.textSize(20);
		surface.text("Normal Mode", 540-surface.textWidth("Normal Mode")/2, 470);
		surface.textSize(10);
		surface.text("1. Normal tag where there is one tagger to start off",  540-surface.textWidth("1. Normal tag where there is one tagger to start off")/2, 490);
		surface.text("2. Once a tagger touches a hider they turn into a tagger too",  540-surface.textWidth("2. Once a tagger touches a hider they turn into a tagger too")/2, 510);
		surface.text("3. If everyone gets tagged taggers win",  540-surface.textWidth("3. If everyone gets tagged taggers win")/2, 530);
		surface.text("4. If time runs out and there are still runners left then runners win",  540-surface.textWidth("4. If time runs out and there are still runners left then runners win")/2, 550);
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
