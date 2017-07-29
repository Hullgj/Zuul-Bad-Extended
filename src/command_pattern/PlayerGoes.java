package command_pattern;

import java.util.List;



/**
 * The player wants to go in a direction, normally through an exit
 * 
 * @author Gavin Hull
 * @version 2016.10.22
 */
public class PlayerGoes implements Command {
    
    PlayerAction theAction;
    
    public PlayerGoes(PlayerAction newAction) {
        theAction = newAction;
    }

    @Override
    public void execute(List<String> user_input) {
        theAction.go(user_input);
    }
    
}
