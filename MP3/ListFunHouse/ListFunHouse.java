// Name - Jordan Wang
// Prog - ListFunHouse
// Spec - Creates a funhouse of listnodes

public class ListFunHouse
{
	//this method will print the entire list on the screen
	public static void print(ListNode list)
	{
		while(list!= null)
		{
			System.out.print(list.getValue() + " ");
			list = list.getNext();
		}
	}
	//this method will return the number of nodes present in list
	public static int nodeCount(ListNode list)
	{
   		int count = 0;
   		while(list != null)
   		{
			count++;
			list = list.getNext();
		}
		return count;
	}

	//this method will create a new node with the same value as the first node and add this
	//new node to the list.  Once finished, the first node will occur twice.
	public static void doubleFirst(ListNode list)
	{
		ListNode copy = new ListNode(list.getValue(), list.getNext());
		list.setNext(copy);
	}

	//this method will create a new node with the same value as the last node and add this
	//new node at the end.  Once finished, the last node will occur twice.
	public static void doubleLast(ListNode list)
	{
		while(list.getNext().getNext() != null)
		{
			list = list.getNext();
		}
		list = list.getNext();
		list.setNext(new ListNode(list.getValue(), list.getNext()));
		list.getNext().setNext(null);
	}

	//method skipEveryOther will remove every other node
	public static void skipEveryOther(ListNode list)
	{
		int removed = nodeCount(list) / 2;
		for(int i = 0; i < removed; i++)
		{
			if(list.getNext().getNext() != null)
				list.setNext(list.getNext().getNext());
			else
				list.setNext(null);
			list = list.getNext();
		}
	}

	//this method will set the value of every xth node in the list
	public static void setXthNode(ListNode list, int x, Comparable value)
	{
		for(int i = 1; i < nodeCount(list); i++)
		{
			if(i % x == 0)
				list.setValue(value);
			list = list.getNext();
		}
	}

	//this method will remove every xth node in the list
	public static void removeXthNode(ListNode list, int x)
	{
		for(int i = 0; i < nodeCount(list) / x; i++)
		{
			if(list.getNext().getNext() != null)
				list.setNext(list.getNext().getNext());
			else
				list.setNext(null);
			list = list.getNext();
		}
	}
}