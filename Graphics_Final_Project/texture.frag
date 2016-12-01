#version 150

// Texture mapping fragment shader
//
// Contributor:  Kapil Dole

// INCOMING DATA
in vec2 texture_Coord;

// Add all variables containing data used by the
// fragment shader for lighting and texture mapping

// OUTGOING DATA
uniform sampler2D imgSampler1;
uniform sampler2D imgSampler2;

// final fragment color
out vec4 fragmentColor;

void main()
{
    // Replace with proper texture mapping code
    // Displaying smiley on front face
    if(gl_FrontFacing == true){
  		fragmentColor = texture2D(imgSampler1, texture_Coord);
  	}
  	// Displaying frowny on back face
  	else {
  		fragmentColor = texture2D(imgSampler2, texture_Coord);	
  	}
}
