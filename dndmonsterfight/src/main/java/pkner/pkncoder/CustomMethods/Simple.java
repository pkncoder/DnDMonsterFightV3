package pkner.pkncoder.CustomMethods;

import java.util.ArrayList;
import java.util.Scanner;

public class Simple 
{
    // Our input object
    private static Scanner input = new Scanner(System.in);
    
    // Println
    public static void println(Object text)
    {
        // Println the givin text
        System.out.println("" + text);
    }

    // Print
    public static void print(Object text)
    {
        // Print the givin text
        System.out.print("" + text);
    }
    
    // Print array
    // Array
    public static void printArray(String[] array)
    {
        for (String item: array)
        {
            Simple.println(item);
        }
    }

    // Array List
    // TODO: Make it so it isn't only String arrlists allowed
    public static void printArray(ArrayList<String> array)
    {
        for (String item: array)
        {
            Simple.println(item);
        }
    }

    // Adding a break in the code, thats it im just lazy to add more ""'s
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

    // Get a string input
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

    // Get the string input with a mathcing rule
    public static String getStringInput(String querry, String rule)
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
                Simple.println("Invalid Input");
            }
        }

        // Return input at the end
        return input;
    }

    // Get a string input with a list of allowed words
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


    // Get an int input
    public static int getIntInput(Object querry)
    {
        // Print out the querry
        System.out.print("" + querry);

        // Return the user's input
        return input.nextInt();
    }

    // Get an input that makes sure that it fits the regex rule givin
    public static int getIntInput(String querry, String rule)
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
                Simple.println("Invalid Input");
            }
        }

        // Return input
        return input;
    }
}
