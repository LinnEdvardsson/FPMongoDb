import java.util.List;

@FunctionalInterface
public interface MovieValueSearch {
    List <String> getValues(Movie movie);
}
