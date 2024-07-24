package pkner.pkncoder.Classes;

public class Armor {
    
    // Holds the title of the armor shown to the player
    String name;

    // The armor class givin to the player when worn (if able)
    int armorClass; 
    
    // Used to decide if the player can wear said armor or not
    int minimumStrengthToWear;

    // Used to add extra ac from their dex
    boolean addDex; // If you add the dex or not
    int maxDexBonus; // If the dex is capped at 2

    // Constructors
    // Used if there is no strength min
    public Armor(String name, int armorClass, boolean addDex, int maxDexBonus) {

        // Name & armor class
        this.name = name;
        this.armorClass = armorClass;

        // No min strength
        this.minimumStrengthToWear = 0;

        // Setting the dexterity items
        this.addDex = addDex;
        this.maxDexBonus = maxDexBonus;
    }

    // Used if there is a strength min
    public Armor(String name, int armorClass, int minimumStrengthToWear, boolean addDex, int maxDexBonus)  {

        // Name & armor class
        this.name = name;
        this.armorClass = armorClass;

        // Set the strength min
        this.minimumStrengthToWear = minimumStrengthToWear;

        // Set the dexterity stuff
        this.addDex = addDex;
        this.maxDexBonus = maxDexBonus;
    }

    // Getters
    // Ac
    public int getAc(int strength, int dexMod) {   
        // If the strength is too low
        if (!(strength >= minimumStrengthToWear)) {
            // Return -1 
            return -1;
        }

        // If there isn't a dex mod
        if (!addDex) {
            // Return the armor class with no mod
            return armorClass;
        }
        
        // If there is a dex cap, return the minimum between 2 (the cap) and the dex mod
        return armorClass + Math.min(maxDexBonus, dexMod);
    }

    // Returns the name
    public String getName() {
        return name;
    }

    // Returns the minimum strength
    public int getMinStr() { 
        return minimumStrengthToWear; 
    }

    // ToString
    @Override
    public String toString() {
        return "Name: " + name + "\nAC: " + armorClass + "\nMin Str to wear: " + minimumStrengthToWear + "\nAdd Dex? " + addDex + "\nMaxDexBonus: " + maxDexBonus;
    }
}
