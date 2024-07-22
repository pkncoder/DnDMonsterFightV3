package pkner.pkncoder.ApiTests;

import java.io.IOException;

import com.google.gson.JsonElement;

import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Api;

public class mainTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        Api json = new Api();

        json.sendGetRequest("https://www.dnd5eapi.co/api/equipment/longsword");

        Weapon weapon = new Weapon(json.get("name").getAsString(), 0, null);

        System.out.println(weapon);
    }
}