#version 150

// Phong fragment shader
//
// Contributor:  Kapil Dole

// INCOMING DATA
uniform vec4 ambient_Material_Color_Tree;
uniform vec4 ambient_Light_Color;
uniform vec4 diffuse_Material_Color_Tree;
uniform vec4 specular_Material_Color;
uniform vec4 light_Source_Color;

uniform float ambient_Reflection_Coefficient;
uniform float diffuse_Reflection_Coefficient;
uniform float specular_Reflection_Coefficient;
uniform float specular_Exponent;


// Add all variables containing data used by the
// fragment shader for lighting and shading
varying vec3 L;
varying vec3 N;
varying vec3 V;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;

void main()
{
    // Add all necessary code to implement the
    // fragment shader portion of Phong shading
    vec4 ambientParam, diffuseParam, specularParam;
    vec3 R;
    
    R = normalize(reflect(L, N));
    
    // Computing ambient factor.
    ambientParam = ambient_Light_Color * ambient_Material_Color_Tree * ambient_Reflection_Coefficient;
    
    // Computing diffuse factor.
    diffuseParam = diffuse_Material_Color_Tree * light_Source_Color * diffuse_Reflection_Coefficient * dot(L, N);
    
    // Computing specular factor.
    specularParam = specular_Material_Color * light_Source_Color * specular_Reflection_Coefficient * pow(clamp(dot(R, V), 0, 1), specular_Exponent);
    
    fragmentColor = ambientParam + diffuseParam + specularParam;
}
