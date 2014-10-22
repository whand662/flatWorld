/*
 * Version 2.5
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameEngineV2 implements Runnable, ActionListener, KeyListener, MouseListener, MouseMotionListener, FocusListener
{
	Game game;
	String title;
	JFrame gameWindow;  // The main window that the game runs inside of.
	GamePanel gamePanel;  // The panel that the game graphics are drawn on.
	int width, height, setTimer;
	Timer timer;
	//Which keys are currently depressed
	private int keyRing[] = new int[256];
	private int upArrow, leftArrow, rightArrow, downArrow;
	int mouseX, mouseY, clickX, clickY, dragX, dragY;
	boolean mouseClick, selectLocFlag, clickFlag, rightClickFlag, dragEnabled;
	boolean focus = true;

	public GameEngineV2(Game g)
	{
		game = g;
		title = "Default BasicGame";
		width = 280;
		height = 280;
		mouseClick = false;
		selectLocFlag = false;
		dragEnabled = false;
		setKeysFalse();

	}

	public void setWindow(String t, int x, int y, int tmr)
	{
		title = t;
		width = x;
		height = y;
		mouseX = x/2;
		mouseY = y/2;
		setTimer = tmr;
	}

	public void start()
	{
		SwingUtilities.invokeLater(this);
	}

	// The run method is called to start the game
	public void run()
	{
		gameWindow = new JFrame(title);  // First we create the window frame object
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Stop the program when the user presses the big red X on the window frame
		gameWindow.setResizable(false); //Keeps the user from re-sizing the window
		gamePanel = new GamePanel(width, height, game);  // Now we create the panel on which the game graphics are drawn
		gameWindow.getContentPane().add(gamePanel, BorderLayout.CENTER);  // Add our panel to the center of the content pane of our window frame.
		gameWindow.addKeyListener(this);
		gameWindow.addMouseListener(this);
		gameWindow.addMouseMotionListener(this);
		gameWindow.addFocusListener(this);
		gameWindow.pack(); // Lay out the window and the panel inside of it.
		gameWindow.setVisible(true); // Make the game window visible
		timer = new Timer(setTimer, this);
		timer.start();
	}

	public void focusGained(FocusEvent fe) {
		focus = true;
	}

	public void focusLost(FocusEvent fe){
		setKeysFalse();
		focus = false;
	}   

	public int getKey(int ascii){
		if(ascii >= 0 && ascii < 256){
			return keyRing[ascii];
		}else{
			switch(ascii){
			case -2:
				return downArrow;
			case -4:
				return leftArrow;
			case -8:
				return upArrow;
			case -6:
				return rightArrow;
			}
		}
		return -1;
	}

	public void unflagKey(int ascii){
		if(ascii >= 0 && ascii < 256){
			if(keyRing[ascii] == 1){
				keyRing[ascii] = 2;
			}			
		}else{
			switch(ascii){
			case -2:
				if(downArrow == 1){
					downArrow = 2;
				}	
				break;
			case -4:
				if(leftArrow == 1){
					leftArrow = 2;
				}	
				break;
			case -8:
				if(upArrow == 1){
					upArrow = 2;
				}	
				break;
			case -6:
				if(downArrow == 1){
					rightArrow = 2;
				}	
				break;
			}
		}
	}

	public void setKeysFalse(){

		for(int count = 0; count < 256; count++){
			keyRing[count] = 0;
		}

		upArrow = 0;
		downArrow = 0;
		leftArrow = 0;
		rightArrow = 0;

	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			if(upArrow == 0){
				upArrow = 1;
			}
			break;

		case KeyEvent.VK_LEFT:
			if(leftArrow == 0){
				leftArrow = 1;
			}
			break;

		case KeyEvent.VK_RIGHT:
			if(rightArrow == 0){
				rightArrow = 1;
			}
			break;

		case KeyEvent.VK_DOWN:
			if(downArrow == 0){
				downArrow = 1;
			}
			break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
		if(keyRing[(int)e.getKeyChar()] == 0){
			keyRing[(int)e.getKeyChar()] = 1;
		}
		System.out.println((int)e.getKeyChar());
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			upArrow = 0;
			return;

		case KeyEvent.VK_LEFT:
			leftArrow = 0;
			return;

		case KeyEvent.VK_RIGHT:
			rightArrow = 0;
			return;

		case KeyEvent.VK_DOWN:
			downArrow = 0;
			return;

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			return;
		}

		if((int)e.getKeyChar() >= 0 && (int)e.getKeyChar() < 256){
			keyRing[(int)e.getKeyChar()] = 0;
		}

	}

	public void actionPerformed(ActionEvent e)
	{
		//handles resizing of window
		width = gamePanel.getWidth();
		height = gamePanel.getHeight();

		//standard timer calls
		if(focus){
			game.processFrame();
			gamePanel.repaint();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1){
			clickFlag = true;
			clickX = arg0.getX() - 5;
			clickY = arg0.getY() - 25;
			//System.out.println("left click");
		}else if(arg0.getButton() == MouseEvent.BUTTON3){
			rightClickFlag = true;
			clickX = arg0.getX() - 5;
			clickY = arg0.getY() - 25;
			//System.out.println("right click");
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		mouseX = arg0.getX() - 5;
		mouseY = arg0.getY() - 25;
	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		//System.out.println("press");
		if(arg0.getButton() == MouseEvent.BUTTON1){
			dragEnabled = true;
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		mouseX = arg0.getX() - 5;
		mouseY = arg0.getY() - 25;
		selectLocFlag = false;
		dragEnabled = false;
		if(arg0.getButton() == MouseEvent.BUTTON3){
			mouseClicked(arg0);
		}
		//System.out.println("release");
	}

	public void mouseDragged(MouseEvent arg0) {
		if(dragEnabled){
			dragX = arg0.getX() - 5;
			dragY = arg0.getY() - 25;
			selectLocFlag = true;
			//System.out.println("dragging");
		}else{
			mouseX = arg0.getX() - 5;
			mouseY = arg0.getY() - 25;
		}
	}

	public void mouseMoved(MouseEvent arg0) {
		mouseX = arg0.getX() - 5;
		mouseY = arg0.getY() - 25;
	}

}

//This GamePanel class extends the standard JPanel and modifies it to draw our game
//graphics instead of just a blank area.
class GamePanel extends JPanel
{
	Game gameObject;

	// Since JPanels are serializable, we have to have this unique ID on our subclass of JPanel
	private static final long serialVersionUID = -947997753137139117L;

	public GamePanel(int width, int height, Game g)
	{
		super();
		gameObject = g;
		setBackground(Color.black);
		setPreferredSize(new Dimension(width, height));
	}

	// This method gets called by the Java graphics engine whenever the panel needs to be repainted.
	// Normally this happens because the panel has been covered by another window and subsequently uncovered.
	// Our version overrides the normal version in the JPanel class, which simply paints the panel background.
	// Our version is more complex since we have to draw all of the game graphics.
	public void paintComponent(Graphics g)
	{
		// First perform the normal behavior for a JPanel
		super.paintComponent(g);

		// Now draw the game graphics
		gameObject.drawFrame(g);
	}
}

