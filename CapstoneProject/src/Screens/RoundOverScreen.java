package Screens;

import System.DrawingSurface;

public class RoundOverScreen extends Screens{
	private DrawingSurface surface;

	
	public RoundOverScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
	}
	
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		String s = "";
		if(surface.getRoundWinner())
			s = "RUNNERS";
		else
			s = "TAGGERS";
		surface.text(s + " WON THE ROUND!", 200, DRAWING_HEIGHT/2);
	}
}
