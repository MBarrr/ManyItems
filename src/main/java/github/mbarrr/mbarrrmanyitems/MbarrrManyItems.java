package github.mbarrr.mbarrrmanyitems;

import com.shampaggon.crackshot.CSUtility;
import github.mbarrr.mbarrrmanyitems.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.Armour.Boots.DoubleJumpBoots;
import github.mbarrr.mbarrrmanyitems.Armour.Helmets.SlimeHead;
import github.mbarrr.mbarrrmanyitems.Handheld.Mounts.BroomStick;
import github.mbarrr.mbarrrmanyitems.Handheld.HandheldItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Dragon head itme
 *  totem
 *  epitome
 *  end crystal
 *
 * Change modelTag to take 10000000 and add tag
 */

public final class MbarrrManyItems extends JavaPlugin{

    private NamespacedKey armourKey = new NamespacedKey(this, "MI-Armour");

    private List<CustomArmour> armours = new ArrayList<>();
    List<HandheldItem> handheldItems = new ArrayList<>();

    private CSUtility csUtility;

    @Override
    public void onEnable() {
        getServer().getPluginCommand("Debug").setExecutor(new DebugCommand(this));

        try {
            csUtility = new CSUtility();
        }catch(NoClassDefFoundError e){
            Bukkit.getConsoleSender().sendMessage("********************************************************" +
                    "CSUtility FAILED TO INITIALISE IN ManyItems onEnable method. PLUGIN WILL RUN IN LIMP HOME MODE" +
                    "********************************************************");
            return;
        }

        BroomStick broomStick = new BroomStick();
        handheldItems.add(broomStick);


        // Plugin startup logic


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

    public CSUtility getCSInstance(){
        return csUtility;
    }

    public static MbarrrManyItems getInstance(){
        return (MbarrrManyItems) Bukkit.getPluginManager().getPlugin("MbarrrManyItems");
    }
}
