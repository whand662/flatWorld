/**
 *
 */
package flatWorld;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import flatWorld.Player;

/**
 * @author ayrix
 *
 */
public class Area {
	public Map activeMap;
	List<Map> maps = new ArrayList<Map>();


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
