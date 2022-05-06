package Screens;
/**
 * This class is the base structure for all of the Screen classes
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for the code
 */
public abstract class Screens {
	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * Setup of the screen
	 * @param width width of the screen
	 * @param height height of the screen
	 */
	public Screens(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Sets everything up related to the screen beforehand
	 */
	public void setup() {
		
	}
	
	/**
	 * draws the screen
	 */
	public void draw() {
		
	}
	
	/**
	 * Actions that happen to the screen when the mouse is pressed
	 */
	public void mousePressed() {
		
	}
	
	/**
	 * Actions that happen to the screen when the mouse is moved
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * Actions that happen to the screen when the mouse is dragged
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * Actions that happen to the screen when the mouse is released
	 */
	public void mouseReleased() {
		
	}
	
	
}
