package contents_items;

/** *
 * Make objects of all items in a room
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
public class ContentItems extends Contents {
    
    /**
     * Constructor for the items in a room
     * 
     * @param contents_name the name of the object
     * @param contents_desc the description of the object
     * @param contents_weight the weight of the object
     */
    public ContentItems(String contents_name, String contents_desc, int contents_weight) {
        super(contents_name, contents_desc, contents_weight);
    }
}
