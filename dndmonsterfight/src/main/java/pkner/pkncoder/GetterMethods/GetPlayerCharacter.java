package pkner.pkncoder.GetterMethods;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;

import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.PlayerCharacter;
import pkner.pkncoder.Classes.PlayerClass;
import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Simple;
import pkner.pkncoder.CustomMethods.Api;

public class GetPlayerCharacter {

    // Hold our json api connection object
    private static Api json;

    // Hold a base url that will be always repeated
    private static String baseUrl = "https://www.dnd5eapi.co";

    // Hold a list of the classes (can be retrieved through the api, however that's another call that we already know)
    private static String[] classes = {"barbarian", "bard", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"};

    // Hold an array list of armor names as a buffer since we see them anyways
    private static ArrayList<String> armorNameBuffer = new ArrayList<String>();

    /*
     * Main method
     * 
     * @returns the final player
     */
    public static PlayerCharacter getPlayer() throws IOException, InterruptedException {
        json = new Api(); // Initialize our API object

        // Get the player's name
        String name = Simple.getStringInput("Name: ");

        // Get the abilities
        int[][] abilities = GetHelperMethods.getAbilityScores();

        // Get the player's level
        int level = Simple.getIntInput("Level: ", "Invalid Input", "[1-9][0-9]*");

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
        // Proficiency bonus is the players level devided by four, rounded up, plus one
        return new PlayerCharacter(name, abilities[0], abilities[1], abilities[2], abilities[3], abilities[4], abilities[5], level, (int) Math.ceil(level / 4.0) + 1, userClass, weapon, armor);
    }

    /*
     * Gets the player class
     * 
     * @return  The final class object
     */
    private static PlayerClass getPlayerClass() throws IOException, InterruptedException {
        Simple.printArray(classes); // Print the classes that can be chosen from
        Simple.space();

        // Get the user's chosen class
        String userClassChoice = Simple.getStringInput("What class would you like to choose: ", classes.clone(), "Invalid Input", false);

        // Send a request with the base url and the addition of /api/classes/ to filter for the classes and add on the user's chosen class (lower cased)
        json.sendGetRequest(baseUrl + "/api/classes/" + userClassChoice.toLowerCase());

        // Create and return the object
        return new PlayerClass(json.get("name").getAsString(), json.get("hit_die").getAsInt());
    }

    /*
     * Gets the player's weapon
     * 
     * @return  The player's weapon
     */
    private static Weapon getPlayerWeapon() throws IOException, InterruptedException {

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

                    // Get a new split url to check for invalid stuff
                    splitUrl = equipment.getAsJsonObject().get("url").getAsString().split("/");

                    // If there is magic-items in the 2rd url index then we don't want this
                    if (splitUrl[2].equals("magic-items")) {
                        continue;
                    }

                    // Also manually remove net so we don't have to worry about it
                    if (splitUrl[3].equals("net")) {
                        continue;
                    }

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

        // At the end of everything, add a "No Armor choice"
        armorNameBuffer.add("No Armor");

        // Get the user's weapon
        String userWeapon = Simple.getStringInput("\nWhat weapon would you like: ", weaponNameBuffer.toArray(new String[weaponNameBuffer.size()]), "Invalid Input", false);

        // Send the request based and formatted on the user's weapon
        json.sendGetRequest(baseUrl + "/api/equipment/" + userWeapon.toLowerCase().replaceAll(" ", "-"));

        // Create and return the weapon
        return new Weapon(
            json.get("name").getAsString(), // {name: ""}
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(),  // {cost:{quantity: ""}}
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]), // {damage:{damage_dice: ""}}

                // Sometimes it is just a set 1 or 2 damage, so this acounts for that (no "d" in the string)
                json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d").length == 1 ? 1 : Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1])
            }
        );
    }

    /*
     * Gets the player's armor
     * 
     * @return  The armor finally assembled
     */
    private static Armor getPlayerArmor() throws IOException, InterruptedException {
        
        // Get the armor's name in a string[]
        String[] armorArray = armorNameBuffer.toArray(new String[armorNameBuffer.size()]);

        // Wipe the Armor Name Buffer after so we can have a clean slate for next time
        armorNameBuffer.clear();

        // Print the array our
        Simple.printArray(armorArray);
        
        // Get the user's armor
        String userArmor = Simple.getStringInput("\nWhat armor would you like: ", armorArray, "Invalid Input", false);

        // Check to see if the user chose no armor
        if (userArmor.toLowerCase().equals("no armor")) {

            // If the user did then return the no armor object
            return new Armor(
                "No Armor",
                10,
                true,
                Integer.MAX_VALUE
            );
        }

        // Send the request of that armor
        json.sendGetRequest(baseUrl + "/api/equipment/" + userArmor.toLowerCase().replaceAll(",", "").replaceAll(" ", "-"));

        // Create and return the object
        return new Armor(
            json.get("name").getAsString(), // {name: ""}
            json.get("armor_class").getAsJsonObject().get("base").getAsInt(), // {armor_class: {base: ""}}
            json.get("str_minimum").getAsInt(), // {str_minimum: ""}
            json.get("armor_class").getAsJsonObject().get("dex_bonus").getAsBoolean(), // {armor_class: {dex_bonus: ""}}
            json.get("armor_class").getAsJsonObject().get("max_bonus") == null ? Integer.MAX_VALUE : json.get("armor_class").getAsJsonObject().get("max_bonus").getAsInt()
        );
    }

}
