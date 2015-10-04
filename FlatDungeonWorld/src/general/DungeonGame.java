package general;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;

import core.Game;
import core.GameEngineV2;
import monster.InformationBar;
import monster.Player;
import flatWorld.Area;
import flatWorld.Map;
import flatWorld.WarpInstructions;


/**
 * @author ayrix, among others...
 */
public class DungeonGame implements Game {

	/**
	 * @author ayrix
	 * Overall gamestate enumerator
	 */
	public enum Gamestate {
		TITLE, GAME, MENU, DEAD, WARP, SETUP
	};


	GameEngineV2 engine;
	static Gamestate GS;
	TitleScreen titleScreen;
	MenuScreen menuScreen;
	InformationBar hud;
	DungeonLib library;
	Player player;
	Area area;
	int xOffset, yOffset;
	private final int WIDTH = 1280;
	private final int HEIGHT = 720;
	int loadCount = 0, moveCount = 0;
	AudioClip ac;
	private boolean GODMODE = true;
	private String currentWorldName;

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
		
		//will be moved once GS SETUP is more developed
		player = new Player(160, 200);
		hud = new InformationBar(player);

		engine.start();
		playSound("odd1.wav");
	}

	public void goToLevel(WarpInstructions toLevel) {
		GS = Gamestate.WARP;
		currentWorldName = toLevel.getTeleName();
		area = new Area(currentWorldName);
		player.x = toLevel.getTeleX() * area.activeMap.TILEWIDTH;
		player.y = toLevel.getTeleY() * area.activeMap.TILEWIDTH;
		loadCount = 50;
	}

	private void drawLevelScreen(Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString(area.getMapName(), (engine.width - g
				.getFontMetrics().stringWidth(area.getMapName())) / 2,
				engine.height / 2);
	}

	public void drawFrame(Graphics g) {
		switch (GS) {
		case TITLE: // startup screen
			titleScreen.draw(g);
			break;
		case GAME: //walking maps
			xOffset = (int)player.getX();
			yOffset = (int)player.getY();
			area.draw(g, engine.width/2-xOffset, engine.height/2-yOffset);
			player.draw(g, engine.width/2-xOffset, engine.height/2-yOffset);
			hud.draw(g);
			break;
		case MENU: // menu interaction
			menuScreen.draw(g);
			break;
		case DEAD: // death screen
			g.setColor(Color.white);
			g.setFont(new Font("SansSerif", Font.BOLD, 100));
			g.drawString("YOU ARE DEAD", 300, 300);
			break;
		case WARP: // changing maps
			drawLevelScreen(g);
			break;
		case SETUP:
			//add char naming and init stat setup screen
			break;
		default:
			break;
		}

	}

	public void tickGame(){

		//GODMODE for testing only
		if(GODMODE){
			//gives mace weapon to player
			if(engine.getKey((int)'g') == 1){
				engine.unflagKey((int)'g');
				player.giveItem(new Weapon(library.superior, library.iron, library.mace, "", true));
			}
			//gives helmet to player
			if(engine.getKey((int)'h') == 1){
				engine.unflagKey((int)'h');
				player.giveItem(new Armor(library.inferior, library.starmetal, library.circlet, "", false));
			}
			//gives player a random weapon
			if(engine.getKey((int)'r') == 1){
				engine.unflagKey((int)'r');
				player.giveItem(library.getItem("weapon", 0));
			}
			//performs full reset of inventory (resets gold, items, and equipment)
			if(engine.getKey((int)'o') == 1){
				engine.unflagKey((int)'o');
				player.clearInventory();
			}
			//physical attack vs player
			if(engine.getKey((int)'p') == 1){
				engine.unflagKey((int)'p');
				player.takeAttack(new AttackBox(10, 'p', 'n'));
				System.out.println(player.stats.health);
			}
			//magical attack vs player
			if(engine.getKey((int)'m') == 1){
				engine.unflagKey((int)'m');
				player.takeAttack(new AttackBox(10, 'm', 'n'));
				System.out.println(player.stats.health);
			}
			//true dmg attack vs player
			if(engine.getKey((int)'t') == 1){
				engine.unflagKey((int)'t');
				player.takeAttack(new AttackBox(10, 't', 'n'));
				System.out.println(player.stats.health);
			}
			//fully heals player
			if(engine.getKey((int)'f') == 1){
				engine.unflagKey((int)'f');
				player.stats.fullHeal();
				System.out.println(player.stats.health);
			}
			//places a kobold at the current player position
			if(engine.getKey((int)'k') == 1){
				engine.unflagKey((int)'k');
				area.createKobold((int)player.x, (int)player.y);
			}
			//kills all creatures
			if(engine.getKey((int)'l') == 1){
				engine.unflagKey((int)'l');
				area.killEverything();
			}
		}

		if(engine.getKey((int)'i') == 1){
			engine.unflagKey((int)'i');
			GS = Gamestate.MENU;
		}
		
		if(engine.getKey((int)'w') == 1){
			player.inventory.action('w');
		}
		
		if(engine.getKey((int)'s') == 1){
			player.inventory.action('s');
		}
		
		if(engine.getKey((int)'a') == 1){
			player.inventory.action('a');
		}
		
		if(engine.getKey((int)'d') == 1){
			player.inventory.action('d');
		}
		
		

		//moveCount system allows finer speed control by allowing movement in fewer frames, set if statement to disable
		moveCount++;		
		if(moveCount >= 0){
			moveCount = 0;

			if(engine.getKey(UP) > 0){
				player.moveUp(area);
			}
			if(engine.getKey(LEFT) > 0){
				player.moveLeft(area);
			}
			if(engine.getKey(RIGHT) > 0){
				player.moveRight(area);
			}
			if(engine.getKey(DOWN) > 0){
				player.moveDown(area);
			}

			//move other creatures here
		}

		player.setFacing(engine.getFacing());
		player.tick(area);
		
		if(player.stats.dead == true){
			GS = Gamestate.DEAD;
			return;
		}
		
		WarpInstructions mapWarpEvent = area.tick(player);
		if(mapWarpEvent != null){
			goToLevel(mapWarpEvent);
		}

	}

	public void processFrame() {
		switch (GS) {

		case SETUP:
			goToLevel(new WarpInstructions("test", 4, 5));
			break;
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
			tickGame();
			break;

		case MENU: // menu interaction
			if(engine.getKey(105) == 1){
				engine.unflagKey(105);
				GS = Gamestate.GAME;
			}
			if (engine.getKey(UP) == 1) {
				engine.unflagKey(UP);
				menuScreen.cursorUp();
			}
			if (engine.getKey(DOWN) == 1) {
				engine.unflagKey(DOWN);
				menuScreen.cursorDown();
			}
			if (engine.getKey(LEFT) == 1) {
				engine.unflagKey(LEFT);
				menuScreen.cursorLeft();
			}
			if (engine.getKey(RIGHT) == 1) {
				engine.unflagKey(RIGHT);
				menuScreen.cursorRight();
			}
			if (engine.getKey(ENTER) == 1) {
				engine.unflagKey(ENTER);
				menuScreen.chooseOption();
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
	
	public void startSetup(){
		GS = Gamestate.SETUP;
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
