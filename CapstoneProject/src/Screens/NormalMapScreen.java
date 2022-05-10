package Screens;

import java.awt.geom.Line2D;
import java.util.List;

import Player.Player;
import Player.Sprite;
import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Sprite l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35;
	private Player p;
	
	/* The area created by spawnX and spawnY will be the spawn area.
	 * When the count down to START the game reaches 0,
	 * the spawnY line will disappear and only the 
	 * runners will be able to move. After 15 seconds,
	 *  taggers will be able to move.
	 */
	private Sprite spawnX, spawnY;

	private List<Sprite> platform;
	
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// X:1080 by Y:720 range lines
		l0 = new Sprite (500,450,450,550);
		l1 = new Sprite (300,500,400,600);
		l2 = new Sprite (700,300,800,200);
		l3 = new Sprite (950,30,1050,130);
		l4 = new Sprite (500,500,600,500);
		l5 = new Sprite (500, 70, 300, 70);
		l6 = new Sprite (780, 70, 1010,70);
		l7 = new Sprite (400,270,650,150);
		l8 = new Sprite (50,300,250,375);
		l9 = new Sprite (829,375,1050,300);
		l10 = new Sprite (50,550,230,550);
		l11 = new Sprite (849,550,1050,550);
		l12 = new Sprite (400,500,630,500);
		l13 = new Sprite (480, 360,700,360);
		l14 = new Sprite (600,620,800,450);
		l15 = new Sprite (250,250,350,350);
		l16 = new Sprite (200,500,500,300);
		l17 = new Sprite (650,0,700,50);
		l18 = new Sprite (320,690,480,640);
		l19 = new Sprite (600,690,500,590);
		l20 = new Sprite (100,250,280,140);
		l21 = new Sprite (150,0,250,100);
		l22 = new Sprite (300,200,450,175);
		l23 = new Sprite (670,100,770,150);
		l24 = new Sprite (720,125,750,105);
		l25 = new Sprite (0,400,70,450);
		l26 = new Sprite (40,600,100,660);
		l27 = new Sprite (850,170,950,270);
		l28 = new Sprite (1020,240,1080,180);
		l29 = new Sprite (790,570,880,680);
		l30 = new Sprite (1000,680,1080,600);
		l31 = new Sprite (980,450,1080,340);
		l32 = new Sprite (840,425,970,505);
		l33 = new Sprite (500,150,550,100);
		l34 = new Sprite (200,680,280,550);
		l35 = new Sprite (730,400,780,400);

		spawnX = new Sprite (0,150,150,150);
		spawnY = new Sprite (150,0,150,150);
		
		platform.add(l0);
		platform.add(l1);
		platform.add(l2);
		platform.add(l3);
		platform.add(l4);
		platform.add(l5);
		platform.add(l6);
		platform.add(l7);
		platform.add(l8);
		platform.add(l9);
		platform.add(l10);
		platform.add(l11);
		platform.add(l12);
		platform.add(l13);
		platform.add(l14);
		platform.add(l15);
		platform.add(l16);
		platform.add(l17);
		platform.add(l18);
		platform.add(l19);
		platform.add(l20);
		platform.add(l21);
		platform.add(l22);
		platform.add(l23);
		platform.add(l24);
		platform.add(l25);
		platform.add(l26);
		platform.add(l27);
		platform.add(l28);
		platform.add(l29);
		platform.add(l30);
		platform.add(l31);
		platform.add(l32);
		platform.add(l33);
		platform.add(l34);
		platform.add(l35);
		platform.add(spawnX);
		platform.add(spawnY);
	}
	
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		
		//window border lines
		surface.pushStyle();
		surface.strokeWeight(8);
		surface.stroke(25,255,255);
		surface.line(0, 0, 1080, 0);
		surface.line(0, 0, 0, 720);
		surface.line(0, 720, 1080, 720);
		surface.line(1080, 720, 1080, 0);
		surface.popStyle();
		
		//Platforms
		surface.strokeWeight(5);
		for(Sprite l : platform)
			l.draw(surface);
		
		spawnX.draw(surface);
		spawnY.draw(surface);
	}
	
	
}
