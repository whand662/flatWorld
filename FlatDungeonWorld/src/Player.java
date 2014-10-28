import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Creature {
	
	Item inventory[];
	boolean wieldingSword = false;
	public Player(int locx, int locy) {
		super(locx, locy);
		speed = 2;
		size = 10;
		try {
			sprite  = ImageIO.read(new File("res/chars/char1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		inventory = new Item[30];
		updateSprite();
	}
	
	public void draw(Graphics g, int xOffset, int yOffset){
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(xOffset, yOffset);
		g2d.drawImage(sprite, at, null);
	}
	protected void updateSprite(){
		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
		at = new AffineTransform();

		// 4. resize component
		at.scale(.2, .2);
		
		// 2. do the actual rotation
		at.rotate(Math.PI*facing.theta);

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))
		at.translate(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	
	public int give(Item tempItem){
		return 0;
	}
	public void setSword(boolean state){
		wieldingSword = state;
	}
}
