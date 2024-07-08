// Package
package GetterMethods;

// Other classes used in enemy
import Classes.Armor;
import Classes.Enemy;
import Classes.Weapon;

// The usual use case methods
import CustomMethods.Simple;

public class GetEnemy {

    // The main method
    public static Enemy getEnemy() {
        /*
         * Gameplan:
         * Get the name
         * Get the hp
         * Get the scores / stats / abilities
         * Get the weapon
         * Get the armor
        */

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
        Weapon weapon = GetHelperMethods.getWeapon();

        // Clear
        Simple.clearTerminal();
        
        // Get our armor
        Armor armor = GetHelperMethods.getArmor(abilityScores[0][0]);

        // Return our final enemy
        return new Enemy(name, hp, abilityScores[0], abilityScores[1], abilityScores[2], abilityScores[3], abilityScores[4], abilityScores[5], armor, weapon);
    }
}
