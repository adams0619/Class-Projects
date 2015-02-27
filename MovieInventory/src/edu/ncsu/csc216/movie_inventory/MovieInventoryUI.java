package edu.ncsu.csc216.movie_inventory;

import java.util.Scanner;

/**
 * This class is used for calling methods in the Movie or MovieInventory class
 * once the user has entered input into the program
 * 
 * @author Adams Ombonga (amombong) [Code modified from CSC 216 Staff Tutorial]
 * @version 1.0 (January 16, 2015)
 */
public class MovieInventoryUI {

	/**
	 * Variable used to call methods from MovieInventory class
	 */
	private MovieInventory inventory;

	/**
	 * Scanner used for capturing user input
	 */
	private Scanner console;

	/**
	 * Constructs a MovieInventoryUI object that contains a MovieInventory and a
	 * Scanner for reading console input.
	 */
	public MovieInventoryUI() {
		inventory = new MovieInventory();
		console = new Scanner(System.in);
	}

	/**
	 * Controls the looping of the user interface.
	 */
	public void userInterface() {
		promptForEntry();
	}

	/**
	 * Controls what happens once the user inputs an action-able item If an
	 * invalid input is entered the method re-prompts the user for a valid
	 * input.
	 */
	private void promptForEntry() {
		movieProgramMenu();
		// Variable to save the user-selected entry
		int entry = 0;

		// Variable to determine if the user entry is valid
		boolean menuPass = true;

		// Variable to determine if menu needs to be redisplayed to user
		boolean reRun = true;

		// Loop to check if user input is acceptable
		while (reRun) {
			if (!menuPass) {
				movieProgramMenu();
			}

			if (console.hasNextInt()) {
				entry = console.nextInt();
				// Exit program
				if (entry == 2 + 2) {
					exitProgram();
					// Remove a movie based on title
				} else if (entry == 2 + 1) {
					console.nextLine();
					removeMovie();
					reRun = true;
					menuPass = false;
					// Add movie to collection based on user input
				} else if (entry == 2) {
					console.nextLine();
					promptForMovie();
					reRun = true;
					menuPass = false;
					// List movies in the collection
				} else if (entry == 1) {
					listMovies();
					reRun = true;
					menuPass = false;
					// Improper input, re-prompt user for valid command
				} else {
					console.nextLine();
					System.out.println("Invalid Commmand.");
					reRun = true;
					menuPass = false;
				}
			} else {
				console.nextLine();
				System.out.println("Invalid Commmand.");
				reRun = true;
				menuPass = false;
			}
		}
	}

	/**
	 * Method used to call the exit program code from MovieInventory class
	 */
	private void exitProgram() {
		inventory.exitProgram();
	}

	/**
	 * Method used to remove a movie from inventory based on user entered title
	 */
	private void removeMovie() {
		System.out.println("Title to remove?");
		// Variable containing user entered title, that is to be removed
		String title = console.nextLine();
		if (inventory.remMovie(title)) {
			System.out.println("Movie removed from collection");
		} else {
			System.out.println("Movie cannot be removed from collection");
		}
	}

	/**
	 * List the movies in movie the movie collection array
	 */
	private void listMovies() {
		System.out.println("Movie Inventory:");
		// Print returned list of movies to console
		System.out.println(inventory.listMovies());
	}

	/**
	 * UI Menu used for displaying action-able items to user
	 */
	public void movieProgramMenu() {
		// Print menu options to console
		System.out.println("\nMovieInventory Menu");
		System.out.println("1. List Movies");
		System.out.println("2. Add Movie");
		System.out.println("3. Remove movie");
		System.out.println("4. Quit");
		System.out.println("Entry?");
	}

	/**
	 * UI functionality for adding a Movie. Prompts user for the necessary
	 * details before adding movie to collection. Improper input results in a
	 * re-prompt for proper input or IllegalArgumentException being thrown
	 * 
	 */
	public void promptForMovie() {
		// Prompt for/save movie title
		System.out.println("\nTitle? ");
		String title = console.nextLine();

		// Boolean to determine if entered release year is an integer
		boolean validRelease = false;
		// Variable used to hold the user-entered release year
		int releaseYear = 0;
		while (!validRelease) {
			System.out.println("Release Year? ");
			if (console.hasNextInt()) {
				releaseYear = console.nextInt();
				validRelease = true;
			} else {
				System.out.println("Invalid Release Year");
				console.nextLine();
				validRelease = false;
			}
		}
		console.nextLine(); // throw away rest of line

		// Prompt for/save movie genre
		System.out.println("Genre? ");
		String genre = console.nextLine();

		// Prompt for/save movie rating
		System.out.println("Rating? ");
		String rating = console.nextLine();

		// Try/Catch Block used to catch an exception if the release year is
		// below
		// the specified minimum release year.
		try {
			Movie m = new Movie(title, releaseYear, genre, rating);
			if (inventory.addMovie(m)) {
				System.out.println("Movie added to collection.");
			} else {
				System.out.println("Movie cannot be added to the collection.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid release year.");
		}
	}

	/**
	 * Starts the program by calling the user interface.
	 * 
	 * @param args
	 *            command line args
	 */
	public static void main(String[] args) {
		MovieInventoryUI ui = new MovieInventoryUI();
		ui.userInterface();
	}
}