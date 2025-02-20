import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functions {


    Function<List<Movie>, Map<String, Long>> actorsInMultipleMovies = list -> list.stream().map(Movie::getCast).
            flatMap(List::stream)
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    Function<List<Movie>, Stream<String>> countAttributeFunc = movieList -> movieList.stream().map(Movie::getLanguages).flatMap(List::stream);

    Function<List<Movie>, Map<String, Long>> moviesWithDuplicateTitles = movieList -> movieList.stream().map(Movie::getTitle)
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    Function<List<Movie>, Stream<String>> uniqueGenres = movieList1 -> movieList1.stream().map(Movie::getGenres).flatMap(List::stream);

    Function<List<Movie>, Map<String, Long>> mostPopularActor = list -> list.stream().map(Movie::getCast)
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

}
