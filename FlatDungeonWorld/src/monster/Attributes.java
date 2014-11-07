package monster;
import java.util.ArrayList;


public class Attributes {

	private Stat stats[];
	private int statMod[][];

	public boolean dead;
	boolean overburdened;

	int level;

	int naturalAR = 0;
	int naturalMR = 0;
	public int health;
	int healthMAX;
	int mana, manaMAX;
	int carryWeight, carryMAX;
	int speed, speedMAX;
	long exp, expMAX;
	ArrayList afflictions;



	public Attributes(int statStart[]) {
		stats = new Stat[6];
		statMod = new int[6][2];
		initializeStats(statStart);
	}

	public void tick(int weight){
		updateStats(weight);
		for(int i = 0; i < stats.length; i++){
			if(statMod[i][1] > 0){
				statMod[i][1]--;
			}else{
				statMod[i][0] = 0;
			}
		}
	}
	
	public void updateStats(int weight){
		healthMAX = checkStat("ENDURANCE") * 5;
		manaMAX = checkStat("WISDOM") * 5;
		speedMAX = checkStat("AGILITY");
		carryMAX = ((checkStat("STRENGTH")/2) + checkStat("ENDURANCE")) * 7;
		
		//section below handles speed changes based on weight carried
		if(weight > carryMAX){
			overburdened = true;
		}else{
			overburdened = false;
		}
		updateSpeed(weight);
	}

	public void updateSpeed(int weight){
		
		if(overburdened){
			speed = 0;
		}
		if((int)Math.round(speedMAX * (1 - ((double)weight/(double)carryMAX))) == 0){
			speed = 1;
		}
		speed = (int)Math.round(speedMAX * (1 - ((double)weight/(double)carryMAX)));
	}

	public void addMod(String label, int mod, int duration){
		for(int i = 0; i < stats.length; i++){
			if(stats[i].getName() == label){
				statMod[i][0] = mod;
				statMod[i][1] = duration;
				return;
			}
		}
	}

	public int checkStat(String label){
		for(int i = 0; i < stats.length; i++){
			if(stats[i].getName() == label){
				if(stats[i].getValue() + statMod[i][0] <= 0){
					return 1;
				}else{
					return stats[i].getValue() + statMod[i][0];
				}
			}
		}
		return 0;
	}

	public void initializeStats(int statStart[]){
		stats[0] = new Stat("STRENGTH", statStart[0]);
		stats[1] = new Stat("ENDURANCE", statStart[1]);
		stats[2] = new Stat("AGILITY", statStart[2]);
		stats[3] = new Stat("INTELLIGENCE", statStart[3]);
		stats[4] = new Stat("WISDOM", statStart[4]);
		stats[5] = new Stat("LUCK", statStart[5]);

		level = 1;
		exp = 0;
		expMAX = 1000;
		carryWeight = 0;

		dead = false;
		overburdened = false;

		healthMAX = checkStat("ENDURANCE") * 5;
		manaMAX = checkStat("WISDOM") * 5;
		speedMAX = checkStat("AGILITY");
		carryMAX = ((checkStat("STRENGTH")/2) + checkStat("ENDURANCE")) * 7;

		fullHeal();
	}

	public void damageHP(int amt){
		health -= amt;
		if(health <= 0){
			dead = true;
			health = 0;
		}
	}

	public void damageMP(int amt){
		mana -= amt;
		if(mana < 0){
			mana = 0;
		}
	}

	public void fullHeal(){
		health = healthMAX;
		mana = manaMAX;
		afflictions = new ArrayList();
	}

}

class Stat{

	private String name;
	private int value;

	public Stat(String nm, int val){
		name = nm;
		value = val;
	}

	public String getName(){
		return name;
	}

	public int getValue(){
		return value;
	}

}
