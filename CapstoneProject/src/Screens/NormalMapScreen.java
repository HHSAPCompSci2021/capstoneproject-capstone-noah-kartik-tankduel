package Screens;

import java.awt.geom.Line2D;
import java.util.ArrayList;
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

	private ArrayList<Sprite> platform = new ArrayList<Sprite>();
	
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// X:1080 by Y:720 range lines
		l0 = new Sprite (500,450,450,5);
//		l1 = new Sprite (300,500,400,5);
//		l2 = new Sprite (700,300,800,5);
//		l3 = new Sprite (950,30,1050,5);
//		l4 = new Sprite (500,500,600,5);
//		l5 = new Sprite (500, 70, 300,5);
//		l6 = new Sprite (780, 70, 1010,5);
//		l7 = new Sprite (400,270,650,5);
//		l8 = new Sprite (50,300,250,5);
//		l9 = new Sprite (829,375,1050,5);
//		l10 = new Sprite (50,550,230,5);
//		l11 = new Sprite (849,550,1050,5);
//		l12 = new Sprite (400,500,630,5);
//		l13 = new Sprite (480, 360,700,5);
//		l14 = new Sprite (600,620,800,5);
//		l15 = new Sprite (250,250,350,5);
//		l16 = new Sprite (200,500,500,5);
//		l17 = new Sprite (650,0,700,5);
//		l18 = new Sprite (320,690,480,5);
//		l19 = new Sprite (600,690,500,5);
//		l20 = new Sprite (100,250,280,5);
//		l21 = new Sprite (150,0,250,5);
//		l22 = new Sprite (300,200,450,5);
//		l23 = new Sprite (670,100,770,5);
//		l24 = new Sprite (720,125,750,5);
//		l25 = new Sprite (0,400,70,5);
//		l26 = new Sprite (40,600,100,5);
//		l27 = new Sprite (850,170,950,5);
//		l28 = new Sprite (1020,240,1080,5);
//		l29 = new Sprite (790,570,880,5);
//		l30 = new Sprite (1000,680,1080,5);
//		l31 = new Sprite (980,450,1080,5);
//		l32 = new Sprite (840,425,970,5);
//		l33 = new Sprite (500,150,550,5);
//		l34 = new Sprite (200,680,280,5);
//		l35 = new Sprite (730,400,780,5);

		spawnX = new Sprite (0,150,150,5);
		spawnY = new Sprite (150,0,5,150);
		
		platform.add(l0);
//		platform.add(l1);
//		platform.add(l2);
//		platform.add(l3);
//		platform.add(l4);
//		platform.add(l5);
//		platform.add(l6);
//		platform.add(l7);
//		platform.add(l8);
//		platform.add(l9);
//		platform.add(l10);
//		platform.add(l11);
//		platform.add(l12);
//		platform.add(l13);
//		platform.add(l14);
//		platform.add(l15);
//		platform.add(l16);
//		platform.add(l17);
//		platform.add(l18);
//		platform.add(l19);
//		platform.add(l20);
//		platform.add(l21);
//		platform.add(l22);
//		platform.add(l23);
//		platform.add(l24);
//		platform.add(l25);
//		platform.add(l26);
//		platform.add(l27);
//		platform.add(l28);
//		platform.add(l29);
//		platform.add(l30);
//		platform.add(l31);
//		platform.add(l32);
//		platform.add(l33);
//		platform.add(l34);
//		platform.add(l35);
//		platform.add(spawnX);
//		platform.add(spawnY);
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
