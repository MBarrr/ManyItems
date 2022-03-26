package github.mbarrr.mbarrrmanyitems.Items.Handheld;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class CrackShotItem extends CustomItem implements Listener {


    private String itemTitle;
    private boolean crackshotItemFound;
    private int customModelData;

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     * @param itemTitle Crackshot title of the item
     * @param customModelData Custom model data, or 0 for none
     */
    public CrackShotItem(String itemTitle, int customModelData){
        this.itemTitle = itemTitle;

        //Check if crackshot item can be found
        if(MbarrrManyItems.getInstance().getCSInstance().generateWeapon(itemTitle) == null){
            Bukkit.getConsoleSender().sendMessage("Item: " + itemTitle + " not found in CS title list");
            crackshotItemFound = false;
            return;
        }

        //Set item to crackshot item
        setItem(MbarrrManyItems.getInstance().getCSInstance().generateWeapon(itemTitle));

        //If custom model data is present, add it to item
        this.customModelData = customModelData;
        if(customModelData != 0){
            ItemStack item = getItem();
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(customModelData);
            item.setItemMeta(itemMeta);
            setItem(item);
        }

        crackshotItemFound = true;
        MbarrrManyItems.getInstance().getServer().getPluginManager().registerEvents(this, MbarrrManyItems.getInstance());
    }

    /**
     * Called when the item is used
     * @param e Event
     */
    @EventHandler
    public void weaponShootEvent(WeaponShootEvent e){
        if(!e.getWeaponTitle().equals(itemTitle)) return;

        onClick(e);
    }

    /**
     * Called when the fired weapon is this item
     * @param e Event
     */
    protected void onClick(WeaponShootEvent e){
        Bukkit.broadcastMessage(itemTitle+ " CS Event fired");

    }

    @Override
    public boolean isCrackShot() {
        return true;
    }

    public boolean isCrackshotItemFound(){
        return crackshotItemFound;
    }

    public boolean hasCustomModelData(){
        return !(customModelData == 0);
    }

    public int getCustomModelData(){
        return customModelData;
    }
}
