
//
//  textureParams.java
//
//  Created by Joe Geigel on 1/23/13.
//
//  Contributor:  Kapil Dole
//
//  Simple class for setting up the textures for the textures
//  assignment.
//
//  20155 version
//

import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.*;

public class textureParams {

	// Add any data members you need here.
	Texture texure_ID1, texure_ID2;
	
	/**
	 * constructor
	 */
	public textureParams() {
	}

	/**
	 * This function loads texture data to the GPU.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the various shaders.
	 *
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 *
	 */
	public void loadTexture(GL3 gl3) {
		try {
			// Loading sun and moon image files using input stream.
			InputStream inputStream1 = new FileInputStream("moon.png");
			InputStream inputStream2 = new FileInputStream( "sun.png" );
			
			// TextureIO is used for setting up the texture images.
			texure_ID1 = TextureIO.newTexture(inputStream1, false, "png");
			texure_ID2 = TextureIO.newTexture(inputStream2, false, "png");
		} catch (IOException except) {
			except.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * This function sets up the parameters for texture use.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the various shaders.
	 *
	 * @param program - The ID of an OpenGL (GLSL) program to which parameter values
	 *      			are to be sent
	 *
	 * @param gl3 - GL3 object on which all OpenGL calls are to be made
	 *
	 */
	public void setUpTexture(int program, GL3 gl3) {
		// initializing and binding the sampler for smiley image.
		gl3.glActiveTexture(GL.GL_TEXTURE0+0);
		texure_ID1.bind(gl3);
		gl3.glUniform1i(gl3.glGetUniformLocation(program, "imgSampler1"), 0);
		
		// initializing and binding the sampler for frowny image.
		gl3.glActiveTexture(GL.GL_TEXTURE0+1);
		texure_ID2.bind(gl3);
		gl3.glUniform1i(gl3.glGetUniformLocation(program, "imgSampler2"), 1);
	}
}
