import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

class Book {
    private String id;
    private String title;
    private String author;
    private Double rating;
    public Book (String id, String title, String author, Double rating){
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }
    public Book (){ // default constructor

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Double getRating() {
        return rating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

public class BookApi {
    public static void main(String[] args) throws IOException {
        String apiURL = "https://akai-recruitment.herokuapp.com/book";
        JSONArray jsonArray = sendHttpGETRequest(apiURL);
        if(jsonArray != null){
            threeHighestRatedAuthors(jsonArray);
        }
        else {
            System.out.println("GET request failed.");
        }

    }
    private static void threeHighestRatedAuthors(JSONArray jsonArray){
        Iterator<Object> iterator = jsonArray.iterator();

        HashMap<String, Double> authorsRatings = new HashMap<>();
        HashMap<String,Integer> authorsAmountOfBooks = new HashMap<>();
        String authorName;
        Double authorSingleBookRating;
        Integer amountOfBooks;

        while (iterator.hasNext()){
            JSONObject book = (JSONObject) iterator.next();
            authorName = book.getString("author");
            authorSingleBookRating = book.getDouble("rating");
            if (!authorsRatings.containsKey(authorName)){
                authorsRatings.put(authorName,authorSingleBookRating);
                authorsAmountOfBooks.put(authorName,1);
            }
            else {
                Double sumOfRatings = authorSingleBookRating + authorsRatings.get(authorName);
                authorsRatings.replace(authorName,sumOfRatings);
                amountOfBooks = authorsAmountOfBooks.get(authorName);
                amountOfBooks ++;
                authorsAmountOfBooks.replace(authorName,amountOfBooks);
            }
        }

        for(String author: authorsRatings.keySet()){
            authorsRatings.replace(author,authorsRatings.get(author)/authorsAmountOfBooks.get(author));
        }

        Double [] arrayOfRatings = new Double[authorsRatings.size()];
        int i = 0;
        for (Double rating: authorsRatings.values()){
            arrayOfRatings[i] = rating;
            i ++;
        }
        Arrays.sort(arrayOfRatings, Collections.reverseOrder());

        DecimalFormat f = new DecimalFormat ("0.00");
        int k = 0;
        for (Map.Entry<String, Double> authorRat: authorsRatings.entrySet()){
            if(authorRat.getValue().equals(arrayOfRatings[k]) && k < 3){
                System.out.println(authorRat.getKey() + " - " + f.format(arrayOfRatings[k]));
                k++;
            }
        }
    }

    private static JSONArray sendHttpGETRequest(String apiURL) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK){// Successful connection
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()){
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();

            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            return jsonArray;
        }
        else {
            return null;
        }
    }
}
