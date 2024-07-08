package Classes;

public class Enemy extends Base {

    // Hold our abilities in arrays [ability score] | [ability mod]
    private int[] strength;
    private int[] dexterity;
    private int[] constitution;
    private int[] wisdom;
    private int[] intelligence;
    private int[] charisma;

    // Hold the armor type for the player character
    private Armor armor;

    // Hold the weapon type
    private Weapon weapon;

    // Constructor
    public Enemy(String name, int hp, int[] strength, int[] dexterity, int[] constitution, int[] wisdom, int[] intelligence, int[] charisma, Armor armor, Weapon weapon) {
        // Super the constructor
        super(
            name,  
            hp,
            armor.getAc(strength[0], dexterity[1]),
            0,
            weapon.getDice(), 
            0
        );

        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;

        this.armor = armor;
        this.weapon = weapon;
    }

    // To-string
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
            
            "Weapon: " + weapon.getName() + "\nTo Hit Modifier: +0\nDamage Dice: " + weapon.getDice()[0] + "d" + weapon.getDice()[1] + " + 0\n\n"
            
            ;

        return finalString;
    }

}
