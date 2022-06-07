package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Other;

import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Sea shell horn
 * Charges with damage and provides a damage buff
 * when blown
 */

public class Conch extends CrackShotItem {

    private List<Player> activePlayers = new ArrayList<>();
    private HashMap<Player, ItemStack> playerConchMap = new HashMap<>();

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     *
     * @param itemTitle             Crackshot title of the item
     * @param customModelData       Custom model data, or 0 for none
     * @param requiresEquipListener
     */
    public Conch(String itemTitle, int customModelData, boolean requiresEquipListener) {
        super(itemTitle, customModelData, requiresEquipListener);
    }


    private boolean hasConchInHotBar(Player player){
        return getConchInHotBar(player) != null;
    }


    /**
     * Checks whether a player
     * has a conch in their hotbar
     * @param player Player to check
     * @return True if player has conch in hotbar, otherwise false
     */
    private ItemStack getConchInHotBar(Player player){
        ItemStack[] contents = player.getInventory().getContents();

        for(int i = 0; i < 9; i++){
            ItemStack item = contents[i];
            if(item.equals(getItem())) return item;
        }
        return null;
    }


    /**
     * Handles events where
     * the player picks up a conch
     * and it goes to their hotbar
     * @param e EntityPickupEvent
     */
    @EventHandler
    private void itemPickUpEvent(EntityPickupItemEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!e.getItem().equals(getItem())) return;

        Player player = (Player) e.getEntity();
        if(!hasConchInHotBar(player)) return;

        addPlayerIfNotPresent(player);
        playerConchMap.put(player, e.getItem().getItemStack());
    }

    /**
     * Handles events where
     * an active player drops
     * the conch in their hotbar
     * @param e PlayerDropItemEvent
     */
    @EventHandler
    private void conchDropEvent(PlayerDropItemEvent e){
        if(!e.getItemDrop().getItemStack().equals(getItem())) return;
        if(!activePlayers.contains(e.getPlayer())) return;

        if(!hasConchInHotBar(e.getPlayer())){
            activePlayers.remove(e.getPlayer());
            playerConchMap.remove(e.getPlayer());
        }
    }

    /**
     * Add player to player list if conch is in hotbar
     * @param e PlayerJoinEvent
     */
    @EventHandler
    private void playerJoinEvent(PlayerJoinEvent e){
        if(hasConchInHotBar(e.getPlayer())){
            activePlayers.add(e.getPlayer());
            playerConchMap.put(e.getPlayer(), getConchInHotBar(e.getPlayer()));
        }
    }

    /**
     * Remove player from active
     * conch list if they logout
     * @param e PlayerQuitEvent
     */
    @EventHandler
    private void playerLogOutEvent(PlayerQuitEvent e){
        if(activePlayers.contains(e.getPlayer())){
            activePlayers.remove(e.getPlayer());
            playerConchMap.remove(e.getPlayer());
        }

    }

    /**
     * Remove player from active
     * conch list if they die
     * with active conch
     * @param e PlayerDeathEvent
     */
    @EventHandler
    private void playerDeathEvent(PlayerDeathEvent e){
        if(activePlayers.contains(e.getEntity())){
            activePlayers.remove(e.getEntity());
            playerConchMap.remove(e.getEntity());
        }
    }

    /**
     * Adds player to active conch
     * list if they are not present
     * @param player Player to add
     */
    private void addPlayerIfNotPresent(Player player){
        if(!activePlayers.contains(player)){
            activePlayers.add(player);
            playerConchMap.put(player, getConchInHotBar(player));
        }
    }

    /**
     * Handle adding and removing
     * active players if they
     * move a conch to/from their hotbar
     * @param e InventoryClickEvent
     */
    @EventHandler
    private void conchMoveEvent(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!e.getCurrentItem().equals(getItem()) && !e.getCursor().equals(getItem())) return;

        Player player = (Player) e.getWhoClicked();

        if(activePlayers.contains(player)){
            if(!hasConchInHotBar(player)) activePlayers.remove(player);
            playerConchMap.remove(player);
        }

        else{
            if(hasConchInHotBar(player)) addPlayerIfNotPresent(player);
        }
    }

    /**
     * Handle entity damage
     * events with conch
     * involved
     * @param e
     */
    @EventHandler
    private void playerDamageEvent(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if(!activePlayers.contains(player)) return;

        chargeConch(playerConchMap.get(player), e.getDamage());
    }

    /**
     * Charge conchg
     * @param conch Conch to charge
     * @param amount Amount to charge
     */
    private void chargeConch(ItemStack conch, double amount){

    }
}