package flatWorld;


public class Wearable extends Item{

	protected int durability = 1, MAXDUR = 1;

	public Wearable(boolean known) {
		super("Wearable item" , 1, 1, known);
	}

	public int getMaxDurability(){
		return MAXDUR;
	}

	public int getCurrentDurability(){
		return durability;
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
