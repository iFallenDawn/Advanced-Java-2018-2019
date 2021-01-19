//Jordan Wang
//Diff
//Spec: Diff

public class Diff
{
	private String s1, s2, diff1, diff2;
	public Diff()
	{
		s1 = "";
		s2 = "";
		diff1 = "";
		diff2 = "";
	}
	public Diff(String s1, String s2)
	{
		this.s1 = s1;
		this.s2 = s2;
		diff1 = "";
		diff2 = "";
	}
	public String algorithm1(String one, String two)
	{
		String result = "";
		String edited = two;
		String[]words = one.split(" ");
		for(int i = 0; i < words.length; i++)
		{
			if(edited.contains(words[i]))
			{
				result += words[i] + " ";
				int index = edited.indexOf(words[i]);
				edited = edited.substring(0, index) + edited.substring(index + words[i].length());
				//System.out.println("Edited: " + edited);
			}
		}
		return result;
	}
	public String algorithm2(String one, String two)
	{
		String result = "";
		String edited = two;
		edited = edited.replaceAll(" ", "");
		for(int i = 0; i < one.length(); i++)
		{
			String character = one.substring(i, i+1);
			if(edited.contains(character))
			{
				int index = edited.indexOf(character);
				result += character;
				//System.out.println("Index: " + index);
				if(edited.length() > 0)
					edited = edited.substring(index + 1);
				//System.out.println("Edited:" + edited);
				//System.out.println("Result: " + result + "\n");
			}
		}
		return result;
	}
	public String toString()
	{
		String result = "";
		diff1 = algorithm1(s1, s2);
		//System.out.println("\n" + diff1);
		diff2 = algorithm1(s2, s1);
		//System.out.println("\n" + diff2);
		result = algorithm2(diff1, diff2);
		if(result.length() == 0)
			return "NONE\n";
		return result + "\n";
	}
}
