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

        // Get the user's items
        ClassType userClass = getUserClass();
        Weapon weapon = getWeapon(userClass);
        Armor armor = getArmor(userClass, abilities[0][0]);

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

    // The weapon
    public static Weapon getWeapon(ClassType userClass)
    {   
        // Clear terminal on new menu
        Simple.clearTerminal();

        // Hold a variable with our array
        Weapon[][] weaponMainArr = (Weapon[][]) (userClass.getUserHashmap()[2]);

        // Loop over the possible weapons for the user's class, so each array of weapons
        for (Weapon[] weaponSubArr: weaponMainArr) {
            // Loop over each weapon
            for (Weapon weapon: weaponSubArr) {

                // Print every weapon that the class can use
                Simple.println(weapon);
            }
        }

        Simple.space();

        // Forever loop until we get the result we want and return
        while (true)  {
            // Ask the user on what weapon they would like to choose
            String userMaybeWeapon = Simple.getStringInput("Weapon (ex. Dagger): ", "[a-zA-Z ]+");

            // Test to see if the result is valid
            // Loop every weapon 
            for (Weapon[] weaponSubArr: weaponMainArr) {

                // Loop over each weapon
                for (Weapon weapon: weaponSubArr) {

                    // Test to see if it is a valid weapon that the user put in
                    if (weapon.toString().toUpperCase().equals(userMaybeWeapon.toUpperCase()))
                    {
                        // If it is then return that weapon
                        return weapon;
                    }
                }
            }

            // If the if statement doesn't trigger than put 'invalid input' as the thing the user put in is invalid
            Simple.println("Invalid Input");
        }
    }

    // The armor
    public static Armor getArmor(ClassType userClass, int strength)
    {
        // Clear terminal on new menu
        Simple.clearTerminal();

        // Hold a variable with our array
        Armor[][] armorMainArr = (Armor[][]) (userClass.getUserHashmap()[1]);

        // Loop over the possible armors for the user's class, so each array of armors
        for (Armor[] armorSubArr: armorMainArr) {
            // Loop over each armor
            for (Armor armor: armorSubArr) {

                // Print every armor that the class can use
                Simple.println(armor);
            }
        }

        Simple.space();

        // Forever loop until we get the result we want and return
        while (true) {
            // Ask the user on what armor they would like to choose
            String userMaybeArmor = Simple.getStringInput("Armor (ex. Hide): ", "[a-zA-Z ]+");

            // Test to see if the result is valid
            // Loop every armor 
            for (Armor[] armorSubArr: armorMainArr) {

                // Loop over each armor
                for (Armor armor: armorSubArr) {

                    // Test to see if it is a valid armor that the user put in
                    if (armor.toString().toUpperCase().equals(userMaybeArmor.toUpperCase())) {
                        
                        // If it is then return that armor
                        return armor;
                    }
                }
            }

            // If the if statement doesn't trigger than put 'invalid input' as the thing the user put in is invalid
            Simple.println("Invalid Input");
        }
    }
}