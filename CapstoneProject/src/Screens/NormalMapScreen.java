package Screens;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Line2D l0;
	private Line2D l1;
	private Line2D l2;
	private Line2D l3;
	private Line2D l4;
	private Line2D l5;
	private Line2D l6;
	private Line2D l7;
	private Line2D l8;
	
	private Line2D[] platforms;// will contain all the platforms.
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		platforms = new Line2D[10];
		l0 = new Line2D.Double (70, 70, 300, 70);
		l1 = new Line2D.Double(780, 70, 1010,70);
		l2 = new Line2D.Double(400,270,650,150);
		l3 = new Line2D.Double(0,300,250,375);
		l4 = new Line2D.Double(829,375,1079,300);
		l5 = new Line2D.Double(0,550,230,550);
		l6 = new Line2D.Double(849,550,1079,550);
		l7 = new Line2D.Double(400,500,630,500);
		platforms[0] = l0;
		platforms[1] = l1;
		platforms[2] = l2;
		platforms[3] = l3;
		platforms[4] = l4;
		platforms[5] = l5;
		platforms[6] = l6;
		platforms[7] = l7;
		
	}
	
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		surface.strokeWeight(8);
		
		//window border lines
		surface.pushStyle();
		surface.stroke(255,0,0);
		surface.line(0, 0, 1080, 0);
		surface.line(0, 0, 0, 720);
		surface.line(0, 720, 1080, 720);
		surface.line(1080, 720, 1080, 0);
		surface.popStyle();
		
		surface.strokeWeight(5);
//		for(Line2D line: platforms) {
//			surface.line((float)line.getX1(), (float)line.getY1(), (float)line.getX2(), (float)line.getY2());
//		}
		surface.line((float)l0.getX1(), (float)l0.getY1(), (float)l0.getX2(), (float)l0.getY2());
		surface.line((float)l1.getX1(), (float)l1.getY1(), (float)l1.getX2(), (float)l1.getY2());
		surface.line((float)l2.getX1(), (float)l2.getY1(), (float)l2.getX2(), (float)l2.getY2());
		surface.line((float)l3.getX1(), (float)l3.getY1(), (float)l3.getX2(), (float)l3.getY2());
		surface.line((float)l4.getX1(), (float)l4.getY1(), (float)l4.getX2(), (float)l4.getY2());
		surface.line((float)l5.getX1(), (float)l5.getY1(), (float)l5.getX2(), (float)l5.getY2());
		surface.line((float)l6.getX1(), (float)l6.getY1(), (float)l6.getX2(), (float)l6.getY2());
		surface.line((float)l7.getX1(), (float)l7.getY1(), (float)l7.getX2(), (float)l7.getY2());
	}
}
