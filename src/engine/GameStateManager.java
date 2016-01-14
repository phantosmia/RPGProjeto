package engine;



public class GameStateManager {
	private GameState[] gameStates;
	public static final int NUMGAMESTATES = 2;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public GamePanel gamePanel;
	public GameStateManager(GamePanel gamePanel){
		gameStates = new GameState[NUMGAMESTATES];
		currentState = MENUSTATE;
		loadState(currentState);
		this.gamePanel = gamePanel;
		//System.out.println(this.gamePanel);
		//gameStates.add(new MenuState(this));
		//gameStates.add(new Level1State(this));
		
	}
	private void loadState(int state){
		if(state == MENUSTATE){
			gameStates[state] = new MenuState(this);
		}
		if(state == LEVEL1STATE){
			gameStates[state] = new Map1State(this, gamePanel);
		}
		
	}
	private void unloadState(int state){
		gameStates[state] = null;
	}
	public void setState(int state){
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	public void update(){
		try{
		gameStates[currentState].update();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}
	public void draw(java.awt.Graphics2D g){
		try{
		gameStates[currentState].draw(g);
		}
		catch(Exception e){
			
		}
		
	}
	public void keyPressed(int k){
		gameStates[currentState].keyPressed(k);
	}
	public void keyReleased(int k){
		gameStates[currentState].keyReleased(k);
	}
}
