import java.util.ArrayList;

//
//  Clipper.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Kapil Dole
//

///
// Object for performing clipping
//
///

public class clipper {

	public class EndPoint {
		float x, y;

		public EndPoint(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	public class ClipperEdge {

		EndPoint e1, e2;
		String orientation;

		public ClipperEdge(EndPoint e1, EndPoint e2, String orientation) {
			this.e1 = e1;
			this.e2 = e2;
			this.orientation = orientation;
		}

		public boolean checkInside(EndPoint eCheck) {
			if (orientation.equals("Left")) {
				return eCheck.x > e1.x;
			} else if (orientation.equals("Right")) {
				return eCheck.x < e1.x;
			} else if (orientation.equals("Top")) {
				return eCheck.y < e1.y;
			} else {
				return eCheck.y > e1.y;
			}
		}

		// point of intersection can be calculated be below formula
		// Px = ((x1*y2 - y1*x2)(x3 - x4) - (x1 - x2)(x3*y4 - y3*x4)) / ((x1 -
		// x2)(y3 - y4) - (y1 - y2)(x3 - x4))
		// Py = ((x1*y2 - y1*x2)(y3 - y4) - (y1 - y2)(x3*y4 - y3*x4)) / ((x1 -
		// x2)(y3 - y4) - (y1 - y2)(x3 - x4))
		public EndPoint getIntersectionPoint(EndPoint e3, EndPoint e4) {
			float denominator = (e1.x - e2.x) * (e3.y - e4.y) - (e1.y - e2.y) * (e3.x - e4.x);

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

	///
	// clipPolygon
	//
	// Clip the polygon with vertex count in and vertices inx/iny
	// against the rectangular clipping region specified by lower-left
	/// corner
	// (llx,lly) and upper-right corner (urx,ury). The resulting vertices
	/// are
	// placed in outx/outy.
	//
	// The routine should return the the vertex count of the polygon
	// resulting from the clipping.
	//
	// @param in the number of vertices in the polygon to be clipped
	// @param inx - x coords of vertices of polygon to be clipped.
	// @param iny - y coords of vertices of polygon to be clipped.
	// @param outx - x coords of vertices of polygon resulting after
	/// clipping.
	// @param outy - y coords of vertices of polygon resulting after
	/// clipping.
	// @param llx - x coord of lower left of clipping rectangle.
	// @param lly - y coord of lower left of clipping rectangle.
	// @param urx - x coord of upper right of clipping rectangle.
	// @param ury - y coord of upper right of clipping rectangle.
	//
	// @return number of vertices in the polygon resulting after clipping
	//
	///
	public int clipPolygon(int in, float inx[], float iny[], float outx[], float outy[], float llx, float lly,
			float urx, float ury) {

		ArrayList<ClipperEdge> clippingEdges = new ArrayList<ClipperEdge>();
		EndPoint lowerLeft = new EndPoint(llx, lly);
		EndPoint upperLeft = new EndPoint(llx, ury);
		EndPoint lowerRight = new EndPoint(urx, lly);
		EndPoint upperRight = new EndPoint(urx, ury);

		clippingEdges.add(new ClipperEdge(lowerLeft, upperLeft, "Left"));
		clippingEdges.add(new ClipperEdge(upperLeft, upperRight, "Top"));
		clippingEdges.add(new ClipperEdge(lowerRight, upperRight, "Right"));
		clippingEdges.add(new ClipperEdge(lowerLeft, lowerRight, "Bottom"));

		int numClipped = 0, counter = 1;
		ClipperEdge Edge = clippingEdges.get(0);
		ClippedPolygon CP = clipPolygon_helper(inx, iny, outx, outy, in, numClipped, Edge);

		while (counter < clippingEdges.size() && CP.numClipped > 0) {
			ClipperEdge currentEdge = clippingEdges.get(counter);

			CP = clipPolygon_helper(CP.outx.clone(), CP.outy.clone(), CP.outx, CP.outy, CP.numClipped, 0, currentEdge);
			counter++;
		}

		for (int index = 0; index < clippingEdges.size(); index++) {
			
		}

		return CP.numClipped; // should return number of vertices in clipped
								// poly.
	}

	public ClippedPolygon clipPolygon_helper(float inx[], float iny[], float outx[], float outy[], int in,
			int numClipped, ClipperEdge currentEdge) {

		EndPoint endpoint1 = new EndPoint(inx[in - 1], iny[in - 1]);
		for (int counter = 0; counter < in; counter++) {

			EndPoint endpoint2 = new EndPoint(inx[counter], iny[counter]);
			if (currentEdge.checkInside(endpoint1) && currentEdge.checkInside(endpoint2)) {
				outx[numClipped] = endpoint2.x;
				outy[numClipped] = endpoint2.y;
				numClipped++;
			} else if (currentEdge.checkInside(endpoint1) && !currentEdge.checkInside(endpoint2)) {
				EndPoint intersection = currentEdge.getIntersectionPoint(endpoint1, endpoint2);
				outx[numClipped] = intersection.x;
				outy[numClipped] = intersection.y;
				numClipped++;
			} else if (!currentEdge.checkInside(endpoint1) && currentEdge.checkInside(endpoint2)) {
				EndPoint intersection = currentEdge.getIntersectionPoint(endpoint1, endpoint2);
				outx[numClipped] = intersection.x;
				outy[numClipped] = intersection.y;
				outx[numClipped + 1] = endpoint2.x;
				outy[numClipped + 1] = endpoint2.y;
				numClipped += 2;
			} else {
				// Pass.
			}
			endpoint1 = endpoint2;
		}

		return new ClippedPolygon(outx, outy, numClipped);
	}

	public class ClippedPolygon {
		float outx[], outy[];
		int numClipped;

		public ClippedPolygon(float outx[], float outy[], int numClipped) {
			this.outx = outx;
			this.outy = outy;
			this.numClipped = numClipped;
		}
	}
}
