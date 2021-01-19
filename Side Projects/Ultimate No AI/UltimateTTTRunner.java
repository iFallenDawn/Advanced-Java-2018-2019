//Jordan Wang
//TicTacToe
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import java.awt.*; // abstract windowing toolkit
import java.awt.event.*; // respond to windows events
import javax.swing.*; // JGui stuff
import javax.swing.border.EmptyBorder;

public class UltimateTTTRunner extends JFrame
{
	private int turns = 0;
	private boolean player1Turn = true;
	private UltimateTTT game;
	private int dim = 3;
	private JButton[][][][] singleGrid = new JButton[dim][dim][dim][dim];
	//biggerboard row, biggerboard column, board row, board column
	private JPanel[][] allGames = new JPanel[dim][dim];
	private ButtonListener listener;
	private JPanel currentPanel;
	public UltimateTTTRunner()
	{
		super("Wang Ultimate TicTacToe");
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(dim, dim));
		game = new UltimateTTT(dim, dim);
		listener = new ButtonListener();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				allGames[pRow][pCol] = new JPanel();
				allGames[pRow][pCol].setLayout(new GridLayout(dim, dim));
				allGames[pRow][pCol].setBorder(new EmptyBorder(10, 10, 10, 10));
				for(int btnRow = 0; btnRow < dim; btnRow++)
				{
					for(int btnCol = 0; btnCol < dim; btnCol++)
					{
						singleGrid[pRow][pCol][btnRow][btnCol] = new JButton();
						singleGrid[pRow][pCol][btnRow][btnCol].setPreferredSize(new Dimension(20, 20));
						singleGrid[pRow][pCol][btnRow][btnCol].addActionListener(listener);
						allGames[pRow][pCol].add(singleGrid[pRow][pCol][btnRow][btnCol]);
					}
				}
				cp.add(allGames[pRow][pCol]);
			}
		}

		setSize(dim*200, dim*200);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		UltimateTTTRunner t = new UltimateTTTRunner();
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			for(int pRow = 0; pRow < dim; pRow++)
			{
				for(int pCol = 0; pCol < dim; pCol++)
				{
					for(int btnRow = 0; btnRow < dim; btnRow++)
					{
						for(int btnCol = 0; btnCol < dim; btnCol++)
						{
							if(singleGrid != null && source == singleGrid[pRow][pCol][btnRow][btnCol])
							{
								turns+= 1;
								singleGrid[pRow][pCol][btnRow][btnCol].removeActionListener(this);
								if(player1Turn == true)
								{
									game.updateBoards(pRow, pCol, btnRow, btnCol, 1);
									updateGUI();
									if(game.checkWin(1))
									{
										JOptionPane.showMessageDialog (null, "Player 1 won!");
										dispose();
										new UltimateTTTRunner();
									}
									player1Turn = game.playerTurn(player1Turn);
								}
								else
								{
									game.updateBoards(pRow, pCol, btnRow, btnCol, 2);
									updateGUI();
									if(game.checkWin(2))
									{
										JOptionPane.showMessageDialog (null, "Player 2 won!");
										dispose();
										new UltimateTTTRunner();
									}
									player1Turn = game.playerTurn(player1Turn);
								}
								System.out.println("Turn: " + turns);
								System.out.println("Weight of board player 1 at [" + pRow + ", " + pCol + "]: " + game.getGames()[pRow][pCol].getSingleWeight(1));
								System.out.println("Total Weight Player 1: " + game.getTotalWeight(1) + "\n");
								System.out.println("Weight of board player 2 at [" + pRow + ", " + pCol + "]: " + game.getGames()[pRow][pCol].getSingleWeight(2));
								System.out.println("Total Weight Player 2: " + game.getTotalWeight(2) + "\n");
								System.out.println("Total Weight Value: " + (game.getTotalWeight(1) - game.getTotalWeight(2) + "\n"));
								disableButtons();
								if(game.getGames()[btnRow][btnCol].checkDraw() || game.getGames()[pRow][pCol].checkDraw())
								{
									enableAllButtons();
								}
								else
								{
									enableButtons(btnRow, btnCol);
								}
							}
						}
					}
				}
			}
		}
	}

	public void disableButtons()
	{
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				for(int btnRow = 0; btnRow < dim; btnRow++)
				{
					for(int btnCol = 0; btnCol < dim; btnCol++)
					{
						singleGrid[pRow][pCol][btnRow][btnCol].setEnabled(false);
					}
				}
			}
		}
	}

	public void enableButtons(int row, int col)
	{
		for(int i = 0; i < dim; i++)
		{
			for(int j = 0; j < dim; j++)
			{
				singleGrid[row][col][i][j].setEnabled(true);
			}
		}
	}

	public void enableAllButtons()
	{
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				for(int btnRow = 0; btnRow < dim; btnRow++)
				{
					for(int btnCol = 0; btnCol < dim; btnCol++)
					{
						singleGrid[pRow][pCol][btnRow][btnCol].setEnabled(true);
					}
				}
			}
		}
	}
	public void updateGUI()
	{
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				for(int btnRow = 0; btnRow < dim; btnRow++)
				{
					for(int btnCol = 0; btnCol < dim; btnCol++)
					{
						if(game.getGames()[pRow][pCol].getBoard()[btnRow][btnCol] == 1)
						{
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(Color.RED);
						}
						else if(game.getGames()[pRow][pCol].getBoard()[btnRow][btnCol] == 2)
						{
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(Color.YELLOW);
						}
						else
						{
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(null);
						}
					}
				}
			}
		}
	}
}
//https://docs.riddles.io/ultimate-tic-tac-toe/rules