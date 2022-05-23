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
	
		
		
		MenuScreen menu = new MenuScreen(this);//0
		screens.add(menu);
		
		RulesScreen rules = new RulesScreen(this);//1
		screens.add(rules);
		
		PlayScreen1 playScreen1 = new PlayScreen1(this);//2
		screens.add(playScreen1);
		
		SettingScreen setting = new SettingScreen(this);//3
		screens.add(setting);
		
		
		NormalMapScreen normalMap = new NormalMapScreen(this);//4
		screens.add(normalMap);
		
		RoundOverScreen roundOver = new RoundOverScreen(this);//5
		screens.add(roundOver);
		
		GameOverScreen gameOver = new GameOverScreen(this);//6
		screens.add(gameOver);
		
		AbilitiesInstructScreen abilities = new AbilitiesInstructScreen(this);//7
		screens.add(abilities);
		
		TwoPlayerOrNetworkNotUsed network = new TwoPlayerOrNetworkNotUsed(this);//8
		screens.add(network);
		
		StartNetworkGame networkGame = new StartNetworkGame(this);//9
		screens.add(networkGame);
		
		Start1v1Game oneVOneGame = new Start1v1Game(this);//10
		screens.add(oneVOneGame);
		
		WaterMapScreen waterMap = new WaterMapScreen(this);//11
		screens.add(waterMap);
		
		ForestMapScreen forestMap = new ForestMapScreen(this);//12
		screens.add(forestMap);
		
		CreditsScreen credits = new CreditsScreen(this);//13
		screens.add(credits);
		
		FourPlayerOrNetworkNotUsed fourPlayer = new FourPlayerOrNetworkNotUsed(this);//14
		screens.add(fourPlayer);
		
		NormalMapFreezeTagScreen freeze = new NormalMapFreezeTagScreen(this);//15
		screens.add(freeze);
		
		MultiplayerOrNetwork multiNetwork = new MultiplayerOrNetwork(this);//16
		screens.add(multiNetwork);
		
		NormalMapCopsNRobbers copsNRobbers = new NormalMapCopsNRobbers(this);//17
		screens.add(copsNRobbers);
		
		WaterMapFreezeTagScreen waterMapFreezeTag = new WaterMapFreezeTagScreen(this);//18
		screens.add(waterMapFreezeTag);
		
		CopsNRobbersWaterMap copsNRobbersWaterMap = new CopsNRobbersWaterMap(this);
		screens.add(copsNRobbersWaterMap);
		
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
	
	public int getMapScreen() {
		if(map == 0 && gameMode == 1)//normal map/normal mode
			return 4;
		if(map == 0 && gameMode == 2)//normal map/freeze mode
			return 15;
		if(map == 0 && gameMode == 3)//normal map/cops n robbers mode
			return 17;
		
		
		if(map == 1 && gameMode == 1)//water map/normal mode
			return 11;
		if(map == 0 && gameMode == 2)//water map/freeze mode
			return 18;
		if(map == 0 && gameMode == 3)//water map/cops n robbers mode
			return 17;
		
		
		if(map == 0 && gameMode == 1)//forest map/normal mode
			return 4;
		if(map == 0 && gameMode == 2)//forest map/freeze mode
			return 15;
		if(map == 0 && gameMode == 3)//forest map/cops n robbers mode
			return 17;
		return 17;
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
		
		NormalMapFreezeTagScreen freezeMode = new NormalMapFreezeTagScreen(this);
		
		NormalMapCopsNRobbers copsNRobbers = new NormalMapCopsNRobbers(this);
		
		WaterMapFreezeTagScreen waterMapFreeze = new WaterMapFreezeTagScreen(this);
		
		CopsNRobbersWaterMap waterMapCopsNRobbers = new CopsNRobbersWaterMap(this);
		screens.set(17, copsNRobbers);
		screens.set(4, normalMap);
		screens.set(5, roundOver);
		screens.set(15, freezeMode);
		screens.set(18, waterMapFreeze);
		screens.set(19, waterMapCopsNRobbers);
	}

}