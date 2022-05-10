package Screens;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Line2D l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29;

	private Line2D[] platforms;// will contain all the platforms.
	
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// X:1080 by Y:720 range lines
		l0 = new Line2D.Double (0,100,100,100);
		l1 = new Line2D.Double (300,500,400,600);
		l2 = new Line2D.Double (700,300,800,200);
		l3 = new Line2D.Double (950,30,1050,130);
		l4 = new Line2D.Double (500,500,600,500);
		l5 = new Line2D.Double (70, 70, 300, 70);
		l6 = new Line2D.Double(780, 70, 1010,70);
		l7 = new Line2D.Double(400,270,650,150);
		l8 = new Line2D.Double(0,300,250,375);
		l9 = new Line2D.Double(829,375,1079,300);
		l10 = new Line2D.Double(0,550,230,550);
		l11 = new Line2D.Double(849,550,1079,550);
		l12 = new Line2D.Double(400,500,630,500);
		l13 = new Line2D.Double (2,2,2,2);
		l14 = new Line2D.Double (2,2,2,2);
		l15 = new Line2D.Double (2,2,2,2);
		l16 = new Line2D.Double (2,2,2,2);
		l17 = new Line2D.Double (2,2,2,2);
		l18 = new Line2D.Double (2,2,2,2);
		l19 = new Line2D.Double (2,2,2,2);
		l20 = new Line2D.Double (2,2,2,2);
		l21 = new Line2D.Double (2,2,2,2);
		l22 = new Line2D.Double (2,2,2,2);
		l23 = new Line2D.Double (2,2,2,2);
		l24 = new Line2D.Double (2,2,2,2);
		l25 = new Line2D.Double (2,2,2,2);
		l26 = new Line2D.Double (2,2,2,2);
		l27 = new Line2D.Double (2,2,2,2);
		l28 = new Line2D.Double (2,2,2,2);
		l29 = new Line2D.Double (2,2,2,2);

		platforms = new Line2D[] {l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29};
	}
	
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		
		//window border lines
		surface.pushStyle();
		surface.strokeWeight(8);
		surface.stroke(255,0,0);
		surface.line(0, 0, 1080, 0);
		surface.line(0, 0, 0, 720);
		surface.line(0, 720, 1080, 720);
		surface.line(1080, 720, 1080, 0);
		surface.popStyle();
		
		//Platforms
		surface.strokeWeight(1);
		for(Line2D l : platforms)
			surface.line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
		
	}
	
	
}
