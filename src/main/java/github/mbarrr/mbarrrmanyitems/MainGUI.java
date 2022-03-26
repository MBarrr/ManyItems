package github.mbarrr.mbarrrmanyitems;

import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import mbarrr.github.guilib.GUI;
import mbarrr.github.guilib.GUILib;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MainGUI extends GUI {

    private NamespacedKey menuKey;
    private MbarrrManyItems instance;

    public MainGUI(GUILib guiLib, MbarrrManyItems instance) {
        super(guiLib, 54, "Items", instance, null);

        this.instance = instance;
        this.menuKey = new NamespacedKey(instance, "main-menu");
    }

    public void loadCustomItems(){
        getInv(0).clear();

        List<CustomItem> items = instance.getCustomItems();

        for(int i = 0; i < items.size(); i++){
            CustomItem customItem = items.get(i);
            ItemStack icon = customItem.getItem();

            List<String> lore = icon.getItemMeta().getLore();
            lore.add("********** ADMIN INFO **********");

            lore.add("CrackShot Item? " + customItem.isCrackShot());

            if (customItem.isCrackShot()) {
                CrackShotItem crackShotItem = (CrackShotItem) customItem;
                lore.add("CrackShot Item Found? " + crackShotItem.isCrackshotItemFound());
                lore.add("Has custom model data? " + crackShotItem.hasCustomModelData());

                if(crackShotItem.hasCustomModelData()){
                    lore.add("Custom model data: " + crackShotItem.getCustomModelData());
                }
            }

            ItemMeta meta = icon.getItemMeta();
            meta.setLore(lore);
            icon.setItemMeta(meta);

            addItem(icon, menuKey, 0, i, 0);
        }
    }

    @Override
    public void clickValid(InventoryClickEvent e) {
        e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
    }
}
