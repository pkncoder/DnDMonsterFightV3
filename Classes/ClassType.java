package Classes;

import java.util.HashMap;

public class ClassType 
{
    public static enum CLASSES
    {
        Barbarian,
        Bard,
        Cleric,
        Druid,
        Fighter,
        Monk,
        Paladin,
        Ranger,
        Rogue,
        Sorcerer,
        Warlock,
        Wizard
    }

    private static HashMap<CLASSES, Object[]> classes = new HashMap<CLASSES, Object[]>();
    
    private CLASSES userClass;
    private Object[] userHashmap;

    public ClassType(CLASSES userClass)
    {
        this.userClass = userClass;

        this.userHashmap = classes.get(userClass);
    }

    public static void initHashmap()
    {

        Armor[][] allArmor = {ArmorColection.getLightArmor(), ArmorColection.getMediumArmor(), ArmorColection.getHeavyArmor()};
        Armor[][] lightAndMedArmor = {ArmorColection.getLightArmor(), ArmorColection.getMediumArmor()};
        Armor[][] lightAndMedArmorNoMeteal = {ArmorColection.getLightArmorNoMeatals(), ArmorColection.getMediumArmorNoMeatals()};
        Armor[][] lightArmor = {ArmorColection.getLightArmor()};
        Armor[][] noArmor = {{ArmorColection.getNoArmor()}};

        

        Weapon[][] allWeapons = {
            WeaponColection.getSimpleMelee(),
            WeaponColection.getSimpleRanged(),
            WeaponColection.getMartialMelee(),
            WeaponColection.getMartialRanged()
        };
        Weapon[][] simpleWeapons = {
            WeaponColection.getSimpleMelee(),
            WeaponColection.getSimpleRanged()
        };
        Weapon[][] squishySpellcasters = {
            new Weapon[]
            {
                WeaponColection.getSimpleMelee()[1],
                WeaponColection.getSimpleRanged()[1],
                WeaponColection.getSimpleRanged()[3],
                WeaponColection.getSimpleMelee()[7],
                WeaponColection.getSimpleRanged()[0]
            }
        };

        int[] dTwelve = {1, 12};
        int[] dTen = {1, 10};
        int[] dEight = {1, 8};
        int[] dSix = {1, 6};

        classes.put(CLASSES.Barbarian, 
            new Object[] {
                dTwelve,
                lightAndMedArmor,
                allWeapons
            }
        );
        classes.put(CLASSES.Bard, 
            new Object[] {
                dEight,
                lightArmor,
                new Weapon[][] {
                    WeaponColection.getSimpleMelee(),
                    new Weapon[]
                    {
                        WeaponColection.getMartialRanged()[1],
                        WeaponColection.getMartialMelee()[7],
                        WeaponColection.getMartialMelee()[11],
                        WeaponColection.getMartialMelee()[13]
                    }
                }
            }
        );
        classes.put(CLASSES.Cleric, 
            new Object[] {
                dEight,
                lightAndMedArmor,
                simpleWeapons
            }
        );
        classes.put(CLASSES.Druid, 
            new Object[] {
                dEight,
                lightAndMedArmorNoMeteal,
                new Weapon[][] {
                    new Weapon[]
                    {
                        WeaponColection.getSimpleMelee()[0],
                        WeaponColection.getSimpleMelee()[1],
                        WeaponColection.getSimpleRanged()[1],
                        WeaponColection.getSimpleMelee()[4],
                        WeaponColection.getSimpleMelee()[6],
                        WeaponColection.getSimpleMelee()[7],
                        WeaponColection.getMartialMelee()[12],
                        WeaponColection.getSimpleMelee()[8],
                        WeaponColection.getSimpleRanged()[3],
                        WeaponColection.getSimpleMelee()[9]
                    }
                }
            }
        );
        classes.put(CLASSES.Fighter, 
            new Object[] {
                dTen,
                allArmor,
                allWeapons
            }
        );
        classes.put(CLASSES.Monk, 
            new Object[] {
                dEight,
                noArmor,
                new Weapon[][] {
                    WeaponColection.getSimpleMelee(),
                    new Weapon[]
                    {
                        WeaponColection.getMartialMelee()[13]
                    }
                }
            }
        );
        classes.put(CLASSES.Paladin, 
            new Object[] {
                dTen,
                allArmor,
                allWeapons
            }
        );
        classes.put(CLASSES.Ranger, 
            new Object[] {
                dTen,
                lightAndMedArmor,
                allWeapons
            }
        );
        classes.put(CLASSES.Rogue, 
            new Object[] {
                dEight,
                lightArmor,
                new Weapon[][] {
                    WeaponColection.getSimpleMelee(),
                    new Weapon[]
                    {
                        WeaponColection.getMartialRanged()[1],
                        WeaponColection.getMartialMelee()[7],
                        WeaponColection.getMartialMelee()[11],
                        WeaponColection.getMartialMelee()[13]
                    }
                }
            }
        );
        classes.put(CLASSES.Sorcerer, 
            new Object[] {
                dSix,
                noArmor,
                squishySpellcasters
            }
        );
        classes.put(CLASSES.Warlock, 
            new Object[] {
                dEight,
                lightArmor,
                simpleWeapons
            }
        );
        classes.put(CLASSES.Wizard, 
            new Object[] {
                dSix,
                noArmor,
                squishySpellcasters
            }
        );
    }

    public HashMap<CLASSES, Object[]> getHashmap()
    {
        return classes;
    }

    public CLASSES getUserClass()
    {
        return userClass;
    }

    public Object[] getUserHashmap()
    {
        return userHashmap;
    }

    public int[] getHpDie() 
    {
        return (int[]) classes.get(userClass)[0];
    }
}
