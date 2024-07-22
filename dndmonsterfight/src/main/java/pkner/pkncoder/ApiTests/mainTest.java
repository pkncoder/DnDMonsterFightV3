package pkner.pkncoder.ApiTests;

import java.io.IOException;

import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Api;

public class mainTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        Api json = new Api();

        json.sendGetRequest("https://www.dnd5eapi.co/api/equipment/longsword");

        Weapon weapon = new Weapon(
            json.get("name").getAsString(), 
            json.get("cost").getAsJsonObject().get("quantity").getAsInt(), 
            new int[] {
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[0]),
                Integer.parseInt(json.get("damage").getAsJsonObject().get("damage_dice").getAsString().split("d")[1])
            }
        );

        System.out.println(weapon);
    }
}