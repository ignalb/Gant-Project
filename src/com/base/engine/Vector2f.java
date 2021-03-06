package com.base.engine;

public class Vector2f {
	
	private float x, y;
	
	public Vector2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float length(){
		return (float)Math.sqrt(x * x + y * y);
	}
	public float dot(Vector2f v){
		return x * v.getX() + y * v.getY();
	}
	public Vector2f normalize(){
		float length = length();
		
		x /= length;
		y /= length;
		
		return this;
	}
	
	public Vector2f add(Vector2f v){
		return new Vector2f(x + v.getX(), y + v.getY());
	}
	public Vector2f subtract(Vector2f v){
		return new Vector2f(x - v.getX(), y - v.getY());
	}
	public Vector2f multiply(Vector2f v){
		return new Vector2f(x * v.getX(), y * v.getY());
	}
	public Vector2f divide(Vector2f v){
		return new Vector2f(x / v.getX(), y / v.getY());
	}
	public Vector2f add(float m){
		return new Vector2f(x + m, y + m);
	}
	public Vector2f subtract(float m){
		return new Vector2f(x - m, y - m);
	}
	public Vector2f multiply(float m){
		return new Vector2f(x * m, y * m);
	}
	public Vector2f divide(float m){
		return new Vector2f(x / m, y / m);
	}
	
	public Vector2f rotate(float angle){
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
	}
	
	public float getX(){
		return x;
	}
	public void setX(float x){
		this.x = x;
	}
	
	public float getY(){
		return y;
	}
	public void getY(float y){
		this.y = y;
	}
	
	public String toString(){
		return "(" + x + " " + y + ")";
	}

}
