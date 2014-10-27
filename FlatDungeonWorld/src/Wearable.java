
public class Wearable extends Item{

	private int durability = 1, MAXDUR = 1;
	
	public Wearable(String label, int cost, int lbs, boolean known) {
		super(label, cost, lbs, known);
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
