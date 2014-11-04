import java.awt.Graphics;
import java.awt.Graphics2D;
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
	//just for testing, will change location
	int maxSpeed;
	int maxCarryWeight = 150;
	
	public Player(int locx, int locy) {
		super(locx, locy);
		maxSpeed = 9;
		size = 10;
		try {
			rawSprite  = ImageIO.read(new File("res/chars/char1.gif"));
			swordRaw = ImageIO.read(new File("res/chars/sword1.gif"));
			updateSprite();
		} catch (IOException e) {
			e.printStackTrace();
		}
		inventory = new Inventory(20);
	}
	
	public void tickPlayer(){
		inventory.tickInventory(this);
		speed = updateSpeed();
	}
	
	public int updateSpeed(){
		
		if(inventory.getInventoryWeight() > maxCarryWeight){
			return 0;
		}
		if((int)Math.round(maxSpeed * (1 - ((double)inventory.getInventoryWeight()/(double)maxCarryWeight))) == 0){
			return 1;
		}
		return (int)Math.round(maxSpeed * (1 - ((double)inventory.getInventoryWeight()/(double)maxCarryWeight)));
	}
	
	public void draw(Graphics g, int xOffset, int yOffset){
		if(preparedSprite != null)
			g.drawImage(preparedSprite, xOffset-preparedSprite.getWidth()/2, yOffset-preparedSprite.getHeight()/2, null);
//		g.drawImage(swordPrep, xOffset, yOffset, null);
	}

	/**
	 * See			http://stackoverflow.com/questions/4918482/rotating-bufferedimage-instances
	 * @return at	a prepared AffineTransform for drawing
	 */
	protected void updateSprite(){
		if(rawSprite == null){
			return;
		}
		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
		AffineTransform at = new AffineTransform();
		
		// 4.
		at.scale(.2, .2);
		
		// 3. put the component back to 0,0
		at.translate(rawSprite.getWidth()/2, rawSprite.getHeight()/2);
		
		// 2. do the actual rotation
		at.rotate(Math.PI*heading);

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))
		at.translate(-rawSprite.getWidth()/2, -rawSprite.getHeight()/2);

		AffineTransformOp atOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		preparedSprite = atOp.filter(rawSprite, preparedSprite);
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
