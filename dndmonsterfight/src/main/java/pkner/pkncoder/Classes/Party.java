package pkner.pkncoder.Classes;

import java.util.ArrayList;

public class Party {
    
    // Hold an arraylist holding all the characters
    private ArrayList<Character> recruitments = new ArrayList<Character>();

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
     * @param   newPartyMember  the character to add to the party
     */
    public void addPartyMember(Character newPartyMember)
    {
        recruitments.add(newPartyMember);
    }

    /*
     * Removes a party member from the list (assuming that it exists, if it doesn't this does nothing)
     * 
     * @param   gonePartyMember  the character to remove from the party
     */
    public void removePartyMember(Character gonePartyMember)
    {
        recruitments.remove(gonePartyMember);
    }

    /*
     * @return  The recruitment characters arraylist
     */
    public ArrayList<Character> getParty()
    {
        return recruitments;
    }

    /*
     * @return  The names of every character in the recruitment list in the form of a string array
     */
    public String[] getCharacterNames() {

        // Hold an array with the same len as the amount of characters added to the arrlist
        String[] names = new String[recruitments.size()];

        // Loop over every character in the list
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
