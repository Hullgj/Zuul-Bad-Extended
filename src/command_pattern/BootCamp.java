package command_pattern;

import contents_items.PlayerItems;
import contents_items.Room;



/**
 * Returns the type of action to be used
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class BootCamp {
    
    public static PlayerAction getAction(Room currentRoom, PlayerItems player_items) {
        // we are using a Player object here as a receiver to handle everything
        return new Player(currentRoom, player_items);
    }
}
