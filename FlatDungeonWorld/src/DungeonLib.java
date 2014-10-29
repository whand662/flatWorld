public class DungeonLib{

	final Quality shoddy;
	final Quality inferior;
	final Quality average;
	final Quality superior;
	final Quality masterwork;
	
	final Material leather;
	final Material wood;
	final Material iron;
	final Material bronze;
	final Material steel;
	final Material mithril;
	final Material adamantite;
	final Material starmetal;
	final Material unobtainium;
	
	public DungeonLib(){
		
		//define item quality levels
		shoddy = new Quality("Shoddy", .5);
		inferior = new Quality("Inferior", .8);
		average = new Quality("", 1.0);
		superior = new Quality("Superior", 1.2);
		masterwork = new Quality("Masterwork", 1.5);
		
		//define item materials
		leather = new Material("leather", 20, 5, 10);
		wood = new Material("wooden", 20, 5, 10);
		iron = new Material("iron", 20, 5, 10);
		bronze = new Material("bronze", 20, 5, 10);
		steel = new Material("steel", 20, 5, 10);
		mithril = new Material("mithril", 20, 5, 10);
		adamantite = new Material("adamantine", 20, 5, 10);
		starmetal = new Material("starmetel", 20, 5, 10);
		unobtainium = new Material("unobtainium", 20, 5, 10);
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