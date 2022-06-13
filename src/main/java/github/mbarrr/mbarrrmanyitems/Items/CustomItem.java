package github.mbarrr.mbarrrmanyitems.Items;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class CustomItem implements IItem, Listener {

    public static long EFFECT_DURATION = 5*20L;
    protected String itemTitle;
    private ItemStack item;
    private BukkitRunnable statusRunnable;

    public CustomItem(boolean requiresEquipListener, String itemTitle){
        MbarrrManyItems instance = MbarrrManyItems.getInstance();
        //Register listener if required
        instance.getServer().getPluginManager().registerEvents(this, instance);

        if(requiresEquipListener){
            statusRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    renewEffects();
                }
            };
            statusRunnable.runTaskTimer(instance, 0, EFFECT_DURATION);
        }

        instance.addCustomItem(this);
        this.itemTitle = itemTitle;
    }

    public ItemStack getItem(){
        return item;
    }

    public void setItem(ItemStack item){
        this.item = item;
    }

    @Override
    public boolean isCrackShot() {
        return (this instanceof CrackShotItem);
    }

    /**
     * Player switches to weapon
     */
    @EventHandler
    private void switchToWeaponEvent(PlayerItemHeldEvent e){
        Player player = e.getPlayer();
        Inventory inventory = player.getInventory();

        //Player swapped off item
        ItemStack previousItem = inventory.getItem(e.getPreviousSlot());

        if(previousItem != null && previousItem.equals(getItem())){
            onDequip(player);
        }

        //Player swapped onto item
        ItemStack newItem = inventory.getItem(e.getNewSlot());

        if(newItem != null && newItem.equals(getItem())){
            onEquip(player);
        }
    }

    @EventHandler
    private void userDamageEvent(EntityDamageByEntityEvent e){
        onUserHurt(e);
    }

    protected void onUserHurt(EntityDamageByEntityEvent e){

    }

    public String getItemTitle() {
        return itemTitle;
    }

    protected void onEquip(Player player){

    }

    protected void onDequip(Player player){

    }

    protected void renewEffects(){

    }
}
