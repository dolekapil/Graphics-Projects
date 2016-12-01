
//
// viewParams.java
//
// Created by Joe Geigel on 1/23/13.
//
// Contributor:  Kapil Dole
//
// 20155
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class viewParams {

	///
	// constructor
	///
	public viewParams() {

	}

	/**
	 * This function sets up the view and projection parameter for a frustum
	 * projection of the scene. See the assignment description for the values
	 * for the projection parameters.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * 
	 * @return None.
	 */
	public void setUpFrustum(int program, GL3 gl3) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform1f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 1f represents only one value is being passed.

		// Passing float value 1.0 as a projection type flag, for frustum
		// projection.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "projectionType"), 1.0f);

		// Passing -1.0 as left projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "leftParam"), -1.0f);

		// Passing 1.0 as right projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "rightParam"), 1.0f);

		// Passing 1.0 as top projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "topParam"), 1.0f);

		// Passing -1.0 as bottom projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "bottomParam"), -1.0f);

		// Passing 0.9 as near projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "nearParam"), 0.9f);

		// Passing 4.5 as far projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "farParam"), 4.5f);
	}

	/**
	 * This function sets up the view and projection parameter for an
	 * orthographic projection of the scene. See the assignment description for
	 * the values for the projection parameters.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * 
	 * @return None.
	 */
	public void setUpOrtho(int program, GL3 gl3) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform1f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 1f represents only one value is being passed.

		// Passing float value 2.0 as a projection type flag, for
		// orthographic projection.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "projectionType"), 2.0f);

		// Passing -1.0 as left projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "leftParam"), -1.0f);

		// Passing 1.0 as right projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "rightParam"), 1.0f);

		// Passing 1.0 as top projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "topParam"), 1.0f);

		// Passing -1.0 as bottom projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "bottomParam"), -1.0f);

		// Passing 0.9 as near projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "nearParam"), 0.9f);

		// Passing 4.5 as far projection parameter.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "farParam"), 4.5f);
	}

	/**
	 * This function clears any transformations, setting the values to the
	 * defaults: no scaling (scale factor of 1), no rotation (degree of rotation
	 * = 0), and no translation (0 translation in each direction).
	 * 
	 * You will need to write this function, and maintain all of the values
	 * which must be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * 
	 * @return None.
	 */
	public void clearTransforms(int program, GL3 gl3) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform3f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 3f represents vector of 3 values is being passed to uniform
		// variable.

		// Setting the scaling parameters to identity, in order to clear out the
		// current scaling factor x, y and z values.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "scalingParam"), 1.0f, 1.0f, 1.0f);

		// Setting the rotation parameters to zero, in order to clear out the
		// current rotation factor x, y and z values.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "rotationParam"), 0.0f, 0.0f, 0.0f);

		// Setting the translation parameters to zero, in order to clear out the
		// current translation factor x, y and z values.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "translationParam"), 0.0f, 0.0f, 0.0f);
	}

	/**
	 * This function sets up the transformation parameters for the vertices of
	 * the teapot. The order of application is specified in the driver program.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * which must be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * @param scaleX - amount of scaling along the x-axis
	 * @param scaleY - amount of scaling along the y-axis
	 * @param scaleZ - amount of scaling along the z-axis
	 * @param rotateX - angle of rotation around the x-axis, in degrees
	 * @param rotateY - angle of rotation around the y-axis, in degrees
	 * @param rotateZ - angle of rotation around the z-axis, in degrees
	 * @param translateX - amount of translation along the x axis
	 * @param translateY - amount of translation along the y axis
	 * @param translateZ - amount of translation along the z axis
	 * 
	 * @return None.
	 */
	public void setUpTransforms(int program, GL3 gl3, float scaleX, float scaleY, float scaleZ, float rotateX,
			float rotateY, float rotateZ, float translateX, float translateY, float translateZ) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform3f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 3f represents vector of 3 values is being passed to uniform
		// variable.

		// Setting the scaling parameters to scaleX, scaleY and scaleZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "scalingParam"), scaleX, scaleY, scaleZ);

		// Setting the rotation parameters to rotateX, rotateY and rotateZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "rotationParam"), rotateX, rotateY, rotateZ);

		// Setting the translation parameters to translateX, translateY and
		// translateZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "translationParam"), translateX, translateY, translateZ);
	}

	/**
	 * This function clears any changes made to camera parameters, setting the
	 * values to the defaults: eye (0.0,0.0,0.0), lookat (0,0,0.0,-1.0), and up
	 * vector (0.0,1.0,0.0).
	 * 
	 * You will need to write this function, and maintain all of the values
	 * which must be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * 
	 * @return None.
	 */
	void clearCamera(int program, GL3 gl3) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform3f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 3f represents vector of 3 values is being passed to uniform
		// variable.

		// Clearing the eyePoint parameter values and setting to 0, 0 and 0.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "eyePointParam"), 0.0f, 0.0f, 0.0f);

		// Clearing the lookAt parameter values and setting to 0, 0 and -1.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "lookAtParam"), 0.0f, 0.0f, -1.0f);

		// Clearing the up parameter values and setting to 0, 1 and 0.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "upParam"), 0.0f, 1.0f, 0.0f);
	}

	/**
	 * This function sets up the camera parameters controlling the viewing
	 * transformation.
	 * 
	 * You will need to write this function, and maintain all of the values
	 * which must be sent to the vertex shader.
	 * 
	 * @param program - The ID of an OpenGL (GLSL) shader program to which 
	 * parameter values are to be sent
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 * @param eyeX - x coordinate of the camera location
	 * @param eyeY - y coordinate of the camera location
	 * @param eyeZ - z coordinate of the camera location
	 * @param lookatX - x coordinate of the lookat point
	 * @param lookatY - y coordinate of the lookat point
	 * @param lookatZ - z coordinate of the lookat point
	 * @param upX - x coordinate of the up vector
	 * @param upY - y coordinate of the up vector
	 * @param upZ - z coordinate of the up vector
	 * 
	 * @return None.
	 */
	void setUpCamera(int program, GL3 gl3, float eyeX, float eyeY, float eyeZ, float lookatX, float lookatY,
			float lookatZ, float upX, float upY, float upZ) {
		// The functions glGetUniformLocation, returns the location of the
		// uniform variable. and the function glUniform3f, specifies the
		// value of the uniform variable for the current uniform variable.
		// 3f represents vector of 3 values is being passed to uniform
		// variable.

		// Setting the eyePoint parameter values to eyeX, eyeY and eyeZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "eyePointParam"), eyeX, eyeY, eyeZ);

		// Setting the lookAt parameter values to lookatX, lookatY and lookatZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "lookAtParam"), lookatX, lookatY, lookatZ);

		// Setting the up parameter values to upX, upY and upZ.
		gl3.glUniform3f(gl3.glGetUniformLocation(program, "upParam"), upX, upY, upZ);
	}

}
