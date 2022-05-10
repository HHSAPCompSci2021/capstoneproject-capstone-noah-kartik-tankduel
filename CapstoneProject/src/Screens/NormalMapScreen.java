package Screens;

import java.awt.geom.Line2D;

import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Line2D l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29;
	
	
	/* The area created by spawnX and spawnY will be the spawn area.
	 * When the count down to START the game reaches 0,
	 * the spawnY line will disappear and only the 
	 * runners will be able to move. After 15 seconds,
	 *  taggers will be able to move.
	 */
	private Line2D spawnX, spawnY;

	private Line2D[] platforms;// will contain all the platforms.
	
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// X:1080 by Y:720 range lines
		l0 = new Line2D.Double (0,150,150,150);
		l1 = new Line2D.Double (300,500,400,600);
		l2 = new Line2D.Double (700,300,800,200);
		l3 = new Line2D.Double (950,30,1050,130);
		l4 = new Line2D.Double (500,500,600,500);
		l5 = new Line2D.Double (500, 70, 300, 70);
		l6 = new Line2D.Double (780, 70, 1010,70);
		l7 = new Line2D.Double (400,270,650,150);
		l8 = new Line2D.Double (0,300,250,375);
		l9 = new Line2D.Double (829,375,1079,300);
		l10 = new Line2D.Double (0,550,230,550);
		l11 = new Line2D.Double (849,550,1079,550);
		l12 = new Line2D.Double (400,500,630,500);
		l13 = new Line2D.Double (480, 360, 700,360);
		l14 = new Line2D.Double (600,620,800,450);
		l15 = new Line2D.Double (250,250,350,350);
		l16 = new Line2D.Double (200,500,500,300);
		l17 = new Line2D.Double (650,0,700,50);
		l18 = new Line2D.Double (320,720,480,640);
		l19 = new Line2D.Double (600,720,500,590);
		l20 = new Line2D.Double (100,250,250,150);
		l21 = new Line2D.Double (150,0,250,100);
		l22 = new Line2D.Double (350,150,350,250);
		l23 = new Line2D.Double (2,2,2,2);
		l24 = new Line2D.Double (2,2,2,2);
		l25 = new Line2D.Double (2,2,2,2);
		l26 = new Line2D.Double (2,2,2,2);
		l27 = new Line2D.Double (2,2,2,2);
		l28 = new Line2D.Double (2,2,2,2);
		l29 = new Line2D.Double (2,2,2,2);

		platforms = new Line2D[] {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29};
		
		spawnX = new Line2D.Double (0,150,150,150);
		spawnY = new Line2D.Double (150,0,150,150);
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
		surface.strokeWeight(1);
		for(Line2D l : platforms)
			surface.line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
		
		surface.line((float)spawnX.getX1(), (float)spawnX.getY1(), (float)spawnX.getX2(), (float)spawnX.getY2());
		surface.line((float)spawnY.getX1(), (float)spawnY.getY1(), (float)spawnY.getX2(), (float)spawnY.getY2());

	}
	
	
}