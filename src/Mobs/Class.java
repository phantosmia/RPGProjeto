package Mobs;

public enum Class {
	KNIGHT, MAGE, ARCHER;
	public String toString(){
		switch(this){
		case KNIGHT: return "Knight";
		case MAGE: return "Mage";
		case ARCHER: return "Archer";
		default: return "None";
		}
	}
}
