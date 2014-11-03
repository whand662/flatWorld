import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Map {

	MapTile[][] World;
	String mapName;
	String[] tileIndex = new String[]{"undefined", "dirt", "water", "lava", "stairdown", "stairup"};
	BufferedImage[] tilePics;
	int maxX, maxY;
	private final int TILEWIDTH = 40;

	public Map(String mapFile) {
		mapName = mapFile;
		initializeMap(mapName);
	}

	public String getMapName(){
		return mapName;
	}

	public boolean locWalkable(int x, int y){
		if(x < 0 || y < 0 || x >= maxX || y >= maxY){
			return false;
		}
		x = x / 40;
		y = y / 40;
		return (World[x][y].walkable());
	}

	/**
	 * Draw all tiles in the proper location
	 * 
	 * @param g		the graphics object to draw with
	 * @param game	the game object to draw on
	 */
	public void draw(Graphics g, int xOffset, int yOffset){
		int x, y, tileNum;

		for(int counter1 = 0; counter1 < maxY/40; counter1++){
			for(int counter2 = 0; counter2 < maxX/40; counter2++){
				try{
					x = (counter2 * TILEWIDTH) + xOffset;
					y = (counter1 * TILEWIDTH) + yOffset;
					tileNum = tileToInt(World[counter2][counter1].getName());
					World[counter2][counter1].draw(g, tilePics[tileNum], x, y);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	}

	private String intToTile(int num){
		
		if(num < tileIndex.length){
			return tileIndex[num];
		}
		
		return "undefined";
	}

	private int tileToInt(String tile){
		
		for(int i = 0; i < tileIndex.length; i++){
			if(tileIndex[i] == tile){
				return i;
			}
		}
		
		return 0;
	}

	private void initializeMap(String mapFile){
		String line;
		int x = 0, y = 0;
		StringTokenizer st;
		String delimiters = ";";

		tilePics = new BufferedImage[6];
		for(int count = 0; count < tilePics.length; count++){
			try {
				tilePics[count] = ImageIO.read(new File("Tiles/" + intToTile(count) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try{
			BufferedReader in = new BufferedReader(new FileReader("Maps/" + mapFile + ".txt"));
			line = in.readLine();
			st = new StringTokenizer(line, delimiters, false);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			World = new MapTile[x][y];

			for(int counter1 = 0; counter1 < y; counter1++){
				line = in.readLine();
				for(int counter2 = 0; counter2 < x; counter2++){
					World[counter2][counter1] = identifyTile(line.charAt(counter2));
				}
			}

			in.close();
		}catch (IOException e){
			e.printStackTrace();
		}

		maxX = x * 40;
		maxY = y * 40;
	}

	private MapTile identifyTile(char tileCode){
		MapTile temp = new MapTile("undefined", false);
		switch(tileCode){
		case 'd':
			temp = new MapTile("dirt", true);
			break;
		case 'l':
			temp = new MapTile("lava", false);
			break;
		case 'w':
			temp = new MapTile("water", false);
			break;
		case '>':
			temp = new MapTile("stairdown", true);
			break;
		case '<':
			temp = new MapTile("stairup", true);
			break;
		}
		
		return temp;
	}

	public int tickMap(Player player) {
		int x = player.x / 40;
		int y = player.y / 40;
		
		if(World[x][y].getName() == "stairdown"){
			return 1;
		}else if(World[x][y].getName() == "stairup"){
			return -1;
		}
		return 0;
	}

}
