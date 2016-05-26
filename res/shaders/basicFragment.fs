#version 450

in vec2 texCoord0;
out vec4 fragColor;

uniform vec3 color;
uniform sampler2D sampler;

void main() {
	vec4 textureColor = texture2D(sampler, texCoord0.xy);
	fragColor = vec4(color, 1);
	
	if(textureColor != 0)
		fragColor *= textureColor;
}