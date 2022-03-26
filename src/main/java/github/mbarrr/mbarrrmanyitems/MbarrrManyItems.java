package github.mbarrr.mbarrrmanyitems;

import com.shampaggon.crackshot.CSUtility;
import github.mbarrr.mbarrrmanyitems.Commands.DebugCommand;
import github.mbarrr.mbarrrmanyitems.Commands.MainGUICommand;
import github.mbarrr.mbarrrmanyitems.Items.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Boots.DoubleJumpBoots;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.SlimeHead;
import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.Mounts.Flying.BroomStick;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Trident;
import mbarrr.github.guilib.GUILib;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
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
 *  do something with lightning
 *
 *
 * Change modelTag to take 10000000 and add tag
 */

public final class MbarrrManyItems extends JavaPlugin{

    private NamespacedKey armourKey = new NamespacedKey(this, "MI-Armour");

    private List<CustomItem> customItems = new ArrayList<>();

    private CSUtility csUtility;

    private GUILib guiLib;

    private MainGUI mainGUI;

    @Override
    public void onEnable() {
        guiLib = new GUILib(this);
        mainGUI = new MainGUI(guiLib, this);

        getServer().getPluginCommand("Debug").setExecutor(new DebugCommand(this));
        getServer().getPluginCommand("MainMenu").setExecutor(new MainGUICommand(this));


        try {
            csUtility = new CSUtility();
        }catch(NoClassDefFoundError e){
            Bukkit.getConsoleSender().sendMessage("********************************************************" +
                    "CSUtility FAILED TO INITIALISE IN ManyItems onEnable method. PLUGIN WILL RUN IN LIMP HOME MODE" +
                    "********************************************************");
            return;
        }

        initializeItems();
    }

    private void initializeItems(){
        BroomStick broomStick = new BroomStick();
        DoubleJumpBoots doubleJumpBoots = new DoubleJumpBoots(0, this);
        SlimeHead slimeHead = new SlimeHead(1, this);
        Trident trident = new Trident();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(CustomItem customItem:customItems){
            if(customItem instanceof CustomArmour){
                ((CustomArmour) customItem).onRestart();
            }
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
    public void setDisplayAttributes(ItemStack item, String title, List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public CSUtility getCSInstance(){
        return csUtility;
    }
    public static MbarrrManyItems getInstance(){
        return (MbarrrManyItems) Bukkit.getPluginManager().getPlugin("MbarrrManyItems");
    }
    public void addCustomItem(CustomItem customItem){
        this.customItems.add(customItem);
    }
    public List<CustomItem> getCustomItems(){
        return customItems;
    }
    public MainGUI getMainGUI() {
        return mainGUI;
    }
}
