//Jordan Wang
//Connect Four Runner
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff

import java.util.*;

public class ConnectFourRunner extends JFrame
{
	private boolean player1Turn = true;
	private ConnectFour game;
	private JButton[][] grid = new JButton[6][7];
	private JPanel pnlGrid;
	private ButtonListener listener;
	public ConnectFourRunner()
	{
		super("Wang Connect Four");
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
	//	ConnectFourRunner c = new ConnectFourRunner();
	/*	for (int i = 0; i < 10000; i++)
		{
			int[][] b = ConnectMCTS.randomBoard(6, 7);
			ConnectMCTS.printBoard(b);
		} */
	//	ConnectMCTS.test(Math.sqrt(2), Math.sqrt(2), 100);
	//	int[][] board = {{0, 1, 2, 3}, {1, 2, 5, 7}};
	//	System.out.println(Arrays.toString(board));
		ConnectMCTS.write_file(1000);



	}
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
	/*		int player = 2;
			while(player > 0)
			{
				ConnectMCTS mcts = new ConnectMCTS(game.getBoard(), player, Math.sqrt(2));
				int move = mcts.bestMove();
				int j = 0;
				while (grid[j][move].isEnabled() == false) { j++; }
				game.insertPiece(j, move, 3 - player);
				updateBoard();
				grid[j][move].setEnabled(false);
				if (j > 0)
					grid[j - 1][move].setEnabled(true);
				player1Turn = true;
				if(game.checkWin(3 - player))
				{
					JOptionPane.showMessageDialog (null, "Player " + (3 - player) + " won!");
					dispose();
					new ConnectFourRunner();
					break;
				}
				player = 3 - player;
			} */
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
								new ConnectFourRunner();
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
								new ConnectFourRunner();
							}
							else
							{
								if(i - 1 > -1 && game.getBoard()[i-1][j] == 0)
								{
									grid[i-1][j].setEnabled(true);
								}
							}
						}
					}
				}
			}
			ConnectMCTS mcts = new ConnectMCTS(game.getBoard(), 1, Math.sqrt(2), 100000);
			int move = mcts.bestMove();
			int j = 0;
			while (grid[j][move].isEnabled() == false) { j++; }
			game.insertPiece(j, move, 2);
			updateBoard();
			grid[j][move].setEnabled(false);
			if (j > 0)
				grid[j - 1][move].setEnabled(true);
			player1Turn = true;
			if(game.checkWin(2))
			{
				JOptionPane.showMessageDialog (null, "Player 2 won!");
				dispose();
				new ConnectFourRunner();
			}
			//System.out.println(game);
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