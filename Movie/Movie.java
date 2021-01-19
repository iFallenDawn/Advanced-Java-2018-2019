//Name:
//Program:
//Spec:

//Mother Class for all Movies
public class Movie
{
	//All Variables Common for Each Movie
	private String title, summary, poster;
	//String "poster" is the name of a file that contains a picture of the Movie Poster
	private int time;
	private double rating;
	//Default Constructor
	public Movie()
	{
		title = "";
		summary = "";
		poster = "";
		time = 0;
		rating = 0.0;
	}
	//Overloaded Constructor
	public Movie(String title, String summary, String poster, int time, double rating)
	{
		this.title = title;
		this.summary = summary;
		this.poster = poster;
		this.time = time;
		this.rating = rating;
	}
	//Methods to Return All Variables
	public String getTitle()
	{
		return title;
	}

	public int getTime()
	{
		return time;
	}

	public double getRating()
	{
		return rating;
	}

	public String getSummary()
	{
		return summary;
	}

	public String getPoster()
	{
		return poster;
	}
	//Prints the Summary, Length of Movie and the Rating out of 10 for the Movie
	public String toString()
	{
		String result = "";
		result += summary + "\n\nLength: " + time + " minutes\nRating: "+ rating + " / 10";
		return result;
	}
}
