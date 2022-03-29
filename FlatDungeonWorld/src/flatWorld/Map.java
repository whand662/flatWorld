package flatWorld;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import flatWorld.Creature;
import flatWorld.Kobold;
import flatWorld.Player;


public class Map {


	String mapName;
	FloorTile[][] floor;
	EnvTile[][] environment;
	List<Creature> creatures = new ArrayList<Creature>();
	BufferedImage mapImage;
	int maxX, maxY;
	public final int TILEWIDTH = 40;
	int width,height;

	public Map(){

	}
	public Map(String mapFile) {
		mapName = mapFile;
		initializeMap(mapName);
	}

	public String getMapName() {
		return mapName;
	}

	public boolean locWalkable(int x, int y) {
		if (x < 0 || y < 0 || x >= maxX || y >= maxY) {
			return false;
		}
		x = x / TILEWIDTH;
		y = y / TILEWIDTH;

		if (environment[x][y] == null) {
			return floor[x][y].walkable();
		} else {
			return floor[x][y].walkable() && environment[x][y].walkable();
		}
	}

	public boolean locWalkable(double x, double y) {
		int intx = (int) x;
		int inty = (int) y;
		if (intx < 0 || inty < 0 || intx >= maxX || inty >= maxY) {
			return false;
		}
		intx = intx / TILEWIDTH;
		inty = inty / TILEWIDTH;
		if (environment[intx][inty] == null) {
			return floor[intx][inty].walkable();
		} else {
			return floor[intx][inty].walkable()
					&& environment[intx][inty].walkable();
		}
	}

	/**
	 * Draw the map image in the proper location
	 *
	 * @param g		the graphics object to draw with
	 * @param game	the game object to draw on
	 */
	public void draw(Graphics g, int xOffset, int yOffset) {

		g.drawImage(mapImage, xOffset, yOffset, null);

		for (Creature creature : creatures) {
			creature.draw(g, xOffset, yOffset);
		}
	}

	//	This is super important
	private void initializeMap(String mapFile) {
		String line, temp;
		int xLoc = 0, yLoc = 0;
		int tempx, tempy;
		StringTokenizer st;
		String delimiters = ";";

		// Attempt to read map file
		try {
			BufferedReader in = new BufferedReader(new FileReader("res/maps/"
					+ mapFile + ".txt"));
			line = in.readLine();
			st = new StringTokenizer(line, delimiters, false);

			xLoc = Integer.parseInt(st.nextToken());
			yLoc = Integer.parseInt(st.nextToken());
			floor = new FloorTile[xLoc][yLoc];
			environment = new EnvTile[xLoc][yLoc];

			maxX = xLoc * TILEWIDTH;
			maxY = yLoc * TILEWIDTH;

			// Read in regular tiles
			for (int c1 = 0; c1 < yLoc; c1++) {
				line = in.readLine();
				for (int c2 = 0; c2 < xLoc; c2++) {
					floor[c2][c1] = FloorTile.get(line.charAt(c2));
				}
			}

			// Read in special elements
			line = in.readLine();
			while (line != null) {
				st = new StringTokenizer(line, delimiters, false);
				xLoc = Integer.parseInt(st.nextToken());
				yLoc = Integer.parseInt(st.nextToken());
				environment[xLoc][yLoc] = EnvTile.get(st.nextToken().charAt(0));
				if (environment[xLoc][yLoc].name() == "stairup"
						|| environment[xLoc][yLoc].name() == "stairdown") {
					temp = st.nextToken();
					tempx = Integer.parseInt(st.nextToken());
					tempy = Integer.parseInt(st.nextToken());
					environment[xLoc][yLoc].setWarpInfo(temp, tempx, tempy);
				}
				line = in.readLine();
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bufferMap();
	}
	public void bufferMap(){
		// Pre-buffer map image
		// System.out.println("Buffering map");
		int xLoc, yLoc;
		mapImage = new BufferedImage(maxX, maxY, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = mapImage.getGraphics();
		for (int y = 0; y < maxY/TILEWIDTH; y++) {
			for (int x = 0; x < maxX/TILEWIDTH; x++) {
				try {
					xLoc = (x * TILEWIDTH);
					yLoc = (y * TILEWIDTH);
					floor[x][y].draw(g, xLoc, yLoc, TILEWIDTH, TILEWIDTH);
					if (environment[x][y] != null) {
						environment[x][y].draw(g, xLoc, yLoc, TILEWIDTH, TILEWIDTH);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	public WarpInstructions tick(Player player) {
		tickCreatures();
		int x = (int) player.x / TILEWIDTH;
		int y = (int) player.y / TILEWIDTH;

		if (environment[x][y] != null) {
			return environment[x][y].getWarpInfo();
		}

		return null;
	}

	public void tickCreatures() {
		for (Creature creature : creatures) {
			creature.tick(this);
		}
	}

	public void createKobold(int x, int y) {
		Kobold poorSap = new Kobold(x, y);
		creatures.add(poorSap);
	}

	public void killEverything() {
		creatures.clear();

	}

	public void emptyEnv(){
		environment = new EnvTile[width][height];
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

}
