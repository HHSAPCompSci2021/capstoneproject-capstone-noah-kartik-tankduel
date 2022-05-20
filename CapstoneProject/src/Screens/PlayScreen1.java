package Screens;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import System.DrawingSurface;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkManagementPanel;
/**
 * This screen is the first PlayScreen of the game. From this screen a person can choose the game mode and the map
 * 
 * @author Noah Pien and Kartik Joshi
 * Credits to John Shelby for part of code
 */
public class PlayScreen1 extends Screens{

	private Rectangle back;
	private Rectangle map;
	private Rectangle normalMap;
	private Rectangle normalMode;
	private Rectangle freezeMode;
	private Rectangle copsNRobbers;
	private DrawingSurface surface;
	private Rectangle startButton;
	private Rectangle waterMap;
	private Rectangle forestMap;
	public static NetworkManagementPanel nmp;

	/**
	 * Gives the PlayScreen the default values
	 * @param surface the DrawingSurface on which to draw on
	 */
	public PlayScreen1(DrawingSurface surface) {
		super(1080, 720);
		this.surface = surface;
		back = new Rectangle(20,690,70,20);
		map = new Rectangle(50,50,70,70);
		waterMap = new Rectangle(150,400,250,100);
		normalMap = new Rectangle(150,250,250,100);
		normalMode = new Rectangle(700,250,250,100);
		freezeMode = new Rectangle(700,400,250,100);
		copsNRobbers = new Rectangle(700,550,250,100);
		startButton = new Rectangle(430,450,200,100);
		forestMap = new Rectangle(150,550,250,100);

	}

