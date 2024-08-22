package pkner.pkncoder.Classes;

import java.util.ArrayList;

import pkner.pkncoder.CustomMethods.Simple;

public class Encounter {
    
    // The two alliences
    private PartyColection players;
    private PartyColection enemies;

    // List of every base in initiative order
    private ArrayList<Base> initiativeList = new ArrayList<Base>();

    // List of every base's party in initiative order
    private ArrayList<Party> partiesInitiativeList = new ArrayList<Party>();

    // The array list of not dead bases (set during printAttackTable())
    private ArrayList<Base> preyBases;

    // The current turn number
    private int turnNum = 0;

    // The amount of bases dead (used as a limit for the recursive nature of printAttackTable() and used to check for a win or not)
    private int basesDead = 0;

    // If a win has been found
    private boolean win = false;

    /*
     * Class constructor
     * 
     * Sets everything up to start taking turns
     * 
     * @param   players   The Party Collection that holds all of the player parties
     * @param   enemies   The Party Collection that holds all of the enemy parties
     */
    public Encounter(PartyColection players, PartyColection enemies) {

        // Set the party collections
        this.players = players;
        this.enemies = enemies;

        // Setup the initiatives
        setInitiatives();
    }

    // Helper command to Encounter
    private void setInitiatives() {

        // Add the bases to the initiative parties
        addBasesToInitiativeParties(players);
        addBasesToInitiativeParties(enemies);
        

        // TODO: Find a better way of pairing Parties and Players, or find a better alg
        // Insertion sort
        for (int i = 1; i < initiativeList.size(); i++) {

            // Get the keys (the items that need sorted)
            Base key = initiativeList.get(i);
            Party partyKey = partiesInitiativeList.get(i);

            // Store a slot which is the spot that is assumed sorted
            int slot = i - 1;

            // While the slot is greater than 0 and the slot's initiative is less than the key's initiative it needs to move back
            while (slot >= 0 && initiativeList.get(slot).getInitiative() < key.getInitiative()) {
                
                // Move slot back
                initiativeList.set(slot + 1, initiativeList.get(slot));
                partiesInitiativeList.set(slot + 1, partiesInitiativeList.get(slot));

                // Set slot back one again
                slot = slot - 1;
            }

            // Set the keys where they need to be
            initiativeList.set(slot + 1, key);
            partiesInitiativeList.set(slot + 1, partyKey);

        }
    }

    /*
     * Helper Method for setInitiatives() that adds the base and party to their respective initiative list
     * 
     * @param   partyColection  A party collection to roll initiative's of and add to the list (unsorted)
     */
    private void addBasesToInitiativeParties(PartyColection partyColection) {
        // Loop over every base in our party
        for (Party party: partyColection.getPartyList()) {
            for (Base base: party.getParty()) {

                // Roll the base's initiative
                base.rollInititave();

                // Add it into the list
                initiativeList.add(base);
                partiesInitiativeList.add(party);

            }
        }
    }

    // Takes a single turn
    public void takeTurn() {

        // Clear the terminal and print out the attack table
        Simple.clearTerminal();
        printAttackTable();

        // If win is false then continue with normal turn action(s)
        if (!win) {

            // Enact an attack
            Simple.space();
            attack();

            // Forward to the next turn after the attack is finished
            forwardTurn();
        }
    }

