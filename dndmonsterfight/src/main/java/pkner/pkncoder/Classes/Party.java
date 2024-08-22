package pkner.pkncoder.Classes;

import java.util.ArrayList;

public class Party {
    
    // Hold an arraylist holding all the bases
    private ArrayList<Base> recruitments = new ArrayList<Base>();

    // The name and type of this party
    private String name;
    private String type;

    /*
     * Class Constructor
     * 
     * @param   name    The name of the party
     * @param   type    The type of party (usually "PLAYERS" or "ENEMIES")
     */
    public Party(String name, String type)
    {
        // Set the values
        this.name = name;
        this.type = type;
    }

    /*
     * Adds a new party member to the list
     * 
     * @param   newPartyMember  the base to add to the party
     */
    public void addPartyMember(Base newPartyMember)
    {
        recruitments.add(newPartyMember);
    }

    /*
     * Removes a party member from the list (assuming that it exists, if it doesn't this does nothing)
     * 
     * @param   gonePartyMember  the base to remove from the party
     */
    public void removePartyMember(Base gonePartyMember)
    {
        recruitments.remove(gonePartyMember);
    }

    /*
     * @return  The recruitment bases arraylist
     */
    public ArrayList<Base> getParty()
    {
        return recruitments;
    }

    /*
     * @return  The names of every base in the recruitment list in the form of a string array
     */
    public String[] getBaseNames() {

        // Hold an array with the same len as the amount of bases added to the arrlist
        String[] names = new String[recruitments.size()];

        // Loop over every base in the list
        for (int i = 0; i < recruitments.size(); i++) {
            names[i] = recruitments.get(i).getName(); // add the name to the array
        }

        // Finally, return names
        return names;
    }

    /*
     * @return  The party's name
     */
    public String getName()
    {
        return name;
    }

    /*
     * @return  The party's type
     */
    public String getType()
    {
        return type;
    }
}
