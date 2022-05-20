//package Screens;
//
//import java.awt.Rectangle;
//
//import System.DrawingSurface;
//import processing.core.PImage;
//
//
//public class WaterMapScreen extends Screens{
//	private DrawingSurface surface;
//	private PImage water;
//	public WaterMapScreen(DrawingSurface surface) {
//		super(1080, 720);
//		this.surface = surface;
//		// TODO Auto-generated constructor stub
//	}
//	public void setup() {
//		water = surface.loadImage("img/beach.png");
//	}
//	
//	public void draw() {
//		surface.clear();
//		surface.pushStyle();
//		surface.image(water, 0, 0, 1080, 720);
//		surface.popStyle();
//	}
//		
//}


package Screens;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
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

public class WaterMapScreen extends Screens implements NetworkListener{
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
	private boolean roundWinner;
	public static String currentRunner;
	private int repeatName;
	private NetworkMessenger nm;
	private boolean hostIsDead;
	
	private static final String messageTypeCurrentLocation = "CURRENT_LOCATION";
	private static final String messageTypeInit = "CREATE_PLAYER";
	private static final String messageTypeRemovePlayer = "REMOVE_PLAYER";
	private static final String messageTypeSetTagger = "SET_TAGGER";
	private static final String messageTypeGameOver = "GAME_OVER";
	private static final String messageTypeInvisible = "INVISIBLE";
	private static final String messageTypeInvisibleOff = "INVISIBLE_OFF";
	private static final String messageTypeDiveTag = "DIVETAG";
	private static final String messageTypeDiveOff = "DIVE_OFF";


	private PImage water;
	private Rectangle beach;

	
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
	
	public WaterMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		p =new Player(50,50);
		players = new ArrayList<Player>();
		p.host = "me!";
		players.add(p);
		beach = new Rectangle(0,520,1080,400);

		// X:1080 by Y:720 range lines, make sure that x1 < x2
		l0 = new Line2D.Double (200,50,250,50);
		l1 = new Line2D.Double (600,50,650,50);
		l2 = new Line2D.Double (900,50,1000,50);
		l3 = new Line2D.Double (1000,50,1000,100);
		l4 = new Line2D.Double (900,100,1000,100);
		l5 = new Line2D.Double (1050,75,1100,75); // edge piece just in case it glitches 
		l6 = new Line2D.Double (400,100,600,150); 
		l7 = new Line2D.Double (250,150,300,100);
		l8 = new Line2D.Double (300,150,450,225);
		l9 = new Line2D.Double (625,250,725,175);
		l10 = new Line2D.Double (950,175,1020,125);
		l11 = new Line2D.Double (1035,150,1075,200);
		l12 = new Line2D.Double (850,300,1000,300);
		l13 = new Line2D.Double (975,275,1050,275);
		l14 = new Line2D.Double (1050,275,1050,350);
		l15 = new Line2D.Double (975,350,1050,350);
		l16 = new Line2D.Double (200,150,900,500);
		l17 = new Line2D.Double (950,500,1025,400);
		l18 = new Line2D.Double (50,200,175,200);
		l19 = new Line2D.Double (25,250,100,250);
		l20 = new Line2D.Double (25,250,25,325);
		l21 = new Line2D.Double (25,325,100,325);
		l22 = new Line2D.Double (150,290,200,290);	
		l23 = new Line2D.Double (150,290,150,350);
		l24 = new Line2D.Double (150,350,200,350);
		l25 = new Line2D.Double (225,320,300,400);
		l26 = new Line2D.Double (550,370,600,400);
		l27 = new Line2D.Double (250,500,350,450);
		l28 = new Line2D.Double (75,680,150,600);
		l29 = new Line2D.Double (300,650,375,520);
		l30 = new Line2D.Double (510,520,510,650);
		l31 = new Line2D.Double (510,650,600,650);
		l32 = new Line2D.Double (400,680,550,100);
		l32 = new Line2D.Double (450,680,550,680);
		l33 = new Line2D.Double (575,520,850,540);
		l34 = new Line2D.Double(750,680,1000,550);
		l35 = new Line2D.Double(900,650,1020,650);

		spawnX = new Line2D.Double(0,150,150,150);
		spawnY = new Line2D.Double(150,0,150,150);
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
		
