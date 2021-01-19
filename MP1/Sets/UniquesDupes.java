//Jordan Wang
//Unique Dupes
//Spec: Gets the uniques and duplicates out of a string

import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.ArrayList;


public class UniquesDupes
{
	/** @param input a list of words separated by spaces
	*	@return  a Set that contains only the unique words from the input
	*	Note: This is the built-in behavior of a Set
	*/
	public static Set<String> getUniques(String input)
	{
		Set<String> uniques = new TreeSet<String>(); // A Set to store the uniques
		String[] storage = input.split(" ");
		for(String s: storage)
		{
			uniques.add(s);
		}
		return uniques;
	}

	/** @param input a list of words separated by spaces
	*	@return  a Set that contains only the words that are duplicates in the original list
	*	Note: Take a look at the API for the add() method in Set... it returns a boolean
	*	Hint: Consider creating 2 sets and use the behavior of Sets to simplify the task
	*/
	public static Set<String> getDupes(String input)
	{
		String[] storage = input.split(" ");
		Set<String> set = new TreeSet<String>();
		Set<String> dupes = new TreeSet<String>();
		for(String s: storage)
		{
			if(set.add(s) == false)
				dupes.add(s);
		}
		return dupes;
	}
}