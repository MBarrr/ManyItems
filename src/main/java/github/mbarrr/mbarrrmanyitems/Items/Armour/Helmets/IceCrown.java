package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * MIGHT NEED TO REGISTER LISTENER ************************************
 */

/**
 * Ice crown, wearer takes 2x damage from fire,
 * attacker is slowed when attacking wearer
 *
 */
public class IceCrown extends Helmet{

    private final PotionEffect attackerFreezeEffect = new PotionEffect(PotionEffectType.SLOW, 2, 2);

    public IceCrown(MbarrrManyItems instance) {
        super(2, instance, "IceCrown");
    }

    @Override
    protected void onWearerAttacked(EntityDamageByEntityEvent e) {
        super.onWearerAttacked(e);
        if(!(e.getDamager() instanceof LivingEntity)) return;

        ((LivingEntity) e.getDamager()).addPotionEffect(attackerFreezeEffect);
    }

    @EventHandler
    protected void fireDamageEvent(EntityDamageEvent e){
        EntityDamageEvent.DamageCause damageCause = e.getCause();

        if(damageCause.equals(EntityDamageEvent.DamageCause.FIRE) || damageCause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
            e.setDamage(e.getDamage()*2);
        }
    }
}