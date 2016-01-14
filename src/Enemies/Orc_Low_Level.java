package Enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import User.Animation;

public class Orc_Low_Level extends Enemy{
	public int currentAction;
	private static final int IDLE = 0;
	private static final int WALKINGRIGHT = 2;
	private static final int WALKINGLEFT = 1;
	private static final int WALKINGDOWN = 0;
	private static final int WALKINGUP = 3;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 3, 3, 3, 3, // numero de quadros q
			// cada animacao
			// possui
	};
	public Orc_Low_Level(int level) {
		super(level);
		setMoveSpeed(1);
		// TODO Auto-generated constructor stub
		width = 32;
		height = 32;
		damage = 2;
		sightRange = 80f;
		try {
			BufferedImage spritesheet = ImageIO
					.read(getClass().getResourceAsStream("/Enemies/orc_low_level.png"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 4; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for (int j = 0; j < numFrames[i]; j++) {
				
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					
				}
				sprites.add(bi);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		animation = new Animation();
		currentAction = WALKINGDOWN;
		animation.setFrames(sprites.get(WALKINGDOWN));
		animation.setDelay(500);
	}
	private void getNextPosition(){
		if(left){
			x -= getSpeed();
			
		}
		else if(right){
			x += getSpeed();
			
		}
		if(up){
			y -= getSpeed();
			
		}
		else if(down){
			y += getSpeed();
			
		}
	}
	public void update(){
		getNextPosition();
		if (target == null)
			look();
		else {
			//if((x != target.getX() + 32 || y != target.getY() + 32) )
				chase();
		}
		
		if (left){
			if (currentAction != WALKINGLEFT) {
				currentAction = WALKINGLEFT;
				animation.setFrames(sprites.get(WALKINGLEFT));
				animation.setDelay(160);
				width = 30;
			
				System.out.println(x);
				}
		}
		else if (right){
			if (currentAction != WALKINGRIGHT) {
				currentAction = WALKINGRIGHT;
				animation.setFrames(sprites.get(WALKINGRIGHT));
				animation.setDelay(160);
				width = 30;	
				}
		}
		else if (up){
			if (currentAction != WALKINGUP) {
				currentAction = WALKINGUP;
				animation.setFrames(sprites.get(WALKINGUP));
				animation.setDelay(160);
				width = 30;
				
				}
		}
		else if (down){
			if (currentAction != WALKINGDOWN) {
				currentAction = WALKINGDOWN;
				animation.setFrames(sprites.get(WALKINGDOWN));
				animation.setDelay(160);
				width = 30;
			
				
				}
		}
		else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
		up = false;
		down = false;
		left = false;
		right = false;
	}
}
