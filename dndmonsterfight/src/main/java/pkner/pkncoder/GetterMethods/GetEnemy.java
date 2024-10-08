// Package
package pkner.pkncoder.GetterMethods;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;

// Other classes used in enemy
import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.Enemy;
import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Api;
// The usual use case methods
import pkner.pkncoder.CustomMethods.Simple;

public class GetEnemy {

    private static Api json;

    private static String characterUrl = "https://www.dnd5eapi.co";

    /*
     * The main method that gets the final put together enemy
     * 
     * @return  the final enemy object
     */
    public static Enemy getEnemy(String[] invalidNames) throws IOException, InterruptedException {
        /*
         * Gameplan:
         * Get the name
         * Get the hp
         * Get the scores / stats / abilities
         * Get the weapon
         * Get the armor
        */

        json = new Api();

        // Get the enemy's name
        String name = Simple.getStringInput("Name: ", invalidNames, "Name already taken.");

        // Get the enemy's hp
        // The regex states that it can only be above (or equal to) 1
        int hp = Simple.getIntInput("Hit Points: ", "Invalid Input", "[1-9][0-9]*");

        // Get our scores
        int[][] abilityScores = GetHelperMethods.getAbilityScores();

        // Get our weapon
        Simple.clearTerminal();
        Weapon weapon = getWeapon();
        
        
        // Get our armor
        Simple.clearTerminal();
        Armor armor = getArmor();

        // Return our final enemy
        return new Enemy(name, hp, abilityScores[0], abilityScores[1], abilityScores[2], abilityScores[3], abilityScores[4], abilityScores[5], weapon, armor);
    }

    /*
     * Get the final found weapon
     * 
     * @return  the weapon with the data from the api put into the object
     */
    private static Weapon getWeapon() throws IOException, InterruptedException {

        // Hold an array list full of the weapon names
        ArrayList<String> weaponNameBuffer = new ArrayList<String>();

        // Print out and save the names of the simple weapons
        json.sendGetRequest(characterUrl + "/api/equipment-categories/simple-weapons");
        printAndSaveApiData(weaponNameBuffer, -1);

        // Print out and save the names of the martial weapons
        json.sendGetRequest(characterUrl + "/api/equipment-categories/martial-weapons");
        printAndSaveApiData(weaponNameBuffer, 21); // Max of 21 to skip the net (idk why it's in there)

        // Get the array form of the weapon name buffer
        String[] weaponNames = weaponNameBuffer.toArray(new String[weaponNameBuffer.size()]);

        // Get the user's weapon choice
        String userWeapon = Simple.getStringInput("What weapon would you like to use: ", weaponNames, "Invalid Input", false);
        

        // Send the request based and formatted on the user's weapon
        json.sendGetRequest(characterUrl + "/api/equipment/" + userWeapon.toLowerCase().replaceAll(",", "").replaceAll(" ", "-"));

        // Create and return the weapon
        return new Weapon(
            json.get("name").getAsString(),
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(),
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]),

                // Sometimes it is just a set 1 or 2 damage, so this acounts for that
                json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d").length == 1 ? 1 : Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1])
            }
        );
    }

    /*
     * Get the final found armor
     * 
     * @return  the armor with the data from the api put into the object
     */
    private static Armor getArmor() throws IOException, InterruptedException {
        // Hold an array list full of the armor names
        ArrayList<String> armorNameBuffer = new ArrayList<String>();

        // Print out and save the names of the armors
        json.sendGetRequest(characterUrl + "/api/equipment-categories/armor");
        printAndSaveApiData(armorNameBuffer, 12);

        // Add a no armor option to the list and print out "No Armor" to the screen with the rest
        armorNameBuffer.add("No Armor");
        Simple.println("No Armor");

        // Get the array form of the armor name buffer
        String[] armorNames = armorNameBuffer.toArray(new String[armorNameBuffer.size()]);

        // Get the user's armor choice
        Simple.space();
        String userArmor = Simple.getStringInput("What armor would you like to use: ", armorNames, "Invalid Input", false);

        // If the user armor is no armor, return that
        if (userArmor.toLowerCase().equals("no armor")) {

            // If the user did then return the no armor object
            return new Armor(
                "No Armor",
                10,
                true,
                Integer.MAX_VALUE
            );
        }
        
        // Else, send the request based and formatted on the user's armor
        json.sendGetRequest(characterUrl + "/api/equipment/" + userArmor.toLowerCase().replaceAll(" ", "-"));

        // Create and return the armor
        return new Armor(
            json.get("name").getAsString(),
            json.get("armor_class").getAsJsonObject().get("base").getAsInt(),
            json.get("str_minimum").getAsInt(),
            json.get("armor_class").getAsJsonObject().get("dex_bonus").getAsBoolean(),
            json.get("armor_class").getAsJsonObject().get("max_bonus") == null ? Integer.MAX_VALUE : json.get("armor_class").getAsJsonObject().get("max_bonus").getAsInt()
        );
    }

    /*
     * Prints all the data from the already fetched json and saves any data into a buffer until the maxItterations
     * 
     * @param   buffer          buffer to add data to
     * @param   maxItteration   the max amount of itterations to edit too (-1 if all of them)
     */
    private static void printAndSaveApiData(ArrayList<String> buffer, int maxItteration) throws IOException, InterruptedException {
        
        // If the maxItterations is equal to -1 (which is the null version of this) then set it to the full json array size
        if (maxItteration == -1) {maxItteration = json.get("equipment").getAsJsonArray().asList().size();}

        // Loop for as many times as the maxItterations
        for (int i = 0; i < maxItteration; i++) {

            // Get the item in the array
            JsonElement item = json.get("equipment").getAsJsonArray().asList().get(i);

            // Print out the json object
            Simple.println(item.getAsJsonObject().get("name").getAsString());

            // Add it to the buffer
            buffer.add(item.getAsJsonObject().get("name").getAsString());
        }
    }
}
