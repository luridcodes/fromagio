#version 330

layout (location=0) in vec3 inPosition;
layout (location=1) in vec4 colour;

out vec4 outColour;

void main()
{
    gl_Position = vec4(inPosition, 1.0);
    outColour = colour;
}