package pkner.pkncoder.Classes;
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

    /*
     * Class Constructor
     * 
     * @param   name            name of the base
     * @param   hp              health of the base
     * @param   attackStr       bonus to the attack roll
     * @param   attackDmgRoll   [number of dice] | [dice type]
     * @param   attackDmgMod    amount of damage added after rolling it
     */
    public Base(String name, int hp, int ac, int attackStr, int[] attackDmgRoll, int attackDmgMod) {

        // Set the values accordingly
        // Name
        this.name = name;

        // Stats
        this.hp = hp;
        this.ac = ac;

        // To-hit
        this.attackStr = attackStr;

        // Damage
        this.attackDmgRoll = attackDmgRoll;
        this.attackDmgMod = attackDmgMod;
    }

    /*
     * Attacks another base
     * 
     * @param   other   the other base to attack
     */
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

    // Roll a random initiative and set initiative as so
    public void rollInititave() {
        // Int of 0....0.99 * 20 (max - ((min + 1) cancles out)) plus min
        initiative = (int) (Math.random() * 20) + 1;
    }

    /*
     * @return  name of the base
     */
    public String getName() {
        return name;
    }

    /*
     * @return  hp of the base
     */
    public int getHp() {
        return hp;
    }

    /*
     * @return  ac of the base
     */
    public int getAC() {
        return ac;
    }

    /*
     * @return  initiative of the base
     */
    public int getInitiative() {
        return initiative;
    }


    /*
     * Sets the initiative of the base
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
     * Attack: +{attack strength} to hit
     * Damage: {num dice}d{dice type} + {damage mod} damage
     * 
     * 
     * @return  a string formated as such
     */
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