import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DungeonGame implements Game{
	
	public enum Gamestate{TITLE, GAME, MENU, DEAD, WARP};
	GameEngineV2 engine;
	static Gamestate GS;
	TitleScreen titleScreen;
	Map currentWorld;
	int test;
	
	public static void main(String args[])
	{
		new DungeonGame();
	}
	
	public DungeonGame()
	{
		engine = new GameEngineV2(this);
		engine.setWindow("Dungeon Game", 1280, 720, 10);
		
		//define startup variables
		GS = Gamestate.TITLE;
		titleScreen = new TitleScreen(this, engine);
		
		engine.start();
	}
	
	public void goToLevel(String levelName){
		GS = Gamestate.WARP;
		currentWorld = new Map(levelName);
	}
	
	private void drawLevelScreen(Graphics g){
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.setColor(Color.white);
		g.drawString(currentWorld.getMapName(), (engine.width - g.getFontMetrics().stringWidth(currentWorld.getMapName()))/2, engine.height/2);
	}

	public void drawFrame(Graphics g) {
		switch(GS){
		case TITLE: //startup screen
			titleScreen.draw(g);
			break;
		case GAME: //walking maps
			
			break;
		case MENU: //menu interaction
			
			break;
		case DEAD: //death screen
			
			break;
		case WARP: //changing maps
			drawLevelScreen(g);
			break;
		}
		
	}

	public void processFrame() {
		switch(GS){
		case TITLE: //startup screen
			if(engine.upArrowPressed){
				titleScreen.cursorUp();
			}
			if(engine.downArrowPressed){
				titleScreen.cursorDown(); 
			}
			if(engine.enterPressed){
				try {
					titleScreen.chooseOption();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case GAME: //walking maps
			
			break;
		case MENU: //menu interaction
			
			break;
		case DEAD: //death screen
			
			break;
		case WARP: //changing maps
			
			break;
		}
		
	}

}
