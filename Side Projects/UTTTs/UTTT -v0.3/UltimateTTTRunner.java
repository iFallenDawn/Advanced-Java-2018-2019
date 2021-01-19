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
	private boolean hints = false;
	private boolean markGray = false;
	private boolean markRed = false;
	private boolean markYellow = false;
	private boolean player1Turn = true;
	private final Color redMarker = new Color(255,100,100);
	private final Color yellowMarker = new Color(255,255,153);
	private final Color grayMarker = Color.GRAY.brighter();
//	private UltimateTTT game;
	private int dim = 3;
	private int turns = 0;
	private JButton[][][][] singleGrid = new JButton[dim][dim][dim][dim];
	private JButton btnInstructions, btnMarkgray, btnMarkred, btnMarkyellow, btnHint, btnUndo, btnReset;
	//biggerboard row, biggerboard column, board row, board column
	private JPanel[][] allGames = new JPanel[dim][dim];
	private JPanel currentPanel;
	private JTextArea jtaMoves, jtaRating;
	private JScrollPane scroll;
	private ButtonListener listener;
	private int active = -1;
	private JLabel lblInstructions;
	private BruteForceTree bft;
	private int[][][][] game = new int[dim][dim][dim][dim];

	public UltimateTTTRunner()
	{
		super("Wang Ultimate TicTacToe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel cp = new JPanel();
	//	ImageIcon image = new ImageIcon("instructions.jpg");
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("instructions.jpg")));
		lblInstructions = new JLabel(image);
		cp.setLayout(new GridLayout(dim, dim));
	//	game = new UltimateTTT(dim, dim);
		game = new int[dim][dim][dim][dim];
		listener = new ButtonListener();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 10);

		btnInstructions = new JButton("Instructions");
		btnInstructions.addActionListener(listener);
		btnMarkgray = new JButton("Mark Gray");
		btnMarkgray.addActionListener(listener);
		btnMarkred = new JButton("Mark Red");
		btnMarkred.addActionListener(listener);
		btnMarkred.setPreferredSize(new Dimension(98,20));
		btnMarkyellow = new JButton("Mark Yellow");
		btnMarkyellow.addActionListener(listener);
		btnHint = new JButton("Enable Hints");
		btnHint.addActionListener(listener);
		btnReset = new JButton("Play again?");
		btnReset.addActionListener(listener);
		btnReset.setEnabled(false);
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(listener);
		btnUndo.setEnabled(false);


		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new BorderLayout());

		JPanel pnlEnd = new JPanel();
		pnlEnd.setLayout(new BorderLayout());
		pnlEnd.add(btnUndo, BorderLayout.SOUTH);
		pnlEnd.add(btnReset, BorderLayout.NORTH);

		JPanel pnlMarkers = new JPanel();
		pnlMarkers.setLayout(new BorderLayout());
		pnlMarkers.add(btnMarkred, BorderLayout.WEST);
		pnlMarkers.add(btnMarkgray, BorderLayout.CENTER);
		pnlMarkers.add(btnMarkyellow, BorderLayout.EAST);

		pnlInfo.add(pnlEnd, BorderLayout.WEST);
		pnlInfo.add(pnlMarkers, BorderLayout.NORTH);
		pnlInfo.add(btnInstructions, BorderLayout.SOUTH);
		pnlInfo.add(btnHint, BorderLayout.EAST);

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
		c.add(pnlInfo, BorderLayout.SOUTH);
		setSize(700, 500);
		setVisible(true);
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
			else if(source == btnMarkgray)
			{
				markRed = false;
				markYellow = false;
				markGray = !markGray;
				if(markGray)
					enableAllButtons();
				else if (active != -1)
				{
					disableButtons();
					enableButtons(active / 3, active % 3);
				}
				else
					enableAllButtons();
				//System.out.println("MarkGray: " + markGray);
			}
			else if(source == btnMarkred)
			{
				markRed = !markRed;
				markYellow = false;
				markGray = false;
				if(markRed)
					enableAllButtons();
				else if (active != -1)
				{
					disableButtons();
					enableButtons(active / 3, active % 3);
				}
				else
					enableAllButtons();
				//System.out.println("MarkRed: " + markRed);
			}
			else if(source == btnMarkyellow)
			{
				markRed = false;
				markYellow = !markYellow;
				markGray = false;
				if(markYellow)
					enableAllButtons();
				else if (active != -1)
				{
					disableButtons();
					enableButtons(active / 3, active % 3);
				}
				else
					enableAllButtons();
				//System.out.println("MarkYellow: " + markYellow);
			}
			else if(source == btnHint)
			{
				if (bft != null)
				{
					btnHint.setEnabled(false);
					hints = true;
					//BruteForceTree bft = new BruteForceTree(game.getState(), 2, active);
					ArrayList<Integer> moves = bft.getAll();
					setNextMoves(moves);
				}
			}
			else if(source == btnReset)
			{
				dispose();
				new UltimateTTTRunner();
			}
			else if(source == btnUndo)
			{
				//Only available for two player
			}
			else
			{
				if(markGray || markRed || markYellow)
				{
					for(int pRow = 0; pRow < dim; pRow++)
					{
						for(int pCol = 0; pCol < dim; pCol++)
						{
							for(int btnRow = 0; btnRow < dim; btnRow++)
							{
								for(int btnCol = 0; btnCol < dim; btnCol++)
								{
									if(source == singleGrid[pRow][pCol][btnRow][btnCol])
									{
										if(singleGrid[pRow][pCol][btnRow][btnCol].getBackground() != grayMarker && markGray == true)
											singleGrid[pRow][pCol][btnRow][btnCol].setBackground(grayMarker);
										else if(singleGrid[pRow][pCol][btnRow][btnCol].getBackground() != redMarker && markRed == true)
											singleGrid[pRow][pCol][btnRow][btnCol].setBackground(redMarker);
										else if(singleGrid[pRow][pCol][btnRow][btnCol].getBackground() != yellowMarker && markYellow == true)
											singleGrid[pRow][pCol][btnRow][btnCol].setBackground(yellowMarker);
										else
											singleGrid[pRow][pCol][btnRow][btnCol].setBackground(new JButton().getBackground());
									}
								}
							}
						}
					}
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
										jtaMoves.append("\n" + turns + "\t" + tableMoves(pRow, pCol, btnRow, btnCol));
										if(player1Turn == true)
										{
										//	game.updateBoards(pRow, pCol, btnRow, btnCol, 1);
											game[pRow][pCol][btnRow][btnCol] = 1;
											if (turns != 1)
												bft.makeMove(UTTTUtility.powerSeries(new int[]{pRow, pCol, btnRow, btnCol}, singleGrid.length));
											updateGUI();
											//if(game.checkWin(1))
											if(UTTTUtility.isWin(game, 1))
											{
												JOptionPane.showMessageDialog (null, "Player 1 won!");
												btnReset.setEnabled(true);
												disableAllButtons();
											}
										//	player1Turn = game.playerTurn(player1Turn);
											player1Turn = !player1Turn;
										}
									/*	else
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
										}*/
										disableButtons();
									//	if(game.getGames()[btnRow][btnCol].checkDraw() || game.getGames()[pRow][pCol].checkDraw())
										active = 3 * btnRow + btnCol;
										if(UTTTUtility.possibleMoves(game, active).isEmpty())
										{
											active = -1;
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
					if (bft == null)
						bft = new BruteForceTree(game, 1, active);
					//	bft = new BruteForceTree(game.getState(), 1, active);
					int move = bft.bestMove();
				//	System.out.println(move);
					int btnCol = move % 3;
					move /= 3;
					int btnRow = move % 3;
					move /= 3;
					int pCol = move % 3;
					move /= 3;
					int pRow = move % 3;
				//	game.updateBoards(pRow, pCol, btnRow, btnCol, 2);
					game[pRow][pCol][btnRow][btnCol] = 2;
					jtaMoves.append("\t" + tableMoves(pRow, pCol, btnRow, btnCol));
					updateGUI();
					singleGrid[pRow][pCol][btnRow][btnCol].removeActionListener(this);
				//	if(game.checkWin(2))
					if(UTTTUtility.isWin(game, 2))
					{
						JOptionPane.showMessageDialog (null, "Player 2 won!");
						btnReset.setEnabled(true);
						disableAllButtons();
					}
					else
					{
					//	player1Turn = game.playerTurn(player1Turn);
						player1Turn = !player1Turn;
						disableButtons();
						enableButtons(btnRow, btnCol);
						active = 3 * btnRow + btnCol;
					}
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
						jtaRating.append("" + ((((int) (Math.round(1000 / rating) + 1)) / 2) - 1));
					else
						jtaRating.append(String.format("%.2f", rating));
					int min = bft.min();
					int max = bft.max();
					jtaRating.append("\t\t\t\t\t(Depth: " + min + " - " + max + ")");
					bft.makeMove(UTTTUtility.powerSeries(new int[]{pRow, pCol, btnRow, btnCol}, singleGrid.length));
					if (hints)
					{
						ArrayList<Integer> moves = bft.getAll();
						setNextMoves(moves);
					}
				}
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

	public void disableAllButtons()
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
					//	if(game.getGames()[pRow][pCol].getBoard()[btnRow][btnCol] == 1)
						if(game[pRow][pCol][btnRow][btnCol] == 1)
						{
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(Color.RED);
						}
				//		else if(game.getGames()[pRow][pCol].getBoard()[btnRow][btnCol] == 2)
						else if(game[pRow][pCol][btnRow][btnCol] == 2)
						{
							singleGrid[pRow][pCol][btnRow][btnCol].setBackground(Color.YELLOW);
						}
					//	else if (singleGrid[pRow][pCol][btnRow][btnCol].getBackground() != Color.GRAY)
					//	{
					//		singleGrid[pRow][pCol][btnRow][btnCol].setBackground(null);
					//	}
					}
				}
			}
		}
	}
}
//https://docs.riddles.io/ultimate-tic-tac-toe/rules