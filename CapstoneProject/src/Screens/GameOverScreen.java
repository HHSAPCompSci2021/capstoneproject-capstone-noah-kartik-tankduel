package Screens;

import System.DrawingSurface;

public class GameOverScreen extends Screens{
	private DrawingSurface surface;

	
	public GameOverScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
	}
	
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		

	}
}