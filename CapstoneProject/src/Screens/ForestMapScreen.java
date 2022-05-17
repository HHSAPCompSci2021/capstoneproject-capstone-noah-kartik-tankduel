package Screens;

import System.DrawingSurface;
import processing.core.PImage;

public class ForestMapScreen extends Screens{

	private DrawingSurface surface;
	private PImage forest;
	public ForestMapScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// TODO Auto-generated constructor stub
	}
	public void setup() {
		forest = surface.loadImage("img/forest.jpg");
	}
	public void draw() {
		surface.clear();
		surface.pushStyle();
		surface.image(forest, 0, 0, 1080, 720);
		surface.popStyle();
	}

}
