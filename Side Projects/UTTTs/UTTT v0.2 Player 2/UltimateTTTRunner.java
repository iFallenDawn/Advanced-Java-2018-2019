//Jordan Wang
//TicTacToe
//Spec: Creates a 2D array of JButtons that reflects the int array in the Connect Four Object,
//		and handles how the user interface looks.

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.*; // JGui stuff
import java.util.*;

public class UltimateTTTRunner extends JFrame
{
	private boolean player1Turn = true;
	private UltimateTTT game;
	private int dim = 3;
	private int turns = 0;
	private JButton[][][][] singleGrid = new JButton[dim][dim][dim][dim];
	private JButton btnInstructions;
	//biggerboard row, biggerboard column, board row, board column
	private JPanel[][] allGames = new JPanel[dim][dim];
	private JPanel currentPanel;
	private JTextArea jtaMoves, jtaRating;
	private JScrollPane scroll;
	private ButtonListener listener;
	private int active = -1;
	private JLabel lblInstructions;

	public UltimateTTTRunner()
	{
		super("Wang Player 2 Ultimate TicTacToe");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel cp = new JPanel();
		ImageIcon image = new ImageIcon("instructions.jpg");
		lblInstructions = new JLabel(image);
		cp.setLayout(new GridLayout(dim, dim));
		game = new UltimateTTT(dim, dim);
		listener = new ButtonListener();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 10);
		btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(listener);
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				allGames[pRow][pCol] = new JPanel();
				allGames[pRow][pCol].setLayout(new GridLayout(dim, dim));
				allGames[pRow][pCol].setBorder(border);
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

		jtaMoves = new JTextArea(20, 20);
		jtaMoves.setFont(new Font("Calibri", Font.PLAIN, 14));
		jtaMoves.append("Turns\tP1\tP2");
		jtaMoves.setEditable(false);
		jtaMoves.setLineWrap(true);

		scroll = new JScrollPane(jtaMoves);

		jtaRating = new JTextArea(1, 1);
		jtaRating.setFont(new Font("Calibri", Font.PLAIN, 18));
		jtaRating.append("Rating: ");
		jtaRating.setEditable(false);

		BruteForceTree.reset();
		c.add(cp, BorderLayout.CENTER);
		c.add(scroll, BorderLayout.EAST);
		c.add(jtaRating, BorderLayout.NORTH);
		c.add(btnInstructions, BorderLayout.SOUTH);
		setSize(700, 500);
		setVisible(true);



