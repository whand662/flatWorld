package general;
/*
 * Item object library
 */



public class DungeonLib{

	private ItemGenesis itemGen;
	
	public final Quality shoddy;
	public final Quality inferior;
	public final Quality average;
	public final Quality superior;
	public final Quality masterwork;
	
	public final Material paper;
	public final Material leather;
	public final Material wood;
	public final Material iron;
	public final Material bronze;
	public final Material steel;
	public final Material mithril;
	public final Material adamantite;
	public final Material starmetal;
	public final Material unobtainium;
	
	public final WearableType longsword;
	public final WearableType mace;
	public final WearableType battleaxe;
	public final WearableType greatsword;
	
	public final WearableType circlet;
	
	public DungeonLib(){
		
		itemGen = new ItemGenesis(this);
		
		//define item quality levels
		shoddy = new Quality("Shoddy", .5);
		inferior = new Quality("Inferior", .8);
		average = new Quality("", 1.0);
		superior = new Quality("Superior", 1.2);
		masterwork = new Quality("Masterwork", 1.5);
		
		//define item materials
		paper = new Material("Paper", 1, 1, 1);
		leather = new Material("Leather", 1, 20, 5);
		wood = new Material("Wooden", 5, 25, 5);
		iron = new Material("Iron", 15, 50, 10);
		bronze = new Material("Bronze", 15, 70, 10);
		steel = new Material("Steel", 20, 80, 15);
		mithril = new Material("Mithril", 30, 70, 10);
		adamantite = new Material("Adamantine", 30, 100, 30);
		starmetal = new Material("Starmetel", 40, 150, 25);
		unobtainium = new Material("Unobtainium", 60, 200, 30);
		
		//define item types
		longsword = new WearableType("Longsword", "weapon", 1, 1, 's');
		greatsword = new WearableType("Greatsword", "weapon", 1.5, 2, 'n');
		mace = new WearableType("Mace", "weapon", .9, 1, 'b');
		battleaxe = new WearableType("Battle-axe", "weapon", .9, 1, 's');
		
		circlet = new WearableType("Circlet", "helmet", .1, 0, 'n');
	}
	
	public Item getItem(String type, int tier){
		return itemGen.getItem(type, tier);
	}
}

class WearableType{
	
	private String name, slot;
	private double modifier;
	private int handsReq;
	private char data;

	public WearableType(String str, String slt, double val, int hands, char c){
		name = str;
		slot = slt;
		modifier = val;
		handsReq = hands;
		data = c;
	}

	public String getName(){
		return name;
	}
	
	public String getSlot(){
		return slot;
	}
	
	public double getModifier(){
		return modifier;
	}
	
	public char getData(){
		return data;
	}
	
	public int getHands(){
		return handsReq;
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