package pkner.pkncoder.Classes;

public class WeaponColection {
    
    // Values used during init
    private static int[] oneDOne = {1, 1};
    private static int[] oneDFour = {1, 4};
    private static int[] oneDSix = {1, 6};
    private static int[] oneDEight = {1, 8};
    private static int[] oneDTen = {1, 10};
    private static int[] oneDTwelve = {1, 12};
    private static int[] twoDSix = {2, 6};

    private static int fiveCp = 5;
    private static int oneSp = 10;
    private static int twoSp = 20;
    private static int fiveSp = 50;
    private static int oneGp = 100;
    private static int twoGp = 200;
    private static int fiveGp = 500;
    private static int tenGp = 1000;
    private static int fifteenGp = 1500;
    private static int twentyGp = 2000;
    private static int twentyFiveGp = 2500;
    private static int thirtyGp = 3000;
    private static int fiftyGp = 5000;
    private static int seventyFiveGp = 7500;

    
    // Simple Melee
    private static Weapon club = new Weapon("Club", oneSp, oneDFour);
    private static Weapon dagger = new Weapon("Dagger", twoGp, oneDEight);
    private static Weapon greatClub = new Weapon("Great Club", twoSp, oneDEight);
    private static Weapon handAxe = new Weapon("Handaxe", fiveGp, oneDSix);
    private static Weapon javelin = new Weapon("Javelin", fiveSp, oneDSix);
    private static Weapon lightHammer = new Weapon("Light Hammer", twoSp, oneDFour);
    private static Weapon mace = new Weapon("Mace", fiveGp, oneDSix);
    private static Weapon quarterStaff = new Weapon("Quarterstaff", twoSp, oneDSix);
    private static Weapon sickle = new Weapon("Sickle", oneGp, oneDFour);
    private static Weapon spear = new Weapon("Spear", oneGp, oneDSix);

    private static Weapon[] simpleMelee = {
        club,
        dagger,
        greatClub,
        handAxe,
        javelin,
        lightHammer,
        mace,
        quarterStaff,
        sickle,
        spear
    };
    

    // Simple ramged
    private static Weapon lightCrossbow = new Weapon("Light Crossbow", twentyFiveGp, oneDEight);
    private static Weapon dart = new Weapon("Dart", fiveCp, oneDFour);
    private static Weapon shortbow = new Weapon("Shortbow", twentyFiveGp, oneDSix);
    private static Weapon sling = new Weapon("Sling", oneSp, oneDFour);

    private static Weapon[] simpleRanged = {
        lightCrossbow,
        dart,
        shortbow,
        sling
    };


    // Martial Melee
    private static Weapon battleaxe = new Weapon("Battleaxe", tenGp, oneDEight);
    private static Weapon flail = new Weapon("Flail", tenGp, oneDEight);
    private static Weapon glaive = new Weapon("Glaive", twentyGp, oneDTen);
    private static Weapon greatAxe = new Weapon("Greataxe", thirtyGp, oneDTwelve);
    private static Weapon greatSword = new Weapon("Greatsword", fiftyGp, twoDSix);
    private static Weapon halberd = new Weapon("Halberd", twentyGp, oneDTen);
    private static Weapon lance = new Weapon("Lance", tenGp, oneDTwelve);
    private static Weapon longSword = new Weapon("Longsword", fifteenGp, oneDEight);
    private static Weapon maul = new Weapon("Maul", tenGp, twoDSix);
    private static Weapon morningStar = new Weapon("Morningstar", fifteenGp, oneDEight);
    private static Weapon pike = new Weapon("Pike", fiveGp, oneDTen);
    private static Weapon rapier = new Weapon("Rapier", twentyFiveGp, oneDEight);
    private static Weapon scimitar = new Weapon("Scimitar", twentyFiveGp, oneDSix);
    private static Weapon shortsword = new Weapon("Shortsword", tenGp, oneDSix);
    private static Weapon trident = new Weapon("Trident", fiveGp, oneDSix);
    private static Weapon warPick = new Weapon("War Pick", fiveGp, oneDEight);
    private static Weapon warHammer = new Weapon("Warhammer", fifteenGp, oneDEight);
    private static Weapon whip = new Weapon("Whip", twoGp, oneDFour);

    private static Weapon[] martialMelee = {
        battleaxe,
        flail,
        glaive,
        greatAxe,
        greatSword,
        halberd,
        lance,
        longSword,
        maul,
        morningStar,
        pike,
        rapier,
        scimitar,
        shortsword,
        trident,
        warPick,
        warHammer,
        whip
    };


    // Martial Ranged
    private static Weapon blowgun = new Weapon("Blowgun", tenGp, oneDOne);
    private static Weapon handCrossbow = new Weapon("Hand Crossbow", seventyFiveGp, oneDSix);
    private static Weapon heavyCrossbow = new Weapon("Heavy Crossbow", fiftyGp, oneDTen);
    private static Weapon longbow = new Weapon("Longbow", fiftyGp, oneDEight);

    private static Weapon[] martialRanged = {
        blowgun,
        handCrossbow,
        heavyCrossbow,
        longbow
    };


    // The getters
    public static Weapon[] getSimpleMelee()
    {
        return simpleMelee;
    }

    public static Weapon[] getSimpleRanged()
    {
        return simpleRanged;
    }


    public static Weapon[] getMartialMelee()
    {
        return martialMelee;
    }

    public static Weapon[] getMartialRanged()
    {
        return martialRanged;
    }

    public static Weapon[][] getAllWeapons()
    {
        return new Weapon[][] {simpleMelee, simpleRanged, martialMelee, martialRanged};
    }
}
