package pkner.pkncoder;

import java.io.IOException;

import pkner.pkncoder.Classes.Encounter;
import pkner.pkncoder.CustomMethods.Simple;
import pkner.pkncoder.GetterMethods.GetEncounter;

public class DnDMonsterFight {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Clear the terminal for the welcome message
        Simple.clearTerminal();
        
        // Print our welcome message
        printWelcomeMessage();

        // Wait until the user is ready to continue
        Simple.space();
        Simple.getStringInput("Press ENTER when ready to continue: ");

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

    private static void printWelcomeMessage() {
        Simple.println("""
                       Hello, welcome to DnD Monster Fight (Version 3)
                       With this you can create parties that will hold Players and Enemies to finally brawl out in a Player vs. Enemy battle!
                       
                       Here are some terms that you may not know how to use or what they are:
                       \tHP - Hit Points, aka. the amount of health.
                       \tAC - Armor Class, aka. how hard someone is to hit.
                       \tClass - The proffession of the Player that decides what Weapons and Armors the Player can use.
                       \tLevel - A value that changes your Hit Points along with your constitution.
                       
                       In DnD there are 6 main stats. All 6 will be described in relation to a tomato:
                       \tStrength - How far you can throw the tomato.
                       \tDexterity - How fast and percise you can dice the tomato.
                       \tConstitution - How well you fair against a moldy tomato (ew).
                       \tWisdom - How well you know how to use the tomato in  a dish.
                       \tIntelligence - How many fun facts you know about a tomato.
                       \tCharisma - How well you can sell the tomato.
                       
                       
                       There are a few terms related to things that you will create within the simulation. Here they are:
                       \tCharacter - A mold for players and enemies without a level, class, weapon, or armor.
                       \tParty - A collection of Players or Enemies along in a team or a group.
                       \tPlayer - A character with a level, class, weapon, and armor.
                       \tEnemy - A character with a weapon and armor without the normal restrictions of a class.
                       \tEncounter - A collection of parties of either player or enemies
                       
                       
                       Finally, the creation process (creating parties, players, and enemies) is controlled with a set of 3 actions and 3 subjects.
                       In every command, you will use one of the actions paired with one subject:
                       \tActions (Things to change or view):
                       \t\tCreate - Add one of the subjects to the final encounter.
                       \t\tRemove - Remove one of the subjects.
                       \t\tView - Look at one, or multiple of the subjects.
                       
                       \tSubjects (Things that the actions change or view, already explained above):
                       \t\tPlayer
                       \t\tEnemy
                       \t\tParty
                       """);
    }
}