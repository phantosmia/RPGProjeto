package Util;

import engine.MapObject;

public class Util {
	public static boolean lineOfSight(MapObject go1, MapObject  go2){
		return true;
	}
	public static float dist(double x1, double y1, double x2, double y2){
		double x = x2 - x1;
		double y = y2 - y1;
		return (float) Math.sqrt((x * x) + (y * y));
	}
}
