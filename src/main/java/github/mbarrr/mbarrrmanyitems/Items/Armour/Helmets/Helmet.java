package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.Items.Armour.ArmourSlot;
import github.mbarrr.mbarrrmanyitems.Items.Armour.CustomArmour;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Helmet extends CustomArmour {

    private Material mat;

    public Helmet(int tag, MbarrrManyItems instance, String title) {
        super(ArmourSlot.HELMET, Material.WOODEN_HOE, tag, instance, title);

        this.mat = Material.WOODEN_HOE;

        int modelTag = 1000000+tag;
        setModelTag(modelTag);
    }

    @Override
    @EventHandler
    public void playerArmourEvent(InventoryClickEvent e){
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getClickedInventory() instanceof PlayerInventory)) return;

        PlayerInventory playerInventory = (PlayerInventory) e.getClickedInventory();
        InventoryAction inventoryAction = e.getAction();
        Player p = (Player) e.getWhoClicked();
        ItemStack cursorItem;

        //get correct item from event, in the case of MOVE_TO_OTHER_INVENTORY (shift clicking) another approach has to be taken, as I could not find the item from the event.
        switch(inventoryAction){
            case HOTBAR_SWAP:
                cursorItem = playerInventory.getItem(e.getHotbarButton());
                if(cursorItem == null) return;
                break;
            case MOVE_TO_OTHER_INVENTORY:
                //return if player is wearing a helmet or if item is not valid armour
                if(playerInventory.getHelmet() != null && !playerInventory.getHelmet().getType().equals(Material.AIR)) return;
                if(!checkArmourItem(e.getCurrentItem())) return;
                //cancel event and equip item
                e.setCancelled(true);
                playerInventory.setHelmet(e.getCurrentItem());
                e.getCurrentItem().setAmount(0);
                checkPutOn((Player) e.getWhoClicked());
                return;
            default:
                cursorItem = e.getCursor();
                break;
        }

        //return if clicked slot is not helmet slot
        if(e.getSlot() != 39) return;

        //return if item is null or not same material as armour
        if(cursorItem.getType() == null || !cursorItem.getType().equals(mat)) return;

        //return if item does not have correct tags
        if(!checkArmourItem(cursorItem)) return;

        //cancel event, swap armour and cursor
        e.setCancelled(true);
        ItemStack currentArmour = p.getInventory().getHelmet();

        playerInventory.setHelmet(cursorItem);
        p.getOpenInventory().setCursor(currentArmour);

        if(isPlayerWearingArmour(p)) return;

        addPlayer(p);
        playerAddEffect(p);
    }

    /**
     * Previously had dupe exploit with equiping from the offhand, fixed by cancelling event and returning if offhand is used
     * @param e
     */

    //Handles when a player puts on armour via right clicking with the item in their hand
    @Override
    @EventHandler
    public void playerRightClickArmour(PlayerInteractEvent e){
        try {
            //return if armour is in offhand
            if (!e.getHand().equals(EquipmentSlot.HAND)) {
                e.setCancelled(true);
                return;
            }

            //Return if item is null or does not have correct tags
            ItemStack item = e.getItem();
            if (item == null) return;
            if (!checkArmourItem(item)) return;

            //Swap equipped helmet with helmet item in hand
            ItemStack currentArmour = e.getPlayer().getInventory().getHelmet();
            e.getPlayer().getInventory().setHelmet(item);
            e.getPlayer().getInventory().setItemInMainHand(currentArmour);

            //Check player has armour on
            checkPutOn(e.getPlayer());
        }catch(AssertionError er){

        }
    }
}
