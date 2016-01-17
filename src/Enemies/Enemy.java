package Enemies;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Mobs.Creature;
import Util.StatObject;
import Util.Stats;
import engine.Delay;
import engine.GamePanel;
import engine.MapObject;
public class Enemy extends Creature{
	protected float sightRange;
	protected float ATTACK_RANGE;
	protected StatObject target;
	protected int damage;
	double xDirecional;
	double yDirecional;
	protected Delay attackDelay;
	protected int countDown;
	protected int countUp;
	protected int countLeft;
	protected int countRight;
	protected boolean isChasing = false;
	public static final float DAMPING = 0.5f;
	protected int attackDamage;
	public int type;
	public Enemy(int level) {
		target = null;
		deadSprite = new BufferedImage[1];
		stats = new Stats(0, false);
		setType(3);
		font = new Font("Arial", Font.PLAIN, 12);
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 12);
		try {
			deadSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/gravestone.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(){
		super.update();
	}
	public void chase() {
		
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
		else{
			setTarget(null);
			isChasing = false;
			up = true;
			//System.out.println("o");
		}
		
		if(xDirecional > xAnterior && xDirecional != target.getX()){
			right = true;
			tempDirection = 1;
			
		}
		else if(xDirecional < xAnterior && xDirecional!= target.getX()){
			left = true;
			tempDirection = 2;
		}
		
		else if(yDirecional > yAnterior && yDirecional != target.getY()){
			down = true;
			tempDirection = 3;
			
		}
		else if(yDirecional < yAnterior && yDirecional != target.getY()){
			up = true;
			tempDirection = 4;
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
	public void attack(){
		if(getTarget().getCurrentHealth() > 0){
			getTarget().damage(getAttackDamage());
		//	System.out.println("Health of Player:"+getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
			attackDelay.restart();
		}
	}
	public void look() {
		//System.out.println("are you looking?");
		tempDirection = 0;
		ArrayList<MapObject> objects=  GamePanel.sphereCollide((float)x, (float)y, sightRange);
		for(MapObject go : objects){
			if(go.getType() == PLAYER_ID){
			 setTarget((StatObject)go);
			 isChasing = true;
			 }
		}
		
	}
	public void draw(Graphics2D g){
		super.draw(g);
		//System.out.println("ccc");
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(getCurrentHealth()+" / "+getMaxHealth(), (int)getX()-12 , (int)getY()-20);
		g.setFont(font);
	}
}
