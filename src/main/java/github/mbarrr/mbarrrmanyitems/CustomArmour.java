package github.mbarrr.mbarrrmanyitems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomArmour implements Listener {


    int slot;
    private int tag;
    ItemStack armourItem;
    private MbarrrManyItems instance;
    private PotionEffect potionEffect;
    List<Player> players = new ArrayList<>();
    private BukkitRunnable runnable;


    public CustomArmour(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance, PotionEffectType potionEffectType, int amplifier){
        this.potionEffect = new PotionEffect(potionEffectType, 6*20, amplifier);
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

    public void addEffect(){

    }

    //Listen for player putting on armour
    @EventHandler
    public void playerArmourEvent(InventoryClickEvent e){
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getClickedInventory() instanceof PlayerInventory)) return;

        InventoryAction inventoryAction = e.getAction();

        ItemStack item;

        switch(inventoryAction){
            case PLACE_ALL:
                item = e.getCursor();
                break;
            case SWAP_WITH_CURSOR:
                item = e.getCursor();
                break;
            case HOTBAR_SWAP:
                item = e.getClickedInventory().getItem(e.getHotbarButton());
                break;
            case MOVE_TO_OTHER_INVENTORY:
                item = e.getCursor();
                break;
            default:
                return;
        }



        //return if clicked slot is not armour slot of this ite
        if(!e.getSlotType().equals(InventoryType.SlotType.ARMOR)) return;


        e.getWhoClicked().sendMessage("clicked3");


        //return if new armour is null or air
        if(item == null || item.getType().equals(Material.AIR)) return;


        //return if item does not have armour tag
        if(!instance.hasArmourTag(item)) return;


        e.getWhoClicked().sendMessage("clicked4");
        //return if armour tag does not match this object's
        int tag = instance.getArmourTag(item);
        if(tag != this.tag) return;

        e.getWhoClicked().sendMessage("clicked5");
        //Armour is valid


        Player player = (Player) e.getWhoClicked();
        if(players.contains(player)) return;
        e.getWhoClicked().sendMessage("clicked6");

        players.add(player);
        player.addPotionEffect(potionEffect);
    }



    private void check(){
        for(Player player:players){

            ItemStack playerArmour = player.getInventory().getArmorContents()[3-slot];

            if(playerArmour != null && instance.hasArmourTag(playerArmour)){

                if(instance.getArmourTag(playerArmour) == tag){
                    Bukkit.broadcastMessage("Armour is correct");
                    player.addPotionEffect(potionEffect);
                    break;
                }
                Bukkit.broadcastMessage("Armour is air/null, or doesnt have tag");
            }

            player.removePotionEffect(potionEffect.getType());
            players.remove(player);
            Bukkit.broadcastMessage("end");
        }
    }

    //Listen for player taking off armour



    //Listen for player logging in with armour on

    //listen for player logging off with armour on

    //Check for player leaving


}
