import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Core.GameEngineV2.ArrDirect;


public class Creature {

	int x, y, size = 5;
	int stats[];
	int speed = 5;

	//prototyping variables, will be moved
	int naturalAR = 100;
	int naturalMR = -50;
	int health = 300;

	protected ArrDirect facing = ArrDirect.N;

	AffineTransform at;
	BufferedImage sprite;

	public Creature(int locx, int locy) {
		x = locx;
		y = locy;
	}

	public void takeAttack(AttackBox incoming){
		switch (incoming.getType()) {

		case 'p':
			if(naturalAR > 0){
				health -= (100.0 / (100 + naturalAR) * incoming.getForce());
			}else{
				health -= (2 - (100.0 / (100 - naturalAR))) * incoming.getForce();
			}
			break;

		case 'm':
			if(naturalMR > 0){
				health -= (100.0 / (100 + naturalMR) * incoming.getForce());
			}else{
				health -= (2 - (100.0 / (100 - naturalMR))) * incoming.getForce();
			}
			break;

		default:
			health -= incoming.getForce();
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
		at = new AffineTransform();


		// 4. resize component
		at.scale(1, 1);

		// 3. translate it to the center of the component
		at.translate(x, y);

		// 2. do the actual rotation
		at.rotate(Math.PI*facing.theta);

		// 1. translate the object so that you rotate it around the 
		//    center (easier :))
		at.translate(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	public void draw(Graphics g, int xOffset, int yOffset){
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

	public void setFacing(ArrDirect _facing) {
		this.facing = _facing;
		if (facing != ArrDirect.STILL){
			updateSprite();
		}
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

}
