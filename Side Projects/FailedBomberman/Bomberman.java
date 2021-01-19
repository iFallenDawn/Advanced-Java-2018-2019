//Jordan Wang
//
//

import java.awt.Graphics2D;
import java.awt.Image;

public class Bomberman
{
	private double x, y, angle;
	private double size = .3f;
	private int numBombs, bombPower, speed, health;
	private Map map;
	private Image image;
	private boolean alive;
	public Bomberman()
	{
		numBombs = 1;
		bombPower = 1;
		speed = 5;
		health = 3;
		alive = true;
	}
	public Bomberman(Image image, Map map, double x, double y)
	{
		numBombs = 1;
		bombPower = 1;
		speed = 5;
		health = 3;
		alive = true;
		this.image = image;
		this.map = map;
		this.x = x;
		this.y = y;
	}
	public boolean move(double dx, double dy)
	{
		double newx = x + dx;
		double newy = y + dy;
		if(validLocation(newx, newy) == true)
		{
			x = newx;
			y = newy;
			angle = (Math.atan2(dx, dy) - (Math.PI/2));
			return true;
		}
		return false;
	}
	public boolean validLocation(double newx, double newy)
	{
		if(map.blocked(newx - size, newy - size))
			return false;
		if(map.blocked(newx - size, newy + size))
			return false;
		if(map.blocked(newx + size, newy - size))
			return false;
		if(map.blocked(newx + size, newy + size))
			return false;
		return true;
	}
	public void decreaseHealth()
	{
		health--;
		if(health < 0)
			alive = false;
	}
	public boolean isAlive()
	{
		return alive;
	}
	public void paint(Graphics2D g)
	{
		int xpos = (int)(map.getSize() * x);
		int ypos = (int)(map.getSize() * x);

		g.rotate(angle, xpos, ypos);
		g.drawImage(image,(int)(xpos - 16), (int) (ypos - 16), null);
		g.rotate(-angle, xpos, ypos);
	}
}
/*public int getBombs()
	{
		return numBombs;
	}
	public int getPower()
	{
		return bombPower;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void increaseBombs()
	{
		numBombs++;
	}
	public void increasePower()
	{
		bombPower++;
	}
	public void increaseSpeed()
	{
		speed++;
	}
	public void decreaseBombs()
	{
		if(numBombs - 1 >= 1)
			numBombs--;
	}
	public void decreasePower()
	{
		if(bombPower - 1 >= 1)
			bombPower--;
	}
	public void decreaseSpeed()
	{
		if(speed - 1 >= 1)
			bombPower--;
	}
*/