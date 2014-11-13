package flatWorld;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public enum EnvTile {
	undefined	(false, 'u'),
	stairup 	(true,	'<'),
	stairdown 	(true,	'>'),
	tree		(false, 't', "tree1.png")
	;
	
	boolean walkable;
	Character tileAbbr;
	private WarpInstructions warpData;
	static HashMap<Character, EnvTile> dict = new HashMap<Character, EnvTile>();
	BufferedImage img = null;
	static AffineTransform at;
	
	static{
		for(EnvTile tile: EnvTile.values()){
			dict.put(tile.tileAbbr, tile);
		}			
	}

	private EnvTile(boolean walkOn, Character abbreviation, String imgName){
		walkable = walkOn;
		tileAbbr = abbreviation;
		try {
			img = ImageIO.read(new File("res/tiles/" + imgName));
		} catch (IOException e) {
			e.printStackTrace();
			//Image didn't open. Try to get undefined.png
			try {
				img = ImageIO.read(new File("res/tiles/undefined.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	private EnvTile(boolean walkOn, Character abbreviation){
		walkable = walkOn;
		tileAbbr = abbreviation;
		try {
			img = ImageIO.read(new File("res/tiles/" + this.name() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			//Image didn't open. Try to get undefined.png
			try {
				img = ImageIO.read(new File("res/tiles/undefined.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static EnvTile get(Character ch){
		EnvTile temp = dict.get(ch);
		if (temp == null){
			return EnvTile.undefined;
		}
		return temp;
	}
	
	public void setWarpInfo(String map, int x, int y){
		warpData = new WarpInstructions(map, x, y);
	}
	
	public WarpInstructions getWarpInfo(){
		return warpData;
	}

	public void draw(Graphics g, int x, int y, int width, int height){
		if(img == null)
			return;
		at = new AffineTransform();
		at.translate(x, y);
		if (img.getWidth() != width) {
			at.scale((double)width/(double)img.getWidth(), 1);
		}
		if (img.getHeight() != height) {
			at.scale(1, height/(double)img.getHeight());
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, at, null);
	}
	
	public boolean walkable(){
		return walkable;
	}
}
