//Vivek Shah
//Snake
//Spec: Snake Game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class SnakeP1 extends JFrame
{
	private JButton[][] board;
	private JLabel length;
	private KeyboardHandler klistener;
	private TimerListener tmrlistener;
	private JPanel buttons;
	private Timer tmr;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//2D Array of Objects
	private PieceP1[][] game;
	//Arraylist of Pieces that Make Snake
	private ArrayList<PieceP1> snake = new ArrayList<PieceP1>();
	//Coordinates of Apple
	private int appleR, appleC;
	private int count = 0;
	private int score, tickrate;
	private boolean addSnake, hasMoved;
	public SnakeP1()
	{
		JFrame frame = new JFrame("Snake");
		Container cp = frame.getContentPane();
		cp.setLayout(null);
		cp.setBackground(new Color(252, 84, 84));
		//Creates Keyboard Listener
		klistener = new KeyboardHandler();
		frame.setFocusable(true);
		frame.addKeyListener(klistener);
		frame.setBackground(new Color(252, 84, 84));
		//Creates Panels For the Buttons
		buttons = new JPanel();
		GridLayout buttonLayout = new GridLayout(55,75);
		buttons.setLayout(buttonLayout);
		buttons.setBackground(new Color(252, 84, 84));
		board = new JButton[55][75];
		game = new PieceP1[55][75];
		addSnake = false;
		hasMoved = false;
		score = snake.size();
		//Intializes the Entire Button Array and the Object Array
		for(int row = 0; row < 55; row++)
		{
			for(int col = 0; col < 75; col++)
			{
				board[row][col] = new JButton();
				board[row][col].setSize(1,1);
				board[row][col].setEnabled(false);
				board[row][col].setBorder(new EmptyBorder(0,0,0,0));
				board[row][col].setBackground(new Color(0, 0, 168));
				buttons.add(board[row][col]);
				game[row][col] = new PieceP1(row, col);
				game[row][col].changeFloor(true);
			}
		}
		//Creates Border of Buttons in the Array
		for(int col = 0; col < 75; col++)
		{
			board[0][col].setBackground(new Color(252, 84, 84));
			board[54][col].setBackground(new Color(252, 84, 84));
			game[0][col].changeWall(true);
			game[0][col].changeFloor(false);
			game[54][col].changeWall(true);
			game[54][col].changeFloor(false);
		}
		for(int row = 0; row < 55; row++)
		{
			board[row][0].setBackground(new Color(252, 84, 84));
			board[row][74].setBackground(new Color(252, 84, 84));
			game[row][0].changeWall(true);
			game[row][0].changeFloor(false);
			game[row][74].changeWall(true);
			game[row][74].changeFloor(false);
		}
		//Creates A snake and makes a new head
		game[2][2].changeHead(true);
		game[2][2].changeSnake(true);
		game[2][2].changeFloor(false);
		snake.add(game[2][2]);
		board[2][2].setBackground(Color.YELLOW);
		//Makes New Apple
		newApple();
		buttons.setBounds(0,0, screenSize.width - 150,screenSize.height - 100);
		tmrlistener = new TimerListener();
		tickrate = 65;
		tmr = new Timer(tickrate, tmrlistener);
		length = new JLabel("Length: " + score);
		length.setFont(new Font(Font.SERIF, Font.BOLD, 12));
		length.setForeground(Color.WHITE);
		length.setBounds(screenSize.width - 250, 10, 200,20);
		cp.add(length);
		cp.add(buttons);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(screenSize.width - 136,screenSize.height-70);
		tmr.start();
	}
	public static void main(String[] args)throws IOException
	{
		SnakeP1 s = new SnakeP1();
	}
	//Checks collision between snake head and border or snake body
	public void dies()
	{
		if(snake.get(0).getRow() == 0 || snake.get(0).getRow() == 54 || snake.get(0).getColumn() == 0 || snake.get(0).getColumn() == 74)
		{
			tmr.stop();
			board[snake.get(0).getRow()][snake.get(0).getColumn()].setBackground(Color.GRAY);
			JOptionPane.showMessageDialog(null, "Score: " + snake.size());
			JOptionPane.showMessageDialog(null, "Thanks For Playing! Bye!");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
		else
		{
			if(snake.size() > 1)
			{
				for(int i = 1; i < snake.size(); i++)
				{
					if(snake.get(0).getRow() == snake.get(i).getRow() && snake.get(0).getColumn() == snake.get(i).getColumn())
					{
						tmr.stop();
						board[snake.get(0).getRow()][snake.get(0).getColumn()].setBackground(Color.GRAY);
						JOptionPane.showMessageDialog(null, "Score: " + snake.size());
						JOptionPane.showMessageDialog(null, "Thanks For Playing! Bye!");
						this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
					}
				}
			}
		}
	}
	//Moves each PieceP1 based on the location of the snake piece ahead of it
	public void move()
	{
		for(int i = snake.size()-1; i >= 0; i--)
		{
			snake.get(i).changeFloor(false);
			if(snake.get(i).getHead())
			{
				if(snake.get(i).getDirection().equals("U"))
				{
					if(snake.size() > 1)
					{
						if(snake.get(i).getRow()-1 == snake.get(i+1).getRow())
							break;
						else
							snake.get(i).changeR(snake.get(i).getRow() - 1);
					}
					else
						snake.get(i).changeR(snake.get(i).getRow() - 1);
				}
				else if (snake.get(i).getDirection().equals("D"))
				{
					if(snake.size() > 1)
					{
						if(snake.get(i).getRow()+1 == snake.get(i+1).getRow())
							break;
						else
							snake.get(i).changeR(snake.get(i).getRow() + 1);
					}
					else
						snake.get(i).changeR(snake.get(i).getRow() + 1);
				}
				else if (snake.get(i).getDirection().equals("L"))
				{
					if(snake.size() > 1)
					{
						if(snake.get(i).getColumn()-1 == snake.get(i+1).getColumn())
							break;
						else
							snake.get(i).changeC(snake.get(i).getColumn() - 1);
					}
					else
						snake.get(i).changeC(snake.get(i).getColumn() - 1);
				}
				else if (snake.get(i).getDirection().equals("R"))
				{
					if(snake.size() > 1)
					{
						if(snake.get(i).getColumn()+1 == snake.get(i+1).getColumn())
							break;
						else
							snake.get(i).changeC(snake.get(i).getColumn() + 1);
					}
					else
						snake.get(i).changeC(snake.get(i).getColumn() + 1);
				}
			}
			else
			{
				snake.get(i).changeR(snake.get(i-1).getRow());
				snake.get(i).changeC(snake.get(i-1).getColumn());
				snake.get(i).changeDirection(snake.get(i-1).getDirection());
			}
		}
		for(int i = 0; i < snake.size(); i++)
		{
			game[snake.get(i).getRow()][snake.get(i).getColumn()] = snake.get(i);
		}
	}
	//Clears Board, and puts everything back on except the snake
	public void resetBoard()
	{
		for(int row = 0; row < 55; row++)
		{
			for(int col = 0; col < 75; col++)
			{
				game[row][col] = new PieceP1(row, col);
				game[row][col].changeFloor(true);
			}
		}
		for(int col = 0; col < 75; col++)
		{
			game[0][col].changeWall(true);
			game[0][col].changeFloor(false);
			game[54][col].changeWall(true);
			game[54][col].changeFloor(false);
		}
		for(int row = 0; row < 55; row++)
		{
			game[row][0].changeWall(true);
			game[row][0].changeFloor(false);
			game[row][74].changeWall(true);
			game[row][74].changeFloor(false);
		}
		game[appleR][appleC].changeApple(true);
		game[appleR][appleC].changeFloor(false);
	}
	//Makes the Button board mirror the object board
	public void updateBoard()
	{
		for(int row = 0; row < 55; row++)
		{
			for(int col = 0; col < 75; col++)
			{
				if(game[row][col].getFloor())
				{
					board[row][col].setBackground(new Color(0, 0, 168));
				}
				else if(game[row][col].getWall())
				{
					board[row][col].setBackground(new Color(252, 84, 84));
				}
				else if(game[row][col].getSnake())
				{
					board[row][col].setBackground(Color.YELLOW);
				}
				else if(game[row][col].getApple())
				{
					board[row][col].setBackground(Color.RED);
				}
			}
		}
	}
	//Checks if the snake head hits the apple, generates new apple and sets addSnake to true
	public void eatApple()
	{
		if(snake.get(0).getRow() == appleR && snake.get(0).getColumn() == appleC)
		{
			addSnake = true;
			newApple();
		}
	}
	//Generates location of new apple as long as it is the floor and nothing else
	public void newApple()
	{
		appleR = (int)(Math.random() * 54);
		appleC = (int)(Math.random() * 74);
		while(!(game[appleR][appleC].getFloor()))
		{
			appleR = (int)(Math.random() * 54);
			appleC = (int)(Math.random() * 74);
		}
		game[appleR][appleC].changeFloor(false);
		game[appleR][appleC].changeApple(true);
		board[appleR][appleC].setBackground(Color.RED);
	}
	//Sets the piece of floor behind the last snake piece, to a snake piece, based on the movement of the last piece
	public void addSnake()
	{
		if(snake.get(snake.size() - 1).getDirection().equals("U"))
		{
			for(int i = 1; i < 2; i++)
			{
				if(snake.get(snake.size() - 1).getRow() + (i * 1) < 54)
				{
					snake.add(game[snake.get(snake.size() - 1).getRow() + (i * 1)][snake.get(snake.size() - 1).getColumn()]);
					game[snake.get(snake.size() - 1).getRow() + (i * 1)][snake.get(snake.size() - 1).getColumn()].changeFloor(false);
					game[snake.get(snake.size() - 1).getRow() + (i * 1)][snake.get(snake.size() - 1).getColumn()].changeSnake(true);
				}
			}
		}
		if(snake.get(snake.size() - 1).getDirection().equals("D"))
		{
			for(int i = 1; i < 2; i++)
			{
				snake.add(game[snake.get(snake.size() - 1).getRow() - (i * 1)][snake.get(snake.size() - 1).getColumn()]);
				game[snake.get(snake.size() - 1).getRow() - (i * 1)][snake.get(snake.size() - 1).getColumn()].changeFloor(false);
				game[snake.get(snake.size() - 1).getRow() - (i * 1)][snake.get(snake.size() - 1).getColumn()].changeSnake(true);
			}
		}
		if(snake.get(snake.size() - 1).getDirection().equals("L"))
		{
			for(int i = 1; i < 2; i++)
			{
				snake.add(game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() + (i * 1)]);
				game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() + (i * 1)].changeFloor(false);
				game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() + (i * 1)].changeSnake(true);
			}
		}
		if(snake.get(snake.size() - 1).getDirection().equals("R"))
		{
			for(int i = 1; i < 2; i++)
			{
				snake.add(game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() - (i * 1)]);
				game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() - (i * 1)].changeFloor(false);
				game[snake.get(snake.size() - 1).getRow()][snake.get(snake.size() - 1).getColumn() - (i * 1)].changeSnake(true);
			}
		}
	}
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			resetBoard();
			move();
			updateBoard();
			eatApple();
			//For each tick, adds a Snake piece to the board, until it is 5 pieces for one apple
			if(addSnake == true && count < 5)
			{
				addSnake();
				count++;
			}
			if(count == 5)
			{
				addSnake = false;
				count = 0;
			}
			//After an increase of 50 pieces, the snake moves faster by 5 miliseconds
			if(snake.size() - 1 % 50 == 0 && snake.size() > 1)
			{
				tickrate -= 5;
				tmr.setDelay(tickrate);
			}
			dies();
			length.setText("Length: " + snake.size());
			//hasMoved makes it so the user cannot input more than one direction change per tick
			hasMoved = false;
		}
	}
	//Taking arrow key inputs, changes the direction of the snake head
	private class KeyboardHandler implements KeyListener
	{
		public void keyReleased(KeyEvent event)
		{

		}
		public void keyTyped(KeyEvent event)
		{

		}
		public void keyPressed(KeyEvent event)
		{
			if(event.getKeyCode() == KeyEvent.VK_LEFT)
				if(snake.get(0).getDirection().equals("R") && snake.size() > 1)
				{
				}
				else
				{
					if(hasMoved == false)
					{
						snake.get(0).changeDirection("L");
						hasMoved = true;
					}
				}
			if(event.getKeyCode() == KeyEvent.VK_RIGHT)
				if(snake.get(0).getDirection().equals("L") && snake.size() > 1)
				{
				}
				else
				{
					if(hasMoved == false)
					{
						snake.get(0).changeDirection("R");
						hasMoved = true;
					}
				}
			if(event.getKeyCode() == KeyEvent.VK_DOWN)
				if(snake.get(0).getDirection().equals("U") && snake.size() > 1)
				{
				}
				else
				{
					if(hasMoved == false)
					{
						snake.get(0).changeDirection("D");
						hasMoved = true;
					}
				}
			if(event.getKeyCode() == KeyEvent.VK_UP)
				if(snake.get(0).getDirection().equals("D") && snake.size() > 1)
				{
				}
				else
				{
					if(hasMoved == false)
					{
						snake.get(0).changeDirection("U");
						hasMoved = true;
					}
				}
		}
	}
}