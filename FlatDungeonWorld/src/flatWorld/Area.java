/**
 * 
 */
package flatWorld;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import monster.Player;

/**
 * @author ayrix
 *
 */
public class Area {
	Biome biome = Biome.forest;
	public Map activeMap;
	List<Map> maps = new ArrayList<Map>();

	public Area(Biome biome){
		Map map = new Map();
		int width = 80;
		int height = 20;
		this.biome = biome;
		switch (biome){
		case forest:
			map.setSize(width, height);
			map.emptyEnv();
			map.generateFloor(FloorTile.dirtyGrass);
			map.addTrees(.5);
		case plains:
		case desert:
		case urban:
			default:
		}
		activeMap = map;
	}
	public Area(String name) {
		maps.add(new Map(name));
		activeMap = maps.get(0);
	}

	public String getMapName() {
		return activeMap.getMapName();
	}

	public void draw(Graphics g, int i, int j) {
		activeMap.draw(g, i, j);
		
	}

	public boolean locWalkable(double x, double y) {
		return activeMap.locWalkable(x, y);
	}

	public WarpInstructions tick(Player player) {
		return activeMap.tick(player);
	}

	public void createKobold(int x, int y) {
		activeMap.createKobold(x, y);
	}

	public void killEverything() {
		activeMap.killEverything();
		
	}

}
