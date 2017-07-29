package command_pattern;

 
import contents_items.PlayerItems;
import contents_items.Room;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import resource_bundle.CommandWords;
import resource_bundle.Languages;

/**
 * Print Class containing all print methods
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public class Print {
    
    private final CommandWords commandWords;
    private final Languages languages;
    private static Scanner reader;
    
    public Print() {
        this.commandWords = new CommandWords();
        this.languages = new Languages();
        reader = new Scanner(System.in);
    }

    /**
     * Print out the opening message for the player.
     * @param currentRoom the room the player is in
     */
    public void printWelcome(Room currentRoom) {
        println("game__welcome_ln_1");
        println("game__welcome_ln_2");
        println("game__welcome_ln_3");
        System.out.println();
        
        printLook(currentRoom);
    }
    
    /**
     * Print user guidance for the player's location
     * @param currentRoom the room the player is in
     */
    public void printLocation(Room currentRoom) {
        print("game__location_you_are");
        println(currentRoom.getDescription());
        print("game__location_exits"
                + currentRoom.getCurrentExits());
        System.out.println();
    }
    
    /**
     * Print a sequence of print literal strings
     * @param currentRoom the room the player is in
     */
    public void printLook(Room currentRoom) {
        printLocation(currentRoom);
        printItems(currentRoom);
        printPlayers(currentRoom);
    }
    
    /**
     * Print the room's items. From an string array, where first element is the
     * description of the item and the second is the item's weight
     * @param currentRoom the room the player is in
     */
    public void printItems(Room currentRoom) {
        if (currentRoom.countItems() > 0) {
            String[] words = currentRoom.getItems().split(" ");
            
            print("game__items");

            for (int i = 0; i < words.length - 1; i += 2)
                System.out.print(languages.getValue(words[i]) + " " + words[i+1] + " ");

            System.out.println();
        }
    }
    
    /**
     * Get and print the names and items for all players in the room
     * 
     * Development: need to get the array list of the player items from the 
     * Player class
     * 
     * @param currentRoom the room the player is in
     */
    public void printPlayers(Room currentRoom) {
        if (currentRoom.countPlayers() > 0) {
            
            print("game__players");
            
            // get the names and items for all players in the room
            List<PlayerItems> player_items = currentRoom.getPlayers();
            for(PlayerItems player : player_items) {
                
                String[] words = player.getPlayerInfo().split(" ");
                
                for (int i = 0; i < words.length - 2; i += 3)
                    System.out.print(languages.getValue(words[i]) + " " + languages.getValue(words[i+1]) + " " + words[i+2]);
                
                System.out.println();
            }
        }
    }
    
    /**
     * Print out some help information. Here we print some stupid, cryptic
     * message and a list of the command words.
     */
    public void printHelp() {
        print("game__help_ln_1");
        println("game__help_ln_2");
        System.out.println();
        print("game__help_ln_3");
        System.out.println( commandWords.showValues() );
    }
    
    
    
    /**
     * Show the user interface and get the input
     * @return the user's input as a string
     */
    public String getInput() {
        System.out.print("> ");     // print prompt
        return reader.nextLine();
    }
    
    /**
     * Print out relevant error messages
     * @param err the key of the error message
     */
    public void printErr(String err) {
        System.out.print("\nError: ");
        print(err);
        System.out.println();
    }
    
    /**
     * Prints a line of keys after converting to its value
     * 
     * @param text the input text as a string
     */
    public void println(String text) {
        List<String> words = Arrays.asList(text.split(" "));
        
        words.stream()
                .filter(w -> languages.isKey(w))
                .map(w -> languages.getValue(w))
                .forEach(System.out::println);
    }
    
    /**
     * Simple print method, allowing classes to input a built string
     * Normal escape sequences accepted
     * Can be for proper nouns, which won't get translated
     * 
     * @param text the input text as a string
     */
    public void print(String text) {
        List<String> words = Arrays.asList(text.split(" "));
        
        words.stream()
                .filter(w -> languages.isKey(w))
                .map(w -> languages.getValue(w) + " ")
                .forEach(System.out::print);
    }
    
}
