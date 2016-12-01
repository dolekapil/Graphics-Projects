//
//  shapes.java
//
//  Routines for shape-drawing functions.
//
//  @author Vasudev
//  Contributor: Kapil Dole
//

public class shapes extends simpleShape {

	/**
	 * Object selection variables
	 */
	public static final int OBJ_SUN = 0;
	public static final int OBJ_HOUSE = 1;
	public static final int OBJ_TREE = 2;
	public static final int OBJ_TRUNK = 3;
	public static final int OBJ_TERRIN = 4;

	/**
	 * Constructor
	 */
	public shapes() {
	}

	/**
	 * makeHouse() - create a house object
	 */
	private void makeHouse() {
		float[] houseVertices, houseNormals;
		int[] houseElements, houseNormalIndices;

		House h = new House();
		houseVertices = h.houseVertices;
		houseNormals = h.houseNormals;
		houseElements = h.houseElements;
		houseNormalIndices = h.houseNormalIndices;

		for (int i = 0; i < houseElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * houseElements[i]; // slots 0, 1, 2
			int point2 = 3 * houseElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * houseElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * houseNormalIndices[i];
			int normal2 = 3 * houseNormalIndices[i + 1];
			int normal3 = 3 * houseNormalIndices[i + 2];

			addTriangleWithNorms(houseVertices[point1 + 0], houseVertices[point1 + 1], houseVertices[point1 + 2],
					houseNormals[normal1 + 0], houseNormals[normal1 + 1], houseNormals[normal1 + 2],

			houseVertices[point2 + 0], houseVertices[point2 + 1], houseVertices[point2 + 2], houseNormals[normal2 + 0],
					houseNormals[normal2 + 1], houseNormals[normal2 + 2],

			houseVertices[point3 + 0], houseVertices[point3 + 1], houseVertices[point3 + 2], houseNormals[normal3 + 0],
					houseNormals[normal3 + 1], houseNormals[normal3 + 2]);
		}
	}

	/**
	 * makeTree() - create a tree object
	 */
	private void makeTree() {
		float[] treeVertices, treeNormals;
		int[] treeElements, treeNormalIndices;

		Tree t = new Tree();
		treeVertices = t.treeVertices;
		treeNormals = t.treeNormals;
		treeElements = t.treeElements;
		treeNormalIndices = t.treeNormalIndices;

		for (int i = 0; i < treeElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * treeElements[i]; // slots 0, 1, 2
			int point2 = 3 * treeElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * treeElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * treeNormalIndices[i];
			int normal2 = 3 * treeNormalIndices[i + 1];
			int normal3 = 3 * treeNormalIndices[i + 2];

			addTriangleWithNorms(treeVertices[point1 + 0], treeVertices[point1 + 1], treeVertices[point1 + 2],
					treeNormals[normal1 + 0], treeNormals[normal1 + 1], treeNormals[normal1 + 2],

			treeVertices[point2 + 0], treeVertices[point2 + 1], treeVertices[point2 + 2], treeNormals[normal2 + 0],
					treeNormals[normal2 + 1], treeNormals[normal2 + 2],

			treeVertices[point3 + 0], treeVertices[point3 + 1], treeVertices[point3 + 2], treeNormals[normal3 + 0],
					treeNormals[normal3 + 1], treeNormals[normal3 + 2]);
		}
	}

