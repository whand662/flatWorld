import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapTile {
	
	Image img;
	boolean walkable;
	String tileName;
	
	public MapTile(String tile, boolean walkOn){
		tileName = tile;
		walkable = walkOn;
		try {
			img = ImageIO.read(new File("Tiles/" + tileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, int x, int y){
		g.drawImage(img, x, y, null);
	}
	
	public boolean walkable(){
		return walkable;
	}
}
