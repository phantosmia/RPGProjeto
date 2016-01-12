package engine;

import java.awt.Graphics2D;

import User.Animation;

public abstract class MapObject {
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	public Animation animation;
	public int width;
	public int height;
	public double x;
	public double y;
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void draw(Graphics2D g){
		
		g.drawImage(animation.getImage(),(int)x- width / 2 ,(int)y - height/2 , null);
	}
}
