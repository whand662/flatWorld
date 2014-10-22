import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Map {

	MapTile[][] World;
	String mapName;
	Image[] tilePics;

	public Map(String mapFile) {
		mapName = mapFile;
		initializeMap(mapName);
	}

	public String getMapName(){
		return mapName;
	}

	public boolean locWalkable(int x, int y){
		try{
			return (World[x][y].walkable());
		}catch(Exception e){
			return false;
		}
	}

	public void draw(Graphics g, DungeonGame game){
		for(int counter1 = 0; counter1 < 20; counter1++){
			for(int counter2 = 0; counter2 < 20; counter2++){
				try{
					World[counter2][counter1].draw(g, tilePics[tileToInt(World[counter2][counter1].getName())], (counter2 * 40), (counter1 * 40));
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	}

	private String intToTile(int num){
		switch(num){
		case 0:
			return "undefined";
		case 1:
			return "dirt";
		case 2:
			return "water";
		case 3:
			return "lava";
		}
		return "undefined";
	}
	
	private int tileToInt(String tile){
		switch(tile){
		case "undefined":
			return 0;
		case "dirt":
			return 1;
		case "water":
			return 2;
		case "lava":
			return 3;
		}
		return 0;
	}

	private void initializeMap(String mapFile){
		String line;
		int x, y;
		StringTokenizer st;
		String delimiters = ";";

		tilePics = new Image[4];
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
		}
		return temp;
	}

}
