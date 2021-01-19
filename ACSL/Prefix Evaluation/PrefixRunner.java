//Jordan Wang
//Prefix Runner

import java.util.Scanner;
public class PrefixRunner
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Prefix p = new Prefix();
		String input;
		for(int i = 0; i < 5; i++)
		{
			input = sc.nextLine();
			p = new Prefix(input);
			System.out.println(p);
		}
	}
}
/*
* + 4 5 - 3 -1
@ - 8 9 82 46
@ | - -8 10 82 46
+ > 8 * 2 7 9 6
| * @ - 1 6 34 12 > - 990 1000 * -2 3 + -51 49
*/