import java.awt.Color;
import java.awt.Graphics;

public class InformationBar {

	Player player;
	
	public InformationBar(Player p) {
		player = p;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawRect(0, 620, 70, 100);
		g.setColor(Color.red);
		g.fillRect(1, (int)(621 + (100 - 100 * ((double)player.health/player.healthMAX))), 68, (int)(99 * ((double)player.health/player.healthMAX)));
	}

}