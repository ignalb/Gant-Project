package com.base.engine;

public class Vertex {

	public static final int SIZE = 5;
	
	private Vector3f pos;
	private Vector2f textureCoord;
	
	public Vertex(Vector3f pos) {
		this(pos, new Vector2f(0,0));
	}
	public Vertex(Vector3f pos, Vector2f texture) {
		this.setPos(pos);
		this.setTextureCoord(texture);
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector2f getTextureCoord() {
		return textureCoord;
	}

	public void setTextureCoord(Vector2f textureCoord) {
		this.textureCoord = textureCoord;
	}
	
}
