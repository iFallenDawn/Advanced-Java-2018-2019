//Jordan Wang

import java.util.*;
public class Prefix
{
	private String[] filler;
	private String operator;
	private int num1, num2, num3;
	private ArrayList<String> expression;
	public Prefix()
	{
		filler = new String[0];
		expression = new ArrayList<String>();
	}
	public Prefix(String input)
	{
		operator = "";
		num1 = 0;
		num2 = 0;
		num3 = 0;
		expression = new ArrayList<String>();
		filler = input.split(" ");
		for(String s: filler)
		{
			expression.add(s);
		}
	}
	public boolean isNumeric(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	public String toString()
	{
		String result = "";
		int replace = 0;
		while(expression.size() != 1)
		{
			for(int i = 0; i < expression.size(); i++)
			{
				if(i + 1 < expression.size() && isNumeric(expression.get(i+1)))
				{
					operator = expression.get(i);
				}
				if(operator.equals("+"))
				{
					if(i + 1 < expression.size() && i + 2 < expression.size())
					{
						if(isNumeric(expression.get(i+1)) && isNumeric(expression.get(i+2)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							num2 = Integer.parseInt(expression.remove(i+1));
							replace = num1 + num2;
							expression.set(i, "" + replace);
							i = 0;
						}
					}
				}
				else if(operator.equals("-"))
				{
					if(i + 1 < expression.size() && i + 2 < expression.size())
					{
						if(isNumeric(expression.get(i+1)) && isNumeric(expression.get(i+2)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							num2 = Integer.parseInt(expression.remove(i+1));
							replace = num1 - num2;
							expression.set(i, "" + replace);
							i = 0;
						}
					}
				}
				else if(operator.equals("*"))
				{
					if(i + 1 < expression.size() && i + 2 < expression.size())
					{
						if(isNumeric(expression.get(i+1)) && isNumeric(expression.get(i+2)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							num2 = Integer.parseInt(expression.remove(i+1));
							replace = num1 * num2;
							expression.set(i, "" + replace);
							i = 0;
						}
					}
				}
				else if(operator.equals("@"))
				{
					if(i + 1 < expression.size() && i + 2 < expression.size() && i + 3 < expression.size())
					{
						if(isNumeric(expression.get(i+1)) && isNumeric(expression.get(i+2)) && isNumeric(expression.get(i+3)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							num2 = Integer.parseInt(expression.remove(i+1));
							num3 = Integer.parseInt(expression.remove(i+1));
							if(num1 > 0)
								replace = num2;
							else
								replace = num3;
							expression.set(i, "" + replace);
							i = 0;
						}
					}
				}
				else if(operator.equals(">"))
				{
					if(i + 1 < expression.size() && i + 2 < expression.size() && i + 3 < expression.size())
					{
						if(isNumeric(expression.get(i+1)) && isNumeric(expression.get(i+2)) && isNumeric(expression.get(i+3)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							num2 = Integer.parseInt(expression.remove(i+1));
							num3 = Integer.parseInt(expression.remove(i+1));
							num1 = Math.max(num1, num2);
							replace = Math.max(num1, num3);
							expression.set(i, "" + replace);
							i = 0;
						}
					}
				}
				else if(operator.equals("|"))
				{
					if(i + 1 < expression.size())
					{
						if(isNumeric(expression.get(i+1)))
						{
							num1 = Integer.parseInt(expression.remove(i+1));
							replace = Math.abs(num1);
							expression.set(i, "" + replace);
						}
					}
				}
			}
		}
		result+= expression.get(0);
		return result;
	}
}