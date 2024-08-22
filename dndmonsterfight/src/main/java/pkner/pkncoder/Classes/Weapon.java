package pkner.pkncoder.Classes;

public class Weapon {

    // Title of the weapon
    private String name;
    
    // Price of the weapon (unimplimented)
    private int cost;

    // The amount of damage the dice will do [Amount of dice rolled] | [Type of dice rolled]
    private int[] dice;

    /*
     * Class constructor
     * 
     * @param   name    The name of the weapon
     * @param   cost    The cost of the weapon (currently unimplimented)
     * @param   dice    [number of dice to be rolled] | [the type of dice to be rolled]
     */
    public Weapon(String name, int cost, int[] dice) {

        // Setting all of our variables
        this.name = name;
        this.cost = cost;
        this.dice = dice;
    }

    /*
     * @returns The name of the weapon
     */
    public String getName() {
        return name;
    }

    /*
     * @returns The cost of the weapon
     */
    public int getCose() {
        return cost;
    }

    /*
     * @returns The dice of the weapon
     */
    public int[] getDice() {
        return dice;
    }

    /*
     * To string method
     * 
     * Name: {name}
     * Cost: {cost}
     * Damage: {number of dice to be rolled}d{the type of dice to be rolled}
     * 
     * @return a string formatted as above
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nCost: " + cost + "\nDamage: " + dice[0] + "d" + dice[1];
    }
}
