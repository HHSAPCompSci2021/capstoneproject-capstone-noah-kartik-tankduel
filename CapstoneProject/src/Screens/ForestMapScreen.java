//package Screens;
//
//import System.DrawingSurface;
//import processing.core.PImage;
//
//public class ForestMapScreen extends Screens{
//
//	private DrawingSurface surface;
//	private PImage forest;
//	public ForestMapScreen(DrawingSurface surface) {
//		super(1080, 720);
//		this.surface = surface;
//		// TODO Auto-generated constructor stub
//	}
//	public void setup() {
//		forest = surface.loadImage("img/forest.jpg");
//	}
//	public void draw() {
//		surface.clear();
//		surface.pushStyle();
//		surface.image(forest, 0, 0, 1080, 720);
//		surface.popStyle();
//	}
//
//}
package Screens;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Queue;
import Player.Player;
import SpecialAbilities.*;
import System.DrawingSurface;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkManagementPanel;
import networking.frontend.NetworkMessenger;
import processing.core.PImage;
/**
 * The normal game mode for the forest map
 * @author Noah Pien and Kartik Joshi
 */
public class ForestMapScreen extends Screens implements NetworkListener{
	private DrawingSurface surface;
	private Line2D l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35;
	private Player p;
	private ArrayList<Player> players;
	private static DiveTag diveTag;
	private static HighJump highJump;
	private static SneakyCloak sneakyCloak;
	private static SpeedBoost speedBoost;
	private static SpecialAbilities[] abilities;
	private Player r;
	private Player t;
	private long taggedTime;
	public static boolean roundWinner;
	public static String currentRunner;
	private int repeatName;
	private NetworkMessenger nm;
	private boolean hostIsDead;
	public static boolean abilityUseable;
	private PImage forest;
	private static final String messageTypeCurrentLocation = "CURRENT_LOCATION";
	private static final String messageTypeInit = "CREATE_PLAYER";
	private static final String messageTypeRemovePlayer = "REMOVE_PLAYER";
	private static final String messageTypeSetTagger = "SET_TAGGER";
	private static final String messageTypeGameOver = "GAME_OVER";
	private static final String messageTypeInvisible = "INVISIBLE";
	private static final String messageTypeInvisibleOff = "INVISIBLE_OFF";
	private static final String messageTypeDiveTag = "DIVETAG";
	private static final String messageTypeDiveOff = "DIVE_OFF";
	private Rectangle ground;
	private Rectangle forests;
	private Rectangle tree1;
	private Rectangle tree2;
	private Rectangle tree3;
	private Rectangle tree4;	
	/* The area created by spawnX and spawnY will be the spawn area.
	 * When the 10 second count down to START the game reaches 0,
	 * the spawnY line will disappear and only the 
	 * runners will be able to move. After 15 seconds,
	 *  taggers will be able to move.
	 */
	private Line2D spawnX, spawnY;

	private Line2D border1,border2,border3,border4;
	private Line2D[] platforms;
	int timer;
	double curTime;
	boolean first;
	boolean second;
	boolean third;

	int firstRun;
	int check;
	
	/**
	 * Draws the forest map screen 
	 * @param surface - DrawingSurface PApplet
	 */

