package pkner.pkncoder.Classes;

import pkner.pkncoder.CustomMethods.Simple;

public class PlayerCharacter extends Base {

    // Hold our abilities in arrays [ability score] | [ability mod]
    private int[] strength;
    private int[] dexterity;
    private int[] constitution;
    private int[] wisdom;
    private int[] intelligence;
    private int[] charisma;

    // Hold our character's class
    private PlayerClass userClass;

    // Hold our character's level
    private int level;

    // Hold the armor type for the player character
    private Armor armor;

    // Hold the weapon type
    private Weapon weapon;

    // Constructor
    public PlayerCharacter(
        String name,
        int[] strength, int[] dexterity, int[] constitution, int[] wisdom, int[] intelligence, int[] charisma,
        int level,
        PlayerClass userClass,
        Weapon weapon,
        Armor armor
    ) {
        
        // Super the constructor
        // Hp - half of the hp die (rounded up) times one less than the level plus the con mod per level plus the hit die
        super(
            name,  
            ( ( ((userClass.getHpDie() / 2) + 1) * (level - 1) ) + (level * constitution[1]) ) + userClass.getHpDie(), 
            armor.getAc(strength[0], dexterity[1]),
            0,
            weapon.getDice(), 
            0
        );

        

        // Set everything as nedded
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.intelligence = intelligence;
        this.charisma = charisma;

        this.userClass = userClass;
        this.weapon = weapon;
        this.armor = armor;

        this.level = level;
    }

    @Override
    public void rollInititave()
    {
        // Clear the terminal
        Simple.clearTerminal();

        // Ask for what the user want's to do
        Simple.println("How would " + super.getName() + " like to do initiative?");
        Simple.space();; // Empty space
        Simple.println("Roll own Inititave - 1");
        Simple.println("Random Number for Inititave - 2");
        Simple.space();; // Empty space

        // Prompt the user
        int choice = Simple.getIntInput("What would you like to do: ", "[12]");

        // If the number is simply random
        if (choice == 2) {

            // Roll the initiative by the super class and exit out of the method
            super.rollInititave();
            return;
        }

        // Set the initiative as the user's input
        super.setInitiative(Simple.getIntInput("Rolled Initiative: ", "[1-9][0-9]*"));
    }

    @Override
    public String toString() {
        
        String finalString =
            "Name: " + super.getName() + "\n\n" +

            "Class: " + userClass.getClassName() + "\n" +
            "Level: " + level + "\n\n" +

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
