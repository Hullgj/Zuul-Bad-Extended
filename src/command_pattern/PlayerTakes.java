package command_pattern;

import java.util.List;



/**
 * The player wants to take an item from the room
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerTakes implements Command {
    
    PlayerAction theAction;
    
    public PlayerTakes(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.take(user_input);
    }
    
}
