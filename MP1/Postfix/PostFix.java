//Name - Jordan Wang
//Prog - Post Fix
//Spec - Calculates a postfix expression and returns the result.

import java.util.Stack;
import java.util.Scanner;


public class PostFix
{
	private Stack<Double> stack;
	private String expression;

	public PostFix()
	{
		stack = new Stack<Double>();
		expression = "";
	}

	public PostFix(String exp)
	{
		stack = new Stack<Double>();
		expression = "";
		setExpression(exp);
	}

	public void setExpression(String exp)
	{
		expression = exp;
	}

	public double calc(double one, double two, char op)
	{
		double result = 0;
		if(op == '+')
			result = one + two;
		else if(op == '-')
			result = one - two;
		else if(op == '*')
			result = one * two;
		else if(op == '/')
			result = one / two;
		else if(op == '^')
			result = Math.pow(one, two);
		return result;
	}

	public void solve()
	{
		String operators = "+-/*^";
		String[] input = expression.split(" ");
		for(int i = 0; i < input.length; i++)
		{
			if(operators.indexOf(input[i]) == -1)
				stack.add(Double.parseDouble(input[i]));
			else
			{
				double two = stack.pop();
				double one = stack.pop();
				stack.push(calc(one, two, input[i].charAt(0)));
			}
		}
	}

	//add a toString
	public String toString()
	{
		return expression + " = " + stack.pop();
	}
}