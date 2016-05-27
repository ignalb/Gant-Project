package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Material material;
	private Camera camera;
	
	public Game(){
		mesh = ResourceLoader.loadMesh("cube.obj");
		camera = new Camera();
		shader = new BasicShader();
		material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1));
		transform = new Transform();
		transform.setProjection(70.0f, Window.getWidth(), Window.getHeight(), 0.1f, 1000.0f);
		Transform.setCamera(camera);
		
		/*Vertex[] vertices = {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0.0f, 0.0f)),
							 new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f, 0.0f)),
							 new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f, 0.0f)),
							 new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f, 1.0f))};
		int[] indices = new int[] {	3,1,0,
									2,1,3,
									0,1,2,
									0,2,3};
		
		mesh.addVertices(vertices, indices);*/
		
	}
	
	public void input(){
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD9))
			System.out.println("Top CW");
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD7))
			System.out.println("Top CCW");
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD3))
			System.out.println("Bottom CW");
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD1))
			System.out.println("Bottom CCW");
		if(Input.getKeyDown(Keyboard.KEY_DELETE))
			System.out.println("Left CW");
		if(Input.getKeyDown(Keyboard.KEY_INSERT))
			System.out.println("Left CCW");		
		if(Input.getKeyDown(Keyboard.KEY_NEXT))
			System.out.println("Right CW");
		if(Input.getKeyDown(Keyboard.KEY_PRIOR))
			System.out.println("Right CCW");
		if(Input.getKeyDown(Keyboard.KEY_END))
			System.out.println("Front CW");
		if(Input.getKeyDown(Keyboard.KEY_HOME))
			System.out.println("Front CCW");		
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD2))
			System.out.println("Back CW");
		if(Input.getKeyDown(Keyboard.KEY_NUMPAD8))
			System.out.println("Back CCW");
		
		camera.input();
			
		
	}
	
	float temp = 0;
	
	public void update(){
		/*temp += Time.getDelta();
		float sinTemp = (float)Math.sin(temp);
		transform.setTranslation(sinTemp, 0, 5);
		transform.setRotation(0, sinTemp * 180, 0);
		transform.setScale(Math.abs(sinTemp), Math.abs(sinTemp), 1);*/
	}
	
	public void render(){
		shader.bind();
		shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
		mesh.draw();
	}
	
}
