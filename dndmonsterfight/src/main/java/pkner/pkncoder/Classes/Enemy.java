package pkner.pkncoder.Classes;

public class Enemy extends Base {

    // Hold our abilities in arrays [ability score] | [ability mod]
    private int[] strength;
    private int[] dexterity;
    private int[] constitution;
    private int[] wisdom;
    private int[] intelligence;
    private int[] charisma;

    // Hold the weapon type
    private Weapon weapon;

    // Hold the armor type for the player character
    private Armor armor;

    /*
     * Class constructor
     * 
     * Sets all the needed variables and calls the superclass
     * 
     * @param   name            Enemy's name
     * @param   hp              Enemy's hp
     * @param   strength        [Score] | [Score Modifier]
     * @param   dexterity       [Score] | [Score Modifier]
     * @param   constitution    [Score] | [Score Modifier]
     * @param   wisdom          [Score] | [Score Modifier]
     * @param   intelligence    [Score] | [Score Modifier]
     * @param   charisma        [Score] | [Score Modifier]
     * @param   weapon          The Enemys's weapon
     * @param   armor           The Enemys's armor
     */
    public Enemy(String name, int hp, int[] strength, int[] dexterity, int[] constitution, int[] wisdom, int[] intelligence, int[] charisma, Weapon weapon, Armor armor) {
        // Call the Super class's constructor (base)
        super(
            name,  
            hp,
            armor.getAc(strength[0], dexterity[1]),
            0,
            weapon.getDice(), 
            0
        );

        // Ability scores
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;

        // Weapon / armor
        this.armor = armor;
        this.weapon = weapon;
    }

    /*
     * To string method
     * 
     * 
     * Name: {name}
     * 
     * Hp: {hp amount}
     * 
     * Armor: {armor's name}
     * Armor Class: {ac}
     * 
     * Strength: {score} ({modifier})
     * Dexterity: {score} ({modifier})
     * Constitution: {score} ({modifier})
     * Wisdom: {score} ({modifier})
     * Intelligence: {score} ({modifier})
     * Charisma: {score} ({modifier})
     * 
     * Weapon: {Weapon name}
     * To Hit Modifier: +-{toHitMod}
     * Damage Dice: {amount of dice rolled}d{dice type} +-{damageMod}
     * 
     * 
     * @return  A string formatted as shown above
     */
    @Override
    public String toString() {
        String finalString =
            "Name: " + super.getName() + "\n\n" +

            "Hp: " + super.getHp() + "\n\n" +

            "Armor: " + armor.getName() + "\nArmor Class: " + armor.getAc(strength[0], dexterity[1]) + "\n\n" +

            "Strength: " + strength[0] + " (" + strength[1] + ")\n" +
            "Dexterity: " + dexterity[0] + " (" + dexterity[1] + ")\n" +
            "Constitution: " + constitution[0] + " (" + constitution[1] + ")\n" +
            "Wisdom: " + wisdom[0] + " (" + wisdom[1] + ")\n" +
            "Intelligence: " + intelligence[0] + " (" + intelligence[1] + ")\n" +
            "Charisma: " + charisma[0] + " (" + charisma[1] + ")\n\n" +
            
            "Weapon: " + weapon.getName() + "\nTo Hit Modifier: " + (super.getToHitMod() > 0 ? ("+" + super.getToHitMod()) : super.getToHitMod()) + "\nDamage Dice: " + weapon.getDice()[0] + "d" + weapon.getDice()[1] + (super.getDamageMod() > 0 ? (" + " + super.getDamageMod()) : (" - " + Math.abs(super.getDamageMod()))) + "\n\n"
            
            ;

        return finalString;
    }

}
