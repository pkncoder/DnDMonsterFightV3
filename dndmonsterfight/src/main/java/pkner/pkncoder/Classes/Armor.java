package pkner.pkncoder.Classes;

public class Armor {
    
    // Holds the name of the armor
    String name;

    // The base armor class of the armor
    int armorClass; 
    
    // Used to decide if the player can wear said armor or not
    int minimumStrengthToWear;

    // Used to add extra ac from their dex
    boolean addDex; // If you add the dex or not
    int maxDexBonus; // If the dex is capped at 2

    /*
     * Class constructor
     * 
     * @param   name        the name of the armor
     * @param   armorClass  the base armor class of the armor
     * @param   addDex      a boolean decing if the dex is to be added or not
     * @param   maxDexBonus the max amount of dex to be added to the character's final ac
     */
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

    /*
     * Class constructor
     * 
     * @param   name                    the name of the armor
     * @param   armorClass              the base armor class of the armor
     * @param   minimumStrengthToWear   the min amount of strength needed to wear the armor
     * @param   addDex                  a boolean decing if the dex is to be added or not
     * @param   maxDexBonus             the max amount of dex to be added to the character's final ac
     */
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

    /*
     * Returns the character's final ac
     * 
     * @param   strength    the characters strength score
     * @param   dexMod      the player's dexterity modifier
     */
    public int getAc(int strength, int dexMod) {   
        // If the strength is too low
        if (strength < minimumStrengthToWear) {
            // Return the natural armor amount
            return 10 + dexMod;
        }

        // If there isn't a dex mod
        if (!addDex) {
            // Return the armor class with no mod
            return armorClass;
        }
        
        // If there is a dex cap, return the minimum between 2 (the cap) and the dex mod
        return armorClass + Math.min(maxDexBonus, dexMod);
    }

    /*
     * @return the armor's name
     */
    public String getName() {
        return name;
    }

    /*
     * @return the minimum strength needed to wear
     */
    public int getMinStr() { 
        return minimumStrengthToWear; 
    }

    /*
     * To string
     * 
     * Name: {name}
     * Ac: {base ac}
     * Min Str to wear: {min strength}
     * Add Dex? {true / false}
     * Max Dex Bonus: {max dex bonus}
     * 
     * @return a string formatted as above
     */
    @Override
    public String toString() {
        return (
            "Name: " + name + "\n" + 
            "AC: " + armorClass + "\n" + 
            "Min Str to wear: " + minimumStrengthToWear + "\n" + 
            "Add Dex? " + addDex + "\n" + 
            "Max Dex Bonus: " + maxDexBonus
        );
    }
}
