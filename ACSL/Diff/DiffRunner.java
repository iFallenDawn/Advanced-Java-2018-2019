//Jordan Wang
//Diff Runner
//Spec: Runs Diff

import java.util.*;

public class DiffRunner
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Diff d;
		String first = "";
		String second = "";

		for(int i = 0; i < 10; i++)
		{
			first = sc.nextLine();
			second = sc.nextLine();
			d =new Diff(first, second);
			System.out.print(d);
		}
	}
}
/*
The quick brown fox did jump over a log
The brown rabbit quickly did outjump the fox

How many pickled peppers did Peter Piper pick
Peter Piper picked a peck of pickled peppers

The AllStar Contest is in Wayne Hills NJ
Hills are where there are contestants from NJ

Fuzzy Wuzzy was a bear Fuzzy Wuzzy had no hair
Fuzzy Wuzzy was not fuzzy was he

Super cali frigi listic expi alli docious
frigid call superman allies expired list

*/
