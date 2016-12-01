//
// Vertex shader for the transformation assignment
//
// Author:  W. R. Carithers
//
// Contributor:  Kapil Dole

#version 150

// attribute:  vertex position
in vec4 vPosition;

// add other global shader variables to hold the
// parameters your application code is providing

// Adding global variables.
uniform float projectionType;
uniform float leftParam;
uniform float rightParam;
uniform float topParam;
uniform float bottomParam;
uniform float nearParam;
uniform float farParam;

// Adding global vector variables.
uniform vec3 scalingParam;
uniform vec3 rotationParam;
uniform vec3 translationParam;
uniform vec3 eyePointParam;
uniform vec3 lookAtParam;
uniform vec3 upParam;

void main()
{
	// declaring the local variables.
	vec3 nVector, uVector, vVector;
	mat4 scalingMatrix, rotationXMatrix, rotationYMatrix, rotationZMatrix, translationMatrix;
	mat4 transformMatrix, worldToCameraMatrix, projectionMatrix ;
	
   	// Calculating n, u and v vectors based on the camera location (eyePoint),
   	// direction of camera is looking (lookAt) and The up direction of the
   	// camera (up).
   	nVector = normalize(eyePointParam - lookAtParam);
	uVector = normalize(cross(upParam, nVector));
	vVector = normalize(cross(nVector, uVector));
	
	// Computing 4*4 translation matrix, using translation x, y and z parameters.
	translationMatrix = mat4(1.0, 0.0, 0.0, 0.0,
					  		 0.0, 1.0, 0.0, 0.0,
					  		 0.0, 0.0, 1.0, 0.0,
					 		 translationParam.x, translationParam.y, translationParam.z, 1.0);
	
	// Computing 4*4 scaling matrix, using scaling x, y and z parameters.
	scalingMatrix = mat4(scalingParam.x, 0.0,0.0,0.0,
						 0.0, scalingParam.y, 0.0, 0.0,
						 0.0, 0.0, scalingParam.z, 0.0,
						 0.0, 0.0, 0.0, 1.0);
	
	// Computing 4*4 rotation matrix about x axis, by taking sin and cos of the rotation angles.
	float cosXValue = cos(radians(rotationParam.x));
	float sinXValue = sin(radians(rotationParam.x));
	rotationXMatrix = mat4(1.0, 0.0, 0.0, 0.0,
					  	   0.0, cosXValue, -sinXValue, 0.0,
					  	   0.0, sinXValue, cosXValue, 0.0,
					  	   0.0, 0.0, 0.0, 1.0);
	
	// Computing 4*4 rotation matrix about y axis, by taking sin and cos of the rotation angles.
	float cosYValue = cos(radians(rotationParam.y));
	float sinYValue = sin(radians(rotationParam.y));
	rotationYMatrix = mat4(cosYValue, 0.0, -sinYValue, 0.0,
					  	   0.0, 1.0, 0.0, 0.0,
					  	   sinYValue, 0.0, cosYValue, 0.0,
					  	   0.0, 0.0, 0.0, 1.0);
	
	// Computing 4*4 rotation matrix about z axis, by taking sin and cos of the rotation angles.
	float cosZValue = cos(radians(rotationParam.z));
	float sinZValue = sin(radians(rotationParam.z));
	rotationZMatrix = mat4(cosZValue, sinZValue, 0.0, 0.0,
					  	   -sinZValue, cosZValue, 0.0, 0.0,
					  	   0.0, 0.0, 1.0, 0.0,
					  	   0.0, 0.0, 0.0, 1.0);
	
	// Transform matrix will be combination of all 3, translation, rotation (around all three 
	// x, y and z axes) and scaling.
	transformMatrix = translationMatrix * (rotationZMatrix * rotationYMatrix * rotationXMatrix) * scalingMatrix;
	
	// Computing the world to camera coordinates, using view transform matrix with the help of u, v and n
	// vectors and eyePoint parameter.
	worldToCameraMatrix = mat4(uVector.x, vVector.x, nVector.x, 0.0, 
					   	 	   uVector.y, vVector.y, nVector.y, 0.0,
					   	       uVector.z, vVector.z, nVector.z, 0.0,
					   		   -dot(uVector, eyePointParam), -dot(vVector, eyePointParam), -dot(nVector, eyePointParam), 1.0);
	
	// Computing projection matrix based on the projection type flag, if it is 1.0, then compute
	// for the frustum projection, otherwise for orthographic projection.
	if(projectionType == 1.0){
		projectionMatrix = mat4((2.0 * nearParam / (rightParam - leftParam)), 0.0, 0.0, 0.0,
						  		 0.0, (2.0 * nearParam / (topParam - bottomParam)), 0.0, 0.0,
						  		 (rightParam + leftParam) / (rightParam - leftParam), (topParam + bottomParam) / (topParam - bottomParam), -(farParam + nearParam) / (farParam - nearParam), -1.0,
						  		 0.0, 0.0, -2.0 * farParam * nearParam / (farParam - nearParam), 0.0);	
	}
	else{
		projectionMatrix = mat4((2.0 / (rightParam - leftParam)), 0.0, 0.0, 0.0,
						  		 0.0, (2.0 / (topParam - bottomParam)), 0.0, 0.0,
						  		 0.0, 0.0, -2.0 / (farParam - nearParam), 0.0,
						  		 -(rightParam + leftParam) / (rightParam - leftParam), -(topParam + bottomParam) / (topParam - bottomParam), -(farParam + nearParam) / (farParam - nearParam), 1.0);
	}
	
	// Finally by multiplying projection matrix and modelView matrix to the object coordinates,
	// we will get clip space coordinates, which is displayed on the screen.
    gl_Position =  projectionMatrix * worldToCameraMatrix * transformMatrix * vPosition;
}