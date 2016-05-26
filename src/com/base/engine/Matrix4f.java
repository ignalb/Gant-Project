package com.base.engine;

public class Matrix4f {

	private float[][] m;
	
	public Matrix4f() {
		setM(new float[4][4]);
	}

	public Matrix4f initIdentity() {
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				m[i][j] = (j == i) ? 1 : 0;
		
		return this;
	}
	
	public Matrix4f initTranslation(float x, float y, float z) {
		initIdentity();
		m[0][3] = x;
		m[1][3] = y;
		m[2][3] = z;
		
		return this;
	}
	public Matrix4f initRotation(float x, float y, float z) {
		Matrix4f rx = new Matrix4f().initIdentity();
		Matrix4f ry = new Matrix4f().initIdentity();
		Matrix4f rz = new Matrix4f().initIdentity();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
		
		rz.m[0][0] = (float)Math.cos(z);	rz.m[0][1] = -(float)Math.sin(z);
		rz.m[1][0] = (float)Math.sin(z);	rz.m[1][1] =  (float)Math.cos(z);
		rx.m[1][1] = (float)Math.cos(x);	rx.m[1][2] = -(float)Math.sin(x);
		rx.m[2][1] = (float)Math.sin(x);	rx.m[2][2] =  (float)Math.cos(x);
		ry.m[0][0] = (float)Math.cos(y);	ry.m[0][2] = -(float)Math.sin(y);
		ry.m[2][0] = (float)Math.sin(y);	ry.m[2][2] =  (float)Math.cos(y);
		
		m =  rz.multiply(ry.multiply(rx)).getM();
		
		return this;
	}
	public Matrix4f initScale(float x, float y, float z) {
		initIdentity();
		m[0][0] = x;
		m[1][1] = y;
		m[2][2] = z;
		
		return this;
	}
	
	public Matrix4f initProjection(float fov, float width, float height, float zNear, float zFar) {
		float aspectR = width / height;
		float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
		float zRange = zNear - zFar;
		
		initIdentity();
		m[0][0] = 1.0f / (tanHalfFOV * aspectR);
		m[1][1] = 1.0f / tanHalfFOV;
		for(int i = 0; i < 4; i++){
			m[3][i] = m[2][i];
		}
		m[2][2] = (-zNear - zFar) / zRange;		m[2][3] = 2 * zFar * zNear / zRange;
		
		
		return this;
	}
	
	public Matrix4f initCamera(Vector3f forward, Vector3f up) {
		Vector3f f = forward;
		f.normalize();
		
		Vector3f r = up;
		r.normalize();
		r = r.cross(f);
		
		Vector3f u = f.cross(r);
		
		initIdentity();
		
		m[0][0] = r.getX();	m[0][1] = r.getY();	m[0][2] = r.getZ();
		m[1][0] = u.getX(); m[1][1] = u.getY();	m[1][2] = u.getZ();
		m[2][0] = f.getX();	m[2][1] = f.getY();	m[2][2] = f.getZ();
		
		return this;
	}
	
	public Matrix4f multiply(Matrix4f M) {
		Matrix4f result = new Matrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				result.set(i, j,m[i][0] * M.get(0, j) +
								m[i][1] * M.get(1, j) +
								m[i][2] * M.get(2, j) +
								m[i][3] * M.get(3, j));
			}
		}
		
		return result;
	}
	
	public float[][] getM() {
		return m;
	}
	public float get(int x, int y) {
		return m[x][y];
	}

	public void setM(float[][] m) {
		this.m = m;
	}
	
	public void set(int x, int y, float f){
		m[x][y] = f;
	}
	
}
