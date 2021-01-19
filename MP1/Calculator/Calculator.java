//Jordan Wang
//Calculator
//Spec: Takes an infix expression and converts it to postfix, then solves the expression.

import java.util.Stack;
import java.util.Scanner;

public class Calculator
{
	private Stack<Double> stack;
	private String expression;
	private String postFix;
	public Calculator()
	{
		stack = new Stack<Double>();
		expression = "";
		postFix = "";
	}
	public Calculator(String exp)
	{
		stack = new Stack<Double>();
		expression = exp;
		postFix = "";
	}
	public void convert()
	{
		String operators = "+-/*^";
		Stack<String> storage = new Stack<String>();
		String[] input = expression.split(" ");
		for(int i = 0; i < input.length; i++)
		{
			if(operators.indexOf(input[i]) == -1 && !input[i].equals("(") && !input[i].equals(")"))
				postFix += input[i] + " ";
			if(input[i].equals("("))
				storage.push(input[i]);
			if(input[i].equals(")"))
			{
				while(storage.size() > 0 && !storage.peek().equals("("))
				{
					postFix += storage.pop() + " ";
				}
				if(storage.peek().equals("("))
					storage.pop();
			}
			if(operators.indexOf(input[i]) != -1)
			{
				if(storage.size() == 0 || storage.peek().equals("("))
					storage.push(input[i]);
				else
				{
					while(!storage.isEmpty() && !storage.peek().equals("(") && precedence(input[i]) <= precedence(storage.peek()))
					{
						postFix += storage.pop() + " ";
					}
					storage.push(input[i]);
				}
			}
		}
		while(!storage.isEmpty())
			postFix += storage.pop() + " ";
	}

	public int precedence(String op)
	{
		if(op.equals("^"))
			return 3;
		else if(op.equals("/") || op.equals("*"))
			return 2;
		else
			return 1;
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
		String[] input = postFix.split(" ");
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
	public String toString()
	{
		convert();
		solve();
		return "" + stack.pop();
	}
}