
public class AttackBox {

	private int force;
	private char type, element;

	public AttackBox(int f, char t, char e) {
		force = f;
		type = t;
		element = e;
	}

	public int getForce(){
		return force;
	}

	public char getType(){
		return type;
	}

	public char getElement(){
		return element;
	}

}
