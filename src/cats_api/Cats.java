package cats_api;

// Import HTTP dependencies
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

// Import JSON Simple
// import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Cats {

    // Method to connect to API and parse response as a JSON object
    public JSONObject getCatFact() throws Exception {

        String url = "https://catfact.ninja/fact";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // Parse the JSON response from the API Endpoint
        Object catFact = new JSONParser().parse(response.body());

        // Typecast the catFact Object to a JSONObject
        JSONObject catFactJSON = (JSONObject) catFact;

        return catFactJSON;
    }

}
