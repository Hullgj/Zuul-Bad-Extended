package main;

import command_pattern.Print;
import contents_items.Room;
import command_pattern.Parser;
import contents_items.PlayerItems;
import resource_bundle.Resources;

/**
 * This class is the main class of the "World of Zuul" application. "World of
 * Zuul" is a very simple, text based adventure game. Users can walk around some
 * scenery. That's all. It should really be extended to make it more
 * interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public class Game {
    
//    private final Player player;
    private final PlayerItems player_items;
    private final PlayerItems ai_player_items;

    private final Parser parser;
    private final String[] lang;
    private final Print printer;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        // set the language
        this.lang = new String[] {"en", "US"};
        Resources.setLangLocale(lang);
        
        this.printer = new Print();
        
        // Initiate the players
        // create an ai player
        this.ai_player_items = new PlayerItems("player__ai_1", "item__blaster", "item__blaster_d", 9);
        // the player starts with no items and zero weight
        this.player_items = new PlayerItems("player__user_1", "item__apple", "item__apple_d", 2);
                
        parser = new Parser(createRooms(), player_items);
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms() {
        
        Room outside, theatre, pub, lab, office;

        // create the rooms
        outside = new Room("room_desc__outside");
        theatre = new Room("room_desc__lecture");
        pub = new Room("room_desc__pub");
        lab = new Room("room_desc__lab");
        office = new Room("room_desc__office");

        // initialise room exits
        outside.setExit("exit__east", theatre);
        outside.setExit("exit__south", lab);
        outside.setExit("exit__west", pub);
        theatre.setExit("exit__west", outside);
        pub.setExit("exit__east", outside);
        lab.setExit("exit__north", outside);
        lab.setExit("exit__east", office);
        office.setExit("exit__west", lab);
        
        
        try {
            // add items to rooms
            outside.addItem("item__notebook", "item__notebook_d", 2);
            outside.addItem("item__trap", "item__trap_d", 9);
            
            // put characters in rooms
            outside.addPlayer(ai_player_items);
        } catch (Exception ex) {
            printer.println(ex.getMessage());
        }
        
        return outside;  // start game outside
    }

    

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (!finished) {
            finished = parser.useController();
        }
    }
}
