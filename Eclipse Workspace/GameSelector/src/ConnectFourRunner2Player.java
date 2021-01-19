//Jordan Wang
//Connect Four Runner
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff

import java.util.*;

public class ConnectFourRunner2Player extends JFrame
{
	private boolean player1Turn = true;
	private ConnectFour game;
	private JButton[][] grid = new JButton[6][7];
	private JPanel pnlGrid;
	private ButtonListener listener;
	public ConnectFourRunner2Player()
	{
		super("Wang Paul Connect Four");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		pnlGrid = new JPanel();
		pnlGrid.setLayout(new GridLayout(6, 7));
		game = new ConnectFour(6, 7);
		listener = new ButtonListener();

		/** Instantiates each button in the grid array, and only enables the first row of buttons
		*	Adds every button to the JPanel as well */
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				grid[i][j] = new JButton();
				grid[i][j].setEnabled(false);
				if(i == grid.length - 1)
				{
					grid[i][j].setEnabled(true);
				}
				pnlGrid.add(grid[i][j]);
			}
		}

		//Adds listeners to all the buttons
		for(int r = 0; r < 6; r++)
		{
			for(int c = 0; c < 7; c++)
			{
				if(grid[r][c] != null)
					grid[r][c].addActionListener(listener);
			}
		}
		cp.add(pnlGrid);
		setSize(750, 750);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		ConnectFourRunner2Player c = new ConnectFourRunner2Player();
	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			/** This for loop goes through the entire 2D JButton Array
			*	and checks which button was pressed, whose turn it is,
			*	enables the button above it if it doesn't go out of bounds
			*	disables the button pressed, and checks the win.
			*	The 2D grid of JButtons correspond to the 2D array of ints in the
			*	Connect 4 object */
			for(int i = 0; i < grid.length; i++)
			{
				for(int j = 0; j < grid[i].length; j++)
				{
					if(grid[i][j] != null && source == grid[i][j])
					{
						grid[i][j].removeActionListener(this);
						if(player1Turn == true)
						{
							game.insertPiece(i, j, 1);
							updateBoard();
							if(game.checkWin(1))
							{
								JOptionPane.showMessageDialog (null, "Player 1 won!");
								dispose();
								new ConnectFourRunner2Player();
							}
							else
							{
								if(i - 1 > -1 && game.getBoard()[i-1][j] == 0)
								{
									grid[i-1][j].setEnabled(true);
								}
							}
						}
						else
						{
							game.insertPiece(i, j, 2);
							updateBoard();
							if(game.checkWin(2))
							{
								JOptionPane.showMessageDialog (null, "Player 2 won!");
								dispose();
								new ConnectFourRunner2Player();
							}
							else
							{
								if(i - 1 > -1 && game.getBoard()[i-1][j] == 0)
								{
									grid[i-1][j].setEnabled(true);
								}
							}
						}
						if(game.checkDraw())
						{
							JOptionPane.showMessageDialog (null, "It's a draw!");
							dispose();
							new ConnectFourRunner2Player();
						}
					}
				}
			}
		}
	}
	/** This method goes through the entire array in the Connect Four object
	*	and looks which location has the number 1 (corresponding to player 1)
	*	and which location has the number 2 (corresponding to player 2).
	*	Depending on which number it is, the color of the button in the same location is changed */
	public void updateBoard()
	{
		player1Turn = game.playerTurn(player1Turn);
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(game.getBoard()[i][j] == 1)
				{
					grid[i][j].setBackground(Color.RED);
				}
				else if(game.getBoard()[i][j] == 2)
				{
					grid[i][j].setBackground(Color.YELLOW);
				}
				else
					grid[i][j].setBackground(null);
			}
		}
	}
}