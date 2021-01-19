//Jordan Wang
//Ultimate Tic Tac Toe
//Spec: 3x3 array of Tic Tac Toe games

import java.util.Arrays;

public class UltimateTTT
{
	private boolean switchTurn;
	private int rows;
	private int cols;
	private TicTacToe [][]board;
	public UltimateTTT()
	{
		board = new TicTacToe[0][0];
		switchTurn = true;
	}
	public UltimateTTT(int r, int c)
	{
		rows = r;
		cols = c;
		board = new TicTacToe[rows][cols];
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[i].length; j++)
			{
				board[i][j] = new TicTacToe(rows, cols);
			}
		}
	}
	//Switches the player turns
	public boolean playerTurn(boolean turn)
	{
		if(turn == true)
		{
			switchTurn = false;
			//System.out.println("Player 2's turn!\n");
		}
		else
		{
			switchTurn = true;
			//System.out.println("Player 1's turn!\n");
		}
		return switchTurn;
	}

	public TicTacToe[][] getGames() { return board; }

	public boolean checkWin(int player)
	{
		for(TicTacToe[] t:board)
		{
			for(TicTacToe g:t)
			{
				if(g.checkWin(player))
					return true;
			}
		}
		return false;
	}

	public void updateBoards(int bRow, int bCol, int sRow, int sCol, int player)
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(i == bRow && j == bCol)
				{
					board[i][j].insertPiece(sRow, sCol, player);
				}
			}
		}
	}
	public int getTotalWeight(int player)
	{
		int total = 0;
		for(TicTacToe[] b: board)
		{
			for(TicTacToe t: b)
			{
				total+= t.getSingleWeight(player);
			}
		}
		return total;
	}
}
