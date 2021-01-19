//Jordan Wang
//TicTacToe
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff

public class TicTacToeRunner extends JFrame
{
	private boolean player1Turn = true;
	private TicTacToe game;
	private int row = 3;
	private int col = 3;
	private JButton[][] grid = new JButton[row][col];
	private JPanel pnlGrid;
	private ButtonListener listener;
	public TicTacToeRunner()
	{
		super("Wang Ultimate TicTacToe");
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		pnlGrid = new JPanel();
		pnlGrid.setLayout(new GridLayout(row, col));
		game = new TicTacToe(row, col);
		listener = new ButtonListener();

		/** Instantiates each button in the grid array
		*	Adds every button to the JPanel as well */
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				grid[i][j] = new JButton();
				pnlGrid.add(grid[i][j]);
			}
		}

		//Adds listeners to all the buttons
		for(int r = 0; r < row; r++)
		{
			for(int c = 0; c < col; c++)
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
		TicTacToeRunner t = new TicTacToeRunner();
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
								new TicTacToeRunner();
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
								new TicTacToeRunner();
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
				if(game.checkDraw())
				{
					JOptionPane.showMessageDialog (null, "It's a draw!");
					dispose();
					new TicTacToeRunner();
				}
			}
			//System.out.println(game);
		}
	}
	/** This method goes through the entire array in the Connect Four object
	*	and looks which location has the number 1 (corresponding to player 1)
	*	and which location has the number 2 (corresponding to player 2).
	*	Depending on which number it is, the color of the button in the same location is changed */
	/*public void updateBoard()
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
	}*/
}