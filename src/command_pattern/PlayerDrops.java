package command_pattern;

import java.util.List;

/**
 * The player wants to drop an item
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerDrops implements Command {
    
    PlayerAction theAction;
    
    public PlayerDrops(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.drop(user_input);
    }
    
}
