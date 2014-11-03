
public class WarpInstructions {

	private int teleX, teleY;
	private String teleName;

	public WarpInstructions(String map, int x, int y){
		teleName = map;
		teleX = x;
		teleY = y;
	}

	public int getTeleX(){
		return teleX;
	}

	public int getTeleY(){
		return teleY;
	}

	public String getTeleName(){
		return teleName;
	}
}
