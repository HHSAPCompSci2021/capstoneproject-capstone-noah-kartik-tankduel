package System;
import java.awt.Point;
import java.util.ArrayList;
import Screens.*;
import processing.core.PApplet;
/**
 * This class chooses which screen is currently shown and also changes the insides of the window depending on the size
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for part of code
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	private ArrayList<Integer> keys;
	private Screens activeScreen;
	private ArrayList<Screens> screens;
	private boolean inputMethod; // false for WASD and true for arrow keys

	/**
	 * Sets the DrawingSurface
	 */
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
	
	/**
	 * adjusts the screen size
	 */
	public void settings() {
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	/**
	 * sets up each screen
	 */
	public void setup() {
		surface.setResizable(true);
		for (Screens s : screens)
			s.setup();
	}
	
	/**
	 * draws each screen
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	/**
	 * when a key is pressed, keyCode is added to keys
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/**
	 * when a key is released, the keyCode is removed from keys
	 */
	@SuppressWarnings("removal")
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * when it is pressed determines whether keys contains code
	 * @param code the code
	 * @return a boolean whether the integer is inside keys
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	 * does the mousePressed action of the current screen
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * does the mouseMoved action of the current screen
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	 * does the mouseDragged action of the current screen
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * does the mouseReleased action of the current screen
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * gives the coordinate points after the window is stretched
	 * @param assumed point supposedly clicked
	 * @return actual point location
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * gives the coordinate points after the window is stretched
	 * @param actual the actual point clicked
	 * @return supposed point location
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	/**
	 * switches the screen
	 * @param i the screen to switch to
	 */
	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	public void setInputMethod(boolean input) {
		inputMethod = input;
	}
	
	public boolean getInputMethod() {
		return inputMethod;
	}

}