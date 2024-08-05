package pkner.pkncoder.Classes;

import java.util.ArrayList;

public class Encounter {
    
    // The two alliences
    private PartyColection players;
    private PartyColection enemies;

    // List of every base in initiative order
    private ArrayList<Base> initiativeList;

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
    private void setInitiatives() {}

    // Takes a single turn
    public void takeTurn() {}

    // Prints out all the information for an action
    private void printAttackTable() {}

    // Enact an attack against another base
    private void attack() {}

    /*
     * Checks to see if all win conditions are met
     * 
     * @returns true if all win conditions are met if not, then returns false
     */
    public boolean checkWin() {return true;}


}
