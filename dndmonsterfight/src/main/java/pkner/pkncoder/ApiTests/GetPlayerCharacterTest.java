package pkner.pkncoder.ApiTests;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;

import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.PlayerCharacter;
import pkner.pkncoder.Classes.PlayerClass;
import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Simple;
import pkner.pkncoder.CustomMethods.Api;

public class GetPlayerCharacterTest {

    private static String baseUrl = "https://www.dnd5eapi.co";
    private static Api json;

    private static String[] classes = {"barbarian", "bard", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"};

    private static ArrayList<String> armorNameBuffer = new ArrayList<String>();

    public static void main(String[] args) throws IOException, InterruptedException {
        PlayerCharacter player = getPlayerCharacter();

        Simple.clearTerminal();
        Simple.println(player);
    }

    public static PlayerCharacter getPlayerCharacter() throws IOException, InterruptedException {
        json = new Api(); // Initialize our API object

        // Get the players class
        Simple.clearTerminal();
        PlayerClass userClass = getPlayerClass();

        // Get the player's weapon
        Simple.clearTerminal();
        Weapon weapon = getPlayerWeapon();

        // Get the players armor
        Simple.clearTerminal();
        Armor armor = getPlayerArmor();

        // Create the player
        return new PlayerCharacter("", new int[] {1,1}, new int[] {1,1}, new int[] {1,1}, new int[] {1,1}, new int[] {1,1}, new int[] {1,1}, 1, userClass, weapon, armor);
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

    public static Weapon getPlayerWeapon() throws IOException, InterruptedException {

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

                        // Add this to the armor name buffer
                        armorNameBuffer.add(json.get("name").getAsString());
                        continue;
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

        String userWeapon = Simple.getStringInput("\nWhat weapon would you like: ", weaponNameBuffer.toArray(new String[weaponNameBuffer.size()]), "Invalid Input", false);

        json.sendGetRequest(baseUrl + "/api/equipment/" + userWeapon.toLowerCase().replaceAll(" ", "-"));

        return new Weapon(
            json.get("name").getAsString(), // {name: ""}
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(),  // {cost:{quantity: ""}}
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]), // {damage:{damage_dice: ""}}
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1]) // {damage:{damage_dice: ""}}
            }
        );
    }

    public static Armor getPlayerArmor() throws IOException, InterruptedException {
        
        String[] armorArray = armorNameBuffer.toArray(new String[armorNameBuffer.size()]);

        Simple.printArray(armorArray);
        
        String userArmor = Simple.getStringInput("\nWhat armor would you like: ", armorArray, "Invalid Input", false);

        json.sendGetRequest(baseUrl + "/api/equipment/" + userArmor.toLowerCase().replaceAll(" ", "-"));

        return new Armor(
            json.get("name").getAsString(), // {name: ""}
            json.get("armor_class").getAsJsonObject().get("base").getAsInt(), // {armor_class: {base: ""}}
            json.get("str_minimum").getAsInt(), // {str_minimum: ""}
            json.get("armor_class").getAsJsonObject().get("dex_bonus").getAsBoolean(), // {armor_class: {dex_bonus: ""}}
            json.get("armor_class").getAsJsonObject().get("max_bonus") == null ? Integer.MAX_VALUE : json.get("armor_class").getAsJsonObject().get("max_bonus").getAsInt()
        );
    }

}
