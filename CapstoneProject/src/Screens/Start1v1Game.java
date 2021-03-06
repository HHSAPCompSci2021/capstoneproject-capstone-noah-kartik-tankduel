package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import System.DrawingSurface;


/**
 * Lets you choose if you want to play 1v1 on your computer or if you want to play network
 * @author Noah Pien and Kartik Joshi
 *
 */
public class Start1v1Game extends Screens{
	private DrawingSurface surface;
	private Rectangle start;
	public static String player1;
	public static String player2;
	public static String player3;
	public static String player4;
	/**
	 * sets up the start 1v1 game screen
	 * @param surface the surface on which to draw on
	 */
	public Start1v1Game(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
		start = new Rectangle(440,310,200,100);
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
		surface.clear();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		surface.textSize(10);
		surface.fill(255,255,255);
		surface.rect(start.x, start.y, start.width, start.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("START", start.x+start.width/2-surface.textWidth("START")/2, start.y +70);
		surface.pushStyle();
		if(surface.getGameMode() == 1) {
			if(player1 == null || player1.equals("")) {
				player1 = JOptionPane.showInputDialog("Enter player one's name!");
			}
			if(player2 == null || player2.equals("")) {
				player2 = JOptionPane.showInputDialog("Enter player two's name!");

			}
		}
		if(surface.getGameMode() == 2) {
			if(player1 == null || player1.equals("")) {
				player1 = JOptionPane.showInputDialog("Enter player one's name!");
			}
			if(player2 == null || player2.equals("")) {
				player2 = JOptionPane.showInputDialog("Enter player two's name!");

			}
			if(player3 == null || player3.equals("")) {
				player3 = JOptionPane.showInputDialog("Enter player three's name!");
			}
			if(player4 == null || player4.equals("")) {
				player4 = JOptionPane.showInputDialog("Enter player four's name!");

			}
		}
		
		if(surface.getGameMode() == 3) {
			if(player1 == null || player1.equals("")) {
				player1 = JOptionPane.showInputDialog("Enter player one's name!");
			}
			if(player2 == null || player2.equals("")) {
				player2 = JOptionPane.showInputDialog("Enter player two's name!");

			}
			if(player3 == null || player3.equals("")) {
				player3 = JOptionPane.showInputDialog("Enter player three's name!");
			}
			if(player4 == null || player4.equals("")) {
				player4 = JOptionPane.showInputDialog("Enter player four's name!");

			}
		}
		surface.popStyle();
		
	}
	

	/**
	 * Actions for when mouse is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(start.contains(p)) {
			if(surface.getMap() == 0 && surface.getGameMode() == 1)
				surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
			if(surface.getMap() == 0 && surface.getGameMode() == 2)
				surface.switchScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN);
			if(surface.getMap() == 0 && surface.getGameMode() == 3)
				surface.switchScreen(ScreenSwitcher.COPSNROBBERS);
			if(surface.getMap() == 1)
				surface.switchScreen(ScreenSwitcher.WATERMAP);
			if(surface.getMap() == 1 && surface.getGameMode() == 2)
				surface.switchScreen(ScreenSwitcher.FREEZETAGWATERMAPSCREEN);
			if(surface.getMap()  == 1 && surface.getGameMode() == 3)
				surface.switchScreen(ScreenSwitcher.COPSNROBBERSWATERMAPSCREEN);
			if(surface.getMap() == 2 && surface.getGameMode() == 1)
				surface.switchScreen(ScreenSwitcher.FORESTMAP);	
			if(surface.getMap() == 2 && surface.getGameMode() == 2){
				surface.switchScreen(ScreenSwitcher.FORESTMAPFREEZETAG);
			}
			if(surface.getMap() == 2 && surface.getGameMode() == 3) {
				surface.switchScreen(ScreenSwitcher.FORESTMAPCOPSNROBBERS);
			}
		}
	}
}