		BruteForceTree bft = new BruteForceTree(game.getState(), 2, active);
		int move = bft.bestMove();
	//	System.out.println(move);
		int btnCol = move % 3;
		move /= 3;
		int btnRow = move % 3;
		move /= 3;
		int pCol = move % 3;
		move /= 3;
		int pRow = move % 3;
		game.updateBoards(pRow, pCol, btnRow, btnCol, 1);
		turns++;
		jtaMoves.append("\n" + turns + "\t" + tableMoves(pRow, pCol, btnRow, btnCol) + "\t");
		updateGUI();
		singleGrid[pRow][pCol][btnRow][btnCol].removeActionListener(listener);
		if(game.checkWin(1))
		{
			JOptionPane.showMessageDialog (null, "Player 2 won!");
			dispose();
			new UltimateTTTRunner();
		}
		player1Turn = game.playerTurn(player1Turn);
		disableButtons();
		enableButtons(btnRow, btnCol);
		active = 3 * btnRow + btnCol;
//		ArrayList<Integer> moves = bft.getAllNext();
//		setNextMoves(moves);
		double rating = bft.getRating();
		jtaRating.setText("Rating: ");
		if (Math.abs(rating) > 10)
			jtaRating.append("#");
		if (rating > 0)
			jtaRating.append("+");
		else if (rating < 0)
			jtaRating.append("-");
		rating = Math.abs(rating);
		if (rating > 10)
			jtaRating.append("" + ((((int) Math.round(1000 / rating)) / 2) - 1));
		else
		jtaRating.append(String.format("%.2f", rating));
	}
	public static void main(String[] args)
	{
	//	System.out.println(UTTTUtility.powerSeries(new int[]{1, 2, 2}, 3));
		UltimateTTTRunner t = new UltimateTTTRunner();
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			clearBack();
			Object source = event.getSource();
			if(source == btnInstructions)
			{
				JOptionPane.showMessageDialog(null, lblInstructions, "About", JOptionPane.PLAIN_MESSAGE, null);
			}
			else
			{
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
									singleGrid[pRow][pCol][btnRow][btnCol].removeActionListener(this);
									turns++;
									jtaMoves.append(tableMoves(pRow, pCol, btnRow, btnCol));
									if(player1Turn == false)
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
									disableButtons();
									if(game.getGames()[btnRow][btnCol].checkDraw() || game.getGames()[pRow][pCol].checkDraw())
									{
										enableAllButtons();
									}
									else
									{
										enableButtons(btnRow, btnCol);
									}
									active = 3 * btnRow + btnCol;
								}
							}
						}
					}
				}
				BruteForceTree bft = new BruteForceTree(game.getState(), 2, active);
				int move = bft.bestMove();
			//	System.out.println(move);
				int btnCol = move % 3;
				move /= 3;
				int btnRow = move % 3;
				move /= 3;
				int pCol = move % 3;
				move /= 3;
				int pRow = move % 3;
				game.updateBoards(pRow, pCol, btnRow, btnCol, 1);
				jtaMoves.append("\n" + turns + "\t" + tableMoves(pRow, pCol, btnRow, btnCol) + "\t");
				updateGUI();
				singleGrid[pRow][pCol][btnRow][btnCol].removeActionListener(this);
				if(game.checkWin(1))
				{
					JOptionPane.showMessageDialog (null, "Player 2 won!");
					dispose();
					new UltimateTTTRunner();
				}
				player1Turn = game.playerTurn(player1Turn);
				disableButtons();
				enableButtons(btnRow, btnCol);
				active = 3 * btnRow + btnCol;
		//		ArrayList<Integer> moves = bft.getAllNext();
		//		setNextMoves(moves);
				double rating = bft.getRating();
				jtaRating.setText("Rating: ");
				if (Math.abs(rating) > 10)
					jtaRating.append("#");
				if (rating > 0)
					jtaRating.append("+");
				else if (rating < 0)
					jtaRating.append("-");
				rating = Math.abs(rating);
				if (rating > 10)
					jtaRating.append("" + ((((int) Math.round(1000 / rating)) / 2) - 1));
				else
					jtaRating.append(String.format("%.2f", rating));
			}
		}
	}

	public void setNextMoves(ArrayList<Integer> moves)
	{
		for (int move : moves)
		{
			int btnCol = move % 3;
			move /= 3;
			int btnRow = move % 3;
			move /= 3;
			int pCol = move % 3;
			move /= 3;
			int pRow = move % 3;
			singleGrid[pRow][pCol][btnRow][btnCol].setBackground(Color.GREEN);

		}
	}

	public String tableMoves(int pRow, int pCol, int btnRow, int btnCol)
	{
		String result = "";
		if(pCol == 0)
			result+= "A";
		else if(pCol == 1)
			result+= "B";
		else if(pCol == 2)
			result+= "C";
		result+= 3 - pRow;

		if(btnCol == 0)
			result+= "a";
		else if(btnCol == 1)
			result+= "b";
		else if(btnCol == 2)
			result+= "c";
		result+= 3 - btnRow;
		return result;
	}

	public void clearBack()
	{
		for(int pRow = 0; pRow < dim; pRow++)
		{
			for(int pCol = 0; pCol < dim; pCol++)
			{
				for(int btnRow = 0; btnRow < dim; btnRow++)
				{
					for(int btnCol = 0; btnCol < dim; btnCol++)
					{
						if (singleGrid[pRow][pCol][btnRow][btnCol].getBackground() == Color.GREEN)
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(new JButton().getBackground());
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