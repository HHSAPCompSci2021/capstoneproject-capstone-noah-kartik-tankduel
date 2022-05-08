package Screens;

import java.awt.Rectangle;

import System.DrawingSurface;

public class GameScreen extends Screens{
	private DrawingSurface surface;

	public GameScreen(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		
	}
	
	public void draw() {
		surface.background(0,0,0);
	}
}
