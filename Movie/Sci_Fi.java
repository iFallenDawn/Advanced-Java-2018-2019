//Name:
//Program:
//Spec:

//Extends the Movie Class
//This a child class to the mother class Movie
public class Sci_Fi extends Movie
{
	//Variable native to only this class
	private String bestCGI;

	public Sci_Fi()
	{
		bestCGI = "";
	}
	//Constructor that supers all the variables to the Movie class
	public Sci_Fi(String title, String summary, String poster, int time, double rating)
	{
		super(title, summary, poster, time, rating);
	}
	//Sets the Best CGI scene to the inputed String
	public void setBestCGI(String cGI)
	{
		bestCGI = cGI;
	}
	//Supers the toString and Prints out the Genre and the Best CGI Moment
	public String toString()
	{
		String result = super.toString();
		result += "\n\nGenre: Science Fiction\n\nBest CGI Moment:\n" + bestCGI;
		return result;
	}
}
