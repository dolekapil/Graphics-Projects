//
//  Rasterizer.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Kapil Dole.
//

///
// 
// This is a class that performas rasterization algorithms
//
///

import java.util.*;

public class Rasterizer {

	///
	// number of scan lines.
	///

	int n_scanlines;

	///
	// Constructor
	//
	// @param n - number of scan lines.
	//
	///

	Rasterizer(int n) {
		n_scanlines = n;
	}

	/**
	 * 
	 * This is our node class, which will hold edge related information like
	 * y maximum value, x value which is associated with y minimum and the 
	 * inverse slope.
	 * 
	 * @author Kapil Dole.
	 *
	 */
	class Node {
		int yMaximum;
		double xVal;
		double slope;
		Node link;

		public Node(int yMaximum, double xVal, double slope) {
			this.yMaximum = yMaximum;
			this.xVal = xVal;
			this.slope = slope;
			this.link = null;
		}
	}

	/**
	 * 
	 * This method builds edge table for our algorithm.
	 * 
	 * @param n - total number of points.
	 * @param x - list of x coordinates of points.
	 * @param y - list of y coordinates of points.
	 * @param yMininimum - minimum value among y coordinates.
	 * @param yMaximum - maximum value among y coordinates.
	 * @return Edge table.
	 */
	public Node[] buildEdgeTable(int n, int x[], int y[], int yMininimum, int yMaximum) {

		Node[] edgeTable = new Node[yMaximum - yMininimum + 1];

		for (int counter = 0; counter < n; counter++) {
			// calculating each pair of points.
			int x1 = x[counter], x2 = x[(counter + 1) % n];
			int y1 = y[counter], y2 = y[(counter + 1) % n];

			// We are ignoring horizontal lines, i.e. lines with slope=0.
			if (y2 - y1 != 0) {
				int yMax, yMin, xVal;
				double slope;
				// Computing Y max, Y min and x min values for each pair.
				yMax = Math.max(y1, y2);
				yMin = Math.min(y1, y2);
				if (y1 == yMin) {
					xVal = x1;
				} else {
					xVal = x2;
				}

				// Computing inverse slope for each pair.
				slope = (double) (x2 - x1) / (double) (y2 - y1);

				int bucketIndex = yMin - yMininimum;

				// If the given bucket is empty, then add new node in it, otherwise
				// Prepend the node into bucket.
				if (edgeTable[bucketIndex] == null) {
					edgeTable[bucketIndex] = new Node(yMax, xVal, slope);
				} else {
					Node current = edgeTable[bucketIndex];

					edgeTable[bucketIndex] = insertInBucket(current, new Node(yMax, xVal, slope));
				}
			}
		}
		return edgeTable;
	}

	/**
	 * 
	 * Draw a filled polygon in the simpleCanvas C.
	 * 
	 * The polygon has n distinct vertices. The coordinates of the vertices making 
	 * up the polygon are stored in the x and y arrays. The ith vertex will have 
	 * coordinate (x[i], y[i]) 
	 * 
	 * You are to add the implementation here using only calls
	 * to C.setPixel()
	 */

	public void drawPolygon(int n, int x[], int y[], simpleCanvas C) {

		// Computing minimum and maximum values among the y coordinate.
		int yMinimum = y[0], yMaximum = y[0];
		for (int counter = 1; counter < n; counter++) {
			if (y[counter] < yMinimum) {
				yMinimum = y[counter];
			}
			if (y[counter] > yMaximum) {
				yMaximum = y[counter];
			}
		}

		Node[] edgeTable = buildEdgeTable(n, x, y, yMinimum, yMaximum);
		List<Node> activeList = new ArrayList<Node>();

		// Removing those entries from the active list which reached their
		// Y maximum value, i.e. scan line number = Y maximum.
		for (int index = yMinimum; index <= yMaximum; index++) {
			for (int i = 0; i < activeList.size(); i++) {
				Node current = activeList.get(i);
				if (current.yMaximum == index) {
					activeList.remove(i);
					--i;
				}
			}

			// If the edge table is not empty at current index, then add
			// the entries into active list and we are sorting the data
			// by x value and if x values are same then by inverse slope.
			if (edgeTable[index - yMinimum] != null) {
				Node currentEdgeNode = edgeTable[index - yMinimum];
				int j;
				// Repeat till all the entries in the bucket are added to
				// the active list.
				while (currentEdgeNode != null) {
					for (j = 0; j < activeList.size(); j++) {
						Node currentActiveNode = activeList.get(j);
						
						// If current active list node's X value( or slope) is greater
						// than that of edge list value then add edge list value before
						// it, otherwise add it later to active list value.
						if (currentActiveNode.xVal > currentEdgeNode.xVal
								|| (currentActiveNode.xVal == currentEdgeNode.xVal
										&& currentActiveNode.slope > currentEdgeNode.slope)) {
							activeList.add(j, currentEdgeNode);
							break;
						} else if ((currentActiveNode.xVal == currentEdgeNode.xVal
								&& currentActiveNode.slope <= currentEdgeNode.slope)) {
							activeList.add(j + 1, currentEdgeNode);
							break;
						}
					}

					// If edge list value is greatest then add it to the last of active list.
					if (j == activeList.size()) {
						activeList.add(currentEdgeNode);
					}

					currentEdgeNode = currentEdgeNode.link;
				}
			}

			// Plot the pixels for each pair of nodes in active list.
			int counter = 0;
			while (counter < activeList.size()) {
				// Round up the left end point.
				double left = Math.ceil(activeList.get(counter).xVal);
				// Round down the right end point.
				double right = Math.floor(activeList.get(counter + 1).xVal);

				// Plotting the pixels for give scan line.
				for (int k = (int) left; k <= right; k++) {
					C.setPixel(k, index);
				}
				counter = counter + 2;
			}

			// Compute new X value by adding inverse slope to it, and
			// update it in the given node.
			int incrementCounter = 0;
			while (incrementCounter < activeList.size()) {
				Node roundUpNode = activeList.get(incrementCounter);
				Node roundDownNode = activeList.get(incrementCounter + 1);
				roundUpNode.xVal = roundUpNode.xVal + roundUpNode.slope;
				roundDownNode.xVal = roundDownNode.xVal + roundDownNode.slope;
				incrementCounter += 2;
			}
		}
	}

	/**
	 * 
	 * This function inserts the value in the given bucket when bucket is not
	 * empty and in sorted manner by their X or slope values.
	 * 
	 * @param head - Current node in the bucket.
	 * @param insertNode - Node to insert into bucket.
	 * @return - New Head for the bucket.
	 */
	public Node insertInBucket(Node head, Node insertNode) {

		// If current head of bucket has less x value (or slope), then add new node next to it,
		// Otherwise and before it and return new head.
		if (head.xVal < insertNode.xVal || (head.xVal == insertNode.xVal && head.slope > insertNode.slope)) {
			Node current = head.link;
			Node previous = head;
			while(current != null){
				if (current.xVal < insertNode.xVal || (current.xVal == insertNode.xVal && current.slope > insertNode.slope)) {
					previous = current;
					current = current.link;
				}
				else{
					break;
				}
			}
			previous.link = insertNode;
			insertNode.link = current;
		} else {
			insertNode.link = head;
			head = insertNode;
		}
		return head;
	}

}
