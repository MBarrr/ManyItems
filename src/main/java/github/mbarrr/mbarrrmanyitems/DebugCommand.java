package github.mbarrr.mbarrrmanyitems;

import github.mbarrr.mbarrrmanyitems.Handheld.Mounts.PhantomMount;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
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
            sender.sendMessage("starting");

            Player player = (Player) sender;

            Location loc = player.getLocation();

            CraftPlayer cp = (CraftPlayer) player;

            EntityPlayer nmsPlayer = cp.getHandle();

            World world = ((CraftWorld) loc.getWorld()).getHandle();


            PhantomMount phantomMount = new PhantomMount(EntityTypes.PHANTOM, world, nmsPlayer, 2, 5);
            phantomMount.setPosition(loc.getX(), loc.getY(), loc.getZ());

            world.addEntity(phantomMount, CreatureSpawnEvent.SpawnReason.CUSTOM);

            sender.sendMessage("stopped");
        }

        else{
            int i = Integer.parseInt(args[0]);

            ItemStack test = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta meta = test.getItemMeta();
            meta.setCustomModelData(1000000+i);
            test.setItemMeta(meta);
            ((Player) sender).getInventory().addItem(test);
        }



        return true;
    }
}
