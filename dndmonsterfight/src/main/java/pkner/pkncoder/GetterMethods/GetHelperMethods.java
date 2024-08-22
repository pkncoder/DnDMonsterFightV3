package pkner.pkncoder.GetterMethods;

import pkner.pkncoder.CustomMethods.Simple;

public class GetHelperMethods {

    // The regex used for most of the ability scores
    private static String positiveMoreThanOneRegex = "[1-9][0-9]*";

    /*
     * Gets the ability scores for bases
     * 
     * @return  all of the scores in one big 2D array
     */
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

    /*
     * Calculate the abilities score modifier
     * 
     * @param   score   the ability score
     * @return          the final ability mod
     */
    private static int calculateAbilityMod(int score) {
        // For every 2 after 10, you get +1 to your ability mod (ex. 10 is +0 and 12 is +1)
        return ((score - 10) / 2);
    }

    /*
     * Used to get the allowed commands
     * 
     * @param   actions     the possible actions
     * @param   subjects    the possible subjects
     * @return              A final arary list composing of all the different combos of actinos and subjects (action then subject only)
     */
    public static String[] getAllowedCommands(String[] actions, String[] subjects)
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
