// Name - Jordan Wang
// Prog - List Node
// Spec - Creates a list node

public class ListNode implements Linkable
{
	private Comparable value;
	private ListNode nextNode;

	public ListNode()
	{
		value = null;
		nextNode = null;
	}

	public ListNode(Comparable val, ListNode next)
	{
		value = val;
		nextNode = next;
	}

	public Comparable getValue()
	{
		return value;
	}

	public ListNode getNext()
	{
	   return nextNode;
	}

	public void setValue(Comparable val)
	{
		value = val;
	}

	public void setNext(Linkable next)
	{
		nextNode = (ListNode)next;
	}
}