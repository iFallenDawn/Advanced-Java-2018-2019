//Name:
//Program:
//Spec:

public class MovieRunner
{
	public static void main(String[] args)
	{
		//Creates the Movie GUI
		MovieGUI g = new MovieGUI();

		//Creates Movie of a certain Genre with the title, summary, poster, length, and rating
		Movie m = new Action("Mission: Impossible - Fallout", "Ethan Hunt and the IMF team join forces with CIA assassin August Walker to prevent a disaster of epic proportions. Arms dealer John Lark and a group of terrorists known as the Apostles plan to use three plutonium cores for a simultaneous nuclear attack on the Vatican, Jerusalem and Mecca, Saudi Arabia. When the weapons go missing, Ethan and his crew find themselves in a desperate race against time to prevent them from falling into the wrong hands.", "fallout.jpg", 148, 7.8);
		//Casting the movie as the type of Genre
		//Sets the native variable to that Genre
		((Action) m).setThrillingMoment("When August Walker gets hit by a lightning bolt as he and Ethan are skydiving.");
		//Adds the Movie to the GUI
		g.addMovie(m);

		m = new Action("Avengers: Infinity War", "Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality. The fate of the planet and existence itself has never been more uncertain as everything the Avengers have fought for has led up to this moment.", "infinitywar.jpg", 160, 8.5);
		((Action) m).setThrillingMoment("Snap");
		g.addMovie(m);


		m = new Action("Fast Five", "Ever since ex-cop Brian O'Conner (Paul Walker) and Mia Torretto (Jordana Brewster) broke her brother Dom (Vin Diesel) out of custody, they've traveled border to border to evade authorities. In Rio de Janeiro, they must do one final job before they can gain their freedom for good. Assembling their elite team of car racers, Brian and Dom know they must confront the corrupt businessman who wants them dead, before the federal agent (Dwayne Johnson) on their trail finds them.", "ffive.jpg", 132, 7.3);
		((Action) m).setThrillingMoment("When they put the entire heist plan in motion");
		g.addMovie(m);


		m = new Sci_Fi("Inception", "Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people's dreams and steal their secrets from their subconscious. His skill has made him a hot commodity in the world of corporate espionage but has also cost him everything he loves. Cobb gets a chance at redemption when he is offered a seemingly impossible task: Plant an idea in someone's mind. If he succeeds, it will be the perfect crime, but a dangerous enemy anticipates Cobb's every move", "inception.jpg", 148, 8.8);
		((Sci_Fi) m).setBestCGI("The city bending in the dreamscape.");
		g.addMovie(m);

		m = new Sci_Fi("The Prestige", "Period thriller set in Edwardian London where two rival magicians, partners until the tragic death of an assistant during a show, feud bitterly after one of them performs the ultimate magic trick - teleportation. His rival tries desperately to uncover the secret of his routine, experimenting with dangerous new science as his quest takes him to the brink of insanity and jeopardises the lives of everyone around the pair.", "theprestige.jpg", 130, 8.5);
		((Sci_Fi) m).setBestCGI("When Angier tests out his machine for the first time by himself and carrying a gun.");
		g.addMovie(m);

		m = new Sci_Fi("The Matrix", "Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), an elusive figure considered to be the most dangerous man alive, can answer his question -- What is the Matrix? Neo is contacted by Trinity (Carrie-Anne Moss), a beautiful stranger who leads him into an underworld where he meets Morpheus. They fight a brutal battle for their lives against a cadre of viciously intelligent secret agents. It is a truth that could cost Neo something more precious than his life.", "matrix.jpg", 136, 8.7);
		((Sci_Fi) m).setBestCGI("When Neo is dodging all of the bullets");
		g.addMovie(m);
		
	}
}