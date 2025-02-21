
import org.junit.jupiter.api.Test;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

class MovieDataHandlerTest {
    List<Movie> testList = Arrays.asList(
            new Movie("F001", "The Shawshank Redemption", 1994, List.of("Drama"), "Frank Darabont", List.of("Tim Robbins", "Morgan Freeman"), 9.3, List.of("English"), 142),
            new Movie("F002", "The Godfather", 1972, List.of("Crime", "Drama"), "Francis Ford Coppola", List.of("Marlon Brando", "Al Pacino"), 9.2, List.of("English", "Italian"), 175),
            new Movie("F003", "The Dark Knight", 2008, List.of("Action", "Crime", "Drama"), "Christopher Nolan", List.of("Christian Bale", "Heath Ledger"), 9.0, List.of("English"), 152),
            new Movie("F004", "Pulp Fiction", 1994, List.of("Crime", "Drama"), "Quentin Tarantino", List.of("John Travolta", "Uma Thurman"), 8.9, List.of("English"), 154),
            new Movie("F005", "Schindler's List", 1993, List.of("Biography", "Drama", "History"), "Steven Spielberg", List.of("Liam Neeson", "Ralph Fiennes"), 9.0, List.of("English", "German", "Polish"), 195),
            new Movie("F006", "Inception", 2010, List.of("Action", "Adventure", "Sci-Fi"), "Christopher Nolan", List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 8.8, List.of("English", "Japanese", "French"), 148),
            new Movie("F007", "Psyco", 2001, List.of("Action", "Horror"), "Christopher Nolan", List.of("Christian Bale"), 8.9, List.of("English", "Swedish"), 200)
    );

    MovieDataHandler handler = new MovieDataHandler();
    List<Movie> emptyList = Collections.emptyList();


    @Test
    void getNumbOfMovies() {
        long count = handler.getNumbOfMovies(testList, 1994);
        assertEquals(2, count);
        assertNotEquals(3, handler.getNumbOfMovies(testList, 1994));
    }


    @Test
    void getLongestMovie() {
        int longestMovie = handler.getLongestMovie(testList);
        assertEquals(200, longestMovie);
        assertNotEquals(142, longestMovie);
        System.out.println("Longest movie: " + longestMovie + " min");
    }


    /// Testar metod med en ny lista med highestRatedCast, ser om testList har 2 skådespelare i högst rankad film.
    @Test
    void getActors() {
        List<String> highestRatedCast = handler.getActorsInHighestRatedMovie(testList);
        List<String> expected = Arrays.asList("Tim Robbins", "Morgan Freeman");
        assertEquals(2, highestRatedCast.size());
        assertNotEquals(3, highestRatedCast.size());

        assertEquals(expected, highestRatedCast);

        assertTrue(highestRatedCast.contains("Tim Robbins"));
        assertTrue(highestRatedCast.contains("Morgan Freeman"));
        System.out.println("Highest Rated Cast: " + highestRatedCast);
    }

    /// Ytterligare test för ovan metod ifall listan med filmer är tom och då retunerar en tom lista.
    @Test
    void getActorsEmptyList() {
        List<String> result = handler.getActorsInHighestRatedMovie(Collections.emptyList());
        assertEquals(Collections.emptyList(), result);
        System.out.println("List: " + result);
    }

    ///
    @Test
    void getLeastActors() {
        List <String> leastActors = handler.getLeastActorsInMovie(testList);
        List<String> expected = Arrays.asList("Psyco");
        assertEquals(leastActors, expected);
        assertTrue(leastActors.contains("Psyco"));
        assertEquals(1, leastActors.size());
        assertNotEquals("Inception", expected);
        System.out.println("Least Actors in: " + leastActors);
    }

    @Test
    void getLeastActorsEmptyList() {
        List<String> result = handler.getLeastActorsInMovie(Collections.emptyList());
        assertEquals(List.of(), result);
        assertNotEquals(List.of(), handler.getLeastActorsInMovie(testList));
        System.out.println("EmptyList: " + result);
    }

    @Test
    void actorsInMultipleMovies() {
        int actorsInMultipleMovies = handler.actorsInMultipleMovies(testList, Functions.actorCounter);
        assertEquals(1, actorsInMultipleMovies);
        assertFalse(actorsInMultipleMovies == 3);
        System.out.println("Actors in more than one movie: " + actorsInMultipleMovies);
    }

    @Test
    void actorsInMovieEmptyList() {
        int actorsInMultipleMoviesEmpty = handler.actorsInMultipleMovies(Collections.emptyList(), Functions.actorCounter);
        assertEquals(0, actorsInMultipleMoviesEmpty);
        assertFalse(actorsInMultipleMoviesEmpty == testList.size());
        System.out.println("EmptyList: " + actorsInMultipleMoviesEmpty);
    }

    @Test
    void getMostPopularActor() {
        String result = handler.getMostPopularActor(testList, Functions.actorCounter);
        assertEquals("Christian Bale", result);
        assertFalse(testList.get(5).equals("Christian"));
        System.out.println("Most popular actor: " + result);
    }

    @Test
    void getMostPopularActorWithEmptyList() {
        String result = handler.getMostPopularActor(emptyList, Functions.actorCounter);
        assertNull(result);
        assertTrue(emptyList.isEmpty());
        System.out.println("EmptyList: " + result);
    }


    @Test
    void getMulitMovieTitle() {
        boolean result = handler.getMulitMovieTitle(testList, Functions.moviesWithDuplicateTitles);
        assertFalse(result);
        System.out.println("MulitMovieTitle: " + result);
    }

    @Test
    void searchForValuesLanguages() {
        long result = handler.searchForValues(testList, Functions.uniqueLanguages);
        long expected = 7;
        assertEquals(expected, result);
        assertNotEquals(1, result);
        assertFalse(result < expected);
        System.out.println("SearchForValues " + result + " languages");
    }

    @Test
    void searchForValuesGenres() {
        long result = handler.searchForValues(testList, Functions.uniqueGenres);
        long expected = 8;
        assertEquals(expected, result);
        assertFalse(expected < result);
        System.out.println("SearchForValues " + result + " genres");

    }
}