import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import Core.Game;
import Core.GameEngineV2;


/**
 * @author ayrix
 * ##+++==== HEY WILLIS TRY alt+shift+j while clicking on a function declaration
 */
public class DungeonGame implements Game {
	
	/**
	 * @author ayrix
	 * Overall gamestate enumerator
	 */
	public enum Gamestate {
		TITLE, GAME, MENU, DEAD, WARP
	};


	GameEngineV2 engine;
	static Gamestate GS;
	TitleScreen titleScreen;
	MenuScreen menuScreen;
	DungeonLib library;
	Player player;
	Map currentWorld;
	int xOffset, yOffset;
	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	int loadCount = 0;
	AudioClip ac;
	private boolean GODMODE = true;
	
	public static void main(String args[]) {
		new DungeonGame();
	}

	public DungeonGame() {
		engine = new GameEngineV2(this);
		engine.setWindow("Dungeon Game", WIDTH, HEIGHT, 10);

		// define startup variables
		GS = Gamestate.TITLE;
		titleScreen = new TitleScreen(this, engine);
		menuScreen = new MenuScreen(this, engine);
		library = new DungeonLib();
		player = new Player(160, 200);

		engine.start();
		playSound("odd1.wav");
	}

	public void goToLevel(String levelName) {
		GS = Gamestate.WARP;
		currentWorld = new Map(levelName);
		loadCount = 50;
	}

	private void drawLevelScreen(Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString(currentWorld.getMapName(), (engine.width - g
				.getFontMetrics().stringWidth(currentWorld.getMapName())) / 2,
				engine.height / 2);
	}

	public void drawFrame(Graphics g) {
		switch (GS) {
		case TITLE: // startup screen
			titleScreen.draw(g);
			break;
		case GAME: //walking maps
			xOffset = player.getX();
			yOffset = player.getY();
			currentWorld.draw(g, engine.width/2-xOffset, engine.height/2-yOffset);
			player.draw(g, engine.width/2, engine.height/2);
			break;
		case MENU: // menu interaction
			menuScreen.draw(g);
			break;
		case DEAD: // death screen

			break;
		case WARP: // changing maps
			drawLevelScreen(g);
			break;
		}

	}
	
	public void processGame(){
		
		if(GODMODE){
			if(engine.getKey(103) == 1){
				engine.unflagKey(103);
				player.giveItem(new Weapon(library.superior, library.iron, library.mace, "", true));
			}
			if(engine.getKey(104) == 1){
				engine.unflagKey(104);
				player.giveItem(new Armor(library.inferior, library.starmetal, library.circlet, "", false));
			}
			if(engine.getKey(114) == 1){
				engine.unflagKey(114);
				player.giveItem(library.getItem("weapon", 0));
			}
		}

		if(engine.getKey(105) == 1){
			engine.unflagKey(105);
			GS = Gamestate.MENU;
		}
		player.update();
		player.setFacing(engine.getFacing());
		if(engine.getKey(UP) > 0){
			player.moveUp(currentWorld);
		}
		if(engine.getKey(LEFT) > 0){
			player.moveLeft(currentWorld);
		}
		if(engine.getKey(RIGHT) > 0){
			player.moveRight(currentWorld);
		}
		if(engine.getKey(DOWN) > 0){
			player.moveDown(currentWorld);
		}
		
		player.tickPlayer();
		
	}

	public void processFrame() {
		switch (GS) {
		
		case TITLE: // startup screen
			if (engine.getKey(UP) == 1) {
				engine.unflagKey(UP);
				titleScreen.cursorUp();
			}
			if (engine.getKey(DOWN) == 1) {
				engine.unflagKey(DOWN);
				titleScreen.cursorDown();
			}
			if (engine.getKey(ENTER) == 1) {
				try {
					engine.unflagKey(ENTER);
					titleScreen.chooseOption();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
			
		case GAME: // walking maps
			processGame();
			break;
			
		case MENU: // menu interaction
			if(engine.getKey(105) == 1){
				engine.unflagKey(105);
				GS = Gamestate.GAME;
			}
			break;
		case DEAD: // death screen

			break;
		case WARP: //changing maps
			loadCount--;
			if(loadCount <=0){
				GS = Gamestate.GAME;
			}
			break;
		}

	}

	public void playSound(String fileName) {
		try {
			URL audioFile = new URL("file:res/audio/" + fileName);
			ac = Applet.newAudioClip(audioFile);
			ac.play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
