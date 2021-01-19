//Jordan Wang
//Reassembly

public class Reassembly
{
	private String input;
	public Reassembly()
	{
		input = "";
	}
	public Reassembly(String input)
	{
		this.input = input;
	}
	public String toString()
	{
		String[] storage = input.split(" ");
		String result = "";
		String digits = "";
		String num = storage[0];
		int length = Integer.parseInt(storage[1]);
		long sum = 0;
		for(int i = 0; i < num.length(); i+= length)
		{
			if(i + length > num.length())
			{
				digits = num.substring(i);
				for(int j = 0; j < i + length - num.length(); j++)
					digits+= "0";
			}
			else
			{
				digits = num.substring(i, i + length);
			}
			sum += Long.parseLong(digits);
		}
		result+= sum;
		return result;
	}
}
//length + length