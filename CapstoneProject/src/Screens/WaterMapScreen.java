package Screens;

import java.awt.Rectangle;

import System.DrawingSurface;
import processing.core.PImage;


public class WaterMapScreen extends Screens{
	private DrawingSurface surface;
	private PImage water;
	public WaterMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// TODO Auto-generated constructor stub
	}
	public void setup() {
		water = surface.loadImage("img/beach.png");
		
	}
	
	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.image(water, 0, 0, 1080, 720);
		surface.popStyle();
	}
		
}