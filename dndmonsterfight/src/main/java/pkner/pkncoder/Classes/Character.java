package pkner.pkncoder.Classes;

import pkner.pkncoder.CustomMethods.Simple;

public class Character implements Comparable<Character> {

    // Name of the character
    private String name;

    // Armor class and hp of the character
    private int hp;
    private int ac;

    // To hit modifier
    private int toHitMod;

    // Damage dice and damage modifier
    private int[] damageDice;
    private int damageMod;

    // Initiative
    private int initiative;

    /*
     * Class Constructor
     * 
     * @param   name        Name of the character
     * @param   hp          Health of the character
     * @param   ac          Armor class of the character
     * @param   toHitMod    Bonus to the to hit roll
     * @param   damageDice  [number of dice] | [dice type]
     * @param   damageMod   Amount of damage added after rolling it
     */
    public Character(String name, int hp, int ac, int toHitMod, int[] damageDice, int damageMod) {
        // Set the values accordingly
        // Name
        this.name = name;

        // Stats
        this.hp = hp;
        this.ac = ac;

        // To-hit
        this.toHitMod = toHitMod;

        // Damage
        this.damageDice = damageDice;
        this.damageMod = damageMod;
    }

    /*
     * Attacks another character
     * 
     * @param   other   the other character to attack
     */
    public void attack(Character other) {
        // Hold a variable to keep track of all damage in this attack
        int totalDamage = 0;

        // Get a random number ranging between 1 and 20 for the hit die, and add the to hit modifier
        int toHit = Simple.randomInt(1, 20) + toHitMod;

        // If the toHit is greater than or equals the other's ac, or its a nat 20 we hit the other character
        if (toHit >= other.getAC() || toHit - toHitMod == 20) {

            // Loop for however many times we need to 'roll the dice'
            for (int i = 0; i < damageDice[0]; i++) {

                // Hold a variable that keeps track of how much damage this roll did
                int damageRoll = Simple.randomInt(1, damageDice[1]);

                // If we got a nat 20 just double the rolled damage of this roll
                if (toHit - toHitMod == 20) {damageRoll *= 2;}

                // Add the damage to the total damage
                totalDamage += damageRoll;
            }

            // Finally, subtract the total damage we did plus the damage mod
            other.hp -= totalDamage + damageMod;
        }
    }

    // Roll a random initiative and set initiative as so
    public void rollInititave() {
        // Random number
        initiative = Simple.randomInt(1, 20);
    }

    /*
     * @return  name of the character
     */
    public String getName() {
        return name;
    }

    /*
     * @return  hp of the character
     */
    public int getHp() {
        return hp;
    }

    /*
     * @return  ac of the character
     */
    public int getAC() {
        return ac;
    }

    /*
     * @return  initiative of the character
     */
    public int getInitiative() {
        return initiative;
    }

    /*
     * @return  the to hit modifier
     */
    public int getToHitMod() {
        return toHitMod;
    }

    /*
     * @return  the damage modifier
     */
    public int getDamageMod() {
        return damageMod;
    }


    /*
     * Sets the initiative of the character
     * 
     * @param   initiativeNum   the new value to be set
     */
    public void setInitiative(int initiativeNum) {
        initiative = initiativeNum;
    }

    /*
     * To string
     * 
     * 
     * Name: {name}
     * 
     * HP: {hp}
     * AC: {ac}
     * 
     * To Hit Modifier: +-{toHitMod}
     * Damage: {amount of dice rolled}d{dice type} +- {damageMod}
     * 
     * 
     * @return  a string formated as such
     */
    @Override
    public String toString() {
        return (
            "Name: " + name + "\n\n" +

            "HP: " + hp + "\n" +
            "AC: " + ac + "\n\n" +
            
            // If the to hit modifier is greater or equal to 0 then print it with a +, else print it with it's own minus sign
            "To Hit Modifier: " + (toHitMod >= 0 ? ("+" + toHitMod) : toHitMod) + "\n" +
            // If the attack damage mod is greater than or equal to 0 then print it with a +, else print the abs of it with a -
            "Damage: " + damageDice[0] + "d" + damageDice[1] + (damageMod >= 0 ? (" + " + damageMod) : (" - " + Math.abs(damageMod)))
        );
    }

    /*
     * Used to sort the characters in decending order
     * 
     * @param   other   The other character were're comparing to
     * @return          A number detemining
     */
    @Override
    public int compareTo(Character other)
    {
        // The other one minus this one's initiative for decending order
        return other.getInitiative() - this.initiative;
    }
}