import java.applet.Applet;
import java.applet.AudioClip;
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

	AudioClip splashClip, MenuClip;
	int cursorLoc = 1, waitTime = 0, webWait = 0;
	long time;
	Image splash, menuScreen;
	Game game;
	GameEngineV2 engine;
	String text[] = {"Created By: Willis Hand & Caleb Cook-Kollars", "Begin your quest!", "What am I playing anyway?", "Who are you?"};

	public TitleScreen(Game game, GameEngineV2 engine) {
		this.game = game;
		this.engine = engine;
		try {
			splash = ImageIO.read(new File("splash.png"));
			menuScreen = ImageIO.read(new File("startscreen.png"));
			//splashClip = Applet.newAudioClip(this.getClass().getResource("splashsound.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		time = System.currentTimeMillis();
		//splashClip.play();
	}

	private void resetWait(){
		waitTime = 20;
	}
	
	private void visitWeb(){
		webWait = 30;
	}

	public void cursorUp(){	
		if(waitTime <= 0){
			cursorLoc--;
			if(cursorLoc < 1){
				cursorLoc = text.length - 1;
			}
			resetWait();
		}
	}

	public void cursorDown(){
		if(waitTime <= 0){
			cursorLoc++;
			if(cursorLoc > text.length - 1){
				cursorLoc = 1;
			}
			resetWait();
		}
	}

	public void chooseOption(){
		switch(cursorLoc){
		case 1:

			break;
		case 2:

			break;
		case 3:
			if(webWait<=0){
				visitWeb();
				try {
					java.awt.Desktop.getDesktop().browse(new URI("http://www.signalytical.com/"));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

	public void draw(Graphics g){

		if(System.currentTimeMillis() < time + 3500){
			g.drawImage(splash, 0, 0, null);
			return;
		}

		if(waitTime > 0){
			waitTime--;
		}
		if(webWait > 0){
			webWait--;
		}

		g.drawImage(menuScreen, 0, 0, null);

		int stringWidth, count;

		Font mediumFont = new Font("SansSerif", Font.BOLD, 16);
		Font smallFont = new Font("SansSerif", Font.BOLD, 10);

		for(count = 0; count < text.length; count++){

			g.setFont(mediumFont);
			g.setColor(Color.white);

			if(count == 0){
				g.setFont(smallFont);
				g.drawString(text[count], 10, engine.height - 10);
			}else{
				if(cursorLoc == count){
					g.setColor(Color.yellow);
				}
				stringWidth = g.getFontMetrics().stringWidth(text[count]);
				g.drawString(text[count], (engine.width - stringWidth)/2, 300 + count*60);
			}
		}

		g.setColor(Color.red);
		stringWidth = g.getFontMetrics().stringWidth(text[cursorLoc]);
		g.drawRect((engine.width - (stringWidth+5))/2, 284 + cursorLoc * 60 , stringWidth + 5, 20);		

	}
}
