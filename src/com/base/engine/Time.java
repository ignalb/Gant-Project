package com.base.engine;

public class Time {

	public static final long SECOND = (long)1e9;
	
	private static double deltaTime;
	
	public static long getTime(){
		return System.nanoTime();
	}
	
	public static double getDelta(){
		return deltaTime;
	}
	public static void setDelta(double time){
		Time.deltaTime = time;
	}
	
}
