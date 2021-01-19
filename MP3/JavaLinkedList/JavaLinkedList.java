//Jordan Wang
//JavaLinkedList
//Spec: Gets various information from a linked list

import java.util.LinkedList;

public class JavaLinkedList
{
	private LinkedList<Integer> list;

	public JavaLinkedList()
	{
		list = new LinkedList<Integer>();
	}

	public JavaLinkedList(int[] nums)
	{
		list = new LinkedList<Integer>();
		for(int num : nums)
		{
			list.add(num);
		}
	}

	public int getSum()
	{
		int total = 0;
		for(Integer i: list)
		{
			total+= i;
		}
		return total;
	}

	public double getAvg()
	{
		return (double)getSum() / list.size();
	}

	public int getLargest()
	{
		int largest = Integer.MIN_VALUE;
		for(Integer i: list)
		{
			if(i >= largest)
				largest = i;
		}
		return largest;
	}

	public int getSmallest()
	{
		int smallest = smallest=Integer.MAX_VALUE;
		for(Integer i: list)
		{
			if(i <= smallest)
				smallest = i;
		}
		return smallest;
	}

	public String toString()
	{
		String result = "";
		result+= "Sum: " + getSum() + "\n";
		result+= "Average: " + getAvg() + "\n";
		result+= "Minimum: " + getSmallest() + "\n";
		result+= "Maximum: " + getLargest() + "\n";
		return result;
	}
}