	/**
	 * makeTrunk() - create a trunk object
	 */
	private void makeTrunk() {
		float[] trunkVertices, trunkNormals;
		int[] trunkElements, trunkNormalIndices;

		Trunk t = new Trunk();
		trunkVertices = t.trunkVertices;
		trunkNormals = t.trunkNormals;
		trunkElements = t.trunkElements;
		trunkNormalIndices = t.trunkNormalIndices;

		for (int i = 0; i < trunkElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * trunkElements[i]; // slots 0, 1, 2
			int point2 = 3 * trunkElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * trunkElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * trunkNormalIndices[i];
			int normal2 = 3 * trunkNormalIndices[i + 1];
			int normal3 = 3 * trunkNormalIndices[i + 2];

			addTriangleWithNorms(trunkVertices[point1 + 0], trunkVertices[point1 + 1], trunkVertices[point1 + 2],
					trunkNormals[normal1 + 0], trunkNormals[normal1 + 1], trunkNormals[normal1 + 2],

			trunkVertices[point2 + 0], trunkVertices[point2 + 1], trunkVertices[point2 + 2], trunkNormals[normal2 + 0],
					trunkNormals[normal2 + 1], trunkNormals[normal2 + 2],

			trunkVertices[point3 + 0], trunkVertices[point3 + 1], trunkVertices[point3 + 2], trunkNormals[normal3 + 0],
					trunkNormals[normal3 + 1], trunkNormals[normal3 + 2]);
		}
	}

	/**
	 * makeTerrin() - create a terrin object
	 */
	private void makeTerrin() {
		float[] terrinVertices, terrinNormals;
		int[] terrinElements, terrinNormalIndices;

		Terrin t = new Terrin();
		terrinVertices = t.terrinVertices;
		terrinNormals = t.terrinNormals;
		terrinElements = t.terrinElements;
		terrinNormalIndices = t.terrinNormalIndices;

		for (int i = 0; i < terrinElements.length - 2; i += 3) {

			// calculate the base indices of the three vertices
			int point1 = 3 * terrinElements[i]; // slots 0, 1, 2
			int point2 = 3 * terrinElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * terrinElements[i + 2]; // slots 6, 7, 8

			int normal1 = 3 * terrinNormalIndices[i];
			int normal2 = 3 * terrinNormalIndices[i + 1];
			int normal3 = 3 * terrinNormalIndices[i + 2];

			addTriangleWithNorms(terrinVertices[point1 + 0], terrinVertices[point1 + 1], terrinVertices[point1 + 2],
					terrinNormals[normal1 + 0], terrinNormals[normal1 + 1], terrinNormals[normal1 + 2],

			terrinVertices[point2 + 0], terrinVertices[point2 + 1], terrinVertices[point2 + 2],
					terrinNormals[normal2 + 0], terrinNormals[normal2 + 1], terrinNormals[normal2 + 2],

			terrinVertices[point3 + 0], terrinVertices[point3 + 1], terrinVertices[point3 + 2],
					terrinNormals[normal3 + 0], terrinNormals[normal3 + 1], terrinNormals[normal3 + 2]);
		}
	}

	//
	// The quad is parallel to the XY plane with the front face
	// pointing down the +Z axis
	//

	/**
	 * Each group of three values specifies a quad vertex
	 */
	float[] sunVertices = {
			// top row
			-0.50f, 0.50f, 0.50f, -0.25f, 0.50f, 0.50f, 0.00f, 0.50f, 0.50f, 0.25f, 0.50f, 0.50f, 0.50f, 0.50f, 0.50f,
			// second row
			-0.50f, 0.25f, 0.50f, -0.25f, 0.25f, 0.50f, 0.00f, 0.25f, 0.50f, 0.25f, 0.25f, 0.50f, 0.50f, 0.25f, 0.50f,
			// third (middle) row
			-0.50f, 0.00f, 0.50f, -0.25f, 0.00f, 0.50f, 0.00f, 0.00f, 0.50f, 0.25f, 0.00f, 0.50f, 0.50f, 0.00f, 0.50f,
			// fourth row
			-0.50f, -0.25f, 0.50f, -0.25f, -0.25f, 0.50f, 0.00f, -0.25f, 0.50f, 0.25f, -0.25f, 0.50f, 0.50f, -0.25f,
			0.50f,
			// fifth (last) row
			-0.50f, -0.50f, 0.50f, -0.25f, -0.50f, 0.50f, 0.00f, -0.50f, 0.50f, 0.25f, -0.50f, 0.50f, 0.50f, -0.50f,
			0.50f };

	/**
	 * Each pair of values specifies a vertex's texture coordinates
	 */

