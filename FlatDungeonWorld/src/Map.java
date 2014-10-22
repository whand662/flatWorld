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

	public void Draw(Graphics g, Game game){
		
	}

	private void initializeMap(String mapFile){
		String line;
		int x, y;
		StringTokenizer st;
		String delimiters = ";";
		
		
		//try {
			//Image img = ImageIO.read(new File("Tiles/" + tileName + ".png"));
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		try{
			BufferedReader in = new BufferedReader(new FileReader("Maps/" + mapFile + ".txt"));
			line = in.readLine();
			st = new StringTokenizer(line, delimiters, false);
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			World = new MapTile[x][y];

			for(int counter1 = (y-1); counter1 > -1; counter1--){
				line = in.readLine();
				for(int counter2 = 0; counter2 < x; counter2++){
					System.out.println(counter1 + "and" + counter2);
					World[counter2][counter1] = identifyTile(line.charAt(counter1));
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
