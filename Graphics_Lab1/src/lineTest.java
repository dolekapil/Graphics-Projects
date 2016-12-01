
//
//  lineTest.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2014 Rochester Institute of Technology. All rights reserved.
//
//  Modified by Vasudev Prasad Bethamcherla on 08/19/2014
//
//  Contributor:  YOUR_NAME_HERE
//

import java.awt.*;
import java.awt.event.*;

public class lineTest implements KeyListener {

	private static int drawHeight = 600;
	private static int drawWidth = 600;

	private simpleCanvas S;
	private Rasterizer R;

	lineTest(int w, int h) {

		S = new simpleCanvas(600, 600);
		R = new Rasterizer(600);

		S.setColor(0.0f, 0.0f, 0.0f);
		S.clear();
		S.setColor(1.0f, 1.0f, 1.0f);

		// Idea for lettering style from:
		// http://patorjk.com/software/taag/#p=display&f=Star%20Wars&t=Type%20Something
		//		          _______   ______   
		//		         /  _____| /  __  \
		//		        |  |  __  |  |  |  | 
		//		        |  | |_ | |  |  |  | 
		//		        |  |__| | |  `--'  | 
		//		         \______|  \______/


		// ######## The letter 'G' in green ########
		S.setColor(0.0f, 1.0f, 0.0f);
		R.drawLine(80, 340, 220, 340, S); // Horizontal left to right
		R.drawLine(40, 380, 80, 340, S); // 315 degree slope
		R.drawLine(220, 340, 260, 380, S); // 45 degree slope
		R.drawLine(260, 380, 260, 440, S); // Vertical bottom to top
		R.drawLine(260, 440, 180, 440, S); // Horizontal right to left
		R.drawLine(180, 440, 180, 400, S);
		R.drawLine(180, 400, 220, 400, S);
		R.drawLine(220, 400, 200, 380, S);
		R.drawLine(200, 380, 100, 380, S);
		R.drawLine(100, 380, 80, 400, S);
		R.drawLine(80, 400, 80, 500, S);
		R.drawLine(80, 500, 100, 520, S);
		R.drawLine(100, 520, 200, 520, S);
		R.drawLine(200, 520, 220, 500, S);
		R.drawLine(220, 500, 220, 480, S);
		R.drawLine(220, 480, 260, 480, S);
		R.drawLine(260, 480, 260, 520, S);
		R.drawLine(260, 520, 220, 560, S); // 135 degree slope
		R.drawLine(220, 560, 80, 560, S);
		R.drawLine(80, 560, 40, 520, S); // 225 degree slope
		R.drawLine(40, 520, 40, 380, S); // Vertical top to bottom

		// ######## The letter 'O' in orange ########
		S.setColor(1.0f, 0.647f, 0.0f);
		R.drawLine(450, 320, 520, 340, S); // 16.6 degree slope
		R.drawLine(520, 340, 540, 360, S); // 45 degree slope
		R.drawLine(540, 360, 560, 450, S); // 77.47 degree slope
		R.drawLine(560, 450, 540, 540, S); // 102.83 degree slope
		R.drawLine(540, 540, 520, 560, S); // 135 degree slope
		R.drawLine(520, 560, 450, 580, S); // 163.3 degree slope
		R.drawLine(450, 580, 380, 560, S); // 196.71 degree slope
		R.drawLine(380, 560, 360, 540, S); // 225 degree slope
		R.drawLine(360, 540, 340, 450, S);
		R.drawLine(340, 450, 360, 360, S);
		R.drawLine(360, 360, 380, 340, S);
		R.drawLine(380, 340, 450, 320, S);
		R.drawLine(420, 380, 480, 380, S);
		R.drawLine(480, 380, 520, 420, S);
		R.drawLine(520, 420, 520, 480, S);
		R.drawLine(520, 480, 480, 520, S);
		R.drawLine(480, 520, 420, 520, S);
		R.drawLine(420, 520, 380, 480, S);
		R.drawLine(380, 480, 380, 420, S);
		R.drawLine(380, 420, 420, 380, S);

		// ##### Use light blue to write your initials #####

		S.setColor(0.678f, 0.847f, 0.902f);

		// ######## The letter 'K' in light blue. ########
		R.drawLine(40, 280, 40, 60, S); // Vertical top to bottom
		R.drawLine(40, 280, 80, 280, S); // Horizontal line
		R.drawLine(80, 280, 80, 170, S); // Vertical line
		R.drawLine(80, 170, 200, 240, S); // 30.25 degree slope
		R.drawLine(200, 240, 220, 280, S); // 63.43 degree slope
		R.drawLine(220, 280, 260, 280, S);
		R.drawLine(260, 280, 225, 215, S);
		R.drawLine(225, 215, 130, 155, S);
		R.drawLine(130, 155, 280, 60, S);
		R.drawLine(280, 60, 225, 60, S);
		R.drawLine(80, 140, 80, 60, S);
		R.drawLine(80, 140, 100, 140, S);
		R.drawLine(100, 140, 225, 60, S);
		R.drawLine(80, 60, 40, 60, S);

		// ######## The letter 'D' in light blue. ########
		R.drawLine(340, 280, 340, 250, S); // Vertical line top to bottom
		R.drawLine(340, 250, 380, 250, S); // Horizontal line
		R.drawLine(340, 280, 500, 280, S); // Horizontal line
		R.drawLine(500, 280, 540, 250, S); // 324 degree slope
		R.drawLine(540, 250, 540, 100, S);
		R.drawLine(540, 100, 500, 70, S); // 36 degree slope
		R.drawLine(500, 70, 340, 70, S);
		R.drawLine(340, 70, 340, 100, S);
		R.drawLine(340, 100, 380, 100, S);
		R.drawLine(380, 250, 380, 100, S);
		R.drawLine(410, 250, 490, 250, S);
		R.drawLine(490, 250, 510, 235, S);
		R.drawLine(510, 235, 510, 120, S);
		R.drawLine(410, 100, 490, 100, S);
		R.drawLine(510, 120, 490, 100, S);
		R.drawLine(410, 100, 410, 250, S);
	}

	// Because we are a KeyListener
	public void keyTyped(KeyEvent e) {
		// What key did we type?
		char key = e.getKeyChar();

		if ((key == 'Q') || (key == 'q'))
			System.exit(0); // quit

	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	static public void main(String[] args) {

		lineTest L = new lineTest(drawWidth, drawHeight);
		L.S.addKeyListener(L);

		Frame f = new Frame("Line Test");
		f.add("Center", L.S);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
}
