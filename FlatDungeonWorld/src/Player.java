import java.awt.Color;
import java.awt.Graphics;


public class Player extends Creature {
	
	int drawx, drawy;
	Item inventory[];

	public Player(int locx, int locy, int engx, int engy) {
		super(locx, locy);
		drawx = engx;
		drawy = engy;
		speed = 2;
		size = 10;
		
		inventory = new Item[30];
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(drawx - size, drawy - size, size * 2, size * 2);
		g.setColor(Color.GREEN);
		g.drawRect(drawx - size, drawy - size, size * 2, size * 2);
	}
	
	public int give(Item tempItem){
		
		return 0;
	}
	
}
