import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Kobold extends Creature {
	public Kobold(int locx, int locy) {
		super(locx, locy);
		try {
			rawSprite = ImageIO.read(new File("res/chars/kobold.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
