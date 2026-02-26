#version 330

layout (location=0) in vec3 inPosition;
layout (location=1) in vec4 colour;

uniform mat4 model;      // Add these uniforms
uniform mat4 view;
uniform mat4 projection;

out vec4 outColour;

void main()
{
    gl_Position = projection * view * model * vec4(inPosition, 1.0);
    // gl_Position = vec4(inPosition, 1.0);
    outColour = colour;
}