package com.engine;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	public static GamePanel instance = null;

	public static final int PWIDTH = 640; // size of panel
	public static final int PHEIGHT = 600;
	private Thread animator; // for the animation
	private boolean running = false; // stops the animation

	private Graphics2D dbg;
	private int FPS, SFPS;
	private long CurrentSecond = 0;
	private long NewSecond = 0;
 

	private Canvas gui = null;
	private BufferStrategy strategy = null;
	
	public static MyCanvas activeScreen = null;
	
	public Random rnd;
	
	public GamePanel() {
		instance = this;

		rnd = new Random();
		
		setBackground(Color.white); // white background
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

		gui = new Canvas();
		gui.setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		add(gui, BorderLayout.CENTER);

		setFocusable(true); // create game components
		requestFocus(); // JPanel now receives key events

		// Adiciona um Key Listner
		gui.addKeyListener(new KeyAdapter() {
			// listen for esc, q, end, ctrl-c
			public void keyPressed(KeyEvent e) {
				if(activeScreen != null) {
					//telaAtiva.keyPressed(e);
				}
			}

			public void keyReleased(KeyEvent e) {
				if(activeScreen != null) {
					//telaAtiva.keyReleased(e);
				}
			}
		});
		
		gui.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				 if(activeScreen !=null ) {
					 //telaAtiva.mouseWheelMoved(e);
				 }
				
			}
		});
		
		gui.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) { 
				if(activeScreen != null) {
					//telaAtiva.mouseMoved(e);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) { 
				if(activeScreen != null) {
					//telaAtiva.mouseDragged(e);
				}
			}
		});
		 
		gui.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				 if(activeScreen != null) {
					// telaAtiva.mousePressed(e);
				 }
			}
			
			public void mouseReleased(MouseEvent e) {
				if(activeScreen != null) {
					// telaAtiva.mouseReleased(e);
				 }
			}
			
			public void mouseExited(MouseEvent e) {
				if(activeScreen != null) {
					// telaAtiva.mouseExited(e);
				 }
			}
			
			public void mouseEntered(MouseEvent e) {
				if(activeScreen != null) {
					// telaAtiva.mouseEntered(e);
				 }
			}
			
			public void mouseClicked(MouseEvent e) {
				if(activeScreen != null) {
					// telaAtiva.mouseClicked(e);
				 }
			}
		});

	 
		activeScreen = new CanvasMain();

	} // end of GamePanel()


	public void addNotify() {
		super.addNotify(); // creates the peer
		startGame(); // start the thread
	}

	private void startGame()
	// initialise and start the thread
	{
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} // end of startGame()

	public void stopGame()
	// called by the user to stop execution
	{
		running = false;
	}

	public void run()
	/* Repeatedly update, render, sleep */
	{
		running = true;

		long diffTime, timePrevious;

		diffTime = 0;
		timePrevious = System.currentTimeMillis();
		CurrentSecond = (long) (timePrevious / 1000);

		gui.createBufferStrategy(2);
		strategy = gui.getBufferStrategy();

		while (running) {

			dbg = (Graphics2D) strategy.getDrawGraphics();
			dbg.setClip(0, 0, PWIDTH, PHEIGHT);
			dbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			gameUpdate(diffTime);
			gameRender();

			dbg.dispose();
			strategy.show();

			try {
				Thread.sleep(1); // sleep a bit
			} catch (InterruptedException ex) {
			}

			diffTime = System.currentTimeMillis() - timePrevious;
			timePrevious = System.currentTimeMillis();
			NewSecond = (long) (timePrevious / 1000);

			if (NewSecond != CurrentSecond) {
				FPS = SFPS;
				CurrentSecond = NewSecond;
				SFPS = 1;
			} else {
				SFPS++;
			}

		}
		System.exit(0); // so enclosing JFrame/JApplet exits
	} // end of run()

	private void gameUpdate(long diffTime) { 
		if(activeScreen != null) {
			activeScreen.gameUpdate(diffTime);
		}
	}

	private void gameRender()
	// draw the current frame to an image buffer
	{
		// clear the background
		dbg.setColor(Color.black);
		dbg.fillRect(0, 0, PWIDTH, PHEIGHT); 
		
		// draw game elements
		if(activeScreen != null) {
			activeScreen.gameRender(dbg);
		}
		
		// draw FPS
		dbg.setColor(Color.WHITE);
		dbg.drawString("FPS: " + FPS, 20, 20);
		
		
		

	} // end of gameRender()

	public static void main(String args[]) {
		GamePanel ttPanel = new GamePanel();

		// create a JFrame to hold the timer test JPanel
		JFrame app = new JFrame("Game Core");
		app.getContentPane().add(ttPanel, BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		app.pack();
		app.setResizable(false);
		app.setVisible(true);
	} // end of main()

} // end of GamePanel class
