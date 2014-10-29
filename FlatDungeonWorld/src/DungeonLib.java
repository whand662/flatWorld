public class DungeonLib{

	final Quality shoddy;
	final Quality inferior;
	final Quality average;
	final Quality superior;
	final Quality masterwork;
	
	final Material paper;
	final Material leather;
	final Material wood;
	final Material iron;
	final Material bronze;
	final Material steel;
	final Material mithril;
	final Material adamantite;
	final Material starmetal;
	final Material unobtainium;
	
	/*final WearableType sword;
	final WearableType mace;
	final WearableType axe;
	
	final WearableType helmet;
	final WearableType gloves;
	final WearableType leggings;
	final WearableType boots;
	final WearableType chestplate;
	final WearableType shield;*/
	
	public DungeonLib(){
		
		//define item quality levels
		shoddy = new Quality("Shoddy", .5);
		inferior = new Quality("Inferior", .8);
		average = new Quality("", 1.0);
		superior = new Quality("Superior", 1.2);
		masterwork = new Quality("Masterwork", 1.5);
		
		//define item materials
		paper = new Material("paper", 1, 1, 1);
		leather = new Material("leather", 1, 20, 5);
		wood = new Material("wooden", 5, 25, 5);
		iron = new Material("iron", 15, 50, 10);
		bronze = new Material("bronze", 15, 70, 10);
		steel = new Material("steel", 20, 80, 15);
		mithril = new Material("mithril", 30, 70, 10);
		adamantite = new Material("adamantine", 30, 100, 30);
		starmetal = new Material("starmetel", 40, 150, 25);
		unobtainium = new Material("unobtainium", 60, 200, 30);
	}
}

class WearableType{
	private String name, slot;
	private double modifier;
	private int handsReq;
	private char data;

	public WearableType(String str, int val, char dat){
		name = str;
		modifier = val;
		data = dat;
	}

	public String getName(){
		return name;
	}
	
	public double getModifiere(){
		return modifier;
	}
	
	public char getData(){
		return data;
	}
}

class Material{
	private String name;
	private int hardness, weight, durability;

	public Material(String str, int val, int hp, int lbs){
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