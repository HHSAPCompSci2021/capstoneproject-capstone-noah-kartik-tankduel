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
	
	/**
	 * sets up the start network game screen
	 * @param surface the surface on which to draw on
	 */
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
		if(!NetworkManagementPanel.isHost) {
			
			if(surface.getMapScreen() == 4)
				if (((NormalMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 15)
				if (((NormalMapFreezeTagScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 17)
				if (((NormalMapCopsNRobbers) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			
			else if(surface.getMapScreen() == 11)
				if (((WaterMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 18)
				if (((WaterMapFreezeTagScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 19)
				if (((CopsNRobbersWaterMap) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			
			else if(surface.getMapScreen() == 12)
				if (((ForestMapScreen) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 20)
				if (((ForestMapFreezeTag) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			else if(surface.getMapScreen() == 21)
				if (((ForestMapCopsNRobbers) surface.getScreen(surface.getMapScreen())).getNetworkMessenger() == null)
					return;
			
			
			
			Queue<NetworkDataObject> queue = null;
			if(surface.getMapScreen() == 4)
				queue = ((NormalMapScreen) surface.getScreen(ScreenSwitcher.NORMALMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 15)
				queue = ((NormalMapFreezeTagScreen) surface.getScreen(ScreenSwitcher.FREEZETAGNORMALMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 17)
				queue = ((NormalMapCopsNRobbers) surface.getScreen(ScreenSwitcher.COPSNROBBERS)).getNetworkMessenger().getQueuedMessages();
			
			else if(surface.getMapScreen() == 11)
				queue = ((WaterMapScreen) surface.getScreen(ScreenSwitcher.WATERMAP)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 18)
				queue = ((WaterMapFreezeTagScreen) surface.getScreen(ScreenSwitcher.FREEZETAGWATERMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 19)
				queue = ((CopsNRobbersWaterMap) surface.getScreen(ScreenSwitcher.COPSNROBBERSWATERMAPSCREEN)).getNetworkMessenger().getQueuedMessages();
			
			else if(surface.getMapScreen() == 12)
				queue = ((ForestMapScreen) surface.getScreen(ScreenSwitcher.FORESTMAP)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 20)
				queue = ((ForestMapFreezeTag) surface.getScreen(ScreenSwitcher.FORESTMAPFREEZETAG)).getNetworkMessenger().getQueuedMessages();
			else if(surface.getMapScreen() == 21)
				queue = ((ForestMapCopsNRobbers) surface.getScreen(ScreenSwitcher.FORESTMAPCOPSNROBBERS)).getNetworkMessenger().getQueuedMessages();
			
			
			if(queue == null)
				return;
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
							if((int)ndo.message[2] == 0 && (int)ndo.message[3] == 3)
								surface.switchScreen(ScreenSwitcher.COPSNROBBERS);
							if((int)ndo.message[2] == 1 && (int)ndo.message[3] == 1)
								surface.switchScreen(ScreenSwitcher.WATERMAP);
							if((int)ndo.message[2] == 1 && (int)ndo.message[3] == 2)
								surface.switchScreen(ScreenSwitcher.FREEZETAGWATERMAPSCREEN);
							if((int)ndo.message[2] == 1 && (int)ndo.message[3] == 3)
								surface.switchScreen(ScreenSwitcher.COPSNROBBERSWATERMAPSCREEN);
							if((int)ndo.message[2] == 2 && (int)ndo.message[3] == 1)
								surface.switchScreen(ScreenSwitcher.FORESTMAP);
							if((int)ndo.message[2] == 2 && (int)ndo.message[3] == 2)
								surface.switchScreen(ScreenSwitcher.FORESTMAPFREEZETAG);
							if((int)ndo.message[2] == 2 && (int)ndo.message[3] == 3)
								surface.switchScreen(ScreenSwitcher.FORESTMAPCOPSNROBBERS);
						}
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
			if(surface.getMap() == 0 && surface.getGameMode() == 3) {
				surface.switchScreen(ScreenSwitcher.COPSNROBBERS);
				((NormalMapCopsNRobbers) surface.getScreen(ScreenSwitcher.COPSNROBBERS)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 0, 3);
			}
			if(surface.getMap() == 1 && surface.getGameMode() == 1) {
				surface.switchScreen(ScreenSwitcher.WATERMAP);
				((WaterMapScreen) surface.getScreen(ScreenSwitcher.WATERMAP)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 1,1);
			}
			if(surface.getMap() == 1 && surface.getGameMode() == 2) {
				surface.switchScreen(ScreenSwitcher.FREEZETAGWATERMAPSCREEN);
				((WaterMapFreezeTagScreen) surface.getScreen(ScreenSwitcher.FREEZETAGWATERMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 1,2);
			}
			if(surface.getMap() == 1 && surface.getGameMode() == 3) {
				surface.switchScreen(ScreenSwitcher.COPSNROBBERSWATERMAPSCREEN);
				((CopsNRobbersWaterMap) surface.getScreen(ScreenSwitcher.COPSNROBBERSWATERMAPSCREEN)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 1,3);
			}
			if(surface.getMap() == 2 && surface.getGameMode() == 1) {
				surface.switchScreen(ScreenSwitcher.FORESTMAP);
				((ForestMapScreen) surface.getScreen(ScreenSwitcher.FORESTMAP)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true, 2,1);
			}
			if(surface.getMap() == 2 && surface.getGameMode() == 2) {
				surface.switchScreen(ScreenSwitcher.FORESTMAPFREEZETAG);
				((ForestMapFreezeTag) surface.getScreen(ScreenSwitcher.FORESTMAPFREEZETAG)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true,2,2);
			}
			if(surface.getMap() == 2 && surface.getGameMode() == 3) {
				surface.switchScreen(ScreenSwitcher.FORESTMAPCOPSNROBBERS);
				((ForestMapCopsNRobbers) surface.getScreen(ScreenSwitcher.FORESTMAPCOPSNROBBERS)).getNetworkMessenger().sendMessage(NetworkDataObject.MESSAGE, messageTypeStartGame, true,2,3);
			}
			numberOfPlayers = PlayScreen1.nmp.numberOfPeople();
		}
	}
}
