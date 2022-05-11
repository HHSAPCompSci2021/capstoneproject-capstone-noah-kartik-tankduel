package Screens;

import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import Player.Player;
import Player.Sprite;
import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Line2D l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35;
	private Player p;
	

	
	/* The area created by spawnX and spawnY will be the spawn area.
	 * When the count down to START the game reaches 0,
	 * the spawnY line will disappear and only the 
	 * runners will be able to move. After 15 seconds,
	 *  taggers will be able to move.
	 */
	private Line2D spawnX, spawnY;

	private Line2D border1,border2,border3,border4;
	private Line2D[] platforms;
		
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		p =new Player(DRAWING_WIDTH/2-p.PLAYER_WIDTH/2,DRAWING_HEIGHT/2 - p.PLAYER_HEIGHT/2);
		
		// X:1080 by Y:720 range lines, make sure that x1 < x2
		l0 = new Line2D.Double (450,550,500,450);
		l1 = new Line2D.Double (300,500,400,600);
		l2 = new Line2D.Double (700,300,800,200);
		l3 = new Line2D.Double (950,30,1050,130);
		l4 = new Line2D.Double (500,500,600,500);
		l5 = new Line2D.Double (300, 70, 500, 70);
		l6 = new Line2D.Double (780, 70, 1010,70);
		l7 = new Line2D.Double (400,270,650,150);
		l8 = new Line2D.Double (50,300,250,375);
		l9 = new Line2D.Double (829,375,1050,300);
		l10 = new Line2D.Double (50,550,230,550);
		l11 = new Line2D.Double (849,550,1050,550);
		l12 = new Line2D.Double (400,500,630,500);
		l13 = new Line2D.Double (480, 360,700,360);
		l14 = new Line2D.Double (600,620,800,450);
		l15 = new Line2D.Double (250,250,350,350);
		l16 = new Line2D.Double (200,500,500,300);
		l17 = new Line2D.Double (650,0,700,50);
		l18 = new Line2D.Double (320,690,480,640);
		l19 = new Line2D.Double (500,590,600,690);
		l20 = new Line2D.Double (100,250,280,140);
		l21 = new Line2D.Double (150,0,250,100);
		l22 = new Line2D.Double (300,200,450,175);
		l23 = new Line2D.Double (670,100,770,150);
		l24 = new Line2D.Double (720,125,750,105);
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

		spawnX = new Line2D.Double(0,150,150,150);
		spawnY = new Line2D.Double(150,0,150,150);
		
		border1 = new Line2D.Double (0, 0, 1080, 0);
		border2 = new Line2D.Double (0, 0, 0, 720);
		border3 = new Line2D.Double (0, 720, 1080, 720);
		border4 = new Line2D.Double (1080, 720, 1080, 0);
		
		platforms = new Line2D[] {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32,l33,l34,l35,spawnX,spawnY,border1,border2,border3,border4};
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
		
		surface.strokeWeight(2);

		p.draw(this.surface);

		//Platforms
		surface.strokeWeight(5);
		for(Line2D l: platforms)
			surface.line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
		p.walk(0);
		if (surface.isPressed(KeyEvent.VK_LEFT))
			p.walk(-1);
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			p.walk(1);
		if (surface.isPressed(KeyEvent.VK_UP))
			p.jump();
		

		p.act(platforms);
	}
	
	
	
	
}
