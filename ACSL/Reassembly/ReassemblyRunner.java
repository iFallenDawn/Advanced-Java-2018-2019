//Jordan Wang
//Reassembly Runner

import java.util.Scanner;
public class ReassemblyRunner
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Reassembly r = new Reassembly();
		String input;
		for(int i = 0; i < 5; i++)
		{
			input = sc.nextLine();
			r = new Reassembly(input);
			System.out.println(r);
		}
	}
}
/*
13256709 3
3587612098 1
265472 5
3126854901231 4
25768437216701562 7
*/