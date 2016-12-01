
//
//  cgShape.java
//
//  20155
//
//  Class that includes routines for tessellating a number of basic shapes.
//
//  Students are to supply their implementations for the functions in
//  this file using the function "addTriangle()" to do the tessellation.
//
//  Contributor:  Kapil Dole
//

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;

/**
 * This class is basically designed for testing tesselation of various shapes
 * cube, cone, cylinder and sphere.
 * 
 * @author Kapil Dole
 */
public class cgShape extends simpleShape {

	// Constant factor which we will use while computations, as all
	// computations of polygons are between -0.5 and 0.5.
	public final float HALF = (float) 0.5;
	
	/**
	 * The makeCube method - Creates a unit cube, centered at the origin, with a
	 * given number of subdivisions in each direction on each face. Can only use
	 * calls to addTriangle().
	 * 
	 * @param subdivisions - number of equal subdivisions to be made in each 
	 * direction along each face.
	 * 
	 * @return None.
	 */
	public void makeCube(int subdivisions) {
		if (subdivisions < 1)
			subdivisions = 1;

		// we can calculate each division by dividing length of cube (unit) by
		// total number of subdivisions.
		float eachDivision = (float) 1.0 / subdivisions;

		// for each cube face we are considering following end points:
		// (x1, y1) - lower left end point.
		// (x1, y2) - upper left end point.
		// (x2, y2) - upper right end point.
		// (x2, y1) - lower right end point.
		int index1 = 1;
		while (index1 <= subdivisions) {
			// computing x coordinates of cube face.
			float x1 = (index1 - 1) * eachDivision - HALF;
			float x2 = index1 * eachDivision - HALF;

			int index2 = 1;
			while (index2 <= subdivisions) {
				// computing y coordinates of cube face for each x coordinates.
				float y1 = (index2 - 1) * eachDivision - HALF;
				float y2 = index2 * eachDivision - HALF;

				// now we need to display all 6 faces of cube after tesselation
				// that means each face can be displayed by two triangles.
				// Displaying face (x, y, -half).
				addTriangle(x2, y1, -HALF, x1, y1, -HALF, x1, y2, -HALF);
				addTriangle(x2, y1, -HALF, x1, y2, -HALF, x2, y2, -HALF);

				// Displaying face (x, y, half).
				addTriangle(x1, y1, HALF, x2, y1, HALF, x1, y2, HALF);
				addTriangle(x1, y2, HALF, x2, y1, HALF, x2, y2, HALF);

				// Displaying face (-half, y, z).
				addTriangle(-HALF, x1, y2, -HALF, x2, y1, -HALF, x1, y1);
				addTriangle(-HALF, x2, y2, -HALF, x2, y1, -HALF, x1, y2);

				// Displaying face (half, y, z).
				addTriangle(HALF, x2, y1, HALF, x1, y2, HALF, x1, y1);
				addTriangle(HALF, x2, y1, HALF, x2, y2, HALF, x1, y2);

				// Displaying face (x, -half, z).
				addTriangle(x2, -HALF, y1, x1, -HALF, y2, x1, -HALF, y1);
				addTriangle(x2, -HALF, y1, x2, -HALF, y2, x1, -HALF, y2);

				// Displaying face(x, half, z).
				addTriangle(x1, HALF, y2, x2, HALF, y1, x1, HALF, y1);
				addTriangle(x2, HALF, y2, x2, HALF, y1, x1, HALF, y2);

				index2++;
			}
			index1++;
		}
	}

	
	/**
	 * The method makeCylinder - Create polygons for a cylinder with unit height, centered 
	 * at the origin, with separate number of radial subdivisions and height 
	 * subdivisions. Can only use calls to addTriangle().
	 * 
	 * @param radius - Radius of the base of the cylinder
	 * @param radialDivisions - number of subdivisions on the radial base
	 * @param heightDivisions - number of subdivisions along the height
	 * 
	 * @return None.
	 */
	public void makeCylinder(float radius, int radialDivisions, int heightDivisions) {
		if (radialDivisions < 3)
			radialDivisions = 3;

		if (heightDivisions < 1)
			heightDivisions = 1;

		float x1, x2, y1, y2, z1, z2;
		int index1 = 1;
		// There are two parts in tesselating the cylinder, first tesselating
		// both the disc and second tessalating the curved surface where you 
		// have to draw rectangles for given height and then tesselate it.
		while (index1 <= radialDivisions) {
			// computing coordinates of the triangle on the disk based on the
			// radialDivision. The coordinates of the triangle will in below
			// format. E1(x1, +/-half, z1), C(0, +/-half, 0) and E2(x2, +/-half,
			// z2)
			x1 = (float) (radius * Math.cos(2 * (index1 - 1) * Math.PI / radialDivisions));
			x2 = (float) (radius * Math.cos(2 * index1 * Math.PI / radialDivisions));
			z1 = (float) (radius * Math.sin(2 * (index1 - 1) * Math.PI / radialDivisions));
			z2 = (float) (radius * Math.sin(2 * index1 * Math.PI / radialDivisions));

			// Displaying the disk (x, -half, z) using triangle.
			addTriangle(0, -HALF, 0, x1, -HALF, z1, x2, -HALF, z2);

			// Displaying the disk (x, half, z) using triangle.
			addTriangle(x2, HALF, z2, x1, HALF, z1, 0, HALF, 0);

			int index2 = 1;
			// Drawing rectangles for the given height, so that we can tesselate
			// each rectangle into two triangles.
			while (index2 <= heightDivisions) {
				// calculating the y coordinates based on the current height.
				y1 = (float) (index2 - 1) / heightDivisions - HALF;
				y2 = (float) index2 / heightDivisions - HALF;

				// Tesselating all the rectangles into two triangles.
				addTriangle(x1, y2, z1, x2, y2, z2, x1, y1, z1);
				addTriangle(x2, y2, z2, x2, y1, z2, x1, y1, z1);

				index2++;
			}
			index1++;
		}
	}
	

