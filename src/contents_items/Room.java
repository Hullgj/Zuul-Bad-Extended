package contents_items;

import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public class Room extends Items
{
    private final String room_desc;
    // Exits from the room
    private final HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". Add a hash map of exits relevant to each room
     * @param room_desc The room's description.
     */
    public Room(String room_desc) 
    {
        this.room_desc = room_desc;
        exits = new HashMap<>();
    }

    /**
     * Link a user's move from an exit to a destination room
     * @param move the direction the user has moved
     * @param dest_room the room the exit connects to
     */
    public void setExit(String move, Room dest_room) 
    {
        exits.put(move, dest_room);
    }
    
    /**
     * Get the direction of the user's move and link it to an exit in a Room
     * @param move the direction the user has moved
     * @return a Room object of the exit chosen or null if no valid exit
     */
    public Room getExits(String move) {
        return exits.get(move);
    }
    
    /**
     * Get a string of current room's exits
     * @return all relevant exits
     */
    public String getCurrentExits() {
        String currentExits = new String();
        Set<String> exitSet = exits.keySet();
        
        currentExits = exitSet.stream()
                .map(e -> " " + e)
                .reduce(currentExits, String::concat);
        
        return currentExits;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return room_desc;
    }
    
}
