
public class Inventory {

	private int gold = 0;
	private Item backpack[];
	private int totalWeight = 0;

	public Inventory(int size) {
		backpack = new Item[size];
	}
	
	public void tickInventory(Player player){
		totalWeight = 0;
		for(int i = 0; i < getSize(); i++){
			if(backpack[i] instanceof Trinket){
				((Trinket) backpack[i]).tickTrinket();
			}
			if(backpack[i] != null){
				totalWeight += backpack[i].getWeight();
			}
		}
		//tick armor
		//check for broken equipment
		//add weight of equipment
	}

	public void storeGold(int qty){
		gold += qty;
	}
	
	public int removeGold(int qty){
		if(qty > gold){
			return 1;
		}else{
			gold -= qty;
		}
		return 0;
	}
	
	public int storeItem(Item tempItem){
		for(int count = 0; count < 20; count++){
			if(backpack[count] == null){
				backpack[count] = tempItem;
				return 0;
			}
		}
		return 1;
	}
	
	public int getSize(){
		return backpack.length;
	}

	public Item whatsHere(int location) {
		
		return backpack[location];
	}
}
