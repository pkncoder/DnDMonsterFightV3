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

    private static String baseUrl = "https://www.dnd5eapi.co";

    public static void main(String[] args) throws IOException, InterruptedException {
        getEnemy();
    }

    // The main method
    public static Enemy getEnemy() throws IOException, InterruptedException {
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
        String name = Simple.getStringInput("Name: ");

        // Get the enemy's hp
        // The regex states that it can only be above (or equal to) 1
        int hp = Simple.getIntInput("Hit Points: ", "[1-9][0-9]*");

        // Get our scores
        int[][] abilityScores = GetHelperMethods.getAbilityScores();

        // Clear
        Simple.clearTerminal();

        // Get our weapon
        Weapon weapon = getWeapon();
        Simple.println(weapon);
        Simple.getStringInput("");

        // Clear
        Simple.clearTerminal();
        
        // Get our armor
        Armor armor = getArmor();

        // Return our final enemy
        return new Enemy(name, hp, abilityScores[0], abilityScores[1], abilityScores[2], abilityScores[3], abilityScores[4], abilityScores[5], armor, weapon);
    }

    private static Weapon getWeapon() throws IOException, InterruptedException {

        // Hold an array list full of the weapon names
        ArrayList<String> weaponNameBuffer = new ArrayList<String>();

        // Print out and save the names of the simple weapons
        json.sendGetRequest(baseUrl + "/api/equipment-categories/simple-weapons");
        printAndSaveApiData(weaponNameBuffer);

        // Print out and save the names of the martial weapons
        json.sendGetRequest(baseUrl + "/api/equipment-categories/martial-weapons");
        printAndSaveApiData(weaponNameBuffer);

        // Get the array form of the weapon name buffer
        String[] weaponNames = weaponNameBuffer.toArray(new String[weaponNameBuffer.size()]);

        // Get the user's weapon choice
        Simple.space();
        String userWeapon = Simple.getStringInput("What weapon would you like to use: ", weaponNames, "Invalid Input", false);
        

        // Send the request based and formatted on the user's weapon
        json.sendGetRequest(baseUrl + "/api/equipment/" + userWeapon.toLowerCase().replaceAll(" ", "-"));

        // Create and return the weapon
        return new Weapon(
            json.get("name").getAsString(), // {name: ""}
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(),  // {cost:{quantity: ""}}
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]), // {damage:{damage_dice: ""}}
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1]) // {damage:{damage_dice: ""}}
            }
        );
    }

    private static Armor getArmor() {
        return null;
    }

    private static void printAndSaveApiData(ArrayList<String> buffer) throws IOException, InterruptedException {
        
        for (JsonElement data: json.get("equipment").getAsJsonArray().asList()) {
            Simple.println(data.getAsJsonObject().get("name").getAsString());
            buffer.add(data.getAsJsonObject().get("name").getAsString());
        }
    }
}
