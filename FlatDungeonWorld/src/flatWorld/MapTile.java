package flatWorld;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public enum MapTile {
	undefined 	(false,	'u'),
	dirt 		(true,	'd'),
	water 		(false,	'w'),
	lava 		(false,	'l'),
	lightGrass 	(true,	'g'),
	dirtyGrass 	(true,	'i'),
	stairup 	(true,	'<'),
	stairdown 	(true,	'>'),
	;
	
	boolean walkable;
	Character tileAbbr;
	private WarpInstructions warpData;
	static HashMap<Character, MapTile> dict = new HashMap<Character, MapTile>();
	BufferedImage img = null;
	
	static{
		for(MapTile tile: MapTile.values()){
			dict.put(tile.tileAbbr, tile);
		}			
	}

	private MapTile(boolean walkOn, Character abbreviation, String imgName){
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
	private MapTile(boolean walkOn, Character abbreviation){
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
	
	public static MapTile get(Character ch){
		MapTile temp = dict.get(ch);
		if (temp == null){
			return MapTile.undefined;
		}
		return temp;
	}
	
	public void setWarpInfo(String map, int x, int y){
		warpData = new WarpInstructions(map, x, y);
	}
	
	public WarpInstructions getWarpInfo(){
		return warpData;
	}
	
	public void draw(Graphics g, int x, int y){
		g.drawImage(img, x, y, null);
	}
	
	public boolean walkable(){
		return walkable;
	}
}
