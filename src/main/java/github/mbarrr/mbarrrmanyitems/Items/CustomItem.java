package github.mbarrr.mbarrrmanyitems.Items;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.inventory.ItemStack;

public class CustomItem implements IItem{

    private ItemStack item;


    public CustomItem(){
        MbarrrManyItems.getInstance().addCustomItem(this);
    }

    public ItemStack getItem(){
        return item;
    }

    public void setItem(ItemStack item){
        this.item = item;
    }

    @Override
    public boolean isCrackShot() {
        return false;
    }
}
