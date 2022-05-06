package Screens;
/**Credits to John Shelby for part of code
 * 
 *
 */
public interface ScreenSwitcher {
	public static final int MENU = 0;
	public static final int RULES = 1;
	public static final int PLAYSCREEN1 = 2;
	public static final int SETTING = 3;


	
	public void switchScreen(int i);
}
