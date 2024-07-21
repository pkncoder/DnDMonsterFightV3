package pkner.pkncoder.GetterMethods;


import pkner.pkncoder.Classes.Armor;
import pkner.pkncoder.Classes.Party;
import pkner.pkncoder.Classes.Weapon;
import pkner.pkncoder.CustomMethods.Simple;

public class GetHelperMethods {

    private static String positiveMoreThanOneRegex = "[1-9][0-9]*";

    public static int[][] getAbilityScores() {
        
        // Enemy primary stats
        int strengthScore = Simple.getIntInput("Strength: ", positiveMoreThanOneRegex);
        int dexterityScore = Simple.getIntInput("Dexterity: ", positiveMoreThanOneRegex);
        int constitutionScore = Simple.getIntInput("Constitution: ", positiveMoreThanOneRegex);
        int wisdomScore = Simple.getIntInput("Wisdom: ", positiveMoreThanOneRegex);
        int intelligenceScore = Simple.getIntInput("Intellegence: ", positiveMoreThanOneRegex);
        int charismaScore = Simple.getIntInput("Charisma: ", positiveMoreThanOneRegex);

        // Split up the primary stats into [stat] [mod]
        int[] strength = {strengthScore, calculateAbilityMod(strengthScore)};
        int[] dexterity = {dexterityScore, calculateAbilityMod(dexterityScore)};
        int[] constitution = {constitutionScore, calculateAbilityMod(constitutionScore)};
        int[] wisdom = {wisdomScore, calculateAbilityMod(wisdomScore)};
        int[] intelligence = {intelligenceScore, calculateAbilityMod(intelligenceScore)};
        int[] charisma = {charismaScore, calculateAbilityMod(charismaScore)};

        // Return a 2d array containing all of the score info
        return new int[][] {strength, dexterity, constitution, wisdom, intelligence, charisma};
    }

    // Used to calculate ability score modifiers
    public static int calculateAbilityMod(int score) {
        // For every 2 after 10, you get +1 to your ability mod (ex. 10 is +0 and 12 is +1)
        return ((score - 10) / 2);
    }

    // Used to get the weapon with no spesific class binds
    public static Weapon getWeapon(Weapon[][] allowedWeapons) {

        // Loop every weapon and print it out
        // Each sub-arr
        for (Weapon[] weaponLevel: allowedWeapons)
        {
            // Each weapon
            for (Weapon weapon: weaponLevel)
            {
                // Print out each weapon's name
                Simple.println(weapon.getName());
            }
        }

        // Ask the user their perfered weapon
        // Loop forever since we return during loop
        while (true) {
            
            // Ask for the input and set it to uppercase so we don't have to worry about any case sensitiveness
            String userInput = Simple.getStringInput("Weapon Choice: ", "[a-zA-Z ]+").toUpperCase();

            // Loop every weapon to see if the name matches
            // Each sub-arr
            for (Weapon[] weaponLevel: allowedWeapons)
            {
                // Each weapon
                for (Weapon weapon: weaponLevel)
                {
                    // Check if the name matches (to upper so no case sensitive)
                    if (weapon.getName().toUpperCase().equals(userInput))
                    {
                        return weapon;
                    }
                }
            }

            // Print out invalid input if the if statement doesn't go through
            Simple.println("Invalid Input");
        }
    }

    // Used to get the armor with no spesific class binds
    public static Armor getArmor(int strengthScore, Armor[][] allowedArmors) {

        // Loop every armor and print it out
        // Each sub-arr
        for (Armor[] armorLevel: allowedArmors)
        {
            // Each armor
            for (Armor armor: armorLevel)
            {
                // Print out each armor's name
                Simple.println(armor.getName());
            }
        }

        // Ask the user their perfered armor
        // Loop forever since we return during loop
        while (true) {
            
            // Ask for the input and set it to uppercase so we don't have to worry about any case sensitiveness
            String userInput = Simple.getStringInput("Armor Choice: ", "[a-zA-Z ]+").toUpperCase();

            // Loop every armor to see if the name matches
            // Each sub-arr
            for (Armor[] armorLevel: allowedArmors)
            {
                // Each armor
                for (Armor armor: armorLevel)
                {
                    // Check if the name matches (to upper so no case sensitive)
                    if (armor.getName().toUpperCase().equals(userInput))
                    {
                        return armor;
                    }
                }
            }

            // Print out invalid input if the if statement doesn't go through
            Simple.println("Invalid Input");
        }
    }

    public static Party getParty() {

        // Get the name of the party
        String name = Simple.getStringInput("Party Name: ");

        // Get the type of the party (character or enemy)
        String type = Simple.getStringInput("Will this party have Players or Enemies: ", new String[] {"players", "enemies"}, "Invalid Input", false);

        // Now return our party
        return new Party(name, type);
    }

    // Validate command, used to check the command for correctness
    public static boolean validateCommand(String[] splitCommand)
    {
        return true;
    }

    // Used to set the allowed words
    public static String[] getAllowedWords(String[] actions, String[] subjects)
    {
        // Next, initialize allowed words
        String[] allowedWords = new String[actions.length * subjects.length + 1];

        // Hold a variable called added so we can index the allowed words correctly
        int added = 0;

        // Finally, loop over everything and start adding to allowed words as needed
        for (int i = 0; i < actions.length; i++)
        {
            for (int k = 0; k < subjects.length; k++)
            {
                // Add to allowed words at added, the currect action we are at, and the current subject we are at
                allowedWords[added] = actions[i] + " " + subjects[k];

                // Add one to added
                added++;
            }
        }

        // Once all the commands have entered, enter in a finished word too at added (because it incrimented after the last itteration again)
        allowedWords[added] = "done";

        // At the end, return allowed words
        return allowedWords;
    }
}
