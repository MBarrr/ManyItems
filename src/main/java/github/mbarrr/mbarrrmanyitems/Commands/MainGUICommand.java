package github.mbarrr.mbarrrmanyitems.Commands;

import github.mbarrr.mbarrrmanyitems.MainGUI;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainGUICommand implements CommandExecutor {

    private MainGUI mainGUI;

    public MainGUICommand(MbarrrManyItems instance){
        this.mainGUI = instance.getMainGUI();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length < 1) return false;

        switch(args[0]){
            case "reload":
                mainGUI.loadCustomItems();
                break;
            case "show":
                mainGUI.openInventory((Player) sender, 0);

        }




        return true;
    }
}
