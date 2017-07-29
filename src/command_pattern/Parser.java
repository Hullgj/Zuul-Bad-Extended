package command_pattern;



import contents_items.PlayerItems;
import contents_items.Room;
import resource_bundle.Languages;
import resource_bundle.CommandWords;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a three word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
//public class Parser extends Command
public class Parser
{
    private final CommandWords commandWords;
    private final Languages languages;
    private final Print printer;
    private List<String> user_input, user_command, user_nouns;;
    
    private static boolean wantToQuit;
    private final HashMap<String, ActionButton> commandMap;
        
    private final PlayerAction newAction;
    
    private ActionButton theInterpreter;
    
    // the receiver will perform certain actions whenever called
    private final ActionButton helpInterpreted, goInterpreted, 
            lookInterpreted, giveInterpreted, takeInterpreted, dropInterpreted,
            quitInterpreted;

    /**
     * Create a parser to read from the terminal window.
     * @param currentRoom the room the player is in
     * @param player_items the properties of the player - for initialisation
     */
    public Parser(Room currentRoom, PlayerItems player_items)
    {
        this.commandWords = new CommandWords();
        this.languages = new Languages();
        this.printer = new Print();

        this.user_input = new ArrayList<>();
        this.commandMap = new HashMap<>();
        
        this.newAction = BootCamp.getAction(currentRoom, player_items);
        
        // store the command and the receiver will perform certain actions whenever called
        this.helpInterpreted = setCommandActions(new PlayerHelp(newAction), "help");
//        helpInterpreted.setLimits(1, 3);

        this.goInterpreted = setCommandActions(new PlayerGoes(newAction), "go");
        goInterpreted.setLimits(2, 3);

        this.lookInterpreted = setCommandActions(new PlayerLooks(newAction), "look");
//        lookInterpreted.setLimits(1, 3);

        this.giveInterpreted = setCommandActions(new PlayerGives(newAction), "give");
        giveInterpreted.setLimits(3, 3);

        this.takeInterpreted = setCommandActions(new PlayerTakes(newAction), "take");
        takeInterpreted.setLimits(2, 3);

        this.dropInterpreted = setCommandActions(new PlayerDrops(newAction), "drop");
        dropInterpreted.setLimits(2, 3);

        this.quitInterpreted = setCommandActions(new PlayerQuits(newAction), "quit");
        quitInterpreted.setLimits(1, 1);        
    }

    /**
     * Link a commandWord from the user's input to an interpreted action
     * @param commandWord the command the user has chosen
     * @param action the action the interpreter connects to
     */
    private void interpreterMap(String commandWord, ActionButton action) 
    {
        commandMap.put(commandWord, action);
    }
    
    /**
     * Setup the command actions with an action button and add it to the 
     * interpreter map
     * 
     * @param command the Command wanting to connect
     * @param commandWord the word associated with the command
     * @return a new action button of the command action
     */
    private ActionButton setCommandActions(Command command, String commandWord) {
        ActionButton action_interpreter = new ActionButton(command);
        
        interpreterMap(commandWord, action_interpreter);
        
        return action_interpreter;
    }
    
    /**
     * Get the linked action from a known command word
     * @param action the type of action chosen
     * @return the chosen action as an ActionButton or null if no valid action
     */
    public ActionButton getInterpreter(String action) {
        if (commandMap.get(action) == null) 
            throw new NoSuchElementException("Specified action unknown");
        else
            return commandMap.get(action) ;
    }
    
    /**
     * We can get the user's input after processing here.
     * @return a list of strings of the user's input
     */
    public List<String> getUserInput() {
        return user_input;
    }
    
    /**
     * Set whether the user wants to quit
     * @param wantToQuit class variable that defines whether the user has quit
     */
    public static void setQuit(boolean wantToQuit) {
        Parser.wantToQuit = wantToQuit;
    }
    
    /**
     * Get the player input and link it to an Action Button
     * @return a boolean to signify whether the player wants to continue or quit
     */
    public boolean useController() {
        // get the user input
        user_input = commandWords.getCommandWords( printer.getInput() );
        
        if (user_input != null) {
            try {
                // check command word and get the key
                user_command = commandWords.getKeys(user_input.subList(0, 1));
                
                if (user_input.size() > 1) {
                    // strip all non-nouns from the user input
                    user_nouns = languages.getKeys(user_input.subList(1, user_input.size()));
                
                    // rebuild user_input with valid keys
                    user_input = user_command;
                    user_input.addAll(user_nouns);
                }
                else
                    user_input = user_command;
                

                theInterpreter = getInterpreter(user_input.get(0));

                if (theInterpreter.withinLimits( user_input, user_input.get(0)) )
                    getInterpreter(user_input.get(0)).interpret(user_input);
                else 
                    printer.printErr("err_unknown_command");
            } 
            catch (NoSuchElementException e) {
                printer.printErr("err_user_input");
            }
        }
        else 
            printer.printErr("err_unknown_command");
        
        return wantToQuit;
    }
}