    // Prints out the attack table with all the needed info and checks win contitions
    public void printAttackTable() {

        /*
         * > | m1 (hp) | {PARTY}
         * 
         * 1 | l2 (hp) | {PARTY}
         * 2 | d1 (hp) | {PARTY}
         * ---------------------
         * x | m2 xxxx | {PARTY}
         * 3 | d3 (hp) | {PARTY}
         * 
        */

        // During the recursion of the attackers being dead, check to see if we have just one base left
        if (basesDead == initiativeList.size() - 1) {
            
            // If there is, then set win to true and return out
            win = true;
            return;
        }


        /* Attacker */

        // Save a clone of initiative lists so we can remove bases from it as needed without affecting the full lists
        ArrayList<Base> initiativeListClone = (ArrayList<Base>) initiativeList.clone();
        ArrayList<Party> partiesInitiativeListClone = (ArrayList<Party>) partiesInitiativeList.clone();
        
        // Three parts to each base when they get printed out
        // {start} | {middle} | {additions}
        String start;
        String middle;
        String additions;

        // First, get the attacker and their party
        // At the same time remove them from the clones so they are ignored when looping over the rest of the bases
        Base attacker = initiativeListClone.remove(turnNum);
        Party attackerParty = partiesInitiativeListClone.remove(turnNum);

        // Check and make sure that the attacker is alive
        if (attacker.getHp() <= 0) {

            /* If the attacker is found dead, then we need to skip to the next turn */
            
            // Increase bases dead
            basesDead++;

            // Go to the next turn
            forwardTurn();

            // Re-call this function so we can check the next attacker, and consiquently print the next turn's attack table (assuming that the next attacker is alive)
            printAttackTable();

            // After the recursion finishes, set basesDead to 0 so the next time this is called it can re-count everything
            basesDead = 0;

            // Return out as the table has been printed already, or returned out as a win condition has been found
            return;
        }

        // Else, if the attacker isn't dead

        // Set the three parts
        start = ">";
        middle = attacker.getName() + " (" + attacker.getHp() + ")";
        additions = attackerParty.getName();

        // Print out the three parts
        Simple.println(start + " | " + middle + " | " + additions);
        Simple.space();


        /* Possible attackees (prey bases) */

        // Save the length of what we printed out to use later in the form of dashes where the attacker usually is in the intiative list
        int dashesLength = (start + " | " + middle + " | " + additions).length();

        // Save a variable to keep how many dead bases we hit
        // This is used for checking where the attacker was
        int deadBasesHit = 0;
        
        // Loop every base
        for (int i = 0; i < initiativeListClone.size(); i++) {

            // Check to see if the current base is the attacker
            // We add dead bases hit since they get removed from the list, and i is decreased
            if (i + deadBasesHit == turnNum) {
                // If it is then print out the number of dashes we found earlier
                Simple.println("-".repeat(dashesLength));

                // Don't return out as we have to still print the actual base's info the loop is on
            }
            
            // Check to see if the current base is dead
            if (initiativeListClone.get(i).getHp() <= 0) {

                // If it is, then set the parts as such 
                start = "x";
                middle = initiativeListClone.get(i).getName() + " (" + initiativeListClone.get(i).getHp() + ")";
                additions = partiesInitiativeListClone.get(i).getName();

                // Print the final output
                Simple.println(start + " | " + middle + " | " + additions);

                // REMOVE THE EVIDENCE of the dead base so the indicies are still right, and we're not trying to attack a dead base
                initiativeListClone.remove(i);
                partiesInitiativeListClone.remove(i);

                // Increase the amount of dead bases 
                deadBasesHit++;

                // Back up i so the indicies can work and we don't skip anyone and continue on to the next loop
                i--;
                continue;
            }

            // If the current base isn't dead
            // Set the three parts
            start = String.valueOf(i + 1);
            middle = initiativeListClone.get(i).getName() + " (" + initiativeListClone.get(i).getHp() + ")";
            additions = partiesInitiativeListClone.get(i).getName();

            // Print the final output
            Simple.println(start + " | " + middle + " | " + additions);
            
        }


        /* Post attack table */

        // Check to see if the turn num is pointing to the last base, as the attacker check to print the dashes doesn't get caught in the loop, so print them here
        if (turnNum == initiativeList.size() - 1) {
            Simple.println("-".repeat(dashesLength));
        }

        // After printing the attack table set the prey bases to the left over bases in the initiative list's clone
        preyBases = initiativeListClone;

        // Check prey base's size, if it is 0 then it is just the attacker left and a win condition is met
        if (preyBases.size() == 0) {

            // Set win to true
            win = true;
        }
    }

    // Set turn number to the next spot
    public void forwardTurn() {

        // If the current turn is pointing to the last base, it needs to be reset
        if (turnNum == initiativeList.size() - 1) {

            // Set turn number to 0 and return out
            turnNum = 0;
            return;
        }

        // Else, just increase turn number
        turnNum++;
    }

    // Enact an attack against another base
    private void attack() {
        // Querry who their attacking
        int attackerIndex = Simple.getIntInput("Who is " + initiativeList.get(turnNum).getName() + " attacking (ex. 1): ", "[1-" + preyBases.size() + "]");

        // Get the turn number of the current base, and attack the chosen base from the prey bases
        initiativeList.get(turnNum).attack(preyBases.get(attackerIndex - 1));
    }

    /*
     * @return  returns the value of the win variable
     */
    public boolean getWin() {
        return win;
    }

    // Print the win statements
    public void printWinStatements() {

        // Print the attack table
        printAttackTable();

        // Seperate the party win statement and the table
        Simple.space();

        // Print out the party at the turn number's slot
        Simple.println("Party: '" + partiesInitiativeList.get(turnNum).getName() + "' wins!");
    }
}