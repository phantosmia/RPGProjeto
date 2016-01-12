package Mobs;

import java.util.ArrayList;
import java.util.List;


import Util.StatObject;
import gameItens.Ability;
import gameItens.Inventario;

public abstract class Creature extends StatObject{

private int id = 0; //id da criatura

private String gender; // feminino, masculino ou neutro
private int idSpecie; //id da especie

private int speed; // velocidade

private boolean isHostile; // vai atacar o jogador ou nao
private boolean isDead = false; // esta morta
private String nameIndividual; // nome individual da criatura ou do personagem

public Class classe;

private int xpGrantedIfKilled; //xp que dara para o oponente caso seja morto
private boolean canBeKilled; // se eh um npc importante para uma quest, nao pode ser morto
private List <Ability> abilities = new ArrayList();
private Inventario inventario;


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
