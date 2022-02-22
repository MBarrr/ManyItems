package github.mbarrr.mbarrrmanyitems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.PlayerInventory;
import java.util.Set;

public class ArmourListener implements Listener {

    /*@EventHandler
    public void inventoryDragEvent(InventoryDragEvent e){
        Bukkit.broadcastMessage("dragged");
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getInventory() instanceof PlayerInventory)) return;


        Set<Integer> slots = e.getInventorySlots();


        for(int slot:slots){
            if(slot < 40 && slot > 35){
                e.setCancelled(true);

                Bukkit.broadcastMessage("Cancelled");
            }
        }
    }
    */

}
