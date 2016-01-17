package User;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import Mobs.Creature;



public class Player extends Creature{
	
	public int currentAction = -1;
	//private static final int IDLE = 0;
	private static final int WALKINGRIGHT = 2;
	private static final int WALKINGLEFT = 1;
	private static final int WALKINGDOWN = 0;
	private boolean isMoving = false;
	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	private static final int WALKINGUP = 3;
	
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 3, 3, 3, 3, // numero de quadros q
			// cada animacao
			// possui
	
};
	public Player(){
		deadSprite = new BufferedImage[1];
		width = 32;
		height = 32;
		setType(2);
		stats = new Util.Stats(1, true);
		idleRightSprite = new BufferedImage[1];
		idleLeftSprite = new BufferedImage[1];
		idleUpSprite = new BufferedImage[1];
		idleDownSprite = new BufferedImage[1];
		font = new Font("Arial", Font.PLAIN, 12);
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.PLAIN, 12);
		try {
			idleRightSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/beren_idle_right.png"));
			idleLeftSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/beren_idle_left.png"));
			idleUpSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/beren_idle_up.png"));
			idleDownSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/beren_idle_down.png"));
			deadSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Characters/gravestone.png"));
			BufferedImage spritesheet = ImageIO
					.read(getClass().getResourceAsStream("/Characters/beren.png"));
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
	//	currentAction = WALKINGDOWN;
	//	animation.setFrames(sprites.get(WALKINGDOWN));
		animation.setFrames(idleDownSprite);
		animation.setDelay(160);
	}
	
	public void draw(Graphics2D g){
		
		super.draw(g);
		if(!isDead){
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString(getCurrentHealth()+" / "+getMaxHealth(), (int)getX()-12 , (int)getY()-20);
			g.setFont(font);
		}
	}
	private void getNextPosition() {
		if(left){
			x -= 1;
			tempDirection = 1;
		}
		if(right){
			x += 1;
			tempDirection = 2;
		}
		if(down){
			y += 1;
			tempDirection = 3;
		
			
		}
		if(up){
			y -= 1;
			tempDirection = 4;
		}
	}

	public void update() {
		super.update();
		if (!isDead) {
			getNextPosition();
			setPosition(x, y);
			
			if (left) {
				if (currentAction != WALKINGLEFT) {
					currentAction = WALKINGLEFT;
					animation.setFrames(sprites.get(WALKINGLEFT));
					animation.setDelay(160);
					width = 30;

					// System.out.println(x);
				}
			} else if (right) {
				if (currentAction != WALKINGRIGHT) {
					currentAction = WALKINGRIGHT;
					animation.setFrames(sprites.get(WALKINGRIGHT));
					animation.setDelay(160);
					width = 30;
					// System.out.println(x);
				}
			} else if (up) {
				if (currentAction != WALKINGUP) {
					currentAction = WALKINGUP;
					animation.setFrames(sprites.get(WALKINGUP));
					animation.setDelay(160);
					width = 30;
				

				}
			} else if (down) {
				if (currentAction != WALKINGDOWN) {
					currentAction = WALKINGDOWN;
					animation.setFrames(sprites.get(WALKINGDOWN));
					animation.setDelay(160);
					width = 30;
					System.out.println("hf");
				}
				
			} else {
				currentAction = -1;
				if(!isMoving){
					if(tempDirection == 1)
						animation.setFrames(idleLeftSprite);
					else if(tempDirection == 2)
						animation.setFrames(idleRightSprite);
					else if(tempDirection == 3)
						animation.setFrames(idleDownSprite);
					else if(tempDirection == 4)
						animation.setFrames(idleUpSprite);
					
					animation.setDelay(400);
					width = 30;
				}
				
			}
			animation.update();
		}
	}
	
}
