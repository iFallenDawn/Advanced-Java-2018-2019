//Jordan Wang

import java.awt.Color;
import java.awt.Graphics2D;

public class Map
{
	private static final int clear = 1;
	private static final int box = 2;
	private static final int barrier = 3;
	private static final int width = 15;
	private static final int height = 15;

	public static final int size = 40;

	int[][] grid = new int[height][width];
	public Map()
	{
		/* Sets everything in the map to a clear tile first */
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
				grid[i][j] = clear;
		}
		/* Fills in the borders with barrier blocks */
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				if(i == 0)
					grid[i][j] = barrier;
				else if(i == grid.length - 1)
					grid[i][j] = barrier;
				else
				{
					if(j == 0 || j == grid.length - 1)
						grid[i][j] = barrier;
				}
			}
		}
		/* Fills in every other block in the map with barrier blocks */
		for(int i = 2; i < grid.length - 2; i++)
		{
			for(int j = 2; j < grid.length - 2; j++)
			{
				if(i%2 == 0 && j%2 == 0)
					grid[i][j] = barrier;
			}
		}
		/* Fills in parts of the map with breakable boxes (?)
			Insert a for loop here to do it later
		*/

		/** Prints out board for test*/
		/*for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid.length; j++)
			{
				System.out.print(grid[i][j] + "\t");
			}
			System.out.println();
		}*/
	}
	public void paint(Graphics2D g)
	{
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(grid[i][j] == barrier)
				{
					g.setColor(Color.gray);
				}
				else if(grid[i][j] == clear)
				{
					g.setColor(Color.green);
				}
				else if(grid[i][j] == box)
				{
					g.setColor(Color.gray);
				}
				g.fillRect(i*size, j*size, size, size);
				g.setColor(g.getColor().darker());
				g.drawRect(i*size, j*size, size, size);

			}
		}
	}
	public int getSize()
	{
		return size;
	}
	public boolean blocked(double x, double y)
	{
		return grid[(int)x][(int)y] == barrier || grid[(int)x][(int)y] == box;
	}
}
// 15*15 map, 13*13 playing field