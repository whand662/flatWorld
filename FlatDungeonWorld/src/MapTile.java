import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapTile {
	
	boolean walkable;
	String tileName;
	
	public MapTile(String tile, boolean walkOn){
		tileName = tile;
		walkable = walkOn;
	}
	
	public void draw(Graphics g, Image img, int x, int y){
		
	}
	
	public String getName(){
		return tileName;
	}
	
	public boolean walkable(){
		return walkable;
	}
}
