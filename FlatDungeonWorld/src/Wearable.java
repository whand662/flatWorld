
public class Wearable extends Item{

	int durability = 1, MAXDUR = 1;
	
	public Wearable(boolean known) {
		super("Wearable item" , 1, 1, known);
	}

	public void tickDUR(int dmg){
		durability -= dmg;
	}
	
	public void repair(){
		durability = MAXDUR;
	}
	
	public boolean isBroke(){
		if(durability <= 0){
			return true;
		}
		return false;
	}
}
