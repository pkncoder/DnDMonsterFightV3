package pkner.pkncoder.ApiTests;

import java.io.IOException;

import pkner.pkncoder.Classes.PlayerClass;
// import pkner.pkncoder.Classes.Weapon;
// import pkner.pkncoder.Classes.Armor;
// import pkner.pkncoder.Classes.PlayerCharacter;

import pkner.pkncoder.CustomMethods.Simple;
import pkner.pkncoder.CustomMethods.Api;

public class GetPlayerCharacterTest {

    private static String baseUrl = "https://www.dnd5eapi.co";
    private static Api json;

    private static String[] classes = {"barbarian", "bard", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"};

    public static void main(String[] args) throws IOException, InterruptedException {
        json = new Api(); // Initialize our API object

        // Clear the terminal and send the player's class
        Simple.clearTerminal();
        getPlayerClass();

        // Create the class
        PlayerClass userClass = new PlayerClass(json.get("name").getAsString(), json.get("hit_die").getAsInt());

        // Print out the class
        Simple.println(userClass);
    }

    public static void getPlayerClass() throws IOException, InterruptedException {
        Simple.printArray(classes); // Print the classes that can be chosen from
        Simple.space();

        // Get the user's chosen class
        String userClassChoice = Simple.getStringInput("What class would you like to choose: ", classes, baseUrl, false);

        // Send a request with the base url and the addition of /api/classes/ to filter for the classes and add on the user's chosen class (lower cased)
        json.sendGetRequest(baseUrl + "/api/classes/" + userClassChoice.toLowerCase());
    }

}
