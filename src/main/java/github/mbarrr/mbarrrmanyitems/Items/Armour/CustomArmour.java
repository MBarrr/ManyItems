package github.mbarrr.mbarrrmanyitems.Items.Armour;

import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;

/** TODO test everything since changed to inherit CustomItem
 *
 */

public class CustomArmour implements Listener {

    private String title;
    private String name;
    private int slot;
    private int tag;
    private MbarrrManyItems instance;
    private Collection<PotionEffect> potionEffects = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private BukkitRunnable runnable;
    private ItemStack item;

    public CustomArmour(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance, String name){
        this.name = name;
        this.tag = tag;
        this.instance = instance;

        //Get integer slot from enum ArmourSlot
        ArmourSlot[] armourSlots = ArmourSlot.values();
        for(int i = 0; i < 4; i++){
            if(armourSlots[i].equals(armourSlot)) slot = i;
            }

        ItemStack armourItem = new ItemStack(material);
        instance.addArmourTag(armourItem, tag);
        item = armourItem;

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                check();
            }
        };
        runnable.runTaskTimer(instance, 0, 5 * 20L);

        instance.addCustomArmour(this);
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    /**
     *     Listen for player putting on armour
     */
    @EventHandler
    public void playerArmourEvent(InventoryClickEvent e){
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getClickedInventory() instanceof PlayerInventory)) return;

        InventoryAction inventoryAction = e.getAction();
        ItemStack item;

        //get correct item from event, in the case of MOVE_TO_OTHER_INVENTORY (shift clicking) another approach has to be taken, as I could not find the item from the event.
        switch(inventoryAction){
            case PLACE_ALL:
            case PLACE_ONE:
            case SWAP_WITH_CURSOR:
                item = e.getCursor();
                break;
            case HOTBAR_SWAP:
                item = e.getClickedInventory().getItem(e.getHotbarButton());
                break;
            case MOVE_TO_OTHER_INVENTORY:
                checkPutOn((Player) e.getWhoClicked());

                return;
            default:
                return;
        }

        //return if clicked slot is not armour slot of this ite
        if(!e.getSlotType().equals(InventoryType.SlotType.ARMOR)) return;
        if(!checkArmourItem(item)) return;


        //Armour is valid
        Player player = (Player) e.getWhoClicked();
        if(players.contains(player)) return;

        players.add(player);
        playerAddEffect(player);
    }

    /**
     * Give player effects and add to list if player is wearing armour according to checkPlayerArmour
     * @param player Player
     */
    protected void checkPutOn(Player player){
        //Wait one tick and check if the player is wearing the piece of aromur
        BukkitRunnable iHateLife = new BukkitRunnable() {
            @Override
            public void run() {
                if(checkPlayerArmour(player)){
                    players.add(player);
                    playerAddEffect(player);
                }
            }
        };
        iHateLife.runTaskLater(instance, 1);
    }


    /**
     * This throws a ConcurrentModificationException when a player is dropped from the list, I'm ignoring it on account of the fact I've no idea about it
     */
    private void check(){
        List<Player> tempPlayerStore = new ArrayList<>();


        for(Player player:players){
            if (checkPlayerArmour(player)) {
                playerRenewEffect(player);
            }
            else{
                tempPlayerStore.add(player);
            }

        }

        for(Player player:tempPlayerStore){
            removePlayerEffect(player);
        }
        tempPlayerStore.clear();
    }

    /**
     *     Listen for player leaving with armour on
     */

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(players.contains(p)) removePlayerEffect(p);
    }

    /**
     * Listen for player joining with armour on
     * @param e PlayerJoinEvent
     */
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(checkPlayerArmour(p)){
            playerAddEffect(p);
            players.add(p);
        }
    }

    /**
     * Handles when a player puts on armour via right-clicking with the item in their hand
     * @param e PlayerInteractEvent
     */
    @EventHandler
    public void playerRightClickArmour(PlayerInteractEvent e){
        if(e.getHand() == null) return;
        if(!e.getHand().equals(EquipmentSlot.HAND)) {
            e.setCancelled(true);
            return;
        }

        ItemStack item = e.getItem();
        if(item == null) return;
        if(!checkArmourItem(item)) return;
        checkPutOn(e.getPlayer());
    }


    @EventHandler
    private void entityHitEvent(EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player){
            if(isPlayerWearingArmour((Player) e.getEntity())){
                onWearerAttacked(e);
            }
        }

        if(e.getDamager() instanceof Player){
            if(isPlayerWearingArmour((Player) e.getDamager())){
                onWearerAttack(e);
            }
        }
    }

    protected void onWearerAttacked(EntityDamageByEntityEvent e){

    }

    protected void onWearerAttack(EntityDamageByEntityEvent e){

    }


    /**
     * Checks whether the player is wearing the correct item in the armour slot relating to that item
     * @param p Player
     * @return True if the player is wearing the armour
     */
    private boolean checkPlayerArmour(Player p){
        ItemStack playerArmour = p.getInventory().getArmorContents()[3-slot];
        return checkArmourItem(playerArmour);
    }

    /**
     * Checks whether an item is not null and has the right armour tag
     * @param item Item to check
     * @return True if the item has the right armour tag
     */
    protected boolean checkArmourItem(ItemStack item){
        if(!(item != null && instance.hasArmourTag(item))) return false;

        return instance.getArmourTag(item) == tag;
    }



    /**
     * Checks whether player is in the player list for this armour
     * @param player Player to check
     * @return True if in list
     */
    public boolean isPlayerWearingArmour(Player player){
        return players.contains(player);
    }

    public ItemStack getItem() {
        return item;
    }

    protected void playerAddEffect(Player player){
        player.addPotionEffects(potionEffects);

    }

    protected void addPlayer(Player p){
        players.add(p);
    }

    protected void removePlayerEffect(Player player){
        players.remove(player);
    }

    protected void playerRenewEffect(Player player){
        player.addPotionEffects(potionEffects);
    }

    public String getName(){
        return name;
    }

    public void setDisplayAttributes(String title, List<String> lore){
        instance.setDisplayAttributes(item, title, lore);
        this.title = title;
    }

    public void addEffect(PotionEffectType potionEffectType, int amplifier){
        potionEffects.add(new PotionEffect(potionEffectType, 6*20, amplifier));

    }

    public void addEnchantment(Enchantment enchantment, int level){
        try {
            item.addEnchantment(enchantment, level);
        }catch(IllegalArgumentException e){
            Bukkit.getConsoleSender().sendMessage("Enchantment: "+enchantment.toString() +" level is too high for armour piece: "+title);
        }
    }

    public void onRestart(){
        for(Player player:players){
            removePlayerEffect(player);
        }
    }

    public String getTitle(){
        return title;
    }

    @SuppressWarnings("ConstantConditions")
    protected void setModelTag(int modelTag){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(modelTag);
        item.setItemMeta(itemMeta);
    }
}
