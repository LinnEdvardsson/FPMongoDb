import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutPut {

    List<Movie> movieList;
    MovieDataHandler handler;
    Functions func;

    public OutPut(List<Movie> movieList) {
        this.movieList = movieList;
        handler = new MovieDataHandler();
        func = new Functions();
    }

    public void NumbOfMovies() {
        System.out.println("Number of movies from 1975: " + handler.getNumbOfMovies(movieList, 1975));
    }

    public void LongestMovie() {
        System.out.println("Longest movie was: " + handler.getLongestMovie(movieList) + " minutes");
    }

    public void UniqueGenres() {
        System.out.println("Unique genres: " + handler.searchForValues(movieList, func.uniqueGenres));
    }

    public void HighestRatedMovieActors() {
        System.out.println("Actors in highest rated movie is: " + handler.getActorsInHighestRatedMovie(movieList));
    }

    public void MovieWithLeastActors() {
        System.out.println("Least number of actors in movie: " + handler.getLeastActorsInMovie(movieList));
    }

    public void ActorsInMultipleMovies() {
        System.out.println("Number of actors in more than 1 movie: " + handler.actorsInMultipleMovies(movieList, func.actorsInMultipleMovies));
    }

    public void PopularActors() {
        System.out.println("Pop actor: " + handler.getMostPopularActor(movieList, func.mostPopularActor));
    }

    public void UniqueLanguage() {
        System.out.println("Number of unique languages: " + handler.searchForValues(movieList, func.countAttributeFunc));
    }

    public void MultiMovieTitle() {
        System.out.println("Movie title belongs to several movies: " + handler.getMulitMovieTitle(movieList, func.moviesWithDuplicateTitles));
    }
}
