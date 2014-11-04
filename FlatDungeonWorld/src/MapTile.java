import java.awt.Graphics;
import java.awt.Image;

public class MapTile {
	
	boolean walkable;
	String tileName;
	private WarpInstructions warpData;
	
	public MapTile(String tile, boolean walkOn){
		tileName = tile;
		walkable = walkOn;
	}
	
	public void setWarpInfo(String map, int x, int y){
		warpData = new WarpInstructions(map, x, y);
	}
	
	public WarpInstructions getWarpInfo(){
		return warpData;
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
