package github.mbarrr.mbarrrmanyitems;

import com.shampaggon.crackshot.CSUtility;
import github.mbarrr.mbarrrmanyitems.Commands.DebugCommand;
import github.mbarrr.mbarrrmanyitems.Commands.MainGUICommand;
import github.mbarrr.mbarrrmanyitems.Items.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Boots.DoubleJumpBoots;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.Helmet;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.SlimeHead;
import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears.Trident;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Swords.EbonyBlade;
import mbarrr.github.guilib.GUILib;
import org.bukkit.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Dragon head itme
 *  totem
 *  epitome
 *  end crystal
 *  do something with lightning
 *  airdrop/alien artifact
 *weapon turns enemies into chickens
 *
 * Change modelTag to take 10000000 and add tag
 */

public final class MbarrrManyItems extends JavaPlugin{

    private NamespacedKey armourKey = new NamespacedKey(this, "MI-Armour");

    private List<CustomItem> customItems = new ArrayList<>();
    private List<CustomArmour> customArmours = new ArrayList<>();

    private CSUtility csUtility;

    private GUILib guiLib;

    private MainGUI mainGUI;

    @SuppressWarnings("ConstantConditions")
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
                    "CSUtility FAILED TO INITIALISE IN ManyItems onEnable method. PLUGIN WILL RUN IN LIMP MODE" +
                    "********************************************************");
            return;
        }

        initializeItems();
    }

    private void initializeItems(){
        DoubleJumpBoots doubleJumpBoots = new DoubleJumpBoots(0, this);
        SlimeHead slimeHead = new SlimeHead(1, this);
        Trident trident = new Trident();
        EbonyBlade ebonyBlade = new EbonyBlade();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(CustomArmour customArmour:customArmours){
            customArmour.onRestart();
        }
    }

    public CustomArmour getArmour(String armourName){
        for(CustomArmour customArmour:customArmours){
            if(customArmour.getName().equals(armourName)){
                return customArmour;
            }
        }
        return null;
    }

    /**
     * Get the display ItemStack associated with a custom item from it's name, display item has no custom attributes except for model data
     * @param name Name of the CustomItem object
     * @return @Nullable The display itemstack, or null if not found
     */
    public ItemStack getDisplayCustomItem(String name){
        for(CustomItem item:customItems){
            if(item.getItemTitle().equals(name)){
                //Get CustomModelData and clear item meta
                ItemStack displayItem = item.getItem();
                int modelID = displayItem.getItemMeta().getCustomModelData();
                displayItem.setItemMeta(null);

                //Set CustomModelData for empty meta
                ItemMeta meta = displayItem.getItemMeta();
                meta.setCustomModelData(modelID);
                displayItem.setItemMeta(meta);

                return displayItem;
            }
        }
        return null;
    }

    /**
     * Get the display ItemStack associated with a custom armour from it's name, display item has no custom attributes except for model data
     * @param name Name of the CustomArmour object
     * @return @Nullable The display itemstack, or null if not found
     */
    public ItemStack getCustomArmourDisplayItem(String name){
        for(CustomArmour item:customArmours){
            if(item.getName().equals(name)){
                ItemStack displayItem = item.getItem();

                if(item instanceof Helmet && displayItem.getItemMeta().hasCustomModelData()){
                    int modelID = displayItem.getItemMeta().getCustomModelData();
                    displayItem.setItemMeta(null);
                    ItemMeta meta = displayItem.getItemMeta();
                    meta.setCustomModelData(modelID);
                    displayItem.setItemMeta(meta);
                }

                else{
                    displayItem.setItemMeta(null);
                }

                return displayItem;
            }
        }
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    public void addTag(NamespacedKey key, @NotNull ItemStack item, int val){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, val);
        item.setItemMeta(itemMeta);
    }
    @SuppressWarnings("ConstantConditions")
    public boolean hasTag(@NotNull ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(key, PersistentDataType.INTEGER);
    }
    @SuppressWarnings("ConstantConditions")
    public int getTag(@NotNull ItemStack item, NamespacedKey key){
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
        //noinspection ConstantConditions
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
    public void addCustomArmour(CustomArmour customArmour){
        customArmours.add(customArmour);
    }
}
