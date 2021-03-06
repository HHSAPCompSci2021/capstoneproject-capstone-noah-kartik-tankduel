package Screens;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Queue;

import Player.Player;
import SpecialAbilities.DiveTag;
import SpecialAbilities.HighJump;
import SpecialAbilities.SneakyCloak;
import SpecialAbilities.SpecialAbilities;
import SpecialAbilities.SpeedBoost;
import System.DrawingSurface;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkManagementPanel;
import networking.frontend.NetworkMessenger;
/**
 * The freeze game mode for the forest map
 * @author Noah Pien and Kartik Joshi
 */
public class ForestMapFreezeTag extends Screens implements NetworkListener{
	private DrawingSurface surface;
	private Line2D l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35;
	private Player p;
	private ArrayList<Player> players;
	private ArrayList<Player> playersMulti;

	private static DiveTag diveTag;
	private static HighJump highJump;
	private static SneakyCloak sneakyCloak;
	private static SpeedBoost speedBoost;
	private static SpecialAbilities[] abilities;
	private Rectangle ground;
	private Rectangle forests;
	private Rectangle tree1;
	private Rectangle tree2;
	private Rectangle tree3;
	private Rectangle tree4;
	private Player f1;
	private Player f2;
	private Player r1;
	private Player r2;
	public static boolean roundWinner;
	public static String[] currentRunner;
	public static String[] currentTagger;

	private int repeatName;
	private NetworkMessenger nm;
	public static boolean abilityUseable;
	
	private static final String messageTypeCurrentLocation = "CURRENT_LOCATION";
	private static final String messageTypeInit = "CREATE_PLAYER";
	private static final String messageTypeIsFrozen = "IS_FROZEN";
	private static final String messageTypeUnfrozen = "UNFROZEN";

