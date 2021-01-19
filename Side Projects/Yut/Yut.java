//Jordan Wang
//Connect Four Object
//Spec: Creates a 2D array of ints and handles all the logic regarding wins and pieces being placed down

import java.util.Arrays;

public class Yut
{
	private boolean switchTurn;
	private int empty = 0;
	private int player1 = 1;
	private int player2 = 2;
	private int player1Pieces;
	private int player2Pieces;
	private int rows;
	private int cols;
	private int[][]outerGrid;
	private int[][]smallerGrid;
	public Yut()
	{
		switchTurn = true;
		outerGrid = new int[0][0];
		smallerGrid = new int[0][0];
		player1Pieces = 0;
		player2Pieces = 0;
	}
	public Yut(int rows, int cols, int numPieces)
	{
		player1Pieces = numPieces;
		player2Pieces = numPieces;
		switchTurn = true;
		this.rows = rows;
		this.cols = cols;
		outerGrid = new int[this.rows][this.cols];
		smallerGrid = new int[this.rows - 1][this.cols - 1];
	}

	//Switches the player turns
	public boolean playerTurn(boolean turn)
	{
		if(turn == true)
		{
			switchTurn = false;
			System.out.println("Player 2's turn!\n");
		}
		else
		{
			switchTurn = true;
			System.out.println("Player 1's turn!\n");
		}
		return switchTurn;
	}

	public void insertPiece(int x, int y, int player)
	{

	}

	public boolean validPlacement(int x, int y)
	{
		return false;
	}

	public boolean checkWin(int player)
	{
		return false;
	}

	//Returns the outer board to be used in the runner
	public int[][] getOuterBoard()
	{
		return outerGrid;
	}

	public int[][] getSmallerBoard()
	{
		return smallerGrid;
	}

	//Only used for testing purposes to print out the board
	public String toString()
	{
		String result = "";
		return result;
	}
}