
//
//  cgCanvas.java 20155
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Kapil Dole
//

import Jama.*;
import java.util.*;

/**
 * 
 * This is a simple canvas class for adding functionality for the 2D portion of
 * Computer Graphics.
 *
 */
public class cgCanvas extends simpleCanvas {

	Rasterizer rasterizer;
	clipper clipper;
	static int polygon_id = 0;
	Hashtable<Integer, Polygon> polygonList;
	Matrix transform_Matrix;
	float top, bottom, left, right;
	Polygon current_polygon;
	int view_x, view_y, view_width, view_height;

	/**
	 * 
	 * This is constructor of our class used for initialization of different
	 * objects.
	 * 
	 * @param w - Canvas Width.
	 * @param h - Canvas Height.
	 */
	cgCanvas(int w, int h) {
		super(w, h);
		// Initializing Clipper, Rasterizer and hashtable holding polygon
		// list objects.
		clipper = new clipper();
		rasterizer = new Rasterizer(h);
		polygonList = new Hashtable<Integer, Polygon>();
	}


	/**
	 * 
	 *	This method Adds and stores a polygon to the canvas. Note that this 
	 *  method does not draw the polygon, but merely stores it for later draw. 
	 *  Drawing is initiated by the draw() method. Here, we are using hash table
	 *  to hold the list of polygon with the unique polygon id.
	 *  
	 * @param x - Array of x coords of the vertices of the polygon to be added.
	 * @param y - Array of y coords of the vertices of the polygin to be added.
	 * @param n - Number of vertices in polygon.
	 * 
	 * @return Unique Polygon ID.
	 */
	public int addPoly(float x[], float y[], int n) {
		Polygon p = new Polygon(x.clone(), y.clone(), n);
		polygonList.put(polygon_id, p);
		// Returning unique polygon ID.
		return polygon_id++;
	}


	/**
	 * 
	 * This method draws the polygon with the given id. we are drawing the
	 * polygon after applying the current transformation on the vertices
	 * of the polygon.
	 * 
	 * @param polyID - the ID of the polygon to be drawn
	 * 
	 * @return None.
	 */
	public void drawPoly(int polyID) {
		// getting desired polygon with specific id.
		current_polygon = this.polygonList.get(polyID);
		int n = current_polygon.n;
		// getting input (world) coordinates of polygon and storing the copy
		// the input array.
		float[] inx = current_polygon.x.clone();
		float[] iny = current_polygon.y.clone();
		// Output array for normalized coordinates, which we get after clipping
		// the polygon.
		float[] outx = new float[50];
		float[] outy = new float[50];

		// Applying transformation (scaling, rotation, translation) if there
		// is any Otherwise we will be multiplying the coordinates with identity
		// matrix and storing the result back to the input array.
		for(int index = 0; index < n; index++){
			double[][] mat = new double[][] { { inx[index] }, { iny[index] }, { 1 } };
			// Storing the input coordinates of polygon in 3 * 1 matrix.
			Matrix current_matrix = new Matrix(mat);
			current_matrix = transform_Matrix.times(current_matrix);
			// Updating the input array with new values after transformation.
			inx[index] = (float) current_matrix.get(0, 0);
			iny[index] = (float) current_matrix.get(1, 0);
		}
		// Once transformation is applied on input coordinates, we pass the polygon for clipping.
		// After clipping the polygon, we get total number of clipped vertices
		// and the coordinates of clipped polygon.
		int clipped_vertices = clipper.clipPolygon(n, inx, iny, outx, outy, left, bottom, right, top);

		// Now, normalized coordinated are passed through Viewing Transform From
		// Clip Window which returns us screen coordinates. The sequence in view 
		// transform is translate the polygon to lower left of clipping window, 
		// Perform scaling and Translate it back to viewing window.
		// i.e. T(xv_min, yv_min) S(sx, sy) T(-left, -bottom).

		// All of above 3 operations can be combined into single matrix.
		double x_Factor = (view_width - view_x) / (right - left);
		double y_Factor = (view_height - view_y) / (top - bottom);
		double[][] view_mat = new double[][] { { x_Factor, 0, (-left * x_Factor) + view_x },
				{ 0, y_Factor, (-bottom * y_Factor) + view_y }, { 0, 0, 1 } };
		Matrix viewTransform_matrix = new Matrix(view_mat);

		// Output screen coordinates after viewing transformation.
		int[] x_screen = new int[clipped_vertices];
		int[] y_screen = new int[clipped_vertices];

		for (int index = 0; index < clipped_vertices; index++) {
			// Normalized coordinates goes input to view transform.
			double[][] mat = new double[][] { { outx[index] }, { outy[index] }, { 1 } };
			Matrix current_matrix = new Matrix(mat);
			current_matrix = viewTransform_matrix.times(current_matrix);

			// Assigning processed coordinates to output array.
			x_screen[index] = (int) current_matrix.get(0, 0);
			y_screen[index] = (int) current_matrix.get(1, 0);
		}
		// Displaying the transformed polygon on screen.
		rasterizer.drawPolygon(clipped_vertices, x_screen, y_screen, this);
	}


