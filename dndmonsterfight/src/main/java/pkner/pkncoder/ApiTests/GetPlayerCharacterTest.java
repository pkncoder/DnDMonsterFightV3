package pkner.pkncoder.ApiTests;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;

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

    private static ArrayList<String> armorIndexBuffer = new ArrayList<String>();
    public static void main(String[] args) throws IOException, InterruptedException {
        json = new Api(); // Initialize our API object

        // Get the players class
        Simple.clearTerminal();
        PlayerClass userClass = getPlayerClass();

        // Get the player's weapon
        Simple.clearTerminal();
        getPlayerWeapon();
    }

    public static PlayerClass getPlayerClass() throws IOException, InterruptedException {
        Simple.printArray(classes); // Print the classes that can be chosen from
        Simple.space();

        // Get the user's chosen class
        String userClassChoice = Simple.getStringInput("What class would you like to choose: ", classes, "Invalid Input", false);

        // Send a request with the base url and the addition of /api/classes/ to filter for the classes and add on the user's chosen class (lower cased)
        json.sendGetRequest(baseUrl + "/api/classes/" + userClassChoice.toLowerCase());

        return new PlayerClass(json.get("name").getAsString(), json.get("hit_die").getAsInt());
    }

    public static void getPlayerWeapon() throws IOException, InterruptedException {

        // Hold a variable that will be used as an addition onto our proficiency name
        String addition;

        // Hold a variable that will store all of the names of the weapons
        ArrayList<String> weaponNameBuffer = new ArrayList<String>();
        
        // Loop each proficiency in the proficiencies of the class
        for (JsonElement proficiency: json.get("proficiencies").getAsJsonArray().asList()) {

            // Send a get request to the proficiencies url
            json.sendGetRequest(baseUrl + proficiency.getAsJsonObject().get("url").getAsString());

            // Send a get request to the refrences's url
            json.sendGetRequest(baseUrl + json.get("reference").getAsJsonObject().get("url").getAsString());

            // Check to see if it is a shield
            if (json.get("url").getAsString().equals("/api/equipment/shield")) {

                // If it is, then continue and skip it
                continue;
            }

            // Get a split url to test the url for patterns
            String[] splitUrl = json.get("url").getAsString().split("/");

            // If this is a collection of equipment items
            if (splitUrl[2].equals("equipment-categories")) {

                // Loop each equipment item in the list
                for (JsonElement equipment: json.get("equipment").getAsJsonArray().asList()) {

                    // Send a request to the equipment's url
                    json.sendGetRequest(baseUrl + equipment.getAsJsonObject().get("url").getAsString());

                    // Test to see if there is an armor aspect to it
                    if (json.get("armor_class") != null) {

                        // Add this to the armor index buffer
                        armorIndexBuffer.add(json.get("index").getAsString());
                        break;
                    }

                    // Else, set the addition to weapon
                    Simple.println(json.get("name").getAsString());
                    weaponNameBuffer.add(json.get("name").getAsString());
                }

                // Continue to the next loop to stop any other tests below
                continue;
            }

            // If there is a damage component
            else if (json.get("damage") != null) {

                //  Set the addition to weapon
                Simple.println(json.get("name").getAsString());
                weaponNameBuffer.add(json.get("name").getAsString());
            }

        }

        Simple.getStringInput("\nWhat weapon would you like: ", weaponNameBuffer.toArray(new String[weaponNameBuffer.size()]), "Invalid Input", false);
    }

}
