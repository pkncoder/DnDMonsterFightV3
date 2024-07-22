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
    
    // Constructor
    public Api() {

        // Create our http client
        this.client = HttpClient.newHttpClient();
        
        // Create our gson builder
        this.gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    }

    // Getters
    // Get the fetched json
    public JsonObject getJson() {
        return json;
    }

    // Get at a key
    // String[] keys - key by key getting
    public JsonElement get(String key) {
        // Return the value at json
        return json.get(key);   
    }

    // Setters
    // Set the new json with a jsonObject
    public void setJsonObject(JsonObject newJsonObject) {
        json = newJsonObject;
    }

    // Send our get request
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

    public void prettyPrintJson() {
        String jsonOutput = gson.toJson(json);

        System.out.println(jsonOutput);
    }

}
