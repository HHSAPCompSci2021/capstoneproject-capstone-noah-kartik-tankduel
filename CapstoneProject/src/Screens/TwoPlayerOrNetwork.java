package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;

public class TwoPlayerOrNetwork extends Screens{
	
	private Rectangle twoPlayer;
	private Rectangle server;
	private DrawingSurface surface;
	private Rectangle back;
	private Rectangle next;
	private int method = 0;

	public TwoPlayerOrNetwork(DrawingSurface surface) {
		super(1080,720);
		this.surface = surface;
		twoPlayer = new Rectangle(300,200,480,50);
		server = new Rectangle (300,300,480,50);
		back = new Rectangle(20,690,70,20);
		next = new Rectangle(990,690,70,20);
		// TODO Auto-generated constructor stub
	}
	public void draw() {
		surface.clear();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		surface.text("Choose type of method to play",540-surface.textWidth("Choose type of method to play")/2,70);
		surface.rect(twoPlayer.x, twoPlayer.y, twoPlayer.width, twoPlayer.height);	
		surface.rect(server.x, server.y, server.width, server.height);	
		surface.fill(0,0,0);
		surface.text("Two player", twoPlayer.x+twoPlayer.width/2-surface.textWidth("PLAY")/2, twoPlayer.y + twoPlayer.height/2 + 18);
		surface.text("Network", server.x+server.width/2-surface.textWidth("PLAY")/2, server.y + server.height/2 + 18);

		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(next.x, next.y, next.width, next.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);

	}
	

	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if(twoPlayer.contains(p)) {
			method = 1;
		} else if(server.contains(p)){
			method = 2;
		}

		if (back.contains(p) )
			surface.switchScreen(ScreenSwitcher.PLAYSCREEN1);
		if (next.contains(p) && method == 1)
			surface.switchScreen(ScreenSwitcher.NORMALMAPSCREEN);
	}

}
