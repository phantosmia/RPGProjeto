package Util;



import User.StatScale;

public class Stats {
	private float xp;
	private int health;
	private int level;
	private boolean levelable;
	public static final int MAX_LEVEL = 99;
	public static final  int MAX_XP = 999999;
	private StatScale scale;
	public static final double LEVEL_CONST = (double)MAX_XP/((double)MAX_LEVEL * (double)MAX_LEVEL);
//	public static final double LEVEL_CONST = 25.0 * Math.pow(3.0, (3.0 / 2.0));
	public Stats(float xp, boolean levelable){
		this.levelable = levelable;
		scale = new StatScale();
		scale.generateStatScale();
		
		if(levelable){
			this.xp = xp;
			this.level = 1;
			}
		else{
			this.xp = -1;
			this.level = (int)1;
			}
		this.health = getMaxHealth();
	}
	public float getStrength() {
		return (float)(getLevel()  * scale.getScale(StatScale.STRENGTH)  * 10);
	}

	public float getMagic() {
		return (float)(getLevel()  * scale.getScale(StatScale.MAGIC) *  10);
	}
	public float getPhysicalDefense() {
		return (float)(getLevel()  * scale.getScale(StatScale.PHYSICALDEFENSE) * 10);
	}
	public float getMagicDefense() {
		return (float)(getLevel() * scale.getScale(StatScale.MAGICDEFENSE)  * 10);
	}
	public int getMaxHealth() {
		//return 10;
		return (int)(getLevel() * scale.getScale(StatScale.VITALITY) * 10);
	}
	public float getSpeed() {
		return 4f;
		//return (float)(getLevel() * (float)(scale.getScale(StatScale.SPEED)));
	}
	public int getCurrentHealth() {
		int max = getMaxHealth();
		if (health > max)
			health = max;

		return health;
	}
	public int getLevel() {
		if(!levelable)
			return level;
		return (int)Math.sqrt((double)xp/LEVEL_CONST) + 1;
		
		/*double x = xp + 105.0;
		double a = Math.sqrt(243.0 * (x * x) + 4050.0 * x + 17500.0);
		double c = (3.0 * x + 25.0) / 25.0;
		double d = Math.cbrt(a / LEVEL_CONST + c);

		return (int) (d - 1.0 / d * 3.0) - 1; */
	}

	public void addXp(float amt) {
		xp += amt;
		if(xp > MAX_XP)
			xp = MAX_XP;
	}
	public void damage(int amt){
		health -= amt;
	}
}

