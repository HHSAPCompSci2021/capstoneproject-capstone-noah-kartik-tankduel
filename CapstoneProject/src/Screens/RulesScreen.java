package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
/**
 * This screen shows the rules of the game
 * 
 * @author Noah Pien and Kartik Joshi
 */
public class RulesScreen extends Screens{

	private Rectangle back;
	private Rectangle next;
	private DrawingSurface surface;
	
	/**
	 * Gives each RulesScreen the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public RulesScreen(DrawingSurface surface) {
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
		surface.text("RULES", 540-surface.textWidth("RULES")/2,70);
		
		surface.fill(255,255,255);
		surface.textSize(35);
		surface.text("Freeze Mode", 540-surface.textWidth("Freeze Mode")/2, 120);
		surface.color(150);
		surface.textSize(25);
		surface.text("1. If you are a runner and you get tagged you will be frozen",  540-surface.textWidth("1. If you are a runner and you get tagged you will be frozen")/2, 170);
		surface.text("2. If one person gets tagged three times the runners lose and the taggers win",  540-surface.textWidth("2. If one person gets tagged three times the runners lose and the taggers win")/2, 195);
		surface.text("3. If runners survive until the time ends they win",  540-surface.textWidth("3. If runners survive until the time ends they win")/2, 220);
		surface.text("4. Taggers must try to tag the runners",  540-surface.textWidth("4. Taggers must try to tag the runners")/2, 245);
		surface.textSize(35);
		surface.text("Cops N Robbers Mode", 540-surface.textWidth("Cops N Robbers Mode")/2, 332.5f);
		surface.textSize(25);
		surface.text("1. Randomly splits half the group into cops and robbers",  540-surface.textWidth("1. Randomly splits half the group into cops and robbers")/2, 382.5f);
		surface.text("2. The goal of the runners is to run away from the cops until the time runs out",  540-surface.textWidth("2. The goal of the runners is to run away from the cops until the time runs out")/2, 407.5f);
		surface.text("3. The cop's goal is to stop the robbers from winning by tagging and putting robbers in jail",  540-surface.textWidth("3. The cop's goal is to stop the robbers from winning by tagging and putting robbers in jail")/2, 432.5f);
		surface.text("4. A robber in jail can be saved if they are tagged by another robber",  540-surface.textWidth("4. A robber in jail can be saved if they are tagged by another robber")/2, 457.5f);


		surface.textSize(35);
		surface.text("Normal Mode", 540-surface.textWidth("Normal Mode")/2, 545);
		surface.textSize(25);
		surface.text("1. The runners try to not get tagged by the taggers",  540-surface.textWidth("1. The runners try to not get tagged by the taggers")/2, 595);
		surface.text("2. The taggers chase after the runners to try and tag them",  540-surface.textWidth("2. The taggers chase after the runners to try and tag them")/2, 620);
		surface.text("3. Runners who are tagged are \"out\" of the game and can only spectate",  540-surface.textWidth("3. Runners who are tagged are \\\"out\\\" of the game and can only spectate")/2, 645);
		surface.text("4. When time is up, if there are runners left, runners win, otherwise taggers win",  540-surface.textWidth("4. When time is up, if there are runners left, runners win, otherwise taggers win")/2, 670);


		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(next.x, next.y, next.width, next.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);
		surface.popStyle();
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU);
		if (next.contains(p))
			surface.switchScreen(ScreenSwitcher.ABILITIES);
	}
	
}
