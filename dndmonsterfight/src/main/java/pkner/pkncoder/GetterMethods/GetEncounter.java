package pkner.pkncoder.GetterMethods;

import java.io.IOException;

import pkner.pkncoder.Classes.Encounter;
import pkner.pkncoder.Classes.Enemy;
import pkner.pkncoder.Classes.Party;
import pkner.pkncoder.Classes.PartyColection;
import pkner.pkncoder.Classes.PlayerCharacter;
import pkner.pkncoder.CustomMethods.Simple;

public class GetEncounter 
{
    // Hold our two party types in party colectoions
    private static PartyColection playerParties = new PartyColection("Players");
    private static PartyColection enemyParties = new PartyColection("Enemies");

    /*
     * The main method that controlls all of the player / enemy / party creation process
     * 
     * @return  the final put together encounter
     */
    public static Encounter getEncounter() throws IOException, InterruptedException 
    {
        /*
         * Gameplan:
         * 
         * Ask the Command
         * Check the Command
         * Decifer the Command
         * Enact the Command
         * Loop Again until done
         * 
         * Return the Encounter
         * 
        */
        
        // Create our allowed words
        // First we need to define our actions and subjects
        String[] actions = {"create", "remove", "view"};
        String[] subjects = {"player", "enemy", "party"};

        // Then we need to get our allowed words
        String[] allowedWords = GetHelperMethods.getAllowedCommands(actions, subjects);

        // Hold a boolean stating if we are done or not
        boolean done = false;

        // Loop until the the done variable is set to true
        // This outer while loop is used after a command is enacted
        while (!done) {

            // Clear the terminal
            Simple.clearTerminal();

            // Print out our 'allowed words' aka commands at the start of the loop
            Simple.printArray(allowedWords);
            Simple.space();

            // We have another loop so we can type 'continue' to not wipe the terminal and 'break' to check for a win and wipe the terminal
            // Have the same condition as above so it isn't just a while true loop (I don't like those)
            while (!done) {

                // Get our command
                String command = Simple.getStringInput("Command: ", allowedWords.clone(), "Invalid Input", false);

                // Switch our command over every possible case
                switch (command) {

                    // Done
                    case "DONE":

                        // Save a varaible for checking if there are enough bases or not
                        int totalBases = playerParties.getNumBases() + enemyParties.getNumBases();

                        // Check to see if there are enough bases (2 or more)
                        if (totalBases < 2) {
                            // If there aren't, then print an error message and continue
                            Simple.println("There must be at least 2 players or enemies combined (You don't have enough).");
                        }

                        
                        // Save a variable for checking if there are enough parties or not
                        int totalParties = playerParties.getPartyList().size() + enemyParties.getPartyList().size();

                        // Check to see if there any parties
                        if (totalParties < 1) {
                            // If there aren't then print an error message and continue
                            Simple.println("There are not enough parties made.");
                            continue;
                        }

                        // Check if there is just one party
                        if (totalParties == 1) {
                            // If there is then check to see if the user is fine with having only one party
                            String choice = Simple.getStringInput("Are you ok with having only one party? Usually parties don't attack each other. If you choose yes then it will be a single base that wins.\n(y or n): ", "[yYnN]");

                            // Switch over the user's choice
                            switch (choice.toUpperCase()) {

                                // If the user chooses yes
                                case "Y":
                                    
                                    // Set done to true and break out
                                    done = true;
                                    break;

                                // If the user chooses no
                                case "N":
                                    // Break out to re-clear the terminal
                                    break;
                            
                                default:
                                    // Just in case
                                    Simple.println("An error has occoured");
                                    break;
                            }
                        }

                        // If none of those checks get set off then set done to true and break out
                        done = true;
                        break;


                    // Creates
                    // Player
                    case "CREATE PLAYER":

                        // At the start, check to see if we even have a party to add too
                        // If we don't then print that there are no parties made
                        if (playerParties.getPartyList().size() <= 0)
                        {
                            Simple.println("No parties made for players.");
                            continue;
                        }
                        
                        // As long as we have parties in the list, continue
                    
                        // Clear the terminal
                        Simple.clearTerminal();

                        // Get our player
                        PlayerCharacter newPlayer = GetPlayerCharacter.getPlayer();

                        // Clear the terminal for party selection
                        Simple.clearTerminal();

                        // Add this character to our character parties
                        playerParties.addBaseToParty(newPlayer);

                        break;

                    // Enemy
                    case "CREATE ENEMY":

                        // At the start, check to see if we even have a party to add too
                        // If we don't then print that there are no parties made
                        if (enemyParties.getPartyList().size() <= 0)
                        {
                            Simple.println("No parties made for enemies.");
                            continue;
                        }
                        
                        // As long as we have parties in the list, continue

                        // Clear the terminal
                        Simple.clearTerminal();
                        
                        // Get our player
                        Enemy newEnemy = GetEnemy.getEnemy();
                        
                        // Clear the terminal for party selection
                        Simple.clearTerminal();

                        // Add this enemy to our enemies parties
                        enemyParties.addBaseToParty(newEnemy);

                        break;
                    
                    // Party
                    case "CREATE PARTY":

                        // Wipe terminal
                        Simple.clearTerminal();

                        // Gain our party
                        Party newParty = getParty();

                        // Add it to the corresponding party type
                        findPartyPlacement(newParty);

                        break;


                    // Removes
                    // Player
                    case "REMOVE PLAYER":
                        
                        // At the start, check to see if we even have a party to add too
                        // If we don't then print that there are no parties made
                        if (playerParties.getNumBases() <= 0)
                        {
                            Simple.println("No players made.");
                            continue;
                        }

                        // As long as we have parties in the list, continue
                    
                        // Clear the terminal
                        Simple.clearTerminal();

                        // Print out all of the info the user needs
                        playerParties.printFullParties();

                        // Then ask what party the base is from
                        String playerRemovingParty = Simple.getStringInput("What party is the character from: ", playerParties.getPartyNames(), "Invalid Input", true);

                        // Then ask what base's name is
                        String removedPlayer = Simple.getStringInput("What character are you removing: ", playerParties.getBaseNames(), "Invalid Input", true);

                        // Remove it
                        playerParties.removeBase(playerRemovingParty, removedPlayer);

                        break;
                    
                    // Enemy
                    case "REMOVE ENEMY":

                        // At the start, check to see if we even have a party to add too
                        // If we don't then print that there are no parties made
                        if (enemyParties.getNumBases() <= 0)
                        {
                            Simple.println("No enemies made.");
                            continue;
                        }
                        
                        // As long as we have parties in the list, continue
                    
                        // Clear the terminal
                        Simple.clearTerminal();

                        // Print out all of the info the user needs
                        enemyParties.printFullParties();

                        // Then ask what party the base is from
                        String enemyRemovingParty = Simple.getStringInput("What party is the enemy from: ", enemyParties.getPartyNames(), "Invalid Input", true);

                        // Then ask what base's name is
                        String removedEnemy = Simple.getStringInput("What enemy are you removing: ", enemyParties.getBaseNames(), "Invalid Input", true);

                        // Remove it
                        enemyParties.removeBase(enemyRemovingParty, removedEnemy);

                        break;

                    // Party
                    case "REMOVE PARTY":

                        // Test to see if we can even remove any parties
                        if (playerParties.getPartyList().size() == 0 && enemyParties.getPartyList().size() == 0) {
                            // Print that there isn't enouph and go back to the start of the loop
                            Simple.println("There aren't any parties made yet.");
                            continue;
                        }

                        // Clear terminal
                        Simple.clearTerminal();

                        // Init a new string to hold the case
                        String partyTypeSelectionRemove;

                        // Test to see if there is a choice in the matter on where to remove a party from
                        if (playerParties.getPartyList().size() == 0) {partyTypeSelectionRemove = "ENEMY"; }
                        else if (enemyParties.getPartyList().size() == 0) {partyTypeSelectionRemove = "Player"; }
                        else {partyTypeSelectionRemove = Simple.getStringInput("Would you like to remove a Player, or Enemy party: ", new String[] {"player", "enemy"}, "Invalid Input", false);}

                        // Switch either character or enemy
                        switch (partyTypeSelectionRemove) {

                            case "Player":

                                // Print out all of the posible parties
                                Simple.printArray(playerParties.getPartyNames());

                                // Ask for which one
                                String removedPlayerParty = Simple.getStringInput("What party would you like to remove: ", playerParties.getPartyNames(), "Invalid Input", true);

                                // Remove it
                                playerParties.removeParty(removedPlayerParty);
                                
                                
                                break;

                            case "ENEMY":

                                // Print out all of the posible parties
                                Simple.printArray(enemyParties.getPartyNames());

                                // Ask for which one
                                String removedEnemyParty = Simple.getStringInput("What party would you like to remove: ", playerParties.getPartyNames(), "Invalid Input", true);

                                // Remove it
                                enemyParties.removeParty(removedEnemyParty);

                                break;
                        }

                        break;


                    // Views
                    // Player
                    case "VIEW PLAYER":

                        // At the start, check to see if we even have any players made
                        // Test to see if the number of bases is less than or equal to 0 (if less then than there should be an error)
                        if (playerParties.getNumBases() <= 0)
                        {
                            Simple.println("No players made.");
                            continue;
                        }

                        // Clear terminal
                        Simple.clearTerminal();

                        // Print out all of the choices of parties + players
                        playerParties.printFullParties();

                        // Ask what player
                        String viewedPlayer = Simple.getStringInput("What player are you viewing: ", playerParties.getBaseNames(), "Invalid Input", true);

                        // Clear the terminal
                        Simple.clearTerminal();

                        // Finally get that player and print it out
                        Simple.println(playerParties.findBaseByName(viewedPlayer));

                        // Wait until the user is done viewing
                        Simple.getStringInput("Press ENTER to continue: ");

                        break;

                    // Enemy
                    case "VIEW ENEMY":

                        // At the start, check to see if we even have any enemies made
                        // Test to see if the number of bases is less than or equal to 0 (if less then than there should be an error)
                        if (enemyParties.getNumBases() <= 0)
                        {
                            Simple.println("No enemies made.");
                            continue;
                        }

                        // Clear terminal
                        Simple.clearTerminal();

                        // Print out all of the choices of parties + enemies
                        enemyParties.printFullParties();

                        // Ask what enemy
                        String viewedEnemy = Simple.getStringInput("What enemy are you viewing: ", enemyParties.getBaseNames(), "Invalid Input", true);

                        // Clear the terminal
                        Simple.clearTerminal();
                        
                        // Finally get that enemy and print it out
                        Simple.println(enemyParties.findBaseByName(viewedEnemy));

                        // Wait until the user is done viewing
                        Simple.getStringInput("Press ENTER to continue: ");

                        break;

                    // Party
                    case "VIEW PARTY":

                        // Check to make sure that a party has been made in the first place
                        if (playerParties.getPartyList().size() == 0 && enemyParties.getPartyList().size() == 0) {
                            Simple.println("No parties created.");
                            continue;
                        }

                        // Clear terminal
                        Simple.clearTerminal();

                        // Test to see if there is a choice in the matter on where to view a party from
                        if (playerParties.getPartyList().size() > 0) {
                            Simple.println("-- Player Parties --");
                            Simple.printArray(playerParties.getPartyNames());

                            Simple.println("");
                        }

                        if (enemyParties.getPartyList().size() > 0) {
                            Simple.println("-- Enemy Parties --");
                            Simple.printArray(enemyParties.getPartyNames());
                            Simple.println("");
                        }

                        // Let them choose when they are done viewing
                        Simple.getStringInput("Press ENTER when done viewing: ");

                        break;

                    // The default, if an error somehow occoured and none of those weny by
                    default:

                        // Print that an error has occoured and return
                        Simple.println("An error has occoured.");
                        break;
                }

                break;
            }
        }
        
        // Return our final encounter
        return new Encounter(playerParties, enemyParties);
    }

