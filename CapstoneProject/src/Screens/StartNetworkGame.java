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
	private static final String messageTypeStartGame = "START_GAME";
	public static int numberOfPlayers;
	private int check  = 1;	
	
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
		if(surface.getMapScreen() == 4)
			if (((NormalMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
				return;
		else if(surface.getMapScreen() == 15)
			if (((NormalMapFreezeTagScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
				return;
		else if(surface.getMapScreen() == 11)
			if (((WaterMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
				return;
		
		Queue<NetworkDataObject> queue = ((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
		if(surface.getMapScreen() == 15)
			queue = ((NormalMapFreezeTagScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger().getQueuedMessages();
		else if(surface.getMapScreen() == 11)
			queue = ((WaterMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger().getQueuedMessages();

		
		while (!queue.isEmpty()) {
			NetworkDataObject ndo = queue.poll();
			if (ndo.messageType.equals(NetworkDataObject.MESSAGE)) {
				if (ndo.message[0].equals(messageTypeStartGame)) {
					System.out.println("message received");
					if((boolean) ndo.message[1]) {
						if((int)ndo.message[2] == 0 && (int)ndo.message[3] == 1)
							surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
						if((int)ndo.message[2] == 0 && (int)ndo.message[3] == 2)
							surface.switchScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN);
						if((int)ndo.message[2] == 1)
							surface.switchScreen(ScreenSwitcher.WATERMAP);
						if((int)ndo.message[2] == 2)
							surface.switchScreen(ScreenSwitcher.FORESTMAP);
					}
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
			if(surface.getMap() == 0 && surface.getGameMode() == 1) {
				surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
				((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 0, 1);
			}
			if(surface.getMap() == 0 && surface.getGameMode() == 2) {
				surface.switchScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN);
				((NormalMapFreezeTagScreen) surface.getScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 0, 2);
			}
			if(surface.getMap() == 1) {
				surface.switchScreen(ScreenSwitcher.WATERMAP);
				((WaterMapScreen) surface.getScreen(ScreenSwitcher.WATERMAP)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 1);
			}
//			if(surface.getMap() == 2) {
//				surface.switchScreen(ScreenSwitcher.FORESTMAP);
//				((ForestMapScreen) surface.getScreen(ScreenSwitcher.FORESTMAP)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true);
//			}
			numberOfPlayers = PlayScreen1.nmp.numberOfPeople();
		}
	}
}
