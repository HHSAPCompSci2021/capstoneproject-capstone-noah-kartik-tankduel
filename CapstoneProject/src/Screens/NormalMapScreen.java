package Screens;

import java.awt.geom.Line2D;

import System.DrawingSurface;

public class NormalMapScreen extends Screens{
	private DrawingSurface surface;
	private Line2D l;
	private Line2D[] platforms;// will contain all the platforms.
	public NormalMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		platforms = new Line2D[30];
		l = new Line2D.Double (2,2,2,2);
		platforms[0] = l;
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
		
		surface.strokeWeight(1);
		surface.line(0, 70, 100, 70);
	}
}