    /*
     * Finds the placement of our party and adds it to that collection
     * 
     * @param   party   the party to test and add too
     */
    private static void findPartyPlacement(Party party) {

        // Switch over the type of party to see where it will be placed
        switch (party.getType()) {

            // Character party
            case "PLAYERS":

                // Add our new party to the players
                playerParties.addParty(party);
                break;

            // Enemy party
            case "ENEMIES":
                
                // Add our new party to the enemies
                enemyParties.addParty(party);
                break;
        
            default:
                break;
        }
    }

    /*
     * Gets the final made party
     * 
     * @return  the final party with name and type
     */
    public static Party getParty() {

        // Save a variable that will exit the loop when set
        // This is used for the name of the party
        String name = "";

        while (name.equals("")) {

            // Get the name of the party
            // Allow any a-z, A-Z, 0-9, or _ character (basically not letting nothing get through)
            String maybeName = Simple.getStringInput("Party Name: ", "[\\w+]+");

            // Check to see if the name is already taken
            if (playerParties.findPartyByName(maybeName) != null || enemyParties.findPartyByName(maybeName) != null) {

                // If it is print out an error message and continue to re-start the loop
                Simple.println("Name Already Taken.");
                continue;
            }

            // If the if statement doesn't trigger, then set name to something other than "" to end the loop and continue
            name = maybeName;
        }

        // Get the type of the party (character or enemy)
        String type = Simple.getStringInput("Will this party have Players or Enemies: ", new String[] {"players", "enemies"}, "Invalid Input", false);

        // Now return our party
        return new Party(name, type);
    }
}