	public ForestMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		p =new Player(50,50);
		players = new ArrayList<Player>();
		players.add(p);
		// X:1080 by Y:720 range lines, make sure that x1 < x2
		l0 = new Line2D.Double (450,450,500,400);
		l1 = new Line2D.Double (300,500,400,600);
		l2 = new Line2D.Double (700,300,800,200);
		l3 = new Line2D.Double (668,2,668,2.5);
		l4 = new Line2D.Double (500,500,600,500);
		l5 = new Line2D.Double (300,70,500,70);
		l6 = new Line2D.Double (780,70,1010,70);
		l7 = new Line2D.Double (400,270,650,150);
		l8 = new Line2D.Double (50,300,200,425);
		l9 = new Line2D.Double (829,375,1050,300);
		l10 = new Line2D.Double (50,550,230,550);
		l11 = new Line2D.Double (849,550,1050,550);
		l12 = new Line2D.Double (400,500,630,500);
		l13 = new Line2D.Double (480,360,700,360);
		l14 = new Line2D.Double (600,620,800,450);
		l15 = new Line2D.Double (250,250,350,350);
		l16 = new Line2D.Double (200,500,500,300);
		l17 = new Line2D.Double (650,2,700,50);
		l18 = new Line2D.Double (320,690,480,640);
		l19 = new Line2D.Double (500,590,600,690);
		l20 = new Line2D.Double (100,250,280,140);
		l21 = new Line2D.Double (200,50,250,100);
		l22 = new Line2D.Double (300,200,450,175);
		l23 = new Line2D.Double (670,100,770,150);
		l24 = new Line2D.Double (595,275,625,255);
		l25 = new Line2D.Double (0,400,70,450);
		l26 = new Line2D.Double (40,600,100,660);
		l27 = new Line2D.Double (850,170,950,270);
		l28 = new Line2D.Double (1020,240,1080,180);
		l29 = new Line2D.Double (790,570,880,680);
		l30 = new Line2D.Double (1000,680,1080,600);
		l31 = new Line2D.Double (980,450,1080,340);
		l32 = new Line2D.Double (840,425,970,505);
		l33 = new Line2D.Double (500,150,550,100);
		l34 = new Line2D.Double (200,680,280,550);
		l35 = new Line2D.Double (730,400,780,400);
		ground = new Rectangle(0,520,1080,200);
		spawnX = new Line2D.Double(0,150,150,150);
		spawnY = new Line2D.Double(150,0,150,150);
		tree1 = new Rectangle(120,320,20,200);
		tree2 = new Rectangle(400,320,20,200);
		tree3 = new Rectangle(680,320,20,200);
		tree4 = new Rectangle(960,320,20,200);
		t = new Player(0,0);
		r = new Player(50,50);
		if(Math.random() < 0.5) 
			t.setPlayerType(true);
		else 
			r.setPlayerType(true);
		
		
		border1 = new Line2D.Double (0, 0, 1080, 0);
		border2 = new Line2D.Double (0, 0, 0, 720);
		border3 = new Line2D.Double (0, 720, 1080, 720);
		border4 = new Line2D.Double (1080, 720, 1080, 0);
		
		platforms = new Line2D[] {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35,spawnX,spawnY,border1,border2,border3,border4};
		
		diveTag = new DiveTag((int)(Math.random()*1060)+10,(int)(Math.random()*700)+10);
		speedBoost = new SpeedBoost((int)(Math.random()*1060)+10,(int)(Math.random()*700)+10);
		highJump = new HighJump((int)(Math.random()*1060)+10,(int)(Math.random()*700)+10);
		sneakyCloak = new SneakyCloak((int)(Math.random()*1060)+10,(int)(Math.random()*700)+10);
		
		abilities = new SpecialAbilities[] {diveTag,speedBoost,highJump,sneakyCloak};

		timer = 6;
		
		curTime = System.currentTimeMillis();
		first = true;
		second = false;
		third = false;
		
