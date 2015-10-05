package general;

/*
	Basic item class, most likely will not be used on its own with the exception of useless "loot" items
	Starts unidentified
*/

public class Item {

	protected int weight = 0, value = 0;
	protected String name = "Undefined Item Name";
	protected boolean identified = false;
	
	public Item(String label, int cost, int lbs, boolean known) {
		name = label;
		value = cost;
		weight = lbs;
		identified = known;
	}
	
	public String getName(){
		if(identified){
			return name;
		}
		return "Strange item";
	}
	
	public int getValue(){
		return value;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public boolean isKnown(){
		return identified;
	}
	
	public void discover(){
		identified = true;
	}

}
