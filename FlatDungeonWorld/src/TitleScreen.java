import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;

public class TitleScreen {

	boolean opened = false;
	int cursorLoc = 1;
	long time;
	Image splash, menuScreen;
	DungeonGame game;
	GameEngineV2 engine;
	String text[] = { "Created By: Willis Hand & Caleb Cook-Kollars",
			"Begin your quest!", "What am I playing anyway?", "Who are you?" };

	public TitleScreen(DungeonGame game, GameEngineV2 engine) {
		this.game = game;
		this.engine = engine;
		try {
			splash = ImageIO.read(new File("splash.png"));
			menuScreen = ImageIO.read(new File("startscreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = System.currentTimeMillis();
	}

	public void cursorUp() {
		cursorLoc--;
		if (cursorLoc < 1) {
			cursorLoc = text.length - 1;
		}	
	}

	public void cursorDown() {
		cursorLoc++;
		if (cursorLoc > text.length - 1) {
			cursorLoc = 1;
		}
	}

	public void chooseOption() {
		switch (cursorLoc) {
		case 1:
			game.goToLevel("Test");
			break;
		case 2:

			break;
		case 3:
			try {
				java.awt.Desktop.getDesktop().browse(
						new URI("http://www.signalytical.com/"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	public void draw(Graphics g) {

		if (System.currentTimeMillis() < time + 3500) {
			g.drawImage(splash, 0, 0, null);
			return;
		}

		g.drawImage(menuScreen, 0, 0, null);

		int stringWidth, count;

		Font mediumFont = new Font("SansSerif", Font.BOLD, 16);
		Font smallFont = new Font("SansSerif", Font.BOLD, 10);

		for (count = 0; count < text.length; count++) {

			g.setFont(mediumFont);
			g.setColor(Color.white);

			if (count == 0) {
				g.setFont(smallFont);
				g.drawString(text[count], 10, engine.height - 10);
			} else {
				if (cursorLoc == count) {
					g.setColor(Color.yellow);
				}
				stringWidth = g.getFontMetrics().stringWidth(text[count]);
				g.drawString(text[count], (engine.width - stringWidth) / 2,
						300 + count * 60);
			}
		}

		g.setColor(Color.red);
		stringWidth = g.getFontMetrics().stringWidth(text[cursorLoc]);
		g.drawRect((engine.width - (stringWidth + 5)) / 2,
				284 + cursorLoc * 60, stringWidth + 5, 20);

	}
}
