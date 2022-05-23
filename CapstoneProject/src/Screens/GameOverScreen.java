package Screens;

import System.DrawingSurface;
/**
 * The game over screen
 * @author Noah Pien and Kartik Joshi
 */
public class GameOverScreen extends Screens{
	private DrawingSurface surface;

	/**
	 * Constructor for GameOverScreen where you call the super constructor to make a full sized window
	 * @param surface the surface to draw on
	 */
	public GameOverScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
	}
	/**
	 * draw the screen when the game is over
	 */
	public void draw() {
		surface.background(255,255,255);
		surface.fill(0,0,0);
		

	}
}