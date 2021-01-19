//Jordan Wang
//Pokemon
//Spec: Keeps track of a pokemon's health, level, the name of its attack, and its max health

public class Pokemon
{
	private String attack;
	private int maxHealth;
	private int health;
	private int level;
	public Pokemon()
	{
		attack = "";
		health = 0;
		maxHealth = 0;
		level = 0;
	}
	public Pokemon(String attack, int health, int level)
	{
		this.level = level;
		this.attack = attack;
		this.health = health;
		maxHealth = health;
	}
	public void increaseLevel()
	{
		level++;
		maxHealth++;
	}
	public void setHealth()
	{
		health = maxHealth;
	}
	public int getHealth()
	{
		return health;
	}
	public String getAttack()
	{
		return attack;
	}
	public void calculateHealth(int damage)
	{
		health -=  damage;
	}
	public String toString()
	{
		String result = "";
		result+= "Health: " + health + "/" + maxHealth + "\n" + "Level: " + level + "\n";
		return result;
	}
}