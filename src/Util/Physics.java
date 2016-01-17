package Util;

import java.awt.Rectangle;

import engine.MapObject;



public class Physics {
	public static MapObject checkCollision(MapObject go1, MapObject go2){
		return checkCollision(new Rectangle((int)go1.getX(),(int)go1.getY(),(int)go1.getX(),(int)go1.getY()),go2);
		
	}
	public static MapObject checkCollision(Rectangle r1, MapObject go2){
		
		Rectangle r2 =	new Rectangle((int)go2.getX(),(int)go2.getY(),(int)go2.getX(),(int)go2.getY());
		boolean res = r1.intersects(r2);
		if(res)
			return go2;
		else
			return null;
	}
}
