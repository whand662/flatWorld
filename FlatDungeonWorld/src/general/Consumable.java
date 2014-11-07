package general;


public class Consumable extends Item implements Useable {

	public Consumable(String label, int cost, int lbs, boolean known) {
		super(label, cost, lbs, known);
	}
	
	public void use(){
		
	}

}
