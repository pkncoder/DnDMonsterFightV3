package pkner.pkncoder;

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

        Enemy frank = new Enemy(
            "Frank",
            47,
            new int[] {13, 1},
            new int[] {14, 2},
            new int[] {18, 4},
            new int[] {8, -1},
            new int[] {20, 5},
            new int[] {10, 0},
            new Armor("No Armor", 10, true, Integer.MAX_VALUE),
            new Weapon("Gun.", 10, new int[] {10, 12})
        );

        Party jhons = new Party("Jhons", "PLAYERS");
        jhons.addPartyMember(jhon);
        Party franks = new Party("Franks", "ENEMIES");
        franks.addPartyMember(frank);
        
        PartyColection players = new PartyColection("players");
        players.addParty(jhons);
        PartyColection enemies = new PartyColection("enemies");
        enemies.addParty(franks);

        Encounter encounter = new Encounter(players, enemies);

        encounter.printAttackTable();
    }

}
