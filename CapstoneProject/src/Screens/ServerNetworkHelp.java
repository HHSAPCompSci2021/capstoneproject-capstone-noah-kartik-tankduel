package Screens;
import java.awt.Point;
import java.awt.Rectangle;

import System.DrawingSurface;
import processing.core.PImage;
/**
 * This screen shows the rules of the game
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for part of code
 */
public class ServerNetworkHelp extends Screens{

	private Rectangle back;
	private Rectangle next;
	private DrawingSurface surface;
	private PImage one;
	private PImage two;
	private PImage three;
	private PImage four;
	private PImage five;
	private PImage six;
	private PImage[] images;
	private int imageNum;
	/**
	 * Gives each RulesScreen the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public ServerNetworkHelp(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);
		next = new Rectangle(990,690,70,20);
		imageNum = 0;
	}

	/**
	 * Draws the screen on the DrawingSurface
	 */
	public void draw() {
			surface.clear();
			surface.background(0,0,0);
			surface.fill(255,255,255);
			surface.textSize(10);
	
			surface.pushStyle();
			surface.textSize(70);
			surface.text("How to start a server " + (imageNum + 1), 540-surface.textWidth("How to start a server")/2,70);
			
			
			surface.image(images[imageNum], 25, 100,1030,600);

			surface.rect(back.x, back.y, back.width, back.height);
			surface.fill(0,0,0);
			surface.textSize(20);
			surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
			
			if(imageNum <5) {
				surface.fill(255,255,255);
				surface.rect(next.x, next.y, next.width, next.height);
				surface.fill(0,0,0);
				surface.textSize(20);
				surface.text("NEXT", next.x+next.width/2-surface.textWidth("NEXT")/2, next.y + next.height/2 + 7);
			}
			surface.popStyle();
		
	}
	
	/**
	 * sets up the instruction images
	 */
	public void setup() {
		images = new PImage[6];
		one = surface.loadImage("one.png");
		two = surface.loadImage("two.png");
		three = surface.loadImage("three.png");
		four = surface.loadImage("four.png");
		five = surface.loadImage("five.png");
		six = surface.loadImage("six.png");

		images[0] = one;
		images[1] = two;
		images[2] = three;
		images[3] = four;
		images[4] = five;
		images[5] = six;
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p) && imageNum == 0)
			surface.switchScreen(ScreenSwitcher.RULES);
		if(back.contains(p)&& imageNum != 0)
			imageNum--;
		if(next.contains(p) && imageNum != 5)
			imageNum++;
		
	}
	
}
