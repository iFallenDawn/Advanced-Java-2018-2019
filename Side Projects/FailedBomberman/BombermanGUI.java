//Jordan Wang
//
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.imageio.ImageIO;

public class BombermanGUI extends Canvas implements KeyListener
{
	private Image playerPic;
	private BufferStrategy strategy;
	private Bomberman player;
	private Map map;
	private boolean playing;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean placeBomb = true;
	public BombermanGUI()
	{
		try
		{
			playerPic = ImageIO.read(new File("sprite.jpg"));
		}
		catch(IOException e)
		{

		}
		JFrame container = new JFrame("Wang Bomberman");
		container.setLayout(null);
		setBounds(0,0,600,600);
		container.add(this);
		container.setSize(615, 638);
		container.setResizable(false);

		container.addKeyListener(this);
		addKeyListener(this);

		container.setVisible(true);

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		map = new Map();
		player = new Bomberman(playerPic, map, 1.5f, 1.5f);

		gameLoop();
	}
	public static void main(String[] args)
	{
		BombermanGUI bman = new BombermanGUI();
	}
	public void gameLoop()
	{
		playing = true;
		long lastLoopTime = System.currentTimeMillis();
		while(playing == true)
		{
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			map.paint(g);
			player.paint(g);

			g.dispose();
			strategy.show();

			try
			{
				Thread.sleep(5);
			}
			catch(Exception e)
			{
				System.out.println("Pineapple");
			}
			long timeElapsed = (System.nanoTime() - lastLoopTime) / 1000000;
			lastLoopTime = System.nanoTime();

			for(int i = 0; i < timeElapsed / 5; i++)
			{
				update(5);
			}

			if(timeElapsed % 5 != 0)
			{
				update(timeElapsed % 5);
			}
		}
	}
	public void update(long timeElapsed)
	{
		double dx = 0;
		double dy = 0;
		if(left == true)
			dx--;
		if(right == true)
			dx++;
		if(up == true)
			dy--;
		if(down == true)
			dy++;
		if(dx != 0 || dy != 0)
		{
			player.move(dx * timeElapsed * .003f, dy * timeElapsed * .003f);
		}
	}
	public void keyTyped(KeyEvent e)
	{
		//Nothing to see here
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == 37)
			left = true;
		if(e.getKeyCode() == 38)
			up = true;
		if(e.getKeyCode() == 39)
			right = true;
		if(e.getKeyCode() == 40)
			down = true;
		if(e.getKeyCode() == 32)
			placeBomb = true;
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == 37)
			left = false;
		if(e.getKeyCode() == 38)
			up = false;
		if(e.getKeyCode() == 39)
			right = false;
		if(e.getKeyCode() == 40)
			down = false;
		if(e.getKeyCode() == 32)
			placeBomb = false;
	}
}

/**	Important information:
/*		15 * 15 board (width by height)
/*		13 * 13 playing field
/*		2D tile map
/*		https://github.com/libgdx/libgdx/wiki
/*		https://coderanch.com/t/633503/java/Tiled-Based-Map-Game-Java
/*		http://www.cokeandcode.com/main/tutorials/space-invaders-101/
/*	Things to mention
/*		Game loops, 2d tile maps, images, buffer strategies, accelerated rendering
*/