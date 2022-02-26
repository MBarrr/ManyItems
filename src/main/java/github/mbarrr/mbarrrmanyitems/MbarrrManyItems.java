package github.mbarrr.mbarrrmanyitems;

import github.mbarrr.mbarrrmanyitems.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.Armour.Boots.DoubleJumpBoots;
import github.mbarrr.mbarrrmanyitems.Armour.Helmets.SlimeHead;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


//armour
//potion effects
//namespacedkeys affect

//use runnables to give players potion effect for a short amount of time, until they take off armour

/**
 * Dragon head itme
 *
 * Change modelTag to take 10000000 and add tag
 */

public final class MbarrrManyItems extends JavaPlugin {

    NamespacedKey armourKey = new NamespacedKey(this, "MI-Armour");

    List<CustomArmour> armours = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("Debug").setExecutor(new DebugCommand(this));

        DoubleJumpBoots doubleJumpBoots = new DoubleJumpBoots(0, this);
        SlimeHead slimeHead = new SlimeHead(1, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        for(CustomArmour armour:armours){
            armour.onRestart();
        }
    }

    public void addTag(NamespacedKey key, ItemStack item, int val){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, val);
        item.setItemMeta(itemMeta);
    }

    public boolean hasTag(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(key, PersistentDataType.INTEGER);
    }

    public int getTag(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.get(key, PersistentDataType.INTEGER);
    }

    public void addArmourTag(ItemStack item, int val){
        addTag(armourKey, item, val);
    }

    public boolean hasArmourTag(ItemStack armour){
        return hasTag(armour, armourKey);
    }

    public int getArmourTag(ItemStack armour){
        return getTag(armour, armourKey);
    }


    public void addArmour(CustomArmour armour){
        this.armours.add(armour);
    }

    public void addEnchantment(ItemStack itemStack){

    }

    public void setDisplayAttributes(ItemStack item, String title, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }





    public CustomArmour getCustomArmour(int index){
        return armours.get(index);
    }



}
