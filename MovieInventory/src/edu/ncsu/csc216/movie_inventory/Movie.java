package edu.ncsu.csc216.movie_inventory;

/**
 * This class is used to deal with the actual movie objects by creating them or
 * returning information about them
 * 
 * @author Adams Ombonga (amombong) [Code Modified from CSC 216 Staff Tutorial]
 * @version 1.0 (January 16, 2015)
 */
public class Movie {

	/**
	 * Variable for minimum acceptable release year
	 */
	private static final int MIN_RELEASE_YEAR = 1880;

	/**
	 * Variable holding movie title
	 */
	private String title;
	/**
	 * Variable holding movie release year
	 */
	private int releaseYear;
	/**
	 * Variable holding movie genre
	 */
	private String genre;
	/**
	 * Variable holding movie rating
	 */
	private String rating;

	/**
	 * Constructs a Movie object using the provided parameters
	 * 
	 * @param title
	 *            Movie's title
	 * @param releaseYear
	 *            Movie's release year
	 * @param genre
	 *            Movie's genre
	 * @param rating
	 *            Movie's rating
	 * @throws IllegalArgumentException
	 *             if release year is not within range
	 */
	public Movie(String title, int releaseYear, String genre, String rating) {
		super();
		// Throw Exception if the release is below the Minimum release year
		if (releaseYear < MIN_RELEASE_YEAR) {
			throw new IllegalArgumentException();
		}
		//
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}

	/**
	 * toString method used to return the movie objects in a specific String
	 * format This toString method uses the "Override" tag in order to follow
	 * the specific String format
	 * 
	 * @return s String containing movie object information
	 */
	@Override
	public String toString() {
		// String variable for printing the movie object information
		String s = "Movie [title=" + title + ", releaseYear=" + releaseYear
				+ ", genre=" + genre + ", rating=" + rating + "]";
		return s;
	}

	/**
	 * hashCode method used to create a unique number for the the object based
	 * on it's parameters. Since toString uses a override tag this method must
	 * do the same.
	 * 
	 * @return result hashCode based on specific object parameters
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Method for determining if the provided objects are equal by comparing the
	 * parameters inside each object (i.e. title)
	 * 
	 * @param obj
	 *            object to be compared
	 * @return true if objects are equal, otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns movie title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns movie release year
	 * 
	 * @return the releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Returns movie genre
	 * 
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Returns movie rating
	 * 
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
}