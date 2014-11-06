

public class Weapon extends Wearable implements Useable {

	Quality quality;
	Material material;
	WearableType type;
	String element;
	int damage;
	
	public Weapon(Quality qual, Material mat, WearableType typ, String elmt, boolean known) {
		super(known);
		quality = qual;
		material = mat;
		type = typ;
		element = elmt;
		weight = material.getWeight();
		damage = (int)(material.getHardness() * quality.getValue());
		MAXDUR = (int)(material.getDurability() * quality.getValue());
		repair();
	}
	
	public String getName(){
		if(!identified){
			return "Mysterious " + type.getName();
		}else if(quality.getName() == ""){
			return material.getName()+ " " + type.getName();
		}else{
			return quality.getName()+ " " + material.getName()+ " " + type.getName();
		}
	}

	public void use() {
		
	}

}