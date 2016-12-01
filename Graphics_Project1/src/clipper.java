import java.util.ArrayList;

//
//  Clipper.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Kapil Dole
//

/**
 * 
 * This class provides object for performing clipping
 *
 */
public class clipper {

	/**
	 * 
	 * This inner class holds end-point details of the polygon. (x and y
	 * coordinates)
	 * 
	 * @author Kapil Dole
	 *
	 */
	public class EndPoint {
		float x, y;

		// Constructor for initialization.
		public EndPoint(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * 
	 * This inner class holds information about clipping edge, which includes
	 * edge end-points and its orientation.
	 * 
	 * @author Kapil Dole
	 *
	 */
	public class ClipperEdge {

		EndPoint e1, e2;
		String orientation;

		// Constructor for initialization.
		public ClipperEdge(EndPoint e1, EndPoint e2, String orientation) {
			this.e1 = e1;
			this.e2 = e2;
			this.orientation = orientation;
		}

		/**
		 * 
		 * This method checks whether the given end-point is inside of
		 * particular clipping edge. we calculate that by the orientation of the
		 * clipping edge.
		 * 
		 * @param 		eCheck		End point to check.
		 * @return		boolean		true if inside, else false.
		 */
		public boolean checkInside(EndPoint eCheck) {
			// If the the clipping edge is left, check whether the x coordinate
			// of the given end-point is greater than that of clipping edge.
			if (orientation.equals("Left")) {
				return eCheck.x > e1.x;
			}
			// If it is right clipping edge, check whether x coordinate of the
			// given end point is lesser than that of clipping edge.
			else if (orientation.equals("Right")) {
				return eCheck.x < e1.x;
			}
			// If the clipping edge is top, then check whether the y coordinate
			// of given end-point is lesser than that of clipping edge.
			else if (orientation.equals("Top")) {
				return eCheck.y < e1.y;
			}
			// If the clipping edge is bottom, then check whether the y
			// coordinate of given end-point is greater than that of clipping
			// edge.
			else {
				return eCheck.y > e1.y;
			}
		}

		/**
		 * 
		 * This function computes the point of intersection between two lines,
		 * when only their end points are given. point of intersection can be
		 * calculated by below formula. 
		 * Px = ((x1*y2 - y1*x2)(x3 - x4) - (x1 - x2)(x3*y4 - y3*x4)) / 
		 * ((x1 - x2)(y3 - y4) - (y1 - y2)(x3 - x4)) 
		 * Py = ((x1*y2 - y1*x2)(y3 - y4) - (y1 - y2)(x3*y4 - y3*x4)) / 
		 * ((x1 - x2)(y3 - y4) - (y1 - y2)(x3 - x4))
		 * 
		 * @param 		e3 			First end point
		 * @param 		e4 			Second end point.
		 * @return		Endpoint	point of intersection.
		 */
		public EndPoint getIntersectionPoint(EndPoint e3, EndPoint e4) {
			float denominator = (e1.x - e2.x) * (e3.y - e4.y) - (e1.y - e2.y) * (e3.x - e4.x);

			// If the denominator is zero, that means the given two lines
			// Does not intersect, and hence we return null in this case
			// otherwise we return point of intersection.
			if (denominator != 0) {
				float Px = (((e1.x * e2.y - e1.y * e2.x) * (e3.x - e4.x))
						- ((e1.x - e2.x) * (e3.x * e4.y - e3.y * e4.x))) / denominator;
				float Py = (((e1.x * e2.y - e1.y * e2.x) * (e3.y - e4.y))
						- ((e1.y - e2.y) * (e3.x * e4.y - e3.y * e4.x))) / denominator;

				EndPoint intersection = new EndPoint(Px, Py);
				return intersection;
			}
			return null;
		}
	}

	/**
	 * 
	 * clipPolygon
	 * Clip the polygon with vertex count in and vertices inx/iny
	 * against the rectangular clipping region specified by lower-left
	 * corner (llx,lly) and upper-right corner (urx,ury). 
	 * The resulting vertices are placed in outx/outy.
	 *
	 * The routine should return the the vertex count of the polygon
	 * resulting from the clipping.
	 * 
	 * @param in	the number of vertices in the polygon to be clipped
	 * @param inx	x coords of vertices of polygon to be clipped.
	 * @param iny	y coords of vertices of polygon to be clipped.
	 * @param outx	x coords of vertices of polygon resulting after clipping.
	 * @param outy	y coords of vertices of polygon resulting after clipping.
	 * @param llx	x coord of lower left of clipping rectangle.
	 * @param lly	y coord of lower left of clipping rectangle.
	 * @param urx	x coord of upper right of clipping rectangle.
	 * @param ury	y coord of upper right of clipping rectangle.
	 * @return int  number of vertices in the polygon resulting after clipping.
	 */
	public int clipPolygon(int in, float inx[], float iny[], float outx[], float outy[], float llx, float lly,
			float urx, float ury) {

		// Creating new array list of type clippingEdges.
		ArrayList<ClipperEdge> clippingEdges = new ArrayList<ClipperEdge>();
		// Creating end-point objects for clipping edge.
		EndPoint lowerLeft = new EndPoint(llx, lly);
		EndPoint upperLeft = new EndPoint(llx, ury);
		EndPoint lowerRight = new EndPoint(urx, lly);
		EndPoint upperRight = new EndPoint(urx, ury);

		// Adding all 4 clipping edges to array list.
		clippingEdges.add(new ClipperEdge(lowerLeft, upperLeft, "Left"));
		clippingEdges.add(new ClipperEdge(upperLeft, upperRight, "Top"));
		clippingEdges.add(new ClipperEdge(lowerRight, upperRight, "Right"));
		clippingEdges.add(new ClipperEdge(lowerLeft, lowerRight, "Bottom"));

		// Keeping counter for number of vertices clipped.
		int numClipped = 0, counter = 1;
		// clipping polygon against first clipping edge to make sure that,
		// part of polygon is inside of clipping window.
		ClipperEdge Edge = clippingEdges.get(0);
		ClippedPolygon CP = clipPolygon_helper(inx, iny, outx, outy, in, numClipped, Edge);

		// continue clipping the polygon against all edges.
		while (counter < clippingEdges.size() && CP.numClipped > 0) {
			ClipperEdge currentEdge = clippingEdges.get(counter);

			// Clipped polygon against previous clipping edge,
			// goes input to next clipping edge.
			CP = clipPolygon_helper(CP.outx.clone(), CP.outy.clone(), CP.outx, CP.outy, CP.numClipped, 0, currentEdge);
			counter++;
		}

		// returning no. of vertices in clipped polygon.
		return CP.numClipped; 
	}

	/**
	 * 
	 * This is helper function used for clipping the polygon against
	 * clipping edge using Sutherland-Hodgeman polygon clipping algo.
	 * 
	 * @param inx	x coords of vertices of polygon to be clipped.
	 * @param iny	y coords of vertices of polygon to be clipped.
	 * @param outx	x coords of vertices of polygon resulting after clipping.
	 * @param outy	y coords of vertices of polygon resulting after clipping.
	 * @param in	the number of vertices in the polygon to be clipped
	 * @param numClipped 	Number of vertices clipped.
	 * @param currentEdge	The current clipping edge.
	 * @return ClippedPolygon	Clipped polygon object.
	 */
	public ClippedPolygon clipPolygon_helper(float inx[], float iny[], float outx[], float outy[], int in,
			int numClipped, ClipperEdge currentEdge) {

		// Checking each edge of polygon and comparing its end-points
		// with clipping edge to check if it is inside or outside.
		EndPoint endpoint1 = new EndPoint(inx[in - 1], iny[in - 1]);
		for (int counter = 0; counter < in; counter++) {
			EndPoint endpoint2 = new EndPoint(inx[counter], iny[counter]);
			// If both end points are inside, add second end point to the 
			// clipped polygon list.
			if (currentEdge.checkInside(endpoint1) && currentEdge.checkInside(endpoint2)) {
				outx[numClipped] = endpoint2.x;
				outy[numClipped] = endpoint2.y;
				numClipped++;
			} 
			// If the first end point is inside but second end point is outside,
			// then we are adding point of intersection into clipped polygon list.
			else if (currentEdge.checkInside(endpoint1) && !currentEdge.checkInside(endpoint2)) {
				EndPoint intersection = currentEdge.getIntersectionPoint(endpoint1, endpoint2);
				outx[numClipped] = intersection.x;
				outy[numClipped] = intersection.y;
				numClipped++;
			} 
			// If the first end point is outside and second one is inside, then we are
			// adding point of intersection and second end point to clipped polygon
			// list.
			else if (!currentEdge.checkInside(endpoint1) && currentEdge.checkInside(endpoint2)) {
				EndPoint intersection = currentEdge.getIntersectionPoint(endpoint1, endpoint2);
				outx[numClipped] = intersection.x;
				outy[numClipped] = intersection.y;
				outx[numClipped + 1] = endpoint2.x;
				outy[numClipped + 1] = endpoint2.y;
				numClipped += 2;
			} 
			// When both end point are outside, we will not add anything to the clipped polygon
			// list.
			else {
				// Pass.
			}
			endpoint1 = endpoint2;
		}
		
		// returning clipped polygon.
		return new ClippedPolygon(outx, outy, numClipped);
	}

	/**
	 * 
	 * This class holds clipped polygon coordinates and count for number
	 * of vertices clipped.
	 * 
	 * @author Kapil Dole
	 *
	 */
	public class ClippedPolygon {
		float outx[], outy[];
		int numClipped;

		// Constructor for initialization.
		public ClippedPolygon(float outx[], float outy[], int numClipped) {
			this.outx = outx;
			this.outy = outy;
			this.numClipped = numClipped;
		}
	}
}
