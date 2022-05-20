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
		playAgain = new Rectangle(990,690,70,20);

	}
	/**
	 * Standard processing draw method
	 */
	public void draw() {
		surface.clear();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		surface.textSize(10);
		
		surface.pushStyle();
		surface.background(255,255,255);
		surface.fill(0,0,0);
		if(!MultiplayerOrNetwork.network) {
			if(surface.getGameMode() == 1) {
			String s = NormalMapScreen.currentRunner;
			surface.fill(0,0,0);
			surface.textSize(50);
			surface.text(s + " WON THE ROUND!", DRAWING_WIDTH/2 - surface.textWidth(s + " WON THE ROUND!")/2, DRAWING_HEIGHT/2);
			
			surface.fill(0,0,0);
			surface.rect(mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
			surface.fill(255,255,255);
			surface.textSize(13);
			surface.text("Main Menu", mainMenu.x+mainMenu.width/2-surface.textWidth("Main Menu")/2, mainMenu.y + mainMenu.height/2 + 4);
			
			surface.fill(0,0,0);
			surface.rect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);
			surface.fill(255,255,255);
			surface.textSize(13);
			surface.text("Play Again", playAgain.x+playAgain.width/2-surface.textWidth("Play Again")/2, playAgain.y + playAgain.height/2 + 4);
			} else if(surface.getGameMode() == 2) {
				String s = NormalMapFreezeTagScreen.currentRunner[0] + " and " + NormalMapFreezeTagScreen.currentRunner[1];
				surface.fill(0,0,0);
				surface.textSize(50);
				surface.text(s + " WON THE ROUND!", DRAWING_WIDTH/2 - surface.textWidth(s + " WON THE ROUND!")/2, DRAWING_HEIGHT/2);
				
				surface.fill(0,0,0);
				surface.rect(mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
				surface.fill(255,255,255);
				surface.textSize(13);
				surface.text("Main Menu", mainMenu.x+mainMenu.width/2-surface.textWidth("Main Menu")/2, mainMenu.y + mainMenu.height/2 + 4);
				
				surface.fill(0,0,0);
				surface.rect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);
				surface.fill(255,255,255);
				surface.textSize(13);
				surface.text("Play Again", playAgain.x+playAgain.width/2-surface.textWidth("Play Again")/2, playAgain.y + playAgain.height/2 + 4);
			}
		}
		else {
			String s = "";
			if(!surface.getRoundWinner()) {//runners win
				s = "RUNNERS";
			}
			else
				s = "TAGGERS";
			surface.fill(0,0,0);
			surface.textSize(50);
			surface.text(s + " WON THE ROUND!", DRAWING_WIDTH/2 - surface.textWidth(s + " WON THE ROUND!")/2, DRAWING_HEIGHT/2);
		}
		surface.popStyle();
	}
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(!MultiplayerOrNetwork.network) {
			if (mainMenu.contains(p)) {
				surface.switchScreen(ScreenSwitcher.MENU);
				surface.refresh1v1();
			}
			
			if (playAgain.contains(p)) {
				if(surface.getGameMode() == 1) {
				surface.refresh1v1();
				surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
				}
				if(surface.getGameMode() == 2) {
					surface.refresh1v1();
					surface.switchScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN);
				}
			}
		}
	}
}
