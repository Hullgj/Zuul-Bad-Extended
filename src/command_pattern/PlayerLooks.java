package command_pattern;

import java.util.List;



/**
 * The player wants to inspect the room
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerLooks implements Command {
    
    PlayerAction theAction;
    
    public PlayerLooks(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.look();
    }
    
}
