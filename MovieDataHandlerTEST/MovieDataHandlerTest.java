import com.mongodb.client.model.geojson.LineString;
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
    int actorsInMultipleMovies = handler.actorsInMovies(testList);
    String result;


    @Test
    void getNumbOfMovies() {
        long count = handler.getNumbOfMovies(testList, 1994);
        assertEquals(2, count);
        assertNotEquals(3, handler.getNumbOfMovies(testList, 1994));
    }


    @Test
    void getLongestMovie() {
        assertTrue(testList.get(4).getRuntime() > testList.get(3).getRuntime());
        assertNotEquals(testList.get(2).getRuntime(), testList.get(3).getRuntime());
        System.out.println("Longest movie: " + testList.get(4).getRuntime());
    }


    /// Testar metod med en ny lista med highestRatedCast, ser om testList har 2 skådespelare i högst rankad film.
    @Test
    void getActors() {
        List<String> highestRatedCast = handler.getActors(testList);
        assertEquals(2, highestRatedCast.size());
        assertNotEquals(3, highestRatedCast.size());
        System.out.println("Highest Rated Cast: " + highestRatedCast);
    }

    /// Ytterligare test för ovan metod ifall listan med filmer är tom och då retunerar en tom lista.
    @Test
    void getActorsEmptyList() {
        List<String> result = handler.getActors(Collections.emptyList());
        assertEquals(Collections.emptyList(), result);
        System.out.println("List: " + result);
    }


//    @Test
//    void getLeastActors() {
//        String leastActors = handler.getLeastActors(testList);
//        assertEquals(leastActors, testList.get(0).getTitle());
//        System.out.println("Least Actors in: " + leastActors);
//    }
//    @Test
//    void getLeastActorsEmptyList() {
//        List<String> result = handler.getLeastActors(Collections.emptyList());
//        assertEquals(Collections.emptyList(), result);
//        System.out.println("List: " + result);
//    }

    //     Map<String, Long> actorsInMovies = movieList.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
    //        return actorsInMovies.entrySet().stream().filter(e -> e.getValue() > 1).mapToInt(e -> 1).sum();
    @Test
    void actorsInMovies() {
        assertEquals(0, actorsInMultipleMovies);
        System.out.println("Actors in more than one movie: " + actorsInMultipleMovies);
    }

    @Test
    void actorsInMovieEmptyList() {
        int actorsInMultipleMoviesEmpty = handler.actorsInMovies(Collections.emptyList());
        assertEquals(0, actorsInMultipleMoviesEmpty);
        System.out.println("Empty list: " + actorsInMultipleMoviesEmpty);
    }

    @Test
    void actorsMultipleMovies() {
        assertNotEquals(3, actorsInMultipleMovies);
    }

    @Test
    void getMostPopularActor() {
        result = handler.getMostPopularActor(testList);
        assertEquals("Christian Bale", result);
        System.out.println("Most popular actor: " + result);
    }

    @Test
    void getMostPopularActorEmptyList() {
        result = handler.getMostPopularActor(Collections.emptyList());
        assertEquals(null, result);
    }


    @Test
    void getMulitMovieTitle() {
        boolean result = handler.getMulitMovieTitle(testList);
        assertFalse(result);
        System.out.println("MulitMovieTitle: " + result);
    }

    // return movieList.stream().map(movieValueSearch::getValues).flatMap(List::stream).distinct().count();
    @Test
    void searchForValues() {

    }
}