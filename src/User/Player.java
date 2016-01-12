package User;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.MapObject;

public class Player extends MapObject{
	
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
	public Player(){
		width = 32;
		height = 32;
		try {
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
		currentAction = WALKINGDOWN;
		animation.setFrames(sprites.get(WALKINGDOWN));
		animation.setDelay(500);
	}
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics2D g){
		
		super.draw(g);
	}
	private void getNextPosition() {
		if(left){
			x -= 1;
		}
		if(right){
			x += 1;
		}
		if(down){
			y += 1;
		}
		if(up){
			y -= 1;
		}
	}
	public void update(){
		getNextPosition();
		setPosition(x,y);
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
	}
	
}
