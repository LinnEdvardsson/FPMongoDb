//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.IntStream;
//
//public class MovieDataHandler {
//
//     List <Movie> movies;
//
//    public MovieDataHandler(List <Movie> movies) {
//       this.movies = movies;
//    }
//
//    /// Fråga ett: Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
//    /// Filtrerar filmer i listan på filmer från 1975, omvandlar varje match till 1 för att summera alla.
//    public int getNumbOfMovies(List <Movie> movies ) {
//        return movies.stream().filter(movie -> movie.getYear() == 1975).mapToInt(movie -> 1).sum();
//    }
//
//    // movies.stream().mapToInt(Integer::valueOf).summaryStatistics().getCount();
//
//    public static void main(String[] args) {
//       // MovieDataHandler handler = new MovieDataHandler();
//        MovieDataHandler handler = new MovieDataHandler();
//
//    }
// }
