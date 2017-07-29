package command_pattern;

import java.util.List;



/**
 * The player wants to quit
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerQuits implements Command {
    
    PlayerAction theAction;
    
    public PlayerQuits(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.quit();
    }
    
}
