package github.mbarrr.mbarrrmanyitems;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {

    MbarrrManyItems instance;

    public DebugCommand(MbarrrManyItems instance){
        this.instance = instance;
    }
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        int index = Integer.parseInt(args[0]);

        Player player = (Player) sender;

        player.getInventory().addItem(instance.getCustomArmour(index).getArmour());



        return true;
    }
}
