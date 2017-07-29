package contents_items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the abstract class for processing and storage of the items
 * 
 * Development: Need to put the Array Lists of contents in ContentItems and 
 * player_list in PlayerItems
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public abstract class Items {
    // An array of all the contents
    private final ArrayList<Contents> contents;
    // An array of all the players
    private final ArrayList<PlayerItems> players_list;
    
    /**
     * Constructor for the map of all physical objects in the game
     */
    public Items() {
        this.contents = new ArrayList<>();
        this.players_list = new ArrayList<>();
    }
    
    /**
     * Add an item to an object. First check if the object already has an item with
     * that name. If it has a duplicate inventory, tell user.  
     * @param name The name of the item
     * @param desc The description of the item
     * @param weight The item's weight
     */
    public void addItem(String name, String desc, int weight) throws Exception {
        if(!contents.stream().anyMatch(c -> c.getName().equals(name))) {
            contents.add(new ContentItems(name, desc, weight));
        }
        else {
            throw new Exception("err_item__ln_1" + "err_item__ln_2 " + name + " err_item__ln_3");
        }
    }
    
    /**
     * Add a player item with player name, item name, item description, item weight
     * 
     * @param player the object to add
     * @throws Exception if the ArrayList being added already has that player
     */
    public void addPlayer(PlayerItems player) throws Exception {
        if(!players_list.stream().anyMatch(c -> c.getPlayerName().equals(player.getPlayerName()))) {
            
            // add to the player list
            players_list.add(new PlayerItems(player.getPlayerName(), player.getName(), player.getDesc(), player.getWeight()));
            
            // add to the player's contents list
            player.addItem(player.getName(), player.getDesc(), player.getWeight());
        }
        else {
            throw new Exception("err_item__ln_1" + "err_item__ln_2 " + player.getPlayerName() + " err_item__ln_3");
        }
    }
    
    /**
     * Count the number of players in an object
     * @return a long of the number of items
     */
    public long countPlayers() {
        return players_list.stream()
                .count();
    }
    
    /**
     * Does the object contain a character
     * @param name of the character
     * @return the item's weight or 0 if none
     */
    public boolean containsPlayer(String name) {
        return players_list.stream()
                .anyMatch(p -> p.getPlayerName().equals(name));
    }
    
    /**
     * Get player based on player name
     * @param player_name the name of the player
     * @return the player's items object
     */
    public PlayerItems getPlayer(String player_name) {
        return players_list.stream()
                .filter(p -> p.getPlayerName().equals(player_name))
                .findAny()
                .orElse(null);
    }
    
    /**
     * Get the objects of all players
     * @return the player's items object
     */
    public List<PlayerItems> getPlayers() {
        return players_list.stream()
                .collect(Collectors.toList());
    }
    
    /**
     * Get all items held by the player
     * @return a list of strings of the items
     */
    public List<String> getPlayerItems() {
        return players_list.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());
    }
    
    /**
     * Does the object contain an item
     * @param name of the item
     * @return true if object has the item
     */
    public boolean containsItem(String name) {
        return contents.stream()
                .anyMatch(c -> c.getName().equals(name));
    }
    
    /**
     * Count the number of items in an object
     * @return a long of the number of items
     */
    public long countItems() {
        return contents.stream()
                .count();
    }
    
    /**
     * Print name and weight of the items
     */
    public String getItems() {
        String items = new String();
        return contents.stream()
                .map(c -> c.getNameWeight())
                .reduce(items, String::concat);
    }
    
    /**
     * Get item description from the name
     * @param name the name of the item
     * @return the description of the item
     */
    public String getItemDesc(String name) {
        return contents.stream()
                .filter(c -> c.getName().equals(name))
                .map(c -> c.getDesc())
                .findFirst()
                .get();
    }
    
    /**
     * Get the item weight
     * @param name the name of the item
     * @return the weight of the item
     */
    public int getItemWeight(String name) {
        return contents.stream()
                .filter(c -> c.getName().equals(name))
                .mapToInt(c -> c.getWeight())
                .findFirst()
                .getAsInt();
    }
    
    /**
     * Get the objects total weight
     * @return an integer of the sum of all the weights
     */
    public int getTotalWeight() {
        return contents.stream()
                .mapToInt(c -> c.getWeight())
                .sum();
    }
    
    /**
     * Remove an item from the object
     * @param name of the item in the object
     * @return the string of the item removed if in the object, otherwise null
     */
    public boolean removeItem(String name) {
        return contents.removeIf(c -> c.getName().equals(name));
    }
    
}
