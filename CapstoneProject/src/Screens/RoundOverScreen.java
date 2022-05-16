package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;

public class RoundOverScreen extends Screens{
	private DrawingSurface surface;
	private Rectangle mainMenu;
	private Rectangle playAgain;

	/**
	 * RoundoverScreen which appears after the round of tag is over. Call to super constructor to create a full sized window
	 * @param surface - Drawing Surface
	 */
	public RoundOverScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		mainMenu = new Rectangle(20,690,70,20);

	}
	/**
	 * Standard processing draw method
	 */
	public void draw() {
		surface.background(255,255,255);
		if(!TwoPlayerOrNetwork.network) {
			String s = NormalMapScreen.currentRunner;
			surface.fill(0,0,0);
			surface.text(s + " WON THE ROUND!", DRAWING_WIDTH/2 - surface.textWidth(s + " WON THE ROUND!")/2, DRAWING_HEIGHT/2);
			surface.fill(0,0,0);
			surface.rect(mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
			surface.fill(255,255,255);
		
			surface.text("Main Menu", mainMenu.x+mainMenu.width/2-surface.textWidth("Main Menu")/2, mainMenu.y + mainMenu.height/2 + 7);

		}
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(!TwoPlayerOrNetwork.network) {
			if (mainMenu.contains(p))
				surface.switchScreen(ScreenSwitcher.MENU);
		}
	}
}
