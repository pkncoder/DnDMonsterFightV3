package pkner.pkncoder.Classes;

import pkner.pkncoder.CustomMethods.Simple;

public class Base implements Comparable<Base> {
    
    // Name of the base
    private String name;

    // Ac and hp of the base
    private int hp;
    private int ac;

    // Attack str (what get's added to the to hit die)
    private int attackStr;

    // Damage stuff including dice rolls
    private int[] attackDmgRoll;
    private int attackDmgMod;

    // Initiative
    private int initiative;

    // Constructor
    public Base(String name, int hp, int ac, int attackStr, int[] attackDmgRoll, int attackDmgMod) {

        this.name = name;

        this.hp = hp;
        this.ac = ac;

        this.attackStr = attackStr;

        this.attackDmgRoll = attackDmgRoll;
        this.attackDmgMod = attackDmgMod;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAC() {
        return ac;
    }

    public int getInitiative() {
        return initiative;
    }

    // Hp by how much specified unless more than hp, in which hp is 0
    public boolean takeDamage(int damage) {

        // Subtract the hp by the damage
        hp -= damage;

        // If the hp is now in the negatives, turn it to 0
        if (hp <= 0) {hp = 0;}

        // Return if hp is more than 0 (true if it's still alive)
        return hp > 0;
    }

    // This will attack a givin monster and use it's ac to get the total damage
    public void attack(Base other) {

        // Hold a value, holding any damage done
        int currentTotalDamage = 0;

        // Get a random number ranging between 1 and 20 for the hit die, and add the strength that the monster has
        int toHit = ((int) ((Math.random() * 20) + 1)) + attackStr;

        // If it does hit or get's a nat 20
        if (toHit >= other.getAC() || toHit - attackStr == 20) {

            // Loop for however many times we need to 'roll the dice'
            for (int i = 0; i < attackDmgRoll[0]; i++) {

                // Hold a value that holds how much damage we did
                int damageRoll = ((int) ((Math.random() * attackDmgRoll[1]) + 1));

                // If we got a nat 20 just double the rolled damage
                if (toHit - attackStr == 20) {damageRoll *= 2;}

                // Mark the damage we did
                currentTotalDamage += damageRoll;
            }

            // Finally, return the total damage we did with the mod
            other.hp -= currentTotalDamage + attackDmgMod;
        }
    }

    public void rollInititave() {
        // Int of 0....0.99 * 20 (max - ((min + 1) cancles out)) plus min
        initiative = (int) (Math.random() * 20) + 1;
    }

    public void setInitiative(int initiativeNum) {
        initiative = initiativeNum;
    }

    // To-str
    @Override
    public String toString() {
        return (
            "Name: " + name +

            "\n\nHP: " + hp + 
            "\nAC: " + ac +
            
            "\n\nAttack: " + "+" + attackStr + " to hit  " +
            "\nDamage: " + attackDmgRoll[0] + "d" + attackDmgRoll[1] + " + " + attackDmgMod + " damage"
        );
    }

    // Compare to from Comparable's interface
    @Override
    public int compareTo(Base other)
    {
        // The other one minus this one's initiative for decending order
        return other.getInitiative() - this.initiative;
    }
}