	float[] sunUV = {
			// top row
			0.00f, 1.00f, 0.25f, 1.00f, 0.50f, 1.00f, 0.75f, 1.00f, 1.00f, 1.00f,
			// second row
			0.00f, 0.75f, 0.25f, 0.75f, 0.50f, 0.75f, 0.75f, 0.75f, 1.00f, 0.75f,
			// third (middle) row
			0.00f, 0.50f, 0.25f, 0.50f, 0.50f, 0.50f, 0.75f, 0.50f, 1.00f, 0.50f,
			// fourth row
			0.00f, 0.25f, 0.25f, 0.25f, 0.50f, 0.25f, 0.75f, 0.25f, 1.00f, 0.25f,
			// fifth (last) row
			0.00f, 0.00f, 0.25f, 0.00f, 0.50f, 0.00f, 0.75f, 0.00f, 1.00f, 0.00f };

	/**
	 * Because the quad faces +Z, all the normals are (0,0,1)
	 */
	float[] sunNormals = { 0.0f, 0.0f, 1.0f };

	/**
	 * Each group of three entries specifies a triangle for the quad
	 */
	int[] sunElements = {
			// top row
			0, 5, 1, 5, 6, 1, 1, 6, 2, 6, 7, 2, 2, 7, 3, 7, 8, 3, 3, 8, 4, 8, 9, 4,
			// second row
			5, 10, 6, 10, 11, 6, 6, 11, 7, 11, 12, 7, 7, 12, 8, 12, 13, 8, 8, 13, 9, 13, 14, 9,
			// third row
			10, 15, 11, 15, 16, 11, 11, 16, 12, 16, 17, 12, 12, 17, 13, 17, 18, 13, 13, 18, 14, 18, 19, 14,
			// fourth row
			15, 20, 16, 20, 21, 16, 16, 21, 17, 21, 22, 17, 17, 22, 18, 22, 23, 18, 18, 23, 19, 23, 24, 19 };

	/**
	 * makeSun() - create a quad object
	 */
	private void makeSun() {
		int i;

		for (i = 0; i < sunElements.length - 2; i += 3) {

			// Calculate the base indices of the three vertices
			int point1 = 3 * sunElements[i]; // slots 0, 1, 2
			int point2 = 3 * sunElements[i + 1]; // slots 3, 4, 5
			int point3 = 3 * sunElements[i + 2]; // slots 6, 7, 8

			// Calculate the base indices of the three texture coordinates
			int normal1 = 2 * sunElements[i]; // slots 0, 1, 2
			int normal2 = 2 * sunElements[i + 1]; // slots 3, 4, 5
			int normal3 = 2 * sunElements[i + 2]; // slots 6, 7, 8

			// Add triangle and texture coordinates
			addTriangleWithUV(sunVertices[point1 + 0], sunVertices[point1 + 1], sunVertices[point1 + 2],
					sunUV[normal1 + 0], sunUV[normal1 + 1], sunVertices[point2 + 0], sunVertices[point2 + 1],
					sunVertices[point2 + 2], sunUV[normal2 + 0], sunUV[normal2 + 1], sunVertices[point3 + 0],
					sunVertices[point3 + 1], sunVertices[point3 + 2], sunUV[normal3 + 0], sunUV[normal3 + 1]);

		}

	}

	/**
	 * Make either a quad or teapot
	 * 
	 * @param choice - 0 for quad, 1 for house
	 * 				   2 for tree, 3 for trunk
	 * 				   4 for terrin.
	 */
	public void makeShape(int choice) {
		if (choice == shapes.OBJ_SUN)
			makeSun();
		else if (choice == shapes.OBJ_HOUSE) {
			makeHouse();
		} else if (choice == shapes.OBJ_TREE) {
			makeTree();
		} else if (choice == shapes.OBJ_TRUNK) {
			makeTrunk();
		} else if (choice == shapes.OBJ_TERRIN) {
			makeTerrin();
		}

	}
}
