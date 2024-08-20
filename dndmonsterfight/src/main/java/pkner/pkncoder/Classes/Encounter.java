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
     * Setup everything to start taking turns
     * 
     * @param players   The Party Collection that holds all of the player parties
     * @param enemies   The Party Collection that holds all of the enemy parties
     */
    public Encounter(PartyColection players, PartyColection enemies) {
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
        for (int i = 1; i < initiativeList.size(); i++) {

            Base key = initiativeList.get(i);
            Party partyKey = partiesInitiativeList.get(i);
            int j = i - 1;

            while (j >= 0 && initiativeList.get(j).getInitiative() < key.getInitiative()) {
                initiativeList.set(j + 1, initiativeList.get(j));
                partiesInitiativeList.set(j + 1, partiesInitiativeList.get(j));
                j = j - 1;
            }
            initiativeList.set(j + 1, key);
            partiesInitiativeList.set(j + 1, partyKey);

        }
    }

    /*
     * Helper Method for setInitiatives() that adds the base and party to their respective initiative list
     * 
     * @param partyColection Party colection ig (idk what else to say)
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
        Simple.clearTerminal();
        printAttackTable();

        if (!(win || checkWin())) {
            Simple.space();
            attack();
        }

        else {
            win = true;
        }
    }

    // Prints out all the information for an action
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

        // Check to see if enough players are dead or not (all except one of the bases are dead)
        if (basesDead == initiativeList.size() - 1) {
            
            // If the amount of bases dead hits a win condition, set win to true and return out
            win = true;
            return;
        }


        // Save a clone of initiative lists so we can remove bases from it as needed without affecting the full lists
        ArrayList<Base> initiativeListClone = (ArrayList<Base>) initiativeList.clone();
        ArrayList<Party> partiesInitiativeListClone = (ArrayList<Party>) partiesInitiativeList.clone();

        // Save a varaible that will keep how many dead bases there are
        
        
        // Three parts to this
        // {index} | {middle} | {additions}
        String index;
        String middle;
        String additions;

        // First, get the attacker
        Base attacker = initiativeListClone.remove(turnNum);
        Party attackerParty = partiesInitiativeListClone.remove(turnNum);

        // Check and make sure that the attacker is alive
        if (attacker.getHp() <= 0) {
            
            // If it isn't then increase the amount of bases dead
            basesDead++;

            // If turn number is at the end of the init list
            if (turnNum == initiativeList.size() - 1) {

                // Reset the turn num
                turnNum = 0;
            }
            
            // If were not at the end of the list
            else {
                // Increase the turn number
                turnNum++;
            }

            // Re-call this function
            printAttackTable();
            basesDead = 0;
            return;
        }

        // Else, if the attacker isn't dead

        // Set the three parts
        index = ">";
        middle = attacker.getName() + " (" + attacker.getHp() + ")";
        additions = attackerParty.getName();

        // Print out the three parts
        Simple.println(index + " | " + middle + " | " + additions);
        Simple.space();

        // Save the length of what we printed out to use later in the form of dashes
        int dashesLength = (index + " | " + middle + " | " + additions).length();

        // Save a variable to keep how many dead bases we hit
        int deadBasesHit = 0;
        
        // Loop every base
        for (int i = 0; i < initiativeListClone.size(); i++) {

            // Check to see if the current base is the attacker
            if (i + deadBasesHit == turnNum) {
                // If it is then print out the number of dashes we found earlier
                Simple.println("-".repeat(dashesLength));
            }
            
            // Check to see if the current base is dead
            if (initiativeListClone.get(i).getHp() <= 0) {

                // If it is, then set the parts as such 
                index = "x";
                middle = initiativeListClone.get(i).getName() + " (" + initiativeListClone.get(i).getHp() + ")";
                additions = partiesInitiativeListClone.get(i).getName();

                // Print the final output
                Simple.println(index + " | " + middle + " | " + additions);

                // REMOVE THE EVIDENCE
                initiativeListClone.remove(i);
                partiesInitiativeListClone.remove(i);

                // Increase the amount of dead bases 
                deadBasesHit++;

                // Back up i so the indicies can work and we don't skip anyone and continue on
                i--;
                continue;
            }

            // If the current base isn't dead
            // Set the three parts
            index = String.valueOf(i + 1);
            middle = initiativeListClone.get(i).getName() + " (" + initiativeListClone.get(i).getHp() + ")";
            additions = partiesInitiativeListClone.get(i).getName();

            // Print the final output
            Simple.println(index + " | " + middle + " | " + additions);
            
        }

        // After the attack tabling set the remaining bases
        preyBases = initiativeListClone;

        // After all that, check to make sure that we don't need to loop turnNum by checking to see if it is the same as the size of initiative list
        // Also, on the last turn the attacker's dashes don't get caught, so print them here
        if (turnNum == initiativeList.size() - 1) {
            // Print the attacker's dashes
            Simple.println("-".repeat(dashesLength));

            // If we do then just set it back to 0
            turnNum = 0; 

            // Return out of the function
            return;
        }

        // If it didn't need resetting increase turn num
        turnNum++;
    }

    // Enact an attack against another base
    private void attack() {
        // Querry who their attacking
        int attackerIndex = Simple.getIntInput("Who are you attacking (ex. Harry or 2): ", "[1-" + preyBases.size() + "]");

        initiativeList.get(turnNum).attack(preyBases.get(attackerIndex - 1));
    }

    public boolean checkWin() {
        int aliveBases = 1;

        for (Base base: preyBases) {
            if (base.getHp() > 0) {
                aliveBases++;
            }
        }

        return aliveBases < 2;
    }

    public boolean getWin() {
        return win;
    }

    public void printWinStatements() {
        printAttackTable();

        Simple.println("Party: '" + partiesInitiativeList.get(turnNum).getName() + "' wins!");
    }
}
