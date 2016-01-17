package Mobs;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


import Util.StatObject;
import engine.Delay;
import gameItens.Ability;
import gameItens.Inventario;

public abstract class Creature extends StatObject{
protected BufferedImage[] deadSprite;
protected BufferedImage[] idleRightSprite;
protected BufferedImage[] idleLeftSprite;
protected BufferedImage[] idleUpSprite;
protected BufferedImage[] idleDownSprite;
private int id = 0; //id da criatura

private String gender; // feminino, masculino ou neutro
private int idSpecie; //id da especie

private int speed; // velocidade

private boolean isHostile; // vai atacar o jogador ou nao
public boolean isDead = false; // esta morta
private String nameIndividual; // nome individual da criatura ou do personagem

public Class classe;
protected Color titleColor;
protected Font titleFont;
protected Delay attackDelay;
protected int damage;
protected float ATTACK_RANGE;
protected Font font;
private int xpGrantedIfKilled; //xp que dara para o oponente caso seja morto
private boolean canBeKilled; // se eh um npc importante para uma quest, nao pode ser morto
private List <Ability> abilities = new ArrayList();
private Inventario inventario;
public void update(){
	if(isDead == true)
		return;
	if (stats.getCurrentHealth() <= 0){
		die();
		isDead = true;
		animation.setFrames(deadSprite); 
	}
}

public void spawn(){
	//initialize
	
}
public void giveItem(){
	
}
public void die(){
	isDead = true;
	//replace image and make drop objects
	//grant XP for player.
}
public void regenerate(){
	
}
public void chase(){
	
}
public void look(){
	
}
}
