package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Material material;
	private Camera camera;
	
	public Game(){
		mesh = new Mesh();//ResourceLoader.loadMesh("cube.obj");
		camera = new Camera();
		shader = new BasicShader();
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1));
		transform = new Transform();
		transform.setProjection(70.0f, Window.getWidth(), Window.getHeight(), 0.1f, 1000.0f);
		Transform.setCamera(camera);
		
		Vertex[] vertices = {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0.0f, 0.0f)),
							 new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f, 0.0f)),
							 new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f, 0.0f)),
							 new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f, 1.0f))};
		int[] indices = new int[] {	3,1,0,
									2,1,3,
									0,1,2,
									0,2,3};
		
		mesh.addVertices(vertices, indices);
		
	}
	
	public void input(){
		if(Input.getKeyDown(Keyboard.KEY_SPACE))
			System.out.println("Space Down");
		if(Input.getKeyUp(Keyboard.KEY_SPACE))
			System.out.println("Space Up");
		if(Input.getMouseDown(0))
			System.out.println("LMB Down at " + Input.getMousePosition());
		if(Input.getMouseUp(0))
			System.out.println("LMB Up");
	}
	
	float temp = 0;
	
	public void update(){
		temp += Time.getDelta();
		float sinTemp = (float)Math.sin(temp);
		transform.setTranslation(sinTemp, 0, 5);
		transform.setRotation(0, sinTemp * 180, 0);
		//transform.setScale(Math.abs(sinTemp), Math.abs(sinTemp), 1);
	}
	
	public void render(){
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
	
}
