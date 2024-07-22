package pkner.pkncoder.Classes;

public class Weapon {

    // Title of the weapon
    private String name;
    
    // Price of the weapon (unimplimented)
    private int cost;

    // The amount of damage the dice will do [Amount of dice rolled] | [Type of dice rolled]
    private int[] dice;

    // Constructor
    public Weapon(String name, int cost, int[] dice) {

        // Setting everything
        this.name = name;
        this.cost = cost;
        this.dice = dice;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getCose() {
        return cost;
    }

    public int[] getDice() {
        return dice;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nCose: " + cost + "\nDamage: " + dice;
    }
}
