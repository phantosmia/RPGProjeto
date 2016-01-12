package engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


import User.Player;
import graphics.Background;



public class Map1State extends GameState{
	public Player player;
	private Background bg;
	public Map1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		player = new Player();
		player.setPosition(100,100);
		bg = new Background("/Backgrounds/backgroundTBL.jpg",0.1);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		player.update();
		//bg.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		player.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(true);
		
		}
		
		if(k == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
		if(k == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(k == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(k == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(k == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		
	}

}
