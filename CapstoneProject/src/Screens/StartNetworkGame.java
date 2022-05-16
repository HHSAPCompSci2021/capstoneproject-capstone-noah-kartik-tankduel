package Screens;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Queue;

import Player.Player;
import System.DrawingSurface;
import networking.frontend.NetworkDataObject;
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
	private static final String messageTypeStartGame = "START_GAME";
		
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
		surface.textSize(10);
		
		surface.pushStyle();
		surface.rect(start.x, start.y, start.width, start.height);
		surface.fill(0,0,0);
		surface.textSize(50);
		surface.text("START", start.x+start.width/2-surface.textWidth("START")/2, start.y +70);
		
		
		if (((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger() == null)
			return;
		
		Queue<NetworkDataObject> queue = ((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
		while (!queue.isEmpty()) {
			NetworkDataObject ndo = queue.poll();

			String host = ndo.getSourceIP();

			if (ndo.messageType.equals(NetworkDataObject.MESSAGE)) {
				if (ndo.message[0].equals(messageTypeStartGame)) {
					if((boolean) ndo.message[1])
						surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
				}
			}
		}
		surface.popStyle();
	}
	

	/**
	 * Actions for when mouse is pressed
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(start.contains(p) && NetworkManagementPanel.connectedSuccess && NetworkManagementPanel.isHost) {
			surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
			((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true);
		}
	}
}
