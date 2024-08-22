package pkner.pkncoder.Tests;

import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.Encounter;
import pkner.pkncoder.Classes.Enemy;
import pkner.pkncoder.Classes.Party;
import pkner.pkncoder.Classes.PartyColection;
import pkner.pkncoder.Classes.PlayerCharacter;
import pkner.pkncoder.Classes.PlayerClass;
import pkner.pkncoder.Classes.Weapon;

public class EncounterTest {
    
    public static void main(String[] args) {

        // Player - jhon
        PlayerCharacter jhon = new PlayerCharacter(
            "Jhon",
            new int[] {13, 1},
            new int[] {14, 2},
            new int[] {18, 4},
            new int[] {8, -1},
            new int[] {20, 5},
            new int[] {10, 0},
            3,
            new PlayerClass("barbarian", 12),
            new Weapon("Excaliber", 300000, new int[] {3, 20}),
            new Armor("Plank", 11, false, 0)
        );

        // Enemy - Frank
        Enemy frank = new Enemy(
            "Frank",
            10,
            new int[] {13, 1},
            new int[] {14, 2},
            new int[] {18, 4},
            new int[] {8, -1},
            new int[] {20, 5},
            new int[] {10, 0},
            new Weapon("Gun.", 10, new int[] {10, 12}),
            new Armor("No Armor", 10, true, Integer.MAX_VALUE)
        );

        // Enemy - Frank 2 (because everyone needs a sequal)
        Enemy frank2 = new Enemy(
            "Frank2",
            78,
            new int[] {13, 1},
            new int[] {14, 2},
            new int[] {18, 4},
            new int[] {8, -1},
            new int[] {20, 5},
            new int[] {10, 0},
            new Weapon("Gun.", 10, new int[] {10, 12}),
            new Armor("No Armor", 10, true, Integer.MAX_VALUE)
        );

        // Parties
        // Players (jhon)
        Party jhons = new Party("jhon", "PLAYERS");
        jhons.addPartyMember(jhon);

        // Parties
        // Enemies
        Party franks = new Party("because everyone needs a sequal", "ENEMIES");
        franks.addPartyMember(frank);
        franks.addPartyMember(frank2);
        
        // Party collection
        // Players (john)
        PartyColection players = new PartyColection("players");
        players.addParty(jhons);

        // Party collection
        // Enemies
        PartyColection enemies = new PartyColection("enemies");
        enemies.addParty(franks);

        // Create the encounter
        Encounter encounter = new Encounter(players, enemies);

        // While win is still false
        while (!encounter.getWin()) {

            // Take a turn
            encounter.takeTurn();
        }

        // Print the win statements at the end
        encounter.printWinStatements();
    }

}
