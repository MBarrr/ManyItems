package github.mbarrr.mbarrrmanyitems.Items.Handheld;
import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponHitBlockEvent;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.CustomItem;
import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import net.minecraft.server.v1_16_R3.WorldGenDecoratorDungeonConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public class CrackShotItem extends CustomItem implements Listener {


    private String itemTitle;
    private boolean crackshotItemFound;
    private int customModelData;
    private List<PotionEffect> targetEffects = new ArrayList<>();
    private List<PotionEffect> userEffects = new ArrayList<>();
    private List<PotionEffect> equipEffects = new ArrayList<>();
    private List<Player> activePlayers = new ArrayList<>();

    /**
     * Generic constructor
     * Handheld items are all crackshot weapons
     * @param itemTitle Crackshot title of the item
     * @param customModelData Custom model data, or 0 for none
     * CustomItem and CrackShotItem both register listeners, this could be problematic in the future
     */
    public CrackShotItem(String itemTitle, int customModelData, boolean requiresEquipListener){
        super(requiresEquipListener);
        MbarrrManyItems instance = MbarrrManyItems.getInstance();

        this.itemTitle = itemTitle;

        //Check if crackshot item can be found
        if(instance.getCSInstance().generateWeapon(itemTitle) == null){
            Bukkit.getConsoleSender().sendMessage("Item: " + itemTitle + " not found in CS title list");
            crackshotItemFound = false;
            return;
        }

        //Set item to crackshot item
        setItem(instance.getCSInstance().generateWeapon(itemTitle));

        //If custom model data is present, add it to item
        this.customModelData = customModelData;
        if(customModelData != 0){
            ItemStack item = getItem();
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(customModelData);
            item.setItemMeta(itemMeta);
            setItem(item);
        }

        instance.getServer().getPluginManager().registerEvents(this, instance);
        crackshotItemFound = true;
    }

    /**
     * Fired when the item is fired
     * @param e Event
     */
    @EventHandler
    private void weaponShootEvent(WeaponShootEvent e){
        if(!e.getWeaponTitle().equals(itemTitle)) return;
        onShoot(e);
    }

    /**
     * Called when the fired weapon is this item
     * @param e Event
     */
    protected void onShoot(WeaponShootEvent e){
        Bukkit.broadcastMessage(itemTitle+ " CS WeaponShootEvent fired");
    }

    /**
     * Fired when a player damages another player with the item
     * @param e
     */
    @EventHandler
    private void itemHitEvent(WeaponDamageEntityEvent e){
        if(!e.getWeaponTitle().equals(itemTitle)) return;

        if(e.getDamager() instanceof Projectile){
            onProjectileHit(e);
        }
        else{
            onMeleeHit(e);
        }
    }

    /**
     * Called when a player damages another player with this item
     * @param e Event
     */
    protected void onMeleeHit(WeaponDamageEntityEvent e){
        if(!(e.getVictim() instanceof LivingEntity)) return;
        e.getPlayer().addPotionEffects(userEffects);
        ((LivingEntity) e.getVictim()).addPotionEffects(targetEffects);
    }

    protected void onProjectileHit(WeaponDamageEntityEvent e){
        Bukkit.broadcastMessage(itemTitle+ " CS WeaponDamageEntityEvent fired");
    }

    @EventHandler
    private void blockHitEvent(WeaponHitBlockEvent e){
        if(!e.getWeaponTitle().equals(itemTitle)) return;
        projectileHitBlock(e);
    }

    protected void projectileHitBlock(WeaponHitBlockEvent e){
        Bukkit.broadcastMessage(itemTitle+ " CS WeaponHitBlockEvent fired");
    }

    @Override
    protected void onEquip(Player player) {
        super.onEquip(player);
        activePlayers.add(player);
        player.addPotionEffects(equipEffects);
    }

    @Override
    protected void onDequip(Player player) {
        super.onDequip(player);
        if(activePlayers.contains(player)) activePlayers.remove(player);
    }

    @Override
    protected void renewEffects() {
        super.renewEffects();
        for(Player player:activePlayers){
            player.addPotionEffects(equipEffects);
        }
    }

    /**
     * Adds an effect that is applied to the user when hitting an entity
     * @param potionEffectType potionEffectType
     * @param duration Duration in seconds
     * @param amplifier amplifier
     */
    protected void addUserEffect(PotionEffectType potionEffectType, int duration, int amplifier){
        userEffects.add(new PotionEffect(potionEffectType, duration, amplifier));
    }
    /**
     * Adds an effect that is applied to the victim when hitting an entity
     * @param potionEffectType potionEffectType
     * @param duration Duration in seconds
     * @param amplifier amplifier
     */
    protected void addTargetEffect(PotionEffectType potionEffectType, int duration, int amplifier){
        targetEffects.add(new PotionEffect(potionEffectType, duration, amplifier));
    }
    protected void addEquipEffect(PotionEffectType potionEffectType, int amplifier){
        equipEffects.add(new PotionEffect(potionEffectType, amplifier, 5*20));
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public boolean isCrackshotItemFound(){
        return crackshotItemFound;
    }
    public boolean hasCustomModelData(){
        return !(customModelData == 0);
    }
    public int getCustomModelData(){
        return customModelData;
    }
}
