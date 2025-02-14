import java.util.List;

public class OutPut {

    List<Movie> movieList;
    MovieDataHandler handler;

    public OutPut(List<Movie> movieList) {
        this.movieList = movieList;
        handler = new MovieDataHandler();
    }

    public void NumbOfMovies() {
        System.out.println("Number of movies from 1975: " + handler.getNumbOfMovies(movieList));
    }

    public void LongestMovie() {
        System.out.println("Longest movie was: " + handler.getLongestMovie(movieList) + " minutes");
    }

    public void UniqueGenres() {
        System.out.println("Unique genres: " + handler.getUniqueGenres(movieList));
    }

    public void HighestRatedMovieActors() {
        System.out.println("Actors in highest rated movie is: " + handler.getActors(movieList));
    }

    public void MovieWithLeastActors() {
        System.out.println("Least number of actors in movie: " + handler.getLeastActors(movieList));
    }

    public void ActorsInMultipleMovies() {
        System.out.println("Number of actors in more than 1 movie: " + handler.actorsInMovies(movieList));
    }

    public void PopularActors() {
        System.out.println("Pop actor: " + handler.getMostPopularActor(movieList));
    }

    public void Uniquelanguage() {
        System.out.println("Number of unique languages: " + handler.getUniquelanguage(movieList));
    }

    public void MultiMovieTitle() {
        System.out.println("Movie title belongs to several movies: " + handler.getMulitMovieTitle(movieList));
    }


}
