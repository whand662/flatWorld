package general;
/*
 * Generates random items
 */



import java.util.Random;

public class ItemGenesis {

	Random randNum;
	DungeonLib library;

	public ItemGenesis(DungeonLib lib) {
		library = lib;
		randNum = new Random();
	}

	public Item getItem(String type, int tier){
		switch(type){
		case "random":
			return randomItem(tier);
		case "weapon":
			return randomWeapon(tier);
		case "armor":
			return randomArmor(tier);
		case "trinket":
			return randomTrinket(tier);
		case "consumable":
			return randomConsumable(tier);
		case "loot":
			return randomLoot(tier);
		}

		return null;
	}

	public Item randomItem(int tier){
		switch(randNum.nextInt(5)){
		case 0:
			return randomWeapon(tier);
		case 1:
			return randomArmor(tier);
		case 2:
			return randomTrinket(tier);
		case 3:
			return randomConsumable(tier);
		case 4:
			return randomLoot(tier);
		}
		return null;
	}

	public Weapon randomWeapon(int tier){
		return new Weapon(randomQuality(), randomMaterial(tier, "weapon"), randomType("weapon"), "non-elemental", true);
	}

	public Armor randomArmor(int tier){

		return null;
	}

	public Trinket randomTrinket(int tier){

		return null;
	}

	public Consumable randomConsumable(int tier){

		return null;
	}

	public Item randomLoot(int tier){

		return null;
	}

	private Quality randomQuality(){
		switch(randNum.nextInt(5)){
		case 0:
			return library.shoddy;
		case 1:
			return library.inferior;
		case 2:
			return library.average;
		case 3:
			return library.superior;
		case 4:
			return library.masterwork;
		}
		return null;
	}

	private Material randomMaterial(int tier, String type){
		if(type == "weapon"){
			switch(randNum.nextInt(9)){
			case 0:
				return library.paper;
			case 1:
				return library.wood;
			case 2:
				return library.iron;
			case 3:
				return library.bronze;
			case 4:
				return library.steel;
			case 5:
				return library.mithril;
			case 6:
				return library.adamantite;
			case 7:
				return library.starmetal;
			case 8:
				return library.unobtainium;
			}
		}else{
			switch(randNum.nextInt(10)){
			case 0:
				return library.paper;
			case 1:
				return library.leather;
			case 2:
				return library.wood;
			case 3:
				return library.iron;
			case 4:
				return library.bronze;
			case 5:
				return library.steel;
			case 6:
				return library.mithril;
			case 7:
				return library.adamantite;
			case 8:
				return library.starmetal;
			case 9:
				return library.unobtainium;
			}
		}
		return null;
	}
	
	private WearableType randomType(String type){
		/*if(type == "weapon"){
			switch(randNum.nextInt(9)){
			case 0:
				return library.paper;
			case 1:
				return library.wood;
			case 2:
				return library.iron;
			case 3:
				return library.bronze;
			case 4:
				return library.steel;
			case 5:
				return library.mithril;
			case 6:
				return library.adamantite;
			case 7:
				return library.starmetal;
			case 8:
				return library.unobtainium;
			}
		}else{
			switch(randNum.nextInt(10)){
			case 0:
				return library.paper;
			case 1:
				return library.leather;
			case 2:
				return library.wood;
			case 3:
				return library.iron;
			case 4:
				return library.bronze;
			case 5:
				return library.steel;
			case 6:
				return library.mithril;
			case 7:
				return library.adamantite;
			case 8:
				return library.starmetal;
			case 9:
				return library.unobtainium;
			}
		}*/
		return library.longsword;
	}

}
