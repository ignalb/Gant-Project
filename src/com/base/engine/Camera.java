package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Camera {
	private Vector3f yAxis = new Vector3f(0,1,0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	
	public Camera() {
		this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
	}
	public Camera(Vector3f pos, Vector3f forward, Vector3f up) {
		this.pos = pos;
		this.forward = forward;
		this.up = up;
				
	}
	
	public void move(Vector3f dir, float amount) {
		pos = pos.add(dir.multiply(amount));
	}
	
	public void rotateY(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, yAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	public void rotateX(float angle) {
		Vector3f hAxis = yAxis.cross(forward);
		hAxis.normalize();
		
		forward.rotate(angle, hAxis);
		forward.normalize();
		
		up = forward.cross(hAxis);
		up.normalize();
	}
	
	public Vector3f getRight() {
		Vector3f right = up.cross(forward);
		right.normalize();
		return right;
	}
	public Vector3f getLeft() {
		Vector3f left = forward.cross(up);
		left.normalize();
		return left;
	}
	
	public Vector3f getPos() {
		return pos;
	}
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	public Vector3f getForward() {
		return forward;
	}
	public void setForward(Vector3f forward) {
		this.forward = forward;
	}
	public Vector3f getUp() {
		return up;
	}
	public void setUp(Vector3f up) {
		this.up = up;
	}
	
	boolean isLocked = false;
	Vector2f center = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	public void input() {
		float sensitivity = 0.25f;
		float movement = (float)(10 * Time.getDelta());
		if(Input.getKey(Keyboard.KEY_A))
			move(getRight(), -movement);
		if(Input.getKey(Keyboard.KEY_D))
			move(getRight(), movement);
		if(Input.getKey(Keyboard.KEY_W))
			move(getForward(), movement);
		if(Input.getKey(Keyboard.KEY_S))
			move(getForward(), -movement);
		
		if(Input.getKeyDown(Keyboard.KEY_ESCAPE)){
			Input.setCursor(true);
			isLocked = false;
		}
		if(Input.getMouseDown(0)){
			Input.setMousePosition(center);
			Input.setCursor(false);
			isLocked = true;
		}
		if(isLocked){
			Vector2f dPos = Input.getMousePosition().subtract(center);
			boolean rotY = dPos.getX() != 0;
			boolean rotX = dPos.getY() != 0;
			
			if(rotY)
				rotateY(dPos.getX() * sensitivity);
			if(rotX)
				rotateX(-dPos.getY() * sensitivity);
			
			if(rotY || rotX)
				Input.setMousePosition(center);
		}
		
	}
	
}
