package pkner.pkncoder;

import java.io.IOException;

import pkner.pkncoder.Classes.Encounter;
import pkner.pkncoder.GetterMethods.GetEncounter;

public class DnDMonsterFight {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Get our encounter
        Encounter encounter = GetEncounter.getEncounter();

        // While win is still false
        while (!encounter.getWin()) {

            // Take a turn
            encounter.takeTurn();
        }

        // Print the win statements at the end
        encounter.printWinStatements();
    }
}