//Jordan Wang
//Stretch
//Spec: Stretch stretch stretch

import java.util.ArrayList;
import java.util.Arrays;
public class Stretch
{
	private String input, result;
	private int rows, cols, start;
	private int maxPos;
	private int[] blocked;
	private int[][]grid;
	public Stretch()
	{
		result = "";
		input = "";
		rows = 0;
		cols = 0;
		start = 0;
		maxPos = 0;
		blocked = new int[0];
		grid = new int[0][0];
	}
	public Stretch(String input)
	{
		String[] temp = input.split(" ");
		result = "";
		ArrayList<String> filler = new ArrayList<String>(Arrays.asList(temp));
		rows = Integer.parseInt(filler.remove(0));
		cols = Integer.parseInt(filler.remove(0));
		start = Integer.parseInt(filler.remove(0));
		grid = new int[rows][cols];
		maxPos = 0;
		blocked = new int[Integer.parseInt(filler.remove(0))];
		for(int i = 0; i < blocked.length; i++)
		{
			blocked[i] = Integer.parseInt(filler.remove(0));
		}
	}

	public int tryA(boolean left, int maxPos)
	{
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[i].length; j++)
			{
				if(left)
				{
					if(grid[i][j] == 1 && j >= maxPos && j + 2 < cols)
					{
						if(grid[i][j+1] == 0 && grid[i][j+2] == 0)
						{
							grid[i][j] = 7;
							grid[i][j+1] = 7;
							grid[i][j+2] = 7;
							if(j + 3 < cols)
								grid[i][j+3] = 1;
							result+= "A";
							return j + 3;
						}
					}
				}
				else
				{
					if(grid[i][j] == 1 && j <= maxPos && j-2 >= 0)
					{
						if(grid[i][j-1] == 0 && grid[i][j-2] == 0)
						{
							grid[i][j] = 7;
							grid[i][j-1] = 7;
							grid[i][j-2] = 7;
							if(j - 3 >= 0)
								grid[i][j-3] = 1;
							result+= "A";
							return j-3;
						}
					}
				}
			}
		}
		return maxPos;
	}

	public int tryB(boolean left, int maxPos)
	{
		for(int i = 0; i < grid.length; i++)
		{
			if(grid[i][maxPos] == 1 && i - 2 >= 0)
			{
				if(grid[i-1][maxPos] == 0 && grid[i-2][maxPos] == 0)
				{
					grid[i][maxPos] = 1;
					grid[i-1][maxPos] = 1;
					grid[i-2][maxPos] = 1;
					if(left && maxPos + 1 < cols - 1)
					{
						grid[i-1][maxPos+1] = 2;
						grid[i][maxPos+1] += 1;
						grid[i-2][maxPos+1] +=1;
					}
					if(!left && maxPos - 1 > 0)
					{
						grid[i-1][maxPos-1] = 2;
						grid[i][maxPos - 1] += 1;
						grid[i-2][maxPos - 1] += 1;
					}
					result+= "B";
					if(left)
						return maxPos+1;
					else
						return maxPos-1;
				}
			}
			if(grid[i][maxPos] == 1 && i + 2 < rows)
			{
				if(grid[i+1][maxPos] == 0 && grid[i+2][maxPos] == 0)
				{
					grid[i][maxPos] = 7;
					grid[i+1][maxPos] = 7;
					grid[i+2][maxPos] = 7;
					if(left && maxPos+1 < cols - 1)
					{
						grid[i+1][maxPos+1] = 2;
						grid[i][maxPos+1] += 1;
						grid[i+2][maxPos+1] += 1;
					}
					if(!left && maxPos - 1 > 0)
					{
						grid[i+1][maxPos-1] = 2;
						grid[i][maxPos-1] += 1;
						grid[i+2][maxPos-1] += 1;
					}
					result+="B";
					if(left)
						return maxPos+1;
					else
						return maxPos-1;
				}
			}
		}
		return maxPos;
	}

	public int tryC(boolean left, int maxPos)
	{
		for(int i = 0; i < grid.length; i++)
		{
			if(left)
			{
				if(grid[i][maxPos] == 1 && maxPos + 1 < cols && i + 1 < rows)
				{
					if(grid[i+1][maxPos] == 0 && grid[i+1][maxPos+1] == 0)
					{
						grid[i][maxPos] = 7;
						grid[i+1][maxPos] = 7;
						grid[i+1][maxPos+1] = 7;
						if(maxPos + 2 < cols)
						{
							grid[i+1][maxPos+2] +=1;
						}
						result+="C";
						return maxPos + 2;
					}
				}
			}
			else
			{
				if(grid[i][maxPos] == 1 && maxPos - 1 > 0 && i - 1 >= 0)
				{
					if(grid[i-1][maxPos-1] == 0 && grid[i][maxPos-1] == 0)
					{
						grid[i][maxPos] = 7;
						grid[i-1][maxPos - 1] = 7;
						grid[i][maxPos - 1] = 7;
						if(maxPos - 2 >= 0)
							grid[i-1][maxPos - 2] +=1;
						result+="C";
						return maxPos-2;
					}
				}
			}
		}
		return maxPos;
	}
	public int tryD(boolean left, int maxPos)
	{
		for(int i = 0; i < grid.length; i++)
		{
			if(left)
			{
				if(grid[i][maxPos] == 1 && maxPos + 2 < cols && i + 2 < rows)
				{
					if(grid[i][maxPos + 1] == 0 && grid[i+1][maxPos+1] == 0 && grid[i+2][maxPos+1] == 0)
					{
						grid[i][maxPos+1] = 7;
						grid[i+1][maxPos+1] = 7;
						grid[i+2][maxPos+1] = 7;
						if(maxPos + 2 < cols)
							grid[i+2][maxPos+2] += 1;
						result+="D";
						return maxPos+2;
					}
				}
			}
			else
			{
				if(grid[i][maxPos] == 1 && maxPos >= 1 && i - 2 >= 0)
				{
					if(grid[i-1][maxPos] == 0 && grid[i-2][maxPos] == 0 && grid[i-2][maxPos-1] == 0)
					{
						grid[i-1][maxPos] = 7;
						grid[i-2][maxPos] = 7;
						grid[i-2][maxPos - 1] = 7;
						if(maxPos - 2 >= 0)
							grid[i-2][maxPos-2] += 1;
						result+= "D";
						return maxPos-2;
					}
				}
			}
		}
		return maxPos;
	}
	public int tryE(boolean left, int maxPos)
	{
		for(int i = 0; i < grid.length; i++)
		{
			if(left)
			{
				if(grid[i][maxPos] == 1 && maxPos + 1 < cols && i+1 < rows)
				{
					if(grid[i][maxPos+1] == 0 && grid[i+1][maxPos+1] == 0 && grid[i+1][maxPos+2] == 0)
					{
						grid[i][maxPos+1] = 7;
						grid[i+1][maxPos+1] = 7;
						grid[i+1][maxPos+2] = 7;
						if(maxPos + 3 < cols)
							grid[i+1][maxPos+3] += 1;
						result+= "E";
						return maxPos+3;
					}
				}
			}
			else
			{
				if(grid[i][maxPos] == 1 && maxPos >= 2 && i - 1 >= 0)
				{
					if(grid[i][maxPos-1] == 0 && grid[i-1][maxPos-1] == 0 && grid[i-1][maxPos-2] == 0)
					{
						grid[i][maxPos-1] = 7;
						grid[i][maxPos-1] = 7;
						grid[i-1][maxPos-2] = 7;
						if(maxPos - 3 >= 0)
							grid[i-1][maxPos-3] += 1;
						result+="E";
						return maxPos-3;
					}
				}
			}
		}
		return maxPos;
	}
	public String toString()
	{
		boolean left = false;
		if(start % cols == 1)
			left = true;
		grid[(start-1)/cols][(start-1)%cols] = 1;
		if(start > cols)
			grid[(start-1)/cols - 1][(start-1) % cols] = -10;
		if((start-1) / cols + 1 < rows)
			grid[(start-1)/cols+1][(start-1) % cols] = -10;
		for(int n: blocked)
		{
			grid[(n-1)/cols][(n-1) % cols] = -10;
		}
		if(left)
			maxPos = 0;
		else
			maxPos = cols - 1;
		int count = 0;
		while((maxPos < cols && left) || (maxPos >= 0 && !left))
		{
			if(count % 5 == 0)
			{
				maxPos = tryA(left, maxPos);
			}
			if(count % 5 == 1)
			{
				maxPos = tryB(left, maxPos);
			}
			if(count % 5 == 2)
			{
				maxPos = tryC(left, maxPos);
			}
			if(count % 5 == 3)
			{
				maxPos = tryD(left, maxPos);
			}
			if(count % 5 == 4)
			{
				maxPos = tryE(left, maxPos);
			}
			count+= 1;
		}
		return result + "\n";
	}
}