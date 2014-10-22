import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonGame extends Application implements Game{
	
	public enum Gamestate{TITLE, GAME, MENU, DEAD, WARP};
	GameEngineV2 engine;
	static Gamestate GS;
	TitleScreen titleScreen;
	Map currentWorld;
	int loadCount = 0;
	int x = 200, y = 500; //dummy values for testing
	
	public static void main(String args[])
	{
		launch(args);
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
		playSound("Kalimba.mp3");
	}
	
	public void goToLevel(String levelName){
		GS = Gamestate.WARP;
		currentWorld = new Map(levelName);
		loadCount = 400;
	}
	
	private void drawLevelScreen(Graphics g){
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString(currentWorld.getMapName(), (engine.width - g.getFontMetrics().stringWidth(currentWorld.getMapName()))/2, engine.height/2);
	}

	public void drawFrame(Graphics g) {
		switch(GS){
		case TITLE: //startup screen
			titleScreen.draw(g);
			break;
		case GAME: //walking maps
			currentWorld.draw(g, this);
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
			loadCount--;
			if(loadCount <=0){
				GS = Gamestate.GAME;
			}
			break;
		}
		
	}
	
	public void playSound(String fileName){
		File audioFile = new File("res/audio/"+fileName);
		try {
			Media clip = new Media(audioFile.toURI().toString());
			MediaPlayer clipPlayer = new MediaPlayer(clip);
			clipPlayer.play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// Application initialization function
	}

}