	/**
	 * This method makeCone - Create polygons for a cone with unit height, centered at the
	 * origin, with separate number of radial subdivisions and height subdivisions. 
	 * Can only use calls to addTriangle()
	 * 
	 * @param radius - Radius of the base of the cone
	 * @param radialDivisions - number of subdivisions on the radial base
	 * @param heightDivisions - number of subdivisions along the height
	 * 
	 * @return None.
	 */
	public void makeCone(float radius, int radialDivisions, int heightDivisions) {
		if (radialDivisions < 3)
			radialDivisions = 3;

		if (heightDivisions < 1)
			heightDivisions = 1;

		float x1, x2, y1, y_increment, z1, z2, x1_decrement, x2_decrement, z1_decrement, z2_decrement;
		int index1 = 1;
		// There are two parts in tesselating the cone, first tesselating the
		// bottom disc and second tessalating the conical surface where you
		// have to draw rectangles which will become trapezoidal shapes for
		// given height and then tesselate it.
		while (index1 <= radialDivisions) {
			// computing coordinates of the triangle on the bottom disk based on
			// the radialDivision. The coordinates of the triangle will in given
			// format. E1(x1, -half, z1), C(0, -half, 0) and E2(x2, -half, z2)
			x1 = (float) (radius * Math.cos(2 * (index1 - 1) * Math.PI / radialDivisions));
			x2 = (float) (radius * Math.cos(2 * index1 * Math.PI / radialDivisions));
			z1 = (float) (radius * Math.sin(2 * (index1 - 1) * Math.PI / radialDivisions));
			z2 = (float) (radius * Math.sin(2 * index1 * Math.PI / radialDivisions));
			
			// Displaying the bottom disk (x, -half, z) using triangle.
			addTriangle(x1, -HALF, z1, x2, -HALF, z2, 0, -HALF, 0);

			// Calculating incremental factor for y as we are moving from disc to
			// apex of cone and decrementing factor as the tesselating rectangle
			// slowly becomes trapzoidal.
			x1_decrement = x1 / heightDivisions;
			x2_decrement = x2 / heightDivisions;
			y_increment = 1 / (float) heightDivisions;
			z1_decrement = z1 / heightDivisions;
			z2_decrement = z2 / heightDivisions;

			int index2 = 1;
			y1 = -HALF;
			// We are drawing rectangular shapes for each height and it will
			// become trapazoidal as we move towards narrow end of the cone
			// and at last there will be single triangle at top.
			while (index2 <= heightDivisions - 1) {
				// Displaying each rectangle or trapezium using two triangles.
				addTriangle(x1, y1, z1, x1 - x1_decrement, y1 + y_increment, z1 - z1_decrement, x2, y1, z2);
				addTriangle(x1 - x1_decrement, y1 + y_increment, z1 - z1_decrement, x2 - x2_decrement, 
						y1 + y_increment, z2 - z2_decrement, x2, y1, z2);

				// updating values of all variable as we move upwards.
				x1 = x1 - x1_decrement;
				x2 = x2 - x2_decrement;
				y1 = y1 + y_increment;
				z1 = z1 - z1_decrement;
				z2 = z2 - z2_decrement;

				index2++;
			}
			// At the apex of the cone, we will draw a single triangle.
			addTriangle(x1, y1, z1, 0, HALF, 0, x2, y1, z2);

			index1++;
		}
	}

	
	/**
	 * This method makeSphere - Create sphere of a given radius, centered at the origin,
	 * using spherical coordinates with separate number of thetha and phi subdivisions.	
	 * Can only use calls to addTriangle.
	 * 
	 * @param radius - Radius of the sphere
	 * @param slices - number of subdivisions in the theta direction
	 * @param stacks - Number of subdivisions in the phi direction.
	 * 
	 * @return None.
	 */
	public void makeSphere(float radius, int slices, int stacks) {
		// IF USING RECURSIVE SUBDIVISION, MODIFY THIS TO USE
		// A MINIMUM OF 1 AND A MAXIMUM OF 5 FOR 'slices'

		if (slices < 3)
			slices = 3;

		if (stacks < 3)
			stacks = 3;

		// Here, we are using recursive subdivision approach for tesselation
		// of sphere. We are starting with a regular platonic solid
		// (icosahedron) and we are declaring 19 triangles of it, as per the
		// discussion in the class.

		// Triangle T0 = <0, a, -1, -a, 1, 0, a, 1, 0>
		makeSphere_helper(0, radius, -1, -radius, 1, 0, radius, 1, 0, slices, radius);

		// Triangle T1 = <0, a, 1, a, 1, 0, -a, 1, 0>
		makeSphere_helper(0, radius, 1, radius, 1, 0, -radius, 1, 0, slices, radius);

		// Triangle T2 = <0, a, 1, -1, 0, a, 0, -a, 1>
		makeSphere_helper(0, radius, 1, -1, 0, radius, 0, -radius, 1, slices, radius);

		// Triangle T3 = <0, a, 1, 0, -a, 1, 1, 0, a>
		makeSphere_helper(0, radius, 1, 0, -radius, 1, 1, 0, radius, slices, radius);

		// Triangle T4 = <0, a, -1, 1, 0, -a, 0, -a, -1>
		makeSphere_helper(0, radius, -1, 1, 0, -radius, 0, -radius, -1, slices, radius);

		// Triangle T5 = <0, a, -1, 0, -a, -1, -1, 0, -a>
		makeSphere_helper(0, radius, -1, 0, -radius, -1, -1, 0, -radius, slices, radius);

		// Triangle T6 = <0, -a, 1, -a, -1, 0, a, -1, 0>
		makeSphere_helper(0, -radius, 1, -radius, -1, 0, radius, -1, 0, slices, radius);

		// Triangle T7 = <0, -a, -1, a, -1, 0, -a, -1, 0>
		makeSphere_helper(0, -radius, -1, radius, -1, 0, -radius, -1, 0, slices, radius);

		// Triangle T8 = <-a, 1, 0, -1, 0, -a, -1, 0, a>
		makeSphere_helper(-radius, 1, 0, -1, 0, -radius, -1, 0, radius, slices, radius);

		// Triangle T9 = <-a, -1, 0, -1, 0, a, -1, 0, -a>
		makeSphere_helper(-radius, -1, 0, -1, 0, radius, -1, 0, -radius, slices, radius);

		// Triangle T10 = <a, 1, 0, 1, 0, a, 1, 0, -a>
		makeSphere_helper(radius, 1, 0, 1, 0, radius, 1, 0, -radius, slices, radius);

		// Triangle T11 = <a, -1, 0, 1, 0, -a, 1, 0, a>
		makeSphere_helper(radius, -1, 0, 1, 0, -radius, 1, 0, radius, slices, radius);

		// Triangle T12 = <0, a, 1, -a, 1, 0, -1, 0, a>
		makeSphere_helper(0, radius, 1, -radius, 1, 0, -1, 0, radius, slices, radius);

		// Triangle T13 = <0, a, 1, 1, 0, a, a, 1, 0>
		makeSphere_helper(0, radius, 1, 1, 0, radius, radius, 1, 0, slices, radius);

		// Triangle T14 = <0, a, -1, -1, 0, -a, -a, 1, 0>
		makeSphere_helper(0, radius, -1, -1, 0, -radius, -radius, 1, 0, slices, radius);

		// Triangle T15 = <0, a, -1, a, 1, 0, 1, 0, -a>
		makeSphere_helper(0, radius, -1, radius, 1, 0, 1, 0, -radius, slices, radius);

		// Triangle T16 = <0, -a, -1, -a, -1, 0, -1, 0, -a>
		makeSphere_helper(0, -radius, -1, -radius, -1, 0, -1, 0, -radius, slices, radius);

		// Triangle T17 = <0, -a, -1, 1, 0, -a, a, -1, 0>
		makeSphere_helper(0, -radius, -1, 1, 0, -radius, radius, -1, 0, slices, radius);

		// Triangle T18 = <0, -a, 1, -1, 0, a, -a, -1, 0>
		makeSphere_helper(0, -radius, 1, -1, 0, radius, -radius, -1, 0, slices, radius);

		// Triangle T19 = <0, -a, 1, a, -1, 0, 1, 0, a>
		makeSphere_helper(0, -radius, 1, radius, -1, 0, 1, 0, radius, slices, radius);
	}

	
	/**
	 * This is recursive method used for tesselating the sphere using recursive subdivision
	 * method.
	 * 
	 * @param x1 - x coordinate of first point.
	 * @param y1 - y coordinate of first point.
	 * @param z1 - z coordinate of first point.
	 * @param x2 - x coordinate of second point.
	 * @param y2 - y coordinate of second point.
	 * @param z2 - z coordinate of second point.
	 * @param x3 - x coordinate of third point.
	 * @param y3 - y coordinate of third point.
	 * @param z3 - z coordinate of third point.
	 * @param subdivisions - total number of subdivisions.
	 * @param radius - radius of sphere.
	 * 
	 * @return None.
	 */
	void makeSphere_helper(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3,
			int subdivisions, float radius) {

		if (subdivisions > 1) {
			// recursively calling the helper method for lesser subdivisions and
			// tesselating it more by passing midpoints of the given edges, we 
			// will keep recursively calling the method till the subdivisions
			// is equal to 1 which is base case of our method. Here we are
			// calling recursive helper method 4 times
			// each call is for 1 sub triangle.

			makeSphere_helper(x1, y1, z1, (x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2, (x1 + x3) / 2, (y1 + y3) / 2,
					(z1 + z3) / 2, (subdivisions - 1), radius);

			makeSphere_helper((x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2, (x2 + x3) / 2, (y2 + y3) / 2, (z2 + z3) / 2,
					(x1 + x3) / 2, (y1 + y3) / 2, (z1 + z3) / 2, (subdivisions - 1), radius);

			makeSphere_helper((x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2, x2, y2, z2, (x2 + x3) / 2, (y2 + y3) / 2,
					(z2 + z3) / 2, (subdivisions - 1), radius);

			makeSphere_helper((x1 + x3) / 2, (y1 + y3) / 2, (z1 + z3) / 2, (x2 + x3) / 2, (y2 + y3) / 2, (z2 + z3) / 2,
					x3, y3, z3, (subdivisions - 1), radius);
		} else {
			// When subdivisions = 1, we have to normalize all of the points,
			// before displaying the triangle.

			// Normalizing point 1.
			float normalize_pt1 = (float) Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
			x1 = (x1 / normalize_pt1) * radius;
			y1 = (y1 / normalize_pt1) * radius;
			z1 = (z1 / normalize_pt1) * radius;

			// Normalizing point 2.
			float normalize_pt2 = (float) Math.sqrt(x2 * x2 + y2 * y2 + z2 * z2);
			x2 = (x2 / normalize_pt2) * radius;
			y2 = (y2 / normalize_pt2) * radius;
			z2 = (z2 / normalize_pt2) * radius;

			// Normalizing point 3.
			float normalize_pt3 = (float) Math.sqrt(x3 * x3 + y3 * y3 + z3 * z3);
			x3 = (x3 / normalize_pt3) * radius;
			y3 = (y3 / normalize_pt3) * radius;
			z3 = (z3 / normalize_pt3) * radius;

			// Displaying the triangle after normalization.
			addTriangle(x1, y1, z1, x2, y2, z2, x3, y3, z3);
		}
	}
}
