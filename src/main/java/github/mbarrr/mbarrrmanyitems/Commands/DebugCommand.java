package github.mbarrr.mbarrrmanyitems.Commands;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {

    MbarrrManyItems instance;

    public DebugCommand(MbarrrManyItems instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        return true;
    }
}
