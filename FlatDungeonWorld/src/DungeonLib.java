public class DungeonLib{
	public DungeonLib(){
		
		//define item quality levels
		final Quality shoddy = new Quality("Shoddy", .5);
		final Quality inferior = new Quality("Inferior", .8);
		final Quality average = new Quality("", 1.0);
		final Quality superior = new Quality("Superior", 1.2);
		final Quality masterwork = new Quality("Masterwork", 1.5);
		
		//define item materials
		final Material leather = new Material("leather", 20, 5, 10);
	}
}

class Material{
	private String name;
	private int hardness, weight, durability;

	public Material(String str, int val, int lbs, int hp){
		name = str;
		hardness = val;
		weight = lbs;
		durability = hp;
	}

	public String getName(){
		return name;
	}
	
	public int getHardness(){
		return hardness;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getDurability(){
		return durability;
	}
}

class Quality{
	private String name;
	private double value;
	
	public Quality(String str, double val){
		name = str;
		value = val;
	}

	public String getName(){
		return name;
	}
	
	public double getValue(){
		return value;
	}
}