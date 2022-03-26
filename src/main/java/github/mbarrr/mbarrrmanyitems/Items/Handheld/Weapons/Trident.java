package github.mbarrr.mbarrrmanyitems.Items.Handheld.Weapons;

import com.shampaggon.crackshot.events.WeaponShootEvent;
import github.mbarrr.mbarrrmanyitems.Items.Handheld.CrackShotItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Trident extends CrackShotItem {


    List<PotionEffect> potionEffects = new ArrayList<>();

    public Trident() {
        super("Trident", 1000009);
        PotionEffect dolphinsGrace = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 4*20, 3);
        PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 1*20, 1);
        PotionEffect nightVision = new PotionEffect(PotionEffectType.WATER_BREATHING, 4*20, 1);
        potionEffects.add(dolphinsGrace);
        potionEffects.add(regen);
        potionEffects.add(nightVision);
    }

    @Override
    protected void onClick(WeaponShootEvent e) {
        Player player = e.getPlayer();


        if(player.isInWater()){
            player.addPotionEffects(potionEffects);
        }
        super.onClick(e);
    }

    /** TODO TEST THIS METHOD IS CALLED
     * When a player is damaged while holding a trident,
     * the damage is multipled by a factor of 1.25
     * @param e Event
     */
    @EventHandler
    protected void playerDamagedEvent(EntityDamageEvent e){
        //Return if damaged entity is not player
        if(!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        //Player is holding trident, and is not in water
        if(player.getEquipment().getItemInMainHand() == null) return;

        if(!player.isInWater() && player.getEquipment().getItemInMainHand().equals(getItem())){
            //Multiply damage by 1.25
            e.setDamage(e.getDamage()*1.25F);
        }
    }
}
