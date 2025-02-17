import com.mongodb.client.model.geojson.LineString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieDataHandlerTest {
    MovieDataHandler handler = new MovieDataHandler();
    List<Movie> testList;

    {

    testList =Arrays.asList(
            new Movie("F001", "The Shawshank Redemption", 1994, List.of("Drama"), "Frank Darabont", List.of("Tim Robbins", "Morgan Freeman"), 9.3, List.of("English"), 142),
            new Movie("F002", "The Godfather", 1972, List.of("Crime", "Drama"), "Francis Ford Coppola", List.of("Marlon Brando", "Al Pacino"), 9.2, List.of("English", "Italian"), 175),
            new Movie("F003", "The Dark Knight", 2008, List.of("Action", "Crime", "Drama"), "Christopher Nolan", List.of("Christian Bale", "Heath Ledger"), 9.0, List.of("English"), 152),
            new Movie("F004", "Pulp Fiction",1994,List.of("Crime", "Drama"), "Quentin Tarantino",List.of("John Travolta","Uma Thurman"),8.9,List.of("English"),154),
            new Movie("F005","Schindler's List",1993,List.of("Biography", "Drama","History"), "Steven Spielberg",List.of("Liam Neeson","Ralph Fiennes"),9.0,List.of("English","German","Polish"),195),
            new Movie("F006","Inception",2010,List.of("Action", "Adventure","Sci-Fi"), "Christopher Nolan",List.of("Leonardo DiCaprio","Joseph Gordon-Levitt"),8.8,List.of("English","Japanese","French"),148)
            );
}

    @Test
    void getNumbOfMovies() {
        long count = handler.getNumbOfMovies(testList, 1994);
        assertEquals(2, count);
    }

    @Test
    void getLongestMovie() {
    }

    @org.junit.jupiter.api.Test
    void getActors() {
    }

    @org.junit.jupiter.api.Test
    void getLeastActors() {
    }

    @org.junit.jupiter.api.Test
    void actorsInMovies() {
    }

    @org.junit.jupiter.api.Test
    void getMostPopularActor() {
    }

    @org.junit.jupiter.api.Test
    void getMulitMovieTitle() {
    }

    @org.junit.jupiter.api.Test
    void searchForValues() {
    }
}