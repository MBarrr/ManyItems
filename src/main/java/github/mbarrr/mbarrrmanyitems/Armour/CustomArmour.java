package github.mbarrr.mbarrrmanyitems.Armour;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**TODO
 *
 */

public class CustomArmour implements Listener {

    String title;
    int slot;
    private int tag;
    ItemStack armourItem;
    private MbarrrManyItems instance;
    private Collection<PotionEffect> potionEffects = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    private BukkitRunnable runnable;


    public CustomArmour(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance){
        this.tag = tag;
        this.instance = instance;

        //Get integer slot from enum ArmourSlot
        ArmourSlot[] armourSlots = ArmourSlot.values();
        for(int i = 0; i < 4; i++){
            if(armourSlots[i].equals(armourSlot)) slot = i;
            }

        armourItem = new ItemStack(material);

        instance.addArmourTag(armourItem, tag);

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                check();
            }
        };

        runnable.runTaskTimer(instance, 0, 5 * 20L);

        instance.getServer().getPluginManager().registerEvents(this, instance);
        instance.addArmour(this);
    }

    public ItemStack getArmour(){
        return armourItem;
    }

    public void addEffect(PotionEffectType potionEffectType, int amplifier){
        potionEffects.add(new PotionEffect(potionEffectType, 6*20, amplifier));

    }

    //Listen for player putting on armour
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


        e.getWhoClicked().sendMessage("clicked3");


        if(!checkArmourItem(item)) return;

        e.getWhoClicked().sendMessage("clicked5");
        //Armour is valid


        Player player = (Player) e.getWhoClicked();
        if(players.contains(player)) return;
        e.getWhoClicked().sendMessage("clicked6");

        players.add(player);
        playerAddEffect(player);
    }

    private void checkPutOn(Player player){
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



    private void check(){
        for(Player player:players){

            if(checkPlayerArmour(player)){
                Bukkit.broadcastMessage("Armour is correct");
                playerRenewEffect(player);
                break;
            }

            removePlayerEffect(player);
            Bukkit.broadcastMessage("end");
        }
    }

    //Listen for player taking off armour

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if(players.contains(p)) removePlayerEffect(p);
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();


        if(checkPlayerArmour(p)){
            Bukkit.broadcastMessage("Armour is correct");
            playerAddEffect(p);
            players.add(p);
        }
    }

    //Handles when a player puts on armour via right clicking with the item in their hand
    @EventHandler
    public void playerRightClickArmour(PlayerInteractEvent e){

        ItemStack item = e.getItem();
        if(item == null) return;
        if(!checkArmourItem(item)) return;
        checkPutOn(e.getPlayer());
    }

    private boolean checkPlayerArmour(Player p){
        ItemStack playerArmour = p.getInventory().getArmorContents()[3-slot];
        return checkArmourItem(playerArmour);
    }

    private boolean checkArmourItem(ItemStack item){
        if(!(item != null && instance.hasArmourTag(item))) return false;

        if(instance.getArmourTag(item) == tag) return true;
        return false;

    }

    protected boolean isPlayerWearingArmour(Player player){
        return players.contains(player);
    }

    protected void playerAddEffect(Player player){
        player.addPotionEffects(potionEffects);

    }

    protected void removePlayerEffect(Player player){
        players.remove(player);
    }

    protected void playerRenewEffect(Player player){
        player.addPotionEffects(potionEffects);
    }

    public void setDisplayAttributes(String title, List<String> lore){
        instance.setDisplayAttributes(armourItem, title, lore);
        this.title = title;
    }

    public void addEnchantment(Enchantment enchantment, int level){
        try {
            armourItem.addEnchantment(enchantment, level);
        }catch(IllegalArgumentException e){
            Bukkit.getConsoleSender().sendMessage("Enchantment: "+enchantment.toString() +" level is too high for armour piece: "+title);
        }
    }

    public void onRestart(){
        for(Player player:players){
            removePlayerEffect(player);
        }
    }


    //Listen for player logging in with armour on

    //listen for player logging off with armour on

    //Check for player leaving


}
