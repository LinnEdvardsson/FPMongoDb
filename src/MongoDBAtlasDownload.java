import com.mongodb.client.*;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Locale.filter;
import static java.util.Locale.forLanguageTag;

public class MongoDBAtlasDownload {
    private String uri = "mongodb+srv://linnedvardsson:bulle123@cluster0.kh0oq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    List<Movie> movieList;
    MovieDataHandler handler;
    OutPut oPd;

    public MongoDBAtlasDownload() {
        movieList = new ArrayList<>();
        handler = new MovieDataHandler();
        oPd = new OutPut(movieList);
        Download();

    }

    public List<Movie> Download() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
//            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            getAllQuestions();


        } catch (Exception e){
            e.printStackTrace();
        }
        return movieList;
    }

    public void getAllQuestions() {
        oPd.NumbOfMovies();
        oPd.LongestMovie();
        oPd.UniqueGenres();
        oPd.HighestRatedMovieActors();
        oPd.MovieWithLeastActors();
        oPd.ActorsInMultipleMovies();
        oPd.PopularActors();
        oPd.Uniquelanguage();
        oPd.MultiMovieTitle();
    }

    public static void main(String[] args) {
        MongoDBAtlasDownload m = new MongoDBAtlasDownload();
    }
}