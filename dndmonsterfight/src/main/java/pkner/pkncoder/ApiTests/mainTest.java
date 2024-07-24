package pkner.pkncoder.ApiTests;

import java.io.IOException;

import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Api;

import pkner.pkncoder.CustomMethods.Simple;

public class mainTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Clear the terminal
        Simple.clearTerminal();

        // Create our api accsess point
        Api json = new Api();

        // Send our get request for a longsword and print that api call one is finished after
        json.sendGetRequest("https://www.dnd5eapi.co/api/equipment/longsword");
        Simple.println("Api call one done.");

        // Create our weapon
        Weapon weapon = new Weapon(
            json.get("name").getAsString(), // {name: ""}
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(),  // {cost:{quantity: ""}}
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]), // {damage:{damage_dice: ""}}
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1]) // {damage:{damage_dice: ""}}
            }
        );

        // Send our get request for studded leather armor and print that the api call two is finished after
        json.sendGetRequest("https://www.dnd5eapi.co/api/equipment/hide-armor");
        Simple.println("Api call two done.\n");

        // Create our armor
        Armor armor = new Armor(
            json.get("name").getAsString(), // {name: ""}
            json.get("armor_class").getAsJsonObject().get("base").getAsInt(), // {armor_class: {base: ""}}
            json.get("str_minimum").getAsInt(), // {str_minimum: ""}
            json.get("armor_class").getAsJsonObject().get("dex_bonus").getAsBoolean(), // {armor_class: {dex_bonus: ""}}
            json.get("armor_class").getAsJsonObject().get("max_bonus").isJsonNull() ? Integer.MAX_VALUE : json.get("armor_class").getAsJsonObject().get("max_bonus").getAsInt()
        );

        Simple.println(weapon);
        Simple.println("--------------");
        Simple.println(armor);
    }
}