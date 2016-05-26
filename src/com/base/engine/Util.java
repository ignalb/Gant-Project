package com.base.engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Util {

	public static FloatBuffer createFloatBuffer(int size) {
		return BufferUtils.createFloatBuffer(size);
	}
	public static IntBuffer createIntBuffer(int size) {
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values) {
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
		FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
		
		for(Vertex vertex : vertices){
			buffer.put(vertex.getPos().getX());
			buffer.put(vertex.getPos().getY());
			buffer.put(vertex.getPos().getZ());
			buffer.put(vertex.getTextureCoord().getX());
			buffer.put(vertex.getTextureCoord().getY());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				buffer.put(matrix.get(i, j));
			}
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static String[] removeEmptyStrings(String[] data) {
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i < data.length; i++)
			if(!data[i].equals(""))
				result.add(data[i]);
		
		return result.toArray(new String[0]);
	}
	
	public static int[] toIntArray(Integer[] array) {
		int[] result = new int[array.length];
		
		for(int i = 0; i < array.length; i++){
			result[i] = array[i];
		}
		
		return result;
	}
	
}
