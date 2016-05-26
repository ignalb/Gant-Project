package com.base.engine;

public class Vector3f {

	private float x, y, z;
	
	public Vector3f(float x, float y, float z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}

	public float length(){
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	public float dot(Vector3f v){
		return x * v.getX() + y * v.getY() + z * v.getZ();
	}
	public Vector3f cross(Vector3f v){
		float x_ = y * v.getZ() - z * v.getY();
		float y_ = z * v.getX() - x * v.getZ();
		float z_ = x * v.getY() - y * v.getX();
		
		return new Vector3f(x_, y_, z_);
	}
	public Vector3f normalize(){
		float length = length();
		
		x /= length;
		y /= length;
		z /= length;
		
		return this;
	}
	
	public Vector3f add(Vector3f v){
		return new Vector3f(x + v.getX(), y + v.getY(), z + v.getZ());
	}
	public Vector3f subtract(Vector3f v){
		return new Vector3f(x - v.getX(), y - v.getY(), z - v.getZ());
	}
	public Vector3f multiply(Vector3f v){
		return new Vector3f(x * v.getX(), y * v.getY(), z * v.getZ());
	}
	public Vector3f divide(Vector3f v){
		return new Vector3f(x / v.getX(), y / v.getY(), z / v.getZ());
	}
	public Vector3f add(float m){
		return new Vector3f(x + m, y + m, z + m);
	}
	public Vector3f subtract(float m){
		return new Vector3f(x - m, y - m, z - m);
	}
	public Vector3f multiply(float m){
		return new Vector3f(x * m, y * m, z * m);
	}
	public Vector3f divide(float m){
		return new Vector3f(x / m, y / m, z / m);
	}
	
	public Vector3f rotate(float angle, Vector3f axis){
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.multiply(this).multiply(conjugate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
		
		return this;
	}
	
	public Vector3f abs() {
		return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
	public String toString(){
		return "(" + x + " " + y + " " + z + ")";
	}
	
}
