// Name - Jordan Wang
// Prog - ListFunHouseRunner
// Spec - Puts list nodes into a listfunhouse and performs various functions on the listfunhouse.

import java.util.*;

public class ListFunHouseRunner
{
	public static void main ( String[] args )
	{
		ListNode z = new ListNode("go",
			 		new ListNode("on",
		      		new ListNode("at",
		       		new ListNode("34",
			 		new ListNode("2.1",
		      		new ListNode("-a-2-1",
		       		new ListNode("up",
			 		new ListNode("over",null))))))));

		System.out.println("Test Cases\n\n");

		System.out.println("Original list values\n");
		ListFunHouse.print(z);
		System.out.println("\n");

		System.out.println("num nodes = " + ListFunHouse.nodeCount(z));

		System.out.println("\nList values after calling nodeCount:\n");
		ListFunHouse.print(z);
		System.out.println();

		ListFunHouse.doubleFirst(z);
		System.out.println("\nList values after calling doubleFirst:\n");
		ListFunHouse.print(z);
		System.out.println();

		ListFunHouse.doubleLast(z);
		System.out.println("\nList values after calling doubleLast:\n");
		ListFunHouse.print(z);
		System.out.println();

		ListFunHouse.skipEveryOther(z);
		System.out.println("\nSkipping every other:\n");
		ListFunHouse.print(z);
		System.out.println();

		ListFunHouse.removeXthNode(z,2);
		System.out.println("\nList values after calling removeXthNode(2):\n");
		ListFunHouse.print(z);
		System.out.println();


		ListFunHouse.setXthNode(z,2,"one");
		System.out.println("\nList values after calling setXthNode(2,one):\n");
		ListFunHouse.print(z);
		System.out.println();
	}
}
/*
Test Cases


Original list values

go on at 34 2.1 -a-2-1 up over

num nodes = 8

List values after calling nodeCount:

go on at 34 2.1 -a-2-1 up over

List values after calling doubleFirst:

go go on at 34 2.1 -a-2-1 up over

List values after calling doubleLast:

go go on at 34 2.1 -a-2-1 up over over

Skipping every other:

go on 34 -a-2-1 over

List values after calling removeXthNode(2):

go 34 -a-2-1 over

List values after calling setXthNode(2,one):

go one -a-2-1 over
Press any key to continue . . .
*/