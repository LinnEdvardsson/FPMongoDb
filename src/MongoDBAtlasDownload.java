import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Locale.filter;
import static java.util.Locale.forLanguageTag;

//"mongodb+srv://linnedvardsson:bulle123@cluster0.kh0oq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
public class MongoDBAtlasDownload {

    List<Movie> movieList;

    public MongoDBAtlasDownload() {

        //Skriv in rätt uri!
        String uri = "mongodb+srv://linnedvardsson:bulle123@cluster0.kh0oq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";


        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

//             Skriver ut alla filmer
            for (Movie movie : movieList) {
                System.out.println(movie);
            }

            getAllQuestions();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      public void getAllQuestions() {
          NumbOfMovies();
          LongestMovie();
          UniqueGenres();
          HighestRatedMovieActors();
          MovieWithLeastActors();
          ActorsInMultipleMovies();
          PopularActors();
          Uniquelanguage();
          MultiMovieTitle();
      }

    // Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    // Filtrerar filmer i listan på filmer från 1975, omvandlar varje match till 1 för att summera alla.
    public long getNumbOfMovies(List<Movie> movieList) {
        return movieList.stream().filter(movie -> movie.getYear() == 1975).count();
    }

    // Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    // Mappar lista på runtime och tar ut int-elementen med mappning r för att hämta maxvärde.
    public int getLongestMovie(List<Movie> movieList) {
        return movieList.stream().mapToInt(Movie::getRuntime).max().orElse(0);
    }

    // Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal. FEL????
    public long getUniqueGenres(List<Movie> movieList) {
        return movieList.stream().flatMap(x -> x.getGenres().stream()).distinct().count();
    }

    //Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.(KAN VARA FLERA)

    /// Lägger in rating i en comparing som jämför objekten Movie baserat på rating. Sätter den i max för att returnera högsta värdet med skådespelare.
    public List<String> getActors(List<Movie> movieList) {
        Comparator<Movie> movieComparator = Comparator.comparing(Movie::getImdbRating);
        return movieList.stream().max(movieComparator).map(Movie::getCast).orElse(Collections.emptyList());

    }

    //Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String
    public String getLeastActors(List<Movie> movieList) {
        Comparator<Movie> compActor = Comparator.comparing(x -> x.getCast().size());
        return movieList.stream().min(compActor).map(Movie::getTitle).orElse(null);
    }

    //Hur många skådisar var med i mer än 1 film? Returnera ett tal.

    /// lägger resultat i hashmap för att ge nyckel (actor) och värde (förekommer antal ggr). Går igenom listan och filtrerar på värden och summerar hur många gånger man förekommer.
    public int actorsInMovies(List<Movie> movieList) {
        Map<String, Long> acc = movieList.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        return acc.entrySet().stream().filter(e -> e.getValue() > 1).mapToInt(e -> 1).sum();
    }

    //Vad hette den skådis som var med i flest filmer? Returnera en String

    /// samma comparing som ovan, men i return nu jämför values i mappen, och det högsta values hämtas nyckeln för.
    public String getMostPopularActor(List<Movie> movieList) {
        Map<String, Long> pop = movieList.stream().flatMap(m -> m.getCast().stream()).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        return pop.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }

    public long getUniquelanguage(List<Movie> movieList) {
        return movieList.stream().flatMap(x -> x.getLanguages().stream()).distinct().count();
    }

    public boolean getMulitMovieTitle(List<Movie> movieList) {
        Map<String, Long> movieTitles = movieList.stream().map(Movie::getTitle).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        return movieTitles.entrySet().stream().anyMatch(e -> e.getValue() > 1);
    }

    public void NumbOfMovies() {
        System.out.println("Number of movies from 1975: " + getNumbOfMovies(movieList));
    }

    public void LongestMovie() {
        System.out.println("Longest movie was: " + getLongestMovie(movieList) + " minutes");
    }

    public void UniqueGenres() {
        System.out.println("Unique genres: " + getUniqueGenres(movieList));
    }

    public void HighestRatedMovieActors() {
        System.out.println("Actors in highest rated movie is: " + getActors(movieList));
    }

    public void MovieWithLeastActors() {
        System.out.println("Least number of actors in movie: " + getLeastActors(movieList));
    }

    public void ActorsInMultipleMovies() {
        System.out.println("Number of actors in more than 1 movie: " + actorsInMovies(movieList));
    }

    public void PopularActors() {
        System.out.println("Pop actor: " + getMostPopularActor(movieList));
    }

    public void Uniquelanguage() {
        System.out.println("Number of unique languages: " + getUniquelanguage(movieList));
    }

    public void MultiMovieTitle() {
        System.out.println("Movie title belongs to several movies: " + getMulitMovieTitle(movieList));
    }

    public static void main(String[] args) {
        MongoDBAtlasDownload m = new MongoDBAtlasDownload();
    }
}