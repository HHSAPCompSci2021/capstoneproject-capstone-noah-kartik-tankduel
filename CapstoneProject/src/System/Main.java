package System;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * contains the main method
 * @author Noah Pien and Kartik Joshi
 *
 */
public class Main {

	/**
	 * Runs the main method and starts the game
	 * @param args the string
	 */
		public static void main(String args[]) {

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setMinimumSize(new Dimension(720, 480));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);		
		canvas.requestFocus();
	}

}
