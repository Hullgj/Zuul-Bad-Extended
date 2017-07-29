package command_pattern;

import java.util.List;



/**
 * The player wants to give an item to another player
 *  
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerGives implements Command {
    
    PlayerAction theAction;
    
    public PlayerGives(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.give(user_input);
    }
    
}
