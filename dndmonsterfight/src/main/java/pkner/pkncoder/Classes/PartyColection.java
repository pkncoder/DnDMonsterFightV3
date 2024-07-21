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

    // Constructor
    public PartyColection(String name)
    {
        this.name = name;
    }

    // Find a party based on a string, and return said party
    public Party findPartyByName(String partyName) {
        
        // Loop over every party
        for (Party party: partyList) {

            // If the name of the party equals the strging givin
            if (party.getName().toUpperCase().equals(partyName.toUpperCase())) {

                // Return it
                return party;
            }
        }

        // If the loop never returns, exit out
        return null;
    }
    
    // Find a base based on a string, and return said base
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

        // If the loop never returns, exit out
        return null;

    }

    // Finds the new total num bases to re-calibrate the total bases
    public int findCurentNumBases() {
        // Hold a new variable
        int total = 0;

        // Loop over every base
        for (Party party: partyList) { // party
            for (int i = 0; i < party.getParty().size(); i++) { // base
                total++; // Incriment total
            }
        }

        // Finally, return total
        return total;
    }

    // Add a party to our list
    public void addParty(Party newParty) {
        partyList.add(newParty);
    }

    // Add a base to the party
    public void addBaseToParty(Base newBase) {
        // Get our possible parties to add to
        String[] parties = getPartyNames();

        // Print them out
        Simple.printArray(parties);

        // Ask which one they would like to add to
        String chosenParty = Simple.getStringInput("Where is this base going to: ", parties, "Invalid Input", true);

        // Add that base based on the input
        findPartyByName(chosenParty).getParty().add(newBase);
        totalBases++;
    }

    // Delete a party from our list
    public void removeParty(Party sadParty) {
        // Remove it
        partyList.remove(sadParty);
        
        // Re-calibrate the total bases
        totalBases = findCurentNumBases();
    }

    // Delete a party from our list based on a string
    public void removeParty(String sadParty) {
        // Remove it based on the string
        partyList.remove(findPartyByName(sadParty));
        
        // Re-calibrate the total bases
        totalBases = findCurentNumBases();
    }

    // Delete a base based on a name
    public void removeBase(String partyName, String sadBase) {
        findPartyByName(partyName).removePartyMember(findBaseByName(sadBase));
        totalBases--;
    }

    // Return an array full of each parties name
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

    // Returns the name of every base in the party
    public String[] getBaseNames() {
        
        // Hold an array of the total amount of bases in the parties
        String[] names = new String[totalBases];

        // Hold an index to add to the names array
        int index = 0;

        // Loop over every party
        for (Party party: partyList) {

            // Loop over every base
            for (Base base: party.getParty()) {

                // Set names at index to the current base's name
                names[index] = base.getName();

                // Increment index by 1
                index++;
            }
        }

        // At the end, return names
        return names;
    }

    // Return our parties's name
    public String getName()
    {
        return name;
    }

    // Return the number of bases
    public int getNumBases() {
        return totalBases;
    }
    
    // Return our arraylist
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