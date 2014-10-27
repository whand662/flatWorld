import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

public class MenuScreen {

	int cursorLoc = 1;
	BufferedImage splash, menuScreen;
	DungeonGame game;
	GameEngineV2 engine;

	public MenuScreen(DungeonGame game, GameEngineV2 engine) {
		this.game = game;
		this.engine = engine;
		try {
			menuScreen = ImageIO.read(new File("menuscreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cursorUp() {
		
	}

	public void cursorDown() {
		
	}
	
	public void cursorLeft(){
		
	}
	
	public void cursorRight(){
		
	}

	public void chooseOption() {
		switch (cursorLoc) {
		
		}
	}

	public void draw(Graphics g) {

		g.drawImage(menuScreen, 0, 0, null);

	}
}

