package flatWorld;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GameEngineV2;

public class MenuScreen {

	int cursorLoc = 0;
	BufferedImage splash, menuBackground;
	DungeonGame game;
	GameEngineV2 engine;

	//for item display
	int locx, locy, width = 640, height = 320;

	public MenuScreen(DungeonGame game, GameEngineV2 engine) {
		this.game = game;
		this.engine = engine;
		locy = (engine.height - height) / 2;
		locx = engine.width - width - locy;
		try {
			menuBackground = ImageIO.read(new File("res/pic/menuscreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cursorUp() {
		cursorLoc--;
		if(cursorLoc < 0){
			cursorLoc = 29;
		}
	}

	public void cursorDown() {
		cursorLoc++;
		if(cursorLoc > 29){
			cursorLoc = 0;
		}
	}

	public void cursorLeft(){
		if(cursorLoc >= 0 && cursorLoc <= 9){
			cursorLoc = 28;
		}else if(cursorLoc >= 10 && cursorLoc <= 19){
			cursorLoc -= 10;
		}else if(cursorLoc >= 20 && cursorLoc <= 22){
			cursorLoc = 19;
		}else if(cursorLoc >= 23 && cursorLoc <= 26){
			cursorLoc = 21;
		}else if(cursorLoc >= 27 && cursorLoc <= 29){
			cursorLoc = 24;
		}
	}

	public void cursorRight(){
		if(cursorLoc >= 0 && cursorLoc <= 9){
			cursorLoc += 10;
		}else if(cursorLoc >= 10 && cursorLoc <= 19){
			cursorLoc = 21;
		}else if(cursorLoc >= 20 && cursorLoc <= 22){
			cursorLoc = 24;
		}else if(cursorLoc >= 23 && cursorLoc <= 26){
			cursorLoc = 28;
		}else if(cursorLoc >= 27 && cursorLoc <= 29){
			cursorLoc = 0;
		}
	}

	public void chooseOption() {
		switch (cursorLoc) {

		}
	}

	public void draw(Graphics g) {

		g.drawImage(menuBackground, 0, 0, null);
		drawItemDisplay(g);
		drawInventory(g);

	}

	private void drawInventory(Graphics g){
		int count = 0;
		Font itemfont = new Font("SansSerif", Font.BOLD, height/20);
		g.setColor(Color.BLACK);
		g.setFont(itemfont);
		for(int i = 0; i < game.player.inventory.getSize(); i++){
			if(count < game.player.inventory.getSize()/2){
				if(game.player.inventory.whatsHere(count) != null){
					g.drawString(game.player.inventory.whatsHere(count).getName(), locx + 10, locy + (height/10) * count + height/15);
					g.drawString(Integer.toString(game.player.inventory.whatsHere(count).getWeight()), locx + (width/2) - 25, locy + (height/10) * count + height/15);
				}
			}else{
				if(game.player.inventory.whatsHere(count) != null){
					g.drawString(game.player.inventory.whatsHere(count).getName(), locx + width/2 + 10, locy + (height/10) * (count - 10) + height/15);
					g.drawString(Integer.toString(game.player.inventory.whatsHere(count).getWeight()), locx + width - 25, locy + (height/10) * (count - 10) + height/15);
				}
			}
			count++;
		}

	}

	private void drawItemDisplay(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(locx, locy, width, height);
		g.drawRect(locx + (width / 2) - 30, locy, 30, height);
		g.drawRect(locx + width - 30, locy, 30, height);
		for(int count = 1; count < 10; count++){
			g.drawLine(locx, locy + (height/10) * count, locx + width, locy + (height/10) * count);
		}
	}
}
