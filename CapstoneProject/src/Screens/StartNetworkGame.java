package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
import networking.frontend.NetworkManagementPanel;

/**
 * Lets you choose if you want to play 1v1 on your computer or if you want to play network
 * @author npien
 *
 */
public class StartNetworkGame extends Screens{
	private DrawingSurface surface;
	private Rectangle start;
	private NetworkManagementPanel nmp;
		
	public StartNetworkGame(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
		start = new Rectangle(440,310,200,100);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
		surface.clear();
		surface.background(0,0,0);
	
		surface.fill(255,255,255);
		surface.rect(start.x, start.y, start.width, start.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("START", start.x+start.width/2-surface.textWidth("START")/2, start.y +70);

	}
	

	/**
	 * Actions for when mouse is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(start.contains(p) && NetworkManagementPanel.connectedSuccess && NetworkManagementPanel.isHost) {
			surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
		}
	}
	
	public void startGame() {
		surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
	}

}
