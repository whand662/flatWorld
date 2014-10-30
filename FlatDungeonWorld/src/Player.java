import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Creature {
	
	Inventory inventory;
	
	boolean wieldingSword = false;
	double swordTheta = 1.5;
	int swordx = 0;
	int swordy = 0;
	BufferedImage swordImg;
	AffineTransform st;
	
	public Player(int locx, int locy) {
		super(locx, locy);
		speed = 2;
		size = 10;
		try {
			sprite  = ImageIO.read(new File("res/chars/char1.gif"));
			swordImg = ImageIO.read(new File("res/chars/sword1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		inventory = new Inventory(20);
		updateSprite();
	}
	
	public void tickPlayer(){
		inventory.tickInventory(this);
	}
	
	public void draw(Graphics g, int xOffset, int yOffset){
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(xOffset, yOffset);
		g2d.drawImage(sprite, at, null);
		g2d.drawImage(swordImg, st, null);
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
		
		 st = new AffineTransform();
		
		st.scale(.5, .5);		
		st.rotate(facing.theta*Math.PI);
		st.translate(swordImg.getWidth()/4, -swordImg.getHeight()/4);
	}
	
	public int getPayment(int qty){
		return inventory.removeGold(qty);
	}
	
	public void giveGold(int qty){
		inventory.storeGold(qty);
	}
	
	public int giveItem(Item tempItem){
		return inventory.storeItem(tempItem);
	}
	
	public void setSword(boolean state){
		wieldingSword = state;
	}
}