		platforms = new Line2D[] {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l25,l27,l28,l29,l30,l31,l32,l33,l34,l35,spawnX,spawnY,border1,border2,border3,border4};
		
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
	/**
	 * Standard drawing in processing
	 */
	}
	public void draw() {
		surface.clear();
		surface.fill(255,255,255);
		surface.textSize(10);
		surface.pushStyle();
		surface.background(0,119,190);
		surface.fill(249,215,28);
		surface.noStroke();
		surface.circle(960, 80, 100);
		surface.fill(194,178,128);
		surface.rect(beach.x, beach.y, beach.width, beach.height);
		surface.fill(0,0,0);
		surface.stroke(2);
		if(MultiplayerOrNetwork.network) {
			if(firstRun == 0) {
				p.name = MultiplayerOrNetwork.playerName;
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInit, p.x, p.y,MultiplayerOrNetwork.playerName);
				processNetworkMessages();
			}
		}
		firstRun=1;
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
						processNetworkMessages();
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
						processNetworkMessages();
					}
				}
			}
		}
		if(!MultiplayerOrNetwork.network) {
			surface.textSize(15);
			if(t.getInvisible())
				surface.fill(255,255,255);
			else
				surface.fill(0,0,0);
		//	surface.text(Start1v1Game.player1, (float)t.x - surface.textWidth(Start1v1Game.player1)/2 + (float)t.getWidth()/2, (float)(t.y -3.0));
			if(r.getInvisible())
				surface.fill(255,255,255);
			else
				surface.fill(0,0,0);
			//surface.text(Start1v1Game.player2, (float)r.x - surface.textWidth(Start1v1Game.player2)/2 + (float)r.getWidth()/2, (float)(r.y - 3.0));
		}
		if(MultiplayerOrNetwork.network){
			surface.textSize(15);
			for(Player a: players) {
				surface.fill(0,0,0);
				if(a.getInvisible())
					surface.fill(255,255,255);
			//	surface.text(a.name, (float)a.x - surface.textWidth(a.name)/2 + (float)a.getWidth()/2, (float)(a.y -3.0));

			}
		}

		boolean k = true;
		for(Player p: players) {
			if(p.getPlayerType() == false)
				k = false;
			if(p.invisible && !p.invisUsed) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInvisible, p.name);
				p.invisUsed = true;
				processNetworkMessages();
			}
			if(p.invisUsed && p.turnInvisOff) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInvisibleOff, p.name);
				p.turnInvisOff = false;
				processNetworkMessages();
			}
			
			if(p.dive && !p.diveUsed) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeDiveTag, p.name);
				p.diveUsed = true;
				processNetworkMessages();
			}
			if(p.diveUsed && p.turnDiveOff) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeDiveOff, p.name);
				p.turnDiveOff = false;
				processNetworkMessages();
			}
		}
		if(k) {
			third = false;
			roundWinner = true;
			nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeGameOver, true);
			processNetworkMessages();
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
				if (surface.isPressed(KeyEvent.VK_LEFT))
					p.walk(-1);
				if (surface.isPressed(KeyEvent.VK_RIGHT))
					p.walk(1);
				if (surface.isPressed(KeyEvent.VK_UP))
					p.jump();
			}
			else {
				if (surface.isPressed(KeyEvent.VK_A))
					p.walk(-1);
				if (surface.isPressed(KeyEvent.VK_D))
					p.walk(1);
				if (surface.isPressed(KeyEvent.VK_W))
					p.jump();
				}
		}
		
		if(!MultiplayerOrNetwork.network) {
			t.walk(0);
			r.walk(0);
			
			if(surface.getInputMethod()) {
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
			else {
				if(!((first || second) && t.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_A))
						t.walk(-1);
					if (surface.isPressed(KeyEvent.VK_D))
						t.walk(1);
					if (surface.isPressed(KeyEvent.VK_W))
						t.jump();
				}
				if(!((first || second) && r.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_LEFT))
						r.walk(-1);
					if (surface.isPressed(KeyEvent.VK_RIGHT))
						r.walk(1);
					if (surface.isPressed(KeyEvent.VK_UP))
						r.jump();
				}
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
						processNetworkMessages();
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
	
		if(MultiplayerOrNetwork.network) 
			nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeCurrentLocation, p.x,p.y);
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
	public void connectedToServer(NetworkMessenger nm) {
		this.nm = nm;
	}
	public NetworkMessenger getNetworkMessenger() {
		return nm;
	}
	@Override
	public void networkMessageReceived(NetworkDataObject ndo) {
		
	}
}