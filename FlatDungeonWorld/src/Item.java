public class Item {

	int weight = 0, value = 0;
	String name = "Undefined Item Name";
	boolean identified = false;
	
	public Item(String label, int cost, int lbs, boolean known) {
		name = label;
		value = cost;
		weight = lbs;
		identified = known;
	}
	
	public String getName(){
		return name;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void discover(){
		identified = true;
	}

}
