import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@FunctionalInterface
public interface Count <T>{
    Stream <T> getCount(Movie movie);
}

