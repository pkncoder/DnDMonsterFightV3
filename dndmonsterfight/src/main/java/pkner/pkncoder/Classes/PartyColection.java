package pkner.pkncoder.Classes;

import java.util.ArrayList;

import pkner.pkncoder.CustomMethods.Simple;

public class PartyColection {

    // Our array list full of parties
    private ArrayList<Party> partyList = new ArrayList<Party>();

    // The name of our party colection
    private String name;

    // How many current bases there are in all the parties combined
    private int totalBases;

    /* 
     * Class Constructor
     * 
     * @param   name    the name of the party collection (usually "Players" or "Enemies")
    */
    public PartyColection(String name)
    {
        // Set the party name
        this.name = name;

        // Set total bases to 0
        totalBases = 0;
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
     * Finds a base from one of the parties by the name of it
     * 
     * @param   baseName    The name of the party your trying to find
     * @return              Either the found base, or nothing
     */
    public Base findBaseByName(String baseName) {
        
        // Loop over every party
        for (Party party: partyList) {

            // Loop over every base
            for (Base base: party.getParty()) {
                
                // Test to see if the names match
                if (base.getName().toUpperCase().equals(baseName.toUpperCase())) {

                    // If they do, then return it
                    return base;
                }
                
            }
            
        }

        // If the loop never returns, exit out and return nothing
        return null;

    }

    // Re-calibrates the total bases
    public void findCurentNumBases() {

        // Loop over every party
        for (Party party: partyList) { // party
            // Add the parties array list of bases size to the total bases
            totalBases += party.getParty().size();
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

        // Re-calibrate the total bases
        findCurentNumBases();
    }

    /*
     * Adds a new base to the party chosen in the method
     * 
     * @param   newBase the base that will be added
     */
    public void addBaseToParty(Base newBase) {

        // Get our possible parties to add to
        String[] parties = getPartyNames();

        // Print them out
        Simple.printArray(parties);
        Simple.space();

        // Ask which one they would like to add to
        String chosenParty = Simple.getStringInput("Where is this base going to: ", parties, "Invalid Input", true);

        // Add that base based on the input
        findPartyByName(chosenParty).getParty().add(newBase);

        // Increase the total bases
        totalBases++;
    }

    /*
     * Removes a party from the list
     * 
     * @param   sadParty    the party to be removed (assuming that it exists, if it doesn't then this does nothing)
     */
    public void removeParty(Party sadParty) {
        // Remove it
        partyList.remove(sadParty);
        
        // Re-calibrate the total bases
        findCurentNumBases();
    }

    /*
     * Removes a party from the list based on a string
     * 
     * @param   sadParty    the party to be removed (assuming that it exists, if it doesn't then this does nothing)
     */
    public void removeParty(String sadParty) {
        // Remove it based on the string
        partyList.remove(findPartyByName(sadParty));
        
        // Re-calibrate the total bases
        findCurentNumBases();
    }

    /*
     * Removes a base by a party name and a base name
     * 
     * @param   partyName   The name of the party that the base is from
     * @param   sadBase     The name of the base to be removed
     */
    public void removeBase(String partyName, String sadBase) {

        // Remove the base
        findPartyByName(partyName).removePartyMember(findBaseByName(sadBase));

        // Decrease totalBases
        totalBases--;
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
     * Returns the name of all of the bases
     * 
     * @return  Every bases name in a string array
     */
    public String[] getBaseNames() {
        
        // Hold an array of the total amount of bases in the parties
        String[] names = new String[totalBases];

        // Loop over every party
        for (Party party: partyList) {

            // Loop over every base
            for (int i = 0; i < party.getParty().size(); i++) {

                // Set the bases name at the current index in the name array
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
     * @return  the number of bases
     */
    public int getNumBases() {
        return totalBases;
    }
    
    /*
     * @return  the list of parties
     */
    public ArrayList<Party> getPartyList() {
        return partyList;
    }

    // Prints every party with all the bases below
    public void printFullParties() {

        // Loop every party
        for (Party party: partyList) {
            
            // Print it's name
            Simple.println(party.getName());

            // Loop every base
            for (Base base: party.getParty()) {

                // Print it's name with a tab infront
                Simple.println("\t" + base.getName());
            }
        }
    }
}