package com.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.TextureLoader;

public class ResourceLoader {

	public static Texture loadTexture(String filename) {
		String ext = getEXT(filename);
		
		try {
			int id = TextureLoader.getTexture(ext, new FileInputStream(new File("./res/textures/" + filename))).getTextureID();
			
			return new Texture(id);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	public static String loadShader(String filename) {
		StringBuilder shader_src = new StringBuilder();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader("./res/shaders/" + filename));
			
			String line = "";
			while((line = reader.readLine()) != null) {
				shader_src.append(line).append(System.lineSeparator());
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return shader_src.toString();
	}
	
	public static Mesh loadMesh(String filename) {
		String ext = getEXT(filename);
		BufferedReader reader = null;
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		if(!ext.equals("obj")){
			System.err.println("Error: Cannot load mesh. File format not supported : " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		try {
			reader = new BufferedReader(new FileReader("./res/models/" + filename));
			
			String line = "";
			while((line = reader.readLine()) != null) {
				String[] tokens = line.split(" ");
				tokens = Util.removeEmptyStrings(tokens);
				
				if(tokens.length == 0 || tokens[0].equals("#"))
					continue;
				else if(tokens[0].equals("v"))
					vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
				else if(tokens[0].equals("f")){
					indices.add(Integer.parseInt(tokens[1].split("/")[0])-1);
					indices.add(Integer.parseInt(tokens[2].split("/")[0])-1);
					indices.add(Integer.parseInt(tokens[3].split("/")[0])-1);
					
					if(tokens.length > 4){
						indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
						indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
					}
				}
			}
			
			reader.close();
			
			Mesh result = new Mesh();
			result.addVertices(vertices.toArray(new Vertex[0]), Util.toIntArray(indices.toArray(new Integer[0])));
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
	
	private static String getEXT(String filename) {
		return filename.substring(filename.lastIndexOf('.') + 1);
	}
	
}
