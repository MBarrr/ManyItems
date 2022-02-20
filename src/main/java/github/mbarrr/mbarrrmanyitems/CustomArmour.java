package github.mbarrr.mbarrrmanyitems;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomArmour implements Listener {


    int slot;
    private int tag;
    ItemStack item;
    private MbarrrManyItems instance;
    private PotionEffect potionEffect;
    List<Player> players = new ArrayList<>();


    public CustomArmour(ArmourSlot armourSlot, Material material, int tag, MbarrrManyItems instance, PotionEffectType potionEffectType, int amplifier){
        this.potionEffect = new PotionEffect(potionEffectType, 1000, amplifier);
        this.tag = tag;
        this.instance = instance;

        //Get integer slot from enum ArmourSlot
        ArmourSlot[] armourSlots = ArmourSlot.values();
        for(int i = 0; i < 4; i++){
            if(armourSlots[i].equals(armourSlot)) slot = i;
            }

        item = new ItemStack(material);

        instance.getServer().getPluginManager().registerEvents(this, instance);

        instance.addArmour(this);
    }

    public ItemStack getArmour(){
        return item;
    }

    public void addEffect(){

    }

    //Listen for player putting on armour
    @EventHandler
    protected void playerArmourEvent(InventoryClickEvent e){
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getInventory() instanceof PlayerInventory)) return;
        if(!(e.getClickedInventory() instanceof  PlayerInventory)) return;

        //return if clicked slot is not armour slot of this item
        if(e.getSlot()-100 != slot) return;

        //get player inventory and armour
        ItemStack item = e.getCurrentItem();

        //return if item does not have armour tag
        if(!instance.hasArmourTag(item)) return;

        //return if armour tag does not match this object's
        int tag = instance.getArmourTag(item);
        if(tag != this.tag) return;

        //Armour is valid


        Player player = (Player) e.getWhoClicked();
        if(players.contains(player)) return;

        players.add(player);
        player.addPotionEffect(potionEffect);
    }

    @EventHandler
    private void playerTakeOffArmour(InventoryClickEvent e){
        //return if clicked inventory is not player's inventory or if clicker is not player
        if(!(e.getWhoClicked() instanceof Player)) return;
        if(!(e.getInventory() instanceof PlayerInventory)) return;
        if(!(e.getClickedInventory() instanceof  PlayerInventory)) return;

        //return if clicked slot is not armour slot of this item
        if(e.getSlot()-100 != slot) return;

        Player player = (Player) e.getWhoClicked();

        if(!players.contains(player)) return;

        ItemStack[] armour = player.getInventory().getArmorContents();



        if(!instance.hasArmourTag(armour[slot])){
            //remove effect
            player.removePotionEffect(potionEffect.getType());
            players.remove(player);
        }

        if(instance.getArmourTag(armour[slot]) != tag){
            //remove effect
            player.removePotionEffect(potionEffect.getType());
            players.remove(player);
        }

    }




    //Listen for player taking off armour



    //Listen for player logging in with armour on

    //listen for player logging off with armour on

    //Check for player leaving


}
