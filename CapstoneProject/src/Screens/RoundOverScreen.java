package Screens;

import System.DrawingSurface;

public class RoundOverScreen extends Screens{
	private DrawingSurface surface;

	/**
	 * RoundoverScreen which appears after the round of tag is over. Call to super constructor to create a full sized window
	 * @param surface - Drawing Surface
	 */
	public RoundOverScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
	}
	/**
	 * Standard processing draw method
	 */
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		String s = NormalMapScreen.currentRunner;
		surface.text(s + " WON THE ROUND!", 200, DRAWING_HEIGHT/2);
	}
}
