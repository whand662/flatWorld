import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	BufferedImage swordRaw;
	BufferedImage swordPrep;	
	static String spriteFile = "res/chars/char1.gif";
	//just for testing, will change location
	
	public Player(int locx, int locy) {
		super(locx, locy, spriteFile);
		size = 10;
		try {
			swordRaw = ImageIO.read(new File("res/chars/sword1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		inventory = new Inventory(20);
		//Attribute order is STR END AGL INT WIS LCK
		stats = new Attributes(new int[]{5,5,5,5,5,5});
	}
	
	public void tick(Map currentMap){
		inventory.tick(this);
		
		stats.tick(inventory.getWeight());
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
	
	public void clearInventory(){
		inventory.fullReset();
	}

}
