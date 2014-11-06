import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

import Core.GameEngineV2.ArrDirect;


public class Creature {

	double heading = 0;
	double vel = 0;
	int x, y, size = 20;
	
	Attributes stats;

	protected ArrDirect facing = ArrDirect.N;

	BufferedImage rawSprite = null;
	BufferedImage preparedSprite = null;
	
	public Creature(int locx, int locy) {
		x = locx;
		y = locy;
	}

	public void tick(Map currentMap){
		Random random = new Random();
		double headM = (random.nextDouble()-.5)/10;
		if(headM > .04 || headM < -.04){
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

	public void update(){
		
	}

	/**
	 * See			http://stackoverflow.com/questions/4918482/rotating-bufferedimage-instances
	 * @return at	a prepared AffineTransform for drawing
	 */
	protected void updateSprite(){
		// create the transform, note that the transformations happen
		// in reversed order (so check them backwards)
		AffineTransform at = new AffineTransform();
		
		at.scale(.5, .5);
		
		// 3. put the component back to 0,0
		at.translate(rawSprite.getWidth()/2, rawSprite.getHeight()/2);
		
		// 2. do the actual rotation
		at.rotate(Math.PI*heading);

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))
		at.translate(-rawSprite.getWidth()/2, -rawSprite.getHeight()/2);

		AffineTransformOp atOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		preparedSprite = atOp.filter(rawSprite, preparedSprite);
	}

	public void draw(Graphics g, int xOffset, int yOffset){
		updateSprite();
		g.drawImage(preparedSprite,  x+xOffset-preparedSprite.getWidth()/2,  y+yOffset-preparedSprite.getHeight()/2,  null);
	}

	public void move(Map currentMap, double vel, double heading){
		double xm = Math.cos(heading*Math.PI)*vel;
		double ym = Math.sin(heading*Math.PI)*vel;
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
		heading = facing.theta;
		if (facing != ArrDirect.STILL){
			updateSprite();
		}
	}

	public BufferedImage getSprite() {
		return rawSprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.rawSprite = sprite;
	}

}
