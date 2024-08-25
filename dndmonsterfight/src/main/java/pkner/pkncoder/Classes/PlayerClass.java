package pkner.pkncoder.Classes;

public class PlayerClass {
    
    // The name of the class
    private String className;
    
    // The hp die
    private int hpDie;

    /*
     * Class constructor
     * 
     * @param   className   the name of the class
     * @param   hpDie       the dice type for the hp die
     */
    public PlayerClass(String className, int hpDie) {

        // Set the private variables
        this.className = className;
        this.hpDie = hpDie;
    }

    /*
     * @return  the name of the class
     */
    public String getClassName() {
        return className;
    }

    /*
     * @return  the hp die type
     */
    public int getHpDie() {
        return hpDie;
    }

    /*
     * Name: {name}
     * Hit Die: 1d{hpDie type}
     * 
     * @return  The name and hp die amount in a string (as shown above)
     */
    @Override
    public String toString() {
        return (
            "Name: " + className + "\n" + 
            "Hit Die: 1d" + hpDie
        ); 
    }
}
