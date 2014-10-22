/*
 * Version 2.5
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameEngineV2 implements Runnable, ActionListener, KeyListener, MouseListener, MouseMotionListener
{
	Game game;
	String title;
	JFrame gameWindow;  // The main window that the game runs inside of.
	GamePanel gamePanel;  // The panel that the game graphics are drawn on.
	int width, height, setTimer;
	Timer timer;
	boolean upArrowPressed, leftArrowPressed, rightArrowPressed, downArrowPressed, spaceBarPressed, aButtonPressed, bButtonPressed, cButtonPressed, dButtonPressed, eButtonPressed, oneButtonPressed, twoButtonPressed, threeButtonPressed, fourButtonPressed, fiveButtonPressed, sixButtonPressed, sevenButtonPressed, eightButtonPressed, nineButtonPressed, zeroButtonPressed; // Which keys are currently depressed 
	boolean fButtonPressed, gButtonPressed, hButtonPressed, iButtonPressed, jButtonPressed, kButtonPressed, lButtonPressed, mButtonPressed, nButtonPressed, oButtonPressed, pButtonPressed, qButtonPressed, rButtonPressed, sButtonPressed, tButtonPressed, uButtonPressed, vButtonPressed, wButtonPressed, xButtonPressed, yButtonPressed, zButtonPressed;
	boolean shiftButtonPressed, backspaceButtonPressed, enterPressed;
	int mouseX, mouseY, clickX, clickY, dragX, dragY;
	boolean mouseClick, selectLocFlag, clickFlag, rightClickFlag, dragEnabled;

	public GameEngineV2(Game g)
	{
		game = g;
		title = "Default BasicGame";
		width = 280;
		height = 280;
		mouseClick = false;
		selectLocFlag = false;
		dragEnabled = false;
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
		gameWindow = new JFrame(title);  // Firse we create the window frame object
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Stop the program when the user presses the big red X on the window frame
		gameWindow.setResizable(false); //Keeps the user from re-sizing the window
		gamePanel = new GamePanel(width, height, game);  // Now we create the panel on which the game graphics are drawn
		gameWindow.getContentPane().add(gamePanel, BorderLayout.CENTER);  // Add our panel to the center of the content pane of our window frame.
		gameWindow.addKeyListener(this);
		gameWindow.addMouseListener(this);
		gameWindow.addMouseMotionListener(this);
		gameWindow.pack(); // Lay out the window and the panel inside of it.
		gameWindow.setVisible(true); // Make the game window visible
		timer = new Timer(setTimer, this);
		timer.start();
	}

	public void setKeysFalse(){
		enterPressed = false;
		upArrowPressed = false;
		leftArrowPressed = false;
		rightArrowPressed = false;
		spaceBarPressed = false;
		downArrowPressed = false;
		aButtonPressed = false;
		bButtonPressed = false;
		cButtonPressed = false;
		dButtonPressed = false;
		eButtonPressed = false;
		fButtonPressed = false;
		gButtonPressed = false;
		hButtonPressed = false;
		iButtonPressed = false;
		jButtonPressed = false;
		kButtonPressed = false;
		lButtonPressed = false;
		mButtonPressed = false;
		nButtonPressed = false;
		oButtonPressed = false;
		pButtonPressed = false;
		qButtonPressed = false;
		rButtonPressed = false;
		sButtonPressed = false;
		tButtonPressed = false;
		uButtonPressed = false;
		vButtonPressed = false;
		wButtonPressed = false;
		xButtonPressed = false;
		yButtonPressed = false;
		zButtonPressed = false;
		oneButtonPressed = false;
		twoButtonPressed = false;
		threeButtonPressed = false;
		fourButtonPressed = false;
		fiveButtonPressed = false;
		sixButtonPressed = false;
		sevenButtonPressed = false;
		eightButtonPressed = false;
		nineButtonPressed = false;
		zeroButtonPressed = false;
		backspaceButtonPressed = false;
		shiftButtonPressed = false;
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_ENTER:
			enterPressed = true;
			break;

		case KeyEvent.VK_UP:
			upArrowPressed = true;
			break;

		case KeyEvent.VK_LEFT:
			leftArrowPressed = true;
			break;

		case KeyEvent.VK_RIGHT:
			rightArrowPressed = true;
			break;

		case KeyEvent.VK_DOWN:
			downArrowPressed = true;
			break;

		case KeyEvent.VK_SPACE:
			spaceBarPressed = true;
			break;

		case KeyEvent.VK_A:
			aButtonPressed = true;
			break;

		case KeyEvent.VK_B:
			bButtonPressed = true;
			break;

		case KeyEvent.VK_C:
			cButtonPressed = true;
			break;

		case KeyEvent.VK_D:
			dButtonPressed = true;
			break;

		case KeyEvent.VK_E:
			eButtonPressed = true;
			break;

		case KeyEvent.VK_F:
			fButtonPressed = true;
			break;

		case KeyEvent.VK_G:
			gButtonPressed = true;
			break;

		case KeyEvent.VK_H:
			hButtonPressed = true;
			break;

		case KeyEvent.VK_I:
			iButtonPressed = true;
			break;

		case KeyEvent.VK_J:
			jButtonPressed = true;
			break;

		case KeyEvent.VK_K:
			kButtonPressed = true;
			break;

		case KeyEvent.VK_L:
			lButtonPressed = true;
			break;

		case KeyEvent.VK_M:
			mButtonPressed = true;
			break;

		case KeyEvent.VK_N:
			nButtonPressed = true;
			break;

		case KeyEvent.VK_O:
			oButtonPressed = true;
			break;

		case KeyEvent.VK_P:
			pButtonPressed = true;
			break;

		case KeyEvent.VK_Q:
			qButtonPressed = true;
			break;

		case KeyEvent.VK_R:
			rButtonPressed = true;
			break;

		case KeyEvent.VK_S:
			sButtonPressed = true;
			break;

		case KeyEvent.VK_T:
			tButtonPressed = true;
			break;

		case KeyEvent.VK_U:
			uButtonPressed = true;
			break;

		case KeyEvent.VK_V:
			vButtonPressed = true;
			break;

		case KeyEvent.VK_W:
			wButtonPressed = true;
			break;

		case KeyEvent.VK_X:
			xButtonPressed = true;
			break;

		case KeyEvent.VK_Y:
			yButtonPressed = true;
			break;

		case KeyEvent.VK_Z:
			zButtonPressed = true;
			break;

		case KeyEvent.VK_1:
			oneButtonPressed = true;
			break;

		case KeyEvent.VK_2:
			twoButtonPressed = true;
			break;

		case KeyEvent.VK_3:
			threeButtonPressed = true;
			break;

		case KeyEvent.VK_4:
			fourButtonPressed = true;
			break;

		case KeyEvent.VK_5:
			fiveButtonPressed = true;
			break;

		case KeyEvent.VK_6:
			sixButtonPressed = true;
			break;

		case KeyEvent.VK_7:
			sevenButtonPressed = true;
			break;

		case KeyEvent.VK_8:
			eightButtonPressed = true;
			break;

		case KeyEvent.VK_9:
			nineButtonPressed = true;
			break;

		case KeyEvent.VK_0:
			zeroButtonPressed = true;
			break;

		case KeyEvent.VK_BACK_SPACE:
			backspaceButtonPressed = true;

			break;

		case KeyEvent.VK_SHIFT:
			shiftButtonPressed = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_ENTER:
			enterPressed = false;
			break;

		case KeyEvent.VK_UP:
			upArrowPressed = false;
			break;

		case KeyEvent.VK_LEFT:
			leftArrowPressed = false;
			break;

		case KeyEvent.VK_RIGHT:
			rightArrowPressed = false;
			break;

		case KeyEvent.VK_SPACE:
			spaceBarPressed = false;
			break;

		case KeyEvent.VK_DOWN:
			downArrowPressed = false;
			break;

		case KeyEvent.VK_A:
			aButtonPressed = false;
			break;

		case KeyEvent.VK_B:
			bButtonPressed = false;
			break;

		case KeyEvent.VK_C:
			cButtonPressed = false;
			break;

		case KeyEvent.VK_D:
			dButtonPressed = false;
			break;

		case KeyEvent.VK_E:
			eButtonPressed = false;
			break;

		case KeyEvent.VK_F:
			fButtonPressed = false;
			break;

		case KeyEvent.VK_G:
			gButtonPressed = false;
			break;

		case KeyEvent.VK_H:
			hButtonPressed = false;
			break;

		case KeyEvent.VK_I:
			iButtonPressed = false;
			break;

		case KeyEvent.VK_J:
			jButtonPressed = false;
			break;

		case KeyEvent.VK_K:
			kButtonPressed = false;
			break;

		case KeyEvent.VK_L:
			lButtonPressed = false;
			break;

		case KeyEvent.VK_M:
			mButtonPressed = false;
			break;

		case KeyEvent.VK_N:
			nButtonPressed = false;
			break;

		case KeyEvent.VK_O:
			oButtonPressed = false;
			break;

		case KeyEvent.VK_P:
			pButtonPressed = false;
			break;

		case KeyEvent.VK_Q:
			qButtonPressed = false;
			break;

		case KeyEvent.VK_R:
			rButtonPressed = false;
			break;

		case KeyEvent.VK_S:
			sButtonPressed = false;
			break;

		case KeyEvent.VK_T:
			tButtonPressed = false;
			break;

		case KeyEvent.VK_U:
			uButtonPressed = false;
			break;

		case KeyEvent.VK_V:
			vButtonPressed = false;
			break;

		case KeyEvent.VK_W:
			wButtonPressed = false;
			break;

		case KeyEvent.VK_X:
			xButtonPressed = false;
			break;

		case KeyEvent.VK_Y:
			yButtonPressed = false;
			break;

		case KeyEvent.VK_Z:
			zButtonPressed = false;
			break;

		case KeyEvent.VK_1:
			oneButtonPressed = false;
			break;

		case KeyEvent.VK_2:
			twoButtonPressed = false;
			break;

		case KeyEvent.VK_3:
			threeButtonPressed = false;
			break;

		case KeyEvent.VK_4:
			fourButtonPressed = false;
			break;

		case KeyEvent.VK_5:
			fiveButtonPressed = false;
			break;

		case KeyEvent.VK_6:
			sixButtonPressed = false;
			break;

		case KeyEvent.VK_7:
			sevenButtonPressed = false;
			break;

		case KeyEvent.VK_8:
			eightButtonPressed = false;
			break;

		case KeyEvent.VK_9:
			nineButtonPressed = false;
			break;

		case KeyEvent.VK_0:
			zeroButtonPressed = false;
			break;

		case KeyEvent.VK_BACK_SPACE:
			backspaceButtonPressed = false;
			break;

		case KeyEvent.VK_SHIFT:
			shiftButtonPressed = false;
			break;

		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void actionPerformed(ActionEvent e)
	{
		//handles resized window
		width = gamePanel.getWidth();
		height = gamePanel.getHeight();
		game.processFrame();
		gamePanel.repaint();
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

