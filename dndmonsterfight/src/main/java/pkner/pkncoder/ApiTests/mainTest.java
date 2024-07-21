package pkner.pkncoder.ApiTests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pkner.pkncoder.CustomMethods.Simple;

public class mainTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.dnd5eapi.co/api/monsters/"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.create();

        String jsonOutput = gson.toJson(json);

        Simple.println(jsonOutput);
    }
}