	private static final String messageTypeSetTagger = "SET_TAGGER";
	private static final String messageTypeGameOver = "GAME_OVER";
	private static final String messageTypeInvisible = "INVISIBLE";
	private static final String messageTypeInvisibleOff = "INVISIBLE_OFF";
	private static final String messageTypeDiveTag = "DIVETAG";
	private static final String messageTypeDiveOff = "DIVE_OFF";





	
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
	 * Draws the forest map freeze tag screens 
	 * @param surface - DrawingSurface PApplet
	 */
	public ForestMapFreezeTag(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		p =new Player(50,50);
		players = new ArrayList<Player>();
		p.host = "me!";
		players.add(p);
		playersMulti = new ArrayList<Player>();

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
		spawnX = new Line2D.Double(0,150,150,150);
		spawnY = new Line2D.Double(150,0,150,150);
		f1 = new Player(0,0);
		f2 = new Player(45,0);
		r1 = new Player(90,0);
		r2 = new Player(135,0);
		playersMulti.add(f1);
		playersMulti.add(f2);
		playersMulti.add(r1);
		playersMulti.add(r2);

		double r = Math.random();
	
		if(r < 1.00/6) {
			f1.setPlayerType(true);
			f2.setPlayerType(true);
		}else if(r < 2.00/6) {
			f1.setPlayerType(true);
			r1.setPlayerType(true);
		}
		else if(r < 3.00/6){
			f1.setPlayerType(true);
			r2.setPlayerType(true);
		}
		else if(r<4.00/6) {
			f2.setPlayerType(true);
			r2.setPlayerType(true);
		}
		else if(r < 5.00/6) {
			f2.setPlayerType(true);
			r1.setPlayerType(true);
		}
		else {
			r1.setPlayerType(true);
			r2.setPlayerType(true);
		}
		
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
		
		currentRunner = new String[2];
		currentTagger = new String[0];
		
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
					else {
						int taggerCount = StartNetworkGame.numberOfPlayers/2;
						ArrayList<Integer> guessed = new ArrayList<>();
						for(int i = 0; i<taggerCount;i++) {
							int a = (int)(Math.random()*players.size());
							while(guessed.contains(a)) {
								a = (int)(Math.random()*players.size());
							}
							guessed.add(a);
							players.get(a).setPlayerType(true);
							nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeSetTagger, players.get(a),StartNetworkGame.numberOfPlayers);
						}
					}
					
				}
			}
			
			if(check == 10 || (NetworkManagementPanel.isHost && check == 1)) {
				check = 2;
				currentTagger = new String[players.size()/2];
				currentRunner = new String[players.size() - players.size()/2];
				int r = 0;
				int t = 0;
				for(Player p: players) {
					if(p.getPlayerType()) {
						currentTagger[t] = p.name;
						t++;
					}
					else {
						currentRunner[r] = p.name;
						r++;
					}
				}
			}
			
		}
		if(!MultiplayerOrNetwork.network) {
			surface.textSize(15);
			surface.fill(0,0,0);
			if(!f1.getInvisible())
				surface.text(Start1v1Game.player1, (float)f1.x - surface.textWidth(Start1v1Game.player1)/2 + (float)f1.getWidth()/2, (float)(f1.y -3.0));
			if(!f2.getInvisible())
				surface.text(Start1v1Game.player2, (float)f2.x - surface.textWidth(Start1v1Game.player2)/2 + (float)f2.getWidth()/2, (float)(f2.y - 3.0));
			if(!r1.getInvisible())
				surface.text(Start1v1Game.player3, (float)r1.x - surface.textWidth(Start1v1Game.player1)/2 + (float)r1.getWidth()/2, (float)(r1.y -3.0));
			if(!r2.getInvisible())
				surface.text(Start1v1Game.player4, (float)r2.x - surface.textWidth(Start1v1Game.player2)/2 + (float)r2.getWidth()/2, (float)(r2.y - 3.0));
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
			if(p.getPlayerType() == false && p.frozeOrUnfroze() == false)
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
			//t.draw(surface);
			//r.draw(surface);
			f1.draw(surface);
			f2.draw(surface);
			r1.draw(surface);
			r2.draw(surface);
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
			//t.walk(0);
			//r.walk(0);
			f1.walk(0);
			f2.walk(0);
			r1.walk(0);
			r2.walk(0);
			if(!surface.getInputMethod()) {
				if(!((first || second) && f1.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_LEFT))
						f1.walk(-1);
					if (surface.isPressed(KeyEvent.VK_RIGHT))
						f1.walk(1);
					if (surface.isPressed(KeyEvent.VK_UP))
						f1.jump();
				}
				if(!((first || second) && r1.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_A)) 
						r1.walk(-1);
					if (surface.isPressed(KeyEvent.VK_D)) 
						r1.walk(1);
					if (surface.isPressed(KeyEvent.VK_W)) 
						r1.jump();
				}
				if(!((first || second) && f2.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_V)) 
						f2.walk(-1);
					if (surface.isPressed(KeyEvent.VK_N)) 
						f2.walk(1);
					if (surface.isPressed(KeyEvent.VK_G)) 
						f2.jump();
				}
				if(!((first || second) && r2.getPlayerType())) {
					if (surface.isPressed(KeyEvent.VK_J)) 
						r2.walk(-1);
					if (surface.isPressed(KeyEvent.VK_L)) 
						r2.walk(1);
					if (surface.isPressed(KeyEvent.VK_I)) 
						r2.jump();
				}
			}
