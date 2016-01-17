package Enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import User.Animation;

import engine.Delay;

public class Orc_Low_Level extends Enemy {
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

		attackDamage = 1;
		ATTACK_RANGE = 25f;
		down = true;
		setMoveSpeed(1);
		// TODO Auto-generated constructor stub
		width = 32;
		height = 32;
		damage = 2;
		sightRange = 80f;
		attackDelay = new Delay(800);
		attackDelay.terminate();
		idleRightSprite = new BufferedImage[1];
		idleLeftSprite = new BufferedImage[1];
		idleUpSprite = new BufferedImage[1];
		idleDownSprite = new BufferedImage[1];

		// System.out.println("Saude do orc" + getCurrentHealth());
		try {
			idleRightSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/orc_low_level_idle_right.png"));
			idleLeftSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/orc_low_level_idle_left.png"));
			idleUpSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/orc_low_level_idle_up.png"));
			idleDownSprite[0] = ImageIO.read(getClass().getResourceAsStream("/Enemies/orc_low_level_down.png"));
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Enemies/orc_low_level.png"));
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

	private void getNextPosition() {

		if (!isChasing) {
			// patrolling

			if (left) {
				x -= getSpeed();

			} else if (right) {
				x += getSpeed();

			}
			if (up) {
				countDown = 0;
				if (countUp <= 100) {
					y -= getSpeed();
					countUp += getSpeed();
				} else {
					down = true;
					up = false;
				}

			} else if (down) {
				countUp = 0;
				if (countDown <= 100) {
					y += getSpeed();
					countDown += getSpeed();
				} else {
					// count = 0;
					up = true;
					down = false;
					// down = false;
				}

			}
			animation.update();
		}
	}

	public void update() {
		getNextPosition();
		if (target == null) {
			look();
			// currentAction = IDLE;
			if (tempDirection == 0) {
				// animation.setFrames(sprites.get(IDLE));
				// System.out.println("mfnas");
			}
			if (tempDirection == 1)
				animation.setFrames(idleRightSprite);
			if (tempDirection == 2)
				animation.setFrames(idleLeftSprite);
			if (tempDirection == 3)
				animation.setFrames(idleDownSprite);
			if (tempDirection == 4)
				animation.setFrames(idleUpSprite);
			animation.setDelay(400);
			width = 30;
			animation.update();

		} else {
			if (Util.Util.lineOfSight(this, target)
					&& Util.Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= ATTACK_RANGE) {
				if (attackDelay.isOver())
					attack();
			}
			// if((x != target.getX() + 32 || y != target.getY() + 32) )
			else
				chase();
		}

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

			}
		} else {

			currentAction = IDLE;
			if (tempDirection == 0) {
				animation.setFrames(sprites.get(IDLE));
				// System.out.println("kkk");
			}
			if (tempDirection == 1)
				animation.setFrames(idleRightSprite);
			if (tempDirection == 2)
				animation.setFrames(idleLeftSprite);
			if (tempDirection == 3)
				animation.setFrames(idleDownSprite);
			if (tempDirection == 4)
				animation.setFrames(idleUpSprite);
			animation.setDelay(400);
			width = 30;

		}

		animation.update();
		if (isChasing) {
			up = false;
			down = false;
			left = false;
			right = false;
		}

	}

	public void look() {
		super.look();

	}
}