	/**
	 * 
	 * This method sets the current transformation to the identity matrix.
	 * 
	 * @return None.
	 */
	public void clearTransform() {
		transform_Matrix = Matrix.identity(3, 3);
	}


	/**
	 * 
	 * This method adds the translation to the current transformation by
	 * pre-multiplying the appropriate translation matrix to the current
	 * transformation matrix.
	 * 
	 * @param x - Amount of translation in x
	 * @param y - Amount of translation in y
	 * 
	 * @return None.
	 */
	public void translate(float x, float y) {
		Matrix translate_matrix = new Matrix(new double[][] { { 1, 0, x }, { 0, 1, y }, { 0, 0, 1 } });
		transform_Matrix = translate_matrix.times(transform_Matrix);
	}


	/**
	 * 
	 * This method adds a rotation to the current transformation by pre-multiplying
	 * the appropriate rotation matrix to the current transformation matrix.
	 * 
	 * @param degrees - Amount of rotation in degrees
	 * 
	 * @return None.
	 */
	public void rotate(float degrees) {
		double rad = Math.toRadians(degrees);
		Matrix rotation_matrix = new Matrix(new double[][] { { Math.cos(rad), -Math.sin(rad), 0 },
				{ Math.sin(rad), Math.cos(rad), 0 }, { 0, 0, 1 } });
		transform_Matrix = rotation_matrix.times(transform_Matrix);
	}


	/**
	 * 
	 * This method adds a scale to the current transformation by pre-multiplying
	 * the appropriate scaling matrix to the current transformation matrix.
	 * 
	 * @param x - Amount of scaling in x.
	 * @param y - Amount of scaling in y.
	 * 
	 * @return None.
	 */
	public void scale(float x, float y) {
		Matrix scale_matrix = new Matrix(new double[][] { { x, 0, 0 }, { 0, y, 0 }, { 0, 0, 1 } });
		transform_Matrix = scale_matrix.times(transform_Matrix);
	}


	/**
	 * 
	 * This method defines clip window.
	 * 
	 * @param bottom - y coord of bottom edge of clip window (world coordinates).
	 * @param top - y coord of top edge of clip window (world coordinates).
	 * @param left - x coord of left edge of clip window (in world coordinates).
	 * @param right - x coord of right edge of clip window (in world coordinates).
	 * 
	 * @return None.
	 */
	public void setClipWindow(float bottom, float top, float left, float right) {
		this.bottom = bottom;
		this.top = top;
		this.left = left;
		this.right = right;
	}


	/**
	 * 
	 * This method defines the viewport.
	 * 
	 * @param x - x coord of lower left of view window (screen coordinates).
	 * @param y - y coord of lower left of view window (screen coordinates).
	 * @param width - width of view window (world coordinates).
	 * @param height - width of view window (world coordinates).
	 * 
	 * @return None.
	 */
	public void setViewport(int x, int y, int width, int height) {
		this.view_x = x;
		this.view_y = y;
		this.view_width = x + width;
		this.view_height = y + height;
	}

	
	/**
	 * 
	 * This inner class holds end-point details of the polygon (x and y
	 * coordinates) and total number of vertices (n).
	 * 
	 * @author Kapil Dole
	 *
	 */
	public class Polygon {
		float[] x;
		float[] y;
		int n;

		/**
		 * 
		 * Constructor used for initialization.
		 * 
		 * @param x - List of x coordinates of polygon vertices
		 * @param y - List of y coordinates of polygon vertices
		 * @param n - Total number of polygon vertices.
		 */
		public Polygon(float[] x, float[] y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}
}
