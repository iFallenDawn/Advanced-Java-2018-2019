//Name:
//Program:
//Spec:

//Extends the Movie Class
//This a child class to the mother class Movie
public class Action extends Movie
{
	//Variable native to only this class
	private String thrillingMoment;

	public Action()
	{
		thrillingMoment = "";
	}
	//Constructor that supers all the variables to the Movie class
	public Action(String title, String summary, String poster, int time, double rating)
	{
		super(title, summary, poster, time, rating);
	}
	//Sets the Thrilling scene to the inputed String
	public void setThrillingMoment(String moment)
	{
		thrillingMoment = moment;
	}
	//Supers the toString and Prints out the Genre and the Thrilling Moment
	public String toString()
	{
		String result = super.toString();
		result += "\n\nGenre: Action\n\nMost Thrilling Moment:\n" + thrillingMoment;
		return result;
	}

}
