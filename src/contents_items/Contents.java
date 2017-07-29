package contents_items;

/**
 * Contents defines objects for anything inside a room. The objects have
 * names, descriptions, weights, and other properties
 * 
 * Note: this could include exits, items, characters
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */

public class Contents extends Items {
    private final String contents_name;
    private final String contents_desc;
    private final int contents_weight;
    
    /**
     * Set the name and weight
     * @param contents_name the name
     * @param contents_desc the description of the object
     * @param contents_weight the weight of the object
     */
    public Contents(String contents_name, String contents_desc, int contents_weight) {
        this.contents_name = contents_name;
        this.contents_desc = contents_desc;
        this.contents_weight = contents_weight;
    }
    
    /**
     * Get the name of the object
     * @return a string of the name 
     */
    public String getName() {
        return contents_name;
    }
    
    /**
     * Get the description of the object
     * @return a string of the description 
     */
    public String getDesc() {
        return contents_desc;
    }
    
    /**
     * Get the weight of the object
     * 
     * @return an integer of the name 
     */
    public int getWeight() {
        return contents_weight;
    }
    
    /**
     * Make a string from the name and the weight
     * 
     * @return a string of the name and the weight
     */
    public String getNameWeight() {
        return contents_name + " (" + contents_weight + ") ";
    }
}
