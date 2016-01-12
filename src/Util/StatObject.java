package Util;



public class StatObject  {
	protected Stats stats;
	public void damage(int amt){
		stats.damage(amt);
	}
	public float getStrength() {
		return stats.getStrength();
	}
	public int getCurrentHealth() {
		return stats.getCurrentHealth();
	}
	public int getLevel() {
	 return stats.getLevel();
	}
	public float getSpeed() {
	 return stats.getSpeed();
	}
	public int getMaxHealth() {
		return stats.getMaxHealth();
	}
	public float getMagic() {
		return stats.getMagic();
	}
}