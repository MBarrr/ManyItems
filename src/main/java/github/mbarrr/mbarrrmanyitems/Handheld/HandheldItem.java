package github.mbarrr.mbarrrmanyitems.Handheld;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


public class HandheldItem implements Listener {

    String itemTitle;

    public HandheldItem(String itemTitle){
        this.itemTitle = itemTitle;
        MbarrrManyItems.getInstance().getServer().getPluginManager().registerEvents(this, MbarrrManyItems.getInstance());

    }

    @EventHandler
    public void weaponShootEvent(WeaponShootEvent e){
        if(!e.getWeaponTitle().equals(itemTitle)) return;

        onClick(e);
    }


    protected void onClick(WeaponShootEvent e){

    }

    public ItemStack getItem(){
        return MbarrrManyItems.getInstance().getCSInstance().generateWeapon(itemTitle);
    }
}
