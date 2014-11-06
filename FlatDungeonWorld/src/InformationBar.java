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
		g.drawRect(100, 620, 70, 100);
		
		g.setColor(Color.red);
		g.fillRect(1, (int)(621 + (100 - 100 * ((double)player.stats.health/player.stats.healthMAX))), 68, (int)(99 * ((double)player.stats.health/player.stats.healthMAX)));
		
		g.setColor(Color.blue);
		g.fillRect(101, (int)(621 + (100 - 100 * ((double)player.stats.mana/player.stats.manaMAX))), 68, (int)(99 * ((double)player.stats.mana/player.stats.manaMAX)));
	}

}