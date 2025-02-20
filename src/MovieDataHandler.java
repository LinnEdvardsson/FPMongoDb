import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MovieDataHandler {


    public Count<String> getCount (){
        Count<String> count = movie -> movie.getCast().stream();
        return count;
    }

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

    public List <String> getLeastActorsInMovie(List<Movie> movieList) {
        Comparator <Movie> comparator = Comparator.comparing(x-> x.getCast().size());
        return movieList.stream().min(comparator).map(Movie::getTitle).stream().toList();
    }

    /// lägger resultat i hashmap för att ge nyckel (actor) och värde (förekommer antal ggr). Går igenom listan och filtrerar på värden och summerar hur många gånger man förekommer.
    public int actorsInMultipleMovies(List<Movie> movieList) {
        Map<String, Long> actorsInMovies = countValues(movieList, getCount());
        return actorsInMovies.entrySet().stream().filter(e -> e.getValue() > 1).mapToInt(e -> 1).sum();
    }


    /// samma comparing som ovan, men i return nu jämför values i mappen, och det högsta values hämtas nyckeln för.
    public String getMostPopularActor(List<Movie> movieList) {
        Map <String, Long> popularActor = countValues(movieList, getCount());
        return popularActor.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public boolean getMulitMovieTitle(List<Movie> movieList) {
        Map<String, Long> movieTitles = movieList.stream().map(Movie::getTitle).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        return movieTitles.entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

    public long searchForValues(List<Movie> movieList, ValueSearch values) {
        return movieList.stream().map(values::search).flatMap(List::stream).distinct().count();
    }

    /// Function identity retunerar inmatat data (då från movieList). Mappar allt med förekomster.
    public <T> Map<T, Long> countValues(List<Movie> movieList, Count<T>count) {
        return movieList.stream().flatMap(count::getCount).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}


