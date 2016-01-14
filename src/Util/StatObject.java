package Util;

import engine.MapObject;

public class StatObject  extends MapObject{
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
	public double getSpeed() {
	 return stats.getSpeed();
	}
	public void setMoveSpeed(double speed){
		stats.setMovieSpeed(speed);
	}
	public int getMaxHealth() {
		return stats.getMaxHealth();
	}
	public float getMagic() {
		return stats.getMagic();
	}
}