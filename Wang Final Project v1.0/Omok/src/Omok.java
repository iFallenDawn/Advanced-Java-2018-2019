//Jordan Wang
//Omok Object
//Spec: Creates a 2D array of ints and handles all the logic regarding wins and pieces being placed down

public class Omok
{
	private boolean switchTurn;
	private int rows;
	private int cols;
	private int[][]grid;
	/**Default constructor
	 */
	public Omok()
	{
		switchTurn = true;
		grid = new int[0][0];
	}
	/**Initialization constructor
	 * @param rows The number of rows
	 * @param cols The number of columns
	 */
	public Omok(int rows, int cols)
	{
		switchTurn = true;
		this.rows = rows;
		this.cols = cols;
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
	/**Switches the player turns
	 * @param turn true or false if it's player one's turn
	 * @return switchTurn whoever's turn it is
	 */
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
		//	System.out.println("Player 1's turn!\n");
		}
		return switchTurn;
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
	/** Checks the win given a specific number to check for, player 1 means the int being looked for is 1,
	*	player 2 means the int being looked for is 2 
	*	@param player Whichever player's turn it is
	*	@return boolean If that player has won
	*/
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
				if(count == 5)
				{
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
				if(count == 5)
				{
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
							if(i + 3 < rows && j + 3 < cols && grid[i+3][j+3] == player)
							{
								if(i + 4 < rows && j + 4 < cols && grid[i+4][j+4] == player)
									return true;
							}
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
							if(i + 3 < rows && j - 3 > -1 && grid[i+3][j-3] == player)
							{
								if(i + 4 < rows && j - 4 > -1 && grid[i+4][j-4] == player)
									return true;
							}
						}
					}
				}
			}
			count = 0;
		}
		return false;
	}


	/**
	 * Gets the state of the board
	 * @return grid The board
	 */
	public int[][] getBoard()
	{
		return grid;
	}

	/**
	 * Only used for testing purposes to print out the board
	 */
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