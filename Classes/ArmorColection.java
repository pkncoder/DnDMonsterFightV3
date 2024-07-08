package Classes;

public class ArmorColection  {

    // No armor
    private static Armor noArmor = new Armor("No Armor", 10, false, false);


    // Light armors
    private static Armor padded = new Armor("Padded", 11, true, false);
    private static Armor leather = new Armor("Leather", 11, true, false);
    private static Armor studdedLeather = new Armor("Studded Leather", 12, true, false);

    // Light armor pre-made arrays
    private static Armor[] lightArmor = {
        padded,
        leather,
        studdedLeather
    };

    private static Armor[] lightArmorNoMeatals = {
        padded,
        leather
    };


    // Medium armors
    private static Armor hide = new Armor("Hide", 12, true, true);
    private static Armor chainShirt = new Armor("Chain Shirt", 13, true, true);
    private static Armor scaleMail = new Armor("Scale Mail", 14, true, true);
    private static Armor breastPlate = new Armor("Breast Plate", 14, true, true);
    private static Armor halfPlate = new Armor("Half Plate", 15, true, true);

    // Medium armor pre-made arrays
    private static Armor[] mediumArmor = {
        hide,
        chainShirt,
        scaleMail,
        breastPlate,
        halfPlate
    };

    private static Armor[] mediumArmorNoMeatal = {
        hide
    };

    // Heavy armors
    private static Armor ringMail = new Armor("Ring Mail", 14, false, false);
    private static Armor chainMail = new Armor("Chain Mail", 16, 13, false, false);
    private static Armor splint = new Armor("Splint", 17, 15, false, false);
    private static Armor fullPlate = new Armor("Full Plate", 18, 15, false, false);

    // Heavy armor pre-made array
    private static Armor[] heavyArmor = {
        ringMail,
        chainMail,
        splint,
        fullPlate
    };

    // Getters
    public static Armor getNoArmor()
    {
        return noArmor;
    }


    public static Armor[] getLightArmor()
    {
        return lightArmor;
    }

    public static Armor[] getLightArmorNoMeatals()
    {
        return lightArmorNoMeatals;
    }

    public static Armor[] getMediumArmor()
    {
        return mediumArmor;
    }

    public static Armor[] getMediumArmorNoMeatals()
    {
        return mediumArmorNoMeatal;
    }

    public static Armor[] getHeavyArmor()
    {
        return heavyArmor;
    }

    public static Armor[][] getAllArmors()
    {
        return new Armor[][] {lightArmor, mediumArmor, heavyArmor};
    }
}
