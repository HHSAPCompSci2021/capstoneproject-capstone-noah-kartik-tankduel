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
	public static final int NORMALMAPSCREEN = 4;
	public static final int ROUND_OVER = 5;
	public static final int GAME_OVER = 6;
	public static final int ABILITIES = 7;
	public static final int TWOPLAYERORNETWORK = 8;
	public static final int STARTNETWORKGAME = 9;
	public static final int START1V1GAME = 10;
	public static final int WATERMAP = 11;
	public static final int FORESTMAP = 12;
	public static final int CREDITS = 13;
	public static final int FOURPLAYERORNETWORK = 14;
	public static final int FREEZETAGNORMALMAPSCREEN = 	;
	public static final int MULTIPLAYERORNETWORK = 16;
	/**
	 * switches the screens
	 * @param i the screen to switch to
	 */
	public void switchScreen(int i);
}
