package github.mbarrr.mbarrrmanyitems;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import sun.security.util.Debug;

import java.util.ArrayList;
import java.util.List;


//armour
//potion effects
//namespacedkeys affect

//use runnables to give players potion effect for a short amount of time, until they take off armour


public final class MbarrrManyItems extends JavaPlugin {

    NamespacedKey armourKey = new NamespacedKey(this, "MI-Armour");

    List<CustomArmour> armours = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("Debug").setExecutor(new DebugCommand(this));


        TestArmour testArmour = new TestArmour(ArmourSlot.Boots, Material.LEATHER_BOOTS, 0, this, PotionEffectType.SPEED, 2);





    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void addTag(NamespacedKey key, int val, ItemStack item){
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

    public boolean hasArmourTag(ItemStack armour){
        return hasTag(armour, armourKey);
    }

    public int getArmourTag(ItemStack armour){
        return getTag(armour, armourKey);
    }


    public void addArmour(CustomArmour armour){
        this.armours.add(armour);
    }

    public CustomArmour getCustomArmour(int index){
        return armours.get(index);
    }

}
