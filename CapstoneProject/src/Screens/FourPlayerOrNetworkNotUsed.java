package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import System.DrawingSurface;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkManagementPanel;

/**
 * Lets you choose if you want to play 1v1 on your computer or if you want to play network
 * @author kartik
 *
 *
 */
public class FourPlayerOrNetworkNotUsed extends Screens{
//	
//	public static boolean network;
//	public static String playerName;
//	private Rectangle fourPlayer;
//	private Rectangle server;
//	private DrawingSurface surface;
//	private Rectangle back;
//	private Rectangle next;
//	private int method = 0;
//	public NetworkManagementPanel nmp;
	/**
	 * sets up the four player or network screen
	 * @param surface
	 */
	public FourPlayerOrNetworkNotUsed(DrawingSurface surface) {
		super(1080,720);
//		this.surface = surface;
//		fourPlayer = new Rectangle(465,200,150,30);
//		server = new Rectangle (465,300,150,30);
//		back = new Rectangle(20,690,70,20);
//		next = new Rectangle(990,690,70,20);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
//		surface.clear();
//		surface.background(0,0,0);
//		surface.fill(255,255,255);
//		surface.textSize(10);
//		
//		surface.pushStyle();
//		surface.textSize(30);
//		surface.text("Choose type of method to play",540-surface.textWidth("Choose type of method to play")/2,70);
//		
//		surface.textSize(15);
//		if(method == 1) {
//			surface.fill(25,255,255);
//			surface.rect(fourPlayer.x-5, fourPlayer.y-5, fourPlayer.width+10, fourPlayer.height+10);
//		}
//		surface.fill(255,255,255);
//		surface.rect(fourPlayer.x, fourPlayer.y, fourPlayer.width, fourPlayer.height);	
//		surface.fill(0,0,0);
//		surface.text("FOUR PLAYER", fourPlayer.x+fourPlayer.width/2-surface.textWidth("TWO PLAYER")/2, fourPlayer.y +20);
//		
//		if(method == 2) {
//			surface.fill(25,255,255);
//			surface.rect(server.x-5, server.y-5, server.width+10, server.height+10);
//		}
//		surface.fill(255,255,255);
//		surface.rect(server.x, server.y, server.width, server.height);	
//		surface.fill(0,0,0);
//		surface.text("NETWORK", server.x+server.width/2-surface.textWidth("NETWORK")/2, server.y+20);
//		surface.fill(255,255,255);
//
//		surface.text("Not yet coded", server.x+server.width, server.y+20);
//
//		
//		
//		
//		surface.rect(back.x, back.y, back.width, back.height);
//		surface.fill(0,0,0);
//		surface.textSize(20);
//		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
//		
//		surface.fill(255,255,255);
//		surface.rect(next.x, next.y, next.width, next.height);
//		surface.fill(0,0,0);
//		surface.textSize(20);
//		surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);
//		surface.popStyle();
	}
	

	/**
	 * Actions for when mouse is pressed
	 */
	public void mousePressed() {
//		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
//		if(fourPlayer.contains(p)) {
//			method = 1;
//		}
//		if(server.contains(p)){
//			method = 2;
//		}
//
//		if (back.contains(p) )
//			surface.switchScreen(ScreenSwitcher.PLAYSCREEN1);
//		if (next.contains(p) && method == 1)
//			surface.switchScreen(ScreenSwitcher.START1V1GAME);
//		if (next.contains(p) && method == 2) {
//			while(playerName == null || playerName.equals("")) {
//				playerName = JOptionPane.showInputDialog("Enter a name!");
//			}
//			nmp = new NetworkManagementPanel("ProcessingDrawing", 10, (NetworkListener) surface.getScreen(4));
//			surface.switchScreen(ScreenSwitcher.STARTNETWORKGAME);
//			network = true;
//		}
	}

}
