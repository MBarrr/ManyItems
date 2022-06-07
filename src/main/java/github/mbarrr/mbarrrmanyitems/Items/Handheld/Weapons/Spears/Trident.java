package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons.Spears;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Trident is a mid tier weapon/transport item, it is dropped by
 * poseidon. If a player receives damage while holding the weapon out of water,
 * the damage received is multiplied by a factor of 1.25, respresenting a
 * vulnerability. For this reason the trident can be countered by
 * items such as the lasso, which can draw the user out of water, giving
 * them a 1.25 vulnerabliity
 * The trident deals high damage with a low attack speed
 */

public class Trident extends CrackShotItem {


    List<PotionEffect> potionEffects = new ArrayList<>();

    public Trident() {
        super("Trident", 1000009, false);
        PotionEffect dolphinsGrace = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 4*20, 3);
        PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 1*20, 1);
        PotionEffect nightVision = new PotionEffect(PotionEffectType.WATER_BREATHING, 4*20, 1);
        potionEffects.add(dolphinsGrace);
        potionEffects.add(regen);
        potionEffects.add(nightVision);
    }

    /**
     * Fired when the trident is shot
     * @param e Event
     */
    @Override
    protected void onShoot(WeaponShootEvent e) {
        Player player = e.getPlayer();

        //If player is in water, apply potion effects
        if(player.isInWater()){
            player.addPotionEffects(potionEffects);
        }

        super.onShoot(e);
    }

    /** TODO TEST THIS METHOD IS CALLED
     * When a player is damaged while holding a trident,
     * the damage is multipled by a factor of 1.25
     * @param e Event
     */
    @Override
    protected void onUserHurt(EntityDamageByEntityEvent e) {
        super.onUserHurt(e);
        //Return if damaged entity is not player
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if(!player.isInWater() && player.getEquipment().getItemInMainHand().equals(getItem())){
            //Multiply damage by 1.25
            e.setDamage(e.getDamage()*1.25F);
        }
    }
}
