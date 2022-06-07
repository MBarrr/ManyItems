package github.mbarrr.mbarrrmanyitems.Items.Armour.Helmets;

import github.mbarrr.mbarrrmanyitems.MbarrrManyItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Demon horns
 * Wearer takes no fire damage,
 * damager is set on fire on attack
 */
public class DemonHorns extends Helmet{

    /**
     * Generic constructor
     * @param instance Instance
     */
    public DemonHorns(MbarrrManyItems instance) {
        super(3, instance, "DemonHorns");
    }

    /**
     * Cancel fire damage event
     * for players wearing horns
     * @param e EntityDamageEvent
     */
    @EventHandler
    protected void fireDamageEvent(EntityDamageEvent e){
        EntityDamageEvent.DamageCause damageCause = e.getCause();

        if(damageCause.equals(EntityDamageEvent.DamageCause.FIRE) || damageCause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
            e.setCancelled(true);
        }
    }

    /**
     * Set attacker on fire
     * when attacking wearer
     * @param e EntityDamageByEntityEvent
     */
    @Override
    protected void onWearerAttacked(EntityDamageByEntityEvent e) {
        super.onWearerAttacked(e);
        e.getDamager().setFireTicks(2*20); //Set attacker on fire for 2 seconds
    }
}
