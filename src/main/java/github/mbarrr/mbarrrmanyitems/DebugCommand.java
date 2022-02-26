package github.mbarrr.mbarrrmanyitems;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DebugCommand implements CommandExecutor {

    MbarrrManyItems instance;

    public DebugCommand(MbarrrManyItems instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args[0].equalsIgnoreCase("get")) {

            int index = Integer.parseInt(args[1]);

            Player player = (Player) sender;

            player.getInventory().addItem(instance.getCustomArmour(index).getArmour());
        }

        else{
            ItemStack test = new ItemStack(Material.WOODEN_HOE);
            ItemMeta meta = test.getItemMeta();
            meta.setCustomModelData(1234567);
            test.setItemMeta(meta);
            ((Player) sender).getInventory().addItem(test);
        }

        return true;
    }
}
