package monster;
import java.awt.image.BufferedImage;


public class Kobold extends Creature {
	static String spriteFile = "res/chars/kobold.png";
	static BufferedImage koboldSprite;
	public Kobold(int locx, int locy) {
		super(locx, locy, spriteFile);
	}
	@Override
	public BufferedImage getSprite(){
		return koboldSprite;
	}
	@Override
	public void setSprite(BufferedImage sprite){
		koboldSprite = sprite;
	}
}
