#version 330

in vec2 outTexCoord;
out vec4 fragColour;

uniform sampler2D txtSampler;

void main()
{
    fragColour = texture(txtSampler, outTexCoord);
}