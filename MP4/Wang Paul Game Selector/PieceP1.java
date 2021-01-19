//Vivek Shah
//Piece
//Spec: Makes up board for Snake game
public class PieceP1
{
	//isSnake - part of Snake, etc.
	boolean isSnake, isWall, isApple, isHead, isFloor;
	int row, col;
	String direction;
	public PieceP1()
	{
		isSnake = false;
		isWall = false;
		isApple = false;
		isHead = false;
		isFloor = false;
		direction = "";
		col = 0;
		row = 0;
	}
	public PieceP1(int r, int c)
	{
		isSnake = false;
		isWall = false;
		isApple = false;
		isHead = false;
		isFloor = false;
		direction = "";
		col = c;
		row = r;
	}
	//Takes input to change and returns variables
	public boolean getSnake()
	{
		return isSnake;
	}
	public boolean getWall()
	{
		return isWall;
	}
	public boolean getApple()
	{
		return isApple;
	}
	public boolean getHead()
	{
		return isHead;
	}
	public boolean getFloor()
	{
		return isFloor;
	}
	public void changeSnake(boolean snake)
	{
		isSnake = snake;
	}
	public void changeWall(boolean wall)
	{
		isWall = wall;
	}
	public void changeApple(boolean apple)
	{
		isApple = apple;
	}
	public void changeFloor(boolean floor)
	{
		isFloor = floor;
	}
	public void changeHead(boolean head)
	{
		isHead = head;
	}
	public String getDirection()
	{
		return direction;
	}
	public void changeDirection(String d)
	{
		direction = d;
	}
	public int getRow()
	{
		return row;
	}
	public int getColumn()
	{
		return col;
	}
	public void changeR(int r)
	{
		row = r;
	}
	public void changeC(int c)
	{
		col = c;
	}
}