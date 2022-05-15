package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkManagementPanel;

/**
 * Lets you choose if you want to play 1v1 on your computer or if you want to play network
 * @author npien
 *
 */
public class TwoPlayerOrNetwork extends Screens{
	
	public static boolean network;
	private Rectangle twoPlayer;
	private Rectangle server;
	private DrawingSurface surface;
	private Rectangle back;
	private Rectangle next;
	private int method = 0;
	private NetworkManagementPanel nmp;
	
	public TwoPlayerOrNetwork(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
		twoPlayer = new Rectangle(465,200,150,30);
		server = new Rectangle (465,300,150,30);
		back = new Rectangle(20,690,70,20);
		next = new Rectangle(990,690,70,20);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
		surface.clear();
		surface.background(0,0,0);
	
		surface.fill(255,255,255);
		surface.text("Choose type of method to play",540-surface.textWidth("Choose type of method to play")/2,70);
		
		if(method == 1) {
			surface.fill(25,255,255);
			surface.rect(twoPlayer.x-5, twoPlayer.y-5, twoPlayer.width+10, twoPlayer.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(twoPlayer.x, twoPlayer.y, twoPlayer.width, twoPlayer.height);	
		surface.fill(0,0,0);
		surface.text("TWO PLAYER", twoPlayer.x+twoPlayer.width/2-surface.textWidth("TWO PLAYER")/2, twoPlayer.y +20);
		
		if(method == 2) {
			surface.fill(25,255,255);
			surface.rect(server.x-5, server.y-5, server.width+10, server.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(server.x, server.y, server.width, server.height);	
		surface.fill(0,0,0);
		surface.text("NETWORK", server.x+server.width/2-surface.textWidth("NETWORK")/2, server.y+20);
		surface.fill(255,255,255);

		surface.text("Not yet coded", server.x+server.width, server.y+20);

		
		
		
		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(next.x, next.y, next.width, next.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);

	}
	

	/**
	 * Actions for when mouse is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(twoPlayer.contains(p)) {
			method = 1;
		}
		if(server.contains(p)){
			method = 2;
		}

		if (back.contains(p) )
			surface.switchScreen(ScreenSwitcher.PLAYSCREEN1);
		if (next.contains(p) && method == 1)
			surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
		if (next.contains(p) && method == 2) {
			nmp = new NetworkManagementPanel("ProcessingDrawing", 10, (NetworkListener) surface.getScreen(4));
			surface.switchScreen(ScreenSwitcher.STARTNETWORKGAME);
			network = true;
		}
	}

}
