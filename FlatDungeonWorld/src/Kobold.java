import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Kobold extends Creature {
	static String spriteFile = "res/chars/kobold.png";
	public Kobold(int locx, int locy) {
		super(locx, locy, spriteFile);
	}
}
