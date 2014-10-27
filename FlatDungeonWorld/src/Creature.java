import java.awt.Color;
import java.awt.Graphics;

public class Creature {

	int x, y, size = 5;
	int stats[];
	int speed = 5;

	public Creature(int locx, int locy) {
		x = locx;
		y = locy;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		int rad = 10;
		g.setColor(Color.GREEN);
		g.fillOval(x - rad, y - rad, rad * 2, rad * 2);
	}

	public void moveUp(Map currentMap){
		for(int count = speed; count > 0; count--){
			if(currentMap.locWalkable(x, y - size - 1) 
					&& currentMap.locWalkable(x - size, y - size - 1) 
					&& currentMap.locWalkable(x + size, y - size - 1)){
				y--;
			}	
		}
	}

	public void moveDown(Map currentMap){
		for(int count = speed; count > 0; count--){
			if(currentMap.locWalkable(x, y + size + 1) 
					&& currentMap.locWalkable(x + size, y + size + 1) 
					&& currentMap.locWalkable(x - size, y + size + 1)){
				y++;
			}	
		}
	}

	public void moveLeft(Map currentMap){
		for(int count = speed; count > 0; count--){
			if(currentMap.locWalkable(x - size - 1, y)
					&& currentMap.locWalkable(x - size - 1, y + size)
					&& currentMap.locWalkable(x - size - 1, y - size)){
				x--;
			}	
		}
	}

	public void moveRight(Map currentMap){
		for(int count = speed; count > 0; count--){
			if(currentMap.locWalkable(x + size + 1, y)
					&& currentMap.locWalkable(x + size + 1, y + size)
					&& currentMap.locWalkable(x + size + 1, y - size)){
				x++;
			}	
		}
	}

}
