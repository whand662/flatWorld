/* 
 * Equipment array has 10 elements
 * 0- right hand
 * 1- left hand
 * 2- helmet
 * 3- chest-plate
 * 4- leggings
 * 5- gloves
 * 6- boots
 * 7- amulet
 * 8- ring1
 * 9- ring2
 */
public class Inventory {

	private int gold = 0;
	private Item backpack[];
	private Wearable equipment[];
	private int totalWeight = 0;

	public Inventory(int size) {
		backpack = new Item[size];
		equipment = new Wearable[10];
	}

	public void tick(Player player){
		int i;
		totalWeight = 0;
		for(i = 0; i < getSize(); i++){
			if(backpack[i] instanceof Trinket){
				((Trinket) backpack[i]).tickTrinket();
			}
			if(backpack[i] != null){
				totalWeight += backpack[i].getWeight();
			}
		}
		for(i = 0; i < equipment.length; i++){
			if(equipment[i] instanceof Armor){
				((Armor) equipment[i]).tickArmor(player);
			}
			if(equipment[i] != null){
				if(equipment[i].isBroke()){
					equipment[i] = null;
				}else{
					totalWeight += equipment[i].getWeight();
				}
			}
		}
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

	public int getWeight(){
		return totalWeight;
	}

	public int getSize(){
		return backpack.length;
	}

	public Item whatsHere(int location) {

		return backpack[location];
	}

	public void fullReset(){
		int i;
		gold = 0;
		for(i = 0; i < backpack.length; i++){
			backpack[i] = null;
		}
		for(i = 0; i < equipment.length; i++){
			equipment[i] = null;
		}
	}

	public void action(char c) {
		
	}
}
