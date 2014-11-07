package core;
import java.awt.Graphics;

public interface Game
{
	
	static final int ENTER = 10;
	static final int SPACE = 32;
	static final int UP = -8;
	static final int LEFT = -4;
	static final int RIGHT = -6;
	static final int DOWN = -2;
	
	void processFrame();
	
	void drawFrame(Graphics g);
}