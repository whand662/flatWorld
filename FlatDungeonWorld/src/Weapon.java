
public class Weapon extends Wearable {

	Quality quality;
	Material material;
	String type;
	String element;
	int damage;
	
	public Weapon(String label, int cost, int lbs, boolean known, Quality qual, Material mat, String typ, String elmt) {
		super(label, cost, lbs, known);
		quality = qual;
		material = mat;
		type = typ;
		element = elmt;
		damage = (int)(material.getHardness() * quality.getValue());
		MAXDUR = (int)(material.getDurability() * quality.getValue());
	}
	
	public String getName(){
		if(!identified){
			return "Mysterious " + type;
		}
		return quality.getName()+ " " + material.getName()+ " " + type;
	}

}