package pkner.pkncoder.CustomMethods;

import java.util.ArrayList;
import java.util.Scanner;

public class Simple 
{
    // Our input object
    private static Scanner input = new Scanner(System.in);
    
    /*
     * Prints the data's to string with another line at the end
     * 
     * @param   data    the object to be printed out
     */
    public static void println(Object data)
    {
        // Println the givin data
        System.out.println("" + data);
    }

    /*
     * Prints the data's to string
     * 
     * @param   data    the object to be printed out
     */
    public static void print(Object data)
    {
        // Print the givin data
        System.out.print("" + data);
    }
    
    /*
     * Prints every item in a string array
     * 
     * @param   array   the array of strings to be printed
     */
    public static void printArray(String[] array)
    {
        // Loop every item and print it out
        for (String item: array)
        {
            Simple.println(item);
        }
    }

    /*
     * Prints every item in a string arr list
     * 
     * @param   array   the arr list of strings to be printed
     */
    public static void printArray(ArrayList<String> array)
    {
        // Loop every item and print it out
        for (String item: array)
        {
            Simple.println(item);
        }
    }

    // Adding a break in the code, thats it, I'm just lazy to add ""
    public static void space()
    {
        System.out.println();
    }

    // Clear the terminal
    public static void clearTerminal()
    {
        // Weird escape codes
        System.out.print("\033[H\033[2J");
    }

    /*
     * Gets an input without any input validation
     * 
     * @param   querry  the data to querry the user with
     * @return  the value gotten by the user
     */
    public static String getStringInput(Object querry)
    {
        // If not empty string
        // Else print nothing
        if (!querry.equals(""))
        {
            // Print out the querry
            System.out.print("" + querry);
        }

        // Return the user's input
        return input.nextLine();
    }

    /*
     * Gets an input with regex input validation
     * 
     * @param   querry          the data to querry the user with
     * @param   errorMessage    the message presented when the validation doesn't pass
     * @param   rule            the regex that the user has to match with
     * @return                  the value gotten by the user
     */
    public static String getStringInput(String querry, String errorMessage, String rule)
    {
        // Empty string as a temp
        String input = "";

        // While the input is still empty
        while (input.equals(""))
        {
            // Get the input temp as the user's input
            String inputTemp = Simple.getStringInput(querry);

            // check to see if it matches the rule ser
            if (inputTemp.matches(rule))
            {
                // If it does set it as the true input
                input = inputTemp;
            }

            // If it doesn't then print invalid input
            else
            {            
                Simple.println(errorMessage);
            }
        }

        // Return input at the end
        return input;
    }

    /*
     * Gets an input with input validation of a string array and a choice of case sensitivity
     * 
     * @param   querry  the data to querry the user with
     * @param   allowedWords    the array of allowed words
     * @param   errorMessage    the error message printed when the input validation isn't met
     * @param   caseSensitive   if the validation is case sensitive or not
     * @return                  the value gotten by the user
     */
    public static String getStringInput(String querry, String[] allowedWords, String errorMessage, boolean caseSensitive)
    {
        // Check to see if we are not case sensitive
        if (!caseSensitive) {

            // Loop the allowed words
            for (int i = 0; i < allowedWords.length; i++)
            {
                // Set each word to uppercase
                allowedWords[i] = allowedWords[i].toUpperCase();
            }
        }

        // Hold a varaible keeping it empty
        String input = "";
        
        // Create a loop that uses a default input value, and whenever it is set (accepted input), finish
        while (input.equals("")) 
        {
            // Get the user's unchecked input
            String userMaybeInput = getStringInput(querry);

            // Check to see if we need to set it to uppercase
            if (!caseSensitive) { userMaybeInput = userMaybeInput.toUpperCase(); }

            // Now loop over every word that is allowed to check them
            for (String word: allowedWords)
            {
                // See if it is equal to the word we want
                if (word.equals(userMaybeInput))
                {
                    input = userMaybeInput;
                    break;
                }
            }

            if (input.equals("")) { System.out.println(errorMessage); }
        }

        // At the end of everything, return our input
        return input;
    }


    /*
     * Gets an int input without any input validation
     * 
     * @param   querry  the querry to ask the user with
     * @retrurn         The int gotten from the user
     */
    public static int getIntInput(Object querry)
    {
        // Print out the querry
        System.out.print("" + querry);

        // Return the user's input
        return input.nextInt();
    }

    /*
     * Gets an int input with a regex input validation rule
     * 
     * @param   querry          the querry to ask the user with
     * @param   errorMessage    the message presented when the validation doesn't pass
     * @param   rule            the rule to match with
     * @return                  The int gotten from the user through the rule
     */
    public static int getIntInput(String querry, String errorMessage, String rule)
    {
        // Set an input value at min value (because I need a default to test)
        int input = Integer.MIN_VALUE;

        // Have a whileloop that continues while input equal to the min value
        while (input == Integer.MIN_VALUE)
        {
            // Have an input temp that gets the string (so less errors) of the input
            String inputTemp = Simple.getStringInput(querry);

            // Checks to see if it matches the rule
            if (inputTemp.matches(rule))
            {
                // If it does then set it as the int value of input temp
                input = Integer.valueOf(inputTemp);
            }
            
            // If it doesn't then print invalid input
            else
            {
                Simple.println(errorMessage);
            }
        }

        // Return input
        return input;
    }

    /*
     * Returns a 'random' int between a min and max
     * 
     * @param   min Minimum number
     * @param   max Maximum number
     * @return      Random value between min and max
     */
    public static int randomInt(int min, int max) {
        // Return the random number
        // (int) (Math.Random * (max - min + 1)) + min
        return (int) ((Math.random() * (max - min + 1)) + min);
    }
}
