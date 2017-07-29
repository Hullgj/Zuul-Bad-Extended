package contents_items;

/**
 * The items that the player holds
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public class PlayerItems extends Contents {
    
    private int MAX_WEIGHT; // *** we can increase this later
    private final String player_name;
    private final String contents_name;
    private final String contents_desc;
    private final int contents_weight;
    
    /**
     * Constructor for the items a player has
     * 
     * @param player_name the name of the player
     * @param contents_name the name of the player
     * @param contents_desc the description of the object
     * @param contents_weight the weight of the object
     */
    public PlayerItems(String player_name, String contents_name, String contents_desc, int contents_weight) {
        super(contents_name, contents_desc, contents_weight);
        this.MAX_WEIGHT = 10;
        this.player_name = player_name;
        this.contents_name = contents_name;
        this.contents_desc = contents_desc;
        this.contents_weight = contents_weight;
    }
    
    /**
     * Get the player's name
     * @return the name of the player
     */
    public String getPlayerName() {
        return player_name;
    }
    
    /**
     * Make a string of the player's info
     * 
     * @return a string
     */
    public String getPlayerInfo() {
        return player_name + " " + getName() + " (" + getWeight() + ") ";
    }
        
    /**
     * Increase the max weight a player can hold
     * @param weight the new max weight the player can hold
     */
    public void setMaxWeight(int weight) {
        MAX_WEIGHT = weight;
    }
    
    /**
     * Get the player's maximum load capacity
     * @return an integer of the maximum weight a player can carry
     */
    public int getMaxWeight() {
        return MAX_WEIGHT;
    }
    
    /**
     * See if the player can pick up an item
     * @param weight the weight player is attempting to carry
     * @return true if weight is less or equals to the player's load capacity
     */
    public boolean canCarry(int weight) {
        return ( this.getTotalWeight() + weight <= this.getMaxWeight() );
    }
}
