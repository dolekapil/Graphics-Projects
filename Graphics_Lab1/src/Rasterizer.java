//
//  Rasterizer.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  YOUR_NAME_HERE
//

///
// 
// A simple class for performing rasterization algorithms.
//
///

import java.util.*;

public class Rasterizer {

	///
	// number of scanlines
	///

	int n_scanlines;

	///
	// Constructor
	//
	// @param n number of scanlines
	//
	///

	Rasterizer(int n) {
		n_scanlines = n;
	}

	///
	// Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
	//
	// Implementation should be using the Midpoint Method
	//
	// You are to add the implementation here using only calls
	// to C.setPixel()
	//
	// @param x0 x coord of first endpoint
	// @param y0 y coord of first endpoint
	// @param x1 x coord of second endpoint
	// @param y1 y coord of second endpoint
	// @param C The canvas on which to apply the draw command.
	///

	public void drawLine(int x0, int y0, int x1, int y1, simpleCanvas C) {
		int x, y;
		int deltaY = Math.abs(y1 - y0), deltaX = Math.abs(x1 - x0);

		// For vertical line, we will be incrementing y coordinate keeping
		// x coordinate constant.
		if (deltaX == 0) {
			y = Math.min(y0, y1);
			int yMax = Math.max(y0, y1);
			while (y <= yMax) {
				C.setPixel(x0, y);
				y++;
			}
			// For horizontal line, we will be incrementing x coordinate
			// keeping y coordinate constant.
		} else if (deltaY == 0) {
			x = Math.min(x0, x1);
			int xMax = Math.max(x0, x1);

			while (x <= xMax) {
				C.setPixel(x, y0);
				x++;
			}
		}
		// For diagonal line, depending on the slope we will be incrementing
		// or decrementing both x and y coordinate.
		else if (Math.abs(deltaX) == Math.abs(deltaY)) {
			x = x0;
			y = y0;
			int xIncrement = 1, yIncrement = 1;
			if ((x1 - x0) < -1) {
				xIncrement = -1;
			}
			if ((y1 - y0) < -1) {
				yIncrement = -1;
			}

			while (x != x1 && y != y1) {
				C.setPixel(x, y);
				x = x + xIncrement;
				y = y + yIncrement;
			}
			C.setPixel(x, y);
		} else {

			// For gentle positive slope, increment x coordinate for every
			// pixel and conditionally increment y coordinate based on value
			// of d. (0 < m < 1)
			if (((y1 - y0 > 0 && x1 - x0 > 0) && (y1 - y0 <= x1 - x0)) || ((y1 - y0 < 0 && x1 - x0 < 0) && (y1 - y0 >= x1 - x0))) {
				int deltaE = 2 * deltaY;
				int deltaNE = 2 * (deltaY - deltaX);
				int d = deltaE - deltaX;

				for (x = Math.min(x0, x1), y = Math.min(y0, y1); x <= Math.max(x0, x1); ++x) {

					C.setPixel(x, y);

					if (d <= 0) {
						d += deltaE;
					} else {
						++y;
						d += deltaNE;
					}
				}
			}
			// For steep positive slope, increment y coordinate for every
			// pixel and conditionally increment x coordinate based on value
			// of d. (m > 1)
			else if (((y1 - y0 > 0 && x1 - x0 > 0) && (y1 - y0 >= x1 - x0)) || ((y1 - y0 < 0 && x1 - x0 < 0) && (y1 - y0 <= x1 - x0))) {
				int deltaN = 2 * deltaX;
				int deltaNE = 2 * (deltaX - deltaY);
				int d = deltaN - deltaY;

				for (x = Math.min(x0, x1), y = Math.min(y0, y1); y <= Math.max(y0, y1); ++y) {
					C.setPixel(x, y);

					if (d <= 0) {
						d += deltaN;
					} else {
						++x;
						d += deltaNE;
					}
				}
			}
			// For gentle negative slope, increment x coordinate for every
			// pixel and conditionally decrement y coordinate based on value
			// of d. (-1 < m < 0) 
			else if (((y1 - y0 < 0 && x1 -x0 > 0) || (y1 - y0 > 0 && x1 - x0 < 0)) && (Math.abs(y1 - y0) <= Math.abs(x1 - x0))) {
				int deltaW = 2 * deltaY;
				int deltaNW = 2 * (deltaY - deltaX);
				int d = deltaW - deltaX;

				for (x = Math.min(x0, x1), y = Math.max(y0, y1); x <= Math.max(x0, x1); ++x) {

					C.setPixel(x, y);

					if (d <= 0) {
						d += deltaW;
					} else {
						--y;
						d += deltaNW;
					}
				}
			}
			// For steep negative slope, increment y coordinate for every
			// pixel and conditionally decrement x coordinate based on value
			// of d. (m < -1)
			else {
				int deltaN = 2 * deltaX;
				int deltaNW = 2 * (deltaX - deltaY);
				int d = deltaN - deltaY;

				for (x = Math.max(x0, x1), y = Math.min(y0, y1); y <= Math.max(y0, y1); ++y) {

					C.setPixel(x, y);

					if (d <= 0) {
						d += deltaN;
					} else {
						--x;
						d += deltaNW;
					}
				}
			}
		}
	}
}
