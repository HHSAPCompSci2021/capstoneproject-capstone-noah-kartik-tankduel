package Screens;

import java.awt.Rectangle;

import System.DrawingSurface;
import processing.core.PImage;


public class WaterMapScreen extends Screens{
	private DrawingSurface surface;
	private PImage bg;
	private Rectangle beach;
	public WaterMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		beach = new Rectangle(0,520,1080,400);
		// TODO Auto-generated constructor stub
	}
	public void setup() {
		surface.size(1080,720);
		bg = surface.loadImage("img/beach.png");
		
	}
	
	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.image(bg, 0, 0, 1080, 720);
//		surface.background(0,119,190);
//		surface.fill(249,215,28);
//		surface.noStroke();
//		surface.circle(960, 80, 100);
//		surface.fill(194,178,128);
//		surface.rect(beach.x, beach.y, beach.width, beach.height);
		surface.popStyle();
	}
		
}
