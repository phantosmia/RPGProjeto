package engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Enemies.Enemy;
import Enemies.Orc_Low_Level;
import User.Player;
import graphics.Background;



public class Map1State extends GameState{
	public Player player;
	private ArrayList<Enemy> enemies;
	private Background bg;
	public GamePanel gamePanel;
	public Map1State(GameStateManager gsm, GamePanel gamePanel) {
		this.gsm = gsm;
		
	
		this.gamePanel = gamePanel;
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Orc_Low_Level littleOrc;
		littleOrc = new Orc_Low_Level(0);
		enemies = new ArrayList<Enemy>();
		player = new Player();
		player.setPosition(100,100);
		littleOrc.setPosition(300, 100);
		gamePanel.addObject(littleOrc);
		gamePanel.addObject(player);
		enemies.add(littleOrc);
		bg = new Background("/Backgrounds/backgroundTBL.jpg",0.1);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		player.update();
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.update();
			
			}
		
	}
		//bg.update();
		
	

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		player.draw(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(player.isDead == false){
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(true);
			player.setMoving(true);
			
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(true);
			player.setMoving(true);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(true);
			player.setMoving(true);
		}
		
		if(k == KeyEvent.VK_DOWN){
			player.setDown(true);
			player.setMoving(true);
		}
		if(k == KeyEvent.VK_SPACE){
			player.attack();
		}
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(false);
			player.setMoving(false);
			
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(false);
			player.setMoving(false);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(false);
			player.setMoving(false);
		}
		if(k == KeyEvent.VK_DOWN){
			player.setDown(false);
			player.setMoving(false);
		}
		
	}

}