		firstRun = 0;
		check = 0;
		repeatName = 1;
	}
	/**
	 * Standard drawing in processing
	 */
	public void draw() {
		surface.clear();
		surface.textSize(10);
		surface.pushStyle();
		surface.background(136,206,235);
		surface.fill(109,74,59);
		surface.rect(ground.x, ground.y, ground.width, ground.height);
		surface.fill(101,67,33);
		surface.rect(tree1.x, tree1.y, tree1.width, tree1.height);
		surface.rect(tree2.x, tree2.y, tree2.width, tree2.height);
		surface.rect(tree3.x, tree3.y, tree3.width, tree3.height);
		surface.rect(tree4.x, tree4.y, tree4.width, tree4.height);
		surface.fill(66,105,47);
		surface.strokeWeight(5);
		surface.stroke(1,50,32);
		surface.triangle(50,375,130,250,210,375);
		surface.triangle(50,275,130,150,210,275);
		surface.triangle(50, 175, 130, 75, 210, 175);
		
		surface.triangle(330,375,410,250,490,375);
		surface.triangle(330,275,410,150,490,275);
		surface.triangle(330,175,410,75, 490, 175);
		
		surface.triangle(610,375,690,250,760,375);
		surface.triangle(610,275,690,150,760,275);
		surface.triangle(620, 175, 690, 75, 760, 175);
		
		surface.triangle(890,375,970,250,1040,375);
		surface.triangle(890,275,970,150,1040,275);
		surface.triangle(890, 175, 970, 75, 1040, 175);
		surface.stroke(2);
		if(MultiplayerOrNetwork.network&& firstRun <=3) {
			p.name = MultiplayerOrNetwork.playerName;
			nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInit, p.x, p.y,MultiplayerOrNetwork.playerName);
			firstRun++;
		}
		if(MultiplayerOrNetwork.network) {//sets up who is tagger for networking
			if(NetworkManagementPanel.isHost && check == 0) {
				if(players.size()== StartNetworkGame.numberOfPlayers) {
					check =1;
					if(StartNetworkGame.numberOfPlayers == 1)
						;
					else if(StartNetworkGame.numberOfPlayers<=4) {
						int a = (int)(Math.random()*players.size());
						players.get(a).setPlayerType(true);
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeSetTagger, players.get(a));
						
					}
					else {
						int a = (int)(Math.random()*players.size());
						players.get(a).setPlayerType(true);
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeSetTagger,players.get(a));
						int b = (int)(Math.random()*players.size());
						while (a== b)
							b = (int)(Math.random()*players.size());
						players.get(b).setPlayerType(true);
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeSetTagger, players.get(b));
						
					}
				}
			}
		}
		if(!MultiplayerOrNetwork.network) {
			surface.textSize(15);
			surface.fill(0,0,0);
			if(!t.invisible) {
				surface.text(Start1v1Game.player1, (float)t.x - surface.textWidth(Start1v1Game.player1)/2 + (float)t.getWidth()/2, (float)(t.y -3.0));
			}
			if(!r.invisible){
				surface.text(Start1v1Game.player2, (float)r.x - surface.textWidth(Start1v1Game.player2)/2 + (float)r.getWidth()/2, (float)(r.y - 3.0));

			}
		}
		if(MultiplayerOrNetwork.network){
			surface.textSize(15);
			for(Player a: players) {
				surface.fill(0,0,0);
				if(!a.getInvisible())
					surface.text(a.name, (float)a.x - surface.textWidth(a.name)/2 + (float)a.getWidth()/2, (float)(a.y -3.0));

			}
		}

		boolean k = true;
		for(Player p: players) {
			if(p.getPlayerType() == false)
				k = false;
			if(p.invisible && !p.invisUsed) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInvisible, p.name);
				p.invisUsed = true;
				
			}
			if(p.invisUsed && p.turnInvisOff) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInvisibleOff, p.name);
				p.turnInvisOff = false;
				
			}
			
			if(p.dive && !p.diveUsed) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeDiveTag, p.name);
				p.diveUsed = true;
				
			}
			if(p.diveUsed && p.turnDiveOff) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeDiveOff, p.name);
				p.turnDiveOff = false;
				
			}
		}
		if(k) {
			third = false;
			roundWinner = true;
			nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeGameOver, true);
			
			surface.switchScreen(ScreenSwitcher.ROUND_OVER);
		}
			
		
		//window border lines
		surface.pushStyle();
		surface.strokeWeight(8);
		surface.stroke(25,255,255);
		surface.line(0, 0, 1080, 0);
		surface.line(0, 0, 0, 720);
		surface.line(0, 720, 1080, 720);
		surface.line(1080, 720, 1080, 0);
		surface.popStyle();
		
		surface.strokeWeight(2);
		if(MultiplayerOrNetwork.network) {
			for(Player c:players) {
				c.draw(surface);
			}
		}
		if(!MultiplayerOrNetwork.network) {
			t.draw(surface);
			r.draw(surface);
//			surface.textSize(5);
//			surface.fill(0,0,0);
//			if(t.getPlayerType())
//				surface.text("Tagger", (float)t.x - surface.textWidth("Tagger")/2 + (float)t.getWidth()/2, (float)t.y +5);
//			else
//				surface.text("Runner", (float)t.x - surface.textWidth("Runner")/2 + (float)t.getWidth()/2, (float)t.y +5);
//			if(r.getPlayerType())
//				surface.text("Tagger", (float)r.x - surface.textWidth("Tagger")/2 + (float)r.getWidth()/2, (float)r.y +5);
//			else
//				surface.text("Runner", (float)r.x - surface.textWidth("Runner")/2 + (float)r.getWidth()/2, (float)r.y +5);
		}
		//Platforms
		surface.strokeWeight(5);
		for(Line2D l: platforms) {
			if(l.getY1() == 2 && l.getY2() == 2.5) {
				surface.pushStyle();
				surface.strokeWeight(0.1f);
				surface.line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
				surface.strokeWeight(5);
				surface.popStyle();
				continue;
			}
			surface.line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
		}
		if(MultiplayerOrNetwork.network) {
			p.walk(0);
			if(surface.getInputMethod()) {
				if(!((first || second) && p.getPlayerType())) {

					if (surface.isPressed(KeyEvent.VK_LEFT))
						p.walk(-1);
					if (surface.isPressed(KeyEvent.VK_RIGHT))
						p.walk(1);
					if (surface.isPressed(KeyEvent.VK_UP))
						p.jump();
				}
			}
			else {
				if(!((first || second) && p.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_A))
						p.walk(-1);
					if (surface.isPressed(KeyEvent.VK_D))
						p.walk(1);
					if (surface.isPressed(KeyEvent.VK_W))
						p.jump();
				}
			}
		}
		
		if(!MultiplayerOrNetwork.network) {
			t.walk(0);
			r.walk(0);
			
				if(!((first || second) && t.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_LEFT))
						t.walk(-1);
					if (surface.isPressed(KeyEvent.VK_RIGHT))
						t.walk(1);
					if (surface.isPressed(KeyEvent.VK_UP))
						t.jump();
				}
				if(!((first || second) && r.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_A)) 
						r.walk(-1);
					if (surface.isPressed(KeyEvent.VK_D)) 
						r.walk(1);
					if (surface.isPressed(KeyEvent.VK_W)) 
						r.jump();
				}
		}
		
		if(MultiplayerOrNetwork.network && !hostIsDead) {
			p.act(platforms,abilities);
		}
		
		if(!MultiplayerOrNetwork.network) {
			t.act(platforms, abilities);
			r.act(platforms, abilities);
		}		
		
		surface.pushStyle();
		surface.fill(255,0,0);
		if(first) {
			if(System.currentTimeMillis()-curTime >1000) {
				timer -= 1;
				curTime = System.currentTimeMillis();
				if(timer == 0) {
					first = false;
					second = true;
					timer = 10;
					platforms[37] = new Line2D.Double(0,0,0,0);
				}
			}
			surface.textSize(30);
			surface.text("GET READY! " + timer, 450, 50);
			surface.popStyle();
		}
		else if(second) {
			if(System.currentTimeMillis()-curTime >1000) {
				timer -= 1;
				curTime = System.currentTimeMillis();
				if(timer == 0) {
					second = false;
					third = true;
					timer = 180;
					abilityUseable = true;
				}
			}
			surface.textSize(25);
			surface.text("RUNNERS RUN! " + timer, 450, 50);
			surface.popStyle();
		}
		else if(third) {
			if(System.currentTimeMillis()-curTime >1000) {
				timer -= 1;
				curTime = System.currentTimeMillis();
				if(timer == 0) {
					third = false;
					if(!t.getPlayerType())
						currentRunner = Start1v1Game.player1;
					else
						currentRunner = Start1v1Game.player2;
					roundWinner = false;
					if(MultiplayerOrNetwork.network) {
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeGameOver, false);
						
					}
					surface.switchScreen(ScreenSwitcher.ROUND_OVER);
					
				}
			}
			surface.textSize(50);
			surface.text(timer, 500, 50);
			surface.popStyle();
		}
		if(third) {
			surface.pushStyle();
			surface.strokeWeight(1);
			diveTag.draw(surface);
			speedBoost.draw(surface);
			highJump.draw(surface);
			sneakyCloak.draw(surface);
			surface.popStyle();
		}
	
		if(MultiplayerOrNetwork.network) {
			nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeCurrentLocation, p.x,p.y);
			
		}
		if(!MultiplayerOrNetwork.network) {
			if(t.intersects(r) && !(first || second)) {
				if(System.currentTimeMillis()-taggedTime>3000) {
					taggedTime = System.currentTimeMillis();
					r.setPlayerType(!r.getPlayerType());
					t.setPlayerType(!t.getPlayerType());
				}
			}
		}
		boolean remove = false;
		if(MultiplayerOrNetwork.network) {
			if(NetworkManagementPanel.isHost && !(first||second)) {
				for(int i = 0; i<players.size();i++) {
					for(int j = 1; j<players.size();j++) {
						if(players.get(i).intersects(players.get(j)) && players.get(i).getPlayerType()!=players.get(j).getPlayerType()) {
							if(!players.get(i).getPlayerType()) {
								if(players.get(i).equals(p))
									hostIsDead = true;
								nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeRemovePlayer, players.remove(i));
								remove = true;
							}
							else {
								if(players.get(j).equals(p))
									hostIsDead = true;
								nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeRemovePlayer, players.remove(j));
								remove = true;
							}
						}
						if(remove)
							break;
					}
					if(remove)
						break;
				}
			}
		}
		surface.popStyle();
		
		if(MultiplayerOrNetwork.network) {
			processNetworkMessages();
		}
	}
	
	/**
	 * receives the messages from other computers
	 */
	public void processNetworkMessages() {
		
		if (nm == null)
			return;
		
		
		Queue<NetworkDataObject> queue = nm.getQueuedMessages();
		while (!queue.isEmpty()) {
			NetworkDataObject ndo = queue.poll();

			String host = ndo.getSourceIP();

			if (ndo.messageType.equals(NetworkDataObject.MESSAGE)) {
				if (ndo.message[0].equals(messageTypeCurrentLocation)) {
					
						for (Player c : players) {
							if (c.host.equals(host)) {
								c.x = (double)ndo.message[1];
								c.y = (double)ndo.message[2];
							}
						}
				}
				else if (ndo.message[0].equals(messageTypeInit)) {
					
					for (Player c : players) {
						if (c.host.equals(host))
							return;
					}

					Player c = new Player(50,50);
					c.x = (double) ndo.message[1];
					c.y = (double) ndo.message[2];
					String s = "";
					for(Player p : players)
						if(((String)ndo.message[3]).equals(p.name)) {
							s +=repeatName;
							repeatName++;
						}
					c.name = (String)ndo.message[3] ;
					c.host = host;
					players.add(c);

				}
				
				else if (ndo.message[0].equals(messageTypeRemovePlayer)) {
					Player s = (Player)ndo.message[1];
					for(Player a :players) {
						if(a.equals(s)) {
							s = a;
							break;
						}
					}
					players.remove(s);
				}
				
				else if (ndo.message[0].equals(messageTypeSetTagger)) {
					Player s = (Player)ndo.message[1];
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals(s.name)) {
							players.get(i).setPlayerType(true);
						}
					}
				}
				
				else if (ndo.message[0].equals(messageTypeGameOver)) {
					third = false;
					roundWinner = (boolean)ndo.message[1];
					surface.switchScreen(ScreenSwitcher.ROUND_OVER);
				}
				
				else if (ndo.message[0].equals(messageTypeInvisible)) {
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals((String)ndo.message[1])) {
							players.get(i).invisible = true;
							players.get(i).invisUsed = true;
						}
					}				
				}
				
				else if (ndo.message[0].equals(messageTypeInvisibleOff)) {
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals((String)ndo.message[1])) {
							players.get(i).invisible = false;
							players.get(i).turnInvisOff = false;
						}
					}				
				}
				
				else if (ndo.message[0].equals(messageTypeDiveTag)) {
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals((String)ndo.message[1])) {
							players.get(i).dive = true;
							players.get(i).diveUsed = true;
						}
					}				
				}
				
				else if (ndo.message[0].equals(messageTypeDiveOff)) {
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals((String)ndo.message[1])) {
							players.get(i).dive = false;
							players.get(i).turnDiveOff = false;
						}
					}				
				}
			}
			else if (ndo.dataSource.equals(ndo.serverHost)) {
				players.clear();
				players.add(p);
			} else {
					for (int i = players.size()-1; i >= 0; i--)
					if (players.get(i).host.equals(host))
						players.remove(i);
			}
			
		}


	}
	
	
	/**
	 * Gets the winner of the round of tag
	 * @return roundwinner
	 */
	public boolean getRoundWinner() {
		return roundWinner;
	}
	
	/**
	 * Makes it so that once you pick up the speed boost it disappears so you cannot pick it up again
	 */
	public static void deleteSpeed() {
		speedBoost.x = -100;
	}
	/**
	 * Makes it so that once you pick up the invisibility it disappears so you cannot pick it up again
	 */
	public static void deleteCloak() {
		sneakyCloak.x = -100;
	}
	/**
	 * Makes it so that once you pick up the delete jump it disappears so you cannot pick it up again
	 */
	public static void deleteJump() {
		highJump.x = -100;
	}

	/**
	 * Makes it so that once you pick up the deleteDive it disappears so you cannot pick it up again
	 */
	public static void deleteDive() {
		diveTag.x = -100;
	}
	@Override
	/**
	 * connects to the messenger
	 */
	public void connectedToServer(NetworkMessenger nm) {
		this.nm = nm;
	}
	
	/**
	 * gets the messenger
	 * @return the messenger
	 */
	public NetworkMessenger getNetworkMessenger() {
		return nm;
	}
	@Override
	/**
	 * the message received
	 */
	public void networkMessageReceived(NetworkDataObject ndo) {
		
	}
}
