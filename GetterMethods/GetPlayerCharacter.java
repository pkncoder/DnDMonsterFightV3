package GetterMethods;
import Classes.Armor;
import Classes.ClassType;
import Classes.PlayerCharacter;
import Classes.Weapon;
import Classes.ClassType.CLASSES;
import CustomMethods.Simple;

public class GetPlayerCharacter {

    // Used to create the PlayerCharacter object
    public static PlayerCharacter getPlayer() {

        // User name
        String name = Simple.getStringInput("Name: ");

        // The abilities
        int[][] abilities = GetHelperMethods.getAbilityScores();

        // User level
        int level = Simple.getIntInput("Level: ", "[1-9][0-9]*");

        // Initialize all hashmaps
        ClassType.initHashmap();

        // Get the user's class
        ClassType userClass = getUserClass();

        // Get the weapon
        Simple.clearTerminal();
        Weapon weapon = GetHelperMethods.getWeapon((Weapon[][]) userClass.getUserHashmap()[2]);

        // Get the armor
        Simple.clearTerminal();
        Armor armor = GetHelperMethods.getArmor(abilities[0][0], (Armor[][]) userClass.getUserHashmap()[1]);

        // Return the character
        return new PlayerCharacter(name, abilities[0], abilities[1], abilities[2], abilities[3], abilities[4], abilities[5], level, userClass, weapon, armor);
    }

    // Methods for getting the items for the user
    // The class
    public static ClassType getUserClass()
    {
        // Clear the terminal
        Simple.clearTerminal();

        // Loop each class in the CLASSES enum
        for (CLASSES classType: CLASSES.values())
        {
            // Print out each one
            Simple.println(classType);
        }

        Simple.space();

        // Loop until the code returns
        while (true) {

            // Ask the user for the class choice
            String maybeUserClass = Simple.getStringInput("Class (ex. bard): ", "[a-zA-Z]+");

            // Loop each class to see if it fits the description wanted
            for (CLASSES classType: CLASSES.values()) {
            
                // Test to see if the class type is equal to the user-inputed class (if it's valid)
                if (classType.toString().toUpperCase().equals(maybeUserClass.toUpperCase())) {
                    // Return our new classtype
                    return new ClassType(classType);
                }
            }

            // If the if statement didn't go crazy then that means the user put an invalid stuff
            Simple.println("Invalid Input");
        }
    }
}