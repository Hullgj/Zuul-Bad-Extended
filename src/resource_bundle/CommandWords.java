package resource_bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds a resource bundle of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */

public class CommandWords extends Resources
{
    /**
     * Constructor - initialise the command words.
     * We use the CommandBundle as the files that take the commands
     * Languages.class set the language
     * 
     */
    public CommandWords() {
        super.setLanguage(super.getLangLocale(), "CommandBundle");
    }
    

    /**
     * From a list of strings, check whether a given String is a valid command word
     * @param command the command word to check
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String command) {
        return getValues().stream()
                .anyMatch(w -> w.equals(command));
    }
    
    /**
     * From a string split into an array list of Strings
     * 
     * @param string from the user's input, most likely a command word and nouns
     * @return null if no valid commands, otherwise the array list of words
     */
    public List<String> getCommandWords(String string) {
        String[] words;
        words = string.split(" ");
        if (isCommand(words[0])) {
            return new ArrayList<>(Arrays.asList(words));
        }
        return null;
    }
}
