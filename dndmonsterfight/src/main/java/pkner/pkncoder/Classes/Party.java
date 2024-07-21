package pkner.pkncoder.Classes;

import java.util.ArrayList;

public class Party {
    
    // Hold an arraylist holding all the bases
    private ArrayList<Base> recruitments = new ArrayList<Base>();

    // Hold the name and type of this party
    private String name;
    private String type;

    // Constructor
    public Party(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    // Adding a new party member
    public void addPartyMember(Base newPartyMember)
    {
        recruitments.add(newPartyMember);
    }

    // Removing a party member
    public void removePartyMember(Base gonePartyMember)
    {
        recruitments.remove(gonePartyMember);
    }

    // Getters
    // Returning the total party
    public ArrayList<Base> getParty()
    {
        return recruitments;
    }

    // Returning an array with every base name
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
    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }
}
