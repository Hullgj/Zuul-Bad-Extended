package command_pattern;

import java.util.List;



/**
 * The player needs some helpful guidance
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerHelp implements Command {
    
    PlayerAction theAction;
    
    public PlayerHelp(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.help();
    }
    
}
