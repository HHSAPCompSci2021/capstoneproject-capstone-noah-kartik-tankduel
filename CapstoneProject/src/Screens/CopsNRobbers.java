package Screens;

import System.DrawingSurface;

public class CopsNRobbers extends Screens{
	private DrawingSurface surface;
	public CopsNRobbers(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		// TODO Auto-generated constructor stub
	}
	public void draw() {
		surface.background(0,0,0);
	}

}
