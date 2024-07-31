package pkner.pkncoder.Classes;

public class PlayerClass {
    
    private String className;
    
    private int hpDie;

    public PlayerClass(String className, int hpDie) {
        this.className = className;
        this.hpDie = hpDie;
    }

    public String getClassName() {
        return className;
    }

    public int hpDie() {
        return hpDie;
    }

    @Override
    public String toString() {
        return "Name: " + className + "\nHit Die: 1d" + hpDie; 
    }

}
