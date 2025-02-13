import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

//"mongodb+srv://linnedvardsson:bulle123@cluster0.kh0oq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
public class MongoDBAtlasDownload {

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


            // Skriver ut alla filmer
//            for (Movie movie : movieList) {
//                System.out.println(movie);
//            }

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften

//            System.out.println(getNumbOfMovies(movieList));
            getAllQuestions(movieList);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllQuestions(List<Movie> movieList) {
        System.out.println("Number of movies from 1975: " + getNumbOfMovies(movieList));
        System.out.println("Longest movie was: " + getLongestMovie(movieList));
        System.out.println("Unique genres: " + getUniqueGenres(movieList));
    }

    /// Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal
    /// Filtrerar filmer i listan på filmer från 1975, omvandlar varje match till 1 för att summera alla.
    public long getNumbOfMovies(List<Movie> movieList) {
        return movieList.stream().filter(movie -> movie.getYear() == 1975).count();
    }

    /// Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    /// Mappar lista på runtime och tar ut int-elementen med mappning r för att hämta maxvärde.
    public int getLongestMovie(List<Movie> movieList) {
        return movieList.stream().mapToInt(Movie::getRuntime).max().orElse(0);
    }

    /// Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal. FEL????
    public long getUniqueGenres(List<Movie> movieList) {
        return movieList.stream().flatMap(x -> x.getGenres().stream()).distinct().count();
    }




    public static void main(String[] args) {
        MongoDBAtlasDownload m = new MongoDBAtlasDownload();
    }
}