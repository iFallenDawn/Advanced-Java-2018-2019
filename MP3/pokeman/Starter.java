//Jordan Wang
//Starter
//Spec: Keeps track of a starter's name, type, and damage range.

public class Starter extends Pokemon
{
	private String name, attack, type;
	private int health, damageRange;
	public Starter()
	{
		name = "";
		attack = "";
		type = "";
		health = 0;
		damageRange = 0;
	}
	public Starter(String attack, int health, String name, int level, String type, int damageRange)
	{
		super(attack, health, level);
		this.type = type;
		this.name = name;
		this.damageRange = damageRange;
	}
	public int getDamageRange()
	{
		return damageRange;
	}
	public void increaseDamage()
	{
		damageRange++;
	}
	public int getDamage()
	{
		return (int)(Math.random()*3) + damageRange;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String toString()
	{
		return "Pokemon: " + name + "\nType: " + type + "\n" + super.toString();
	}
}