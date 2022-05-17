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
	private int map; // 0 for normal map
	private int gameMode; // 1 for normal mode, 2 for freeze mode, 3 for cops n robbers, 0 is for when the user hasn't chosen a game mode yet

	/**
	 * Sets the DrawingSurface
	 */
	public DrawingSurface() {
		
		
		screens = new ArrayList<Screens>();
		keys = new ArrayList<Integer>();
		inputMethod = false;
		map = 0;
		gameMode = 0;
		
		MenuScreen menu = new MenuScreen(this);
		screens.add(menu);
		
		RulesScreen rules = new RulesScreen(this);
		screens.add(rules);
		
		PlayScreen1 playScreen1 = new PlayScreen1(this);
		screens.add(playScreen1);
		
		SettingScreen setting = new SettingScreen(this);
		screens.add(setting);
		
		
		NormalMapScreen normalMap = new NormalMapScreen(this);
		screens.add(normalMap);
		
		RoundOverScreen roundOver = new RoundOverScreen(this);
		screens.add(roundOver);
		
		GameOverScreen gameOver = new GameOverScreen(this);
		screens.add(gameOver);
		
		AbilitiesInstructScreen abilities = new AbilitiesInstructScreen(this);
		screens.add(abilities);
		
		TwoPlayerOrNetwork network = new TwoPlayerOrNetwork(this);
		screens.add(network);
		
		StartNetworkGame networkGame = new StartNetworkGame(this);
		screens.add(networkGame);
		
		Start1v1Game oneVOneGame = new Start1v1Game(this);
		screens.add(oneVOneGame);
		
		WaterMapScreen waterMap = new WaterMapScreen(this);
		screens.add(waterMap);
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
	
	/**
	 * sets the input method to input
	 * @param input the input method
	 */
	public void setInputMethod(boolean input) {
		inputMethod = input;
	}
	
	/**
	 * returns the current input method
	 * @return the current input method
	 */
	public boolean getInputMethod() {
		return inputMethod;
	}
	
	/**
	 * sets the map
	 * @param i the map
	 */
	public void setMap(int i) {
		map = i;
	}
	
	/**
	 * returns the current map
	 * @return the map
	 */
	public int getMap() {
		return map;
	}
	
	/**
	 * sets the game mode
	 * @param i the game mode
	 */
	public void setGameMode(int i) {
		gameMode = i;
	}
	
	/**
	 * gets the game mode
	 * @return the current game mode
	 */
	public int getGameMode() {
		return gameMode;
	}
	
	/**
	 * gets the round winner
	 * @return the round winner
	 */
	public boolean getRoundWinner() {
		return ((NormalMapScreen)screens.get(NORMALMAPSCREEN)).getRoundWinner();
	}
	
	public Screens getScreen(int i) {
		return screens.get(i);
	}
	
	public void refresh1v1() {		
		
		NormalMapScreen normalMap = new NormalMapScreen(this);
		
		RoundOverScreen roundOver = new RoundOverScreen(this);
								
		screens.set(4, normalMap);
		screens.set(5, roundOver);
	}

}