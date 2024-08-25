package pkner.pkncoder.Classes;

import java.util.ArrayList;

import pkner.pkncoder.CustomMethods.Simple;

public class PartyColection {

    // Our array list full of parties
    private ArrayList<Party> partyList = new ArrayList<Party>();

    // The name of our party colection
    private String name;

    // How many current characters there are in all the parties combined
    private int totalCharacters;

    /* 
     * Class Constructor
     * 
     * @param   name    the name of the party collection (usually "Players" or "Enemies")
    */
    public PartyColection(String name)
    {
        // Set the party name
        this.name = name;

        // Set total characters to 0
        totalCharacters = 0;
    }

    /*
     * Finds a party from the list by the name of it
     * 
     * @param   partyName   The name of the party your trying to find
     * @return              Either the found party, or nothing
     */
    public Party findPartyByName(String partyName) {
        
        // Loop over every party
        for (Party party: partyList) {

            // If the name of the party equals the string given
            if (party.getName().toUpperCase().equals(partyName.toUpperCase())) {

                // Return it
                return party;
            }
        }

        // If the loop never returns, exit out and return nothing
        return null;
    }
    
    /*
     * Finds a character from one of the parties by the name of it
     * 
     * @param   characterName   The name of the party your trying to find
     * @return                  Either the found character, or nothing
     */
    public Character findCharacterByName(String characterName) {
        
        // Loop over every party
        for (Party party: partyList) {

            // Loop over every character
            for (Character character: party.getParty()) {
                
                // Test to see if the names match
                if (character.getName().toUpperCase().equals(characterName.toUpperCase())) {

                    // If they do, then return it
                    return character;
                }
                
            }
            
        }

        // If the loop never returns, exit out and return nothing
        return null;

    }

    // Re-calibrates the total characters
    public void findCurentNumCharacters() {

        // Reset total characters so we can re-count them
        totalCharacters = 0;

        // Loop over every party
        for (Party party: partyList) { // party
            // Add the parties array list of characters size to the total characters
            totalCharacters += party.getParty().size();
        }
    }

    /*
     * Add a new party to our list  
     * 
     * @param   newParty    the party to add to the collection
     */
    public void addParty(Party newParty) {

        // Add the party
        partyList.add(newParty);
    }

    /*
     * Adds a new character to the party chosen in the method
     * 
     * @param   newCharacter    the character that will be added
     */
    public void addCharacterToParty(Character newCharacter) {

        // Get our possible parties to add to
        String[] parties = getPartyNames();

        // Print them out
        Simple.printArray(parties);
        Simple.space();

        // Ask which one they would like to add to
        String chosenParty = Simple.getStringInput("What party is the character going into: ", parties, "Invalid Input", true);

        // Add that character based on the input
        findPartyByName(chosenParty).getParty().add(newCharacter);
    }

    /*
     * Removes a party from the list
     * 
     * @param   sadParty    the party to be removed (assuming that it exists, if it doesn't then this does nothing)
     */
    public void removeParty(Party sadParty) {
        // Remove it
        partyList.remove(sadParty);
    }

    /*
     * Removes a party from the list based on a string
     * 
     * @param   sadParty    the party to be removed (assuming that it exists, if it doesn't then this does nothing)
     */
    public void removeParty(String sadParty) {
        // Remove it based on the string
        partyList.remove(findPartyByName(sadParty));
    }

    /*
     * Removes a character by a party name and a character name
     * 
     * @param   partyName   The name of the party that the character is from
     * @param   sadCharacter     The name of the character to be removed
     */
    public void removeCharacter(String partyName, String sadCharacter) {

        // Remove the character
        findPartyByName(partyName).removePartyMember(findCharacterByName(sadCharacter));
    }

    /*
     * Returns the name of all of the parties
     * 
     * @return  Every parties name in a string array
     */
    public String[] getPartyNames() {

        // Hold an array of the same size of each party we have
        String[] names = new String[partyList.size()];

        // Loop over every party
        for (int i = 0; i < partyList.size(); i++)
        {
            // Set names at the current index of the loop to the parties's name
            names[i] = partyList.get(i).getName();
        }

        // Return names
        return names; 
    }

    /*
     * Returns the name of all of the characters
     * 
     * @return  Every characters name in a string array
     */
    public String[] getCharacterNames() {

        // Set the total number of characters as there is
        findCurentNumCharacters();

        // Hold an array of the total amount of characters in the parties
        String[] names = new String[totalCharacters];

        // Loop over every party
        for (Party party: partyList) {

            // Loop over every character
            for (int i = 0; i < party.getParty().size(); i++) {

                // Set the characters name at the current index in the name array
                names[i] = party.getParty().get(i).getName();
            }
        }

        // At the end, return names
        return names;
    }

    /*
     * @return  the name of the party collection
     */
    public String getName()
    {
        return name;
    }

    /*
     * Updates and returns the num of characters
     * 
     * @return  the number of characters
     */
    public int getNumCharacters() {

        // Check for the num of current characters
        findCurentNumCharacters();
        
        // Return the found value
        return totalCharacters;
    }
    
    /*
     * @return  the list of parties
     */
    public ArrayList<Party> getPartyList() {
        return partyList;
    }

    // Prints every party with all the characters below
    public void printFullParties() {

        // Loop every party
        for (Party party: partyList) {
            
            // Print it's name
            Simple.println(party.getName());

            // Loop every character
            for (Character character: party.getParty()) {

                // Print it's name with a tab infront
                Simple.println("\t" + character.getName());
            }
        }
    }
}