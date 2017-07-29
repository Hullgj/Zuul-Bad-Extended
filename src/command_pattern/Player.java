package command_pattern;

import contents_items.Room;
import contents_items.PlayerItems;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A receiver of the command pattern and a controller of the MVC design
 * All player actions are set here
 * The controller links to the printer, which is the view part of the MVC
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class Player implements PlayerAction {
    
    private final PlayerItems player_items;
    private PlayerItems ai_player_items;
    
    private Room currentRoom;
    
    private final Print printer;
    
    public Player(Room currentRoom, PlayerItems player_items) {
        
        this.printer = new Print();
        this.player_items = player_items;
        this.currentRoom = currentRoom;
        printer.printWelcome(currentRoom);
        
    }
    
    /**
     * The method for the help action. Print help string
     */
    @Override
    public void help() {
        
        printer.printHelp();
        
    }
    
    /**
     * The go action, moving the player through exits into other rooms
     * 
     * @param user_input a direction
     */
    @Override
    public void go(List<String> user_input) {
        
        // get the direction
        String direction = user_input.get(1);

        // Try to leave current room.
        Room nextRoom = currentRoom.getExits(direction);

        if (nextRoom == null)
            printer.printErr("err_go__no_door");
        else {
            this.currentRoom = nextRoom;
            look();
        }
    }

    /**
     * The look action, giving a set of observations of the environment/room
     */
    @Override
    public void look() {
        printer.printLook(currentRoom);
    }

    /**
     * The give action, where the player hands over one of their possessions to
     * a character or player in the room
     * 
     * @param user_input <= give notebook Venkman
     */
    @Override
    public void give(List<String> user_input) {
        // get the name of the item
        String item = user_input.get(1);
        // get the recipient
        String receiver = user_input.get(2);
        
        if (currentRoom.containsPlayer(receiver)) {
            
            if(player_items.containsItem(item)) {
                
                try {
                    // get the receiver in the room
                    ai_player_items = currentRoom.getPlayer(receiver);
                    // add the item to the receiver
                    ai_player_items.addItem(item, player_items.getItemDesc(item), player_items.getItemWeight(item));
                } catch (Exception ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                player_items.removeItem(item);
            }
            else {
                printer.printErr("err_give__dont_have " + item);
            }
        }
        else
            printer.printErr(receiver + " err_give__not_here");
    }

    /**
     * The take action, when the player picks up an item in the room
     * Will check to see if the player has enough strength to carry it
     * 
     * @param user_input <= take notebook
     */
    @Override
    public void take(List<String> user_input) {
        // get the name of the item
        String item = user_input.get(1);
        
        if(currentRoom.containsItem(item)) {
            // The item is in the room
            int item_weight = currentRoom.getItemWeight(item);
            
            if(player_items.canCarry(item_weight)) {
                try {
                    // OK we can pick it up
                    player_items.addItem(item, currentRoom.getItemDesc(item), item_weight);
                } catch (Exception ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                currentRoom.removeItem(item);
            }
            else {
                // The player is carrying too much
                printer.printErr(item + " err_take__too_heavy");
            }
        }
        else {
            printer.printErr("err_take__no " + item + " err_take__in_room");
        }
    }

    /**
     * The drop action, from the player's inventory drop an item into the room
     * 
     * @param user_input <= drop notebook
     */
    @Override
    public void drop(List<String> user_input) {
        // get the name of the item
        String item = user_input.get(1);
        
        if(player_items.containsItem(item)) {            
            try {
                currentRoom.addItem(item, player_items.getItemDesc(item), player_items.getItemWeight(item));
            } catch (Exception ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            player_items.removeItem(item);
        }
        else {
            printer.printErr("err_drop__dont_have " + item);
        }
    }

    /**
     * The quit action, gracefully exit the game
     */
    @Override
    public void quit() {
        printer.println("game__quit");
        Parser.setQuit(true);
        printer.println("game__goodbye");
    }
    
}
