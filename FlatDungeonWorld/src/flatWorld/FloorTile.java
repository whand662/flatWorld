package flatWorld;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public enum FloorTile {
	undefined 	(false,	'u'),
	dirt 		(true,	'd'),
	water 		(false,	'w'),
	lava 		(false,	'l'),
	lightGrass 	(true,	'g'),
	dirtyGrass 	(true,	'i'),
	;

	boolean walkable;
	Character tileAbbr;
	static HashMap<Character, FloorTile> dict = new HashMap<Character, FloorTile>();
	BufferedImage img = null;
	AffineTransform at;

	static{
		for(FloorTile tile: FloorTile.values()){
			dict.put(tile.tileAbbr, tile);
		}
	}

	private FloorTile(boolean walkOn, Character abbreviation, String imgName){
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
	private FloorTile(boolean walkOn, Character abbreviation){
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

	public static FloorTile get(Character ch){
		FloorTile temp = dict.get(ch);
		if (temp == null){
			return FloorTile.undefined;
		}
		return temp;
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
