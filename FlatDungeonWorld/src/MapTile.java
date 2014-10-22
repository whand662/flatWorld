import java.awt.Graphics;
import java.awt.Image;

public class MapTile {
	
	boolean walkable;
	String tileName;
	
	public MapTile(String tile, boolean walkOn){
		tileName = tile;
		walkable = walkOn;
	}
	
	public void draw(Graphics g, Image img, int x, int y){
		g.drawImage(img, x, y, null);
	}
	
	public String getName(){
		return tileName;
	}
	
	public boolean walkable(){
		return walkable;
	}
}
