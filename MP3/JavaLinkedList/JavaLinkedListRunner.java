//Jordan Wang
//JavaLinkedListRunner
//Spec: Tests the javalinkedlist object
import java.util.LinkedList;

public class JavaLinkedListRunner
{
	public static void main(String[] args )
	{
	  	JavaLinkedList test = new JavaLinkedList(new int[]{4,5,6,7,8,9,10,11,12,13});
		System.out.println(test);

		test = new JavaLinkedList(new int[]{24,75,86,37,82,94,111,82,43});
		System.out.println(test);

		test = new JavaLinkedList(new int[]{0,4,5,2,1,4,6});
		System.out.println(test);
	}
}
/*
Sum: 85
Average: 8.5
Minimum: 4
Maximum: 13

Sum: 634
Average: 70.44444444444444
Minimum: 24
Maximum: 111

Sum: 22
Average: 3.142857142857143
Minimum: 0
Maximum: 6

Press any key to continue . . .
*/