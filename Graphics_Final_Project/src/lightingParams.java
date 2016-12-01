
//
// lightingParams.java
//
// Created by Joe Geigel on 1/23/13.
//
// Contributor:  Kapil Dole
//
// 20155 version
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class lightingParams {
	// Add any data members you need here.

	/**
	 * constructor
	 */
	public lightingParams() {
	}

	/**
	 * This function sets up the lighting, material, and shading parameters for
	 * the Phong shader.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 *
	 * @param program - The ID of an OpenGL (GLSL) shader program to which parameter
	 *            		values are to be sent
	 *
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 *
	 */
	public void setUpPhong(int program, GL3 gl3) {
		// Setting material properties for teapot, using RGBA notation (Red,
		// Green, Blue and Alpha channel).
		// Setting ambient material color, which represents background color for
		// various objects.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "ambient_Material_Color"), 0.5f, 0.1f, 0.9f, 1.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "ambient_Material_Color_Terrin"), 0.5f, 0.1f, 0.9f, 1.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "ambient_Material_Color_Tree"), 0.5f, 0.1f, 0.9f, 1.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "ambient_Material_Color_House"), 0.5f, 0.1f, 0.9f, 1.0f);

		// Setting diffuse material color, which represents color for Lambertian
		// reflection.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "diffuse_Material_Color"), 0.89f, 0.0f, 0.0f, 1.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "diffuse_Material_Color_Terrin"), 0.39f, 1.0f, 0.0f, 0.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "diffuse_Material_Color_Tree"), 0.0f, 0.5f, 0.0f, 1.0f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "diffuse_Material_Color_House"), 0.36f, 0.25f, 0.20f, 1.0f);

		// Setting specular material color, which represents color for mirror
		// like reflection.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "specular_Material_Color"), 1.0f, 1.0f, 1.0f, 1.0f);

		// Setting Reflective characteristics of the teapot and the quad, using
		// RGBA notation.
		// Setting value for ambient reflection coefficient for teapot and quad.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "ambient_Reflection_Coefficient"), 0.5f);

		// Setting value for diffuse reflection coefficient for teapot and quad.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "diffuse_Reflection_Coefficient"), 0.7f);

		// Setting value for specular reflection coefficient for teapot and
		// quad.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "specular_Reflection_Coefficient"), 1.0f);

		// Setting value for specular exponent for teapot and quad.
		gl3.glUniform1f(gl3.glGetUniformLocation(program, "specular_Exponent"), 10.0f);

		// Setting properties of the light source.
		// Setting color of light source.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "light_Source_Color"), 1.0f, 1.0f, 0.3f, 1.0f);

		// Setting up light source position.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "light_Source_Position"), 2.0f, 6.0f, 4.0f, 0.4f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "light_Source_Position_Terrin"), 3.0f, 7.0f, 1.0f, 0.5f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "light_Source_Position_Tree"), 3.0f, 6.0f, 5.0f, 0.8f);
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "light_Source_Position_House"), 2.0f, 5.0f, 6.0f, 0.2f);

		// Setting ambient light in the scene
		// Setting ambient light color.
		gl3.glUniform4f(gl3.glGetUniformLocation(program, "ambient_Light_Color"), 0.5f, 0.5f, 0.5f, 1.0f);
	}
}
