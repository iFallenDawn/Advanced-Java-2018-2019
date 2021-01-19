//Jordan Wang
//Dup Runner
//Spec: Given an input, returns the unique worsd from the input and its duplicates
import java.util.*;

public class DupRunner
{
	public static void main( String args[] )
	{
		String list = "a b c d e f g h a b c d e f g h i j k";
		System.out.println("Original List : " + list);
		System.out.println("Uniques : " + UniquesDupes.getUniques(list));
		System.out.println("Dupes : " + UniquesDupes.getDupes(list) + "\n\n");

		list = "one two three one two three six seven one two";
		System.out.println("Original List : " + list);
		System.out.println("Uniques : " + UniquesDupes.getUniques(list));
		System.out.println("Dupes : " + UniquesDupes.getDupes(list) + "\n\n");

		list = "1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 6";
		System.out.println("Original List : " + list);
		System.out.println("Uniques : " + UniquesDupes.getUniques(list));
		System.out.println("Dupes : " + UniquesDupes.getDupes(list) + "\n\n");
	}
}
/*
Original List : a b c d e f g h a b c d e f g h i j k
Uniques : [a, b, c, d, e, f, g, h, i, j, k]
Dupes : [a, b, c, d, e, f, g, h]


Original List : one two three one two three six seven one two
Uniques : [one, seven, six, three, two]
Dupes : [one, three, two]


Original List : 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 6
Uniques : [1, 2, 3, 4, 5, 6]
Dupes : [1, 2, 3, 4, 5]


Press any key to continue . . .
*/