package Enemies;


import java.awt.Graphics2D;
import java.util.ArrayList;

import Util.StatObject;
import Util.Stats;
import Util.Time;
import engine.GamePanel;
import engine.MapObject;



public class Enemy extends StatObject{
	protected float sightRange;
	private float ATTACK_RANGE;
	protected StatObject target;
	protected int damage;
	double xDirecional;
	double yDirecional;
	public static final float DAMPING = 0.5f;
	private int attackDamage;
	public int type;
	public Enemy(int level) {
		stats = new Stats(0, false);
		target = null;
		attackDamage = 1;
		ATTACK_RANGE = 48f;
		
		setMoveSpeed(3);
		setType(3);
	}
	public void update(){
		
	}
	protected void chase() {
		
		double speedX = (getTarget().getX() - x);
		double speedY = (getTarget().getY() - y);
		double xAnterior = x;
		double yAnterior = y;
		
		double maxSpeed = getStats().getSpeed() * DAMPING;
		if(speedX > maxSpeed)
			speedX = maxSpeed;
		if (speedX < maxSpeed)
			speedX = -maxSpeed;
		if(speedY > maxSpeed)
			speedY = maxSpeed;
		if (speedY < maxSpeed)
			speedY = -maxSpeed;
		if(x < getTarget().getX() + sightRange + 20 && y < getTarget().getY() + sightRange + 20 && x > getTarget().getX() - sightRange + 20 && y > getTarget().getY() - sightRange + 20){
			x = x + speedX;
			y = y + speedY;
			
		
			xDirecional = x;
			yDirecional = y;
			if(xDirecional - target.getX() == 0.5 || xDirecional - target.getX() == -0.5){
				xDirecional = target.getX();
				
			}
			if(yDirecional - target.getY() == 0.5 || yDirecional- target.getY() == -0.5){
				yDirecional = target.getY();
				
			} 
			
		}
		if(xDirecional > xAnterior && xDirecional != target.getX()){
			right = true;
			
		}
		else if(xDirecional < xAnterior && xDirecional!= target.getX()){
			left = true;
			
		}
		
		else if(yDirecional > yAnterior && yDirecional != target.getY()){
			down = true;
			
		}
		else if(yDirecional < yAnterior && yDirecional != target.getY()){
			up = true;
			
		}
	}
	public void setTarget(StatObject go) {
		target = go;
	}

	public StatObject getTarget() {
		return this.target;
	}

	public Stats getStats() {
		return stats;
	}
	public int getAttackDamage(){
		return attackDamage;
	}
	public void setAttackRange(int range){
		ATTACK_RANGE = range;
	}
	
	public void setAttackDamage(int amt){
		this.attackDamage = amt;
	}
	
	public void setSightRange(float dist){
		this.sightRange = dist;
	}
	protected void look() {
		ArrayList<MapObject> objects=  GamePanel.sphereCollide((float)x, (float)y, sightRange);
		for(MapObject go : objects){
			if(go.getType() == PLAYER_ID)
			 setTarget((StatObject)go);
		}
	}
	public void draw(Graphics2D g){
		super.draw(g);
	}
}
