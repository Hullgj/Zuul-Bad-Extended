package command_pattern;

import java.util.List;
import resource_bundle.CommandWords;

/**
 * Interpreter acts as the invoker, listening to what the player wants
 * and linking up a relevant action for a method to do
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class ActionButton {
    
    private final Command theCommand;
    private final CommandWords commandWords;
    private int min_words, max_words;
    
    public ActionButton(Command theCommand) {
        this.theCommand = theCommand;
        this.commandWords = new CommandWords();
        this.max_words = 3;
        this.min_words = 1;
    }
    
    public void interpret(List<String> user_input) {
        theCommand.execute(user_input);
    }
    
    /**
     * Optional. If wanting an action command word to have more than 3 and less
     * than 1; the default min and max words, you can set the limits per command.
     * For instance, useful when stripping the user input and checking if an
     * action has the right amount of words inputted.
     * 
     * @param min_words the minimum number of words a user has to enter
     * @param max_words the maximum number of words a user has to enter
     */
    public void setLimits(int min_words, int max_words) {
        this.min_words = min_words;
        this.max_words = max_words;
    }
    
    public int getMin() {
        return min_words;
    }
    
    public int getMax() {
        return max_words;
    }
    
    /**
     * As a key, check whether the user's input matches a particular command word 
     * within a desired range of required words.
     * 
     * @param user_input the array list of strings, coming from the user after processing
     * @param command_word the string to compare against, generally a valid command word
     * @return 
     */
    public boolean withinLimits(List<String> user_input, String command_word) {
        boolean equals = false;
        
        if ( user_input.get(0).equals( commandWords.checkKey(command_word) ) ) {
            
            // print an error if the user's input has less or more words desired by the command
            if (user_input.size() >= min_words || user_input.size() <= max_words)
                equals = true;
        }
        
        return equals;
    }
}
