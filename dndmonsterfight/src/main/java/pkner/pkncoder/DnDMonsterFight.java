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
        Simple.println(
            "Hello, welcome to DnD Monster Fight (Version 3)\n" + 
            "With this you can create parties that will hold Players and Enemies to finally brawl out in a Player vs. Enemy battle!\n\n" +
            
            "First, here are some terms to note if you don't know them.\n" +
            "\tHP - Hit Points, aka. the amount of health.\n" +
            "\tAC - Armor Class, aka. how hard someone is to hit.\n" +
            "\tClass - The proffession of the Player that decides what Weapons and Armors the Player can use.\n" +
            "\tLevel - A value that changes your Hit Points along with your constitution.\n\n" +

            "Strength - \n" + 
            "Dexterity - \n" + 
            "Constitution - \n" + 
            "Wisdom - \n" + 
            "Intelligence - \n" + 
            "Charisma - \n" + 
            "\n\n" +

            "\tCharacter - A mold for players and enemies without a level, class, weapon, or armor.\n" +
            "\tParty - A collection of Players or Enemies along in a team or a group.\n" +
            "\tPlayer - A character with a level, class, weapon, and armor.\n" +
            "\tEnemy - A character with a weapon and armor without the normal restrictions of a class.\n"
        );
    }
}