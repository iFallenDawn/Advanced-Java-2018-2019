//Jordan Wang
//Stretch Runner
//Spec: Runs stretch given test cases

import java.util.*;
public class StretchRunner
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Stretch s;
		String input = "";

		for(int i = 0; i < 5; i++)
		{
			input = sc.nextLine();
			s = new Stretch(input);
			System.out.print(s);
		}
	}
}
/*
6 10 11 2 48 49 ABCDE
5 10 40 1 27 ACDA
6 14 70 4 66 33 7 56 ABCACA
9 12 108 5 69 106 77 91 55 CECDE
6 13 78 1 49 ACDEA
*/