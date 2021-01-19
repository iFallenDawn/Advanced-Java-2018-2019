//Jordan Wang
//Tic Tac Toe
//Spec: A single board of tic tac toe

import java.util.Arrays;

public class TicTacToe
{
	private int empty = 0;
	private int player1 = 1;
	private int player2 = 2;
	private int rows;
	private int cols;
	private int[][] grid;
	public TicTacToe()
	{
		grid = new int[0][0];
	}
	public TicTacToe(int r, int c)
	{
		rows = r;
		cols = c;
		grid = new int[rows][cols];
		/*For loop used for debugging purposes, just prints out the entire board
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				System.out.print(grid[i][j] + "\t");
			}
			System.out.println();
		}*/
	}

	/** After checking validity of the location, the value is changed to the player value
	*	Each number in grid corresponds to a player or the location being empty */
	public void insertPiece(int x, int y, int player)
	{
		if(validPlacement(x, y))
			grid[x][y] = player;
	}
	/** This method checks if the player can place their piece at this location,
	*	they're only allowed to if the value at that location in the 2D int array is 0 */
	public boolean validPlacement(int x, int y)
	{
		if(grid[x][y] == 0)
			return true;
		return false;
	}
	public boolean checkWin(int player)
	{
		int count = 0;
		//Checking horizontals
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(grid[i][j] == player)
					count++;
				else
				{
					count = 0;
				}
				if(count == 3)
				{
					//System.out.println("Horizontal");
					return true;
				}
			}
			count = 0;
		}
		//Checking verticals
		for(int i = 0; i < cols; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				if(grid[j][i] == player)
				{
					count++;
					//System.out.println("i: " + i + "\tj:" + j+ " IF " + count);
				}
				else
				{
					count = 0;
					//System.out.println("i: " + i + "\tj:" + j+ " else " + count);
				}
				if(count == 3)
				{
					//System.out.println("Vertical");
					return true;
				}
			}
			count = 0;
		}
		//Checking diagonals from top left to bottom right by checking bounds first before checking location
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(grid[i][j] == player)
				{
					if(i + 1 < rows && j + 1 < cols && grid[i+1][j+1] == player)
					{
						if(i + 2 < rows && j + 2 < cols && grid[i+2][j+2] == player)
						{
							//System.out.println("Top left diagonal");
							return true;
						}
					}
				}
			}
			count = 0;
		}
		//Checking diagonals from top right to bottom left by checking bounds first before checking location
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(grid[i][j] == player)
				{
					if(i + 1 < rows && j - 1 > -1 && grid[i+1][j-1] == player)
					{
						if(i + 2 < rows && j - 2 > -1 && grid[i+2][j-2] == player)
						{
							//System.out.println("Top right diagonal");
							return true;
						}
					}
				}
			}
			count = 0;
		}
		return false;
	}

	//Checks for draws
	public boolean checkDraw()
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(grid[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	//Returns the board to be used in the runner
	public int[][] getBoard() { return grid; }

	//Only used for testing purposes to print out the board
	public String toString()
	{
		String result = "";
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				result+= grid[i][j] + "\t";
			}
			result+= "\n";
		}
		return result;
	}
}