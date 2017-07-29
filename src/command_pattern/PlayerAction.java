package command_pattern;

import java.util.List;



/**
 * The client
 * An interface that creates all Receivers
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public interface PlayerAction {

    public void help();

    public void go(List<String> user_input);
    
    public void look();
    
    public void give(List<String> user_input);
    
    public void take(List<String> user_input);
    
    public void drop(List<String> user_input);
    
    public void quit();
}
