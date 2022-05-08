package Screens;
/**
 * This class changes the screens
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for the code
 */
public interface ScreenSwitcher {
	public static final int MENU = 0;
	public static final int RULES = 1;
	public static final int PLAYSCREEN1 = 2;
	public static final int SETTING = 3;
	public static final int GAME = 4;


	/**
	 * switches the screens
	 * @param i the screen to switch to
	 */
	public void switchScreen(int i);
}
