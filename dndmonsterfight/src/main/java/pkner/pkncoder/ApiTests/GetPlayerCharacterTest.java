package pkner.pkncoder.ApiTests;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException, InterruptedException {
        json = new Api(); // Initialize our API object

        // Get the players class
        Simple.clearTerminal();
        PlayerClass userClass = getPlayerClass();

        // Get the player's weapon
        getPlayerWeapon();
    }

    public static PlayerClass getPlayerClass() throws IOException, InterruptedException {
        Simple.printArray(classes); // Print the classes that can be chosen from
        Simple.space();

        // Get the user's chosen class
        String userClassChoice = Simple.getStringInput("What class would you like to choose: ", classes, baseUrl, false);

        // Send a request with the base url and the addition of /api/classes/ to filter for the classes and add on the user's chosen class (lower cased)
        json.sendGetRequest(baseUrl + "/api/classes/" + userClassChoice.toLowerCase());

        return new PlayerClass(json.get("name").getAsString(), json.get("hit_die").getAsInt());
    }

    public static void getPlayerWeapon() throws IOException, InterruptedException {
        Simple.clearTerminal();
        // Print out all the allowed weapons
        for (JsonElement proficiency: json.get("proficiencies").getAsJsonArray().asList()) {
            json.sendGetRequest(baseUrl + proficiency.getAsJsonObject().get("url").getAsString());

            json.sendGetRequest(baseUrl + json.get("reference").getAsJsonObject().get("url").getAsString());

            String[] splitUrl = json.get("url").getAsString().split("/");

            if (splitUrl[2].equals("equipment-categories")) {

                for (JsonElement equipment: json.get("equipment").getAsJsonArray().asList()) {
                    json.sendGetRequest(baseUrl + equipment.getAsJsonObject().get("url").getAsString());

                    if (json.get("armor_class") != null) {
                        Simple.println(json.get("name").getAsString() + " | ARMOR");
                    }

                    else {
                        
                        if (json.get("url").getAsString().split("/")[2].equals("magic-items")) {
                            Simple.println(json.get("name").getAsString() + " | ITEM");
                        }

                        else {
                            Simple.println(json.get("name").getAsString() + " | WEAPON");
                        }
                    }
                }
                continue;
            }

            else if (splitUrl[2].equals("ability-scores")) {
                Simple.println(json.get("name").getAsString() + " | ABILITY-SCORE");
                continue;
            }

            if (json.get("url").getAsString().split("/")[2].equals("magic-items")) {
                Simple.println(json.get("name").getAsString() + " | ITEM");
            }

            else {
                Simple.println(json.get("name").getAsString() + " | WEAPON");
            }
        }
    }

}
