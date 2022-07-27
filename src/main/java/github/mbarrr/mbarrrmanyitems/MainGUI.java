package github.mbarrr.mbarrrmanyitems;

import github.mbarrr.mbarrrmanyitems.Items.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets.Helmet;
import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import mbarrr.github.guilib.GUI;
import mbarrr.github.guilib.GUILib;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
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

            addItem(icon, menuKey, i, i, 0);
        }

        loadCustomHelmets(items.size());
    }

    public void loadCustomHelmets(int j){
        List<Helmet> items = instance.getCustomHelmets();

        for(int i = j; i < items.size(); i++){
            CustomArmour customItem = items.get(i);
            ItemStack icon = customItem.getItem();

            addItem(icon, menuKey, i, i, 0);
        }
    }

    @Override
    public void clickValid(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();

        CustomItem customItem = instance.getCustomItems().get(clickedItem.getItemMeta().getPersistentDataContainer().get(menuKey, PersistentDataType.INTEGER));

        e.getWhoClicked().getInventory().addItem(customItem.getItem());

    }
}
