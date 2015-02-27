package edu.ncsu.csc216.movie_inventory;

/**
 * This class is responsible for organizing the movies by adding, removing, and
 * listing items that are in the movie collection
 * 
 * @author Adams Ombonga (amombong) [Code Modified from CSC 216 Staff Tutorial]
 * @version 1.0 (January 16, 2015)
 */
public class MovieInventory {

	/**
	 * Variable containing the size of movie collection
	 */
	private static final int INVENTORY_SIZE = 10;

	/**
	 * Variable creating the movie array
	 */
	private Movie[] movies;

	/**
	 * Method used to initialize the movie array with the appropriate suze
	 */
	public MovieInventory() {
		movies = new Movie[INVENTORY_SIZE];
	}

	/**
	 * Method used to list the movies in the collection, if the collection index
	 * does not contain a movie or is null, then that entry is saved as Empty.
	 * 
	 * @return s, String containing the list of movies in the collection
	 */
	public String listMovies() {
		// String to contain the full numbered list of movies in the collection
		String s = "";
		for (int i = 0; i <= movies.length - 1; i++) {
			if (movies[i] == null) {
				s += "" + (i + 1) + ". " + "Empty\n";
			} else {
				s += "" + (i + 1) + ". " + movies[i] + "\n";
			}
		}
		return s;
	}

	/**
	 * Returns true if the Movie can be added to the inventory. If the Movie is
	 * a duplicate or if there is no more space, the method returns false.
	 * 
	 * @param m
	 *            Movie to add to the inventory
	 * @return true if the Movie can be added to the inventory
	 */
	public boolean addMovie(Movie m) {
		// Check for duplicate movies
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] != null && movies[i].equals(m)) {
				return false; // movie already exists
			}
		}
		boolean added = false; // flag
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			if (movies[i] == null) { // the movie slot is empty
				movies[i] = m;
				added = true;
				break; // if we added, break out of the loop
			}
		}
		return added; // return the flag
	}

	/**
	 * Returns true if the Movie can be removed from the inventory. If the Movie
	 * is not in the inventory then the method returns false.
	 * 
	 * @param title
	 *            user input title to be removed from the inventory
	 * @return removed true if the Movie can be removed to the inventory
	 */
	public boolean remMovie(String title) {
		// Boolean to Check if movie was removed
		boolean removed = false;
		for (int i = 0; i < INVENTORY_SIZE; i++) {
			// String variable to hold the current movie title from array
			String s = "";
			if (movies[i] == null) {
				s = " ";
			} else {
				s = movies[i].getTitle();
			}
			// If-block to check if movie array title equals user entered title
			if (s.equals(title)) {
				movies[i] = null;
				removed = true;
				return true; // Automatically return true if the movie is found
								// in the collection
			} else {
				removed = false;
			}
		}
		return removed; // return movie removal status
	}

	/**
	 * Method used to exit the program
	 */
	public void exitProgram() {
		System.exit(0); // Closes the virutal machines
	}

}