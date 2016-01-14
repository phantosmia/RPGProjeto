package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Util.Util;




public class GamePanel extends JPanel implements Runnable, KeyListener{
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	private Thread thread;
	public static GamePanel game;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	private BufferedImage image;
	private Graphics2D g;
	private GameStateManager gsm;
	public static ArrayList<MapObject> sphereCollide(float x, float y, float radius) {
		ArrayList<MapObject> res = new ArrayList<MapObject>();
		for (MapObject go : game.getObjects()) {
			if (Util.dist((float)go.getX(), (float)go.getY(), x, y) < radius)
				res.add(go);
		}
		return res;
	}

	private ArrayList<MapObject> objects;
	public GamePanel(){
		super();
		game = this;
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		objects = new ArrayList<MapObject>();
		
	}
	public void addObject(MapObject a){
		objects.add(a);
	}
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	private void init(){
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager(game);
		System.out.println(objects);
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		gsm.keyPressed(arg0.getKeyCode());
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		gsm.keyReleased(arg0.getKeyCode());
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		long start;
		long elapsed;
		long wait;
		while(running){
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed =  System.nanoTime() - start;
			wait = targetTime  - elapsed / 1000000;
			if(wait < 0){
				wait = 5;
			}
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private void update(){
		gsm.update();
	}
	private void draw(){
		gsm.draw(g);
	}
	private void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}
	public ArrayList<MapObject> getObjects() {
		return objects;
	}
}
