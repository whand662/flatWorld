package flatWorld;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import flatWorld.Area;
import flatWorld.Map;
import flatWorld.Item;


public class Player extends Creature {

	public Inventory inventory;

	boolean wieldingSword = false;
	double swordTheta = 1.5;
	int swordx = 0;
	int swordy = 0;
	BufferedImage swordRaw;
	BufferedImage swordPrep;
	static String spriteFile = "res/chars/char1.gif";
	private String race, profession;

	public Player(int locx, int locy, String r, String p) {
		super(locx, locy, spriteFile);
		race = r;
		profession = p;
		size = 10;
		try {
			swordRaw = ImageIO.read(new File("res/chars/sword1.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initializePlayerInfo();

	}

	public String getProfession(){
		return profession;
	}

	public String getRace(){
		return race;
	}

	public void tick(Area currentWorld){
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

	private void initializePlayerInfo(){
		inventory = new Inventory(20);
		//Attribute order is STR END AGL INT WIS LCK


		switch(race){
			case "Human":
				//line below will be phased out once attribute system is updated
				stats = new Attributes(new int[]{5,5,5,5,5,5});
				break;
		}
	}
}
