package pkner.pkncoder.Classes;

import java.util.ArrayList;
import java.util.Collections;

public class Encounter {
    
    // The two alliences
    private PartyColection players;
    private PartyColection enemies;

    // List of every base in initiative order
    private ArrayList<Base> initiativeList = new ArrayList<Base>();

    // List of every base's party in initiative order
    private ArrayList<Party> partiesInitiativeList = new ArrayList<Party>();

    // The current turn number
    private int turnNum;

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

        // Loop for every base in all our parties
        for (Party party: players.getPartyList()) {
            for (Base base: party.getParty()) {

                // Roll the base's initiative
                base.rollInititave();

                // Add it into the list
                initiativeList.add(base);
                partiesInitiativeList.add(party);

            }
        }

        // // After everything sort using collections
        // Collections.sort(initiativeList);

        // TODO: Find a better way of pairing Parties and Players, or find a better alg
        for (int i = 0; i < initiativeList.size(); i++) {

            Base key = initiativeList.get(i);
            int j = i - 1;

            while (j >= 0 && initiativeList.get(j).getInitiative() > key.getInitiative()) {
                initiativeList.set(j + 1, initiativeList.get(j));
                j = j + 1;
            }
            initiativeList.set(j + 1, key);

        }
    }

    // Takes a single turn
    public void takeTurn() {}

    // Prints out all the information for an action
    public void printAttackTable() {

        /*
         * 
         * < = turn
         * o = teamate
         * + = ally
         * 
         * 1 | l2 (hp) | +
         * 2 | d1 (hp) |
         * > | m1 (hp) | <
         * x | m2 xxxx | o
         * 3 | d3 (hp) |
         * 
         */



    }

    // Enact an attack against another base
    private void attack() {}

    /*
     * Checks to see if all win conditions are met
     * 
     * @returns true if all win conditions are met if not, then returns false
     */
    public boolean checkWin() {return true;}


}
