package pkner.pkncoder.CustomMethods;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Api {

    // The client used to send the HttpRequest
    private HttpClient client;

    // Gson's json utilities
    private Gson gson;
    private JsonObject json;
    
    // Constructor - sets all the values needed before making api calls
    public Api() {

        // Create our http client
        this.client = HttpClient.newHttpClient();
        
        // Create our gson builder
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    }

    /*
     * Sends a get request and sets json as the value found
     * 
     * @param   url the url to send the request to
     */
    public void sendGetRequest(String url) throws IOException, InterruptedException {

        // Create our request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url)) // url
            .GET()
            .build();

        // Get our response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Make our JsonObject
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        // Set json
        this.json = json;
    }

    /*
     * Returns the currnet JsonObject fetched
     */
    public JsonObject getJson() {
        return json;
    }

    /*
     * Gets a value from the current fetched json data
     * 
     * @param   key the key of which to get from
     * @return  the found key (null if not in the data)
     */
    public JsonElement get(String key) {
        // Return the value at json
        return json.get(key);   
    }

    /*
     * Sets a new JsonObject as our stored json data
     * 
     * @param newJsonObject the json object to be set
     */
    public void setJsonObject(JsonObject newJsonObject) {
        json = newJsonObject;
    }

    // Print out the json with gson's pretty printing
    public void prettyPrintJson() {
        String jsonOutput = gson.toJson(json);

        System.out.println(jsonOutput);
    }

}
