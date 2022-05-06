package System;
import java.awt.Point;
import java.util.ArrayList;
import Screens.*;
import processing.core.PApplet;
/**Credits to John Shelby for part of code
 * 
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	
	private Screens activeScreen;
	private ArrayList<Screens> screens;

	
	public DrawingSurface() {
		
		
		screens = new ArrayList<Screens>();
		
		keys = new ArrayList<Integer>();
		
		
		MenuScreen menu = new MenuScreen(this);
		screens.add(menu);
		
		RulesScreen rules = new RulesScreen(this);
		screens.add(rules);
		
		PlayScreen1 playScreen1 = new PlayScreen1(this);
		screens.add(playScreen1);
		
		SettingScreen setting = new SettingScreen(this);
		screens.add(setting);

		activeScreen = screens.get(0);
		
	}
	
	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screens s : screens)
			s.setup();
	}
	
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	@SuppressWarnings("removal")
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

}