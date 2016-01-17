package engine;

import java.awt.Graphics2D;


import User.Animation;

public abstract class MapObject {
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	public int tempDirection = 0;
	public Animation animation;
	public int width;
	public int height;
	public double x;
	public double y;
	public int type;
	public static final int ENEMY_ID = 3 ;
	public static final int PLAYER_ID = 2;
	public static final int ITEM_ID = 1;
	public static final int DEFAULT_ID = 0;
	public double getY(){
		return y;
	}
	public double getX(){
		return x;
	}
	public int getType(){
		return type;
	}
	public void setType(int type){
		this.type = type;
	}
	public void setPosition(double x2, double y2) {
		this.x = x2;
		this.y = y2;
	}
	
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void draw(Graphics2D g){
		
		g.drawImage(animation.getImage(),(int)x- width / 2 ,(int)y - height/2 , null);
	}
}