//			else {
//				if(!((first || second) && t.getPlayerType())) {
//					if (surface.isPressed(KeyEvent.VK_A))
//						t.walk(-1);
//					if (surface.isPressed(KeyEvent.VK_D))
//						t.walk(1);
//					if (surface.isPressed(KeyEvent.VK_W))
//						t.jump();
//				}
//				if(!((first || second) && r.getPlayerType())) {
//					if (surface.isPressed(KeyEvent.VK_LEFT))
//						r.walk(-1);
//					if (surface.isPressed(KeyEvent.VK_RIGHT))
//						r.walk(1);
//					if (surface.isPressed(KeyEvent.VK_UP))
//						r.jump();
//				}
//			}
		}
		
		if(MultiplayerOrNetwork.network) {
			p.act(platforms,abilities);
		}
		
		if(!MultiplayerOrNetwork.network) {
			//t.act(platforms, abilities);
			//r.act(platforms, abilities);
			f1.act(platforms, abilities);
			f2.act(platforms, abilities);
			r1.act(platforms, abilities);
			r2.act(platforms, abilities);
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
					int i = 0;
					if(!MultiplayerOrNetwork.network) {
						if(!f1.getPlayerType()) {
							currentRunner[i] = Start1v1Game.player1;
							i++;
						}
						if(!f2.getPlayerType()) {
							currentRunner[i] = Start1v1Game.player2;
							i++;
						}
						if(!r1.getPlayerType()) {
							currentRunner[i] = Start1v1Game.player3;
							i++;
						}
						if(!r2.getPlayerType()) {
							currentRunner[i] = Start1v1Game.player4;
						}
					}
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
		if(!MultiplayerOrNetwork.network) {
			boolean b = true;
			for(Player p: playersMulti) {
				if(!p.frozeOrUnfroze() && !p.getPlayerType()) {
					b = false;
				}
				if(p.getTaggedTime() == 3) {
					b = true;
					break;
				}
					
			}
			if(b) {
				int i = 0;
				if(f1.getPlayerType()) {
					currentRunner[i] = Start1v1Game.player1;
					i++;
				}
				if(f2.getPlayerType()) {
					currentRunner[i] = Start1v1Game.player2;
					i++;
				}
				if(r1.getPlayerType()) {
					currentRunner[i] = Start1v1Game.player3;
					i++;
				}
				if(r2.getPlayerType()) {
					currentRunner[i] = Start1v1Game.player4;
				}
				surface.switchScreen(ScreenSwitcher.ROUND_OVER);
	
			}
		}
		if(MultiplayerOrNetwork.network) {
			boolean b = true;
			for(Player p: players) {
				if(!p.frozeOrUnfroze() && !p.getPlayerType()) {
					b = false;
				}
				if(p.getTaggedTime() == 3) {
					b = true;
					break;
				}
					
			}		
			if(b) {
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeGameOver, true);
				
				roundWinner = true;
				surface.switchScreen(ScreenSwitcher.ROUND_OVER);
			}
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
		if(!MultiplayerOrNetwork.network) {//need to add 3 second grace period and maybe has to be frozen for at least 1 second before becoming unfrozen
			if(f1.intersects(r1) && !(first || second)) {
				if(f1.getPlayerType() == r1.getPlayerType()) {
					if(f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getFrozenTime() > 1000) {
						f1.unFrozen();
					} else if(r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getFrozenTime() > 1000) {
						r1.unFrozen();
					}
				}
				else if(f1.getPlayerType() && !r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getunfrozenTime() > 3000) {
					r1.isFrozen();
					r1.gotTagged();
				}
				else if(r1.getPlayerType() && !f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getunfrozenTime() > 3000){
					f1.isFrozen();
					f1.gotTagged();
				}
			}
			
			if(f1.intersects(r2) && !(first || second)) {
				if(f1.getPlayerType() == r2.getPlayerType()) {
					if(f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getFrozenTime() > 1000) {
						f1.unFrozen();
					} else if(r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getFrozenTime() > 1000){
						r2.unFrozen();
					}
				}
				else if(f1.getPlayerType() && !r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getunfrozenTime() > 3000) {
					r2.isFrozen();
					r2.gotTagged();
				}
				else if(r2.getPlayerType() && !f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getunfrozenTime() > 3000){
					f1.isFrozen();
					f1.gotTagged();
				}
			}
			if(f2.intersects(r1) && !(first || second)) {
				if(f2.getPlayerType() == r1.getPlayerType()) {
					if(f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getFrozenTime() > 1000) {
						f2.unFrozen();
					} else if(r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getFrozenTime() > 1000){
						r1.unFrozen();
					}				}
				else if(f2.getPlayerType() && !r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getunfrozenTime() > 3000) {
					r1.isFrozen();
					r1.gotTagged();
				}
				else if(r1.getPlayerType() && !f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getunfrozenTime() > 3000){
					f2.isFrozen();
					f2.gotTagged();
				}
			}
			if(f2.intersects(r2) && !(first || second)) {
				if(f2.getPlayerType() == r2.getPlayerType()) {
					if(f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getFrozenTime() > 1000) {
						f2.unFrozen();
					} else if(r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getFrozenTime() > 1000){
						r2.unFrozen();
					}
				}
				else if(f2.getPlayerType() && !r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getunfrozenTime() > 3000) {
					r2.isFrozen();
					r2.gotTagged();
				}
				else if(r2.getPlayerType() && !f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getunfrozenTime() > 3000){
					f2.isFrozen();
					f2.gotTagged();
				}

			}
			if(f2.intersects(f1) && !(first || second)) {
				if(f2.getPlayerType() == f1.getPlayerType()) {
					if(f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getFrozenTime() > 1000) {
						f2.unFrozen();
					} else if(f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getFrozenTime() > 1000){
						f1.unFrozen();
					}
				}
				else if(f2.getPlayerType() && !f1.frozeOrUnfroze() && System.currentTimeMillis() - f1.getunfrozenTime() > 3000) {
					f1.isFrozen();
					f1.gotTagged();
				}
				else if(f1.getPlayerType() && !f2.frozeOrUnfroze() && System.currentTimeMillis() - f2.getunfrozenTime() > 3000){
					f2.isFrozen();
					f2.gotTagged();
				}
			}
			if(r2.intersects(r1) && !(first || second)) {
				if(r2.getPlayerType() == r1.getPlayerType()) {
					if(r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getFrozenTime() > 1000) {
						r2.unFrozen();
					} else if(r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getFrozenTime() > 1000){
						r1.unFrozen();
					}
				}
				else if(r2.getPlayerType() && !r1.frozeOrUnfroze() && System.currentTimeMillis() - r1.getunfrozenTime() > 3000) {
					r1.isFrozen();
					r1.gotTagged();
				}
				else if(r1.getPlayerType() && !r2.frozeOrUnfroze() && System.currentTimeMillis() - r2.getunfrozenTime() > 3000){
					r2.isFrozen();
					r2.gotTagged();
				}

			}
		}
		if(MultiplayerOrNetwork.network) {
			if(!(first||second)) {
				for(int i = 0; i<players.size();i++) {
					if(players.get(i).intersects(p) && players.get(i).getPlayerType()!=p.getPlayerType()) {
						if(!p.getPlayerType() && !p.frozeOrUnfroze() && System.currentTimeMillis() - p.getunfrozenTime() > 3000 && !players.get(i).equals(p)) {
							p.isFrozen();
							nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeIsFrozen, p);
							
							p.gotTagged();
						}
					}
					if(players.get(i).intersects(p) && players.get(i).getPlayerType() == p.getPlayerType() && System.currentTimeMillis() - p.getFrozenTime() > 1000 && !players.get(i).equals(p)) {
						p.unFrozen();
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeUnfrozen, p);
						
					}
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
				if (ndo.message[0].equals(messageTypeCurrentLocation)) {//works
					
						for (Player c : players) {
							if (c.host.equals(host)) {
								c.x = (double)ndo.message[1];
								c.y = (double)ndo.message[2];
							}
						}
				}
				else if (ndo.message[0].equals(messageTypeInit)) {//works
					
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
				
				else if (ndo.message[0].equals(messageTypeIsFrozen)) {
					Player s = (Player)ndo.message[1];
					for(int i = 0; i<players.size();i++) {
						if(s.name.equals(players.get(i).name)) {
							players.get(i).isFrozen();
							players.get(i).gotTagged();
						}
					}						
				}
				
				else if (ndo.message[0].equals(messageTypeSetTagger)) {
					Player s = (Player)ndo.message[1];
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals(s.name)) {
							players.get(i).setPlayerType(true);
						}
					}
					StartNetworkGame.numberOfPlayers = (int)ndo.message[2];
					check = 10;
				}
				
				else if (ndo.message[0].equals(messageTypeGameOver)) {//works
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
				
				else if (ndo.message[0].equals(messageTypeUnfrozen)) {
					for(int i = 0; i<players.size();i++) {
						if(players.get(i).name.equals(((Player)ndo.message[1]).name)) {
							players.get(i).unFrozen();
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

