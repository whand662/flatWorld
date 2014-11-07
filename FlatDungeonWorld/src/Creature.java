import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Core.GameEngineV2.ArrDirect;


public class Creature {

	double heading = 0;
	double lastHeading = 0;
	double vel = 0;
	int x, y, size = 40;
	
	Attributes stats;

	protected ArrDirect facing = ArrDirect.N;

	AffineTransform at = null;
	BufferedImage sprite = null;
	BufferedImage preparedSprite = null;
	
	public Creature(int locx, int locy, String spriteFile) {
		try {
			sprite = ImageIO.read(new File(spriteFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = locx;
		y = locy;
		stats = new Attributes(new int[]{5,5,5,5,5,5});
	}

	public void tick(Map currentMap){
		Random random = new Random();
		double headM = (random.nextDouble()-.5)/10;
		if(headM > .02 || headM < -.02){
			heading += headM;
		}
		vel += (random.nextDouble()-.4)/100;
		move(currentMap, vel, heading);
		vel=vel%3;
		stats.tick(0);
	}

	public void takeAttack(AttackBox incoming){
		switch (incoming.getType()) {

		case 'p':
			if(stats.naturalAR > 0){
				stats.damageHP((int) (100.0 / (100 + stats.naturalAR) * incoming.getForce()) );
			}else{
				stats.damageHP((int) (2 - (100.0 / (100 - stats.naturalAR))) * incoming.getForce() );
			}
			break;

		case 'm':
			if(stats.naturalMR > 0){
				stats.damageHP((int) (100.0 / (100 + stats.naturalMR) * incoming.getForce()) );
			}else{
				stats.damageHP((int) (2 - (100.0 / (100 - stats.naturalMR))) * incoming.getForce() );
			}
			break;

		default:
			stats.damageHP(incoming.getForce());
			break;
		}

	}

	public void draw(Graphics g, int xOffset, int yOffset){
		Graphics2D g2d = (Graphics2D) g;
		if(sprite != null){
			at = new AffineTransform();
			at.translate(xOffset+x, yOffset+y);
				at.rotate(Math.PI*heading);
			at.scale(.5,.5);
			at.translate(-sprite.getWidth()/2, -sprite.getHeight()/2);
			g2d.drawImage(sprite, at, null);
		}
	}

	public void move(Map currentMap, double vel, double heading){
		double xm = Math.cos(heading*Math.PI)*vel;
		double ym = Math.sin(heading*Math.PI)*vel;

		System.out.println(xm + "\t" + ym);
		if(currentMap.locWalkable(x+xm, y+ym)){
			x += xm;
			y += ym;
		}
	}
	
	public void moveUp(Map currentMap){
		for(int count = stats.speed; count > 0; count--){
			if(currentMap.locWalkable(x, y - size - 1) 
					&& currentMap.locWalkable(x - size, y - size - 1) 
					&& currentMap.locWalkable(x + size, y - size - 1)){
				y--;
			}	
		}
	}

	public void moveDown(Map currentMap){
		for(int count = stats.speed; count > 0; count--){
			if(currentMap.locWalkable(x, y + size + 1) 
					&& currentMap.locWalkable(x + size, y + size + 1) 
					&& currentMap.locWalkable(x - size, y + size + 1)){
				y++;
			}	
		}
	}

	public void moveLeft(Map currentMap){
		for(int count = stats.speed; count > 0; count--){
			if(currentMap.locWalkable(x - size - 1, y)
					&& currentMap.locWalkable(x - size - 1, y + size)
					&& currentMap.locWalkable(x - size - 1, y - size)){
				x--;
			}	
		}
	}

	public void moveRight(Map currentMap){
		for(int count = stats.speed; count > 0; count--){
			if(currentMap.locWalkable(x + size + 1, y)
					&& currentMap.locWalkable(x + size + 1, y + size)
					&& currentMap.locWalkable(x + size + 1, y - size)){
				x++;
			}	
		}
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrDirect getFacing() {
		return facing;
	}

	public void setFacing(ArrDirect facing) {
		this.facing = facing;
		if(facing != ArrDirect.STILL){
			heading = facing.theta;
		}
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

}
