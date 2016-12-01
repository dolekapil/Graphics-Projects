/* 
 * Terrin.java 
 * 
 * Version: 1 Terrin.java, v 1.1 2016/21/05 14:50:40 
 *   
 * Revisions: 
 *     Revision 1.1 Kapil 2016/21/05 14:50:40 
 */

/**
 * This class stores vertices values for the terrin which we got from the blender.
 *
 * @author Kapil Dole
 */
public class Terrin {
	float[] terrinVertices = { -4.448622f, 1.406701f, 3.581350f, -4.448622f, 1.387965f, 3.581350f, -4.448622f,
			1.406701f, -4.783305f, -4.448622f, 1.387965f, -4.783305f, 7.412965f, 1.406701f, 3.581350f, 7.412965f,
			1.387965f, 3.581350f, 7.412965f, 1.406701f, -4.783305f, 7.412965f, 1.387965f, -4.783305f };

	/**
	 * Each group of three values specifies a teapot vertex normal
	 */
	float[] terrinNormals = { 0.000000f, 1.000000f, 0.000000f, 0.000000f, -1.000000f, 0.000000f, 0.000000f, 0.000000f,
			-1.000000f, -1.000000f, 0.000000f, 0.000000f, 0.000000f, 0.000000f, 1.000000f, 1.000000f, 0.000000f,
			0.000000f };

	int[] terrinElements = { 0, 6, 2, 5, 3, 7, 6, 3, 2, 2, 1, 0, 0, 5, 4, 4, 7, 6, 0, 4, 6, 5, 1, 3, 6, 7, 3, 2, 3, 1,
			0, 1, 5, 4, 5, 7 };

	int[] terrinNormalIndices = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3,
			3, 4, 4, 4, 5, 5, 5 };

	public Terrin() {

	}
}
