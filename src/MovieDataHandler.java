import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MovieDataHandler {


    /// Filtrerar filmer i listan på filmer från 1975, omvandlar varje match till 1 för att summera alla.
    public long getNumbOfMovies(List<Movie> movieList, long year) {
        return movieList.stream().filter(movie -> movie.getYear() == year).count();
    }


    /// Mappar lista på runtime och tar ut int-elementen med mappning r för att hämta maxvärde.
    public int getLongestMovie(List<Movie> movieList) {
        return movieList.stream().mapToInt(Movie::getRuntime).max().orElse(0);
    }

    /// Lägger in rating i en comparing som jämför objekten Movie baserat på rating. Sätter den i max för att returnera högsta värdet med skådespelare.
    public List<String> getActorsInHighestRatedMovie(List<Movie> movieList) {
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getImdbRating);
        return movieList.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());

    }

    public List<String> getLeastActorsInMovie(List<Movie> movieList) {
        Comparator<Movie> comparator = Comparator.comparing(x -> x.getCast().size());
        return movieList.stream().min(comparator).map(Movie::getTitle).stream().toList();
    }

    /// lägger resultat i hashmap för att ge nyckel (actor) och värde (förekommer antal ggr). Går igenom listan och filtrerar på värden och summerar hur många gånger man förekommer.
    public int actorsInMultipleMovies(List<Movie> movieList, Function<List<Movie>, Map<String, Long>> mapper) {
        Map<String, Long> attributeCounterMap = mapper.apply(movieList);
        return attributeCounterMap.entrySet().stream().filter(e -> e.getValue() > 1).mapToInt(e -> 1).sum();
    }


    /// samma comparing som ovan, men i return nu jämför values i mappen, och det högsta values hämtas nyckeln för.
    public String getMostPopularActor(List<Movie> movieList, Function<List<Movie>, Map<String, Long>> mapper) {
        Map<String, Long> popularActor = mapper.apply(movieList);
        return popularActor.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public boolean getMulitMovieTitle(List<Movie> movieList, Function<List<Movie>, Map<String, Long>> mapper) {
        Map<String, Long> movieTitles = mapper.apply(movieList);
        return movieTitles.entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

    ///  Högre funktion. Tar Movielist och funktion som inparameter. Använder apply för att metoden ska "veta" att den kommer hantera en movielist.
    /// Hämtar metoden i output då med movielist och akutell funktion som inparameter. (Genre, Languages)
    public long searchForValues(List<Movie> movieList, Function<List<Movie>, Stream<String>> func) {
        return func.apply(movieList).distinct().count();
    }
}