	/**
	 * Draws the screen on the DrawingSurface
	 */
	public void draw() {
		surface.clear();
		
		surface.pushStyle();
		surface.background(0,0,0);
		surface.fill(255,255,255);
		
		surface.pushStyle();
		surface.stroke(25,255,255);
		surface.strokeWeight(4);
		surface.line(538, 0, 538, 720);
		surface.popStyle();

		
		//Maps
		surface.textSize(40);
		surface.text("CHOOSE A MAP", (540-surface.textWidth("CHOOSE A MAP"))/2,150);
		
		if(surface.getMap() == 0) {
			surface.fill(25,255,255);
			surface.rect(normalMap.x-5, normalMap.y-5, normalMap.width+10, normalMap.height+10);
		}
		if(surface.getMap() == 1) {
			surface.fill(25,255,255);
			surface.rect(waterMap.x-5, waterMap.y-5, waterMap.width+10, waterMap.height+10);
		}
		if(surface.getMap() == 2) {
			surface.fill(25,255,255);
			surface.rect(forestMap.x-5, forestMap.y-5, forestMap.width+10, forestMap.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(normalMap.x, normalMap.y, normalMap.width, normalMap.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("NORMAL MAP", normalMap.x+normalMap.width/2-surface.textWidth("NORMAL MAP")/2, normalMap.y + normalMap.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(waterMap.x, waterMap.y, waterMap.width, waterMap.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("WATER MAP", waterMap.x+waterMap.width/2-surface.textWidth("WATER MAP")/2, waterMap.y + waterMap.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(forestMap.x, forestMap.y, forestMap.width, forestMap.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("FOREST MAP", forestMap.x+forestMap.width/2-surface.textWidth("FOREST MAP")/2, forestMap.y + forestMap.height/2 + 7);
		
		//Game Modes
		surface.fill(255,255,255);
		surface.textSize(40);
		surface.text("CHOOSE A GAMEMODE", 540 + (540 - surface.textWidth("CHOOSE A GAMEMODE"))/2,150);
		
		if(surface.getGameMode() == 1) {
			surface.fill(25,255,255);
			surface.rect(normalMode.x-5, normalMode.y-5, normalMode.width+10, normalMode.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(normalMode.x, normalMode.y, normalMode.width, normalMode.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("NORMAL MODE", normalMode.x+normalMode.width/2-surface.textWidth("NORMAL MODE")/2, normalMode.y + normalMode.height/2 + 7);
	
		if(surface.getGameMode() == 2) {
			surface.fill(25,255,255);
			surface.rect(freezeMode.x-5, freezeMode.y-5, freezeMode.width+10, freezeMode.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(freezeMode.x, freezeMode.y, freezeMode.width, freezeMode.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("FREEZE MODE", freezeMode.x+freezeMode.width/2-surface.textWidth("FREEZE MODE")/2, freezeMode.y + freezeMode.height/2 + 7);
		
		if(surface.getGameMode() == 3) {
			surface.fill(25,255,255);
			surface.rect(copsNRobbers.x-5, copsNRobbers.y-5, copsNRobbers.width+10, copsNRobbers.height+10);
		}
		surface.fill(255,255,255);
		surface.rect(copsNRobbers.x, copsNRobbers.y, copsNRobbers.width, copsNRobbers.height);
		surface.fill(0,0,0);
		surface.textSize(30);
		surface.text("COPS N ROBBERS", copsNRobbers.x+copsNRobbers.width/2-surface.textWidth("COPS N ROBBERS")/2, copsNRobbers.y + copsNRobbers.height/2 + 7);
		
		surface.fill(255,255,255);
		surface.rect(startButton.x, startButton.y, startButton.width, startButton.height);
		surface.fill(0,0,0);
		surface.text("Start", startButton.x+startButton.width/2-surface.textWidth("Start")/2, startButton.y + startButton.height/2 + 7);

		//Back button
		surface.fill(255,255,255);
		surface.rect(back.x, back.y, back.width, back.height);
		surface.fill(0,0,0);
		surface.textSize(20);
		surface.fill(0,0,0);
		//surface.rect(map.x, map.y, map.width, map.height);
		surface.text("BACK", back.x+back.width/2-surface.textWidth("BACK")/2, back.y + back.height/2 + 7);
		
		surface.popStyle();
	}
	
	/**
	 * When the mouse is pressed in specific locations, the screen changes
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (back.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU);
		if (normalMap.contains(p))
			surface.setMap(0);
		if (normalMode.contains(p))
			surface.setGameMode(1);
		if (freezeMode.contains(p))
			surface.setGameMode(2);
		if (copsNRobbers.contains(p))
			surface.setGameMode(3);
		if(waterMap.contains(p)) {
			surface.setMap(1);
		}
		if(forestMap.contains(p))
			surface.setMap(2);
		if(startButton.contains(p) && !MultiplayerOrNetwork.network &&surface.getGameMode() == 1 && surface.getMap() == 0) 
			surface.switchScreen(ScreenSwitcher.START1V1GAME);
		if(startButton.contains(p) && !MultiplayerOrNetwork.network && surface.getGameMode() == 2 && surface.getMap() == 0)
			surface.switchScreen(ScreenSwitcher.START1V1GAME);
		if(startButton.contains(p) && !MultiplayerOrNetwork.network && surface.getGameMode() == 1 && surface.getMap() == 1) 
			surface.switchScreen(ScreenSwitcher.START1V1GAME);
		if(startButton.contains(p) && !MultiplayerOrNetwork.network && surface.getGameMode() == 1 && surface.getMap() == 2) 
			surface.switchScreen(ScreenSwitcher.START1V1GAME);
		
		
		if(startButton.contains(p) && MultiplayerOrNetwork.network) {
			nmp = new NetworkManagementPanel("ProcessingDrawing", 10, (NetworkListener) surface.getScreen(surface.getMapScreen()));
			while(!(NetworkManagementPanel.connectedSuccess || NetworkManagementPanel.isHost)) {
				System.out.print("");
			}
			surface.switchScreen(ScreenSwitcher.STARTNETWORKGAME);

		}

	}
	
	
}

