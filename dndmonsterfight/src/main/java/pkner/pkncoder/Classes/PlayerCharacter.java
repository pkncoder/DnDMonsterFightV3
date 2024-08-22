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

    /*
     * Class constructor
     * 
     * Sets all the needed variables and calls the superclass
     * 
     * @param   name            Player's name
     * @param   hp              Player's hp
     * @param   strength        [Score] | [Score Modifier]
     * @param   dexterity       [Score] | [Score Modifier]
     * @param   constitution    [Score] | [Score Modifier]
     * @param   wisdom          [Score] | [Score Modifier]
     * @param   intelligence    [Score] | [Score Modifier]
     * @param   charisma        [Score] | [Score Modifier]
     * @param   level           The Player's level
     * @param   userClass       The Player's class
     * @param   weapon          The Player's weapon
     * @param   armor           The Player's armor
     */
    public PlayerCharacter(
        String name,
        int[] strength, int[] dexterity, int[] constitution, int[] wisdom, int[] intelligence, int[] charisma,
        int level,
        PlayerClass userClass,
        Weapon weapon,
        Armor armor
    ) {
        
        // Call the Super class's constructor (base)
        // Hp - half of the hp die (rounded up) times one less than the level plus the con mod per level plus the hit die
        super(
            name,  
            ( ( ((userClass.getHpDie() / 2) + 1) * (level - 1) ) + (level * constitution[1]) ) + userClass.getHpDie(), 
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

        // Level
        this.level = level;

        // Class, weapon, armor
        this.userClass = userClass;
        this.weapon = weapon;
        this.armor = armor;
    }

    // Override the totally random initiative roll so the player has a chance to *choose* their initiative
    @Override
    public void rollInititave()
    {
        // Clear the terminal
        Simple.clearTerminal();

        // Ask for what the user want's to roll their initiative
        Simple.println("How would " + super.getName() + " like to roll initiative?");
        Simple.space();; // Empty space
        Simple.println("Roll own Inititave - 1");
        Simple.println("Random Number for Inititave - 2");
        Simple.space();; // Empty space

        // Prompt the user for their choice
        int choice = Simple.getIntInput("What would you like to do: ", "[12]");

        // If the number is simply random
        if (choice == 2) {

            // Roll the initiative by the super class and exit out of the method
            super.rollInititave();
            return;
        }

        // Else, set the initiative as the user's input
        Simple.clearTerminal();
        super.setInitiative(Simple.getIntInput("Rolled Initiative: ", "[1-9][0-9]*"));
    }

    /*
     * To string method
     * 
     * 
     * Name: {name}
     * 
     * Class: {class name}
     * Level: {level}
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
     * To Hit Modifier: +0
     * Damage Dice: {amount of dice rolled}d{dice type} + 0
     * 
     * 
     * @return  A string formatted as shown above
     */
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
